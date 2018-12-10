public class TEST
{
    public String[] Namen = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14",
            "15","16","17","18","19","20","21","22","23","24",
            "25","26","27","28","29","30","g","h","i","j","k","l","m","n",
            "o","p","q","r","s","t","u","v","w","x"};
    public int AnzahlTischwechsel = 3;

    //                  Ab hier nichts veraendern

    public ERSTELLEN e;
    TISCH[][] t;
    public boolean NeuMachen;
    public int anzNeu;
    public int anzVoll;
    public int anzWenig;
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
            System.out.print('\u000C');
            anzNeu++;
            NeuListen();
        }
    }

    private void AusgabeBelegungslisten()
    {
        for(int i = 0; i<e.anzWechsel+1; i++)
        {
            for(int j = 0; j<e.anzTische; j++)
            {
                System.out.println("Tisch "+(j+1));
                System.out.println("Spieler 1: "+t[i][j].AmTisch[0].Name);
                System.out.println("Spieler 2: "+t[i][j].AmTisch[1].Name);
                System.out.println("Spieler 3: "+t[i][j].AmTisch[2].Name);
                System.out.println("Spieler 4: "+t[i][j].AmTisch[3].Name);
                System.out.println("");
            }
            System.out.println("====== Ende Liste "+(i+1)+" ======");
        }
    }

    public void MehrmalsGespielt()
    {
        System.out.println("");
        System.out.println("Maximale Anzahl an verschiedenen Spielern: "+(e.anzWechsel+1)*3);
        System.out.println("-----------------");
        for(int i = 0; i<e.anzSpieler; i++)
        {
            int anzM = e.Spielerliste[i].Auswerten();
            if(anzM == (e.anzWechsel+1)*3)
            {
                System.out.println(e.Spielerliste[i].Name+" hat "+anzM+" verschiedene Mitspieler !");
                anzVoll++;
            }
            else
            {
                if(anzM > (e.anzWechsel+1)*3*5/6)
                {
                    System.out.println(e.Spielerliste[i].Name+" hat "+anzM+" verschiedene Mitspieler");
                }
                else
                {
                    System.out.println(e.Spielerliste[i].Name+" hat "
                                       +anzM+" verschiedene Mitspieler -");
                    anzWenig++;
                    if(anzM<(e.anzWechsel+1)*3*4/6)
                    {
                        NeuMachen = true;
                        return;
                    }
                }
            }
        }
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("Anzahl Spieler: "+Namen.length);
        System.out.println("");
        System.out.println("Anzahl an Spielern mit jedes mal neuen Spielern: "+anzVoll);
        System.out.println("Anzahl an Spielern mit relativ wenig neuen Spielern: "+anzWenig);
    }
}