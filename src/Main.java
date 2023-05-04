import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Jeu jeu = new Jeu();
        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");
        int round = 1;

        jeu.distribuerCartes(joueur1, joueur2);
        while(joueur1.aDesCartes() && joueur2.aDesCartes()) {

            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nRound " + round + ":" + ConsoleColors.RESET);
            Carte carte1 = joueur1.playCarte();
            Carte carte2 = joueur2.playCarte();

            System.out.println("(" + (joueur1.getCartes().size() + 1) + ") " + joueur1.getNom() + ": " + carte1.getNom());
            System.out.println("(" + (joueur2.getCartes().size() + 1) + ") " + joueur2.getNom() + ": " + carte2.getNom());

            if(carte1.getValeur() > carte2.getValeur()) {
                joueur1.addCarte(carte1);
                joueur1.addCarte(carte2);
                System.out.println(ConsoleColors.CYAN + joueur1.getNom() + " remporte le round!" + ConsoleColors.RESET);

            } else if(carte2.getValeur() > carte1.getValeur()) {
                joueur2.addCarte(carte1);
                joueur2.addCarte(carte2);
                System.out.println(ConsoleColors.CYAN + joueur2.getNom() + " remporte le round!" + ConsoleColors.RESET);

            } else {
                // Bataille
                List<Carte> enjeu = new ArrayList<>();
                enjeu.add(carte1);
                enjeu.add(carte2);

                boolean finDeBataille = false;
                while (!finDeBataille) {
                    for (int i = 0; i < 3; i++) {
                        if (joueur1.aDesCartes() && joueur2.aDesCartes()) {
                            enjeu.add(joueur1.playCarte());
                            enjeu.add(joueur2.playCarte());
                        } else {
                            finDeBataille = true;
                            break;
                        }
                    }

                    if (!finDeBataille) {
                        carte1 = joueur1.playCarte();
                        carte2 = joueur2.playCarte();

                        System.out.println(ConsoleColors.RED_BOLD + "\nBataille!" + ConsoleColors.RESET);

                        if(carte1 == null) {
                            System.out.println(ConsoleColors.CYAN + joueur1.getNom() + " n'a plus de cartes et perd la bataille!" + ConsoleColors.RESET);
                            break;
                        } else if(carte2 == null) {
                            System.out.println(ConsoleColors.CYAN + joueur2.getNom() + " n'a plus de cartes et perd la bataille!" + ConsoleColors.RESET);
                            break;
                        } else {
                            enjeu.add(carte1);
                            enjeu.add(carte2);
                        }

                        System.out.println("(" + (joueur1.getCartes().size() + 1) + ") " + joueur1.getNom() + ": " + carte1.getNom());
                        System.out.println("(" + (joueur2.getCartes().size()  + 1) + ") " + joueur2.getNom() + ": " + carte2.getNom());

                        if (carte1.getValeur() > carte2.getValeur()) {
                            joueur1.addCartes(enjeu);
                            System.out.println(ConsoleColors.CYAN + joueur1.getNom() + " remporte la bataille!" + ConsoleColors.RESET);
                            finDeBataille = true;
                        } else if (carte2.getValeur() > carte1.getValeur()) {
                            joueur2.addCartes(enjeu);
                            System.out.println(ConsoleColors.CYAN + joueur2.getNom() + " remporte la bataille!" + ConsoleColors.RESET);
                            finDeBataille = true;
                        }
                    }
                }
            }

            round++;
            Thread.sleep(500); // Pour avoir un petit moment d'attente entre chaque round (plus immersif)
        }

        if(joueur1.aDesCartes()) {
            System.out.println(ConsoleColors.GREEN_BOLD + "\n" + joueur1.getNom() + " a gagné la partie!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "\n" + joueur2.getNom() + " a gagné la partie!" + ConsoleColors.RESET);
        }
    }
}