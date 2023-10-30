package GameProcess;
import Player.Droid.Types.GlassDroid;
import Player.Droid.Types.TankDroid;
import Player.Droid.Types.PoisonDroid;
import Player.Droid.Droid;
import Player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 1;
        do {
            System.out.println("Меню:");
            System.out.println("1. PvP");
            System.out.println("2. BossFight");
            System.out.println("0. Вихід");

            switch (DroidBattle.getPlayerChoice()) {
                case 1 -> {
                    Player[] player = new Player[2];
                    player[0] = createPlayer();
                    player[1] = createPlayer();
                    DroidBattle.fightPvP(player);
                }
                case 2 -> System.out.println("Недоступно");
                case 0 -> x = 0;
                default -> System.out.println("Невірний вибір. Будь ласка, виберіть знову.");
            }

        } while (x != 0);
    }

    public static void printTeams(Player[] player){
        for (int i = 0; i < player.length; i++) {
            System.out.println("Player " + player[i].getName());
            for (Droid droid : player[i].getDroids()) {
                System.out.println(droid);
            }
        }
    }
    public static Player createPlayer() {
        String name = playerNameInput();
        Droid[] droids = createDroids();
        return new Player(name, droids);
    }
    public static int playerDronesNumberInput() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            System.out.print("Введіть кількість дроїдів (від 1 до 4): ");
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 4) {
                System.out.println("Ввід неправильний");
            }
        } while (userInput < 1 || userInput > 4);
        return userInput;
    }

    public static String playerNameInput() {
        System.out.print("\nВведіть ім'я гравця: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String droidNameInput() {
        System.out.print("Введіть ім'я дроїда: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int droidClassInput() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            System.out.println("1. TankDroid");
            System.out.println("2. PoisonDroid");
            System.out.println("3. GlassDroid");
            System.out.print("--> ");
            userInput = scanner.nextInt();
            if (userInput < 1 || userInput > 3) {
                System.out.println("Ввід неправильний");
            }
        } while (userInput < 1 || userInput > 3);
        return userInput;
    }

    public static Droid[] createDroids() {
        int numberOfDroids = playerDronesNumberInput();
        Droid[] droids = new Droid[numberOfDroids];
        for (int i = 0; i < droids.length; i++) {
            System.out.println("Виберіть клас для дроїда №" + (i + 1) + ":");
            int choice = droidClassInput();
            String droidName = droidNameInput();
            switch (choice) {
                case 1 -> droids[i] = new TankDroid(droidName);
                case 2 -> droids[i] = new PoisonDroid(droidName);
                case 3 -> droids[i] = new GlassDroid(droidName);
                default -> {
                    System.out.println("Невірний вибір класу дроїда.");
                    i--;
                }
            }
        }
        return droids;
    }
}
