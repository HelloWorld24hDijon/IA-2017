package pechemoulenew;

import java.util.ArrayList;
/**
 * Modelise le terrain et permet de connaitre rapidement si on est sur une dune ou sur du sable
 */
public abstract class TypeTerrain {
    private String identifiant;
    private item itemsommet; //item présent sur le bloc
    private ArrayList<Joueur> listejoueurs = new ArrayList(); //joueurs éventuellement présent sur le bloc
}