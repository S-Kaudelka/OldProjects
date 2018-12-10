import java.io.*;
 
public class LADEN
{
    public static String[] main(String Dateiname)
    {
        String[] Argumente = null;
        
        try //todo
        {
            FileInputStream inputStream = new FileInputStream(Dateiname+".ser");
            
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            
            int anzObj = TzZ((String) objectInput.readObject());
            
            Argumente = new String[anzObj];
            
            for(int anz = 0; anz < anzObj; anz++)
            {
                Argumente[anz] = (String) objectInput.readObject();
            }
            
            return Argumente;
            
        }catch (FileNotFoundException e)
        {
            String[] s = {"nicht vorhanden"};
            System.out.println("Eine Datei mit diesem Namen ist nicht vorhanden");
            return s;
        }
        catch (IOException e)
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