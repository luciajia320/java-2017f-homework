package Homework.CalabashBrothers;

import java.util.Scanner;

/**
 * The fighting between Calabash Brothers and Scorpion.
 * Java 2017f Homework
 *
 * @author 141070055 Tang Wenqing
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Input the side length of the lawn (>=14) to start the story: ");
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextInt() == false || sc.nextInt() < 14) {
                System.out.println("Invalid input! Please make sure to input an integer no less than 14!");
                System.out.println("Input the side length of the lawn (>=14) to start the story: ");
                sc = new Scanner(System.in);
            }
            int side = sc.nextInt();
            System.out.println("-----------------The story of Calabash Brothers-----------------");
            Lawn lawn = new Lawn(side, side);
            System.out.println("【1st Confrontation】");
            lawn.setfield(side, side);
            lawn.showyourself();
            System.out.println("【2nd Confrontation】");
            lawn.setfield(side, side);
            lawn.showyourself();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}