package Justice;

import java.util.ArrayList;

/**
 * Gestion des transaction de la table juge.
 */
public class GestionJuge
{
    private TableJuge juge;
    private TableProces proces;
    private Connexion cx;

    /**
     * Constructeur de confort
     * 
     * @param juge
     * @param proces
     * @throws IFT287Exception
     */
    public GestionJuge(TableJuge juge, TableProces proces) throws IFT287Exception
    {
        this.cx = juge.getConnexion();

        if (juge.getConnexion() != proces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de juge et de proces n'utilisent pas la même connexion au serveur");

        this.juge = juge;
        this.proces = proces;
    }

    /**
     * Ajout d'un nouveau juge dans la base de données
     * 
     * @param tupleJuge
     * 
     * @throws Exception
     */
    public void ajouter(TupleJuge tupleJuge) throws Exception
    {
        try
        {
            if (juge.existe(tupleJuge))
                throw new IFT287Exception("Le juge existe déjà : " + tupleJuge.getId());

            juge.ajouter(tupleJuge);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Afficher la liste des juges actifs et disponibles
     * 
     * @return ArrayList<TupleJuge>
     * @throws Exception
     */
    public ArrayList<TupleJuge> affichage() throws Exception
    {
        ArrayList<TupleJuge> tupleJuge = null;
        try
        {
            tupleJuge = juge.affichage();
            cx.commit();
            return tupleJuge;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Retourne l'ensemble des juges
     * 
     * @return ArrayList<TupleJuge>
     * @throws Exception
     */
    public ArrayList<TupleJuge> affichageAll() throws Exception
    {
        ArrayList<TupleJuge> tupleJuge = null;
        try
        {
            tupleJuge = juge.affichageAll();
            cx.commit();
            return tupleJuge;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Retourne l'ensemble des juges
     * 
     * @return ArrayList<TupleJuge>
     * @throws Exception
     */
    public ArrayList<TupleJuge> affichageAllAll() throws Exception
    {
        ArrayList<TupleJuge> tupleJuge = null;
        try
        {
            tupleJuge = juge.affichageAllAll();
            cx.commit();
            return tupleJuge;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Retirer un juge
     * 
     * @param tupleJuge
     * @throws Exception
     */
    public void retirer(TupleJuge tupleJuge) throws Exception
    {
        try
        {
            if (!juge.existe(tupleJuge))
                throw new IFT287Exception("Juge inexistant : " + tupleJuge.getId());
            if (proces.jugeEnCours(tupleJuge))
                throw new IFT287Exception("Le juge " + tupleJuge.getId() + " n'a pas terminé tout ses procès");
            juge.retirer(tupleJuge);

            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}