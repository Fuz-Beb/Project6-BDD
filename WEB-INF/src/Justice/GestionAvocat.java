package Justice;

import java.util.ArrayList;

/**
 * Gestion des transactions de la table avocat.
 */
public class GestionAvocat
{
    private TableAvocat avocat;
    private Connexion cx;

    /**
     * Constructeur de confort
     * 
     * @param avocat
     */
    public GestionAvocat(TableAvocat avocat)
    {
        this.cx = avocat.getConnexion();
        this.avocat = avocat;
    }

    /**
     * Ajout d'un nouvelle avocat dans la base de données
     * 
     * @param tupleAvocat
     * @throws Exception
     */
    public void ajouter(TupleAvocat tupleAvocat) throws Exception
    {
        try
        {
            // Vérifie si l'avocat existe déjà
            if (avocat.existe(tupleAvocat))
                throw new IFT287Exception("L'avocat existe déjà : " + tupleAvocat.getId());

            avocat.ajouter(tupleAvocat);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
    
    /**
     * Afficher la liste des avocats
     * 
     * @return ArrayList<TupleJury>
     *
     * @throws Exception
     */
    public ArrayList<TupleAvocat> affichage() throws Exception
    {
        ArrayList<TupleAvocat> tupleAvocat = null;

        try
        {
            tupleAvocat = avocat.affichage();

            cx.commit();

            return tupleAvocat;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}