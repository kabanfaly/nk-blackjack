package blackjack;

import java.util.ArrayList;

/**
 *
 * @author CONDE Oumar
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
    public void ajouterCarte(Carte carte){
        if(!cartes.contains(carte)){
            cartes.add(carte);
        }
    }
    /**
     * Calcule le total de points que rapporte toutes les cartes
     * @return le total des points
     */
    public int calculerPoints(){
        int total = 0;
        int taille = cartes.size();
        for(int i=0; i<taille; i++){
            total += cartes.get(i).getValeur();
        }
        return total;
    }

    @Override
    public String toString() {
        String retour = "";
        if(cartes.size() > 0){
        for(int i=0; i<cartes.size(); i++){
            retour += cartes.get(i).toString() + "\n";
        }
        }else{
            retour += "Aucune carte\n";
        }
        return retour;
    }
    
}
