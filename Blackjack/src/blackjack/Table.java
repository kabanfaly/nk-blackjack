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

    public Table(ArrayList<Joueur> joueurs, ArrayList<Carte> cartes, Joueur croupier) {
        this.joueurs = joueurs;
        this.cartes = cartes;
        this.croupier = croupier;
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
