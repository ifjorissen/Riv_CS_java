import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.lang.Math;
/**
 * Write a description of class CubeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeWorld  extends World
{
    VisCubelet [] xy = new VisCubelet [27];
    /**
     * Constructor for objects of class CubeWorld.
     * 
     */
    final int screenW = 600;
    final int screenH = 600; 
    //RASTER DATA
    VisCubelet[] stack = new VisCubelet[256];   //max # of obects.  Is arbitrary, can be
    int top=0,next;                     //any number sufficiently large to hold
    double distance,a,b,l;              //all your objects
    double[] dist = new double[1024];   //same with max # of polygons
    int[][][] points = new int[1024][4][4];
    Color[] colors = new Color[1024];

    //CAMERA DATA
    double camera=0;
    double x=0,y=0,z=-9;

    //OBJECT DATA
    double[] t1 = {1,1,1,-1,-1,-1,-1,1};    //X coordinates of all points
    double[] t2 = {1,-1,-1,-1,-1,1,1,1};    //Y coordinates of all points
    double[] t3 = {1,1,-1,-1,1,1,-1,-1};    //Z coordinates of all points
    int[][] t4 = {{1,2,3,4},    //polygons:
            {1,4,5,0},    //conects point 1,4,5,and 0 to create a square
            {1,0,7,2},
            {4,3,6,5},
            {6,7,0,5},
            {2,3,6,7}};
    Color[] c ={Color.blue,     //colors for cooresponding polygons
            Color.green,    //could be expanded to includeborder colors
            Color.red,
            Color.yellow,
            Color.white,
            Color.black,};

    public CubeWorld()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);

        Cube a = new Cube();
        //addObject(a, 300,300);
        for (int i = 0; i<xy.length;i++)
        {
            addObject(xy[i], xy[i].getX(), xy[i].getY()); //add visC to world
        }
        Slider slideX = new Slider();
        Slider slideY = new Slider();
        Slider slideZ = new Slider();
        addObject(slideX, 100, 590);
        addObject(slideY, 100, 560);
        addObject(slideZ, 100, 530);

    }

    public void display() 
    {
        //BASIC CAMERA MOVEMENT

        //make the object rotate instead of the camera . . . how to go about doing that?
        //a cube class that is the visual representation of the cubelets and cube in the current cube class
        //or, modify that class so that it, as a whole can be rotated, along with the host
        //of cubelet points that come along with it.
        if(Greenfoot.isKeyDown("left")) //camera rotation could be added with 
            x=x-.1;                     //modification to the VisCubelet class
        if(Greenfoot.isKeyDown("up"))
            y=y-.1;
        if(Greenfoot.isKeyDown("down"))
            y=y+.1;
        if(Greenfoot.isKeyDown("right"))
            x=x+.1;

        int dim=0;        
        getBackground().setColor(Color.white);
        getBackground().fillRect(0,0,400,400);

        for(int s=0;s<top;s++)
        {         //z-buffer.  Note that it merely uses z-sorting
            VisCubelet now = stack[s];      //so z-buffering will not work for all objects
            now.getPoints(x,y,z);
            for(int i=0;i<now.numPoly();i++)
            {
                points[dim] = now.getPolygon(i);
                if(now.renderable(i)){        //only adds renderable obects to z-buffer
                    dist[dim] = now.distance(i);
                    colors[dim] = now.getColor(i);
                    dim++;
                }
            }
        }

        distance = 1000000000; 
        for(int f = 0;f<dim;f++){
            a = distance + .1;
            distance = 0;  

            for(int g = 0;g<dim;g++){            //finds next farthest object to render
                l = dist[g];         
                if( l < a  && l > distance) {
                    distance = l;
                    b = g;
                }        
            }
            next = (int)b;
            dist[next] = 10000000;
            getBackground().setColor(colors[next]);
            getBackground().fillPolygon(points[next][0],points[next][1],points[next][0].length);
            getBackground().setColor(Color.black);
            getBackground().drawPolygon(points[next][0],points[next][1],points[next][0].length);
        }
        getBackground().setColor(Color.red);
        getBackground().drawString("Polygons:"+dim,3,32);
    }

}

