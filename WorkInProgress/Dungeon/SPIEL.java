import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    public SPIELER Spieler;

    private boolean Verloren;
    public boolean GoldPlatziert;

    //Variablen fuer Ausgabe
    private boolean IstVeraendert;
    private boolean ZuWenigGold;
    private boolean keinFeldAusg;
    private boolean nichtLeer;

    public FELD[][] Felder;
    public FALLEN[][] Fallen;
    public MONSTER[][] Monster;
    private MONSTER[][] MonsterZurueck;
    public GEGNER gegner;

    public static int Seite = 21; //Anz felder pro seite
    public int FIns; //felder insgesamt
    public int Ua; //ausgewaehltes Feld
    public int Ra; // ""

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;

        Spieler = new SPIELER();

        IstVeraendert = true;

        NeuSpielfeld();
    }

    public void NeuSpielfeld()
    {
        //Seite = Seitenlaenge; FIns = FelderInsgesamt
        FIns = Seite*Seite;
        Fallen = new FALLEN[Seite][Seite];
        Monster = new MONSTER[Seite][Seite];
        MonsterZurueck = new MONSTER[Seite][Seite];
        Felder = new FELD[Seite][Seite];
        for(int u = 0; u < Seite; u++)
        {
            for(int r = 0; r < Seite; r++)
            {
                Felder[u][r] = new FELD();
                Felder[u][r].Skin = new BILD("fWand.png");
                Felder[u][r].Skin.SetzeX(r*40);
                Felder[u][r].Skin.SetzeY(u*40);
            }
        }
        int x = Felder[0][10].Skin.LeseX();
        int y = Felder[0][10].Skin.LeseY();
        Felder[0][10].Skin = new BILD("fEingang.png");
        Felder[0][10].Zustand = "Eingang";
        Felder[0][10].Skin.SetzeX(x);
        Felder[0][10].Skin.SetzeY(y);
        Ua = -1;
        Ra = -1;
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);

        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }

        if(IstVeraendert==true)
        {
            Ausgabe();
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(Verloren || GoldPlatziert){return;}
        if(keystate.IsPressed("S")) //Wand Entfernen
        {
            WandEntfernen();
        }
        if(keystate.IsPressed("G")) //Gold platzieren -> vorerst immer max. 10
        {
            GoldPlatzieren();
        }
        //
        //Fallen
        //
        if(keystate.IsPressed("Q")) // Falle platzieren: Stachel
        {
            FallenPlatzieren("Stachel", keystate);
        }
        if(keystate.IsPressed("W")) // Falle platzieren: Feuer
        {
            FallenPlatzieren("Feuer", keystate);
        }
        if(keystate.IsPressed("E")) // Falle platzieren: Magie
        {
            FallenPlatzieren("Magie", keystate);
        }
        if(keystate.IsPressed("R")) // Falle platzieren: Kaelte
        {
            FallenPlatzieren("Kaelte", keystate);
        }
        if(keystate.IsPressed("T")) // Falle upgraden
        {
            if(Ua != -1 && Ra != -1 && Fallen[Ua][Ra] != null && Spieler.Gold > Fallen[Ua][Ra].UpgradeKosten)
            {
                Spieler.Gold = Spieler.Gold - Fallen[Ua][Ra].Upgraden();
                IstVeraendert = true;
                Ua = -1; Ra = -1;
            }
        }
        if(keystate.IsPressed("Z")) // Falle verkaufen
        {
            if(Ua != -1 && Ra != -1 && Fallen[Ua][Ra] != null)
            {
                Spieler.Gold = Spieler.Gold + Fallen[Ua][Ra].Wert/2;
                Fallen[Ua][Ra].Skin.SetzeX(-100);
                Fallen[Ua][Ra] = null;
                Felder[Ua][Ra].Zustand = "Leer";
                IstVeraendert = true;
                Ua = -1; Ra = -1;
            }
        }
        //
        //Monster
        //
        if(keystate.IsPressed("Y")) // Monster platzieren: Skelett
        {
            MonsterPlatzieren("Skelett", keystate);
        }
        if(keystate.IsPressed("X")) // Monster platzieren: Rauch
        {
            MonsterPlatzieren("Rauch", keystate);
        }
        if(keystate.IsPressed("C")) // Monster platzieren: Adept
        {
            MonsterPlatzieren("Adept", keystate);
        }
        if(keystate.IsPressed("V")) // Monster platzieren: Nebel
        {
            MonsterPlatzieren("Nebel", keystate);
        }
    }

    //
    //Tasten Pruefen Teile
    //

    public void WandEntfernen()
    {
        if(Spieler.Gold < 50)
        {
            ZuWenigGold = true;
            return;
        }
        if(Ua != -1 && Ra != -1 && (Ua != 0 || Ra != 10) && Felder[Ua][Ra] != null && Spieler.Gold >= 50)
        {
            if(Felder[Ua][Ra].Zustand == "Wand" && WegAngrenzend(Ua, Ra))
            {
                Spieler.Gold = Spieler.Gold - 50;
                int x = Felder[Ua][Ra].Skin.LeseX();
                int y = Felder[Ua][Ra].Skin.LeseY();
                Felder[Ua][Ra].Skin = new BILD("fLeer.png");
                Felder[Ua][Ra].Skin.SetzeX(x);
                Felder[Ua][Ra].Skin.SetzeY(y);
                Felder[Ua][Ra].Zustand = "Leer";
                IstVeraendert = true;
                Ua = -1;
                Ra = -1;
            }
        }
    }

    public void GoldPlatzieren()
    {
        if(Spieler.Gold <= 0)
        {
            c.stop();
            Verloren = true;
            return;
        }
        int Gold;
        if(Spieler.Gold < 10)
        {
            Gold = Spieler.Gold;
        }
        else
        {
            Gold = 10;
        }
        if(Ua == -1 && Ra == -1 || !Felder[Ua][Ra].Zustand.equals("Leer"))
        {
            keinFeldAusg = true;
            IstVeraendert = true;
        }
        else
        {
            GoldPlatziert = true;
            int x = Felder[Ua][Ra].Skin.LeseX();
            int y = Felder[Ua][Ra].Skin.LeseY();
            Felder[Ua][Ra].Skin = new BILD("fGold.png");
            Felder[Ua][Ra].Skin.SetzeX(x);
            Felder[Ua][Ra].Skin.SetzeY(y);
            System.out.println("Ein Gegner ist da!");
            Spieler.Gold = Spieler.Gold - Gold;
            gegner = new GEGNER(Gold);
            KI(Gold);
            IstVeraendert = true;
        }
    }

    public void MonsterPlatzieren(String Art, KEYSTATE keystate)
    {
        if(Ua == -1 && Ra == -1 || Ua == 0 && Ra == 10 || Felder[Ua][Ra].Zustand.equals("Wand"))
        {
            keinFeldAusg = true;
            IstVeraendert = true;
        }
        else
        {
            if(Fallen[Ua][Ra] != null || Monster[Ua][Ra] != null)
            {
                nichtLeer = true;
                IstVeraendert = true;
            }
            else
            {
                int g = 0;
                if(Art.equals("Skelett")){g = 30;}
                if(Art.equals("Rauch")){g = 40;}
                if(Art.equals("Adept")){g = 50;}
                if(Art.equals("Nebel")){g = 40;}
                if(Spieler.Gold < g){ZuWenigGold = true;}
                else
                {
                    Monster[Ua][Ra] = new MONSTER(Art,Ua,Ra);
                    Felder[Ua][Ra].Zustand = "Monster";
                    Ua = -1; Ra = -1;
                    Spieler.Gold = Spieler.Gold - g;
                    try { Thread.sleep((100)); } catch (Exception e) {}
                    IstVeraendert = true;
                }
            }
        }
    }

    public void FallenPlatzieren(String Art, KEYSTATE keystate)
    {
        if(Ua == -1 && Ra == -1 || Ua == 0 && Ra == 10 || Felder[Ua][Ra].Zustand.equals("Wand"))
        {
            keinFeldAusg = true;
            IstVeraendert = true;
        }
        else
        {
            if(Fallen[Ua][Ra] != null || Monster[Ua][Ra] != null)
            {
                nichtLeer = true;
                IstVeraendert = true;
            }
            else
            {
                int g = 0;
                if(Art.equals("Stachel")){g = 30;}
                if(Art.equals("Feuer")){g = 50;}
                if(Art.equals("Magie")){g = 70;}
                if(Art.equals("Kaelte")){g = 50;}
                if(Spieler.Gold < g){ZuWenigGold = true;}
                else
                {
                    Fallen[Ua][Ra] = new FALLEN(Art,Ua,Ra);
                    Felder[Ua][Ra].Zustand = "Falle";
                    Ua = -1; Ra = -1;
                    Spieler.Gold = Spieler.Gold - g;
                    try { Thread.sleep((100)); } catch (Exception e) {}
                    IstVeraendert = true;
                }
            }
        }
    }

    //
    //Tasten Pruefen Ende
    //

    public boolean WegAngrenzend(int u, int r)
    {
        if(r > 0)
        {
            if(!Felder[u][r-1].Zustand.equals("Wand"))
            {
                return true;
            }
        }
        if(r < Seite-1)
        {
            if(!Felder[u][r+1].Zustand.equals("Wand"))
            {
                return true;
            }
        }
        if(u > 0)
        {
            if(!Felder[u-1][r].Zustand.equals("Wand"))
            {
                return true;
            }
        }
        if(u < Seite-1)
        {
            if(!Felder[u+1][r].Zustand.equals("Wand"))
            {
                return true;
            }
        }
        return false;
    }

    public void MausklickUeberpruefen(Point p)
    {
        if(Verloren || GoldPlatziert){return;}
        for(int u = 0; u<21; u++)
        {
            for(int r = 0; r<21; r++)
            {
                if(p != null)
                {
                    if(Felder[u][r].Skin.PunktBeruehrt(p))
                    {
                        Ua = u; //Letztes angeklicktes Feld Bestimmen
                        Ra = r;
                        IstVeraendert = true;
                    }
                }
            }
        }
    }

    public void KI(int Gold_p)
    {
        while(gegner.Leben > 0)
        {
            gegner.KI(this);
            gegner.KaelteZeit--;
            if(gegner.KaelteZeit == 0)
            {
                gegner.KaelteWert = 0;
            }
            if(gegner.BrennenZeit > 0)
            {
                gegner.Leben = gegner.Leben - gegner.BrennenDmg;
                System.out.println("Gegner hat "+gegner.BrennenDmg+" Schaden durch Feuer Erhalten");
                gegner.BrennenZeit--;
            }
            else
            {
                gegner.BrennenDmg = 0;
            }
            if(Fallen[gegner.U][gegner.R] != null)
            {
                Fallen[gegner.U][gegner.R] = AufFalle(Fallen[gegner.U][gegner.R]);
            }
            for(int u = 0; u < Seite; u++)
            {
                for(int r = 0; r < Seite; r++)
                {
                    if(Monster[u][r] != null && !Monster[u][r].bewegt)
                    {
                        if(Monster[u][r].KI(this, u ,r))//Gegner da
                        {
                            Kampf(Monster[u][r]);
                            System.out.println("Gegner hat "+gegner.Leben+" Leben");
                            System.out.println("Moster hat "+Monster[u][r].Leben+" Leben");
                            if(Monster[u][r].Leben <= 0 || gegner.Leben <= 0)
                            {
                                if(gegner.Leben <= 0)
                                {System.out.println("Gegner ist besiegt");}
                                if(Monster[u][r].Leben <= 0)
                                {
                                    System.out.println("Monster ist besiegt");
                                    Monster[u][r].Skin.SetzeX(-100);
                                    Monster[u][r] = null;
                                }
                                try { Thread.sleep((4000)); } catch (Exception e) {}
                            }
                            else
                            {
                                try { Thread.sleep((500)); } catch (Exception e) {}
                            }
                        }
                    }
                }
            }
            if(gegner.Skin.LeseX()/40 == Ra && gegner.Skin.LeseY()/40 == Ua && !gegner.hatGold)
            {
                gegner.hatGold = true;
                int x = Felder[Ua][Ra].Skin.LeseX();
                int y = Felder[Ua][Ra].Skin.LeseY();
                Felder[Ua][Ra].Skin = new BILD("fLeer.png");
                Felder[Ua][Ra].Skin.SetzeX(x);
                Felder[Ua][Ra].Skin.SetzeY(y);
            }
            if(gegner.hatGold)
            {
                if(gegner.Skin.LeseX()/40 == 10 && gegner.Skin.LeseY()/40 == 0)
                {
                    gegner.Leben = 0;
                    gegner.BelohnungMax = 0;
                }
            }
            if(!gegner.hatGold && gegner.Skin.LeseX()/40 == 10 && gegner.Skin.LeseY()/40 == 0
            && gegner.Intelligent)
            {
                gegner.Intelligent = false;
            }
            for(int u = 0; u < Seite; u++)
            {
                for(int r = 0; r < Seite; r++)
                {
                    if(Monster[u][r] != null)
                    {
                        Monster[u][r].bewegt = false;
                    }
                }
            }
            try { Thread.sleep((300)); } catch (Exception e) {}
        }
        // Gegner ist tot || Gegner ist entkommen
        if(Felder[Ua][Ra].Skin.LeseName().equals("fGold.png"))
        {
            int x = Felder[Ua][Ra].Skin.LeseX();
            int y = Felder[Ua][Ra].Skin.LeseY();
            Felder[Ua][Ra].Skin = new BILD("fLeer.png");
            Felder[Ua][Ra].Skin.SetzeX(x);
            Felder[Ua][Ra].Skin.SetzeY(y);
        }
        gegner.Skin.SetzeX(-100);
        Spieler.Gold = (int)(Spieler.Gold + Math.round(Gold_p*gegner.BelohnungMax));
        if(Spieler.Gold <= 2){Spieler.Gold = 3;}
        Ua = -1; Ra = -1;
        for(int u = 0; u < Seite; u++)
        {
            for(int r = 0; r < Seite; r++)
            {
                if(Monster[u][r] != null)
                {
                    Monster[u][r].Leben = Monster[u][r].Leben + 5;
                    MonsterZurueck[Monster[u][r].U][Monster[u][r].R] = Monster[u][r];
                    Monster[u][r] = null;
                }
            }
        }
        for(int u = 0; u < Seite; u++)
        {
            for(int r = 0; r < Seite; r++)
            {
                if(MonsterZurueck[u][r] != null)
                {
                    Monster[u][r] = MonsterZurueck[u][r];
                    Monster[u][r].Skin.SetzeX(r*40);
                    Monster[u][r].Skin.SetzeY(u*40);
                }
            }
        }
        GoldPlatziert = false;
    }

    public void Kampf(MONSTER m)
    {
        if(gegner.Leben <= 0)
        {
            return;
        }
        //
        //Angriff Gegner
        int z = (int)(Math.random()*100+1); //1-100
        if(z <= m.Ausweichen)
        {
            System.out.println("Monster Ausgewichen!");
            return ;
        }
        z = (int)(Math.random()*100+1);
        if(z <= gegner.Crit)
        {
            int s = (int)(Math.round(gegner.Angriff*1.5))-gegner.KaelteWert;
            if(s < 1)
            {
                s = 1;
            }
            m.Leben = m.Leben - s;
            System.out.println("Gegner hat einen Kritischen Treffer gelandet!("+s+" Schaden");
        }
        else
        {
            m.Leben = m.Leben - gegner.Angriff;
            System.out.println("Gegner hat einen Treffer gelandet!("+gegner.Angriff+" Schaden)");
        }
        //
        //Angriff Monster
        if(m.Leben <= 0){return;}
        z = (int)(Math.random()*100+1); //1-100
        if(z <= gegner.Ausweichen)
        {
            System.out.println("Gegner Ausgewichen!");
            return ;
        }
        z = (int)(Math.random()*100+1);
        if(z <= m.Prozent)
        {
            if(m.Art == "Feuer")
            {
                if(gegner.BrennenDmg < m.Effekt)
                {
                    gegner.BrennenDmg = m.Effekt;
                }
                if(gegner.BrennenZeit < m.Dauer)
                {
                    gegner.BrennenZeit = m.Dauer;
                }
                System.out.println("Gegner Verbrannt!");
            }
            if(m.Art == "Kaelte")
            {
                if(gegner.KaelteWert < m.Effekt)
                {
                    gegner.KaelteWert = m.Effekt;
                }
                if(gegner.KaelteZeit < m.Dauer)
                {
                    gegner.KaelteZeit = m.Dauer;
                }
                System.out.println("Gegner Abgekuehlt!");
            }
        }
        z = (int)(Math.random()*100+1);
        if(z <= m.Crit)
        {
            int s = (int)(Math.round(m.Angriff*1.5));
            gegner.Leben = gegner.Leben - s;
            System.out.println("Monster hat einen kritischen Treffer gelandet!("+s+" Schaden)");
        }
        else
        {
            gegner.Leben = gegner.Leben - m.Angriff;
            System.out.println("Monster hat einen Treffer gelandet!("+m.Angriff+" Schaden)");
        }
    }

    public FALLEN AufFalle(FALLEN f)
    {
        if(f.Aktiviert)
        {
            return f;
        }
        int z = (int)(Math.random()*100+1); //1-100
        if(z <= gegner.Zerstoeren)
        {
            System.out.println("Falle Zerstoert!");
            return null;
        }
        if(z <= gegner.Zerstoeren+gegner.FAusweichen)
        {
            System.out.println("Ausgewichen!");
            return f;
        }
        gegner.Leben = gegner.Leben - f.Dmg;
        System.out.println("Gegner hat "+f.Dmg+" Schaden erhalten");
        System.out.println("Der Gegner besitzt noch "+gegner.Leben+" Leben");
        z = (int)(Math.random()*100+1);
        if(z <= f.Prozent)
        {
            if(f.Effektart == "Feuer")
            {
                if(gegner.BrennenDmg < f.Effekt)
                {
                    gegner.BrennenDmg = f.Effekt;
                }
                if(gegner.BrennenZeit < f.Dauer)
                {
                    gegner.BrennenZeit = f.Dauer;
                }
                System.out.println("Gegner Verbrannt!");
            }
            if(f.Effektart == "Kaelte")
            {
                if(gegner.KaelteWert < f.Effekt)
                {
                    gegner.KaelteWert = f.Effekt;
                }
                if(gegner.KaelteZeit < f.Dauer)
                {
                    gegner.KaelteZeit = f.Dauer;
                }
                System.out.println("Gegner Abgekuehlt!");
            }
        }
        f.Aktiviert = true;
        try { Thread.sleep((500)); } catch (Exception e) {}
        return f;
    }

    public void Ausgabe()
    {
        if(Verloren || GoldPlatziert)
        {
            AusgabeLoeschen();
            System.out.println("Du hast kein Gold mehr. Du hast verloren");
            return;
        }
        IstVeraendert = false;
        AusgabeLoeschen();

        System.out.println("Der Spieler besitzt " + Spieler.Gold + " Gold.");
        if(Ua > -1 && Ra > -1)
        {
            System.out.println("Ausgewaehltes Feld: Zeile "+(Ua+1)+", Spalte "+(Ra+1));
        }
        else
        {
            System.out.println("Kein Feld ist ausgewaehlt");
        }
        if(ZuWenigGold)
        {
            System.out.println("Zu wenig Gold");
            ZuWenigGold = false;
        }
        if(keinFeldAusg)
        {
            System.out.println("Es wurde kein Feld ausgewaehlt");
            keinFeldAusg = false;
        }
        if(nichtLeer)
        {
            System.out.println("Auf diesem Feld befindet sich schon etwas");
            nichtLeer = false;
        }
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}