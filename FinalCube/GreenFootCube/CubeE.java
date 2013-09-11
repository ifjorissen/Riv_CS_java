import java.awt.*;
/**
 * Write a description of class CubeE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeE  extends Cubelet 
{
    int [] loc = new int [3];
    Color [] col = new Color [2];
    int orient;
    /**
     * Constructor for objects of class CubeE
     */
    public CubeE()
    {
        setLocation(0,0,0); //setDefault location
        //setColor(a); //setdefault color
        //setColor(b);
        setOrient(1); //set default orientation
    }

    public void setOrient(int x)
    {
        orient = x;
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
    }

    public Color [] getColor()
    {
        return col;
    }

    public void setLocation(int x, int y, int z)
    {
        loc[0] = x; //x coordinate
        loc[1] = y; // y coordinate
        loc[2] = z; //z coordinate
    }

    public int [] getLocation()
    {
        return loc;
    }

    public String toString()
    {
        super.toString();
        return "";
    }
}

