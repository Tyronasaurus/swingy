package tbarlow.model.characters;

public class Character {

    protected String name;

    protected int attack;

    protected int defense;

    protected int hp;

    protected int maxhp;

    protected int x;

    protected int y;

    public Character() {

    }

    public Character(String name, int attack, int defense, int hp, int maxhp) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.maxhp = maxhp;
    }


    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMaxhp() {
        return this.maxhp;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            damage = 0;
        }
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void setHpMax(int hp) {
        this.maxhp = hp;
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
