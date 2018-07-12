package tbarlow.controller;

import tbarlow.model.artefacts.Armour;
import tbarlow.model.artefacts.Artefact;
import tbarlow.model.artefacts.Helm;
import tbarlow.model.artefacts.Weapon;
import tbarlow.model.characters.Enemy;
import tbarlow.model.characters.Hero;
import tbarlow.model.map.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

    public Gameplay() {

    }

    public int Run(Map map) {

        List<Enemy> enemyList = map.getEnemyList();
        Hero hero = map.getHero();
        Scanner scanner = new Scanner(System.in);
        //System.out.println("*HP* " + healthPotion.x + " : " + healthPotion.y);
        for (Enemy enemy: enemyList) {
            System.out.println(enemy.getName() + ": " + enemy.getX() + " " + enemy.getY());
        }

        System.out.println("----" + hero.getName().toUpperCase() + "----");

        System.out.println("HEALTH: " + hero.getHp() + "/" + hero.getMaxhp());
        System.out.println("X: " + hero.getX());
        System.out.println("Y: " + hero.getY());

//        System.out.println("NORTH - w");
//        System.out.println("WEST - a");
//        System.out.println("SOUTH - s");
//        System.out.println("EAST - d");

        System.out.println("more info: type 'stat'");
        String input = scanner.nextLine();


        switch (input) {
            case "w":
                map.moveNorth();
                break;
            case "s":
                map.moveSouth();
                break;
            case "d":
                map.moveEast();
                break;
            case "a":
                map.moveWest();
                break;
            case "stat" :
                printHeroStats(hero);
                break;
            case "exit":
                return (-1);
            default:
                System.out.println("Invalid input");
        }
        return 0;
    }

    public Enemy EnemyFound(Hero hero, List<Enemy> enemyList) {
        for (Enemy enemy: enemyList) {
            if (hero.getX() == enemy.getX() && hero.getY() == enemy.getY()) {
                System.out.println(hero.getX() + ":" + enemy.getX() + " AND " + hero.getY() + ":" + enemy.getY());
                return enemy;
            }
        }
        return null;
    }

    public int Fight(Hero hero, Enemy enemy) {
        Utilities utils = new Utilities();
        String[] moves = {"attack", "knock", "whack", "slam", "wallop", "stab"};

        Random rand = new Random();

        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            int herodmg = hero.getAttack() - enemy.getDefense();
            int enemydmg = enemy.getAttack() - hero.getDefense();
            System.out.println("You " + moves[rand.nextInt(6)] + " " + enemy.getName() + " and deal " + herodmg);
            enemy.takeDamage(herodmg);
            if (enemy.getHp() == 0) {
                break;
            }

            utils.sleep(1000);

            System.out.println(enemy.getName() + " " + moves[rand.nextInt(6)] + "s you and deals " + enemydmg);
            hero.takeDamage(enemydmg);

            utils.sleep(1000);

            System.out.println('\n' + hero.getName() + ": " + hero.getHp() + "     VS     "
                    + enemy.getName() + ": " + enemy.getHp() + '\n');
        }
        if (enemy.getHp() == 0) {
            ArtefactDropChance(hero);
            int xpGained = enemy.getAttack() * enemy.getMaxhp();
            int levelledUp = hero.setXp(xpGained);
            if (levelledUp == 1) {
                System.out.println("You've defeated the monster and levelled up!");
                return (1);
            } else {
                System.out.println("You've defeated the monster and gained " + xpGained +"xp!");
                return (0);
            }
        }
        else {
            return -1;
        }
    }

    public int Flight(Hero hero, Enemy enemy) {
        Random rand = new Random();
        int chance = rand.nextInt(100);

        if (chance > 50) {
            //Failed run away
            hero.takeDamage(enemy.getAttack());
            System.out.println("The enemy spots you and attacks first, dealing " + enemy.getAttack() + " damage! You have to fight!");
            return (Fight(hero, enemy));
        }
        else {
            System.out.println("You managed to slip past the enemy unnoticed.");
            return 2;
        }
    }

    public void printHeroStats(Hero hero) {
        System.out.println("----HERO STATS----");
        System.out.println("ATTACK: " + hero.getAttack());
        System.out.println("DEFENSE: " + hero.getDefense());
        System.out.println("LEVEL: " + hero.getLevel());
        System.out.println("XP: " + hero.getXp() + "/" + hero.getXpNeeded());
        printEquipment(hero);
        System.out.println("EXIT - exit");
    }

    private void printEquipment(Hero hero) {
        if (hero.getArmour() != null || hero.getHelm() != null || hero.getWeapon() != null) {
            System.out.println("----EQUIPMENT----");
            if (hero.getHelm() != null) {
                System.out.println(hero.getHelm().getName() + ": " + hero.getHelm().getAmount() + "hp");
            }
            if (hero.getArmour() != null) {
                System.out.println(hero.getArmour().getName() + ": " + hero.getArmour().getAmount() + "def");
            }
            if (hero.getWeapon() != null) {
                System.out.println(hero.getWeapon().getName() + ": " + hero.getWeapon().getAmount() + "att");
            }
            System.out.println("-----------------");
        }
    }

    private void ArtefactDropChance(Hero hero) {
        Random rand = new Random();
        int chance = rand.nextInt(10) + 1;

        if (chance > 1 && chance < 5) {
            Artefact[] artefacts = {new Weapon(hero.getLevel()), new Helm(hero.getLevel()), new Armour(hero.getLevel())};
            Artefact foundArtefact = artefacts[chance - 2];
            System.out.println("You have found a " + foundArtefact.getName());
            String boost = "";
            switch (foundArtefact.getType()) {
                case "Helm" :
                    boost = " hp";
                    break;
                case "Armour" :
                    boost = " defense";
                    break;
                case "Weapon" :
                    boost = " attack";
                    break;
            }
            System.out.println("+" + foundArtefact.getAmount() + boost);
            if (YesNo("Would you like to equip it? y/n")) {
                Equip(hero, foundArtefact);
            }
        }
    }

    public boolean YesNo(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String input = scanner.nextLine();
        if (input.equals("y")) {
            return true;
        }
        else if (input.equals("n")) {
            return false;
        }
        return false;
    }

    private void Equip(Hero hero, Artefact artefact) {
        switch (artefact.getType()) {
            case "Helm" :
                hero.setHelm((Helm) artefact);
                break;
            case "Armour" :
                hero.setArmour((Armour) artefact);
                break;
            case "Weapon" :
                hero.setWeapon((Weapon) artefact);
                break;
        }
    }
}
