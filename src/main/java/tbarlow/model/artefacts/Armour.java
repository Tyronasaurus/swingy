package tbarlow.model.artefacts;

import java.util.Random;

public class Armour extends Artefact{
    public Armour() {
        super("Armour", 0);
        amount = 0;
        this.type = "Armour";
    }

    public Armour(int level) {
        String[] prefix = {"Rusty ", "Dented ", "Shiny ", "Bloodied ", "Tie Dye "};
        String[] names = {"Breastplate", "Cuirass", "Pauldrons", "Steel Plate", "Tuxedo"};

        this.level = level;
        Random rand = new Random();
        this.amount = (rand.nextInt(10) + 1) * level;

        this.name = prefix[rand.nextInt(4)] + names[rand.nextInt(4)];
        this.type = "Armour";
    }

    public Armour(String name, int amount) {
        this.name = name;
        this.level = 1;
        this.amount = amount;
    }
}
