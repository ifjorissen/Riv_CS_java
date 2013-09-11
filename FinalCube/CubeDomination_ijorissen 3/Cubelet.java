import java.awt.*;
/**
 * Write a description of class Cubelet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Cubelet
{
    // instance variables - replace the example below with your own
    public int orient;
    public int [] loc;
    public Color [] col;

    /**
     * Constructor for objects of class Cubelet
     */
    public Cubelet()
    {   
    }

    public abstract void setOrient(int x);

    public abstract void setColor(Color a);

    //returns a number that determines orientation
    public abstract boolean isOrient(); 

    //returns color[] of cubelet
    public abstract Color[] getColor(); 

    public abstract void setLocation(double x, double y, double z);

    //returns an int[] of the 3d coodrinates (x,y,z)
    public abstract double[] getLocation(); 

    public String toString ()
    {
        for (int i = 0; i< col.length; i++)
            System.out.print("Colors: " + col[i] + 
            " " + "Location: " + loc[i]);
        return "";
    }

}
