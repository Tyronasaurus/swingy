package tbarlow.model.characters;


import tbarlow.model.map.Map;

import java.util.Random;

public class Enemy extends Character {

    private String[] prefix = {"Arch ", "Evil ", "Cursed ", "Wicked ", "", "Foul ", "Unholy "};

    private String[] names = {"Goblin", "Troll", "Wyvern", "Murloc", "Warlock", "Basilisk", "Gargoyle", "Werewolf", "Chimaera"};

    public Enemy() {
        Random rand = new Random();

        String enemyType = prefix[rand.nextInt(6)] + names[rand.nextInt(8)];
        int enemyAttack = rand.nextInt(11 ) + 5;
        int enemyDefense = rand.nextInt(5);
        int enemyHp = rand.nextInt(40) + 23;

        this.name = enemyType;
        this.attack = enemyAttack;
        this.defense = enemyDefense;
        this.hp = enemyHp;
        this.maxhp = enemyHp;
    }

    public void setSpawn(Map map) {
        Random rand = new Random();

        this.setX(rand.nextInt(map.mapX) + 1);
        this.setY(rand.nextInt(map.mapY) + 1);
    }

}
