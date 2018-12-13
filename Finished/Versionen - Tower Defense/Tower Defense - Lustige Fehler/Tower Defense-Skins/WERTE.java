public class WERTE
{
    public String SkinName;
    public int Leben;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust;
    public int GoldWert;
    
    // Wenn ein Neuer Gegner Hinzugefuegt wird, in SPIEL am Ende des Konstruktors die Werte erhoehen,
    // Die Liste in WELLEN ergaenzen
    
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
            SkinName = "gSpaeher";
            Leben = 40;
            Speed = 4;
            Ruestung = 3;
            Lebensverlust = 1;
            GoldWert = 20;
        }
        if(Nummer == 3)
        {
            SkinName = "gSchwertkaempfer";
            Leben = 70;
            Speed = 1;
            Ruestung = 10;
            Lebensverlust = 1;
            GoldWert = 30;
        }
        if(Nummer == 4)
        {
            SkinName = "gVeteran";
            Leben = 200;
            Speed = 2;
            Ruestung = 30;
            Lebensverlust = 3;
            GoldWert = 80;
        }
        if(Nummer == 5)
        {
            SkinName = "gKoenigswache";
            Leben = 30;
            Speed = 2;
            Ruestung = 60;
            Lebensverlust = 2;
            GoldWert = 20;
        }
        if(Nummer == 6)
        {
            SkinName = "gPalastwache";
            Leben = 70;
            Speed = 2;
            Ruestung = 30;
            Lebensverlust = 2;
            GoldWert = 20;
        }
        if(Nummer == 7)
        {
            SkinName = "gHellebardier";
            Leben = 45;
            Speed = 2;
            Ruestung = 15;
            Lebensverlust = 1;
            GoldWert = 10;
        }
        if(Nummer == 8)
        {
            SkinName = "gElitekaempfer";
            Leben = 100;
            Speed = 2;
            Ruestung = 30;
            Lebensverlust = 1;
            GoldWert = 10;
        }
        if(Nummer == 9)
        {
            SkinName = "gRitter";
            Leben = 70;
            Speed = 4;
            Ruestung = 10;
            Lebensverlust = 1;
            GoldWert = 10;
        }
        if(Nummer == 10)
        {
            SkinName = "gGepanzerterreiter";
            Leben = 90;
            Speed = 4;
            Ruestung = 15;
            Lebensverlust = 1;
            GoldWert = 10;
        }
        if(Nummer == 11)
        {
            SkinName = "gPaladin";
            Leben = 100;
            Speed = 4;
            Ruestung = 25;
            Lebensverlust = 2;
            GoldWert = 10;
        }
        if(Nummer == 12)
        {
            SkinName = "g";
            Leben = 0;
            Speed = 0;
            Ruestung = 0;
            Lebensverlust = 0;
            GoldWert = 0;
        }
    }
}
