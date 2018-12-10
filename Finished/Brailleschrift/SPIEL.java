import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;
    public boolean FGedrueckt;

    public BILD OL = new BILD("bF.png");
    public BILD OR = new BILD("bF.png");
    public BILD ML = new BILD("bF.png");
    public BILD MR = new BILD("bF.png");
    public BILD UL = new BILD("bF.png");
    public BILD UR = new BILD("bF.png");

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;
        OR.SetzeX(70);

        ML.SetzeY(70);

        MR.SetzeX(70);
        MR.SetzeY(70);

        UL.SetzeY(140);

        UR.SetzeX(70);
        UR.SetzeY(140);
    }

    public void RUN(KEYSTATE keystate)
    {
        if(keystate.IsPressed("F") && FGedrueckt)
        {
            return;
        }
        FGedrueckt = false;

        TastenPruefen(keystate);

        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("F"))
        {
            Ausgabe(ZeichenFertig());
            Leeren();
            FGedrueckt = true;
        }
    }

    public void Leeren()
    {
        int x = OL.LeseX();
        int y = OL.LeseY();
        OL.SetzeY(y+250);
        OL = new BILD("bF.png");
        OL.SetzeX(x);
        OL.SetzeY(y);

        x = OR.LeseX();
        y = OR.LeseY();
        OR.SetzeY(y+250);
        OR = new BILD("bF.png");
        OR.SetzeX(x);
        OR.SetzeY(y);

        x = ML.LeseX();
        y = ML.LeseY();
        ML.SetzeY(y+250);
        ML = new BILD("bF.png");
        ML.SetzeX(x);
        ML.SetzeY(y);

        x = MR.LeseX();
        y = MR.LeseY();
        MR.SetzeY(y+250);
        MR = new BILD("bF.png");
        MR.SetzeX(x);
        MR.SetzeY(y);

        x = UL.LeseX();
        y = UL.LeseY();
        UL.SetzeY(y+250);
        UL = new BILD("bF.png");
        UL.SetzeX(x);
        UL.SetzeY(y);

        x = UR.LeseX();
        y = UR.LeseY();
        UR.SetzeY(y+250);
        UR = new BILD("bF.png");
        UR.SetzeX(x);
        UR.SetzeY(y);
    }

    public ZEICHEN ZeichenFertig()
    {
        boolean OL_;boolean OR_;boolean ML_;boolean MR_;boolean UL_;boolean UR_;
        if(OL.LeseName().equals("bF.png"))
        {OL_ = false;} else {OL_ = true;}
        if(OR.LeseName().equals("bF.png"))
        {OR_ = false;} else {OR_ = true;}
        if(ML.LeseName().equals("bF.png"))
        {ML_ = false;} else {ML_ = true;}
        if(MR.LeseName().equals("bF.png"))
        {MR_ = false;} else {MR_ = true;}
        if(UL.LeseName().equals("bF.png"))
        {UL_ = false;} else {UL_ = true;}
        if(UR.LeseName().equals("bF.png"))
        {UR_ = false;} else {UR_ = true;}
        return new ZEICHEN(OL_,OR_,ML_,MR_,UL_,UR_);
    }

    public void MausklickUeberpruefen(Point p)
    {
        if(OL.PunktBeruehrt(p))
        {
            if(OL.LeseName().equals("bF.png"))
            {
                int x = OL.LeseX();
                int y = OL.LeseY();
                OL = new BILD("bT.png");
                OL.SetzeX(x);
                OL.SetzeY(y);
            }
            else
            {
                int x = OL.LeseX();
                int y = OL.LeseY();
                OL = new BILD("bF.png");
                OL.SetzeX(x);
                OL.SetzeY(y);
            }
        }
        if(OR.PunktBeruehrt(p))
        {
            if(OR.LeseName().equals("bF.png"))
            {
                int x = OR.LeseX();
                int y = OR.LeseY();
                OR = new BILD("bT.png");
                OR.SetzeX(x);
                OR.SetzeY(y);
            }
            else
            {
                int x = OR.LeseX();
                int y = OR.LeseY();
                OR = new BILD("bF.png");
                OR.SetzeX(x);
                OR.SetzeY(y);
            }
        }
        if(ML.PunktBeruehrt(p))
        {
            if(ML.LeseName().equals("bF.png"))
            {
                int x = ML.LeseX();
                int y = ML.LeseY();
                ML = new BILD("bT.png");
                ML.SetzeX(x);
                ML.SetzeY(y);
            }
            else
            {
                int x = ML.LeseX();
                int y = ML.LeseY();
                ML = new BILD("bF.png");
                ML.SetzeX(x);
                ML.SetzeY(y);
            }
        }
        if(MR.PunktBeruehrt(p))
        {
            if(MR.LeseName().equals("bF.png"))
            {
                int x = MR.LeseX();
                int y = MR.LeseY();
                MR = new BILD("bT.png");
                MR.SetzeX(x);
                MR.SetzeY(y);
            }
            else
            {
                int x = MR.LeseX();
                int y = MR.LeseY();
                MR = new BILD("bF.png");
                MR.SetzeX(x);
                MR.SetzeY(y);
            }
        }
        if(UL.PunktBeruehrt(p))
        {
            if(UL.LeseName().equals("bF.png"))
            {
                int x = UL.LeseX();
                int y = UL.LeseY();
                UL = new BILD("bT.png");
                UL.SetzeX(x);
                UL.SetzeY(y);
            }
            else
            {
                int x = UL.LeseX();
                int y = UL.LeseY();
                UL = new BILD("bF.png");
                UL.SetzeX(x);
                UL.SetzeY(y);
            }
        }
        if(UR.PunktBeruehrt(p))
        {
            if(UR.LeseName().equals("bF.png"))
            {
                int x = UR.LeseX();
                int y = UR.LeseY();
                UR = new BILD("bT.png");
                UR.SetzeX(x);
                UR.SetzeY(y);
            }
            else
            {
                int x = UR.LeseX();
                int y = UR.LeseY();
                UR = new BILD("bF.png");
                UR.SetzeX(x);
                UR.SetzeY(y);
            }
        }
    }

    public String ZeichenUebersetzen(ZEICHEN z)
    {
        if(z.OL && !z.OR && !z.ML && !z.MR && !z.UL && !z.UR)
        { return "A";}
        if(z.OL && !z.OR && z.ML && !z.MR && !z.UL && !z.UR)
        { return "B";}
        if(z.OL && z.OR && !z.ML && !z.MR && !z.UL && !z.UR)
        { return "C";}
        if(z.OL && z.OR && !z.ML && z.MR && !z.UL && !z.UR)
        { return "D";}
        if(z.OL && !z.OR && !z.ML && z.MR && !z.UL && !z.UR)
        { return "E";}
        if(z.OL && z.OR && z.ML && !z.MR && !z.UL && !z.UR)
        { return "F";}
        if(z.OL && z.OR && z.ML && z.MR && !z.UL && !z.UR)
        { return "G";}
        if(z.OL && !z.OR && z.ML && z.MR && !z.UL && !z.UR)
        { return "H";}
        if(!z.OL && z.OR && z.ML && !z.MR && !z.UL && !z.UR)
        { return "I";}
        if(!z.OL && z.OR && z.ML && z.MR && !z.UL && !z.UR)
        { return "J";}
        if(z.OL && !z.OR && !z.ML && !z.MR && z.UL && !z.UR)
        { return "K";}
        if(z.OL && !z.OR && z.ML && !z.MR && z.UL && !z.UR)
        { return "L";}
        if(z.OL && z.OR && !z.ML && !z.MR && z.UL && !z.UR)
        { return "M";}
        if(z.OL && z.OR && !z.ML && z.MR && z.UL && !z.UR)
        { return "N";}
        if(z.OL && !z.OR && !z.ML && z.MR && z.UL && !z.UR)
        { return "O";}
        if(z.OL && z.OR && z.ML && !z.MR && z.UL && !z.UR)
        { return "P";}
        if(z.OL && z.OR && z.ML && z.MR && z.UL && !z.UR)
        { return "Q";}
        if(z.OL && !z.OR && z.ML && z.MR && z.UL && !z.UR)
        { return "R";}
        if(!z.OL && z.OR && z.ML && !z.MR && z.UL && !z.UR)
        { return "S";}
        if(!z.OL && z.OR && z.ML && z.MR && z.UL && !z.UR)
        { return "T";}
        if(z.OL && !z.OR && !z.ML && !z.MR && z.UL && z.UR)
        { return "U";}
        if(z.OL && !z.OR && z.ML && !z.MR && z.UL && z.UR)
        { return "V";}
        if(!z.OL && z.OR && z.ML && z.MR && !z.UL && z.UR)
        { return "W";}
        if(z.OL && z.OR && !z.ML && !z.MR && z.UL && z.UR)
        { return "X";}
        if(z.OL && z.OR && !z.ML && z.MR && z.UL && z.UR)
        { return "Y";}
        if(z.OL && !z.OR && !z.ML && z.MR && z.UL && z.UR)
        { return "Z";}
        if(!z.OL && z.OR && !z.ML && z.MR && z.UL && !z.UR)
        { return "Ä";}
        if(!z.OL && z.OR && z.ML && !z.MR && !z.UL && z.UR)
        { return "Ö";}
        if(z.OL && !z.OR && z.ML && z.MR && !z.UL && z.UR)
        { return "Ü";}
        if(z.OL && z.OR && z.ML && z.MR && z.UL && z.UR)
        {
            return null; //Volles Feld-> danach . || ,
        }
        if(!z.OL && !z.OR && !z.ML && !z.MR && z.UL && !z.UR)
        { return ".";}
        if(!z.OL && !z.OR && z.ML && !z.MR && !z.UL && !z.UR)
        { return ",";}
        if(!z.OL && !z.OR && !z.ML && !z.MR && !z.UL && !z.UR)
        { return "_";} //Leerzeichen
        if(!z.OL && !z.OR && !z.ML && !z.MR && !z.UL && z.UR)
        { return "NEUEz";} //Neue Zeile

        return "";
    }

    public void Ausgabe(ZEICHEN z)
    {
        String s = ZeichenUebersetzen(z);
        if(s != null && s.equals("NEUEz"))
        {
            System.out.println("");
            return;
        }
        if(s != null)
        {
            System.out.print(s);
        }
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}