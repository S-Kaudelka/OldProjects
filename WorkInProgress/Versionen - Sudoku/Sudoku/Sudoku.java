public class Sudoku
{
    private int Nummer;
    
    private int A1;    private int A2;    private int A3;    private int A4;    private int A5;
    private int A6;    private int A7;    private int A8;    private int A9;

    private int B1;    private int B2;    private int B3;    private int B4;    private int B5;
    private int B6;    private int B7;    private int B8;    private int B9;

    private int C1;    private int C2;    private int C3;    private int C4;    private int C5;
    private int C6;    private int C7;    private int C8;    private int C9;

    private int D1;    private int D2;    private int D3;    private int D4;    private int D5;
    private int D6;    private int D7;    private int D8;    private int D9;

    private int E1;    private int E2;    private int E3;    private int E4;    private int E5;
    private int E6;    private int E7;    private int E8;    private int E9;

    private int F1;    private int F2;    private int F3;    private int F4;    private int F5;
    private int F6;    private int F7;    private int F8;    private int F9;

    private int G1;    private int G2;    private int G3;    private int G4;    private int G5;
    private int G6;    private int G7;    private int G8;    private int G9;

    private int H1;    private int H2;    private int H3;    private int H4;    private int H5;
    private int H6;    private int H7;    private int H8;    private int H9;

    private int I1;    private int I2;    private int I3;    private int I4;    private int I5;
    private int I6;    private int I7;    private int I8;    private int I9;

    public Sudoku(int Nummer_p)
    {
        Nummer = Nummer_p;
        SudokuErstellen(Nummer_p);
        SudokuAnzeigen();
    }

    private void SudokuErstellen(int Nummer_e)
    {
        if (Nummer_e == 1) 
        {
            A4=2; A7=8; A8=1;
            B3=4; B5=9; B6=7; B8=3; B9=5;
            C1=2; C3=5; C6=1;
            D1=5; D2=7; D4=1; D7=9; D9=4;
            E2=4; E5=6; E8=2;
            F1=3; F3=6; F6=4; F8=7; F9=8;
            G4=8; G7=3; G9=7;
            H1=7; H2=6; H4=5; H5=2; H7=4;
            I2=1; I3=9; I6=3;
        }
        
        if (Nummer_e == 2)
        {
            A2=4; A7=9; A8=5;
            B1=9; B3=5; B4=3; B7=8; B9=6;
            C1=7; C2=2; C4=9; C6=5; C8=1;
            D3=3; D4=4; D6=7; D7=2; D8=8;
            E5=2;
            F2=7; F3=1; F4=6; F6=9; F7=5;
            G2=3; G4=8; G6=6; G8=2; G9=1;
            H1=8; H3=7; H6=1; H7=4; H9=5;
            I2=9; I3=2; I8=3;
        }
    }

    public String ZahlEinsetzen(int Zahl, String Feld)
    {    
        if (Zahl > 9 || Zahl < 0)
        {
            return "Ungültige Zahl";
        }
        if (Nummer == 1)
        {
            if (Feld == "A4" || Feld == "A7" || Feld == "A8" || Feld == "B3" || Feld == "B5" || Feld == "B6" || Feld == "B8" || Feld == "B9" || Feld == "C1" || Feld == "C3" || Feld == "C6" || Feld == "D1" || Feld == "D2" || Feld == "D4" || Feld == "D7" || Feld == "D9" || Feld == "E2" || Feld == "E5" || Feld == "E8" || Feld == "F1" || Feld == "F3" || Feld == "F6" || Feld == "F8" || Feld == "F9" || Feld == "G4" || Feld == "G7" || Feld == "G9" || Feld == "H1" || Feld == "H2" || Feld == "H4" || Feld == "H5" || Feld == "H7" || Feld == "I2" || Feld == "I3" || Feld == "I6")
            {
                return "In diesem Feld ist keine Eingabe möglich";
            }
        }
        if (Nummer == 2)
        {
            if (Feld == "A2" || Feld == "A7" || Feld == "A8" || Feld == "B1" || Feld == "B3" || Feld == "B4" || Feld == "B7" || Feld == "B9" || Feld == "C1" || Feld == "C2" || Feld == "C4" || Feld == "C6" || Feld == "C8" || Feld == "D3" || Feld == "D4" || Feld == "D6" || Feld == "D7" || Feld == "D8" || Feld == "E5" || Feld == "F2" || Feld == "F3" || Feld == "F4" || Feld == "F6" || Feld == "F7" || Feld == "G2" || Feld == "G4" || Feld == "G6" || Feld == "G8" || Feld == "G9" || Feld == "H1" || Feld == "H3" || Feld == "H6" || Feld == "H7" || Feld == "H9" || Feld == "I2" || Feld == "I3" || Feld == "I8")
            {
                return "In diesem Feld ist keine Eingabe möglich";
            }
        }
        if (Feld == "A1")
        {
            A1 = Zahl;
        }
        if (Feld == "A2")
        {
            A2 = Zahl;
        }
        if (Feld == "A3")
        {
            A3 = Zahl;
        }
        if (Feld == "A4")
        {
            A4 = Zahl;
        }
        if (Feld == "A5")
        {
            A5 = Zahl;
        }
        if (Feld == "A6")
        {
            A6 = Zahl;
        }
        if (Feld == "A7")
        {
            A7 = Zahl;
        }
        if (Feld == "A8")
        {
            A8 = Zahl;
        }
        if (Feld == "A9")
        {
            A9 = Zahl;
        }
        if (Feld == "B1")
        {
            B1 = Zahl;
        }
        if (Feld == "B2")
        {
            B2 = Zahl;
        }
        if (Feld == "B3")
        {
            B3 = Zahl;
        }
        if (Feld == "B4")
        {
            B4 = Zahl;
        }
        if (Feld == "B5")
        {
            B5 = Zahl;
        }
        if (Feld == "B6")
        {
            B6 = Zahl;
        }
        if (Feld == "B7")
        {
            B7 = Zahl;
        }
        if (Feld == "B8")
        {
            B8 = Zahl;
        }
        if (Feld == "B9")
        {
            B9 = Zahl;
        }
        if (Feld == "C1")
        {
            C1 = Zahl;
        }
        if (Feld == "C2")
        {
            C2 = Zahl;
        }
        if (Feld == "C3")
        {
            C3 = Zahl;
        }
        if (Feld == "C4")
        {
            C4 = Zahl;
        }
        if (Feld == "C5")
        {
            C5 = Zahl;
        }
        if (Feld == "C6")
        {
            C6 = Zahl;
        }
        if (Feld == "C7")
        {
            C7 = Zahl;
        }
        if (Feld == "C8")
        {
            C8 = Zahl;
        }
        if (Feld == "C9")
        {
            C9 = Zahl;
        }
        if (Feld == "D1")
        {
            D1 = Zahl;
        }
        if (Feld == "D2")
        {
            D2 = Zahl;
        }
        if (Feld == "D3")
        {
            D3 = Zahl;
        }
        if (Feld == "D4")
        {
            D4 = Zahl;
        }
        if (Feld == "D5")
        {
            D5 = Zahl;
        }
        if (Feld == "D6")
        {
            D6 = Zahl;
        }
        if (Feld == "D7")
        {
            D7 = Zahl;
        }
        if (Feld == "D8")
        {
            D8 = Zahl;
        }
        if (Feld == "D9")
        {
            D9 = Zahl;
        }
        if (Feld == "E1")
        {
            E1 = Zahl;
        }
        if (Feld == "E2")
        {
            E2 = Zahl;
        }
        if (Feld == "E3")
        {
            E3 = Zahl;
        }
        if (Feld == "E4")
        {
            E4 = Zahl;
        }
        if (Feld == "E5")
        {
            E5 = Zahl;
        }
        if (Feld == "E6")
        {
            E6 = Zahl;
        }
        if (Feld == "E7")
        {
            E7 = Zahl;
        }
        if (Feld == "E8")
        {
            E8 = Zahl;
        }
        if (Feld == "E9")
        {
            E9 = Zahl;
        }
        if (Feld == "F1")
        {
            F1 = Zahl;
        }
        if (Feld == "F2")
        {
            F2 = Zahl;
        }
        if (Feld == "F3")
        {
            F3 = Zahl;
        }
        if (Feld == "F4")
        {
            F4 = Zahl;
        }
        if (Feld == "F5")
        {
            F5 = Zahl;
        }
        if (Feld == "F6")
        {
            F6 = Zahl;
        }
        if (Feld == "F7")
        {
            F7 = Zahl;
        }
        if (Feld == "F8")
        {
            F8 = Zahl;
        }
        if (Feld == "F9")
        {
            F9 = Zahl;
        }
        if (Feld == "G1")
        {
            G1 = Zahl;
        }
        if (Feld == "G2")
        {
            G2 = Zahl;
        }
        if (Feld == "G3")
        {
            G3 = Zahl;
        }
        if (Feld == "G4")
        {
            G4 = Zahl;
        }
        if (Feld == "G5")
        {
            G5 = Zahl;
        }
        if (Feld == "G6")
        {
            G6 = Zahl;
        }
        if (Feld == "G7")
        {
            G7 = Zahl;
        }
        if (Feld == "G8")
        {
            G8 = Zahl;
        }
        if (Feld == "G9")
        {
            G9 = Zahl;
        }
        if (Feld == "H1")
        {
            H1 = Zahl;
        }
        if (Feld == "H2")
        {
            H2 = Zahl;
        }
        if (Feld == "H3")
        {
            H3 = Zahl;
        }
        if (Feld == "H4")
        {
            H4 = Zahl;
        }
        if (Feld == "H5")
        {
            H5 = Zahl;
        }
        if (Feld == "H6")
        {
            H6 = Zahl;
        }
        if (Feld == "H7")
        {
            H7 = Zahl;
        }
        if (Feld == "H8")
        {
            H8 = Zahl;
        }
        if (Feld == "H9")
        {
            H9 = Zahl;
        }
        if (Feld == "I1")
        {
            I1 = Zahl;
        }
        if (Feld == "I2")
        {
            I2 = Zahl;
        }
        if (Feld == "I3")
        {
            I3 = Zahl;
        }
        if (Feld == "I4")
        {
            I4 = Zahl;
        }
        if (Feld == "I5")
        {
            I5 = Zahl;
        }
        if (Feld == "I6")
        {
            I6 = Zahl;
        }
        if (Feld == "I7")
        {
            I7 = Zahl;
        }
        if (Feld == "I8")
        {
            I8 = Zahl;
        }
        if (Feld == "I9")
        {
            I9 = Zahl;
        }
        if (Feld != "A1"&&Feld != "A2"&&Feld != "A3"&&Feld != "A4"&&Feld != "A5"&&Feld != "A6"&&Feld != "A7"&&Feld != "A8"&&Feld != "A9"&&Feld != "B1"&&Feld != "B2"&&Feld != "B3"&&Feld != "B4"&&Feld != "B5"&&Feld != "B6"&&Feld != "B7"&&Feld != "B8"&&Feld != "B9"&&Feld != "C1"&&Feld != "C2"&&Feld != "C3"&&Feld != "C4"&&Feld != "C5"&&Feld != "C6"&&Feld != "C7"&&Feld != "C8"&&Feld != "C9"&&Feld != "D1"&&Feld != "D2"&&Feld != "D3"&&Feld != "D4"&&Feld != "D5"&&Feld != "D6"&&Feld != "D7"&&Feld != "D8"&&Feld != "D9"&&Feld != "E1"&&Feld != "E2"&&Feld != "E3"&&Feld != "E4"&&Feld != "E5"&&Feld != "E6"&&Feld != "E7"&&Feld != "E8"&&Feld != "E9"&&Feld != "F1"&&Feld != "F2"&&Feld != "F3"&&Feld != "F4"&&Feld != "F5"&&Feld != "F6"&&Feld != "F7"&&Feld != "F8"&&Feld != "F9"&&Feld != "G1"&&Feld != "G2"&&Feld != "G3"&&Feld != "G4"&&Feld != "G5"&&Feld != "G6"&&Feld != "G7"&&Feld != "G8"&&Feld != "G9"&&Feld != "H1"&&Feld != "H2"&&Feld != "H3"&&Feld != "H4"&&Feld != "H5"&&Feld != "H6"&&Feld != "H7"&&Feld != "H8"&&Feld != "H9"&&Feld != "I1"&&Feld != "I2"&&Feld != "I3"&&Feld != "I4"&&Feld != "I5"&&Feld != "I6"&&Feld != "I7"&&Feld != "I8"&&Feld != "I9")
        {
            return "Ungültiges Feld";
        }
        else
        {
            SudokuAnzeigen();
            return "Zahl Eigefügt";
        }
    }        

    public void SudokuAnzeigen()
    {
        System.out.println("  1 2 3   4 5 6   7 8 9");
        System.out.println("  _____________________");
        System.out.println("A|"+A1+" "+A2+" "+A3+" | "+A4+" "+A5+" "+A6+" | "+A7+" "+A8+" "+A9);
        System.out.println("B|"+B1+" "+B2+" "+B3+" | "+B4+" "+B5+" "+B6+" | "+B7+" "+B8+" "+B9);
        System.out.println("C|"+C1+" "+C2+" "+C3+" | "+C4+" "+C5+" "+C6+" | "+C7+" "+C8+" "+C9);
        System.out.println("  ---------------------");
        System.out.println("D|"+D1+" "+D2+" "+D3+" | "+D4+" "+D5+" "+D6+" | "+D7+" "+D8+" "+D9);
        System.out.println("E|"+E1+" "+E2+" "+E3+" | "+E4+" "+E5+" "+E6+" | "+E7+" "+E8+" "+E9);
        System.out.println("F|"+F1+" "+F2+" "+F3+" | "+F4+" "+F5+" "+F6+" | "+F7+" "+F8+" "+F9);
        System.out.println("  ---------------------");
        System.out.println("G|"+G1+" "+G2+" "+G3+" | "+G4+" "+G5+" "+G6+" | "+G7+" "+G8+" "+G9);
        System.out.println("H|"+H1+" "+H2+" "+H3+" | "+H4+" "+H5+" "+H6+" | "+H7+" "+H8+" "+H9);
        System.out.println("I|"+I1+" "+I2+" "+I3+" | "+I4+" "+I5+" "+I6+" | "+I7+" "+I8+" "+I9);
    }
    
    public String Kontrolle()
    {
        if (// Kontrolle von LINKS nach RECHTS
            A1 != A2 && A1 != A3 && A1 != A4 && A1 != A5 && A1 != A6 && A1 != A7 && A1 != A8 && A1 != A9 && A2 != A3 && A2 != A4 && A2 != A5 && A2 != A6 && A2 != A7 && A2 != A8 && A2 != A9 && A3 != A4 && A3 != A5 && A3 != A6 && A3 != A7 && A3 != A8 && A3 != A9 && A4 != A5 && A4 != A6 && A4 != A7 && A4 != A8 && A4 != A9 && A5 != A6 && A5 != A7 && A5 != A8 && A5 != A9 && A6 != A7 && A6 != A8 && A6 != A9 && A7 != A8 && A7 != A9 && A8 != A9 &&
            B1 != B2 && B1 != B3 && B1 != B4 && B1 != B5 && B1 != B6 && B1 != B7 && B1 != B8 && B1 != B9 && B2 != B3 && B2 != B4 && B2 != B5 && B2 != B6 && B2 != B7 && B2 != B8 && B2 != B9 && B3 != B4 && B3 != B5 && B3 != B6 && B3 != B7 && B3 != B8 && B3 != B9 && B4 != B5 && B4 != B6 && B4 != B7 && B4 != B8 && B4 != B9 && B5 != B6 && B5 != B7 && B5 != B8 && B5 != B9 && B6 != B7 && B6 != B8 && B6 != B9 && B7 != B8 && B7 != B9 && B8 != B9 &&
            C1 != C2 && C1 != C3 && C1 != C4 && C1 != C5 && C1 != C6 && C1 != C7 && C1 != C8 && C1 != C9 && C2 != C3 && C2 != C4 && C2 != C5 && C2 != C6 && C2 != C7 && C2 != C8 && C2 != C9 && C3 != C4 && C3 != C5 && C3 != C6 && C3 != C7 && C3 != C8 && C3 != C9 && C4 != C5 && C4 != C6 && C4 != C7 && C4 != C8 && C4 != C9 && C5 != C6 && C5 != C7 && C5 != C8 && C5 != C9 && C6 != C7 && C6 != C8 && C6 != C9 && C7 != C8 && C7 != C9 && C8 != C9 &&
            D1 != D2 && D1 != D3 && D1 != D4 && D1 != D5 && D1 != D6 && D1 != D7 && D1 != D8 && D1 != D9 && D2 != D3 && D2 != D4 && D2 != D5 && D2 != D6 && D2 != D7 && D2 != D8 && D2 != D9 && D3 != D4 && D3 != D5 && D3 != D6 && D3 != D7 && D3 != D8 && D3 != D9 && D4 != D5 && D4 != D6 && D4 != D7 && D4 != D8 && D4 != D9 && D5 != D6 && D5 != D7 && D5 != D8 && D5 != D9 && D6 != D7 && D6 != D8 && D6 != D9 && D7 != D8 && D7 != D9 && D8 != D9 &&
            E1 != E2 && E1 != E3 && E1 != E4 && E1 != E5 && E1 != E6 && E1 != E7 && E1 != E8 && E1 != E9 && A2 != E3 && E2 != E4 && E2 != E5 && E2 != E6 && E2 != E7 && E2 != E8 && E2 != E9 && E3 != E4 && E3 != E5 && E3 != E6 && E3 != E7 && E3 != E8 && E3 != E9 && E4 != E5 && E4 != E6 && E4 != E7 && E4 != E8 && E4 != E9 && E5 != E6 && E5 != E7 && E5 != E8 && E5 != E9 && E6 != E7 && E6 != E8 && E6 != E9 && E7 != E8 && E7 != E9 && E8 != E9 &&
            F1 != F2 && F1 != F3 && F1 != F4 && F1 != F5 && F1 != F6 && F1 != F7 && F1 != F8 && F1 != F9 && F2 != F3 && F2 != F4 && F2 != F5 && F2 != F6 && F2 != F7 && F2 != F8 && F2 != F9 && F3 != F4 && F3 != F5 && F3 != F6 && F3 != F7 && F3 != F8 && F3 != F9 && F4 != F5 && F4 != F6 && F4 != F7 && F4 != F8 && F4 != F9 && F5 != F6 && F5 != F7 && F5 != F8 && F5 != F9 && F6 != F7 && F6 != F8 && F6 != F9 && F7 != F8 && F7 != F9 && F8 != F9 &&
            G1 != G2 && G1 != G3 && G1 != G4 && G1 != G5 && G1 != G6 && G1 != G7 && G1 != G8 && G1 != G9 && G2 != G3 && G2 != G4 && G2 != G5 && G2 != G6 && G2 != G7 && G2 != G8 && G2 != G9 && G3 != G4 && G3 != G5 && G3 != G6 && G3 != G7 && G3 != G8 && G3 != G9 && G4 != G5 && G4 != G6 && G4 != G7 && G4 != G8 && G4 != G9 && G5 != G6 && G5 != G7 && G5 != G8 && G5 != G9 && G6 != G7 && G6 != G8 && G6 != G9 && G7 != G8 && G7 != G9 && G8 != G9 &&
            H1 != H2 && H1 != H3 && H1 != H4 && H1 != H5 && H1 != H6 && H1 != H7 && H1 != H8 && H1 != H9 && H2 != H3 && H2 != H4 && H2 != H5 && H2 != H6 && H2 != H7 && H2 != H8 && H2 != H9 && H3 != H4 && H3 != H5 && H3 != H6 && H3 != H7 && H3 != H8 && H3 != H9 && H4 != H5 && H4 != H6 && H4 != H7 && H4 != H8 && H4 != H9 && H5 != H6 && H5 != H7 && H5 != H8 && H5 != H9 && H6 != H7 && H6 != H8 && H6 != H9 && H7 != H8 && H7 != H9 && H8 != H9 &&
            I1 != I2 && I1 != I3 && I1 != I4 && I1 != I5 && I1 != I6 && I1 != I7 && I1 != I8 && I1 != I9 && I2 != I3 && I2 != I4 && I2 != I5 && I2 != I6 && I2 != I7 && I2 != I8 && I2 != I9 && I3 != I4 && I3 != I5 && I3 != I6 && I3 != I7 && I3 != I8 && I3 != I9 && I4 != I5 && I4 != I6 && I4 != I7 && I4 != I8 && I4 != I9 && I5 != I6 && I5 != I7 && I5 != I8 && I5 != I9 && I6 != I7 && I6 != I8 && I6 != I9 && I7 != I8 && I7 != I9 && I8 != I9 &&
            // Kontrolle von OBEN nach UNTEN
            A1 != B1 && A1 != C1 && A1 != D1 && A1 != E1 && A1 != F1 && A1 != G1 && A1 != H1 && A1 != I1 && B1 != C1 && B1 != D1 && B1 != E1 && B1 != F1 && B1 != G1 && B1 != H1 && B1 != I1 && C1 != D1 && C1 != E1 && C1 != F1 && C1 != G1 && C1 != H1 && C1 != I1 && D1 != E1 && D1 != F1 && D1 != G1 && D1 != H1 && D1 != I1 && E1 != F1 && E1 != G1 && E1 != H1 && E1 != I1 && F1 != G1 && F1 != H1 && F1 != I1 && G1 != H1 && G1 != I1 && H1 != I1 &&
            A2 != B2 && A2 != C2 && A2 != D2 && A2 != E2 && A2 != F2 && A2 != G2 && A2 != H2 && A2 != I2 && B2 != C2 && B2 != D2 && B2 != E2 && B2 != F2 && B2 != G2 && B2 != H2 && B2 != I2 && C2 != D2 && C2 != E2 && C2 != F2 && C2 != G2 && C2 != H2 && C2 != I2 && D2 != E2 && D2 != F2 && D2 != G2 && D2 != H2 && D2 != I2 && E2 != F2 && E2 != G2 && E2 != H2 && E2 != I2 && F2 != G2 && F2 != H2 && F2 != I2 && G2 != H2 && G2 != I2 && H2 != I2 &&
            A3 != B3 && A3 != C3 && A3 != D3 && A3 != E3 && A3 != F3 && A3 != G3 && A3 != H3 && A3 != I3 && B3 != C3 && B3 != D3 && B3 != E3 && B3 != F3 && B3 != G3 && B3 != H3 && B3 != I3 && C3 != D3 && C3 != E3 && C3 != F3 && C3 != G3 && C3 != H3 && C3 != I3 && D3 != E3 && D3 != F3 && D3 != G3 && D3 != H3 && D3 != I3 && E3 != F3 && E3 != G3 && E3 != H3 && E3 != I3 && F3 != G3 && F3 != H3 && F3 != I3 && G3 != H3 && G3 != I3 && H3 != I3 &&
            A4 != B4 && A4 != C4 && A4 != D4 && A4 != E4 && A4 != F4 && A4 != G4 && A4 != H4 && A4 != I4 && B4 != C4 && B4 != D4 && B4 != E4 && B4 != F4 && B4 != G4 && B4 != H4 && B4 != I4 && C4 != D4 && C4 != E4 && C4 != F4 && C4 != G4 && C4 != H4 && C4 != I4 && D4 != E4 && D4 != F4 && D4 != G4 && D4 != H4 && D4 != I4 && E4 != F4 && E4 != G4 && E4 != H4 && E4 != I4 && F4 != G4 && F4 != H4 && F4 != I4 && G4 != H4 && G4 != I4 && H4 != I4 &&
            A5 != B5 && A5 != C5 && A5 != D5 && A5 != E5 && A5 != F5 && A5 != G5 && A5 != H5 && A5 != I5 && B5 != C5 && B5 != D5 && B5 != E5 && B5 != F5 && B5 != G5 && B5 != H5 && B5 != I5 && C5 != D5 && C5 != E5 && C5 != F5 && C5 != G5 && C5 != H5 && C5 != I5 && D5 != E5 && D5 != F5 && D5 != G5 && D5 != H5 && D5 != I5 && E5 != F5 && E5 != G5 && E5 != H5 && E5 != I5 && F5 != G5 && F5 != H5 && F5 != I5 && G5 != H5 && G5 != I5 && H5 != I5 &&
            A6 != B6 && A6 != C6 && A6 != D6 && A6 != E6 && A6 != F6 && A6 != G6 && A6 != H6 && A6 != I6 && B6 != C6 && B6 != D6 && B6 != E6 && B6 != F6 && B6 != G6 && B6 != H6 && B6 != I6 && C6 != D6 && C6 != E6 && C6 != F6 && C6 != G6 && C6 != H6 && C6 != I6 && D6 != E6 && D6 != F6 && D6 != G6 && D6 != H6 && D6 != I6 && E6 != F6 && E6 != G6 && E6 != H6 && E6 != I6 && F6 != G6 && F6 != H6 && F6 != I6 && G6 != H6 && G6 != I6 && H6 != I6 &&
            A7 != B7 && A7 != C7 && A7 != D7 && A7 != E7 && A7 != F7 && A7 != G7 && A7 != H7 && A7 != I7 && B7 != C7 && B7 != D7 && B7 != E7 && B7 != F7 && B7 != G7 && B7 != H7 && B7 != I7 && C7 != D7 && C7 != E7 && C7 != F7 && C7 != G7 && C7 != H7 && C7 != I7 && D7 != E7 && D7 != F7 && D7 != G7 && D7 != H7 && D7 != I7 && E7 != F7 && E7 != G7 && E7 != H7 && E7 != I7 && F7 != G7 && F7 != H7 && F7 != I7 && G7 != H7 && G7 != I7 && H7 != I7 &&
            A8 != B8 && A8 != C8 && A8 != D8 && A8 != E8 && A8 != F8 && A8 != G8 && A8 != H8 && A8 != I8 && B8 != C8 && B8 != D8 && B8 != E8 && B8 != F8 && B8 != G8 && B8 != H8 && B8 != I8 && C8 != D8 && C8 != E8 && C8 != F8 && C8 != G8 && C8 != H8 && C8 != I8 && D8 != E8 && D8 != F8 && D8 != G8 && D8 != H8 && D8 != I8 && E8 != F8 && E8 != G8 && E8 != H8 && E8 != I8 && F8 != G8 && F8 != H8 && F8 != I8 && G8 != H8 && G8 != I8 && H8 != I8 &&
            A9 != B9 && A9 != C9 && A9 != D9 && A9 != E9 && A9 != F9 && A9 != G9 && A9 != H9 && A9 != I9 && B9 != C9 && B9 != D9 && B9 != E9 && B9 != F9 && B9 != G9 && B9 != H9 && B9 != I9 && C9 != D9 && C9 != E9 && C9 != F9 && C9 != G9 && C9 != H9 && C9 != I9 && D9 != E9 && D9 != F9 && D9 != G9 && D9 != H9 && D9 != I9 && E9 != F9 && E9 != G9 && E9 != H9 && E9 != I9 && F9 != G9 && F9 != H9 && F9 != I9 && G9 != H9 && G9 != I9 && H9 != I9 &&
            // 9ER-BLOCK-KONTROLLE
            A1 != A2 && A1 != A3 && A1 != B1 && A1 != B2 && A1 != B3 && A1 != C1 && A1 != C2 && A1 != C3 && A2 != A3 && A2 != B1 && A2 != B2 && A2 != B3 && A2 != C1 && A2 != C2 && A2 != C3 && A3 != B1 && A3 != B2 && A3 != B3 && A3 != C1 && A3 != C2 && A3 != C3 && B1 != B2 && B1 != B3 && B1 != C1 && B1 != C2 && B1 != C3 && B2 != B3 && B2 != C1 && B2 != C2 && B2 != C3 && B3 != C1 && B3 != C2 && B3 != C3 && C1 != C2 && C1 != C3 && C2 != C3 &&
            A4 != A5 && A4 != A6 && A4 != B4 && A4 != B5 && A4 != B6 && A4 != C4 && A4 != C5 && A4 != C6 && A5 != A6 && A5 != B4 && A5 != B5 && A5 != B6 && A5 != C4 && A5 != C5 && A5 != C6 && A6 != B4 && A6 != B5 && A6 != B6 && A6 != C4 && A6 != C5 && A6 != C6 && B4 != B5 && B4 != B6 && B4 != C4 && B4 != C5 && B4 != C6 && B5 != B6 && B5 != C4 && B5 != C5 && B5 != C6 && B6 != C4 && B6 != C5 && B6 != C6 && C4 != C5 && C4 != C6 && C5 != C6 &&
            A7 != A8 && A7 != A9 && A7 != B7 && A7 != B8 && A7 != B9 && A7 != C7 && A7 != C8 && A7 != C9 && A8 != A9 && A8 != B7 && A8 != B8 && A8 != B9 && A8 != C7 && A8 != C8 && A8 != C9 && A9 != B7 && A9 != B8 && A9 != B9 && A9 != C7 && A9 != C8 && A9 != C9 && B7 != B8 && B7 != B9 && B7 != C7 && B7 != C8 && B7 != C9 && B8 != B9 && B8 != C7 && B8 != C8 && B8 != C9 && B9 != C7 && B9 != C8 && B9 != C9 && C7 != C8 && C7 != C9 && C8 != C9 &&
            D1 != D2 && D1 != D3 && D1 != E1 && D1 != E2 && D1 != E3 && D1 != F1 && D1 != F2 && D1 != F3 && D2 != D3 && D2 != E1 && D2 != E2 && D2 != E3 && D2 != F1 && D2 != F2 && D2 != F3 && D3 != E1 && D3 != E2 && D3 != E3 && D3 != F1 && D3 != F2 && D3 != F3 && E1 != E2 && E1 != E3 && E1 != F1 && E1 != F2 && E1 != F3 && E2 != E3 && E2 != F1 && E2 != F2 && E2 != F3 && E3 != F1 && E3 != F2 && E3 != F3 && F1 != F2 && F1 != F3 && F2 != F3 &&
            D4 != D5 && D4 != D6 && D4 != E4 && D4 != E5 && D4 != E6 && D4 != F4 && D4 != F5 && D4 != F6 && D5 != D6 && D5 != E4 && D5 != E5 && D5 != E6 && D5 != F4 && D5 != F5 && D5 != F6 && D6 != E4 && D6 != E5 && D6 != E6 && D6 != F4 && D6 != F5 && D6 != F6 && E4 != E5 && E4 != E6 && E4 != F4 && E4 != F5 && E4 != F6 && E5 != E6 && E5 != F4 && E5 != F5 && E5 != F6 && E6 != F4 && E6 != F5 && E6 != F6 && F4 != F5 && F4 != F6 && F5 != F6 &&
            D7 != D8 && D7 != D9 && D7 != E7 && D7 != E8 && D7 != E9 && D7 != F7 && D7 != F8 && D7 != F9 && D8 != D9 && D8 != E7 && D8 != E8 && D8 != E9 && D8 != F7 && D8 != F8 && D8 != F9 && D9 != E7 && D9 != E8 && D9 != E9 && D9 != F7 && D9 != F8 && D9 != F9 && E7 != E8 && E7 != E9 && E7 != F7 && E7 != F8 && E7 != F9 && E8 != E9 && E8 != F7 && E8 != F8 && E8 != F9 && E9 != F7 && E9 != F8 && E9 != F9 && F7 != F8 && F7 != F9 && F8 != F9 &&
            G1 != G2 && G1 != G3 && G1 != H1 && G1 != H2 && I1 != H3 && G1 != I1 && G1 != I2 && G1 != I3 && G2 != G3 && G2 != H1 && G2 != H2 && G2 != H3 && G2 != I1 && G2 != I2 && G2 != I3 && G3 != H1 && G3 != H2 && G3 != H3 && G3 != I1 && G3 != I2 && G3 != I3 && H1 != H2 && H1 != H3 && H1 != I1 && H1 != I2 && H1 != I3 && H2 != H3 && H2 != I1 && H2 != I2 && H2 != I3 && H3 != I1 && H3 != I2 && H3 != I3 && I1 != I2 && I1 != I3 && I2 != I3 &&
            G4 != G5 && G4 != G6 && G4 != H4 && G4 != H5 && I4 != H6 && G4 != I4 && G4 != I5 && G4 != I6 && G5 != G6 && G5 != H4 && G5 != H5 && G5 != H6 && G5 != I4 && G5 != I5 && G5 != I6 && G6 != H4 && G6 != H5 && G6 != H6 && G6 != I4 && G6 != I5 && G6 != I6 && H4 != H5 && H4 != H6 && H4 != I4 && H4 != I5 && H4 != I6 && H5 != H6 && H5 != I4 && H5 != I5 && H5 != I6 && H6 != I4 && H6 != I5 && H6 != I6 && I4 != I5 && I4 != I6 && I5 != I6 &&
            G7 != G8 && G7 != G9 && G7 != H7 && G7 != H8 && I7 != H9 && G7 != I7 && G7 != I8 && G7 != I9 && G8 != G9 && G8 != H7 && G8 != H8 && G8 != H9 && G8 != I7 && G8 != I8 && G8 != I9 && G9 != H7 && G9 != H8 && G9 != H9 && G9 != I7 && G9 != I8 && G9 != I9 && H7 != H8 && H7 != H9 && H7 != I7 && H7 != I8 && H7 != I9 && H8 != H9 && H8 != I7 && H8 != I8 && H8 != I9 && H9 != I7 && H9 != I8 && H9 != I9 && I7 != I8 && I7 != I9 && I8 != I9 &&
            // KONTROLLE, dass keine "0" drinsteht
            A1 != 0 && A2 != 0 && A3 != 0 && A4 != 0 && A5 != 0 && A6 != 0 && A7 != 0 && A8 != 0 && A9 != 0 && 
            B1 != 0 && B2 != 0 && B3 != 0 && B4 != 0 && B5 != 0 && B6 != 0 && B7 != 0 && B8 != 0 && B9 != 0 && 
            C1 != 0 && C2 != 0 && C3 != 0 && C4 != 0 && C5 != 0 && C6 != 0 && C7 != 0 && C8 != 0 && C9 != 0 && 
            D1 != 0 && D2 != 0 && D3 != 0 && D4 != 0 && D5 != 0 && D6 != 0 && D7 != 0 && D8 != 0 && D9 != 0 && 
            E1 != 0 && E2 != 0 && E3 != 0 && E4 != 0 && E5 != 0 && E6 != 0 && E7 != 0 && E8 != 0 && E9 != 0 && 
            F1 != 0 && F2 != 0 && F3 != 0 && F4 != 0 && F5 != 0 && F6 != 0 && F7 != 0 && F8 != 0 && F9 != 0 && 
            G1 != 0 && G2 != 0 && G3 != 0 && G4 != 0 && G5 != 0 && G6 != 0 && G7 != 0 && G8 != 0 && G9 != 0 && 
            H1 != 0 && H2 != 0 && H3 != 0 && H4 != 0 && H5 != 0 && H6 != 0 && H7 != 0 && H8 != 0 && H9 != 0 && 
            I1 != 0 && I2 != 0 && I3 != 0 && I4 != 0 && I5 != 0 && I6 != 0 && I7 != 0 && I8 != 0 && I9 != 0  )
        {
            return "Alles Richtig";
        }
        else
        {
            return "Nicht Richtig";
        }
    }
}
