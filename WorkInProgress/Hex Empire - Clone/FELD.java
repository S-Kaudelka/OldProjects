public class FELD
{
    public BILD Skin;
    
    public String Typ;
    
    public FELD(String Typ_p)
    {
        Typ = Typ_p;
    }
    
    public void BildAnzeigen(int u, int r)
    {
        if(Skin != null)
        {
            Skin.SetzeY(2000);
        }
        
        Skin = new BILD("images\\f"+Typ+".png");
        Skin.SetzeX(r*50);
        Skin.SetzeY(u*50);
    }
}