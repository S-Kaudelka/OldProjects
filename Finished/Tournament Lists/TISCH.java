public class TISCH
{
    public int Nummer;
    public SPIELER[] AmTisch;
    public int anzAmT;

    public TISCH(int Nummer_p)
    {
        Nummer = Nummer_p;
        AmTisch = new SPIELER[4];
    }

    public boolean SpielerDazu(SPIELER s) //false, wenn voll; darf mehrmals mit gleichen
    {
        if(anzAmT == 4){return false;}

        AmTisch[anzAmT] = s;
        anzAmT++;
        return true;
    }

    public boolean TischTesten() // true, wenn keine mehrmals miteinander gespielt haben
    {
        for(int i = 0; i<4; i++)
        {
            for(int j = 0; j<4; j++)
            {
                if(i != j && AmTisch[j] != null)
                {
                    if(AmTisch[j].Name != null)
                    {
                        if(AmTisch[i].BereitsMitspieler(AmTisch[j].Name))
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void MitspielerVomTischHinzufuegen() //bei listen der einzelnen spieler 
    {
        for(int i = 0; i<4; i++)
        {
            for(int j = 0; j<4; j++)
            {
                if(i != j)
                {
                    AmTisch[i].MitspielerHinzufuegen(AmTisch[j].Name);
                }
            }
        }
    }
}