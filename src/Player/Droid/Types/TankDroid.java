package Player.Droid.Types;
import Player.Player;
import Player.Droid.Droid;

public class TankDroid extends Droid {
    private boolean tauntUsed = false;
    public TankDroid(String name) {
        super(name, 12, 2,"Tank",1);
    }
    @Override
    public void useSkill(Player player) {

        tauntUsed = true;

        System.out.println(getName() + " використовує навичку 'Насмішка'. Вороги тепер не можуть атакувати інших дроїдів.\nОтримує на 50% менше шкоди");
        setSkillCooldownRemaining(getSkillCooldown());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
    @Override
    public void receiveDamage(double damage) {
        if (tauntUsed) {
            tauntUsed = false;
            super.receiveDamage(damage / 2);
        } else {
            super.receiveDamage(damage);
        }
    }
    @Override
    public boolean isTauntUsed(){
        return tauntUsed;
    }


}
