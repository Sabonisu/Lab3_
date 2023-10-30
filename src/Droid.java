public class Droid {
    private String name;
    private double health;
    private double attack;
    private final String className;
    private int poison;
    private final int skillCooldown;
    private int skillCooldownRemaining = 0;

    public Droid(String name, double health, double attack,String className,int skillCooldown) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.className = className;
        this.skillCooldown = skillCooldown;
        poison = 0;
    }
    public void receiveDamage(double damage) {
        if (damage > 0) {
            health -= damage;
            System.out.println(getName() + " Отримує " + damage + " шкоди");
            if (health <= 0) {
                System.out.println(getName() + " помирає");
                health = 0;
            }
        }
    }
    public boolean SkillInCD(){
        return skillCooldownRemaining > 0;
    }

    public String getName() {
        return name;
    }
    public void setName(double name) {
        this.name = String.valueOf(name);
    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public double getAttack() {
        return attack;
    }
    public void setAttack(double attack) {
        this.attack = attack;
    }
    public int getPoison() {
        return poison;
    }

    public void setPoison(int poison) {
        this.poison = poison;
    }

    public boolean isAlive(){
            return health > 0;
    }
    public int getSkillCooldown() {
        return skillCooldown;
    }

    public void attackEnemy(Droid droid){
        droid.receiveDamage(this.attack);
        if(getSkillCooldownRemaining() > 0) {
            setSkillCooldownRemaining(getSkillCooldownRemaining() - 1);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
    public int getSkillCooldownRemaining() {
        return skillCooldownRemaining;
    }

    public void setSkillCooldownRemaining(int skillCooldownRemaining) {
        this.skillCooldownRemaining = skillCooldownRemaining;
    }


    public void useSkill(Player player) {
    }
    public boolean isTauntUsed(){
        return false;
    }
    @Override
    public String toString() {
        return String.format("| Name: %-15s | Class: %-15s | HP: %-4.1f | ATK: %-1.1f | PSN: %-1d |", name, className, health, attack, poison);

    }


}
