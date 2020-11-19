public class FRAGE
{
    //Frage
    public String Fragestellung;
    public String[] Antworten;

    //Sonstiges
    public int AnzFragen;
    public int Nummer;

    public FRAGE(int[] nF)
    {
        AnzFragen = NeuFrage(-1);
        if(nF == null)
        {
            Nummer = NeuFrage((int) (Math.random()*AnzFragen+1))-1;
        }
        else
        {
            int z = (int) (Math.random()*AnzFragen+1);
            while(zahlVorhanden(nF, z))
            {
                z = (int) (Math.random()*AnzFragen+1);
            }
            Nummer = NeuFrage(z)-1;
        }
    }

    public boolean zahlVorhanden(int[] nF, int a)
    {
        for(int i = 0; i<nF.length; i++)
        {
            if(nF[i]+1 == a)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Hier werden alle Fragen eingetragen. Die Erste Antwort MUSS die Richtige sein
     * 
     * Es wird eine Zufaellige Frage ausgewaelt ueber den Parameter
     */
    public int NeuFrage(int z)
    {
        int i = -1;
        //Kopiervorlage
        i++;
        if(z == i)
        {
            Fragestellung = "";
            Antworten = new String[4];
            Antworten[0] = "";
            Antworten[1] = "";
            Antworten[2] = "";
            Antworten[3] = "";
            return i;
        }
        //Ende Vorlage
        //Anfang Fragen
        if(z == i)
        {
            Fragestellung = "Wie wird der Spieler in Minecraft genannt?";
            Antworten = new String[4];
            Antworten[0] = "Steve";
            Antworten[1] = "Peter";
            Antworten[2] = "Notch";
            Antworten[3] = "Markus";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wie heisst der Endgegner in 'The Legend of Zelda - The Minish Cap'";
            Antworten = new String[4];
            Antworten[0] = "Vaati";
            Antworten[1] = "Ganondorf";
            Antworten[2] = "Marardo";
            Antworten[3] = "Bellamu";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wie heisst die Hauptperson in Assassin’s Creed IV - Black Flag?";
            Antworten = new String[4];
            Antworten[0] = "Edward Kenway";
            Antworten[1] = "Connor Kenway";
            Antworten[2] = "Haytham Kenway";
            Antworten[3] = "Ratonhnhaké:ton";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wie bezeichnet Altair sich selber?";
            Antworten = new String[4];
            Antworten[0] = "Der Adler";
            Antworten[1] = "Der Vogel";
            Antworten[2] = "Der Falke";
            Antworten[3] = "Der Schatten";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wer waere der Koenig/die Koenigin von Dalmasca während Final Fantasy 12?";
            Antworten = new String[4];
            Antworten[0] = "Ashe";
            Antworten[1] = "Rasler";
            Antworten[2] = "Vayne";
            Antworten[3] = "Amalia";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wie heisst der Master Chief mit normalem Namen?";
            Antworten = new String[4];
            Antworten[0] = "John";
            Antworten[1] = "Jun";
            Antworten[2] = "Jorge";
            Antworten[3] = "Carter";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Nach welcher Esper wurde die Luftfeste in Final Fantasy 12 benannt?";
            Antworten = new String[4];
            Antworten[0] = "Bahamut";
            Antworten[1] = "Ifrit";
            Antworten[2] = "Shiva";
            Antworten[3] = "Leviathan";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wer gehoert in Final Fantasy 13 nicht zur Gruppe?";
            Antworten = new String[4];
            Antworten[0] = "Rygdea";
            Antworten[1] = "Sazh";
            Antworten[2] = "Snow";
            Antworten[3] = "Fang";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wie heisst der Antagonist in Final Fantasy 13-2?";
            Antworten = new String[4];
            Antworten[0] = "Caius Ballad";
            Antworten[1] = "Jul";
            Antworten[2] = "Etro";
            Antworten[3] = "Fal'Cie Eden";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wer ist der Endgegner in Skyrim?";
            Antworten = new String[4];
            Antworten[0] = "Alduin";
            Antworten[1] = "Balgruuf";
            Antworten[2] = "Mehrunes Dagon";
            Antworten[3] = "Ulfric Sturmmantel";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "In welchem Assassin’s Creed begegnet man Yusuf Tazim";
            Antworten = new String[4];
            Antworten[0] = "Assassin’s Creed: Revelations";
            Antworten[1] = "Assassin’s Creed";
            Antworten[2] = "Assassin’s Creed 2";
            Antworten[3] = "Assassin’s Creed IV: Black Flag";
            return i;
        }
        i++;
        if(z == i)
        {
            Fragestellung = "Wer ist der Endgegner in Dragon Quest 9?";
            Antworten = new String[4];
            Antworten[0] = "Corvus";
            Antworten[1] = "Mortamor";
            Antworten[2] = "Baramos";
            Antworten[3] = "Koenig Gottfried";
            return i;
        }
        //Ende Methode
        return i;
    }
}