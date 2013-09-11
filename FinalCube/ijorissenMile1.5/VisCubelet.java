import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class VisCubelet here.
 * Much of this code has been adapted from BubbleBoy's rasterizer project
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VisCubelet  extends Actor
{
    //POINT DATA
    double[] xPoint;
    double[] yPoint;
    double[] zPoint;
    int[][] shapes;
    int[][] points;
    double[] distance;
    Color[] colors;

    //TEMPORARY VARIABLES
    double xTemp1;
    double yTemp1;
    double zTemp1;
    
        //CAMERA DATA
    double camera=0;
    double xCam=0,yCam=0,zCam=-9;

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
    Cubelet cube;
    //OBJECT DATA
    double X=0,Y=0,Z=0,xa=0,ya=0;  //default location set to 0,0,0
    //default rotation set to 0,0
    /**
     * Act - do whatever the VisCubelet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public VisCubelet(Cubelet xish)
    {
        cube = xish;
        points = new int[2][xPoint.length];
        distance = new double[xPoint.length];
        colors = xish.getColor();

    }

    public int [][] getPoints(double dx, double dy, double dz){     //calculates the 2D coordinates
        double a1 = Math.cos(ya);                               //of all the points
        double a2 = Math.sin(ya);
        double a3 = Math.cos(xa);
        double a4 = Math.sin(xa);                       //accounts for object rotation/location
        for(int i=0;i<xPoint.length;i++){                    //as well as camera location
            xTemp1 = xPoint[i]*a1 - zPoint[i]*a2;                 //camera rotation could be added with a modification
            zTemp1 = zPoint[i]*a1 + xPoint[i]*a2;                 //of this getPoints method

            yTemp1 = yPoint[i]*a3 - zTemp1*a4;
            zTemp1 = zTemp1*a3 + yPoint[i]*a4;

            xTemp1 = xTemp1 + X - dx;
            yTemp1 = yTemp1 + Y - dy;
            zTemp1 = zTemp1 + Z - dz;

            distance[i] = Math.pow(xTemp1,2)+Math.pow(yTemp1,2)+Math.pow(zTemp1+9,2);       //calculates the 'distance' 
            //to each point to be used int
            points[0][i] = 200+(int)(350*Math.atan2(xTemp1,zTemp1));                        //the z-buffer
            points[1][i] = 200+(int)(350*Math.atan2(yTemp1,zTemp1));
        }
        return points;
    }

    public int [] getLocationData()
    {
        return cube.getLocation();
    }

    public Color [] getColor()
    {
        return cube.getColor();
    }

    public boolean renderable(int num){ //determines whether or not a polygon is off of the screen
        int tr1 = shapes[num][0];       //used to simplify rendering
        int tr2 = shapes[num][1];
        int tr3 = shapes[num][2];
        if(shapes[num].length==4){
            int tr4 = shapes[num][3];
            int x1=points[0][tr1],x2=points[0][tr2],x3=points[0][tr3],x4=points[0][tr4];
            int y1=points[1][tr1],y2=points[1][tr2],y3=points[1][tr3],y4=points[1][tr4];              
            return !((x1<0 && x2<0 && x3<0 && x4<0) || (y1<0 && y2<0 && y3<0 && y4<0) || (x1>400 && x2>400 && x3>400 && x4>400) || (y1>400 && y2>400 && y3>400 && y4>400));
        }else{
            int x1=points[0][tr1],x2=points[0][tr2],x3=points[0][tr3];
            int y1=points[1][tr1],y2=points[1][tr2],y3=points[1][tr3];              
            return !((x1<0 && x2<0 && x3<0) || (y1<0 && y2<0 && y3<0) || (x1>400 && x2>400 && x3>400) || (y1>400 && y2>400 && y3>400));
        }
    }

    public void move(double a, double b, double c)
    {         //moves the object
        X=X+a;                                              //a certain amount in
        Y=Y+b;                                              //3D space
        Z=Z+c;
    }

    public void setRotation(double a, double b)
    {     
        //sets the rotation
        xa=a;                                               //of the obect in
        ya=b;                                               //3D space
    }

    public void rotate(double a, double b){                 //rotates the object
        xa=xa+a;                                            //a certain amount
        ya=ya+b;                                            //in 3D space
    }

    public double[] getLoc()
    {          //returns the location of this object
        double[] pos = {X,Y,Z};
        return pos;
    }

    public double[] getRot()
    {          //returns the rotation of this object
        double[] rot = {xa,ya};
        return rot;
    }

    public int getType() //returns the type of the cubelet 
    {
        if (cube.getColor().length == 3) return 3;
        else if (cube.getColor().length == 2) return 2;
        else if (cube.getColor().length == 1) return 1;
        else return 0; //no cube with no colors exists--> except the middle cube
    }
}