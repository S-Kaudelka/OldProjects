public class CONTROLLER implements Runnable
{
    public SPIEL spiel;

    private boolean IsRunning=true;
    
    private int Size;

    private boolean ModeIsInput;

    private int counter;            //wait time between pressing two fields
    private int counterSequence;    //wait time between showing two fields
    
    private int ShowSeqCount;       //Used to show the sequence

    private boolean Wait = true;
    
    private boolean change;
    private boolean reset;

    private Sequence S;

    //    ZU ERLEDIGEN:
    //
    //    Sequence einbinden -> hier im CONTROLLER unterscheiden zwischen beiden Modi

    public CONTROLLER(int Size_p)
    {
        Size = Size_p;
        spiel = new SPIEL(Size);
        S = new Sequence(Size);
        new Thread(this).start();
    }

    public void run()
    {
        while(IsRunning)
        {
            if(ModeIsInput) //Player is repeating the sequence
            {
                if(spiel.Marked)
                {
                    if((spiel.X_Marked != S.In_X[spiel.count-1] ||
                       spiel.Y_Marked != S.In_Y[spiel.count-1]) && !change)
                    {
                        int X = spiel.Felder[spiel.Y_Marked][spiel.X_Marked].LeseX();
                        int Y = spiel.Felder[spiel.Y_Marked][spiel.X_Marked].LeseY();
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeX(-1000);
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked] = new BILD("FeldW.png");
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeX(X);
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeY(Y);
                        
                        change = true;
                    }
                    
                    counter++;
                    if(counter == 12)
                    {
                        counter = 0;
                        spiel.Marked = false;
                        int X = spiel.Felder[spiel.Y_Marked][spiel.X_Marked].LeseX();
                        int Y = spiel.Felder[spiel.Y_Marked][spiel.X_Marked].LeseY();
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeX(-1000);
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked] = new BILD("Feld.png");
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeX(X);
                        spiel.Felder[spiel.Y_Marked][spiel.X_Marked].SetzeY(Y);
                        
                        if(change)
                        {
                            spiel.count = S.CInd;
                            change = false;
                            reset = true;
                        }
                    }
                }
                
                if(spiel.count == S.CInd && counter == 0)
                {
                    ModeIsInput = false;
                    S.CInd++;
                    spiel.count = 0;
                    if(reset)
                    {
                        S = new Sequence(Size);
                        reset = false;
                    }
                }
                else
                {
                    spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                }
            }
            else            //Sequence is shown
            {
                counterSequence++;
                if(counterSequence == 20 && !Wait) //Showing a part has ended
                {
                    counterSequence = 0;
                    Wait = true;

                    int X = spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].LeseX();
                    int Y = spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].LeseY();
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeX(-1000);
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]] = new BILD("Feld.png");
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeX(X);
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeY(Y);
                    
                    ShowSeqCount++;
                    if(ShowSeqCount == S.CInd)
                    {
                        ModeIsInput = !ModeIsInput;
                        ShowSeqCount = 0;
                    }
                }

                if(counterSequence == 10 && Wait) //waiting between two parts has ended
                {
                    counterSequence = 0;
                    Wait = false;
                    
                    int X = spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].LeseX();
                    int Y = spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].LeseY();
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeX(-1000);
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]] = new BILD("FeldM.png");
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeX(X);
                    spiel.Felder[S.In_Y[ShowSeqCount]][S.In_X[ShowSeqCount]].SetzeY(Y);
                }
            }
            try { Thread.sleep((int) (20)); } catch (Exception e) {}
        }
    }

    public void stop()
    {
        IsRunning = false;
    }
}