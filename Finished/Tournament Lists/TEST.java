public class TEST
{
    private String Out = "";
    
    public String[] Namen = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14",
            "15","16","17","18","19","20","21","22","23","24",
            "25","26","27","28","29","30","31","32","33","34","35","36","37","38",
            "39","40","41","42","43","44","45","46","47","48"};
    public int AnzahlTischwechsel = 3;

    //                  Ab hier nichts veraendern

    public ERSTELLEN e;
    TISCH[][] t;
    public boolean NeuMachen;
    public int anzNeu;
    public int anzVoll;
    public int anzWenig;
    public TEST(String[] Names_p)
    {
        Namen = Names_p;
        e = new ERSTELLEN(Namen.length, AnzahlTischwechsel, Namen);
        NeuListen();
    }
    
    public TEST()
    {
        e = new ERSTELLEN(Namen.length, AnzahlTischwechsel, Namen);
        NeuListen();
    }

    public void NeuListen()
    {
        anzVoll = 0;
        anzWenig = 0;
        e.Neue_Listen_Erstellen();
        t = new TISCH[e.anzWechsel+1][e.anzTische];
        t = e.BelegungListen;
        AusgabeBelegungslisten();
        MehrmalsGespielt();
        if(NeuMachen && anzNeu < 20)
        {
            NeuMachen = false;
            Out = "";
            anzNeu++;
            NeuListen();
        }
        else
        {
            Output.WriteInFile(Out);
        }
    }

    private void AusgabeBelegungslisten()
    {
        for(int i = 0; i<e.anzWechsel+1; i++)
        {
            for(int j = 0; j<e.anzTische; j++)
            {
                Out = Out + "Group "+(j+1)+'\n';
                Out = Out + "Player 1: "+t[i][j].AmTisch[0].Name+'\n';
                Out = Out + "Player 2: "+t[i][j].AmTisch[1].Name+'\n';
                Out = Out + "Player 3: "+t[i][j].AmTisch[2].Name+'\n';
                Out = Out + "Player 4: "+t[i][j].AmTisch[3].Name+'\n';
                Out = Out + '\n';
            }
            Out = Out + "====== End of List "+(i+1)+" ======"+'\n';
        }
    }

    public void MehrmalsGespielt()
    {
        Out = Out + '\n';
        Out = Out + "Maximimum number of different players someone could have: "+(e.anzWechsel+1)*3+'\n';
        Out = Out + "-----------------"+'\n';
        for(int i = 0; i<e.anzSpieler; i++)
        {
            int anzM = e.Spielerliste[i].Auswerten();
            if(anzM == (e.anzWechsel+1)*3)
            {
                Out = Out + e.Spielerliste[i].Name+" plays with "+anzM+" different players +"+'\n';
                anzVoll++;
            }
            else
            {
                if(anzM > (e.anzWechsel+1)*3*5/6)
                {
                    Out = Out + e.Spielerliste[i].Name+" plays with "+anzM+" different players"+'\n';
                }
                else
                {
                    Out = Out + e.Spielerliste[i].Name+" plays with "+anzM+" different players -"+'\n';
                    anzWenig++;
                    if(anzM<(e.anzWechsel+1)*3*4/6)
                    {
                        NeuMachen = true;
                    }
                }
            }
        }
        Out = Out + ""+'\n';
        Out = Out + "-----------------"+'\n';
        Out = Out + "Number of players: "+Namen.length+'\n';
        Out = Out + ""+'\n';
        Out = Out + "Number of players with new players everytime: "+anzVoll+'\n';
        Out = Out + "Number of players with relativly few new players: "+anzWenig+'\n';
    }
}