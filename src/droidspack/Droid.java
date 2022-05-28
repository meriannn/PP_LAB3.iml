package droidspack;

public class Droid {
    protected String name;
    protected Integer health;
    protected Integer damage;

    public Droid(){
        name = "0";
        health = 0;
        damage = 0;
    }

    public Droid(String name, Integer health, Integer damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public Droid(Droid droid) {
        name = droid.getName();
        health = droid.getHealth();
        damage = droid.getDamage();
    }


    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "\nDroid \n" +
                "Name\t\t\t" + this.name +
                "\nHP\t\t\t\t"+ this.health +
                "\nGMG\t\t\t\t" + this.damage +
                "\n";
    }

    public void receiveDamage(Integer dmg){

        this.health -= dmg;
        if(this.health < 0)
            this.health = 0;
    }
}
