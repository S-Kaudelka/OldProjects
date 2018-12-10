public class KARTE
{
    public BILD Skin;

    public String Typ = "";
    
    public boolean offen;

    public KARTE()
    {
        Skin = new BILD("kVerdeckt.png");
    }

    public void Aufdecken()
    {
        int x = Skin.LeseX();
        int y = Skin.LeseY();
        Skin.SetzeY(2000);
        Skin = new BILD("k"+Typ+".png");
        Skin.SetzeKoord(x, y);
        offen = true;
    }

    public void Verdecken()
    {
        if(!offen) return;
        int x = Skin.LeseX();
        int y = Skin.LeseY();
        Skin.SetzeY(2000);
        Skin = new BILD("kVerdeckt.png");
        Skin.SetzeKoord(x, y);
        offen = false;
    }
}