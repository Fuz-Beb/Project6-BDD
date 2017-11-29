package Justice;

import java.util.ArrayList;

/**
 * Gestion des transactions de la table juryProces.
 */
public class GestionJuryProces
{
    private TableProces proces;
    private TableJuryProces juryProces;
    private Connexion cx;

    /**
     * Constructeur de confort
     * 
     * @param jury
     * @param proces
     * @param juryProces 
     * @throws IFT287Exception
     */
    public GestionJuryProces(TableJury jury, TableProces proces, TableJuryProces juryProces) throws IFT287Exception
    {
        this.cx = jury.getConnexion();

        if (jury.getConnexion() != proces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de juge et de proces n'utilisent pas la même connexion au serveur");
        if (jury.getConnexion() != juryProces.getConnexion())
            throw new IFT287Exception(
                    "Les instances de juge et de proces n'utilisent pas la même connexion au serveur");

        this.proces = proces;
        this.juryProces = juryProces;
    }

    /**
     * Assigner un proces à un jury
     * 
     * @param tupleProces
     * @param tupleJury
     * @throws Exception
     */
    public void assignerProces(TupleJury tupleJury, TupleProces tupleProces) throws Exception
    {
        try
        {
            if (!proces.existe(tupleProces))
                throw new IFT287Exception("Proces n'existe pas : " + tupleProces.getId());
            if (!proces.devantJury(tupleProces))
                throw new IFT287Exception("Le proces " + tupleProces.getId() + " doit se tenir devant un juge seul");
            if(juryProces.memeProces(tupleJury, tupleProces))
                throw new IFT287Exception("Le jury " + tupleJury.getNas() + " est déjà assigné au procès " + tupleProces.getId());
            if (juryProces.memeJour(tupleJury, tupleProces))
                throw new IFT287Exception("Le jury " + tupleJury.getNas() + " a déjà un procès en même temps que le procès " + tupleProces.getId());
            juryProces.assignerProces(tupleJury, tupleProces);
            
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
    public ArrayList<TupleJuryProces> affichage() throws Exception
    {
        ArrayList<TupleJuryProces> tupleJuryProces = null;

        try
        {
            tupleJuryProces = juryProces.affichage();

            cx.commit();

            return tupleJuryProces;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}