public class FELD
{
    public BILD Skin;
    public boolean Mine;
    public int MinenAngrenzend;
    public boolean markiert;

    public FELD(int r, int u)
    {
        Skin = new BILD("fNeu.png");
        Skin.SetzeHoehe(25);
        Skin.SetzeBreite(25);
        Skin.SetzeX(r*25);
        Skin.SetzeY(u*25);
    }

    /* 
     * Gibt zurueck, ob eine Mine beruehrt wurde
     * Parameter gibt an, ob Aufdecken(true) oder Markieren(false)
     */
    public boolean Beruehrt(boolean modus)
    {
        int x = Skin.LeseX();
        int y = Skin.LeseY();
        if(!modus) //markieren
        {
            if(!markiert)
            {
                markiert = true;
                Skin = new BILD("fMarkiert.png");
                Skin.SetzeX(x);
                Skin.SetzeY(y);
                Skin.SetzeHoehe(25);
                Skin.SetzeBreite(25);
                return false;
            }
            else
            {
                markiert = false;
                Skin = new BILD("fNeu.png");
                Skin.SetzeX(x);
                Skin.SetzeY(y);
                Skin.SetzeHoehe(25);
                Skin.SetzeBreite(25);
                return false;
            }
        }
        if(Mine)
        {
            Skin = new BILD("fMine.png");
            Skin.SetzeX(x);
            Skin.SetzeY(y);
            Skin.SetzeHoehe(25);
            Skin.SetzeBreite(25);
            return true;
        }
        else
        {
            Skin = new BILD("fOffen" + MinenAngrenzend + ".png");
            Skin.SetzeX(x);
            Skin.SetzeY(y);
            Skin.SetzeHoehe(25);
            Skin.SetzeBreite(25);
        }
        return false;
    }
}