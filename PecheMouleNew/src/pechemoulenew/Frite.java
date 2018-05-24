package pechemoulenew;

import java.math.*;
import java.util.Random;
/**
 * Modelise une frite, un bonus qui permet d'avancer de 2 cases dans une direction
 */
public class Frite extends Bonus {
    
       public Frite (int x, int y){
           super(x,y);
       }
    public String aleat()
{
    String result = "";
    Random rand = new Random();
    int nombreAleatoire = rand.nextInt(3 - 0 + 1) + 0;
    if(nombreAleatoire ==  0)
    {
        result = "N";
    }
    else if (nombreAleatoire == 1)
    {
        result = "S";
    }
    else if(nombreAleatoire == 2)
    {
        result = "E";
    }
    else if(nombreAleatoire == 3)
    {
        result = "O";
    }
    
    return result;
}
}

