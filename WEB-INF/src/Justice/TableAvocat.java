package Justice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Permet d'effectuer les accès à la table avocat.
 */
public class TableAvocat
{
    private static PreparedStatement stmtExiste;
    private static PreparedStatement stmtInsert;
    private static PreparedStatement stmtSelectAll;
    private Connexion cx;


    /**
     * Création d'une instance. Des énoncés SQL pour chaque requête sont
     * précompilés.
     * 
     * @param cx
     * @throws SQLException
     */
    public TableAvocat(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement("select * from \"Avocat\" where \"id\" = ?");
        stmtInsert = cx.getConnection()
                .prepareStatement("insert into \"Avocat\" (id, prenom, nom, type) values (?,?,?,?)");
        stmtSelectAll = cx.getConnection().prepareStatement("select * from \"Avocat\" ORDER BY id");
    }

    /**
     * Retourner la connexion associée
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si l'avocat existe
     * 
     * @param tupleAvocat
     * @return boolean
     * @throws SQLException
     */
    public boolean existe(TupleAvocat tupleAvocat) throws SQLException
    {
        stmtExiste.setInt(1, tupleAvocat.getId());
        ResultSet rset = stmtExiste.executeQuery();
        boolean avocatExiste = rset.next();
        rset.close();
        return avocatExiste;
    }

    /**
     * Ajout d'un nouvelle avocat dans la base de données
     * 
     * @param tupleAvocat
     * @throws SQLException
     */
    public void ajouter(TupleAvocat tupleAvocat) throws SQLException
    {
        stmtInsert.setInt(1, tupleAvocat.getId());
        stmtInsert.setString(2, tupleAvocat.getPrenom());
        stmtInsert.setString(3, tupleAvocat.getNom());
        stmtInsert.setInt(4, tupleAvocat.getType());
        stmtInsert.executeUpdate();
    }

    /**
     * Retourne la liste des avocats
     * 
     * @return ArrayList<TupleAvocat>
     * @throws SQLException 
     * @throws IFT287Exception 
     */
    public ArrayList<TupleAvocat> affichage() throws SQLException, IFT287Exception
    {
        ArrayList<TupleAvocat> listAvocat = new ArrayList<TupleAvocat>();
        ResultSet rset = stmtSelectAll.executeQuery();

        if (rset.next())
        {
            do
            {
                listAvocat.add(getAvocat(new TupleAvocat(rset.getInt(1))));
            }
            while (rset.next());
        }
        rset.close();

        return listAvocat;
    }

    /**
     * Objet avocat associé à un avocat de la base de données
     * 
     * @param tupleAvocat
     * @return TupleAvocat
     * @throws SQLException
     * @throws IFT287Exception
     */
    private TupleAvocat getAvocat(TupleAvocat tupleAvocat) throws SQLException, IFT287Exception
    {
        stmtExiste.setInt(1, tupleAvocat.getId());
        ResultSet rset = stmtExiste.executeQuery();

        if (rset.next())
            tupleAvocat = new TupleAvocat(tupleAvocat.getId(), rset.getString(2), rset.getString(3), rset.getInt(4));

        rset.close();
        return tupleAvocat;
    }
}