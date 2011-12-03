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
    public void ajouterJoueur(Joueur j){
        if(!joueurs.contains(j)){
            joueurs.add(j);
        }
    }
    /**
     * Supprime un joueur
     * @param j 
     */
    public void supprimerJoueur(Joueur j){
        if(joueurs.contains(j)){
            joueurs.remove(j);
        }
    }
    
    public void supprimerCarte(Carte c){
        if(cartes.contains(c)){
            cartes.remove(c);
        }
    }
}
