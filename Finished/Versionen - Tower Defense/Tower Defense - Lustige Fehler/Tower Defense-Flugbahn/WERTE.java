public class WERTE
{
    public String SkinName;
    public int Leben;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust;
    public int GoldWert;
    public WERTE(int Nummer)
    {
        if(Nummer == 0)
        {
            SkinName = "gSpeertraeger";
            Leben = 15;
            Speed = 2;
            Ruestung = 1;
            Lebensverlust = 1;
            GoldWert = 10;
        }
        if(Nummer == 1)
        {
            SkinName = "gSoldat";
            Leben = 40;
            Speed = 1;
            Ruestung = 5;
            Lebensverlust = 1;
            GoldWert = 20;
        }
        if(Nummer == 2)
        {
            SkinName = "gRitter";
            Leben = 50;
            Speed = 5;
            Ruestung = 3;
            Lebensverlust = 1;
            GoldWert = 25;
        }
        if(Nummer == 3)
        {
            SkinName = ".PNG";
            Leben = 0;
            Speed = 0;
            Ruestung = 0;
            Lebensverlust = 0;
            GoldWert = 0;
        }
    }
}
