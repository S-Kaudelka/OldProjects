public class CONTROLER implements Runnable
{
    public SPIEL spiel;
    
    private BILD line;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;

    //    ZU ERLEDIGEN:
    //
    // Es kann vorkommen, dass von einer art keines vorhanden ist

    public CONTROLER()
    {
        spiel = new SPIEL();
        line = new BILD("line.png");
        line.SetzeBreite(230);
        line.SetzeHoehe(6);
        line.SetzeY(232);
        new Thread(this).start();
    }
    
    public static void main(String[] args)
    {
        new CONTROLER();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(GAMEWINDOW.getInstance().New_Game)
            {
                spiel.ClearField();
                spiel = new SPIEL();
                GAMEWINDOW.getInstance().New_Game = false;
            }
            if(AmLaufen==true)
            {
                spiel.RUN();
                if(spiel.ende)
                {
                    stop();
                }
                try { Thread.sleep((int) (20)); } catch (Exception e) {}
            }
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}