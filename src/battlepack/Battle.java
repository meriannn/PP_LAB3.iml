package battlepack;

import droidspack.DamageDealer;
import droidspack.Droid;
import droidspack.Healer;
import droidspack.Support;

import java.util.ArrayList;
import java.util.Scanner;

import static droidspack.DamageDealer.CritRateBonusHit;
import static droidspack.Support.StaminaBonusHit;


public class Battle {


    public static int PrePVPcheck(Droid player){

        if(player.getClass() == DamageDealer.class)
            return 1;
        else if(player.getClass() == Support.class)
            return 2;
        else if(player.getClass() == Healer.class )
            return 3;
        else
            return 0;
    }


    public static ArrayList<Droid> Initialize(int droidnum[]){

        ArrayList<Droid> players = new ArrayList<>(droidnum.length + 1);

        for(int count = 0; count < droidnum.length; count++){
            if(droidnum[count] == 1 || droidnum[count] == 2){
                players.add(count, DamageDealer.ReturnOption(droidnum[count]));
            }
            else if(droidnum[count] == 3 || droidnum[count] == 4){
                players.add(count, Support.ReturnOption(droidnum[count]));
            }
            else if (droidnum[count] == 5 || droidnum[count] == 6){
                players.add(count, Healer.ReturnOption(droidnum[count]));
            }
        }

        return players;
    }

    public static void pvpBattle(int[] droidnum){
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";

        ArrayList<Droid> players;
        players = Initialize(droidnum);


        for (int num = 0; num < 2; num++){
            System.out.println("\n\nPlayer"+ (num+1)+"\n"+players.get(num));
        }

        System.out.println("\n\n\n\n\t\t\t\tBATTLE BEGAN\n");
        int count = 1;
        do{
            Droid predator;
            Droid prey;
            if(count ==1){
                predator = players.get(0);
                prey = players.get(1);
            }
            else{
                predator = players.get(1);
                prey = players.get(0);
            }


            switch (PrePVPcheck(predator)){

                case(1):
                    prey.receiveDamage(CritRateBonusHit((DamageDealer) predator));
                    System.out.println("\n"+prey + "\n" + predator);
                    break;

                case(2):
                    prey.receiveDamage(StaminaBonusHit((Support) predator));
                    System.out.println("\n"+prey + "\n" + predator);
                    break;

                case(3):
                    prey.receiveDamage(predator.getDamage());
                    Healer.HealingBonus(predator);
                    System.out.println("\n"+prey + "\n" + predator);
                    break;

                default:
                    System.out.println("oops ... something went wrong\n");
                    break;
            }

            if(count ==1){
                count = 0;
            }
            else {
                count = 1;
            }

        }while((players.get(0).getHealth() > 0) && (players.get(1).getHealth() > 0));

        if(players.get(0).getHealth() > 0)
            System.out.println("\nCongratulations!! Player1 won\n");
        else if(players.get(1).getHealth() > 0)
            System.out.println(ANSI_CYAN + "\nCongratulations!! Player2 won\n" + ANSI_RESET);

    }

    public static void tvtBattle(int[] droidnum){

        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";


        ArrayList<Droid> team1 = new ArrayList<>(3);
        ArrayList<Droid> team2 = new ArrayList<>(3);
        int[] droidnum1 = {droidnum[0], droidnum[1], droidnum[2]};
        int[] droidnum2 = {droidnum[3], droidnum[4], droidnum[5]};

        team1 = Initialize(droidnum1);
        team2 = Initialize(droidnum2);

        PrintTeams(team1, team2);

        String[] strategies = PickStrategies();

        int turn = 0;
        int count = 0;
        ArrayList<Droid> predator = null;
        ArrayList<Droid> prey = null;
        int attackernum = 0;
        int[] available = {0, 0};
        System.out.println("\n\n\n\n\t\t\t\tBATTLE BEGAN\n");


        do{

            switch (turn){
                case(0):
                    predator = team1;
                    prey = team2;
                    attackernum = available[0];
                    break;

                case (1):
                    predator = team2;
                    prey = team1;
                    attackernum = available[1];
                    break;
            }


            int victimnum = FindingPrey(strategies[turn], prey, attackernum);
            System.out.println("\n\nDroid " + predator.get(attackernum).getName()  +
                    " from Team" + (turn+1) + " attacks Droid " +
                    prey.get(victimnum).getName() + " from opponent`s Team");

            /////////////////////////////////////
            try {
                Thread.sleep(3000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            ////////////////////////////////////

            switch (PrePVPcheck(predator.get(attackernum))){

                case(1):
                    prey.get(victimnum).receiveDamage(CritRateBonusHit((DamageDealer) predator.get(attackernum)));
                    //System.out.println("\n"+ prey.get(victimnum) + "\n" + predator);
                    break;

                case(2):
                    prey.get(victimnum).receiveDamage(StaminaBonusHit((Support) predator.get(attackernum)));
                    //System.out.println("\n"+ prey.get(victimnum) + "\n" + predator);
                    break;

                case(3):
                    prey.get(turn).receiveDamage(predator.get(attackernum).getDamage());
                    for(int num = 0; num < predator.size(); num++)
                        Healer.HealingBonus(predator.get(num));
                    //System.out.println("\n"+ prey.get(victimnum) + "\n" + predator);
                    break;

                default:
                    System.out.println("\noops ... something went wrong\n");
                    break;
            }

            count++;
            if(count%6 == 0){
                System.out.println(ANSI_CYAN+ "\n\nResults of round" + count/6 + ANSI_RESET
                + "\n\nTeam1" + team1 + "\n\nTeam2" + team2 + "\n");
            }


            if(prey.get(victimnum).getHealth() == 0){
                switch (turn){
                    case (0):
                        team2.remove(victimnum);
                        break;
                    case (1):
                        team1.remove(victimnum);
                }

            }
            //////////////////////////////////////////////


            switch (turn){
                case(0):
                    turn = 1;
                    available[0]++;
                    if(available[0] == team1.size() | available[0] > team1.size())
                        available[0] = 0;
                    if(available[1] == team2.size() || available[1] > team2.size())
                        available[1] = 0;
                    break;

                case (1):
                    turn =0;
                    available[1]++;
                     if(available[1] == team2.size() || available[1] > team2.size())
                        available[1] = 0;
                     if(available[0] == team1.size() | available[0] > team1.size())
                        available[0] = 0;
                     break;
            }


        }while(team1.size() != 0 && team2.size() != 0);

        if(team1.size() == 0)
            System.out.println(ANSI_CYAN+"\n\n\n\t\tCONGRATULATIONS !!! Team2 is THE WINNER\n" +ANSI_RESET);
        else if(team2.size() == 0)
            System.out.println(ANSI_CYAN+"\n\n\n\t\tCONGRATULATIONS !!! Team1 is THE WINNER\n"+ANSI_RESET);
    }

    public  static String[] PickStrategies(){

        String[] strategies = new String[2];
        for(int playernum = 0; playernum < 2; playernum++){
            Scanner input = new Scanner(System.in);
            System.out.println("\nPlayer" + (playernum+1) + " choose your strategy:" +
                    "\nr\t\tRandom (you attack other player`s random team memeber)" +
                    "\ns\t\tSneaky (you attack other player`s Healer or weakest team member)" +
                    "\nc\t\tConsistent (you attack other player`s Droid with same sequence number)");
            strategies[playernum] = input.nextLine();
        }
        return strategies;
    }

    public static int FindingPrey(String strategy, ArrayList<Droid> enemy, int droidnum){

        switch(strategy){
            case("r"):
                int rand = 0 + (int)(Math.random() * enemy.size());
                return rand;


            case("s"):
                int victimnum = 0;
                for(int count = 0; count < enemy.size(); count++){
                    if(enemy.get(count).getClass() == Healer.class){
                        victimnum = count;
                        break;
                    }
                    else if(enemy.get(count).getHealth() < enemy.get(victimnum).getHealth())
                        victimnum = count;
                }
                return victimnum;

            default:
                if(droidnum >= enemy.size())
                    return 0;
                else
                    return droidnum;
        }
    }

    public static void PrintTeams(ArrayList<Droid> team1, ArrayList<Droid> team2){
        System.out.println("\n\n::: Team1 :::\n");
        for(int len = 0; len < team1.size(); len++){
            System.out.println(team1.get(len));
        }
        System.out.println("\n\n::: Team2 :::\n");
        for(int len = 0; len < team2.size(); len++){
            System.out.println(team2.get(len));
        }
    }




}
