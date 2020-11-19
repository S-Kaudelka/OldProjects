public class CalculatorOrient
{
    private double ConsNo;
    private double ConsEn;
    
    /*
     * int No: number Nomads
     * int En: number Envoys
     */
    public double CalculateConsumption(int No, int En, int ConNum)
    {
        SetConsumption(ConNum);
        
        double Consumption = 0;
        
        Consumption = No*ConsNo/100 + En*ConsEn/100;
        
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
    
    private void SetConsumption(int ConNum)
    {
        if(ConNum == 1)
        {
            ConsNo = 0.666;
            ConsEn = 0.5;
        }
        if(ConNum == 2)
        {
            ConsNo = 0.344;
            ConsEn = 0.225;
        }
        if(ConNum == 3)
        {
            ConsNo = 0.165;
            ConsEn = 0.1;
        }
        if(ConNum == 4)
        {
            ConsNo = 0;
            ConsEn = 0.1;
        }
        if(ConNum == 5)
        {
            ConsNo = 0;
            ConsEn = 0.133;
        }
        if(ConNum == 6)
        {
            ConsNo = 0;
            ConsEn = 0.08;
        }
        if(ConNum == 7)
        {
            ConsNo = 0;
            ConsEn = 0.163;
        }
    }
    
    private double ConGoodProduction(int ConNum)
    {
        if(ConNum == 1)
        {
            return 3;
        }
        if(ConNum == 2)
        {
            return 1.5;
        }
        if(ConNum == 3)
        {
            return 1.5;
        }
        if(ConNum == 4)
        {
            return 1;
        }
        if(ConNum == 5)
        {
            return 1;
        }
        if(ConNum == 6)
        {
            return 1;
        }
        if(ConNum == 7)
        {
            return 4;
        }
        
        return -1;
    }
}