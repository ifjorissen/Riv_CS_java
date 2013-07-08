/**
 * Replace this text with purpose and description
 * 
 * 
 * @author      Isabella Jorissen
 * @version     1.0
 *
 * Title      : Linked Lists
 * Date       : 
 * Assignment : 
 * Comments   : 
 * ~Time      : 
 *
 * Disclaimer : 
 *
 */

/*#
 * Grade    : 
 * Comments : 
 * 
 */

// ****************************************************************
//   DoubleLinked.java
//
//   A class using a doubly linked list to represent a list of integers.
//          
// ****************************************************************
public class DoubleLinked
{
    private IntNode list;            

    // --------------------------------------------------
    // Constructor -- initializes list
    // --------------------------------------------------
    public DoubleLinked()
    {
        list = null;
    }

    // --------------------------------------------------
    // Prints the list elements
    // --------------------------------------------------
    public void print()
    {
        for (IntNode temp = list; temp != null; temp = temp.next)
            System.out.println(temp.val);
    }

    // --------------------------------------------------
    // Adds new element to front of list
    // --------------------------------------------------
    public void addToFront(int val)
    {
        IntNode bow_chicka_wow_wow = new IntNode(val);
        bow_chicka_wow_wow.next = list;

        if (list != null)
            list.prev = bow_chicka_wow_wow;

        list = bow_chicka_wow_wow;   
    }

    public void addToEnd(int val)
    {   
        IntNode crab = list;
        if (list == null)
            list = crab;

        while (crab.next!=null)
            crab = crab.next; //gets to the end of the list

        crab.next = new IntNode (val); //adds the value
    }

    public void removeFirst()
    {
        if (list != null)
        {
            list = list.next; //remove first node
            list.prev = null; //removes previous reference
        }
        //otherwise, list is empty and there is nothing to remove
        else list = list;
    }

    public void removeLast()
    {     
        if (list!=null)
        {
            IntNode gold = list;

            while (gold.next!=null)
                gold = gold.next;

            gold = gold.prev; 
            gold.next = null; //set the next node to null 
           //how to you remove the previous reference; is there one?
        }
    }

    public void remove (int oldVal)
    {
        IntNode gallium = list;

        while (gallium.val != oldVal)
            gallium = gallium.next;
            
        gallium = gallium.prev;
        gallium.next = null; //removes reference to next node
        gallium.next.prev = null; //goes to next node then sets the node previous to it to null
    }

    //*************************************************************
    // An inner class that represents a list element.  
    //*************************************************************

    private class IntNode
    {
        public int val;
        public IntNode next;
        public IntNode prev;

        public IntNode(int val)
        {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
