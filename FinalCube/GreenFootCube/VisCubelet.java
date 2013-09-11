import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class VisCubelet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VisCubelet  extends Actor
{
    /**
     * Act - do whatever the VisCubelet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int x, y; // holds the x,y location in 2D
    Cubelet cube;
    public VisCubelet(Cubelet x)
    {
        cube = x;
    }

    public int [] getLocation()
    {
        return cube.getLocation();
    }

    public Color [] getColor()
    {
        return cube.getColor();
    }

    public void set2DLoc(int texas, int wyoming)
    {
        x = texas;
        y = wyoming;
    }

    public int type() //returns the type of the cubelet 
    {
        if (cube.getColor().length == 3) return 3;
        else if (cube.getColor().length == 2) return 2;
        else if (cube.getColor().length == 1) return 1;
        else return 0; //no cube with no colors exists--> except the middle cube
    }
}