public class CONTROLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;

    public boolean AmLaufen=true;

    private String Dateiname; //F¸r Speichern/Laden

    //    ZU ERLEDIGEN:
    //
    // Zug r¸ckgaengig (?)
    // Schachmatt selber merken? -> in alle richtungen ziehen, an seiner position null einfuegen
    // Koenig kann nicht schmeiﬂen -> danach im schach von der zu schmeiﬂenden figur
    // Array out of bounds nach umwandeln bei speichern
    // Figur nicht bewegen, wenn der eigene koenig danach im schach waere

    public CONTROLER(String Name)
    {
        Dateiname = Name;
        spiel = new SPIEL(this);
        Laden();
        spiel.Ausgabe();
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

    public void Speichern()
    {
        DE_SERIALISIERUNG.Speichern(this, Dateiname);
    }

    public boolean Laden()
    {
        return DE_SERIALISIERUNG.Laden(this, Dateiname);
    }
}