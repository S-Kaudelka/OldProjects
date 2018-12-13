public class TURMPLATZ
{
    public BILD Turm;
    public BILD Geschoss;
    public BILD Animation;
    public int AnimationZeit;
    public boolean AnimIstDa;
    public String Art;
    
    public GEGNER[] Gegner;
    public int Wellengegner;
    public int GegnerAmLeben;
    public int Ziel;
    
    public int Wert;
    public int UpgradeKosten;
    public int Angriff;
    public double Nachladezeit;
    public int Reichweite;
    public int Flaeche;
    public int Level;
    public int SeitenFaktor;
    public int ZeitVergangen;
    
    //Schiessen einfuegen
    
    public TURMPLATZ(String Art_p, String S, int X_p, int Y_p)
    {
        Art=Art_p;
        Turm = new BILD("Turmplatz"+Art+"turm.PNG");
        Turm.SetzeX(X_p);
        Turm.SetzeY(Y_p);
        Artwerte(Art);
        ZeitVergangen=5000;
        if(S=="Oben")
        {
            SeitenFaktor=1;
        }
        else
        {
            SeitenFaktor=-1;
        }
    }
    
    public void Angriff()
    {
        ZeitVergangen=ZeitVergangen+20;
        GegnerBestimmen();
        // wenn Geschoss zu weit weg ist
        if( Geschoss.LeseX()<Turm.LeseX()-600 ||
            Geschoss.LeseX()>Turm.LeseX()+600 || 
            Geschoss.LeseY()>Turm.LeseY()+300 || 
            Geschoss.LeseY()<Turm.LeseY()-300)
        {
            Geschoss.SetzeY(1000);
            Geschoss.SichtbarSetzen(false);
        }
        if(Ziel==-1)
        {
            Geschoss.SetzeY(1000);
            Geschoss.SichtbarSetzen(false);
        }
        if(Ziel>=0)
        {
            // neuen schuss einleiten
            if(Betrag(Turm.Abstand(Gegner[Ziel].Skin))<=Reichweite && Geschoss.LeseSichtbar()==false && ZeitVergangen>=Nachladezeit*1000)
            {
                Geschoss.SetzeX( (int) (Turm.LeseX() + Turm.LeseBreite()/2.0));
                Geschoss.SetzeY( (int) (Turm.LeseY() + Turm.LeseHoehe()/2.0));
                Geschoss.SichtbarSetzen(true);
                ZeitVergangen=0;
            }
            // bewegen
            if(Geschoss.LeseY() != 400 && Geschoss.LeseSichtbar()==true)
            {
                Geschoss.SetzeY(Geschoss.LeseY()+5*SeitenFaktor);
            }
            if(Gegner[Ziel].Skin.LeseX()+25>=Turm.LeseX()+7 && Geschoss.LeseSichtbar()==true)
            {
                Geschoss.SetzeX(Geschoss.LeseX()+7);
            }
            if(Gegner[Ziel].Skin.LeseX()+25<=Turm.LeseX() && Geschoss.LeseSichtbar()==true)
            {
                Geschoss.SetzeX(Geschoss.LeseX()-7);
            }
        }
        if(Ziel>=0)
        {
            if(Art == "Pfeil")
            {
                if(Gegner[Ziel]!=null)
                {
                    if(Geschoss.Abstand(Gegner[Ziel].Skin)<=Flaeche*2)
                    {
                        Geschoss.SetzeY(1000);
                        Geschoss.SichtbarSetzen(false);
                        int Schaden = Angriff-Gegner[Ziel].Ruestung;
                        if(Schaden<=0)
                        {
                            Schaden=1;
                        }
                        Gegner[Ziel].Leben=Gegner[Ziel].Leben-Schaden;
                    }
                }
            }
            if(Art == "Armbrust")
            {
                if(Gegner[Ziel]!=null)
                {
                    if(Geschoss.Abstand(Gegner[Ziel].Skin)<=Flaeche*2)
                    {
                        Geschoss.SetzeY(1000);
                        Geschoss.SichtbarSetzen(false);
                        int Schaden = Angriff-(Gegner[Ziel].Ruestung/2);
                        Gegner[Ziel].Leben=Gegner[Ziel].Leben-Schaden;
                    }
                }
            }
            if(Art =="Kanonen")
            {
                if(Gegner[Ziel]!=null)
                {
                    if(Geschoss.Abstand(Gegner[Ziel].Skin)<=25)
                    {
                        Animation.SetzeX(Geschoss.LeseX()-70);
                        Animation.SetzeY(Geschoss.LeseY()-70);
                        Animation.SichtbarSetzen(true);
                        AnimIstDa=true;
                        int Schaden = Angriff-(Gegner[Ziel].Ruestung*4/5);
                        if(Schaden<=0)
                        {
                            Schaden=1;
                        }
                        for(int i=0; i<Wellengegner; i++)
                        {
                            if(Gegner[i].Skin.Abstand(Geschoss)<=Flaeche)
                            {
                                Gegner[i].Leben=Gegner[i].Leben-Schaden;
                            }
                        }
                        Geschoss.SetzeY(1000);
                        Geschoss.SichtbarSetzen(false);
                    }
                }
            }
        }
        if(AnimIstDa==true)
        {
            AnimationZeit=AnimationZeit+20;
        }
        if(AnimationZeit>=1000)
        {
            Animation.SetzeY(1000);
            Animation.SichtbarSetzen(false);
            AnimationZeit=0;
            AnimIstDa=false;
        }
    }
    
    public void GegnerBestimmen()
    {
        for(int i = 0; i<Wellengegner; i++)
        {
            if(GegnerAmLeben>0)
            {
                if(Betrag(Turm.Abstand(Gegner[i].Skin))<=Reichweite)
                {
                    Ziel=i;
                    return;
                }
            }
        }
        Ziel=-1;
    }
    
    public int TurmUpgraden()
    {
        int Gold_r = 0;
        
        if(Level==4)
        {
            return 0;
        }
        if(Art.equals("Pfeil"))
        {
            Gold_r = 50;
            
            Wert = Wert+Gold_r;
            
            Angriff= Angriff + 5;
            Nachladezeit = Nachladezeit - 0.15;
            Reichweite = Reichweite + 50;
            Flaeche = Flaeche + 1;
            Level++;
        }
        if(Art.equals("Armbrust"))
        {
            Gold_r = 100;
            
            Wert = Wert+Gold_r;
            
            Angriff= Angriff + 10;
            Nachladezeit = Nachladezeit - 0.3;
            Reichweite = Reichweite + 50;
            Flaeche = Flaeche + 1;
            Level++;
        }
        if(Art.equals("Kanonen"))
        {
            Gold_r = 150;
            
            Wert = Wert+Gold_r;
            
            Angriff= Angriff + 8;
            Nachladezeit = Nachladezeit - 0.4;
            Reichweite = Reichweite + 50;
            Flaeche = Flaeche + 20;
            Level++;
        }
        
        return Gold_r;
    }
    
    public void Artwerte(String Art_p)
    {
        if(Art == "Pfeil")
        {
            Wert = 100;
            UpgradeKosten = 50;
            Angriff = 5;
            Nachladezeit = 1;
            Reichweite = 200;
            Flaeche = 5;
            Level = 1;
            Geschoss = new BILD("pPfeil.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
        }
        
        if(Art == "Armbrust")
        {
            Wert = 300;
            UpgradeKosten = 100;
            Angriff = 10;
            Nachladezeit = 2;
            Reichweite = 250;
            Flaeche = 10;
            Level = 1;
            Geschoss = new BILD("pBolzen.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
        }
        
        if(Art == "Kanonen")
        {
            Wert = 400;
            UpgradeKosten = 150;
            Angriff = 10;
            Nachladezeit = 3;
            Reichweite = 300;
            Flaeche = 80;
            Level = 1;
            Geschoss = new BILD("pBombe.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            Animation = new BILD("pExplosion.PNG");
            Animation.TransparenzSetzen(255,255,255);
            Animation.SichtbarSetzen(false);
        }
    }
    
    public double Betrag(double a)
    {
        if(a>=0)
        {
            return a;
        }
        else
        {
            return -a;
        }
    }
}