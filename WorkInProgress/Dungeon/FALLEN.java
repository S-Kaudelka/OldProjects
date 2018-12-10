public class FALLEN
{
    public String Name;
    public BILD Skin;
    public int Level;
    public int Dmg;
    public int Wert;
    public int UpgradeKosten;
    public int Effekt; // Fuer Feuer und Eis Schaden/Reduzierung
    public int Dauer;  // ""              "" Anzahl Schritte
    public int Prozent; // Wahrscheinlichkeit fuer Feuer/Eis
    public String Effektart;
    public boolean Aktiviert;
    
    public FALLEN(String name_p, int u, int r)
    {
        if(name_p.equals("Stachel")) //Standard Schaden
        {
            Name = "Stachel";
            Skin = new BILD("f"+Name+".png");
            Skin.SetzeX(r*40);
            Skin.SetzeY(u*40);
            Level = 1;
            Dmg = 4;
            Wert = 30;
            UpgradeKosten = 40; 
        }
        if(name_p.equals("Feuer")) //verbrennen zu x% -> anhaltender schaden
        {
            Name = "Feuer";
            Skin = new BILD("f"+Name+".png");
            Skin.SetzeX(r*40);
            Skin.SetzeY(u*40);
            Level = 1;
            Dmg = 3;
            Wert = 50;
            Effekt = 1;
            Dauer = 2;
            Prozent = 40;
            UpgradeKosten = 60;
            Effektart = "Feuer";
        }
        if(name_p.equals("Magie")) //hoher Schaden -> manche vllt resistent
        {
            Name = "Magie";
            Skin = new BILD("f"+Name+".png");
            Skin.SetzeX(r*40);
            Skin.SetzeY(u*40);
            Level = 1;
            Dmg = 9;
            Wert = 65;
            UpgradeKosten = 70; 
        }
        if(name_p.equals("Kaelte")) // x% einfrieren -> weniger Angriff fuer bestimmte zeit
        {
            Name = "Kaelte";
            Skin = new BILD("f"+Name+".png");
            Skin.SetzeX(r*40);
            Skin.SetzeY(u*40);
            Level = 1;
            Dmg = 2;
            Wert = 50;
            Effekt = 1;
            Dauer = 3;
            Prozent = 50;
            UpgradeKosten = 60;
            Effektart = "Kaelte";
        }
    }
    
    public int Upgraden()
    {
        if(Level == 5){return 0;}
        if(Name.equals("Stachel"))
        {
            Level++;
            Dmg = Dmg + 3;
            Wert = Wert + UpgradeKosten;
        }
        if(Name.equals("Feuer"))
        {
            Level++;
            Dmg = Dmg + 3;
            Effekt++;
            Dauer++;
            Prozent = Prozent + 5;
            Wert = Wert + UpgradeKosten;
        }
        if(Name.equals("Magie"))
        {
            Level++;
            Dmg = Dmg + 4;
            Wert = Wert + UpgradeKosten;
        }
        if(Name.equals("Kaelte"))
        {
            Level++;
            Dmg = Dmg + 2;
            Effekt++;
            Dauer++;
            Prozent = Prozent + 5;
            Wert = Wert + UpgradeKosten;
        }
        return UpgradeKosten;
    }
}