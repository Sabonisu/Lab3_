package GameProcess;

import Player.Droid.Droid;
import Player.Player;
import Player.Droid.Types.TankDroid;


import java.util.Scanner;
public class DroidBattle {
    public static void fightPvP(Player[] players) {
        Player currentPlayer = players[0];
        Player opponent = players[1];
        Main.printTeams(players);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        while (true) {
            System.out.println("\nХід гравця " + currentPlayer.getName());
            createMenu(chooseDroid(currentPlayer), opponent);

            if (!checkForLivingDroids(currentPlayer.getDroids())) {
                System.out.println("ПЕРЕМОГА ГРАВЦЯ " + opponent.getName() + "!!!\n");
                break;
            }
            if(!checkForLivingDroids(opponent.getDroids())) {
                System.out.println("ПЕРЕМОГА ГРАВЦЯ " + currentPlayer.getName() + "!!!\n");
                break;
            }

            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }
    }
    public static boolean checkForLivingDroids(Droid[] droids) {
        for (Droid droid : droids) {
            if (droid.isAlive()) {
                    return true;
            }
        }
        return false;
    }
    public static Droid chooseDroid(Player player) {
        int choice;
        int numDrones;

        do {
            System.out.println("Оберіть дроїда:");
            for (numDrones = 0; numDrones < player.getDroids().length; numDrones++) {
                String droidStatus = (player.getDroids()[numDrones].isAlive()) ? "" : " мертвий";
                System.out.println((numDrones + 1) + ". " + player.getDroids()[numDrones] + droidStatus);
            }
            choice = getPlayerChoice();
            if (choice >= 1 && choice <= numDrones) {
                Droid selectedDroid = player.getDroids()[choice - 1];
                if (selectedDroid.isAlive()) {
                    return selectedDroid;
                } else {
                    System.out.println("Неможливо вибрати мертвого дроїда. Виберіть іншого дроїда.");
                }
            } else {
                System.out.println("Невірний вибір. Будь ласка, виберіть знову.");
            }
        } while (true);
    }
    public static void createMenu(Droid droid, Player opponent){
        prockPoisonDamage(droid);
        System.out.println("Меню дій:");
        System.out.println("1. Атакувати");
        if(droid.SkillInCD()){
            System.out.println("2. Використати навичку (доступно через " + droid.getSkillCooldownRemaining() + " )");
        }
        else{System.out.println("2. Використати навичку");}
        boolean shouldContinue = true;
        do {
            int choice = getPlayerChoice();
            switch (choice) {
            case 1 -> {
                while (true) {
                    Droid targetDroid = chooseDroidToAttack(opponent);
                    if (targetDroid.isAlive()) {
                        droid.attackEnemy(targetDroid);
                        shouldContinue = false;
                        break;
                    } else {
                        System.out.println("Неможливо атакувати мертвого дроїда. Виберіть іншого ворога.");
                    }
                }
            }
            case 2 -> {
                    if (droid.SkillInCD()) {
                        System.out.println("Навичка ще на кулдауні. Зачекайте.");
                    } else {
                        droid.useSkill(opponent);
                        shouldContinue = false;
                    }
                }
            default -> System.out.println("Невірний вибір. Будь ласка, виберіть знову.");
            }
        } while (shouldContinue);

    }
    public static void prockPoisonDamage(Droid droid){
        if(droid.getPoison() > 0){
            droid.receiveDamage(droid.getPoison());
            droid.setPoison(droid.getPoison() - 1);
        }
    }
    public static int getPlayerChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.print("--> ");
        choice = scanner.nextInt();
        return choice;
    }
    public static Droid chooseDroidToAttack(Player opponent) {
      int choice;
      int numDrones;
      int tauntedBy = -1;
      for(int i = 0; i < opponent.getDroids().length; i++){
          if(opponent.getDroids()[i] instanceof TankDroid && opponent.getDroids()[i].isTauntUsed()){
              tauntedBy = i;
          }
      }
      do {
          System.out.println("Оберіть ціль для атаки:");
          for (numDrones = 0; numDrones < opponent.getDroids().length; numDrones++) {
              Droid enemyDroid = opponent.getDroids()[numDrones];
              String droidStatus = (enemyDroid.isAlive()) ? "" : " мертвий";
              System.out.print((numDrones + 1) + ". " +enemyDroid + droidStatus);
              if(numDrones == tauntedBy){
                  System.out.println(" | насмішка");
              }
              else {System.out.println();
              }
          }
          choice = getPlayerChoice();
          if(tauntedBy != -1){
              if (choice - 1 == tauntedBy) {
                  return opponent.getDroids()[choice - 1];
              } else {
                  System.out.println("Невірний вибір. Будь ласка, виберіть знову.");
              }
          }
          else{
              if (choice >= 1 && choice <= numDrones) {
                  return opponent.getDroids()[choice - 1];
              } else {
                  System.out.println("Невірний вибір. Будь ласка, виберіть знову.");
              }
          }
      } while (true);
  }

}
