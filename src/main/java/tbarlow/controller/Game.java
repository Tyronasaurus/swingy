package tbarlow.controller;

import com.sun.org.apache.bcel.internal.generic.Select;
import tbarlow.model.artefacts.HealthPotion;
import tbarlow.model.characters.*;
import tbarlow.model.map.Map;

import java.util.List;
import java.util.Scanner;
import tbarlow.view.MainMenu;

public class Game {

    private Utilities utils = new Utilities();
    private Scanner scanner = new Scanner(System.in);
    private Gameplay gameplay = new Gameplay();
    Map map = new Map();
    Hero hero;

    public static void main(String[] args) {

        if (args[0].equals("gui")) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        }
        else if(args[0].equals("cli")) {
            new Game();
        }
        
        

    }

    public Game() {
        loadHero();
        while (hero == null) {
            loadHero();
        }

        map.newLevel(hero);

        List<Enemy> enemyList = map.getEnemyList();

        HealthPotion healthPotion = new HealthPotion(map.mapX, map.mapY);
        //GAME LOOP
        while (gameplay.Run(map) != -1) {

            Enemy enemyFound = gameplay.EnemyFound(hero, enemyList);
            if (enemyFound != null) {
                int FoF = FightOrFlight(enemyFound);
                while (FoF == -1) {
                    FoF = FightOrFlight(enemyFound);
                }
                if (FoF == 1) {
                    //FIGHT
                    int fightResult = gameplay.Fight(hero, enemyFound);
                    if ( fightResult != -1) {
                        enemyList.remove(enemyFound);
                    }
                    if (fightResult == 1) {
                        levelStat(hero);
                        map.newLevel(hero);

                        System.out.println("You are now level " + hero.getLevel() + ". New enemies lurk in the shadows");
                        System.out.println("Standing in the middle of an area, " + map.mapX + "x" + map.mapY + " in size");
                    }
                } else if (FoF == 0) {
                    //FLIGHT
                    int flightResult = gameplay.Flight(hero, enemyFound);
                    if ( flightResult != -1 && flightResult != 2) {
                        enemyList.remove(enemyFound);
                    }
                }
            }
            healthPotion.CheckHeal(hero);

            if (!map.insideMap()) {
                System.out.println("You have left the map and thus abandoned the quest for blood");
                utils.sleep(1000);
                if (gameplay.YesNo("Would you like to save your progress? y/n")) {

                    System.out.println("Your game is being saved...");
                    SaveGame saveGame = new SaveGame();
                    if (saveGame.SaveToFile(hero)) {
                        System.out.println("HAZAAR!");
                    } else {
                        System.out.println("Fuck something broke lol");
                    }
                }
                break;
            }

            if (hero.getHp() == 0) {
                System.out.println("You have died");
                break;
            }
        }
        System.out.println("You've exited the game");
    }

    private void loadHero() {
        scanner = new Scanner(System.in);
        LoadGame loadGame = new LoadGame();
        List<Hero> heroList = loadGame.LoadHeroList("heroes.txt");
        if (heroList.size() == 0) {
            return;
        }
        System.out.println("A load game has been found. Please select the character you'd like to load:");
        int i = 1;
        for (Hero hero: heroList) {
            System.out.println(i + ": " + hero.getName() + " the " + hero.heroType);
            i++;
        }
        System.out.println(i + ": New Game");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            if (index <= 0 || index > heroList.size()) {
                System.out.println("Selected number is invalid");
                loadHero();
            }
            if (index == i) {
                newHero();
            }
            hero = heroList.get(index - 1);
        }
    }

    private void newHero() {
        scanner = new Scanner(System.in);
        System.out.println("Welcome hero to your destiny!");
        utils.sleep(1000);
        System.out.println("Tell me, what do you go by?");
        utils.sleep(1000);
        System.out.println("Enter your name: ");

        String name = scanner.nextLine();

        hero = new Hero(name);

        System.out.println("Very well " +  name + ", and your speciality is...?");

        utils.sleep(1000);

        System.out.println("PLEASE TYPE THE NAME OF THE DESIRED CLASS: ");

        SelectClass(name);

        utils.sleep(1000);

        int enemyCount = hero.getLevel() * 4;

        System.out.println(hero.getName() + " the " + hero.heroType + ", you begin in the middle of a marsh, " + enemyCount +
                " enemies lurk these lands. BEWARE!");

        return;
    }

    private void SelectClass(String name) {
        scanner = new Scanner(System.in);

        Hero[] heroes = {new Thief(name), new Warmonger(name), new Scout(name)};

        for (Hero heroClass: heroes) {
            System.out.println(heroClass.heroType + " - " + heroClass.description);
        }

        String input = scanner.nextLine();
        for (Hero heroClass: heroes) {
            if (input.equalsIgnoreCase(heroClass.heroType)) {
                hero = heroClass;
                System.out.println("You looked like a " + hero.heroType + " when I laid my eye sockets on you!");
            }
        }
        if (hero.heroType == null) {
            SelectClass(name);
        }

    }


    private void levelStat(Hero hero) {
        System.out.println("Please choose an attribute to level up: ");
        System.out.println("a = attack + 10");
        System.out.println("d = defense + 5");
        String input = scanner.nextLine();

        switch (input) {
            case "a":
                hero.setAttack(hero.getAttack() + 10);
                break;
            case "d":
                hero.setDefense(hero.getDefense() + 5);
                break;
            default:
                levelStat(hero);
        }
    }

    public int FightOrFlight(Enemy enemy) {
        System.out.println("You have spotted a " + enemy.getName() + "!");
        System.out.println("Enemy HP: " + enemy.getHp());
        System.out.println("Enemy Attack: " + enemy.getAttack());
        System.out.println("Enemy Defense: " + enemy.getDefense());

        System.out.println("FIGHT or FLIGHT?");

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("fight") ) {
            return 1;
        }
        else if (input.equalsIgnoreCase("flight")) {
            return 0;
        }
        System.out.println("There's not enough time! Please select FIGHT or FLIGHT");
        return -1;
    }
}
