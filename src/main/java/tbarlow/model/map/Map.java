package tbarlow.model.map;

import tbarlow.model.characters.Enemy;
import tbarlow.model.characters.Hero;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public int mapX;
    public int mapY;

    private static int enemyCount;

    private Hero hero;

    private List<Enemy> enemyList = new ArrayList<>();

    public Map() {
        //Default lvl 1 map
        mapX = 0;
        mapY = 0;
    }

    public void newLevel(Hero hero) {
        this.hero = hero;

        enemyCount = 0;

        mapX = ((hero.getLevel() - 1) * 5) + 10 - (hero.getLevel() % 2);
        mapY = mapX;

        hero.setX((mapX/2) + 1);
        hero.setY((mapY/2) + 1);

        EnemyFactory();
    }

    public boolean insideMap() {
        if (hero.getX() > this.mapX)
            return false;
        if (hero.getX() < 1)
            return false;
        if (hero.getY() > this.mapY)
            return false;
        if (hero.getY() < 1)
            return false;
        return true;
    }

    public void moveNorth() {
        hero.setY(hero.getY() - 1);
    }

    public void moveSouth() {
        hero.setY(hero.getY() + 1);
    }

    public void moveEast() {
        hero.setX(hero.getX() + 1);
    }

    public void moveWest() {
        hero.setX(hero.getX() - 1);
    }

    public List<Enemy> getEnemyList() {
        return this.enemyList;
    }

    public Hero getHero() {
        return this.hero;
    }

    private void EnemyFactory() {

        while (enemyCount < hero.getLevel() * 4) {
            Enemy newEnemy = new Enemy();
            newEnemy.setSpawn(this);
            if (newEnemy.getX() != hero.getX() && newEnemy.getY() != hero.getY()) {
                enemyList.add(newEnemy);
                enemyCount++;
            }
            else {
                EnemyFactory();
            }
        }
    }
}
