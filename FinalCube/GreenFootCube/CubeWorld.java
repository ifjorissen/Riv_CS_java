import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CubeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeWorld  extends World
{

    /**
     * Constructor for objects of class CubeWorld.
     * 
     */
    public CubeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1); 
        Cube a = new Cube();
        convert(a);
        //addObject(a, 300,300);

        Slider slideX = new Slider();
        Slider slideY = new Slider();
        Slider slideZ = new Slider();
        addObject(slideX, 100, 590);
        addObject(slideY, 100, 560);
        addObject(slideZ, 100, 530);
    }

    public void convert(Cube dexter)
    {
        /**
        x_screen = (+(x_3D / z_3D) + half_screen_width) * half_screen_width;
        y_screen = (-(y_3D / z_3D) + half_screen_height) * half_screen_height;
         */
        int x, y, z, x2D, y2D;
        VisCubelet snazzy;
        //Convert from x, y, z system to x, y
        VisCubelet [] [] [] cubez = dexter.getVisArray();
        int [] [] xy = new int[26][26];
        for (int i = 0; i<cubez.length; i++)
        {
            for (int j = 0; j<cubez[0].length; j++)
            {
                for (int k = 0; k < cubez[0][0].length; k++)
                {
                    snazzy = cubez[i][j][k];
                    x = cubez[i][j][k].getLocation()[0];
                    y = cubez[i][j][k].getLocation()[1];
                    z = cubez[i][j][k].getLocation()[2];
                    x2D = (x/z + 300)*300;
                    y2D = -1*(y/z + 300)* 300;
                    snazzy.set2DLoc(x2D, y2D);
                    addObject(snazzy, x2D, y2D);
                }
            }
        }

    }
}
