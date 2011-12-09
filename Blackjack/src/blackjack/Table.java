package blackjack;

import java.util.ArrayList;

/**
 *
 * @author CONDE Oumar
 */
public class Table {

    ArrayList<Joueur> joueurs;
    ArrayList<Carte> cartes;
    Joueur croupier;
    ArrayList<Carte> defausse; // carte de la defausse

    public Table(ArrayList<Joueur> joueurs, ArrayList<Carte> cartes, Joueur croupier) {
        this.joueurs = joueurs;
        this.cartes = cartes;
        this.croupier = croupier;
        defausse=  new ArrayList<Carte>();
    }

    /**
     * Accède aux cartes
     * @return les cartes
     */
    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    /**
     * Modifie toutes les cartes (sabot)
     * @param cartes les nouvelles cartes
     */
    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    /**
     * Accède au croupier
     * @return le croupier
     */
    public Joueur getCroupier() {
        return croupier;
    }

    /**
     * Modifie le croupier
     * @param croupier le nouveau croupier
     */
    public void setCroupier(Joueur croupier) {
        this.croupier = croupier;
    }

    /**
     * Accède à la liste des joueurs
     * @return les joueurs
     */
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Modifie la liste des joueurs
     * @param joueurs la nouvelle liste des joueurs
     */
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Ajoute un nouveau joueur
     * @param j 
     */
    public void ajouterJoueur(Joueur j) {
        if (!joueurs.contains(j)) {
            joueurs.add(j);
        }
    }

    /**
     * Supprime un joueur
     * @param j 
     */
    public void supprimerJoueur(Joueur j) {
        if (joueurs.contains(j)) {
            joueurs.remove(j);
        }
    }

    /**
     * Supprime un carte du jeu de cartes
     * @param c carte à supprimer
     */
    public void supprimerCarte(Carte c) {
        if (cartes.contains(c)) {
            cartes.remove(c);
        }
    }

    /**
     * Melange les carte du jeu
     */
    public void melanger() {       
        //nombre de cartes
        int n = cartes.size(); 
        //effectue le melange 1000 fois
        for(int i=0; i<1000; i++){
            int position1 = Table.randomInt(n);
            int position2 = Table.randomInt(n);
            //echanger les positions
            Carte tmp = cartes.get(position1);
            cartes.set(position1, cartes.get(position2));
            cartes.set(position2, tmp);
        }
    }
    /**
     * Defausse la première carte du jeu
     */
    public void defausserUneCarte(){
        if(cartes.size() > 0){
            defausse.add(cartes.get(0)); // ajout la de premiere carte a la defausse
            cartes.remove(0); // suppression de la premiere carte des cartes du jeu
        }
    }
    /**
     * Donne la premiere carte du jeu a un joueur
     * @param j le joueur demandeur
     */
    public void donnerCarte(Joueur j){
       j.ajouterCarte(cartes.get(0));
       cartes.remove(0);
    }
    
    /**
     * Cree le sabot: Melange les cartes du jeu puis defausse les 5 premieres carte
     */
    public void creerSabot(){
        this.melanger();
        for(int i=0; i<5; i++){
            this.defausserUneCarte();
        }
    }
    /**
     * Verifie que le sabot contient moins de 52 cartes. Si oui on recupere la carte de la defausse;
     */
    public void validerTour(){
        if(cartes.size() < 52){
            cartes.addAll(defausse); //recuperer les cartes de la defausse
            defausse.clear(); // reinitialisation de la defausse
        }
    }
    /**
     * Renvoie un entier aleatoirement choisi entre 0 (compris) et max (non
     * compris).
     * @param max La borne sur l'entier pouvant etre generee.
     * @return Un entier aleatoire.
     */
    public static int randomInt(int max) {
        java.util.Random gen = new java.util.Random();
        return gen.nextInt(max);
    }
}
