import java.awt.*;
/**
 * Write a description of class CubeC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeC  extends Cubelet 
{
    int [] loc = new int [3];
    Color [] color = new Color [3];
    int orient;
    /**
     * Constructor for objects of class CubeC
     */
    public CubeC()
    {
        setLocation(0,0,0); //setDefault location
       //setColor(a); //setdefault color
       // setColor(b);
       // setColor(c);
        setOrient(1); //set default orientation
    }

    public void setOrient(int x)
    {   
        orient = x; //middles are always oriented correctly
    }

    public boolean isOrient() //checks to see if peice is oriented
    {
        if (orient == 0) return false;
        else return true;
    }

    public void setColor(Color a)
    {
        if (col[0] == null)
            col[0] = a;
        else if (col [1] == null)
            col[1] = a;
        else if (col [2] == null)
            col[2] = a;
    }

    public Color [] getColor()
    {
        return color;
    }

    public void setLocation(int x, int y, int z)
    {
        loc[0] = x; //x coordinate
        loc[1] = y; // y coordinate
        loc[2] = z; //z coordinate
    }

    public int[] getLocation()
    {
        return loc;
    }

    public String toString()
    {
        super.toString();
        return "";
    }
}
