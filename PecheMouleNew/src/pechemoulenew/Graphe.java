/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pechemoulenew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author simonetma
 */
public class Graphe {
    
    //Attributs
    
    private HashMap<Couple,Integer> matriceAdjacence;                           //un graphe est défini par sa matrice d'adjacence
    private int nombreDeSommets;                                                //nombre de sommet du graphe
    private int nbActionElem;
    
    private boolean mark[];
    private ArrayList<Integer> distance;
    private int predecesseur[];
    
    private int infinie;
    
    //Maj Nico : Couple : rpz les coordonnées du sommet et son type
    private HashMap<Couple,TypeTerrain> typeSommets;
    
    
    //Constructeur du graphe
    public Graphe(int _nombreDeSommets) {
        this.nombreDeSommets = _nombreDeSommets;
        this.matriceAdjacence = new HashMap<>();
        this.mark = new boolean[(this.nombreDeSommets*this.nombreDeSommets)+1];
        this.predecesseur = new int[(this.nombreDeSommets*this.nombreDeSommets)+1];
        this.distance = new ArrayList<Integer>();
        this.infinie=1;
        //Maj Nico :
        this.typeSommets = new HashMap<>();
    }
    
    //place "valeur" en position (i,j) de la matrice 
    public void modifierMatrice(int i,int j,int valeur) {
        this.matriceAdjacence.put(new Couple(i,j), valeur);
    }
    
    //Maj Nico, set le type terrain pour des coordonnées
    public void modifierTypeTerrain(int i , int j, TypeTerrain tt){
        this.typeSommets.put(new Couple(i,j), tt);
    }
    
    //renvoie la valeur de la matrice en position (i,j)
    public int Matrice(int i,int j) {
        //valeur par défaut
        int res = 0;
        Couple c = new Couple(i,j);
        //si (i,j) est bien présent dans la matrice
        if(this.matriceAdjacence.containsKey(c)) {
            res = this.matriceAdjacence.get(c);
        }
        return res;
    }
    
    //renvoie le nombre de sommet du graphe
    public int NombreSommet() {
        return this.nombreDeSommets;
    }
    
    //renvoie la matrice d'adjacence
    @Override
    public String toString() {
        String res = "";
        for(int i=1;i<=this.nombreDeSommets;i++) {
            for(int j=1;j<=this.nombreDeSommets;j++) {
                res += this.Matrice(i, j);
                if(j!= this.nombreDeSommets) {
                    res += " / ";
                }
            }
            if(i!= this.nombreDeSommets) {
                res += "\n";
            }
        }
        return res;
    }
    public void ajouterArc(int debut, int fin){
        this.modifierMatrice(debut, fin, 1);
    }
    
    public void ajouterArete(int debut, int fin){
        this.modifierMatrice(debut, fin, 1);
        this.modifierMatrice(fin, debut, 1);
    }
    
    public void ajouterAretePoids(int debut, int fin , int poids){
        this.modifierMatrice(debut, fin, poids);
        this.modifierMatrice(fin, debut, poids);
    }
    
    public boolean EstReflexif(){
        boolean res=true;
        for (int i=1;i<(NombreSommet()+1);i++){
            if (Matrice(i,i)==0){
                res=false;
            }
        }
        return res;
    }
    
    public boolean EstSymetrique(){
        boolean res=true;
        nbActionElem++;
        nbActionElem++;
        for (int i=1;i<(NombreSommet()+1);i++){
            nbActionElem+=2;
            nbActionElem++;
            for(int j=1;j<(NombreSommet()+1);j++){
                nbActionElem+=2;
                nbActionElem++;
                if(Matrice(i,j)!=Matrice(j,i)){
                    nbActionElem+=2;
                    res=false;
                    nbActionElem++;
                }
            }
        }
        return res;
    }
    
    public boolean EstTransitif(){
        boolean res=true;
        nbActionElem++;
        nbActionElem++;
        for (int i=1;i<(NombreSommet()+1);i++){
            nbActionElem+=2;
            nbActionElem++;
            for (int j=1;j<(NombreSommet()+1);j++){
                nbActionElem+=2;
                nbActionElem++;
                for (int k=1; k<(NombreSommet()+1);k++){
                    nbActionElem+=2;
                    nbActionElem++;
                    if((Matrice(i,j) ==1) && (Matrice(j,k)==1)) {
                        nbActionElem++;
                        if((Matrice(i,k)==0)){
                            nbActionElem++;
                            res=false;
                        }
                    }
                }
            }
        }
        return res;
    }
    
    public boolean EstAntiSymetrique(){
        boolean res=true;
        nbActionElem++;
        nbActionElem++;
        for (int i=1;i<(NombreSommet()+1);i++){
            nbActionElem+=2;
            nbActionElem++;
            for (int j=1;j<(NombreSommet()+1);j++){
                nbActionElem+=2;
                nbActionElem++;
                for (int k=1; k<(NombreSommet()+1);k++){
                    nbActionElem+=2;
                    nbActionElem++;
                    if(Matrice(i,j)==Matrice(j,i)){
                        nbActionElem++;
                        if((i!=j)){
                            nbActionElem++;
                            res=false;
                        }
                    }
                }
            }
        }
        return res;
    }
    
    public void ClotureTransitive(){
        boolean modifie = false;
        nbActionElem++;
        do{ 
            nbActionElem++;
            modifie=false;
            nbActionElem++;
            for (int i=1;i<(NombreSommet()+1);i++){
                nbActionElem+=2;
                nbActionElem++;
                for (int j=1;j<(NombreSommet()+1);j++){
                    nbActionElem+=2;
                    nbActionElem++;
                    for (int k=1; k<(NombreSommet()+1);k++){
                        nbActionElem+=2;
                        nbActionElem++;
                        nbActionElem++;
                        if((Matrice(i,j) ==1) && (Matrice(j,k)==1)) {
                            nbActionElem++;
                            if((Matrice(i,k)==0)){
                                nbActionElem++;
                                this.ajouterArc(i, k);
                                nbActionElem++;
                                modifie=true;
                            }
                        }
                    }
                }
            }
            nbActionElem++;
        }while(modifie==true);
    }
    
    public void nbElementaire(){
        System.out.println(this.nbActionElem);;
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param m le nombre de colonnes (à vérifier)
     * @return 
     */
    public int numeroCase(int i, int j, int m){
        return (m*(i-1)+j);
    } 
    
    public int JCase(int n,int taille){
        return n%taille;
    }
    
    public int ICase(int n, int j, int taille){
        return ((n-j)/taille);
    }
    
    public void parcoursLargeur(int s){
        int x;
        boolean Marks[] = new boolean[this.NombreSommet()+ 1];
        for (int i=0;i<=(this.nombreDeSommets);i++){
            Marks[i]=false;
        }
        Marks[0]=true;                    
        ArrayList<Integer> aTraiter=new ArrayList<>();
        aTraiter.add(s);
        while (aTraiter.isEmpty()!=true) {
            x=aTraiter.get(0);
            aTraiter.remove(0);
            System.out.println(x);
            for (int j=0;j<=this.NombreSommet();j++){
                if((Marks[j]==false) && (this.Matrice(j,x)==1)){
                    aTraiter.add(j);
                    Marks[j]=true;
                }
            }
        }
    }
    
    public void parcoursProfondeur(int s){
        int x;
        boolean Marks[] = new boolean[this.NombreSommet()+ 1];
        for (int i=0;i<=(this.nombreDeSommets);i++){
            Marks[i]=false;
        }
        Marks[0]=true;                    
        ArrayList<Integer> aTraiter=new ArrayList<>();
        aTraiter.add(s);
        while (aTraiter.isEmpty()!=true) {
            x=aTraiter.get(aTraiter.size()-1);
            aTraiter.remove(aTraiter.size()-1);
            System.out.println(x);
            for (int j=0;j<=this.NombreSommet();j++){
                if((Marks[j]==false) && (this.Matrice(j,x)==1)){
                    aTraiter.add(j);
                    Marks[j]=true;
                }
            }
        }
    }
    
    public int distance(int a, int b){
        int res;
        Integer[] distance = new Integer[this.nombreDeSommets+1];
        int x;
        boolean Marks[] = new boolean[this.NombreSommet()+ 1];
        for (int i=0;i<=(this.nombreDeSommets);i++){
            Marks[i]=false;
            distance[i]=0;
        }
        Marks[0]=true;                    
        ArrayList<Integer> aTraiter=new ArrayList<>();
        aTraiter.add(a);
        while (aTraiter.isEmpty()!=true) {
            x=aTraiter.get(0);
            aTraiter.remove(0);
            for (int j=0;j<=this.NombreSommet();j++){
                if((Marks[j]==false) && (this.Matrice(j,x)==1)){
                    aTraiter.add(j);
                    Marks[j]=true;
                    distance[j]++;
                }
            }
        }
        res=distance[b];
        return res;
    }
    
    private void relachement(int a,int i){
        if(this.Matrice(a, i)!=0) {
            if(this.distance.get(i)>this.distance.get(a)+this.Matrice(a, i)) {
                this.distance.set(i,this.Matrice(a, i) + this.distance.get(a));
                this.predecesseur[i]=a;
            }
        }
    }
    
    private void calculInfini(){
        this.infinie=0;
        for(int i=1;i<=this.nombreDeSommets;i++){
            for(int j=1;j<=this.nombreDeSommets;j++){
                this.infinie+=this.Matrice(i, j);
            }
        }
        this.infinie+=1;
    }
    
    private void initialisation(int s){
        this.calculInfini();
        for(int i=0; i<=this.nombreDeSommets*this.nombreDeSommets;i++){
            this.mark[i]=false;
            this.distance.add(this.infinie);
            this.predecesseur[i]=-1;
        }
        this.distance.set(s, 0);
    }
    
    private int selectionSommet(){
        int indice=-1;
        int min=this.infinie+2;
        for(int i=1;i<=this.nombreDeSommets*this.nombreDeSommets;i++){
            if(this.distance.get(i) < min && !mark[i]){
                min=this.distance.get(i);
                indice=i;
            }
        }
        return indice;
    }
    
    private boolean existeSommetNonMarqué(){
        boolean res=false;
        for(int i=1;i<=this.nombreDeSommets;i++){
            if(!this.mark[i]){
                res=true;
            }
        }
        return res;
    }
            
    public int[] distanceDijkstra(int dep, int arr){
        //est ce que j'ai considerer le sommet'
        //Tant que mark n'est pas vrai partout on choisit a<- le sommet non marqué tel que d[a] est minimum
        //mark[a]<-vrai
        //pour tt sommet i
        //relachement(a,i) --> Si d[i]>d[a]+p[a,i] alors d[i]<- d[a]+p(a,i) et predecesseur[i]<-a
        this.initialisation(dep);
        while(this.existeSommetNonMarqué()){
            int a = this.selectionSommet();
            this.mark[a]=true;
            for(int i=1;i<=this.nombreDeSommets;i++){
                this.relachement(a, i);
            }
        }
        return this.predecesseur;
    }
    
    public ArrayList reverseDijkstra(int caseVoulu, int[] tabpredecesseurs){
        //Permet de parcourir le tabpredecesseurs et d'obtenir le chemin le plus court
        ArrayList dijkstra = new ArrayList();
        int i = caseVoulu;
        dijkstra.add(i);
        while(tabpredecesseurs[i]!=-1){
            i=tabpredecesseurs[i];
            dijkstra.add(i);
        }
        return dijkstra;
    }
}
