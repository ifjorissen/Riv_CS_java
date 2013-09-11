import greenfoot.*;
import java.awt.*;
/**
 * Write a description of class Cube here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cube extends Actor
{
    /*#
     * Cube array: A 3-Dimensional array of cubelets
     * How are the cubelets arranged?
     * An array based on cubelet type?
     * By layer (bottom, middle, top)?
     */
    /**
     * Locational array of cube
     */
    VisCubelet [][][] cubie;
    Cubelet [] [] [] cube = new Cubelet[3][3][3];
    Color y = Color.yellow;
    Color r = Color.red;
    Color b = Color.blue;
    Color g = Color.green;
    Color bk = Color.black;
    Color o = Color.orange;
    /**
     * Constructor for objects of class Cube
     */
    public Cube()
    {
        Cubelet[][][] cube_temp =  {
                {       
                    {new CubeC(), new CubeE(), new CubeC()},
                    {new CubeE(), new CubeM(), new CubeE()},        //ZLayer1
                    {new CubeC(), new CubeE(), new CubeC()}
                },//one face          
                {
                    {new CubeE(), new CubeM(), new CubeE()},
                    {new CubeM(), new CubeM(), new CubeM()},    //ZLayer2
                    {new CubeE(), new CubeM(), new CubeE()}
                }, //middle "face" or row
                { 
                    {new CubeC(), new CubeE(), new CubeC()},
                    {new CubeE(), new CubeM(), new CubeE()},          //ZLayer3
                    {new CubeC(), new CubeE(), new CubeC()}//back face 

                }
            };
        cube = cube_temp;
        setLocationsColors();
        Visual();
        setImage( new GreenfootImage(300, 300) );
    }

    public void addedToWorld(World world)
    {
    }

    public VisCubelet [][][] getVisArray ()
    {
        return cubie;
    }

    public void Visual()
    {
        cubie = new VisCubelet [3][3][3];
        for (int i = 0; i<cubie.length; i++)
        {
            for (int j = 0; j<cubie[0].length; j++)
            {
                for (int k = 0; k<cubie[0][0].length; k++)         
                {
                    /**
                     * set the visual cubelet to have the same
                     *information as the cublet in the data cube
                     */
                    cubie[i][j][k] = new VisCubelet(cube[i][j][k]);
                }

            }
        }
    }
    //Set new locations of cubelets based on location in array
    private void setLocationsColors()
    {
        for (int i = 0; i < cube.length; i++) //iterate through outermost aspect of array
        {
            for (int j = 0; j < cube[0].length; j++)
            {
                for (int k = 0; k< cube[0][0].length; k++) //iterate through innermost aspect of array
                {
                    cube[i][j][k].setLocation(k, j, i); //i,j,k  is z, y, x not x,y,z
                    if (cube[i][j][k] == cube[1][1][1]) break;
                    else if (i == 0)
                    {
                        cube[i][j][k].setColor(Color.yellow);
                        if (j == 0)
                            cube[i][j][k].setColor(Color.red);
                        if (j == 2)
                            cube[i][j][k].setColor(Color.orange);
                        if (k == 0)
                            cube[i][j][k].setColor(Color.blue);
                        if (k == 2)
                            cube[i][j][k].setColor(Color.green);
                    }
                    if (cube[i][j][k] == cube[1][1][1]) break;
                    else if (i == 1)
                    {
                        if (j == 0) cube[i][j][k].setColor(Color.red);
                        if (j == 1)
                        {
                            if (k == 0) cube[i][j][k].setColor(Color.blue);
                            if (k == 2) cube[i][j][k].setColor(Color.green);
                        }
                        if (j == 2) cube[i][j][k].setColor(Color.orange);
                    }
                    if (cube[i][j][k] == cube[1][1][1]) break;
                    else if (i == 2)
                    {
                        cube[i][j][k].setColor(Color.black);
                        if (j == 0) 
                            cube[i][j][k].setColor(Color.red);
                        if (j == 2)
                            cube[i][j][k].setColor(Color.orange);
                        if (k == 0)
                            cube[i][j][k].setColor(Color.blue);
                        if (k == 2)
                            cube[i][j][k].setColor(Color.green);
                    }
                }
            }
        }
    }

    //returns middle cubelet of the face being looked at

    //Moves face one move counterclockwise
    public void moveCounter(int x)
    {
        moveClock(x);
        moveClock(x);
        moveClock(x);
    }

    //Moves face one move clockwise
    public void moveClock(int x)
    {
        if (x == 1) M1();
        if (x == 2) M2();
        if (x == 3) M3();
        if (x == 4) M4();
        if (x == 5) M5();
        if (x == 6) M6();  
    }

    //moveIt would ideally move all faces; but for the sake of functionality . . .
    // see hardcoded methods below
    public void moveIt ()
    {  
        int xP, yP; //x prime, y prime
        //Ex: cube[0][0][0], at location(ARRAY LOCATION 0,0,0) (-1,-1,-1) goes to (1, -1, -1)
        for (int j = 0; j<cube[0].length; j++)
        {
            for (int k = 0; k<cube[0][0].length; k++)
            {
                if (k == 0)
                {
                    int x = cube[0][j][k].getLocation()[0];
                    int y = cube[0][j][k].getLocation()[1];
                    xP = -y; //simplifies from x' = xcos90 - ysin90
                    yP = x; //simplifies from y'=xsin90 + ycos90
                    cube[0][j][k].setLocation(xP, yP, -1);
                }
            }
        }
    }

    public void M1 () //move m1 face- hardcoded
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;

    }

    public void M2()
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;

    }

    public void M3 () //move m3 face
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;
    }

    public void M4 () //move m4 face
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;
    }

    public void M5 () //move m5 face
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;
    }

    public void M6 () //move m6 face
    {
        // fourcorners
        Cubelet temp = cube[0][0][0];
        cube[0][0][0] = cube[2][0][0];
        cube[2][0][0] = cube[2][0][2];
        cube[2][0][2] = cube[0][0][2];

        // edges
        temp = cube [0][0][1];
        cube[1][0][2] = cube [0][0][1];
        cube[2][0][1] = cube [1][0][2];
        cube[1][0][0] = cube [2][0][1];
        cube[0][0][1] = temp;
    }

    public String toString()
    {
        for (int i = 0; i<cube.length; i++)
        {
            for (int j = 0; j<cube[0].length; j++)
            {
                for (int k = 0; k<cube[0][0].length; k++)
                    System.out.println(cube[i][j][k]);
            }
        }
        return "done";
    }

    //make methods for each "face"
}

