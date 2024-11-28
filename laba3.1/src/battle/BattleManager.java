package battle;

import droids.Droid;
import utils.BattleLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleManager {
    private final List<Droid> droids;
    private Random random = new Random(); // Об'єкт Random для вибору випадкового дроїда

    public BattleManager(List<Droid> droids) {
        this.droids = droids;
    }

    public void startOneOnOneBattle(Scanner scanner) {
        if (droids.size() < 2) {
            System.out.println("Недостатньо дроїдів для битви один на один.");
            return;
        }
        System.out.println("Виберіть двох дроїдів для битви:");
        showDroids();
        int index1 = scanner.nextInt() - 1;
        int index2 = scanner.nextInt() - 1;

        Droid droid1 = droids.get(index1);
        Droid droid2 = droids.get(index2);

        System.out.println("Битва " + droid1.getName() + " проти " + droid2.getName());
        while (droid1.isAlive() && droid2.isAlive()) {
            if (random.nextBoolean()) {
                randomAttack(droid1, droid2);
            } else {
                randomAttack(droid2, droid1);
            }
        }

        System.out.println("Переможець: " + (droid1.isAlive() ? droid1.getName() : droid2.getName()));
        BattleLogger.logBattle(droid1.getName() + " проти " + droid2.getName() + " - Переможець: " + (droid1.isAlive() ? droid1.getName() : droid2.getName()));
    }

    public void startTeamBattle(Scanner scanner) {
        if (droids.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Виберіть дроїдів для команди 1:");
        selectDroidsForTeam(scanner, team1);

        System.out.println("Виберіть дроїдів для команди 2:");
        selectDroidsForTeam(scanner, team2);

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Кожна команда повинна мати принаймні одного дроїда.");
            return;
        }

        System.out.println("Початок командного бою");
        while (teamHasAliveDroid(team1) && teamHasAliveDroid(team2)) {
            Droid attacker = getRandomAliveDroid(team1, team2);
            Droid defender = (team1.contains(attacker)) ? getAliveDroid(team2) : getAliveDroid(team1);

            if (attacker != null && defender != null) {
                randomAttack(attacker, defender);
            }
        }

        System.out.println("Переможець: " + (teamHasAliveDroid(team1) ? "Команда 1" : "Команда 2"));
        BattleLogger.logBattle("Командний бій - Переможець: " + (teamHasAliveDroid(team1) ? "Команда 1" : "Команда 2"));
    }

    private Droid getRandomAliveDroid(List<Droid> team1, List<Droid> team2) {
        List<Droid> aliveDroids = new ArrayList<>();
        for (Droid droid : team1) if (droid.isAlive()) aliveDroids.add(droid);
        for (Droid droid : team2) if (droid.isAlive()) aliveDroids.add(droid);

        return aliveDroids.isEmpty() ? null : aliveDroids.get(random.nextInt(aliveDroids.size()));
    }

    private void randomAttack(Droid attacker, Droid defender) {
        System.out.println(attacker.getName() + " атакує " + defender.getName() + " на " + attacker.getDamage() + " шкоди");
        defender.takeDamage(attacker.getDamage());
        if (!defender.isAlive()) {
            System.out.println(defender.getName() + " був знищений!");
        }
    }

    public void selectDroidsForTeam(Scanner scanner, List<Droid> team) {
        while (true) {
            showDroids();
            System.out.println("Введіть індекс дроїда, щоб додати до команди (або -1, щоб завершити):");
            int index = scanner.nextInt() ;

            if (index == -1) {
                break;
            } else if (index >= 0 && index < droids.size()) {
                team.add(droids.get(index));
                System.out.println(droids.get(index).getName() + " додано до команди.");
            } else {
                System.out.println("Недійсний індекс. Спробуйте знову.");
            }
        }
    }

    private boolean teamHasAliveDroid(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    private Droid getAliveDroid(List<Droid> team) {
        List<Droid> aliveDroids = new ArrayList<>();
        for (Droid droid : team) {
            if (droid.isAlive()) {
                aliveDroids.add(droid);
            }
        }
        return aliveDroids.isEmpty() ? null : aliveDroids.get(random.nextInt(aliveDroids.size()));
    }

    private void showDroids() {
        for (int i = 0; i < droids.size(); i++) {
            Droid droid = droids.get(i);
            System.out.println((i + 1) + ". " + droid.getName() + " [Health: " + droid.getHealth() + ", Damage: " + droid.getDamage() + "]");
        }
    }
}
