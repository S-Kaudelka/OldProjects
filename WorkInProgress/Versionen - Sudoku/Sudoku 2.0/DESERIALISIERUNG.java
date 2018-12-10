import java.io.*;
 
public class DESERIALISIERUNG
{
    public static int anzObjekte = 82;
    
    public static int[] main(int[] args)
    {
        int[] Argumente = new int[82];
            
        int anz = 0;
        try
        {
            FileInputStream inputStream = new FileInputStream("data.ser");
            
            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            
            
            
            for(int i = 0; i < anzObjekte;i++)
            {
                String convert = objectInput.readObject() + " ";
                Argumente[anz] = TzZ(convert);
                anz++;
            }
            
            return Argumente;
            
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return Argumente;
    }
    
    private static int TzZ(String Text) //Text zu Zahl // todo geht nicht
    {
        if(Text.equals("0"))
        {
            return 0;
        }
        if(Text.equals("1"))
        {
            return 1;
        }
        if(Text.equals("2"))
        {
            return 2;
        }
        if(Text.equals("3"))
        {
            return 3;
        }
        if(Text.equals("4"))
        {
            return 4;
        }
        if(Text.equals("5"))
        {
            return 5;
        }
        if(Text.equals("6"))
        {
            return 6;
        }
        if(Text.equals("7"))
        {
            return 7;
        }
        if(Text.equals("8"))
        {
            return 8;
        }
        if(Text.equals("9"))
        {
            return 9;
        }
        return 0;
    }
}