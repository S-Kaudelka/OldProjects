public class WERTE
{
    public String SkinName;
    public int Leben;
    public int Speed;
    public int Ruestung;
    public int Lebensverlust; //Leben, das der Spieler Verliert, wenn der Gegner durchkommt
    public int GoldWert;
    
    // Wenn ein Neuer Gegner Hinzugefuegt wird, die Liste in WELLEN ergaenzen
    //Nicht mehr als Speed 5
    
    public static void GegnerWerteBeimErzeugen(int Nummer, GEGNER G)
    {
        if(Nummer == 0)
        {
            G.SkinName = "gSpeertraeger";
            G.Leben = 15;
            G.Speed = 2;
            G.Ruestung = 1;
            G.Lebensverlust = 1;
            G.GoldWert = 10;
        }
        if(Nummer == 1)
        {
            G.SkinName = "gSoldat";
            G.Leben = 40;
            G.Speed = 1;
            G.Ruestung = 5;
            G.Lebensverlust = 1;
            G.GoldWert = 20;
        }
        if(Nummer == 2)
        {
            G.SkinName = "gSpaeher";
            G.Leben = 40;
            G.Speed = 4;
            G.Ruestung = 3;
            G.Lebensverlust = 1;
            G.GoldWert = 20;
        }
        if(Nummer == 3)
        {
            G.SkinName = "gSchwertkaempfer";
            G.Leben = 70;
            G.Speed = 1;
            G.Ruestung = 10;
            G.Lebensverlust = 1;
            G.GoldWert = 30;
        }
        if(Nummer == 4)
        {
            G.SkinName = "gVeteran";
            G.Leben = 200;
            G.Speed = 2;
            G.Ruestung = 30;
            G.Lebensverlust = 3;
            G.GoldWert = 80;
        }
        if(Nummer == 5)
        {
            G.SkinName = "gKoenigswache";
            G.Leben = 30;
            G.Speed = 2;
            G.Ruestung = 60;
            G.Lebensverlust = 2;
            G.GoldWert = 20;
        }
        if(Nummer == 6)
        {
            G.SkinName = "gPalastwache";
            G.Leben = 70;
            G.Speed = 2;
            G.Ruestung = 30;
            G.Lebensverlust = 2;
            G.GoldWert = 20;
        }
        if(Nummer == 7)
        {
            G.SkinName = "gHellebardier";
            G.Leben = 45;
            G.Speed = 2;
            G.Ruestung = 15;
            G.Lebensverlust = 1;
            G.GoldWert = 10;
        }
        if(Nummer == 8)
        {
            G.SkinName = "gElitekaempfer";
            G.Leben = 100;
            G.Speed = 2;
            G.Ruestung = 30;
            G.Lebensverlust = 1;
            G.GoldWert = 10;
        }
        if(Nummer == 9)
        {
            G.SkinName = "gRitter";
            G.Leben = 70;
            G.Speed = 4;
            G.Ruestung = 10;
            G.Lebensverlust = 1;
            G.GoldWert = 10;
        }
        if(Nummer == 10)
        {
            G.SkinName = "gGepanzerterreiter";
            G.Leben = 90;
            G.Speed = 4;
            G.Ruestung = 15;
            G.Lebensverlust = 1;
            G.GoldWert = 10;
        }
        if(Nummer == 11)
        {
            G.SkinName = "gPaladin";
            G.Leben = 100;
            G.Speed = 4;
            G.Ruestung = 25;
            G.Lebensverlust = 2;
            G.GoldWert = 10;
        }
        if(Nummer == 12)
        {
            G.SkinName = "g";
            G.Leben = 0;
            G.Speed = 0;
            G.Ruestung = 0;
            G.Lebensverlust = 0;
            G.GoldWert = 0;
        }
    }
}
