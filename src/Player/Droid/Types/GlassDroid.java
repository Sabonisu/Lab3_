package Player.Droid.Types;
import GameProcess.DroidBattle;
import Player.Droid.Droid;
import Player.Player;
public class GlassDroid extends Droid {
    public GlassDroid(String name) {
        super(name, 4, 3,"DamageDealer",1);
    }
    @Override
    public void useSkill(Player opponent) {
        System.out.println(getName() + " використовує навичку 'Критичний постіл'.");
        double criticalHitChance = 0.8;

        if (Math.random() < criticalHitChance) {
            while (true) {
                Droid targetDroid = DroidBattle.chooseDroidToAttack(opponent);
                if (targetDroid.isAlive()) {
                    System.out.println("Критичне попадання!!!");
                    targetDroid.receiveDamage(2 * getAttack());
                    break;
                } else {
                    System.out.println("Неможливо атакувати мертвого дроїда. Виберіть іншого ворога.");
                }
            }
        } else {
            while (true) {
                Droid targetDroid = DroidBattle.chooseDroidToAttack(opponent);
                if (targetDroid.isAlive()) {
                    targetDroid.receiveDamage(getAttack());
                    break;
                } else {
                    System.out.println("Неможливо атакувати мертвого дроїда. Виберіть іншого ворога.");
                }
            }
        }
        setSkillCooldownRemaining(getSkillCooldown());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
