package tbarlow.model.characters;


import tbarlow.model.artefacts.*;
import javax.validation.constraints.Min;

public class Hero extends Character {

    public String description;
    public String heroType;

    @Min(0)
    public int xp = 0;

    private int level = 0;

    private Armour armour;
    private Weapon weapon;
    private Helm helm;

    public Hero() {
        this("Hero");
    }

    public Hero(String name) {
        super(name, 10, 0, 100, 100);
        this.level = 1;
        this.xp = 0;
    }

    public Hero(String name, int attack, int defense, int hp, int maxhp, int xp, int level) {
        super(name, attack, defense, hp, maxhp);
        this.xp = xp;
        this.level = level;
    }

    public int setXp(int xp) {
        this.xp += xp;
        int xpneeded = (int) (this.level * 1000 + (Math.pow(this.level -1  ,2) * 450));
        if (this.xp >= xpneeded) {
            levelUp(1);
            this.xp -= xpneeded;
            return (1);
        }
        return (0);
    }

    public Armour getArmour() {
        return armour;
    }

    public Helm getHelm() {
        return helm;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setArmour(Armour armour) {
        if (this.armour != null)
            this.defense -= this.armour.getAmount();
        this.armour = armour;
        this.defense += armour.getAmount();
    }

    public void setHelm(Helm helm) {
        if (this.helm != null) {
            this.maxhp -= this.helm.getAmount();
            this.hp -= this.helm.getAmount();
        }
        this.helm = helm;
        this.maxhp += helm.getAmount();
        this.hp += helm.getAmount();
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack -= this.weapon.getAmount();
        }
        this.weapon = weapon;
        this.attack += weapon.getAmount();
    }

    public int getLevel() {
        return this.level;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpNeeded() {
        return  ((int) (this.level * 1000 + (Math.pow(this.level -1  ,2) * 450)));
    }

    public void levelUp(int level) {
        this.level += level;
        this.setHpMax(this.maxhp + 20);
    }

    public void Heal(int amount) {
        this.hp += amount;
    }
}
