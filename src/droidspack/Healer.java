package droidspack;

public class Healer extends Droid {

    private static Integer healingBonus;

    public Healer() {
        super();
        healingBonus = 0;
    }

    public Healer(String name, Integer health, Integer damage, Integer healingBonus) {

        super(name, health, damage);
        this.healingBonus = healingBonus;
    }

    public Healer(Healer droid) {

        super(droid);
        healingBonus = droid.getHealingBonus();
    }

    public static Integer getHealingBonus() {
        return healingBonus;
    }

    public void HealTeam(Droid[] team){
        for(int count = 0; count < team.length; count++)
            team[count].health += health/4;
    }


    public static Droid HealingBonus(Droid droid){
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        int heal = (droid.getHealth() * healingBonus)/100;
        droid.health += heal;
        System.out.println( "\n" + droid.getName() + ANSI_GREEN + "+" + heal + ANSI_RESET+ " HP\n");
        return droid;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Healing bonus\t" + healingBonus;
    }

    public static Healer getDroid1() {
        return new Healer("chi11ie", 583,31, 22);
    }

    public static Healer getDroid2() {
        return new Healer("w1d0w", 401,28, 18);
    }

    public static void PrintOptionsH(){

        System.out.println(getDroid1() + "\n");
        System.out.println(getDroid2() +"\n");
    }

    public static Healer ReturnOption(int choice) {
        switch (choice) {
            case (5):
                return getDroid1();
            case (6):
                return getDroid2();
            default:
                return null;
        }
    }
}
