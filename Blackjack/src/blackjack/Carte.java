package blackjack;

/**
 *
 * @author KABA N'faly
 */
public class Carte {
    private String type; // le type de la carte    
    private int valeur;  // la valeur que rapporte la carte (point)
    /**
     * Constructeur pour créer une nouvelle carte
     * @param int type
     * @param int valeur 
     */
    public Carte(String type, int valeur) {
        this.type = type;
        this.valeur = valeur;
    }
    /**
     * Accède à la valeur de la carte
     * @return la valeur de la carte
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Modifie la valeur de la carte
     * @param valeur nouvelle valeur de la carte
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Accède au type de la carte
     * @return le type de la carte
     */
    public String getType() {
        return type;
    }

    /**
     * Modifie e type de la carte
     * @param type le nouveau type de la carte
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type+ " : Valeur = " + valeur;
    }
    
}
