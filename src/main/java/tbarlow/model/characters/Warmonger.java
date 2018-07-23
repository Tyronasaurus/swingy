package tbarlow.model.characters;

public class Warmonger extends Hero{

    public Warmonger() {
        setDetails();
    }

    public Warmonger(String name) {
        //NEW GAME
        super(name, 12, 5, 120, 120, 0, 1);
        setDetails();
    }

    public Warmonger(String name, int attack, int defense, int hp, int maxhp, int xp, int level) {
        super(name, attack, defense, hp, maxhp, xp, level);
        setDetails();
    }

    public void setDetails() {
        description = "The Warmonger is all about POWER! His bare fists and big muscles " +
                "give him good defense, and lots of health";
        heroType = "Warmonger";
    }
}
