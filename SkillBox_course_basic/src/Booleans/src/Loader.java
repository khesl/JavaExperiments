package Booleans.src;

public class Loader
{
    public static void main(String[] args)
    {
        Integer milkAmount = 200; // ml
        Integer powderAmount = 5; // g
        Integer eggsCount = 3; // items
        Integer sugarAmount = 5; // g
        Integer oilAmount = 30; // ml
        Integer appleCount = 8;

        //powder - 400 g, sugar - 10 g, milk - 1 l, oil - 30 ml
        makePankakes(powderAmount, sugarAmount, milkAmount, oilAmount);

        milkAmount += 150;
        eggsCount += 2 ;
        //milk - 300 ml, powder - 5 g, eggs - 5
        makeOmelette(milkAmount, powderAmount, eggsCount);

        powderAmount += 350;
        //apples - 3, milk - 100 ml, powder - 300 g, eggs - 4
        makeApple_pie(appleCount, milkAmount, powderAmount, eggsCount);
    }

    private static void makePankakes(Integer powderAmount, Integer sugarAmount,Integer milkAmount,Integer oilAmount){
        if ((powderAmount >= 400) && (sugarAmount >= 10) && (milkAmount >= 1) && (oilAmount >= 30))
        System.out.print("Congrats! You can create");
        else System.out.print("Oh no, you need more ingredients for ");
        System.out.println("Pancakes");
    }
    private static void makeOmelette(Integer milkAmount, Integer powderAmount, Integer eggsCount){
        if ((milkAmount >= 300) && (powderAmount >= 5) && (eggsCount >= 5))
        System.out.print("Congrats! You can create ");
        else System.out.print("Oh no, you need more ingredients for ");
        System.out.println("Omelette");
    }
    private static void makeApple_pie(Integer appleCount, Integer milkAmount,Integer powderAmount,Integer eggsCount){
        if ((appleCount >= 3) && (milkAmount >= 100) && (powderAmount >= 300) && (eggsCount >= 4))
        System.out.print("Congrats! You can create");
        else System.out.print("Oh no, you need more ingredients for ");
        System.out.println("Apple pie");
    }
}