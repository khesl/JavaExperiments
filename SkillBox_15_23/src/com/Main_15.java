package com;
import utils.ConsoleColor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main_15 {
    protected static Scanner scanner;

    public static void main(String[] args){
        Main_15 main = new Main_15();
        scanner = new Scanner(System.in);

        issue_15_2();
        //main.issue_15_2_v2();
        issue_15_3();
    }

    public static void issue_15_3() {
        System.out.println(ConsoleColor.setColor("\tIssue_15(day)_3(num)", ConsoleColor.ANSI_RED));
        DateFormat format = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        DateFormat format_ = new SimpleDateFormat("yyyyMMdd HHmmss");

        System.out.println(format.format(new Date()) + ", and new format " + format_.format(new Date()));
    }

    public static void issue_15_2 (){
        System.out.println(ConsoleColor.setColor("\tIssue_15(day)_2(num)", ConsoleColor.ANSI_RED));
        System.out.print("Set you value:");
        String line = scanner.nextLine();
        int summ = Integer.valueOf(line);
        boolean avail = false;
        System.out.println(" Your summ is : '" + summ + "'");
        for (Items item : Items.values())
            if (summ > item.getCost()) {
                System.out.println("you can buy '" + item.toString() + "', is cost (" + item.getCost() + ")");
                avail = true;
            }
            if (!avail) System.out.println("You can't buy something!");
    }
    public void issue_15_2_v2 (){
        Robot robot = new Robot();
        robot.console();
    }

    private class Robot{
        private int deposite = 0;

        public Robot(){}

        public void console(){
            String in = "";
            System.out.println("robot# Hello, what do you like? (buy, exit)");
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.ANSI_RED));
            while (!(in = scanner.nextLine()).equals("exit")){

                if (in.equals("buy")){
                    System.out.print("robot# I have some drinks: '");
                    for (Items item : Items.values())
                        System.out.print(item.toString() + "(" + item.getCost() + ");");
                    System.out.println("' Your deposit is '" + getDeposite() + "'. (choose one, 'deposit' for deposit info, 'info' for repeat information, 'exit' for back levelUp, 'bye' for end program)");
                    System.out.print(ConsoleColor.setColor("buyConsole# ", ConsoleColor.ANSI_RED));
                    while (!(in = scanner.nextLine()).equals("exit")){
                        boolean rightWrite = false;
                        if (in.equals("bye")) {
                            System.out.println(ConsoleColor.setColor("See you later!", ConsoleColor.ANSI_BLUE));

                            break;
                        }
                        for (Items item : Items.values()){
                            if (item.toString().equals(in)){
                                if (!canBuy(item.getCost()))
                                    System.out.println("robot# Sorry, you haven't enough deposit, choose one more or make a deposit (deposit)");
                                else {
                                    buy(item.getCost());
                                    System.out.println("robot# Thank you for purchase '" + ConsoleColor.setColor(item.toString(), ConsoleColor.ANSI_BLUE) + "', your actual deposit '" + getDeposite() + "'");
                                }
                                rightWrite = true;
                            }
                        }

                        if (in.equals("info")){
                            System.out.print("robot# I have some drinks: '");
                            for (Items item : Items.values())
                                System.out.print(item.toString() + "(" + item.getCost() + ");");
                            System.out.println("' Your deposit is '" + getDeposite() + "'. (choose one, 'deposit' for deposit info, 'info' for repeat information, 'exit' for back levelUp, 'bye' for end program)");
                            rightWrite = true;
                        }

                        if (in.equals("deposit")) {
                            System.out.println("robotDep# what do you want? (balance, add, exit)");
                            System.out.print(ConsoleColor.setColor("depositConsole# ", ConsoleColor.ANSI_RED));
                            while (!(in = scanner.nextLine()).equals("exit")) {
                                if (in.equals("balance")) {
                                    System.out.println("robotDep# Your balance is '" + ConsoleColor.setColor(String.valueOf(getDeposite()), ConsoleColor.ANSI_YELLOW) + "'");
                                }
                                else if (in.equals("add")) {
                                    System.out.println("robot# please write your deposit sum:");
                                    System.out.print(ConsoleColor.setColor("depositAddConsole# ", ConsoleColor.ANSI_RED));

                                    while (!(in = scanner.nextLine()).equals("exit")) {
                                        int dep = 0;
                                        try {
                                            dep = Integer.valueOf(in);
                                            addDeposite(dep);
                                            System.out.println("robot# your deposit now '" + getDeposite() + "'");
                                            break;
                                        } catch (NumberFormatException ex) {
                                            System.out.println("robot# wrong numeric input, be careful! Try more or 'exit'");
                                            System.out.println(ConsoleColor.setColor("depositAddConsole# ", ConsoleColor.ANSI_RED));
                                        }
                                    }
                                } else System.out.println("robot# i don't understand, please try more.");
                                System.out.println("robotDep# what do you want? (balance, add, exit)");
                                System.out.print(ConsoleColor.setColor("depositConsole# ", ConsoleColor.ANSI_RED));
                            }
                        }

                        if (!in.equals("") && !rightWrite) System.out.println("robot# i don't understand, please try more.");
                        System.out.print(ConsoleColor.setColor("buyConsole# ", ConsoleColor.ANSI_RED));
                    }
                }


                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.ANSI_RED));
            }
            System.out.println(ConsoleColor.setColor("See you later!", ConsoleColor.ANSI_BLUE));
        }

        public boolean canBuy(int cost){ return deposite > cost; }
        public void buy(int cost) {deposite-= cost;}

        public int getDeposite() { return deposite; }
        public void setDeposite(int deposite) { this.deposite = deposite; }
        public void addDeposite(int deposite) { this.deposite += deposite; }
    }
    private enum Items {
        water (20),
        coffee (100),
        latte (150);

        private int cost;
        Items (int cost) { this.cost = cost;}

        public int getCost() {return cost;}
    }
}
