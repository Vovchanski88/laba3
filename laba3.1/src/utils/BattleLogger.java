package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BattleLogger {
    private static final String FILE_PATH = "battle_log.txt";

    // Метод для запису результату бою у файл
    public static void logBattle(String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(result);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Помилка запису журналу битв: " + e.getMessage());
        }
    }

    // Метод для зчитування боїв із файлу
    public static List<String> loadBattles() {
        List<String> battles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                battles.add(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання журналу бою: " + e.getMessage());
        }
        return battles;
    }
}
