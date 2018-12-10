public class SPIELER
{
    public BILD Doodle;
    
    public int SprungPositionY;
    public int FallGeschw;
    public int SprungGeschw;
    public int Sprunghoehe;
    public boolean Faellt;
    
    public SPIELER()
    {
        Doodle = new BILD("Doodle.jpg");
        Doodle.SetzeBreite(45);
        Doodle.SetzeHoehe(50);
        Doodle.SetzeX((SPIEL.Breite-Doodle.LeseBreite())/2);
        Doodle.SetzeY(SPIEL.Hoehe-Doodle.LeseHoehe());
        
        SprungPositionY = Doodle.LeseY();
        FallGeschw = 5;
        SprungGeschw = 5;
        Sprunghoehe = 75;
        Faellt = false;
    }
}