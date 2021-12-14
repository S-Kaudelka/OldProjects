import java.awt.Point;

public class SPIEL
{
    private int height;
    private int width;
    private Field[][] tiles;

    private int numberMines;
    private int tilesMarked;
    
    public boolean finished;

    public SPIEL()
    {
        //initialize field
        int n_x = GAMEWINDOW.Wide/25;
        int n_y = (GAMEWINDOW.High-55)/25;
        width = n_x;
        height = n_y;
        tiles = new Field[n_x][n_y];
        for(int x = 0; x < n_x; x++)
        {
            for(int y = 0; y < n_y; y++)
            {
                tiles[x][y] = new Field(x, y);
                int rng = (int) (Math.random()*100);
                if(rng < 15) //15% chance = ~1/7 => random guess for how many mines
                {
                    tiles[x][y].Mine = true;
                    numberMines++;
                }
            }
        }
        GAMEWINDOW.getInstance().setNumberMines(numberMines);
        //count number of adjacent mines
        for(int x = 0; x < n_x; x++)
        {
            for(int y = 0; y < n_y; y++)
            {
                //Diagonal
                if(x > 0 && y > 0 && tiles[x-1][y-1].Mine) //L T
                    tiles[x][y].MinensAdjacent++;
                if(x < width-1 && y > 0 && tiles[x+1][y-1].Mine) //R T
                    tiles[x][y].MinensAdjacent++;
                if(x > 0 && y < height-1 && tiles[x-1][y+1].Mine) //L B
                    tiles[x][y].MinensAdjacent++;
                if(x < width-1 && y < height-1 && tiles[x+1][y+1].Mine) //R B
                    tiles[x][y].MinensAdjacent++;
                //straight line
                if(x > 0 && tiles[x-1][y].Mine) //L
                    tiles[x][y].MinensAdjacent++;
                if(x < width-1 && tiles[x+1][y].Mine) //R
                    tiles[x][y].MinensAdjacent++;
                if(y > 0 && tiles[x][y-1].Mine) //T
                    tiles[x][y].MinensAdjacent++;
                if(y < height-1 && tiles[x][y+1].Mine) //B
                    tiles[x][y].MinensAdjacent++;
            }
        }
    }

    public void RUN(KEYSTATE keystate)
    {
        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            TestMouseClick(p);
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TestMouseClick(Point m)
    {
        if(m.y < 55) return; //clicked above the field, not inside

        //calculates the x and y index
        int x = (int)(m.x/25);
        int y = (int)((m.y-55)/25);

        if(GAMEWINDOW.Reveal_Field)
            flip_field(x, y);
        else
            mark_field(x, y);
    }

    private void flip_field(int x, int y)
    {
        if(!tiles[x][y].image.LeseName().equals("fNeu.png") || tiles[x][y].marked)
        {
            //the field has either been marked or already opened
            return;
        }
        if(tiles[x][y].Mine)
        {
            Game_Over();
            return;
        }

        //not marked, not open, no mine -> regular field
        tiles[x][y].ChangeImage("fOffen"+tiles[x][y].MinensAdjacent);
        if(tiles[x][y].MinensAdjacent == 0)
        {
            //Diagonal
            if(x > 0 && y > 0) //L T
                flip_field(x-1, y-1);
            if(x < width-1 && y > 0) //R T
                flip_field(x+1, y-1);
            if(x > 0 && y < height-1) //L B
                flip_field(x-1, y+1);
            if(x < width-1 && y < height-1) //R B
                flip_field(x+1, y+1);
            //straight line
            if(x > 0) //L
                flip_field(x-1, y);
            if(x < width-1) //R
                flip_field(x+1, y);;
            if(y > 0) //T
                flip_field(x, y-1);
            if(y < height-1) //B
                flip_field(x, y+1);
        }
    }

    private void mark_field(int x, int y)
    {
        if(tiles[x][y].image.LeseName().equals("fNeu.png"))
        {
            tiles[x][y].ChangeImage("fMarkiert");
            tiles[x][y].marked = true;
            tilesMarked++;
            GAMEWINDOW.getInstance().setNumberMarked(tilesMarked);
            if(tilesMarked >= numberMines)
            {
                //if a tile is marked but not a bomb, you don't win
                for(int x_ = 0; x < width; x++)
                {
                    for(int y_ = 0; y < height; y++)
                    {
                        if(tiles[x_][y_].marked && !tiles[x_][y_].Mine)
                        {
                            return;
                        }
                    }
                }
                //all marked tiles are bombs -> win
                Victory();
            }
            return;
        }
        if(tiles[x][y].image.LeseName().equals("fMarkiert.png"))
        {
            tiles[x][y].ChangeImage("fNeu");
            tiles[x][y].marked = false;
            tilesMarked--;
            GAMEWINDOW.getInstance().setNumberMarked(tilesMarked);
            return;
        }
    }
    
    private void Victory()
    {
        finished = true;
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                if(tiles[x][y].image.LeseName().equals("fMarkiert.png"))
                {
                    if(tiles[x][y].Mine)
                    {
                        tiles[x][y].ChangeImage("fMarkierttrue");
                    }
                    else
                    {
                        tiles[x][y].ChangeImage("fMarkiertfalse");
                    }
                }
            }
        }
    }

    private void Game_Over()
    {
        finished = true;
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                if(tiles[x][y].Mine)
                {
                    if(tiles[x][y].image.LeseName().equals("fNeu.png"))
                    {
                        tiles[x][y].ChangeImage("fMine");
                    }
                    if(tiles[x][y].image.LeseName().equals("fMarkiert.png"))
                    {
                        tiles[x][y].ChangeImage("fMarkierttrue");
                    }
                }
                else
                {
                    if(tiles[x][y].image.LeseName().equals("fMarkiert.png"))
                    {
                        tiles[x][y].ChangeImage("fMarkiertfalse");
                    }
                }
            }
        }
    }

    //
    //Ende Run
    //
}