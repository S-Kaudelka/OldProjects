public class SUDOKU
{
    private int Nummer;
    
    private int[][] S; //Spielfeld

    public SUDOKU(int Nummer_p)
    {
        S = new int[9][9];
        
        Nummer = Nummer_p;
        SudokuErstellen();
        
        SudokuAnzeigen();
    }
    
    public void Speichern()
    {
        int[] Argumente = new int[82];
        
        int anz = 0; //Anzahl an bereits gespeicherten Argumenten
        
        for(int u = 0; u < 9; u++)
        {
            for(int r = 0; r < 9; r++)
            {
                Argumente[anz] = S[u][r];
            }
        }
        
        Argumente[81] = Nummer;
        
        SERIALISIERUNG.main(Argumente);
    }
    
    public void Laden()
    {
        int[] Argumente = new int[82];
        
        Argumente = DESERIALISIERUNG.main(null);
        
        Nummer = Argumente[81];
        
        int[][] SpielFeld = new int[9][9];
        
        int u = 0; // ?????????????
        
        for(int anz = 0; anz < 81; anz++)
        {
            int r = anz - (u*9);
            SpielFeld[u][r] = Argumente[anz];
            r++;
            if(r == 9)
            {
                u++;
            }
        }
        
        S = SpielFeld;
        SudokuAnzeigen();
    }

    private void SudokuErstellen()
    {
        if (Nummer == 1) 
        {
            S[0][3]=2; S[0][6]=8; S[0][7]=1;
            S[1][2]=4; S[1][4]=9; S[1][5]=7; S[1][7]=3; S[1][8]=5;
            S[2][0]=2; S[2][2]=5; S[2][5]=1;
            S[3][0]=5; S[3][1]=7; S[3][3]=1; S[3][6]=9; S[3][8]=4;
            S[4][1]=4; S[4][4]=6; S[4][7]=2;
            S[5][0]=3; S[5][2]=6; S[5][5]=4; S[5][7]=7; S[5][8]=8;
            S[6][3]=8; S[6][6]=3; S[6][8]=7;
            S[7][0]=7; S[7][1]=6; S[7][3]=5; S[7][4]=2; S[7][6]=4;
            S[8][1]=1; S[8][2]=9; S[8][5]=3;
        }
        /*
        if (Nummer == 2)
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
        */
    }

    public String ZahlEinsetzen(int Zahl, int unten, int rechts)
    {    
        if (Zahl > 9 || Zahl < 0)
        {
            return "Ungueltige Zahl";
        }
        if (unten < 1 || unten > 9 || rechts < 1 || rechts > 9)
        {
            return "Ungueltiges Feld";
        }
        else
        {
            S[unten - 1][rechts - 1] = Zahl;
            SudokuErstellen();
            SudokuAnzeigen();
            return "Zahl Eigefuegt";
        }
    }        

    public void SudokuAnzeigen()
    {
        System.out.println("   1 2 3   4 5 6   7 8 9");
        System.out.println("  -----------------------");
        System.out.print("A|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[0][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("B|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[1][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("C|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[2][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.println("  -----------------------");
        System.out.print("D|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[3][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("E|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[4][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("F|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[5][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.println("  -----------------------");
        System.out.print("G|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[6][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("H|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[7][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
        System.out.print("I|"); for(int i = 0; i < 9; i++){
                System.out.print(" "+S[8][i]); if(i%3 == 2){System.out.print(" |");}
            } System.out.println("");
    }
    
    public String Kontrolle()
    {
        // Kontrolle von LINKS nach RECHTS
        for(int u = 0; u < 9; u++)
        {
            for(int r = 0; r < 8; r++)
            {
                if(KontrollePartReihe(u, r))
                {
                    return "Falsch";
                }
            }
        }  
        // Kontrolle von OBEN nach UNTEN
        for(int r = 0; r < 9; r++)
        {
            for(int u = 0; u < 8; u++)
            {
                if(KontrollePartReihe(r, u))
                {
                    return "Falsch";
                }
            }
        } 
        
        // 9ER-BLOCK-KONTROLLE
        
        //todo
        
        // KONTROLLE, dass keine "0" drinsteht
        for(int u = 0; u < 9; u++)
        {
            for(int r = 0; r < 9; r++)
            {
                if(S[u][r] == 0)
                {
                    return "Falsch";
                }
            }
        }  
        
        return "Alles Richtig";
    }
    
    private boolean KontrollePartReihe(int FesteReihe, int Beginn)
    {
        for(int durchlauf = Beginn+1; durchlauf < 9; durchlauf++)
        {
            if(S[FesteReihe][Beginn] == S[FesteReihe][durchlauf])
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean KontrollePartBlock(int StartU, int StartR)
    {
        for(int u = StartU; u < 3; u++)
        {
            for(int r = StartR; r < 3; r++)
            {
                if(S[StartU][StartR] == S[u][r] && u != StartU && u != StartR)
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}