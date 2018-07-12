package tbarlow.controller;

import tbarlow.model.artefacts.Artefact;
import tbarlow.model.characters.Hero;

import java.io.*;

public class SaveGame {

    private String fileName;

    public SaveGame() {
        fileName = "heroes.txt";

    }

    public boolean SaveToFile(Hero hero){
        try (FileWriter fileWriter = new FileWriter("heroes.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
            printWriter.printf("%s,%d,%d,%d,%d,%d,%s\n", hero.getName(), hero.getAttack(), hero.getDefense(), hero.getMaxhp(),
                                hero.getXp(), hero.getLevel(), EquipmentString(hero));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String EquipmentString(Hero hero) {
        String eqString = "";
        if (hero.getHelm() != null) {
            eqString += hero.getHelm().getName() + "#" + hero.getHelm().getAmount() + ",";
        }
        else {
            eqString += "null,";
        }

        if (hero.getArmour() != null) {
            eqString += hero.getArmour().getName() + "#" + hero.getArmour().getAmount() + ",";
        }
        else {
            eqString += "null,";
        }

        if (hero.getWeapon() != null) {
            eqString += hero.getWeapon().getName() + "#" + hero.getWeapon().getAmount();
        }
        else {
            eqString += "null";
        }

        return eqString;
    }
}
