import java.awt.Point;

public class WIKI implements Runnable
{
    private BILD Tuerme;
    private BILD Gegner;
    private BILD Titel;
    private BILD Hintergrund;
    private BILD Zurueck;
    
    private BILD[] Liste; //Bilder von allen vorhandenen Tuermen/Gegnern
    private BILD Eintrag;

    public boolean IsRunning;
    private char Zustand; //s -> start, t -> tuerme, g -> gegner, a -> TurmEintrag, b -> GegnerEintrag

    public WIKI()
    {
        System.out.print('\u000C');
        
        Hintergrund = new BILD("Hintergrund.PNG");
        Hintergrund.SetzeBreite(1500);
        Hintergrund.SetzeHoehe(800);

        Tuerme = new BILD("wTuerme.PNG");
        Tuerme.SetzeX(400);
        Tuerme.SetzeY(250);
        Gegner = new BILD("wGegner.PNG");
        Gegner.SetzeX(850);
        Gegner.SetzeY(250);
        Titel = new BILD("bWiki.PNG");
        Titel.SetzeX(550);
        Titel.SetzeY(50);
        Zurueck = new BILD("bZurueck.PNG");
        Zurueck.SetzeX(0);
        Zurueck.SetzeY(0);

        IsRunning = true;
        
        Zustand = 's';

        run();
    }

    //Unterscheiden danach, in welchem teil des wiki man sich befindet
    public void run()
    {
        while(IsRunning)
        {
            KEYSTATE keystate = GAMEWINDOW.getInstance().getKeystate();
            Point p = keystate.PickLastMouseClickPosition();
            switch(Zustand)
            {
                //Start
                case 's':if(Tuerme.PunktBeruehrt(p)) 
                         {
                             Tuerme.SetzeX(-400);
                             Gegner.SetzeX(-850);
                             Titel.SetzeX(-550);
                             Zurueck.SetzeX(1400);
                             Zustand = 't';
                             TuermeAnzeigen();
                         }
                         if(Gegner.PunktBeruehrt(p))
                         {
                             Tuerme.SetzeX(-400);
                             Gegner.SetzeX(-850);
                             Titel.SetzeX(-550);
                             Zurueck.SetzeX(1400);
                             Zustand = 'g';
                             GegnerAnzeigen();
                         }
                         if(Zurueck.PunktBeruehrt(p))
                         {
                             Tuerme.SetzeX(-400);
                             Gegner.SetzeX(-850);
                             Titel.SetzeX(-550);
                             Zurueck.SetzeX(-550);
                             Hintergrund.SetzeX(-1550);
                             IsRunning = false;
                         }
                         break;
                //Tuerme
                case 't':if(Zurueck.PunktBeruehrt(p))
                         {
                             Tuerme.SetzeX(400);
                             Gegner.SetzeX(850);
                             Titel.SetzeX(550);
                             Zurueck.SetzeX(0);
                             TuermeWeg();
                             Zustand = 's';
                         }
                         for(int i = 0; i<9; i++)
                         {
                             if(Liste[i].PunktBeruehrt(p))
                             {
                                 TuermeWeg();
                                 Zurueck.SetzeX(0);
                                 EintragTurm(Liste[i].LeseName());
                                 Zustand = 'a';
                             }
                         }
                         break;
                //Gegner
                case 'g':if(Zurueck.PunktBeruehrt(p))
                         {
                             Tuerme.SetzeX(400);
                             Gegner.SetzeX(850);
                             Titel.SetzeX(550);
                             Zurueck.SetzeX(0);
                             GegnerWeg();
                             Zustand = 's';
                         }
                         for(int i = 0; i<12; i++)
                         {
                             if(Liste[i].PunktBeruehrt(p))
                             {
                                 GegnerWeg();
                                 Zurueck.SetzeX(0);
                                 EintragGegner(Liste[i].LeseName(), i);
                                 Zustand = 'b';
                             }
                         }
                         break;
                //EinzelnerTurm
                case 'a':if(Zurueck.PunktBeruehrt(p))
                         {
                             Zurueck.SetzeX(1400);
                             TuermeAnzeigen();
                             EintragTWeg();
                             Zustand = 't';
                         }
                         break;
                //EinzelnerGegner
                case 'b':if(Zurueck.PunktBeruehrt(p))
                         {
                             Zurueck.SetzeX(1400);
                             GegnerAnzeigen();
                             EintragGWeg();
                             Zustand = 'g';
                         }
                         break;
            }
        }
    }

    public void TuermeAnzeigen()
    {
        Liste = new BILD[9];
        Liste[0] = new BILD("TurmplatzPfeilturm.PNG");
        Liste[1] = new BILD("TurmplatzMultiPfeilturm.PNG");
        Liste[2] = new BILD("TurmplatzScharfschuetzenturm.PNG");
        Liste[3] = new BILD("TurmplatzArmbrustturm.PNG");
        Liste[4] = new BILD("TurmplatzMultiArmbrustturm.PNG");
        Liste[5] = new BILD("TurmplatzBallistenturm.PNG");
        Liste[6] = new BILD("TurmplatzKanonenturm.PNG");
        Liste[7] = new BILD("TurmplatzHEturm.PNG");
        Liste[8] = new BILD("TurmplatzAPturm.PNG");
        
        Liste[0].SetzeX(300);   Liste[3].SetzeX(750);   Liste[6].SetzeX(1200);
        Liste[0].SetzeY(100);   Liste[3].SetzeY(100);   Liste[6].SetzeY(100);
        
        Liste[1].SetzeX(300);   Liste[4].SetzeX(750);   Liste[7].SetzeX(1200);
        Liste[1].SetzeY(300);   Liste[4].SetzeY(300);   Liste[7].SetzeY(300);
        
        Liste[2].SetzeX(300);   Liste[5].SetzeX(750);   Liste[8].SetzeX(1200);
        Liste[2].SetzeY(500);   Liste[5].SetzeY(500);   Liste[8].SetzeY(500);
    }
    
    public void TuermeWeg()
    {
        for(int i = 0; i<9; i++)
        {
            Liste[i].SetzeX(1700);
        }
    }
    
    public void EintragTurm(String Name)
    {
        Eintrag = new BILD(Name);
        Eintrag.SetzeX(600);
        Eintrag.SetzeY(200);
        Eintrag.SetzeBreite(200);
        Eintrag.SetzeHoehe(200);
        
        String TName = "";
        
        String Art = "";
        if(Name.equals("TurmplatzPfeilturm.PNG")){Art = "Pfeil"; TName = "Pfeilturm";}
        if(Name.equals("TurmplatzArmbrustturm.PNG")){Art = "Armbrust"; TName = "Armbrustturm";}
        if(Name.equals("TurmplatzKanonenturm.PNG")){Art = "Kanonen"; TName = "Kanonenturm";}
        TURMPLATZ t = null;
        if(!Art.equals(""))
        {
             t = new TURMPLATZ(Art, -500, -500);
        }
        else
        {
             if(Name.equals("TurmplatzMultiPfeilturm.PNG")){Art = "Pfeil"; TName = "MultiPfeilturm";}
             if(Name.equals("TurmplatzScharfschuetzenturm.PNG")){Art = "Pfeil"; TName = "Scharfschuetzenturm";}
             if(Name.equals("TurmplatzMultiArmbrustturm.PNG")){Art = "Armbrust"; TName = "MultiArmbrustturm";}
             if(Name.equals("TurmplatzBallistenturm.PNG")){Art = "Armbrust"; TName = "Ballistenturm";}
             if(Name.equals("TurmplatzHEturm.PNG")){Art = "Kanonen"; TName = "HE-Turm";}
             if(Name.equals("TurmplatzAPturm.PNG")){Art = "Kanonen"; TName = "AP-Turm";}
             t = new TURMPLATZ(Art, -500, -500);
             t.TurmUpgraden();
             t.TurmUpgraden();
             t.TurmUpgraden();
             if(Name.equals("TurmplatzMultiPfeilturm.PNG")){t.TurmSpezialisieren("MultiPfeil");}
             if(Name.equals("TurmplatzScharfschuetzenturm.PNG")){t.TurmSpezialisieren("Scharfschuetzen");}
             if(Name.equals("TurmplatzMultiArmbrustturm.PNG")){t.TurmSpezialisieren("MultiArmbrust");}
             if(Name.equals("TurmplatzBallistenturm.PNG")){t.TurmSpezialisieren("Ballisten");}
             if(Name.equals("TurmplatzHEturm.PNG")){t.TurmSpezialisieren("HE");}
             if(Name.equals("TurmplatzAPturm.PNG")){t.TurmSpezialisieren("AP");}
        }
        
        
        
        
        System.out.println("Name: "+TName);
        System.out.println("Angriff: "+t.Angriff);
        System.out.println("Nachladezeit: "+t.Nachladezeit+" Sekunden");
        System.out.println("Reichweite: "+t.Reichweite);
        System.out.println("Treffer-Flaeche: "+t.Flaeche);
        System.out.println("Upgrade Kosten: "+t.UpgradeKosten);
    }
    
    public void EintragTWeg()
    {
        System.out.print('\u000C');
        Eintrag.SetzeX(1700);
    }

    
    public void GegnerAnzeigen()
    {
        Liste = new BILD[12];
        Liste[0] = new BILD("gSpeertraeger.PNG");
        Liste[1] = new BILD("gSoldat.PNG");
        Liste[2] = new BILD("gSpaeher.PNG");
        Liste[3] = new BILD("gSchwertkaempfer.PNG");
        Liste[4] = new BILD("gVeteran.PNG");
        Liste[5] = new BILD("gKoenigswache.PNG");
        Liste[6] = new BILD("gPalastwache.PNG");
        Liste[7] = new BILD("gHellebardier.PNG");
        Liste[8] = new BILD("gElitekaempfer.PNG");
        Liste[9] = new BILD("gRitter.PNG");
        Liste[10] = new BILD("gGepanzerterreiter.PNG");
        Liste[11] = new BILD("gPaladin.PNG");
        
        Liste[0].SetzeX(100);   Liste[1].SetzeX(500);   Liste[2].SetzeX(900);  Liste[3].SetzeX(1300);
        Liste[0].SetzeY(100);   Liste[1].SetzeY(100);   Liste[2].SetzeY(100);   Liste[3].SetzeY(100); 
        
        Liste[4].SetzeX(100);   Liste[5].SetzeX(500);   Liste[6].SetzeX(900);  Liste[7].SetzeX(1300);
        Liste[4].SetzeY(300);   Liste[5].SetzeY(300);   Liste[6].SetzeY(300);   Liste[7].SetzeY(300);
        
        Liste[8].SetzeX(100);   Liste[9].SetzeX(500);   Liste[10].SetzeX(900);  Liste[11].SetzeX(1300);
        Liste[8].SetzeY(500);   Liste[9].SetzeY(500);   Liste[10].SetzeY(500);   Liste[11].SetzeY(500);
        
        for(int i = 0; i<12; i++)
        {
            Liste[i].TransparenzSetzen(255, 255, 255);
        }
    }
    
    public void GegnerWeg()
    {
        for(int i = 0; i<12; i++)
        {
            Liste[i].SetzeX(1700);
        }
    }
    
    public void EintragGegner(String Name, int Nummer)
    {
        Eintrag = new BILD(Name);
        Eintrag.SetzeX(600);
        Eintrag.SetzeY(200);
        Eintrag.SetzeBreite(200);
        Eintrag.SetzeHoehe(200);
        Eintrag.TransparenzSetzen(255, 255, 255);
        
        GEGNER g = new GEGNER(); WERTE.GegnerWerteBeimErzeugen(Nummer, g);
        String GName = "";
        for(int i = 1; i<g.SkinName.length(); i++)
        {
            GName = GName+g.SkinName.charAt(i);
        }
        
        System.out.println("Name: "+GName);
        System.out.println("Leben: "+g.Leben);
        System.out.println("Geschwindigkeit: "+g.Speed+" Pixel/20 Milisekunden");
        System.out.println("Ruestung: "+g.Ruestung);
        System.out.println("Lebensverlust: "+g.Lebensverlust);
        System.out.println("Gold: "+g.GoldWert);
    }
    
    public void EintragGWeg()
    {
        System.out.print('\u000C');
        Eintrag.SetzeX(1700);
    }
}