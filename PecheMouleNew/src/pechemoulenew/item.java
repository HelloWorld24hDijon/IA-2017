package pechemoulenew;

/**
 * Un item est un objet place au sol, hors joueur
 */
public abstract class item {
    
    private Couple coordonnees;
    
    public item(int x, int y){
        coordonnees = new Couple(x,y);
    }

}