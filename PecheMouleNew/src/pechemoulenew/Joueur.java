package pechemoulenew;

/**
 * Notre joueur, ou un ennemi
 */
public class Joueur {

    private int Equipe;
        
    private Bonus bonus; // le bonus que possède actuellement le joueur
    private Couple coordonneesJoueur; // les coordonnées du joueur sur la carte
    private int score; // le nombre de points engrangés par le joueur

    /**
    * Fonction permettant de récupérer l'équipe
    * @return Equipe : int, l'équipe du joueur
    */
    public int getEquipe() {
        return Equipe;
    }

    /**
    * Fonction permettant de définir l'équipe
    * @param Equipe
    */
    public void setEquipe(int Equipe) {
        this.Equipe = Equipe;
    }

    
    
    /**
     * Permet de stocker les coordonnées du joueur
     * @param l_coordonnees 
     */
    public void setCoordonneesJoueur(Couple l_coordonnees) {
        this.coordonneesJoueur = l_coordonnees;
    }
    

    /**
     * [incomplète] Fonction comparant les coordonnées du joueur avec ceux de la carte pour repérer s'il y a un bonus à cet emplacement et le créer le cas échéant
     * @return 
     */
    /*public Bonus checkBonus(Map mapjeu) {
        
    }*/
    
}