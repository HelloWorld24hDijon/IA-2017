package pechemoulenew;

import java.util.ArrayList;

/**
 * Ce qui modelise la map forme de graphe.
 * Est compose de types de terrain (Dune Sable)
 * Et a des items au sol
 */
public class Map {
    
    /**
     * Graphe de la map
     */
    Graphe graph;
    
    /**
     * Liste des items de la map
     */
    ArrayList<item> listeItems = new ArrayList();
    
    /**
     * Constructeur de la classe Map, initalise le graphe
     */
    public Map(){
        this.graph = new Graphe(6);
        
        graph.ajouterArete(1, 2);
        graph.ajouterArete(2, 3);
        graph.ajouterArete(1, 4);
        graph.ajouterArete(4, 5);
        graph.ajouterArete(5, 6);
        graph.ajouterArete(3, 6);
        
        System.out.println(graph.numeroCase(1, 2, 0));
        //graph.modifierTypeTerrain(1, 2, );
    }
    
    
    /**
     * Retourne tous les items présent sur la map
     * @return la liste des items
     */
    
    public ArrayList<item> getListeItem(){
        return this.listeItems;
    }
    
    /**
     * Ajoute un item à la liste
     * @param i l'item à ajouter
     */
    public void addItem(item i){
        this.listeItems.add(i);
    }
    
    /*
        Méthode permettant de créer le graphe en fonction de la chaine de caractère infosJoueurs
    */
    private void initialisation(String reponseServeur){
        
        boolean tailleCarte = false; //permet de savoir si on a traité la partie taille de la carte.
        boolean carte = false; //permet de savoir si on a traité la partie création de la carte.
        boolean joueur = false; //permet de savoir si on a traité la partie position des joueurs.
        
        int indexReponseServeur = 0; //Permet d'avancer caractère par caractère dans infosJoueurs
        
        while(tailleCarte){
            while(reponseServeur.charAt(indexReponseServeur)!='x'){ //Parcours de reponseServeur jusqu'à la deuxième partie de la taille
                indexReponseServeur++;
            }
            
            int i = reponseServeur.charAt(indexReponseServeur);
            
            while(reponseServeur.charAt(indexReponseServeur)!='/'){//Parcours de reponseServeur jusqu'au '/'
                indexReponseServeur++;
            }
            
            int j = reponseServeur.charAt(indexReponseServeur);
            
            if(reponseServeur.charAt(indexReponseServeur)=='/'){ //Si le caractère est '/' Le parcours est termine
                tailleCarte = true;
                indexReponseServeur++;
            }
            this.graph = new Graphe(i*j);
        }
        
        while(carte){ //Parcours de reponseServeur pour la création de la carte
            
            if(reponseServeur.charAt(indexReponseServeur) == '-'){//Séparateur '-'
                indexReponseServeur++;
            }else if(reponseServeur.charAt(indexReponseServeur)!='/') //Test pour savoir si on est à la fin de la création de la carte
            {
                System.out.println(reponseServeur.charAt(indexReponseServeur));
            }
            if(reponseServeur.charAt(indexReponseServeur) == '/'){//Si le caractère est un '/' on est à la fin de la création de la carte
                carte = true;
            }
            
        }
        
        while(joueur){ //nombre et emplacement des joueurs.
            int nbInfos = 0; //nombre d'informations traités.
            if(reponseServeur.charAt(indexReponseServeur)=='-'){ //Séparateur des informations
                indexReponseServeur++;
            }else if(reponseServeur.charAt(indexReponseServeur) == ','){ //Séparateur des coordonnées
                
            }else if(reponseServeur.length() == indexReponseServeur){ //Fin de la String
                joueur = true;
            }
        }
    }
    
}