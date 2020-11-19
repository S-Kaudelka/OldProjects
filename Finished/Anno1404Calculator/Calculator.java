public class Calculator
{
    private double ConsPe;
    private double ConsCi;
    private double ConsPa;
    private double ConsNo;
    
    /*
     * int Pe: number Peasants
     * int Ci: number Civilians
     * int Pa: number Patricians
     * int No: number Noblemen
     */
    public double CalculateConsumption(int Pe, int Ci, int Pa, int No, int ConNum)
    {
        SetConsumption(ConNum);
        
        double Consumption = 0;
        
        Consumption = Pe*ConsPe/100 + Ci*ConsCi/100 + Pa*ConsPa/100 + No*ConsNo/100;
        
        return Consumption;
    }
    
    public int CalculateNrBuildingsNeccessary(double Consumption, int ConNum)
    {
        int NrBuildings = 0;
        
        double Prod = ConGoodProduction(ConNum);
        
        double NrB = Consumption/Prod;
        
        NrBuildings = (int) Math.ceil(NrB);
        
        return NrBuildings;
    }
    
    //Okzidental Consumption goods (includings Spices)
    private void SetConsumption(int ConNum)
    {
        if(ConNum == 1)
        {
            ConsPe = 1;
            ConsCi = 0.4;
            ConsPa = 0.22;
            ConsNo = 0.16;
        }
        if(ConNum == 2)
        {
            ConsPe = 0.44;
            ConsCi = 0.44;
            ConsPa = 0.23;
            ConsNo = 0.13;
        }
        if(ConNum == 3)
        {
            ConsPe = 0;
            ConsCi = 0.44;
            ConsPa = 0.22;
            ConsNo = 0.16;
        }
        if(ConNum == 4)
        {
            ConsPe = 0;
            ConsCi = 0.42;
            ConsPa = 0.19;
            ConsNo = 0.08;
        }
        if(ConNum == 5)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0.55;
            ConsNo = 0.39;
        }
        if(ConNum == 6)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0.24;
            ConsNo = 0.14;
        }
        if(ConNum == 7)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0.28;
            ConsNo = 0.16;
        }
        if(ConNum == 8)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0.16;
            ConsNo = 0.09;
        }
        if(ConNum == 9)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0;
            ConsNo = 0.22;
        }
        if(ConNum == 10)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0;
            ConsNo = 0.16;
        }
        if(ConNum == 11)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0;
            ConsNo = 0.2;
        }
        if(ConNum == 12)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0;
            ConsNo = 0.117;
        }
        if(ConNum == 13)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0.08;
            ConsNo = 0.06;
        }
        if(ConNum == 14)
        {
            ConsPe = 0;
            ConsCi = 0;
            ConsPa = 0;
            ConsNo = 0.142;
        }
    }
    
    //Production of Production-Chain (== 1 Building producing the ConGood at 100%)
    private double ConGoodProduction(int ConNum)
    {
        if(ConNum == 1)
        {
            return 2;
        }
        if(ConNum == 2)
        {
            return 1.5;
        }
        if(ConNum == 3)
        {
            return 2;
        }
        if(ConNum == 4)
        {
            return 2;
        }
        if(ConNum == 5)
        {
            return 4;
        }
        if(ConNum == 6)
        {
            return 1.5;
        }
        if(ConNum == 7)
        {
            return 4;
        }
        if(ConNum == 8)
        {
            return 3;
        }
        if(ConNum == 9)
        {
            return 2.5;
        }
        if(ConNum == 10)
        {
            return 2.5;
        }
        if(ConNum == 11)
        {
            return 2;
        }
        if(ConNum == 12)
        {
            return 2;
        }
        if(ConNum == 13)
        {
            return 2;
        }
        if(ConNum == 14)
        {
            return 3;
        }
        return -1;
    }
}