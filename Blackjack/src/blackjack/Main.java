package blackjack;

import java.util.ArrayList;

/**
 *
 * @author KABA N'faly
 */
public class Main {

    private ArrayList<Carte> cartes;

    /**
     * Constructeur pour créer une liste de cartes vides
     */
    public Main() {
        this.cartes = new ArrayList<Carte>();
    }

    /**
     * Constructeur pour créer une liste de cartes à partir d'une autre liste de cartes
     * @param cartes 
     */
    public Main(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    /**
     * Accède aux cartes
     * @return les cartes
     */
    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    /**
     * Modifie la liste des cartes
     * @param cartes 
     */
    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    /**
     * Ajoute une carte à la liste des cartes
     * @param carte 
     */
    public void ajouterCarte(Carte carte) {
        if (!cartes.contains(carte)) {
            cartes.add(carte);
        }
    }

    /**
     * Calcule le total de points que rapporte toutes les cartes
     * @return le total des points
     */
    public int somme() {
        int total = 0;
        int taille = cartes.size();
        ArrayList<Integer> indiceCarteAs = new ArrayList<Integer>();
        for (int i = 0; i < taille; i++) {
            //verifie l'existance d'un as afin d'avoir une valeur optimale
            if (cartes.get(i).getType().contains("As")) {
                indiceCarteAs.add(new Integer(i));
            }
            total += cartes.get(i).getValeur();
        }
        if (total > 21) {
            for (int j = 0; j < indiceCarteAs.size(); j++) {
                if (cartes.get(indiceCarteAs.get(j).intValue()).getValeur() == 11) {
                    cartes.get(indiceCarteAs.get(j).intValue()).setValeur(1);
                    total -= 10;
                }
            }
        }
        return total;
    }

    /**
     * Verifie qu'il est possible de partager le jeu
     * @return 
     */
    public boolean estPartagePossible() {
        if (cartes.size() == 2) {
            return cartes.get(0).getValeur() == cartes.get(1).getValeur();
        }
        return false;
    }

    @Override
    public String toString() {
        String retour = "";
        if (cartes.size() > 0) {
            for (int i = 0; i < cartes.size(); i++) {
                retour += cartes.get(i).toString() + "\n";
            }
            retour += "Total point: " + somme() + "\n";
        } else {
            retour += "Aucune carte\n";
        }
        return retour;
    }
}
