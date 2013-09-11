import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class elephant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class elephant  extends Actor
//dimensions of world
{
    final int width = 50; 
    final int height = 50;
    final int buffer = 5; //wall buffer
    final int speed = 8; //vector magnitude
    final int sec = 10; //iterations of the act method before the image is removed
    int count;

    Vector v;

    Explosion explode;

    /**
     * Act - do whatever the elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public elephant()
    {
        v = new Vector(0 + Greenfoot.getRandomNumber(360), speed);
    }

    public void act() 
    {
        // Add your action code here.
        if (foundBurger())
        {
            eatBurger();
        }
        if (explosionExists())
        {
            //v.scale(); //increases speed by ten percent
            count++;
        }
        if (count>=sec && explosionExists())
        {
            count = 0;
            getWorld().removeObject(explode);
        }
        else move();
    }    

    public boolean foundBurger()
    {
        Actor ninjaBurger = getOneObjectAtOffset(0, 0, ninjaBurger.class);
        if (ninjaBurger != null)
        {
            ninjaBurger.setImage("hamburger.png");
            return true;
        }
        else return false;
    }

    public void eatBurger()
    {
        Actor ninjaBurger = getOneObjectAtOffset(0, 0, ninjaBurger.class);
        if (ninjaBurger != null)
        {
            getWorld().removeObject(ninjaBurger);
        }
    }

    public boolean explosionExists()
    {
        if(getWorld().getObjects(Explosion.class).size()>=1)
            return true;
        else return false;
    }

    //     public void move()
    //     {
    //         //Rotation and random movement
    //         int locationX = (int)((Math.random()*7)-3)%width;
    //         int locationY = (int)((Math.random()*7)-3)%height;
    //         setLocation(getX() + locationX, getY() + locationY);
    // 
    //         //Explosion: if it gets close to a wall
    //         if (getX() == width-5 ||  getY() == height-5)
    //         { 
    //             explode = new Explosion();
    //             getWorld().addObject(explode, getX(), getY());
    //             explode.explode();
    //             setLocation(0,0);
    //         }

    public void move() //vector
    {
        double lettuceX = getX() + v.getX();
        double lettuceY = getY() + v.getY();
        setLocation((int)lettuceX, (int)lettuceY);
        if (getX() == width-buffer ||  getY() == height-buffer)
        { 
            changeDir(); //changes direction
            explode = new Explosion();
            getWorld().addObject(explode, getX(), getY());
            explode.explode();
        }
    }

    private void changeDir()
    {
        //at the left wall
        if (getX() == width-buffer) 
            v.setDirection(v.getDirection() - (90 + Greenfoot.getRandomNumber(180)));
        //at the right wall 
        if (getX() == buffer)
            v.setDirection(v.getDirection() + 90 + Greenfoot.getRandomNumber(180));
        //at the top wall
        if (getY() == height-buffer)
            v.setDirection(v.getDirection() - (90 + Greenfoot.getRandomNumber(180)));
        //at the bottom wall
        if (getY() == buffer)
            v.setDirection(v.getDirection() - (90 + Greenfoot.getRandomNumber(180)));
    }
}
