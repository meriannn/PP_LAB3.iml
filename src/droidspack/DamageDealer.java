package droidspack;


public class DamageDealer extends Droid {
    private static Integer critRate;

    public DamageDealer() {
        super();
        critRate = 0;
    }

    public DamageDealer(String name, Integer health, Integer damage, Integer critRate) {
        super(name, health, damage);
        this.critRate = critRate;
    }

    public DamageDealer(DamageDealer droid) {
        super(droid);
        critRate = droid.getCritRate();
    }

    public static Integer getCritRate() {
        return critRate;
    }

    public static void setCritRate(Integer critRate) {
        DamageDealer.critRate = critRate;
    }



    public static int CritRateBonusHit(DamageDealer droid1){
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        Integer dmg = droid1.damage;
        Integer rand = (int)(Math.random() * 100);
        if(rand <= droid1.critRate){

            dmg *= 3;
        }
        System.out.println("\n"+
                droid1.getName() + " droid ATTACKS\n\n\t" +
                ANSI_RED + "-" +
                dmg + " HP"+ ANSI_RESET);
        return dmg;
    }



    @Override
    public String toString() {
        return super.toString() +
                "Crit Rate\t\t" + critRate;
    }



    public static DamageDealer getDroid1() {
        return new DamageDealer("r3dbu11", 311,88, 46);
    }

    public static DamageDealer getDroid2() {
        return new DamageDealer("anabe11e", 278,93, 39);
    }

    public static void PrintOptionsDD(){

        System.out.println(getDroid1() + "\n");
        System.out.println(getDroid2() +"\n");
    }

    public static DamageDealer ReturnOption(int choice) {
        switch (choice) {
            case (1):
                return getDroid1();
            case (2):
                return getDroid2();
            default:
                return null;
        }
    }
}
