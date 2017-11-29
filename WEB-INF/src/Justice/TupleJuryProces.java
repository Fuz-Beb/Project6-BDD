package Justice;

/**
 * Permet de représenter un tuple de la table jury.
 */
public class TupleJuryProces
{
    private int jury_id;
    private int proces_id;

    /**
     * Constructeur par défaut
     */
    public TupleJuryProces()
    {
    }

    /**
     * Constructeur de confort
     * 
     * @param jury_id
     * @param proces_id
     */
    public TupleJuryProces(int jury_id, int proces_id)
    {
        super();
        this.jury_id = jury_id;
        this.proces_id = proces_id;
    }

    /**
     * @return the jury_id
     */
    public int getJury_id()
    {
        return jury_id;
    }

    /**
     * @param jury_id the jury_id to set
     */
    public void setJury_id(int jury_id)
    {
        this.jury_id = jury_id;
    }

    /**
     * @return the proces_id
     */
    public int getProces_id()
    {
        return proces_id;
    }

    /**
     * @param proces_id the proces_id to set
     */
    public void setProces_id(int proces_id)
    {
        this.proces_id = proces_id;
    }
}