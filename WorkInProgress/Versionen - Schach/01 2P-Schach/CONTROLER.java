public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;

    //    ZU ERLEDIGEN:
    //
    // Zug rückgangig
    // Schachmatt selber merken? -> in alle richtungen ziehen, an seiner position null einfuegen

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
                try { Thread.sleep((int) (20)); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}