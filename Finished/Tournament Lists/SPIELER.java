public class SPIELER
{
    public String Name;
    public String[] Mitspieler; //bisherige mitspieler
    public int anzMitspieler; //maximale anzahl mitspieler

    public SPIELER(String Name_p, int anzMitspieler_p)
    {
        Name = Name_p;
        anzMitspieler = anzMitspieler_p;
        Mitspieler = new String[anzMitspieler];
    }

    public boolean BereitsMitspieler(String n) // het er schon mit dem gespielt?
    {
        for(int i = 0; i<anzMitspieler; i++)
        {
            if(Mitspieler[i] != null)
            {
                if(Mitspieler[i].equals(n))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean MitspielerHinzufuegen(String n)
    {
        for(int i = 0; i<anzMitspieler; i++)
        {
            if(Mitspieler[i] == null)
            {
                Mitspieler[i] = n;
                return true;
            }
        }
        return false;
    }

    public int Auswerten() //gibt anz an verschiedenen spielern zurück
    {
        int a = 0;
        for(int i = 0; i<anzMitspieler; i++)
        {
            if(Mitspieler[i] == null)
            {
                return a;
            }
            if(!Doppelt(i))
            {
                a++;
            }
        }
        return a;
    }

    private boolean Doppelt(int i) //spieler kommt später nochmal vor
    {
        for(int j = i+1; j<anzMitspieler; j++)
        {
            if(Mitspieler[j] == null){return false;}
            if(Mitspieler[i].equals(Mitspieler[j]))
            {
                return true;
            }
        }
        return false;
    }
}