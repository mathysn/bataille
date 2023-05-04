import java.util.*;

public class Jeu {
    private List<Carte> deck;

    public Jeu() {
        this.deck = new ArrayList<Carte>();
        String[] couleurs = {"Pique", "Coeur", "Carreau", "Trèfle"};
        String[] valeurs = {"7", "8", "9", "10", "Valet", "Dame", "Roi", "As"};

        for(String couleur : couleurs) {
            for(int i = 0; i < valeurs.length; i++) {
                int valeur = i + 7;
                deck.add(new Carte(valeurs[i] + " de " + couleur, valeur));
            }
        }
        Collections.shuffle(deck);
    }

    public void distribuerCartes(Joueur joueur1, Joueur joueur2) {
        while(!deck.isEmpty()) {
            joueur1.addCarte(deck.remove(0));
            joueur2.addCarte(deck.remove(0));
        }
    }
}
