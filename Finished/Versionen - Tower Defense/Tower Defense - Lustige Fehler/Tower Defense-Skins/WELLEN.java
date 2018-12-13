public class WELLEN
{
    public SPIEL s;
    
    // Werte, die fuer GE() benoetigt werden
    public int x;
    public int gIns;
    
    // Zusaetzliche Gegner pro Welle
    public int Wz;
    
    public WELLEN(SPIEL Spiel)
    {
        s = Spiel;
    }
    
    // Gegner Erzeugen -> Uebersichtlichkeit
    public void GE(int Von, int Bis, int Typ)
    {
        for(int i = Von; i<Bis; i++)
        {
            s.Gegner[i] = new GEGNER(Typ,s.Werte);
            x = 1500+(i*55);
            s.Gegner[i].Skin.SetzeX(x);
            s.Gegner[i].Skin.SetzeY(375);
            s.Gegner[i].Lebensbalken.SetzeX(s.Gegner[i].Skin.LeseX());
            s.Gegner[i].Lebensbalken.SetzeY(s.Gegner[i].Skin.LeseY()-7);
            s.Gegner[i].RestLebensbalken.SetzeX(s.Gegner[i].Skin.LeseX());
            s.Gegner[i].RestLebensbalken.SetzeY(s.Gegner[i].Skin.LeseY()-7);
        }
    }
    // Zum Kopieren:   GE(,,);
    
    // Liste mit allen Gegnern
    // Nummer - Gengnername
    // 0        Speertraeger
    // 1        Soldat
    // 2        Spaeher
    // 3        Schwertkaempfer
    // 4        Veteran
    // 5        Koenigswache
    // 6        Palastwache
    // 7        Hellebardier
    // 8        Elitekaempfer
    // 9        Ritter
    // 10       Gepanzerterreiter
    // 11       Paladin
    
    public void WelleSchicken(int W)
    {
        if(s.GegnerAmLeben>0)
        {
            return;
        }
        s.IstVeraendert=true;
        x=0;
        gIns=0;
        if(W==0)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[5];
            GE(0,5,0);
            s.Welle++;
            s.Wellengegner=5;
            s.GegnerAmLeben=5;
            return;
        }
        if(W==1)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[6];
            GE(0,5,0);
            GE(5,6,1);
            s.Welle++;
            s.Wellengegner=6;
            s.GegnerAmLeben=6;
            return;
        }
        if(W==2)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[8];
            GE(0,6,0);
            GE(6,8,1);
            s.Welle++;
            s.Wellengegner=8;
            s.GegnerAmLeben=8;
            return;
        }
        if(W==3)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[10];
            GE(0,1,2);
            GE(1,7,0);
            GE(7,10,1);
            s.Welle++;
            s.Wellengegner=10;
            s.GegnerAmLeben=10;
            return;
        }
        if(W==4)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[15];
            GE(0,3,2);
            GE(3,10,0);
            GE(10,15,1);
            s.Welle++;
            s.Wellengegner=15;
            s.GegnerAmLeben=15;
            return;
        }
        if(W==5)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[20];
            GE(0,3,2);
            GE(3,10,0);
            GE(10,20,1);
            s.Welle++;
            s.Wellengegner=20;
            s.GegnerAmLeben=20;
            return;
        }
        if(W==6)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[20];
            GE(0,5,2);
            GE(5,8,0);
            GE(8,20,1);
            s.Welle++;
            s.Wellengegner=20;
            s.GegnerAmLeben=20;
            return;
        }
        if(W==7)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[15];
            GE(0,5,2);
            GE(5,10,1);
            GE(10,15,3);
            s.Welle++;
            s.Wellengegner=15;
            s.GegnerAmLeben=15;
            return;
        }
        if(W==8)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[15];
            GE(0,5,2);
            GE(5,14,0);
            GE(14,15,4);
            s.Welle++;
            s.Wellengegner=15;
            s.GegnerAmLeben=15;
            return;
        }
        if(W==9)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[3];
            GE(0,1,5);
            GE(1,3,6);
            s.Welle++;
            s.Wellengegner=3;
            s.GegnerAmLeben=3;
            return;
        }
        if(W==10)
        {
            s.Gegner = null;
            s.Gegner = new GEGNER[20];
            GE(0,10,0);
            GE(10,20,7);
            s.Welle++;
            s.Wellengegner=20;
            s.GegnerAmLeben=20;
            return;
        }
        // Standardcode fuer Wellen mit "BossWell" alle 10 Wellen
        if(W < 15)
        {
            gIns = W+10+(Wz*2);
            s.Gegner = null;
            s.Gegner = new GEGNER[gIns];
            GE(0,5,2);
            GE(5,6,5);
            GE(6,8,6);
            GE(8,gIns,7);
            s.Welle++;
            s.Wellengegner=gIns;
            s.GegnerAmLeben=gIns;
            Wz++;
            return;
        }
        if(W == 15)
        {
            gIns = 30;
            s.Gegner = null;
            s.Gegner = new GEGNER[gIns];
            GE(0,5,2);
            GE(5,7,5);
            GE(7,10,6);
            GE(10,gIns-3,7);
            GE(gIns-3,gIns-2,5);
            GE(gIns-2,gIns-1,6);
            GE(gIns-1,gIns,4);
            s.Welle++;
            s.Wellengegner=gIns;
            s.GegnerAmLeben=gIns;
            Wz=0;
            return;
        }
        if(W < 25)
        {
            gIns = W+15+(Wz*2);
            s.Gegner = null;
            s.Gegner = new GEGNER[gIns];
            GE(0,5,2);
            GE(5,7,9);
            GE(7,gIns-20,7);
            GE(gIns-20,gIns-15,8);
            GE(gIns-15,gIns-10,5);
            GE(gIns-10,gIns,6);
            s.Welle++;
            s.Wellengegner=gIns;
            s.GegnerAmLeben=gIns;
            Wz++;
            return;
        }
        if(W == 25)
        {
            gIns = 50;
            s.Gegner = null;
            s.Gegner = new GEGNER[gIns];
            GE(0,2,9);
            GE(2,4,10);
            GE(4,5,11);
            GE(5,10,7);
            GE(10,20,8);
            GE(20,22,5);
            GE(22,24,6);
            GE(24,25,4);
            GE(25,35,7);
            GE(35,45,8);
            GE(45,47,5);
            GE(47,49,6);
            GE(49,50,4);
            s.Welle++;
            s.Wellengegner=gIns;
            s.GegnerAmLeben=gIns;
            Wz=0;
            return;
        }
        if(W < 35)
        {
            gIns = W+20+(Wz*2);
            s.Gegner = null;
            s.Gegner = new GEGNER[gIns];
            GE(0,5,9);
            GE(5,8,10);
            GE(8,10,11);
            GE(10,20,7);
            GE(20,gIns-15,8);
            GE(gIns-15,gIns-10,5);
            GE(gIns-10,gIns,6);
            s.Welle++;
            s.Wellengegner=gIns;
            s.GegnerAmLeben=gIns;
            Wz++;
            return;
        }
        if(W == 35)
        {
            //todo
        }
    }
}