package Justice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Permet d'effectuer les accès à la table partie.
 */
public class TablePartie
{
    private PreparedStatement stmtExistePartie;
    private PreparedStatement stmtRetourneAllPartie;
    private PreparedStatement stmtInsertPartie;
    private Connexion cx;

    /**
     * Constructeur de confort. Creation d'une instance. Précompilation
     * d'énoncés SQL.
     * 
     * @param cx
     * @throws SQLException
     */
    public TablePartie(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExistePartie = cx.getConnection().prepareStatement("select * from \"Partie\" where \"id\" = ?");
        stmtRetourneAllPartie = cx.getConnection().prepareStatement("select * from \"Partie\"");
        stmtInsertPartie = cx.getConnection().prepareStatement(
                "insert into \"Partie\" (\"id\", \"prenom\", \"nom\", \"Avocat_id\") values (?,?,?,?)");
    }

    /**
     * Retourner la connexion associée.
     * 
     * @return Connexion
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Objet juge associé à un partie de la base de données
     * 
     * @param tuplePartie
     * @return TuplePartie
     * @throws SQLException
     * @throws IFT287Exception
     */
    public TuplePartie getPartie(TuplePartie tuplePartie) throws SQLException, IFT287Exception
    {
        stmtExistePartie.setInt(1, tuplePartie.getId());
        ResultSet rset = stmtExistePartie.executeQuery();

        if (rset.next())
            tuplePartie = new TuplePartie(tuplePartie.getId(), rset.getString(2), rset.getString(3), rset.getInt(4));

        rset.close();
        return tuplePartie;
    }

    /**
     * Vérifie si un partie existe.
     * 
     * @param tuplePartie
     * @return boolean
     * @throws SQLException
     */
    public boolean existe(TuplePartie tuplePartie) throws SQLException
    {
        stmtExistePartie.setInt(1, tuplePartie.getId());
        ResultSet rset = stmtExistePartie.executeQuery();
        boolean partieExiste = rset.next();
        rset.close();
        return partieExiste;
    }

    /**
     * Ajout d'un nouveau partie
     * 
     * @param tuplePartie
     * @throws SQLException
     */
    public void ajout(TuplePartie tuplePartie) throws SQLException
    {
        /* Ajout du partie. */
        stmtInsertPartie.setInt(1, tuplePartie.getId());
        stmtInsertPartie.setString(2, tuplePartie.getPrenom());
        stmtInsertPartie.setString(3, tuplePartie.getNom());
        stmtInsertPartie.setInt(4, tuplePartie.getAvocat_id());
        stmtInsertPartie.executeUpdate();
    }

    /**
     * Retourne toutes les parties de la base de données
     * 
     * @return ArrayList<TupleParie>
     * @throws SQLException
     * @throws IFT287Exception
     */
    public ArrayList<TuplePartie> retourneAll() throws SQLException, IFT287Exception
    {
        ArrayList<TuplePartie> listPartie = new ArrayList<TuplePartie>();
        ResultSet rset = stmtRetourneAllPartie.executeQuery();

        if (rset.next())
        {
            do
            {
                listPartie.add(getPartie(new TuplePartie(rset.getInt(1))));
            }
            while (rset.next());
        }
        rset.close();

        return listPartie;
    }
}
