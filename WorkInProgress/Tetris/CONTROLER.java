public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;
    
    public static int Sleep = 20;

    //    ZU ERLEDIGEN:
    //
    // drehen nur wenn platz frei

    public CONTROLER()
    {
        spiel = new SPIEL(this);
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                try { Thread.sleep(Sleep); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}