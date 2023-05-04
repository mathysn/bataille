import java.util.*;

public class Joueur {
    private String nom;
    private List<Carte> cartes;

    public Joueur(String nom) {
        this.nom = nom;
        this.cartes = new ArrayList<Carte>();
    }

    public void addCarte(Carte carte) {
        cartes.add(carte);
    }

    public void addCartes(List<Carte> cartes) {
        this.cartes.addAll(cartes);
        cartes.clear();
    }

    public boolean aDesCartes() {
        return !cartes.isEmpty();
    }

    public Carte playCarte() {
        if(this.cartes.isEmpty()) {
            return null;
        }
        return cartes.remove(0);
    }

    public String getNom() {
        return this.nom;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cartes);
    }

    public List<Carte> getCartes() {
        return this.cartes;
    }
}
