package droidspack;

import java.util.ArrayList;

public class Support extends Droid {

    private static Integer stamina;

    public Support() {
        super();
        stamina = 0;
    }

    public Support(String name, Integer health, Integer damage, Integer stamina) {
        super(name, health, damage);
        this.stamina = stamina;
    }

    public Support(Support droid) {
        super(droid);
        stamina = droid.getStamina();
    }


    public static Integer getStamina() {
        return stamina;
    }

    public static void setStamina(Integer stamina) {
        Support.stamina += stamina;
    }

    public static int StaminaBonusHit(Support droid1){

        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        System.out.println("\n Droid " + droid1.name +" fast attacks\n");
        int dmg;
        int gen_dmg = 0;
        int stamina_cp =stamina;
        for (; stamina_cp > 0; stamina -= 10, stamina_cp-=10){
            int rand = 25 + (int)(Math.random() * 65);
            dmg = (rand*droid1.damage)/100;
            gen_dmg += dmg;
            if(rand > 50)
                stamina+=20;
            System.out.println(ANSI_YELLOW + "\t-" + dmg +" HP"+ "\n" + ANSI_RESET);
        }
        if(stamina < 10)
            stamina+=10;
        return gen_dmg;
    }



    @Override
    public String toString() {
        return super.toString()+
                "Stamina\t\t\t" + stamina;
    }


    public static Support getDroid1() {
        final Support droid1;
        return droid1= new Support("n0nst0p", 379,72, 30);
    }

    public static Support getDroid2() {
        final Support droid2;
        return droid2 = new Support("baskervi11e", 401,64, 40);
    }

    public static void PrintOptionsS(){
        System.out.println(getDroid1() + "\n");
        System.out.println(getDroid2() +"\n");
    }

    public static Support ReturnOption(int choice) {
        switch (choice) {
            case (3):
                return getDroid1();
            case (4):
                return getDroid2();
            default:
                return null;
        }
    }
}
