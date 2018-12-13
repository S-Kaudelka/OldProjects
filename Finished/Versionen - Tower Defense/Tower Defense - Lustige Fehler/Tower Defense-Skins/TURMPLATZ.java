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
    
    public TURMPLATZ(String Art_p, String S, int X_p, int Y_p)
    {
        Art=Art_p;
        Turm = new BILD("Turmplatz"+Art+"turm.PNG");
        Turm.SetzeX(X_p);
        Turm.SetzeY(Y_p);
        Artwerte(Art);
        ZeitVergangen=5000;
        if(S.equals("Oben"))
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
        Ziel=-1;
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
            if(Gegner[Ziel].Skin.LeseX()+25>=Geschoss.LeseX()+10 && Geschoss.LeseSichtbar()==true)
            {
                Geschoss.SetzeX(Geschoss.LeseX()+10);
            }
            if(Gegner[Ziel].Skin.LeseX()+25<=Geschoss.LeseX() && Geschoss.LeseSichtbar()==true)
            {
                Geschoss.SetzeX(Geschoss.LeseX()-10);
            }
        }
        if(Ziel>=0)
        {
            if(Art.equals("Pfeil") || Art.equals("MultiPfeil") || Art.equals("Scharfschuetzen"))
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
            if(Art.equals("Armbrust") || Art.equals("MultiArmbrust") || Art.equals("Ballisten"))
            {
                if(Gegner[Ziel]!=null)
                {
                    if(Geschoss.Abstand(Gegner[Ziel].Skin)<=Flaeche*2)
                    {
                        Geschoss.SetzeY(1000);
                        Geschoss.SichtbarSetzen(false);
                        int Schaden = Angriff-(Gegner[Ziel].Ruestung/2);
                        if(Schaden<=5)
                        {
                            Schaden=5;
                        }
                        Gegner[Ziel].Leben=Gegner[Ziel].Leben-Schaden;
                    }
                }
            }
            if(Art.equals("Kanonen") || Art.equals("AP") || Art.equals("HE"))
            {
                if(Gegner[Ziel]!=null)
                {
                    if(Geschoss.Abstand(Gegner[Ziel].Skin)<=25)
                    {
                        Animation.SetzeX(Geschoss.LeseX()-70);
                        Animation.SetzeY(Geschoss.LeseY()-70);
                        Animation.SichtbarSetzen(true);
                        AnimIstDa=true;
                        int Schaden = 0;
                        if(Art.equals("Kanonen") || Art.equals("HE"))
                        {
                            Schaden = Angriff-(Gegner[Ziel].Ruestung*4/5);
                        }
                        if(Art.equals("AP"))
                        {
                            Schaden = Angriff-(Gegner[Ziel].Ruestung*2/5);
                        }
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
        if(AnimationZeit>=150)
        {
            Animation.SetzeY(1000);
            Animation.SichtbarSetzen(false);
            AnimationZeit=0;
            AnimIstDa=false;
        }
    }
    
    public void GegnerBestimmen()
    {
        int x=2000;
        for(int i = 0; i<Wellengegner; i++)
        {
            if(GegnerAmLeben>0)
            {
                if(Betrag(Turm.Abstand(Gegner[i].Skin))<=Reichweite && Gegner[i].Skin.LeseX() < x)
                {
                    x = Gegner[i].Skin.LeseX();
                    Ziel=i;
                }
            }
        }
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
            
            Angriff= Angriff + 6;
            Nachladezeit = Nachladezeit - 0.15;
            Reichweite = Reichweite + 50;
            Flaeche++;
            Level++;
        }
        if(Art.equals("Armbrust"))
        {
            Gold_r = 100;
            
            Wert = Wert+Gold_r;
            
            Angriff= Angriff + 10;
            Nachladezeit = Nachladezeit - 0.2;
            Reichweite = Reichweite + 50;
            Flaeche++;
            Level++;
        }
        if(Art.equals("Kanonen"))
        {
            Gold_r = 150;
            
            Wert = Wert+Gold_r;
            
            Angriff= Angriff + 4;
            Nachladezeit = Nachladezeit - 0.3;
            Reichweite = Reichweite + 50;
            Flaeche = Flaeche + 20;
            Level++;
        }
        //Spezialisierte Türme verbessern unendlich <-> bestimmtes Level?
        return Gold_r;
    }
    
    public int TurmSpezialisieren(String Art_p)
    {
        Artwerte(Art_p);
        return UpgradeKosten;
    }
    
    public void Artwerte(String Art_p)
    {
        //Pfeilturm
        if(Art_p.equals("Pfeil"))
        {
            Wert = 100;
            UpgradeKosten = 50;
            Angriff = 7;
            Nachladezeit = 0.8;
            Reichweite = 200;
            Flaeche = 5;
            Level = 1;
            Geschoss = new BILD("pPfeil.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
        }
        //Armbrustturm
        if(Art_p.equals("Armbrust"))
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
        //Kanonenturm
        if(Art_p.equals("Kanonen"))
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
        
        // Spezialisierungen
        //Pfeil
        if(Art_p.equals("MultiPfeil"))
        {
            Art=Art_p;
            Wert = Wert+100;
            UpgradeKosten = 150;
            Angriff = Angriff+5;
            Nachladezeit = Nachladezeit-0.1;
            Reichweite = Reichweite+25;
            Flaeche++;
            Level++;
            
            Geschoss.SetzeY(1500);
            
            Geschoss = new BILD("pPfeil.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
        }
        if(Art_p.equals("Scharfschuetzen"))
        {
            Art=Art_p;
            Wert = Wert+100;
            UpgradeKosten = 150;
            Angriff = Angriff+15;
            Nachladezeit = Nachladezeit+0.5;
            Reichweite = Reichweite+250;
            Flaeche++;
            Level++;
            
            Geschoss.SetzeY(1500);
            
            Geschoss = new BILD("pPfeil.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
        }
        //Armbrust
        if(Art_p.equals("MultiArmbrust"))
        {
            Art=Art_p;
            Wert = Wert+150;
            UpgradeKosten = 200;
            Angriff = Angriff+5;
            Nachladezeit = Nachladezeit-0.4;
            Reichweite = Reichweite+25;
            Flaeche++;
            Level++;
            
            Geschoss.SetzeY(1500);
            
            Geschoss = new BILD("pBolzen.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
        }
        if(Art_p.equals("Ballisten"))
        {
            Art=Art_p;
            Wert = Wert+150;
            UpgradeKosten = 200;
            Angriff = Angriff+30;
            Nachladezeit = Nachladezeit+0.6;
            Reichweite = Reichweite+50;
            Flaeche++;
            Level++;
            
            Geschoss.SetzeY(1500);
            
            Geschoss = new BILD("pBolzen.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
        }
        //Kanone
        if(Art_p.equals("AP"))
        {
            Art=Art_p;
            Wert = Wert+200;
            UpgradeKosten = 250;
            Angriff = Angriff+8;
            Nachladezeit = Nachladezeit-0.2;
            Reichweite = Reichweite+25;
            Flaeche = Flaeche+5;
            Level++;
            
            Geschoss.SetzeY(1500);
            Animation.SetzeY(1500);
            
            Geschoss = new BILD("pBombe.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            Animation = new BILD("pExplosion.PNG");
            Animation.TransparenzSetzen(255,255,255);
            Animation.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
        }
        if(Art_p.equals("HE"))
        {
            Art=Art_p;
            Wert = Wert+200;
            UpgradeKosten = 250;
            Angriff = Angriff+3;
            Nachladezeit = Nachladezeit-0.2;
            Reichweite = Reichweite+25;
            Flaeche = Flaeche+50;
            Level++;
            
            Geschoss.SetzeY(1500);
            Animation.SetzeY(1500);
            
            Geschoss = new BILD("pBombe.PNG");
            Geschoss.TransparenzSetzen(255,255,255);
            Geschoss.SichtbarSetzen(false);
            
            Animation = new BILD("pExplosionG.PNG");
            Animation.TransparenzSetzen(255,255,255);
            Animation.SichtbarSetzen(false);
            
            int x; int y;
            x=Turm.LeseX();
            y=Turm.LeseY();
            Turm.SetzeY(1500);
            Turm = new BILD("Turmplatz"+Art+"turm.PNG");
            Turm.SetzeX(x);
            Turm.SetzeY(y);
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