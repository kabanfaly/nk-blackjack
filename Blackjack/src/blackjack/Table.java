package blackjack;

import java.util.ArrayList;

/**
 *
 * @author KABA N'faly
 */
public class Table {

    private ArrayList<Joueur> joueurs;
    private ArrayList<Carte> cartes;
    private Joueur croupier;
    private ArrayList<Carte> defausse; // carte de la defausse

    public Table(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.croupier = new Joueur(0, "Croupier");
        this.cartes = new ArrayList<Carte>();
        //Creation de 6 jeux de 52 cartes
        for (int i = 0; i < 6; i++) {
            this.cartes.addAll(Table.creer52Cartes());
        }
        defausse = new ArrayList<Carte>();
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
     * Accède à la defausse
     * @return la defausse
     */
    public ArrayList<Carte> getDefausse() {
        return defausse;
    }

    /**
     * Remplace la defausse
     * @param defausse nouvelle defausse
     */
    public void setDefausse(ArrayList<Carte> defausse) {
        this.defausse = defausse;
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
     * Debarasse la table de jeu en ajoutant toutes les mains des joueurs ainsi
     * celles du croupier dans la defausse, 
     */
    public void debarrasser() {
        for (int i = 0; i < joueurs.size(); i++) {
            defausse.addAll(joueurs.get(i).getMain().getCartes());
            joueurs.get(i).getMain().setCartes(new ArrayList<Carte>());
        }
        defausse.addAll(croupier.getMain().getCartes());
        croupier.getMain().setCartes(new ArrayList<Carte>());
    }

    /**
     * Melange les carte du jeu
     */
    public void melanger() {
        //nombre de cartes
        int n = cartes.size();
        //effectue le melange 1000 fois
        for (int i = 0; i < 1000; i++) {
            int position1 = Table.randomInt(n);
            int position2 = Table.randomInt(n);
            //echanger les positions
            Carte tmp = cartes.get(position1);
            cartes.set(position1, cartes.get(position2));
            cartes.set(position2, tmp);
        }
    }

    /**
     * Effectue l'action de mise pour chaque joueur
     */
    public void mise() {
        int mise = -1;
        for (int i = 0; i < joueurs.size(); i++) {
            while (mise == -1) { // tant que le montant saisi n'est pas valide
                System.out.print(joueurs.get(i).getNom() + "\"Budget = " + joueurs.get(i).getBudget() + " euro(s)\", saisissez votre mise: ");
                mise = Blackjack.readInt();
                if (joueurs.get(i).estBudgetSuffisant(mise)) {
                    joueurs.get(i).miser(mise);
                } else {
                    mise = -1; //pour reprende la mise
                }
            }
            mise = -1;
        }
    }

    /**
     * Distribue deux cartes a chaque joueur et une carte au croupier
     */
    public void distribuer() {
        int choix = 0;
        //distribue deux cartes a chaque joueur
        for (int i = 0; i < joueurs.size(); i++) {
            donnerCarte(joueurs.get(i)); //
            donnerCarte(joueurs.get(i)); //
            System.out.println(joueurs.get(i));
            /*  if (joueurs.get(i).getMain().estPartagePossible()) {
            System.out.println("\n" + joueurs.get(i).getNom() + ", Souhaitez vous partager votre main? ");
            System.out.println("1- oui");
            System.out.println("2- non");
            System.out.print("Votre choix: ");
            choix = Blackjack.readInt();
            if (choix == 1) {
            Joueur j = joueurs.get(i); // creation d'un joueur identique au jour courant
            String nom = j.getNom();
            joueurs.get(i).setNom(nom + " (A)"); //modifie le nom du joueur courant
            joueurs.get(i).getMain().getCartes().remove(j.getMain().getCartes().get(0)); //Supprime la premiere carte du joueur courant
            joueurs.get(i).setEstPartager(true); //carte partagee
            donnerCarte(joueurs.get(i));
            
            j.setNom(nom + " (B)"); // modifie le nom du nouveau joueur (deuxieme partie du joueur courant)
            j.getMain().getCartes().remove(j.getMain().getCartes().get(1)); //Supprime la deuxième carte du 2eme joueur
            donnerCarte(j); // donner une deuxième carte au 2eme joueur
            j.setEstPartager(true); // carte partagee
            ajouterJoueur(j); // ajout du 2 joueur joueur dans le jeu                      
            }
            }*/
            if (joueurs.get(i).getMain().somme() < 21) {
                System.out.println("\n" + joueurs.get(i).getNom() + ", Souhaitez vous double votre mise? ");
                System.out.println("1- oui");
                System.out.println("2- non");
                System.out.print("Votre choix: ");
                choix = Blackjack.readInt();
                if (choix == 1) {
                    joueurs.get(i).miser(joueurs.get(i).getMise());
                    System.out.println("Votre mise est maintenant de " + joueurs.get(i).getMise() + " euros");
                }
            }
        }
        //distribue une carte au croupier
        donnerCarte(croupier);
        System.out.println(this);
    }

    public static void menuChoixHit() {
        System.out.println("******* Choix disponibles *****");
        System.out.println("1- Un Hit   (Tirer une carte)");
        System.out.println("2- Rester   (Stand)");
        System.out.print("Votre choix: ");
    }

    /**
     * Fait un tour
     */
    public void faireUnTour() {
        distribuer();

        for (int i = 0; i < joueurs.size(); i++) {
            if (croupier.getMain().getCartes().get(0).getType().contains("As")) { // si assurance possible
                System.out.println("\n" + joueurs.get(i).getNom() + ", Voulez vous prendre une assurance ?");
                System.out.println("1- oui");
                System.out.println("2- non");
                System.out.print("Votre choix: ");
                if (Blackjack.readInt() == 1) {
                    joueurs.get(i).setEstAssurance(true);
                    //paye la moitie de sa mise comme assurance
                    double miseJoueur = joueurs.get(i).getMise();
                    joueurs.get(i).setMise(miseJoueur + miseJoueur / 2);
                }
            }
            int choix = 0;
            boolean tirer = true;
            while (tirer && joueurs.get(i).getMain().somme() < 21) { // tant que le joueur souhaite recevoir une carte
                System.out.println(joueurs.get(i));
                System.out.println("\n" + joueurs.get(i).getNom() + ", a vous de jouer. Faites un choix");
                Table.menuChoixHit();
                choix = Blackjack.readInt();
                if (choix == 1) {
                    donnerCarte(joueurs.get(i));

                } else {
                    tirer = false;
                }
            }
        }
        //croupier
        pioche16Reste17();
    }

    /**
     * Simule le pioche a 16 et reste a 17
     */
    public void pioche16Reste17() {
        while (croupier.getMain().somme() < 17) {
            donnerCarte(croupier);
        }
    }

    /**
     * Calcul les gagnants et les perdants du jeu
     */
    public void resultat() {
        int pointCroupier = croupier.getMain().somme();
        ArrayList<Joueur> gagnants = new ArrayList<Joueur>();
        ArrayList<Joueur> perdants = new ArrayList<Joueur>();
        if (pointCroupier > 21) { // le croupier depasse 21 point alors tous les joueurs restant gagnent
            gagnants = joueurs;
        } else {//sinon les joueurs dont le nombre de point depasse celui du croupier perdent
            for (int i = 0; i < joueurs.size(); i++) {
                Joueur j = joueurs.get(i);
                /**
                 * on elimine les perdants et le croupier recupère les mises des perdants
                 */
                if (j.getMain().somme() < pointCroupier || j.getMain().somme() > 21) {
                    perdants.add(j);
                    double budgetCroupier = croupier.getBudget();
                    croupier.setBudget(budgetCroupier + j.getMise());
                     //supprimerJoueur(j);
                } else {
                    if (j.isAssurance()) {
                        double miseJoueur = j.getMise();
                        double miseInitiale = (3 * miseJoueur / 2);
                        double miseAssu = miseInitiale / 2;
                        if (pointCroupier == 21) {
                            joueurs.get(i).setBudget(joueurs.get(i).getBudget() + miseAssu * 2);

                        } else {
                            double budgetCroupier = croupier.getBudget();
                            croupier.setBudget(budgetCroupier + miseAssu);
                        }
                    } else if (j.getMain().getCartes().size() == 2 && j.getMain().somme() == 21) { // si blackjack
                        joueurs.get(i).setBudget(j.getBudget() + j.getMise() * 1.5 + j.getMise());
                    } else if (j.getMain().somme() == pointCroupier) { // les joueurs ayant les memes point que le croupier recupèrent leurs mise
                        joueurs.get(i).setBudget(j.getBudget() + j.getMise());
                    } else { // simple gagnant
                        joueurs.get(i).setBudget(j.getBudget() + j.getMise() * 2);
                    }
                                     
                    gagnants.add(joueurs.get(i));
                }
                joueurs.get(i).setMise(0);
                croupier.setMise(0);
                
            }
        }
        System.out.println(this);
        System.out.println("**********$$$$$ RESULTAT $$$$$*********");
        if (!gagnants.isEmpty()) {
            System.out.println("**********---- GAGNANTS ----*********");
            for (int i = 0; i < gagnants.size(); i++) {
                System.out.println(gagnants.get(i));
            }
        } else {
            System.out.println("**********---- LE CROUPIER GAGNE ----*********");
            System.out.println(croupier);
        }
        if (!perdants.isEmpty()) {
            System.out.println("**********---- PERDANTS ----*********");
            for (int i = 0; i < perdants.size(); i++) {
                System.out.println(perdants.get(i));
            }
        } else {
            System.out.println("**********---- LE CROUPIER EST PERDANT ----*********");
            System.out.println(croupier);

        }
    }

    @Override
    public String toString() {
        String retour = "***********$$$$$$$ TABLE $$$$$$$*************\n";
        retour += "Le sabot contient: " + cartes.size() + " cartes\n";
        retour += "La defausse contient: " + defausse.size() + " cartes\n";
        retour += croupier.toString() + "\n";
        double totalMises = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            totalMises += joueurs.get(i).getMise();
            retour += joueurs.get(i).toString() + "\n";
        }
        retour += "Total des mises: " + totalMises + " euro(s)\n";
        return retour;
    }

    /**
     * Defausse la première carte du jeu
     */
    public void defausserUneCarte() {
        if (cartes.size() > 0) {
            defausse.add(cartes.get(0)); // ajout la de premiere carte a la defausse
            cartes.remove(0); // suppression de la premiere carte des cartes du jeu
        }
    }

    /**
     * Donne la premiere carte du jeu a un joueur
     * @param j le joueur demandeur
     */
    public void donnerCarte(Joueur j) {
        j.ajouterCarte(cartes.get(0));
        cartes.remove(0);
    }

    /**
     * Cree le sabot: Melange les cartes du jeu puis defausse les 5 premieres carte
     */
    public void creerSabot() {
        this.melanger();
        for (int i = 0; i < 5; i++) {
            this.defausserUneCarte();
        }
    }

    /**
     * Debarasse la table 
     * Verifie que le sabot contient moins de 52 cartes. Si oui on recupere la carte de la defausse;
     * 
     */
    public void validerTour() {
        debarrasser();
        if (cartes.size() < 52) {
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

    /**
     * Creer un jeu de 52 carte
     */
    public static ArrayList<Carte> creer52Cartes() {
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        //Cartes As
        Carte asTreifle = new Carte("As de treifle", 11);
        Carte asCarreau = new Carte("As de carreau", 11);
        Carte asCoeur = new Carte("As de coeur", 11);
        Carte asPique = new Carte("As de pique", 11);
        //Ajout As
        cartes.add(asTreifle);
        cartes.add(asCarreau);
        cartes.add(asCoeur);
        cartes.add(asPique);

        //Cartes deux
        Carte deuxTreifle = new Carte("Deux de treifle", 2);
        Carte deuxCarreau = new Carte("Deux de carreau", 2);
        Carte deuxCoeur = new Carte("Deux de coeur", 2);
        Carte deuxPique = new Carte("Deux de pique", 2);
        //Ajout deux
        cartes.add(deuxTreifle);
        cartes.add(deuxCarreau);
        cartes.add(deuxCoeur);
        cartes.add(deuxPique);

        //Cartes trois
        Carte troisTreifle = new Carte("Trois de treifle", 3);
        Carte troisCarreau = new Carte("Trois de carreau", 3);
        Carte troisCoeur = new Carte("Trois de coeur", 3);
        Carte troisPique = new Carte("Trois de pique", 3);
        //Ajout trois
        cartes.add(troisTreifle);
        cartes.add(troisCarreau);
        cartes.add(troisCoeur);
        cartes.add(troisPique);

        //Cartes quatre
        Carte quatreTreifle = new Carte("Quatre de treifle", 4);
        Carte quatreCarreau = new Carte("Quatre de carreau", 4);
        Carte quatreCoeur = new Carte("Quatre de coeur", 4);
        Carte quatrePique = new Carte("Quatre de pique", 4);
        //Ajout quatre
        cartes.add(quatreTreifle);
        cartes.add(quatreCarreau);
        cartes.add(quatreCoeur);
        cartes.add(quatrePique);

        //Cartes cinq
        Carte cinqTreifle = new Carte("Cinq de treifle", 5);
        Carte cinqCarreau = new Carte("Cinq de carreau", 5);
        Carte cinqCoeur = new Carte("Cinq de coeur", 5);
        Carte cinqPique = new Carte("Cinq de pique", 5);
        //Ajout cinq
        cartes.add(cinqTreifle);
        cartes.add(cinqCarreau);
        cartes.add(cinqCoeur);
        cartes.add(cinqPique);

        //Cartes six
        Carte sixTreifle = new Carte("Six de treifle", 6);
        Carte sixCarreau = new Carte("Six de carreau", 6);
        Carte sixCoeur = new Carte("Six de coeur", 6);
        Carte sixPique = new Carte("Six de pique", 6);
        //Ajout six
        cartes.add(sixTreifle);
        cartes.add(sixCarreau);
        cartes.add(sixCoeur);
        cartes.add(sixPique);

        //Cartes sept
        Carte septTreifle = new Carte("Sept de treifle", 7);
        Carte septCarreau = new Carte("Sept de carreau", 7);
        Carte septCoeur = new Carte("Sept de coeur", 7);
        Carte septPique = new Carte("Sept de pique", 7);
        //Ajout sept
        cartes.add(septTreifle);
        cartes.add(septCarreau);
        cartes.add(septCoeur);
        cartes.add(septPique);

        //Cartes huit
        Carte huitTreifle = new Carte("Huit de treifle", 8);
        Carte huitCarreau = new Carte("Huit de carreau", 8);
        Carte huitCoeur = new Carte("Huit de coeur", 8);
        Carte huitPique = new Carte("Huit de pique", 8);
        //Ajout huit
        cartes.add(huitTreifle);
        cartes.add(huitCarreau);
        cartes.add(huitCoeur);
        cartes.add(huitPique);

        //Cartes neuf
        Carte neufTreifle = new Carte("Neuf de treifle", 9);
        Carte neufCarreau = new Carte("Neuf de carreau", 9);
        Carte neufCoeur = new Carte("Neuf de coeur", 9);
        Carte neufPique = new Carte("Neuf de pique", 9);
        //Ajout neuf
        cartes.add(neufTreifle);
        cartes.add(neufCarreau);
        cartes.add(neufCoeur);
        cartes.add(neufPique);
        //Cartes dix
        Carte dixTreifle = new Carte("Dix de treifle", 10);
        Carte dixCarreau = new Carte("Dix de carreau", 10);
        Carte dixCoeur = new Carte("Dix de coeur", 10);
        Carte dixPique = new Carte("Dix de pique", 10);
        //Ajout dix
        cartes.add(dixTreifle);
        cartes.add(dixCarreau);
        cartes.add(dixCoeur);
        cartes.add(dixPique);

        //Cartes valet
        Carte valetTreifle = new Carte("Valet de treifle", 10);
        Carte valetCarreau = new Carte("Valet de carreau", 10);
        Carte valetCoeur = new Carte("Valet de coeur", 10);
        Carte valetPique = new Carte("Valet de pique", 10);
        //Ajout valet
        cartes.add(valetTreifle);
        cartes.add(valetCarreau);
        cartes.add(valetCoeur);
        cartes.add(valetPique);
        //Cartes dame
        Carte dameTreifle = new Carte("Dame de treifle", 10);
        Carte dameCarreau = new Carte("Dame de carreau", 10);
        Carte dameCoeur = new Carte("Dame de coeur", 10);
        Carte damePique = new Carte("Dame de pique", 10);

        //Ajout dame
        cartes.add(dameTreifle);
        cartes.add(dameCarreau);
        cartes.add(dameCoeur);
        cartes.add(damePique);
        //Cartes roi
        Carte roiTreifle = new Carte("Roi de treifle", 10);
        Carte roiCarreau = new Carte("Roi de carreau", 10);
        Carte roiCoeur = new Carte("Roi de coeur", 10);
        Carte roiPique = new Carte("Roi de pique", 10);
        //Ajout roi
        cartes.add(roiTreifle);
        cartes.add(roiCarreau);
        cartes.add(roiCoeur);
        cartes.add(roiPique);

        return cartes;

    }
}
