package tbarlow.model.artefacts;

import java.util.Random;

public class Helm extends Artefact{
    public Helm() {
        super("Helm", 0);
        amount = 0;
        type = "Helm";
    }

    public Helm(int level) {

        String[] prefix = {"Over-sized ", "Sweaty ", "Great ", "Jagged ", "Pristine "};
        String[] names = {"Hood", "Helm", "Basinet", "Barbute", "Horse Head"};

        this.level = level;
        Random rand = new Random();
        this.amount = (rand.nextInt(10) + 1) * level;

        this.name = prefix[rand.nextInt(4)] + names[rand.nextInt(4)];
        this.type = "Helm";
    }

    public Helm(String name, int amount) {
        this.name = name;
        this.level = 1;
        this.amount = amount;
    }
}
