import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion  extends Actor
{
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    final int expRadius = 30; //set blast radius
    public Explosion () 
    {
    }    

    public void act()
    {
        explode();
    }

    public void explode () //initiates an explosion
    {
        List <ninjaBurger> objects = getObjectsInRange(expRadius*10, ninjaBurger.class);
        ListIterator <ninjaBurger> angusBeef = objects.listIterator();
        ninjaBurger essplode;
        if (objects.size()>0)
        {
            if (angusBeef.hasNext())
            { 
                essplode = angusBeef.next();
                essplode.setLocation(getX()+expRadius, getY()+expRadius);
            }
        }
    }
}
