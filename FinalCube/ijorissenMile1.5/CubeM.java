import java.awt.*;
/**
 * Write a description of class CubeM here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CubeM  extends Cubelet 
{
    int [] loc = new int [3];
    Color [] col = {Color.cyan, Color.cyan, Color.cyan,
            Color.cyan, Color.cyan, Color.cyan,};
    int orient;
    /**
     * This cube holds all middle (center) cubelets.
     * There are 6 of them, and they determine the cube face's color
     */
    public CubeM()
    {
        setLocation(0,0,0); //setDefault location
        //setColor(a); //setdefault color
        setOrient(1); //set default orientation
    }

    public void setOrient(int x)
    {
        orient = 1; //centers are always oriented correctly
    }

    public boolean isOrient() //checks to see if peice is oriented
    {
        if (orient == 0) return false;
        else return true;
    }

    public void setColor(Color a)
    {
        if (col[0] == Color.cyan)
            col[0] = a;
    }

    public void setLocation(int x, int y, int z)
    {
        loc[0] = x; //x coordinate
        loc[1] = y; // y coordinate
        loc[2] = z; //z coordinate
    }

    public Color[] getColor ()
    {
        return col;
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
