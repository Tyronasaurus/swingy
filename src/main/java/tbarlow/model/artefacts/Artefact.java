package tbarlow.model.artefacts;

public class Artefact {

    protected String name;

    protected int level;

    protected int amount;

    protected String type;

    public Artefact() {
        name = "Artefact";
        level = 0;
        amount = 0;
    }

    public Artefact(String name, int level) {
        this.name = name;
        this.level = level;
        amount = 0;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}
