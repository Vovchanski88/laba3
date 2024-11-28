package droids;

import java.util.List;
import java.util.Scanner;

public class DroidManager {
    private final List<Droid> droids;

    public DroidManager(List<Droid> droids) {
        this.droids = droids;
    }

    public void createDroid(Scanner scanner) {
        System.out.println("Введіть тип дроїда (1: Бойовий, 2: Підтримка): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введіть назву дроїда: ");
        String name = scanner.nextLine();

        Droid droid = switch (type) {
            case 1 -> new BattleDroid(name);
            case 2 -> new SupportDroid(name);
            default -> null;
        };

        if (droid != null) {
            droids.add(droid);
            System.out.println(droid.getName() + " створений.");
        } else {
            System.out.println("Недійсний тип дроїда.");
        }
    }

    public void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("Дроїдів не створено.");
        } else {
            for (int i = 0; i < droids.size(); i++) {
                Droid droid = droids.get(i);
                System.out.println((i+1) + ". " + droid.getName() + " [Здоров'я: " + droid.getHealth() + ", Шкода: " + droid.getDamage() + "]");
            }
        }
    }
}
