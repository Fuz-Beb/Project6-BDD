package Justice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Permet d'effectuer les accès à la table jury.
 */
public class TableJuryProces
{
    private static PreparedStatement stmtSelect;
    private static PreparedStatement stmtExisteJuryProces;
    private static PreparedStatement stmtInsertJuryProces;
    private static PreparedStatement stmtUpdateOcuppeJuryProces;
    private static PreparedStatement stmtProcesMemeJour;
    private Connexion cx;
    
    /**
     * Création d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     * 
     * @param cx
     * @throws SQLException
     */
    public TableJuryProces(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtSelect = cx.getConnection()
                .prepareStatement("select * from \"JuryProces\"");
        stmtExisteJuryProces = cx.getConnection()
                .prepareStatement("select * from \"JuryProces\" where \"Jury_id\" = ? and \"Proces_id\" = ?");
        stmtInsertJuryProces = cx.getConnection()
                .prepareStatement("insert into \"JuryProces\" (\"Jury_id\", \"Proces_id\")" + "values (?,?)");
        stmtUpdateOcuppeJuryProces = cx.getConnection().prepareStatement("update \"Jury\" set \"occupe\" = true where \"nas\" = ?");
        stmtProcesMemeJour = cx.getConnection()
                .prepareStatement("select * from \"Proces\" where \"date\" = ?");
    }
    
    /**
     * Retourne la commande associée
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Affiche la liste des jurys disponible
     * 
     * @return String
     * @throws SQLException
     * @throws IFT287Exception
     */
    public ArrayList<TupleJuryProces> affichage() throws SQLException, IFT287Exception
    {
        ArrayList<TupleJuryProces> listJury = new ArrayList<TupleJuryProces>();

        ResultSet rset = stmtSelect.executeQuery();

        if (rset.next())
        {
            do
            {
                // Ajout de chacun des juges dans la liste
                listJury.add(getJuryProces(new TupleJury(rset.getInt(1)), new TupleProces(rset.getInt(2))));
            }
            while (rset.next());
        }
        rset.close();
        return listJury;
    }
    
    /**
     * Assigner un proces à un jury
     * 
     * @param tupleProces
     * @param tupleJury
     * @throws SQLException
     * @throws IFT287Exception
     */
    public void assignerProces(TupleJury tupleJury, TupleProces tupleProces) throws SQLException, IFT287Exception
    {
        stmtExisteJuryProces.setInt(1, tupleJury.getNas());
        stmtExisteJuryProces.setInt(2, tupleProces.getId());

        ResultSet rset = stmtExisteJuryProces.executeQuery();

        if (rset.next())
        {
            throw new IFT287Exception(
                    "Le jury " + tupleJury.getNas() + " est déjà assigné au procès " + tupleProces.getId());
        }

        stmtInsertJuryProces.setInt(1, tupleJury.getNas());
        stmtInsertJuryProces.setInt(2, tupleProces.getId());
        stmtInsertJuryProces.executeUpdate();
        
        stmtUpdateOcuppeJuryProces.setInt(1, tupleJury.getNas());
        stmtUpdateOcuppeJuryProces.executeUpdate();
    }

    /**
     * Verification d'un jury qui aurait un procès le meme jour qu'un proces à assigner
     * 
     * @param tupleJury 
     * @param tupleProces 
     * @return boolean
     * @throws SQLException 
     * @throws IFT287Exception 
     */
    public boolean memeJour(TupleJury tupleJury, TupleProces tupleProces) throws SQLException, IFT287Exception
    {
        ArrayList<TupleProces> listeProcesMemeDate = recuperationProcesMemeDate(tupleProces);
        
        // Comparaison pour savoir si le jury à un procès à la même date
        
        return comparaisonJuryMemeDate(tupleJury, tupleProces, listeProcesMemeDate);
    }

    /**
     * @param tupleJury
     * @param listeProcesMemeDate
     * @throws SQLException
     */
    private boolean comparaisonJuryMemeDate(TupleJury tupleJury, TupleProces tupleProces, ArrayList<TupleProces> listeProcesMemeDate)
            throws SQLException
    {
        ResultSet rset;
        stmtExisteJuryProces.setInt(1, tupleJury.getNas());
        
        for (int boucle = 0; boucle < listeProcesMemeDate.size() ; boucle++)
        {
            stmtExisteJuryProces.setInt(2, listeProcesMemeDate.get(boucle).getId());
            rset = stmtExisteJuryProces.executeQuery();
            if (rset.next())
            {
                rset.close();
                return true;
            }
            
            rset.close();
        }
        
        return false;
    }

    /**
     * @param tupleProces
     * @return
     * @throws SQLException
     * @throws IFT287Exception
     */
    private ArrayList<TupleProces> recuperationProcesMemeDate(TupleProces tupleProces)
            throws SQLException, IFT287Exception
    {
        ArrayList<TupleProces> listeProcesMemeDate = new ArrayList<TupleProces>();
        
        // Récupération de tout les Procès qui sont à la même date que le procès à assigner
        stmtProcesMemeJour.setDate(1, TableProces.getProces(tupleProces).getDate());
        ResultSet rset = stmtProcesMemeJour.executeQuery();
        
        if (rset.next())
        {
            do
            {
                // Ajout de chacun des proces dans la liste
                listeProcesMemeDate.add(TableProces.getProces(new TupleProces(rset.getInt(1))));
            }
            while (rset.next());
        }
        
        rset.close();
        return listeProcesMemeDate;
    }
    
    /**
     * Objet jury associé à un jury de la base de données
     * 
     * @param tupleJury
     * @param tupleProces 
     * @return TupleJury
     * @throws SQLException
     * @throws IFT287Exception
     */
    public TupleJuryProces getJuryProces(TupleJury tupleJury, TupleProces tupleProces) throws SQLException, IFT287Exception
    {
        TupleJuryProces tupleJuryProces = null;
        stmtExisteJuryProces.setInt(1, tupleJury.getNas());
        stmtExisteJuryProces.setInt(2, tupleProces.getId());
        ResultSet rset = stmtExisteJuryProces.executeQuery();

        if (rset.next()) {
            tupleJuryProces = new TupleJuryProces(rset.getInt(1), rset.getInt(2));
        }

        rset.close();
        return tupleJuryProces;
}
}