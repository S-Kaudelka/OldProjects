public class STAB
{
    public BILD Skin;
    public SCHICHT[] Stapel;
    public int max;
    
    public STAB(int max_p)
    {
        Skin = new BILD("Stab.png");
        Skin.SetzeBreite(20);
        Skin.SetzeHoehe(270);
        max = max_p;
        Stapel = new SCHICHT[max];
    }
    
    public boolean Ablegen(SCHICHT s) // gibt true zurück, wenn schicht platziert werden konnte
    {
        SCHICHT o = Oberster();
        int h = -1;
        if(o != null)
        {
            h = o.Hoehe;
        }
        if(s.Hoehe<h || h-1 == max)
        {
            return false;
        }
        Stapel[NummerOberster()+1] = s;
        return true;
    }
    
    public SCHICHT Nehmen() //gibt oberste schicht zurück und löscht diese vom stapel
    {
        SCHICHT o = Oberster();
        int no = NummerOberster();
        if(no != -1)
        {
            Stapel[no] = null;
        }
        return o;
    }
    
    public SCHICHT Oberster() //Oberste Schicht
    {
        for(int i = max-1; i>=0; i--)
        {
            if(Stapel[i] != null)
            {
                return Stapel[i];
            }
        }
        return null;
    }
    
    public int NummerOberster() //Index im Array
    {
        for(int i = max-1; i>=0; i--)
        {
            if(Stapel[i] != null)
            {
                return i;
            }
        }
        return -1;
    }
}