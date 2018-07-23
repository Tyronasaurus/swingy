package tbarlow.model.characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Character {
    
    @NotNull
    protected String name;
    
    @Min(0)
    protected int attack;

    @Min(0)
    protected int defense;

    @Min(0)
    protected int hp;

    @Min(0)
    protected int maxhp;

    @Min(0)
    protected int x;

    @Min(0)
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
    
    public void takeDamage( @Min(0) int damage) {

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
