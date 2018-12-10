public class WELLEN
{
    private SPIEL s;
    private GEGNER g;

    public int nGeg; //nummer des zu erzeugenden Gegners
    
    public WELLEN(SPIEL spiel)
    {
        s = spiel;
    }

    public void WelleSchicken()
    {
        s.Gegner[nGeg] = new GEGNER(s.Spieler);
        nGeg++;
        if(nGeg == s.Wellengegner)
        {
            s.WelleSchickenWahr = false;
        }
    }
}