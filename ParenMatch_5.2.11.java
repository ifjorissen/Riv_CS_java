
/**
 * Replace this text with purpose and description
 * 
 *   
 *                   ___.
 *                 /.   \.___.
 *                /-  \_/     \
 *             `_// \   ___   |\.'
 *                   |__|  |__|
 * 
 * @author      Isabella Jorissen
 * @version     1.0
 *
 * Title      : ParenMatch
 * Date       : 5.2.2011
 * Assignment : 12-6-2011
 * Comments   : 1.33333 hours
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

import java.util.*;
import java.util.Scanner;

public class ParenMatch
{
    public static void main (String[] args)
    {
        Stack s = new Stack();
        String line;             // the string of characters to be checked 
        Scanner scan = new Scanner(System.in);
        int countR = 0, countL = 0;
        int error=0;
        System.out.println ("\nParenthesis Matching");
        System.out.print ("Enter a parenthesized expression: ");

        line = scan.nextLine();
        //line = line.replaceAll("[^()]", ""); //replaces everything that is noth a parentheses with nothing

        if (line.length()>1)

        {
            
            for (int i = 0; i< line.length(); i++)
            {

                String x = line.charAt(i)+"";

                if (x.equals("("))

                { 
                    s.push(x); //pushes left parentheses into stack
                    countL++;
                }

                else if (x.equals(")")) //charAt(i) is a right parentheses

                {
                    if (!s.empty())
                        s.pop();//we've hit a right paren, we need to pop off a left parentheses
                    else
                    {
                        System.out.println("Error occurred at character " + (i+1));
                        error = i;
                        i = line.length() + 1;
                    }
                    countR++;
                }
            }

            if (s.empty() && countL==countR)
                System.out.println("Balanced");
            else 
                System.out.println("Unbalanced " + "Right parentheses: " + countR + " Left Parentheses: " + countL
                + " Balanced part: " + line.substring(0,(error)));
        }

        else System.out.println("Unbalanced or Empty String");

    }
}
