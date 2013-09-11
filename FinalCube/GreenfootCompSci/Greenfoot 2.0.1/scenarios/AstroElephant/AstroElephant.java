import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AstroElephant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AstroElephant  extends World
{

    /**
     * Constructor for objects of class AstroElephant.
     * 
     */
    public AstroElephant()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(50, 50, 10);
        populate();
        //  setBackground("space1.png");
    }

    public void populate ()
    {
        elephant dumbo = new elephant(); //add an elephant
        addObject(dumbo,0, 0);

        ninjaBurger ninja = new ninjaBurger(); //add random burgers

        addObject(ninja, 5, 12);

        ninjaBurger ninja2 = new ninjaBurger();
        addObject(ninja2, 40, 25);

        ninjaBurger ninja3 = new ninjaBurger();
        addObject(ninja3, 20, 40);

    }

}
