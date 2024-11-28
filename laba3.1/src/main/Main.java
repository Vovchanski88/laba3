package main;

import utils.*;
import droids.*;
import battle.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Droid> droids = new ArrayList<>(); // Список дроїдів створений у Main

        DroidManager droidManager = new DroidManager(droids);
        BattleManager battleManager = new BattleManager(droids);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Створення дроїда");
            System.out.println("2. Показати всіх дроїдів");
            System.out.println("3. Розпочати битву 1 на 1");
            System.out.println("4. Розпочати командний бій");
            System.out.println("5. Зберегти бій у файл");
            System.out.println("6. Повторити збережені битви");
            System.out.println("7. Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> droidManager.createDroid(scanner);
                case 2 -> droidManager.showDroids();
                case 3 -> battleManager.startOneOnOneBattle(scanner);
                case 4 -> battleManager.startTeamBattle(scanner);
                case 5 -> saveBattleToFile();
                case 6 -> replayBattle();
                case 7 -> {
                    System.out.println("Вихід...");
                    return;
                }
                default -> System.out.println("Недійсний варіант.");
            }
        }
    }



    private static void saveBattleToFile() {
        System.out.println("Бій збережено у файл.");
    }

    // Метод для завантаження та відтворення бою
    private static void replayBattle() {
        List<String> battles = BattleLogger.loadBattles();
        if (battles.isEmpty()) {
            System.out.println("Немає збережених битв.");
        } else {
            System.out.println("Збережені битви:");
            for (int i = 0; i < battles.size(); i++) {
                System.out.println((i + 1) + ". " + battles.get(i));
            }
        }
    }
}
