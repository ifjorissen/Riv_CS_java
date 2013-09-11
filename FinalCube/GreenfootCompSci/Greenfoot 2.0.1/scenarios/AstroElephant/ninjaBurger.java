import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ninjaBurgers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ninjaBurger  extends Actor
{
    final int burgers = 3; //there always needs to be 3 burgers, or the elephant will starve
    /**
     * Act - do whatever the ninjaBurgers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ninjaBurger()
    {
        setImage("ninjaBurger.jpg");
    }

    public void act ()
    {
        if(getWorld().getObjects(ninjaBurger.class).size() < burgers)
        {
            replenish(); //keeps burger count at 3
        }
    }

    public void replenish()
    {
        for (int i = getWorld().getObjects(ninjaBurger.class).size(); i <= burgers; i++)
        {
            ninjaBurger replace = new ninjaBurger();
            getWorld().addObject(replace, (int)(Math.random()*45), (int)(Math.random()*45));
        }
    }
}
