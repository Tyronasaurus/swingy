package tbarlow.model.characters;

public class Scout extends Hero{

    public Scout() {
        setDetails();
    }

    public Scout(String name) {
        //NEW GAME
        super(name, 10, 3, 100, 100, 0, 1);
        setDetails();
    }

    public Scout(String name, int attack, int defense, int hp, int maxhp, int xp, int level) {
        super(name, attack, defense, hp, maxhp, xp, level);
        setDetails();
    }

    public void setDetails() {
        description = "The Scout has an eagle which spots and pinpoints the location " +
                "of all enemies on the map.";
        heroType = "Scout";
    }

}
