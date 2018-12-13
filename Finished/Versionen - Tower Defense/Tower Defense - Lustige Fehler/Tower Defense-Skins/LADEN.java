import java.io.*;
 
public class LADEN
{
    public static String[] main()
    {
        String[] Argumente;
            
        int anz;
        try //todo
        {
            FileInputStream inputStream = new FileInputStream("data.ser");
            
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            
            String anzObjVariabl = (String) objectInput.readObject(); //Erster gespeicherter Wert
                                                                      //gibt an, wieviele Tuerme
            int anzObjekte = TzZ(anzObjVariabl) + 12;                 //gespeichert sind
            
            Argumente = new String[anzObjekte];
            
            for(anz = 0; anz < Argumente.length;anz++)
            {
                Argumente[anz] = (String) objectInput.readObject();
            }
            
            return Argumente;
            
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private static int TzZ(String Text) //Text zu Zahl
    {
        for(int i = 0; i < 1500; i++)
        {
            if(Text.equals(i + ""))
            {
                return i;
            }
        }
        return 0;
    }
}