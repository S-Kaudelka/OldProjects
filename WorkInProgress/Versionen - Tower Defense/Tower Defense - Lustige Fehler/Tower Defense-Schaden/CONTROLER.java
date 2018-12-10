public class CONTROLER implements Runnable
{
    SPIEL spiel = new SPIEL(this);
    
    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;
    
    //Tuerme muessen noch Angreifen
    
    public CONTROLER()
    {
        new Thread(this).start();
    }
    
    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.Action(GAMEWINDOW.getInstance().getKeystate());
                if(spiel.GegnerAmLeben > 0)
                {
                    for(int i = 0; i<spiel.Wellengegner; i++)
                    {
                        spiel.Gegner[i].Bewegen();
                    }
                }
            }
            if(Weiter(GAMEWINDOW.getInstance().getKeystate()))
            {
                AmLaufen=true;
            }
            try { Thread.sleep(20); } catch (Exception e) {}
        }
    }
    
    public void end()
    {
        IsRunning=false;
    }
    
    public boolean Weiter(KEYSTATE k)
    {
        if(k.IsPressed("L"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}