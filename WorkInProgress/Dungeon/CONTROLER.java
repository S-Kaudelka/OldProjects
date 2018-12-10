public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;

    // ZU ERLEDIGEN:
    //
    // testen ob alles funktioniert
    // KI wenn gold gefunden besser machen
    // -> Upgraden und monster abhaenig von spielerlevel machen
    // -> Geld platzieren machen -> mehr als 10
    // -> soll spieler alles Gold ausgeben koennen?
    // -> Fallen level mit balken um die mitte anzeigen?
    // -> mehr (staerkere) Monster, Fallen
    // -> staerkere Gegner
    // Fallen funktionieren nicht wenn gegner entkommt?
    
    // BILDER:
    //
    // f___.png -> Feld/Falle
    // m___.png -> Monster
    // g___.png -> Gegner

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