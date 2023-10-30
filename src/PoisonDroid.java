public class PoisonDroid extends Droid {
    private int skillDamage;

    public PoisonDroid(String name) {
        super(name, 7, 1,"PoisonBuilder",2);
        skillDamage = 1;
    }
    @Override
    public void attackEnemy(Droid droid){
        droid.receiveDamage(getAttack());
        droid.setPoison(droid.getPoison()+1);
        System.out.println(getName()+ " накладає +1 отравлення на " + droid.getName());
        if(getSkillCooldownRemaining() > 0) {
            setSkillCooldownRemaining(getSkillCooldownRemaining() - 1);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

    }

    @Override
    public void useSkill(Player player) {
        for(Droid droid : player.getDroids()){
            droid.setPoison(droid.getPoison()+skillDamage);
        }
        System.out.println(getName() + " використовує навичку 'Отруйна хмара'\nНакладає +" + skillDamage + " отравлення на ворожу команду");
        setSkillCooldownRemaining(getSkillCooldown());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
