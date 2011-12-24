package blackjack;

import java.util.ArrayList;

/**
 *
 * @author CONDE Oumar
 */
public class Blackjack {

    /**
     * Lit une ligne de l'entree standard.
     * @return Renvoie la ligne lue ou une chaine vide si un probleme est survenu.
     */
    public static String readLine() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        scanner.useDelimiter("\n");
        String input;
        try {
            input = scanner.next();
        } catch (Exception e) {
            input = "";
        }
        return input;
    }

    /**
     * Lit une ligne de l'entree standard.
     * @return Renvoie le premier entier lu ou -1 si un probleme est survenu.
     */
    public static int readInt() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int input;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            input = -1;
        }
        return input;
    }

    /**
     * Affiche le menu du jeu Blackjack
     */
    public static void menu() {
        System.out.println("------------ MENU ------------");
        System.out.println("1- Nouvelle partie");
        System.out.println("2- Quitter");
        System.out.print("Votre choix: ");
    }

    /**
     * Commence un nouvelle partie en creant des joueurs
     * @return 
     */
    public static ArrayList<Joueur> nouvellePartie() {
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        System.out.println("***** Configuration de la nouvelle partie ********");
        int nbJoueurs = 0;
        //recupération du nombre de joueurs
        while (nbJoueurs <= 0) {
            System.out.print("Nombre de joueurs: ");
            nbJoueurs = Blackjack.readInt();            
            if(nbJoueurs > 7){
                nbJoueurs = 0;
                  System.out.print("Nombre de joueurs autorisés: 7 au maximum ");
            }
        }
        for (int i = 1; i <= nbJoueurs; i++) {
            joueurs.add(new Joueur(1000, "Joueur " + i));
        }
        System.out.println("***** Fin de la configuration ********");
        return joueurs;
    }

    public static void main(String[] args) {
        System.out.println(" $$$$$$$$$$$$$$$$$$$$$$ Bienvenue dans le jeu Blackjack $$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Blackjack.menu();
        int choixMenu = 0;
        Table table = null;
        while (choixMenu != 1 && choixMenu != 2) {
            choixMenu = Blackjack.readInt();
            switch (choixMenu) {
                case 1:
                    table = new Table(Blackjack.nouvellePartie());
                    System.out.println("***** Début des mises ********");
                    table.mise();      // chaque joueur mise un montant
                    System.out.println("***** Fin des mises ********");
                    table.melanger();   // le croupier malange les cartes
                    table.creerSabot(); // le croupier defausse les 5 premieres cartes du jeu et le reste forme le sabot
                    System.out.println("***** Début de la partie ********");
                   // while (!table.estPartieFinie()) {
                        table.faireUnTour();
                        table.resultat();
                        table.validerTour();                        
                    //}
                    System.out.println("***** Fin de la partie ********");

                    break;
                case 2:
                    System.out.println("Merci de votre visite, au revoir !");
                    break;
                default:
                    System.out.println("Attention: Veuillez saisir 1 ou 2");
                    System.out.print("Votre choix: ");
            }
        }
    }
}
