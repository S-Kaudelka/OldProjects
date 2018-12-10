public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;

    //    ZU ERLEDIGEN:
    //

    public CONTROLER(int AnzEbenen)
    {
        if(AnzEbenen < 1 || AnzEbenen > 7)
        {
            System.out.println("Ungültige Anzahl an Schichten");
            return;
        }
        spiel = new SPIEL(this, AnzEbenen);
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                try { Thread.sleep((int) (50)); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}