package tbarlow.model.characters;

public class Thief extends Hero {

    public Thief() {
        setDetails();
    }

    public Thief(String name) {
        //NEW GAME
        super(name, 15, 1, 80, 80, 0, 1);
        setDetails();
    }

    public Thief(String name, int attack, int defense, int hp, int maxhp, int xp, int level) {
        super(name, attack, defense, hp, maxhp, xp, level);
        setDetails();
    }

    public void setDetails() {
        description = "The Thief has a higher chance of successfully fleeing from " +
                "an encounter, and has a chance of pickpocketing the enemy.";
        heroType = "Thief";
    }

}
