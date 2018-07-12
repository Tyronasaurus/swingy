package tbarlow.model.artefacts;

import java.util.Random;

public class Weapon extends Artefact{
    public Weapon() {
        super("Weapon", 0);
        amount = 0;
        this.type = "Weapon";
    }

    public Weapon(int level) {
        String[] prefix = {"Sharp ", "Jewelled ", "Short ", "Heavenly ", "Long "};
        String[] names = {"Sword", "Cutlass", "Shiv", "Spear", "Dildo"};


        this.level = level;
        Random rand = new Random();
        this.amount = (rand.nextInt(10) + 1) * level;

        this.name = prefix[rand.nextInt(4)] + names[rand.nextInt(4)];
        this.type = "Weapon";
    }

    public Weapon(String name, int amount) {
        this.name = name;
        this.level = 1;
        this.amount = amount;
    }
}
