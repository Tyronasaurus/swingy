package tbarlow.model.artefacts;

import tbarlow.model.characters.Hero;
import tbarlow.model.map.Map;

import java.util.Random;

public class HealthPotion {
    public int healAmount;
    public int x;
    public int y;

    public HealthPotion() {
        healAmount = 0;
    }

    public HealthPotion(int mapX, int mapY) {
        Random rand = new Random();

        healAmount = rand.nextInt(100);
        x = rand.nextInt(mapX - 1 );
        y = rand.nextInt(mapY - 1);
    }

    public void CheckHeal(Hero hero) {
        if (hero.getX() == x && hero.getY() == y) {
            hero.Heal(healAmount);
            System.out.println("You found the secret health potion and were healed for " + healAmount);
        }
    }
}
