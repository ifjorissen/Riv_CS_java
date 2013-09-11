import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class CubeWorld_raster extends World
{
    //RASTER DATA
    Cubelet_O [] stack = new Cubelet_O[256];   //max # of obects.  Is arbitrary, can be
    int top=0,next;                     //any number sufficiently large to hold
    double distance,a,b,l;              //all your objects
    double[] dist = new double[1024];   //same with max # of polygons
    int[][][] points = new int[1024][4][4];
    Color[] colors = new Color[1024];

    Cube rubik = new Cube();
    Cubelet [] [] [] rubikCube = rubik.getCubeArray();

    //CAMERA DATA
    double camera=0;
    double x=0,y=0,z=-9;

    //OBJECT DATA
    double[] t1 = {1,1,1,-1,-1,-1,-1,1};    //X coordinates of all points
    double[] t2 = {1,-1,-1,-1,-1,1,1,1};    //Y coordinates of all points
    double[] t3 = {1,1,-1,-1,1,1,-1,-1};    //Z coordinates of all points
    int[][] t4 = {
            {1,2,3,4}, //right face    
            {1,4,5,0}, //back   
            {1,0,7,2}, //top face
            {4,3,6,5}, //down face
            {6,7,0,5}, //left face
            {2,3,6,7}  //front
        };

    Color[] c ={
            Color.green,     //colors for cooresponding polygons
            Color.red,    //could be expanded to includeborder colors
            Color.black,
            Color.yellow,
            Color.blue,
            Color.pink,
        }; //orange is too close to pink
    Cubelet_O test = new Cubelet_O(t1,t2,t3,t4,c);

    Cubelet_O [][][] magic = new Cubelet_O [3][3][3];

    Slider slideX = new Slider();
    Slider slideY = new Slider();

    //ACT COUNTS FOR SLIDER
    int countX = 0;
    int countY = 0;

    public CubeWorld_raster()
    {  
        super(600,600,1);
        addObject(new FPS(),54,8);
        CubeiePop();
        Cubelet_O temp;
        test.setLocation(1,1,1);

        addObject(slideX, 100, 590);
        addObject(slideY, 100, 560);
        for (int i = 0; i<magic.length; i++)
        {
            for (int j = 0; j<magic[0].length; j++)
            {
                for (int k = 0; k<magic[0][0].length; k++)
                {
                    temp = magic[i][j][k];
                    addToRaster(temp);
                    magic[i][j][k].setLocation(temp.getX(),temp.getY(),temp.getZ());
                }
            }
        }
    }

    public void CubeiePop() //populate with cubelets
    {
        for (int i = 0; i<rubikCube.length; i++)
        {
            for (int j = 0; j<rubikCube[0].length; j++)
            {
                for (int k = 0; k<rubikCube[0][0].length; k++)         
                {
                    /**
                     * set the visual cubelet to have the same
                     *information as the cublet in the data cube
                     */
                    magic[i][j][k] = new Cubelet_O(t1,t2,t3,t4,c);
                    magic[i][j][k].setCubelet(rubikCube[i][j][k]);
                }
            }
        }

    }

    public void act(){
        display();

        //WARNING: THE FOLLOWING CODE IS WRONG HOWEVER, IT PRODUCES AN AWESOME(!) EFFECT TRY IT!

        if (slideX.getValue() != 0)
        {
            while (slideX.getValue()>countX)
            {
                for (int i = 0; i<magic.length; i++)
                {
                    for (int j = 0; j<magic[0].length; j++)
                    {
                        for (int k = 0; k<magic[0][0].length; k++)         
                        {
                            magic[i][j][k].rotate(.01,0);
                            //countX++;
                        }
                    }
                }
            }
        }
        if (slideY.getValue() != 0)
        {
            while (slideY.getValue()>countY)
            {
                for (int i = 0; i<magic.length; i++)
                {
                    for (int j = 0; j<magic[0].length; j++)
                    {
                        for (int k = 0; k<magic[0][0].length; k++)         
                        {
                            magic[i][j][k].rotate(0,.01);
                            //countY++;
                        }
                    }
                }
            }
        }

        //         if (slideX.getValue() != 0)
        //         {
        //             while (slideX.getValue()>countX)
        //             {
        //                 for (int i = 0; i<magic.length; i++)
        //                 {
        //                     for (int j = 0; j<magic[0].length; j++)
        //                     {
        //                         for (int k = 0; k<magic[0][0].length; k++)         
        //                         {
        // 
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //         if (slideY.getValue() != 0)
        //         {
        //             while (slideY.getValue()>countY)
        //             {
        //                 for (int i = 0; i<magic.length; i++)
        //                 {
        //                     for (int j = 0; j<magic[0].length; j++)
        //                     {
        //                         for (int k = 0; k<magic[0][0].length; k++)         
        //                         {
        // 
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        move();
    }

    public void move()
    {

    }

    public void display() 
    {
        //BASIC CAMERA MOVEMENT
        if(Greenfoot.isKeyDown("left")) //camera rotation could be added with 
            x=x-.1;                     //modification to the Object class
        if(Greenfoot.isKeyDown("up"))
            y=y-.1;
        if(Greenfoot.isKeyDown("down"))
            y=y+.1;
        if(Greenfoot.isKeyDown("right"))
            x=x+.1;

        int dim=0;        
        getBackground().setColor(Color.white);
        getBackground().fillRect(0,0,600,600);

        for(int s=0;s<top;s++)
        {         //z-buffer.  Note that it merely uses z-sorting
            Cubelet_O now = stack[s];      //so z-buffering will not work for all objects
            now.getPoints(x,y,z);
            for(int i=0;i<now.numPoly();i++)
            {
                points[dim] = now.getPolygon(i);
                if(now.renderable(i))
                {        //only adds renderable obects to z-buffer
                    dist[dim] = now.distance(i);
                    colors[dim] = now.getColor(i);
                    dim++;
                }
            }
        }

        distance = 1000000000; 
        for(int f = 0;f<dim;f++)
        {
            a = distance + .1;
            distance = 0;  

            for(int g = 0;g<dim;g++)
            {            //finds next farthest object to render
                l = dist[g];         
                if( l < a  && l > distance) 
                {
                    distance = l;
                    b = g;
                }        
            }
            next = (int)b;
            dist[next] = 10000000;
            getBackground().setColor(colors[next]);
            getBackground().fillPolygon(points[next][0],points[next][1],points[next][0].length);
            getBackground().setColor(Color.white);
            getBackground().drawPolygon(points[next][0],points[next][1],points[next][0].length);
        }
        getBackground().setColor(Color.cyan);
        getBackground().drawString("Polygons:"+dim,3, 32);
    }

    //OBECTS NOT IN THE RENDER STACK CAN STILL BE ACTIVE, BUT WILL NOT BE DRAWN
    public void addToRaster(Cubelet_O s)
    {  //adds objects to the render stack
        stack[top] = s;                 //note that you CAN add an obect twice, and this
        top++;                          //will result in it being drawn twice
        System.out.println(s + " added to slot " + top);
    }

    public void removeFromRaster(Cubelet_O s)
    { //removes ALL instances of an objects 
        int i=0;                       //from the render stack
        System.out.println("Ship " + s + " removed from raster");
        for(int f=0;f<top;f++){
            if(stack[f]!=s){
                stack[i] = stack[f];
                i++;
            }  
        }
        top--;
    }

}
