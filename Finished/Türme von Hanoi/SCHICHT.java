public class SCHICHT
{
    public BILD Skin;
    public int Hoehe; //0 ganz unten
    
    public SCHICHT(int h)
    {
        Hoehe = h;
        Skin = new BILD("Schicht.png");
        Skin.SetzeBreite((8-h)*20);
    }
}