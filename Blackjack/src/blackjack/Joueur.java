package blackjack;

/**
 *
 * @author CONDE Oumar
 */
public class Joueur {
    private Main main; // la main d'un joueur
    private int budget;  // le budget du joueur

    public Joueur(Main main, int budget) {
        this.main = main;
        this.budget = budget;
    }

    public Joueur(int budget) {
        this.main = new Main();
        this.budget = budget;
    }
    /**
     * Accède au budget d'un joueur
     * @return le budget
     */
    public int getBudget() {
        return budget;
    }
    /**
     * Modifie le budget d'un joueur
     * @param budget le nouveau budget du joueur
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }
    /**
     * Accède aux main d'un joueur
     * @return 
     */
    public Main getMain() {
        return main;
    }
    /**
     * Modifie la main d'un joueur
     * @param main la nouvelle main
     */
    public void setMain(Main main) {
        this.main = main;
    }
           
}
