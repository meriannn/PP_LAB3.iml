package mainpack;

import battlepack.Battle;
import droidspack.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);


    public static int[] ChoosingDroids(int length){
        int[] players = null;
        if( length == 2){
            players = new int[2];
            for(int count = 0; count < length; count ++){
                System.out.println("\nPlayer"+(count+1)+" choose your droid (1-6)");
                int choice = input.nextInt();
                players[count] = choice;
            }
        }

        else if(length == 6){
            int num = 0;
            players = new int[6];
            for(int count = 0; count < 2; count++){
                System.out.println("\nPlayer"+(count+1)+" choose your team of 3 droids (1-6)");
                for(int repeats =0; repeats < 3; repeats++){
                    int choice = input.nextInt();
                    players[num] = choice;
                    num++;
                }
            }
        }

        return players;
    }

    public static void PrintDroids(){

        System.out.println("\n\n\t\t\tAVAILABLE DROIDS");
        DamageDealer.PrintOptionsDD();
        Support.PrintOptionsS();
        Healer.PrintOptionsH();
    }

    public static int[] BattlePreparations(int choice){
        int[] players = null;

        switch (choice){
            case (1):
                players= ChoosingDroids(2);
                break;
            case (2):
                players= ChoosingDroids(6);

        }
        return players;
    }

    public static void MainMenu(){

        int option;
        int[] players;
        do{
            System.out.println(
                    "\n\n\t|\t|\t|\t|MAIN MENU|\t|\t|\t|\t" +
                            "\nPrint available droids\t\t\t1" +
                            "\nPVP Battle\t\t\t\t\t\t2" +
                            "\nTVT Battle\t\t\t\t\t\t3" +
                            "\nEXIT\t\t\t\t\t\t\t4\n");

            option = input.nextInt();
            while(option >=1 && option<= 3){
                switch(option){
                    case(1):
                        PrintDroids();
                        break;
                    case(2):
                        players = BattlePreparations(1);
                        Battle.pvpBattle(players);
                        break;
                    case (3):
                        players = BattlePreparations(2);
                        Battle.tvtBattle(players);
                    default:
                        break;
                }
                option = 0;
            }
        }while(option != 4);

    }





    public static void main(String[] args){

        MainMenu();

   }
}

