package Justice;

import java.util.ArrayList;

/**
 * Gestion des transactions de la table jury.
 */
public class GestionJury
{
    private TableJury jury;
    private Connexion cx;

    /**
     * Constructeur de confort
     * 
     * @param jury
     * @throws IFT287Exception
     */
    public GestionJury(TableJury jury) throws IFT287Exception
    {
        this.cx = jury.getConnexion();

        this.jury = jury;
    }

    /**
     * Ajout d'une jury dans la base de données
     * 
     * @param tupleJury
     * @throws Exception
     */
    public void ajouter(TupleJury tupleJury) throws Exception
    {
        try
        {
            if (jury.existe(tupleJury))
                throw new IFT287Exception("Jury existe déjà : " + tupleJury.getNas());
            jury.ajouter(tupleJury);
            
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Afficher la liste des jurys disponible
     * 
     * @return ArrayList<TupleJury>
     *
     * @throws Exception
     */
    public ArrayList<TupleJury> affichage() throws Exception
    {
        ArrayList<TupleJury> tupleJury = null;

        try
        {
            tupleJury = jury.affichage();

            cx.commit();

            return tupleJury;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}