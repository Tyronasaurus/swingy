package tbarlow.controller;

import tbarlow.model.artefacts.Armour;
import tbarlow.model.artefacts.Helm;
import tbarlow.model.artefacts.Weapon;
import tbarlow.model.characters.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {
    public LoadGame() {
    }

    public List<Hero> LoadHeroList(String fileName) {
        File file = new File(fileName);
        List<Hero> heroList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String HeroToLoad;
            while ((HeroToLoad = br.readLine()) != null) {
                heroList.add(ParseHeroString(HeroToLoad));
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return heroList;
    }

    public Hero ParseHeroString(String heroString) {
        String[] heroArray = heroString.split(",");
        String name = heroArray[0];
        int att = Integer.parseInt(heroArray[1]);
        int def = Integer.parseInt(heroArray[2]);
        int health = Integer.parseInt(heroArray[3]);
        int xp = Integer.parseInt(heroArray[4]);
        int level = Integer.parseInt(heroArray[5]);

        String artefacts[] = { heroArray[6], heroArray[7], heroArray[8] };

        Hero newHero = new Hero(name, att, def, health, health, xp, level);

        setHeroEquipment(artefacts, newHero);

        return newHero;
    }

    public void setHeroEquipment(String[] eqString, Hero hero) {
        if (!eqString[0].equals("null")) {
            //HELM
            String[] helmArr = eqString[0].split("#");
            Helm helm = new Helm(helmArr[0], Integer.parseInt(helmArr[1]));
            hero.setHelm(helm);
        }
        if (!eqString[1].equals("null")) {
            //ARMOUR
            String[] arr = eqString[1].split("#");
            Armour armour = new Armour(arr[0], Integer.parseInt(arr[1]));
            hero.setArmour(armour);
        }
        if (!eqString[2].equals("null")) {
            //WEAPON
            String[] arr = eqString[2].split("#");
            Weapon weapon = new Weapon(arr[0], Integer.parseInt(arr[1]));
            hero.setWeapon(weapon);
        }
    }
}
