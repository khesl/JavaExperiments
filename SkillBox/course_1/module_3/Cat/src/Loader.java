package course_1.module_3.Cat.src;

public class Loader{
    private static boolean isDebug = false;
    public static void main(String[] args)
    {
        //lesson_1();
        //lesson_2();
        //lesson_3();
        //System.out.println((int)'а' + " " + (char)1104);
        //System.out.println(getRamdonName());

        Cat cat = getKitten();
        Cat cat_2 = cat.clone();

        System.out.println(cat);
        System.out.println(cat_2);

    }



    private static Cat getKitten(){
        Double weight = 100 + 100 * Math.random();
        return new Cat(getRamdonName(), weight);
    }

    private static int[] vowels = {1072,1077,1080,1086,1091,1099,1101,1102,1103};
    private static int[] ban = {1098, 1100, 1104};
    private static String getRamdonName(){
        int length =  3 + (int) (Math.random()*3);
        String name = "";
        for (int i =0; i < length; i++){
            char letter;
            boolean isVowelsPrev;
            boolean isVowelsCur;
            do {
                isVowelsPrev = false;
                isVowelsCur = false;
                letter = (char)(1072 + (int)(Math.random()*31));
                boolean banned = false;
                do {
                    banned = false;
                    for (int val : ban)
                        if ((int) letter == val)
                            banned = true;
                    if (banned){
                        if (isDebug) System.out.print("banned " + letter + ": ");
                        letter = (char)(1072 + (int)(Math.random()*31));
                        if (isDebug) System.out.print(letter);
                    }
                } while (banned);
                if (isDebug) System.out.println("\tnew " + i + ": " + letter);
                for (int val : vowels){
                    if (i == 0)
                        isVowelsPrev = true;
                    else if (name.charAt(i-1) == val)
                        isVowelsPrev = true;
                    if ((int)letter == val)
                        isVowelsCur = true;
                }
                if (isDebug) System.out.print((i == 0 ? "." : name.charAt(i-1)) + "-" + letter + "\tisVowelsPrev: " + isVowelsPrev);
                if (isDebug) System.out.println("| isVowelsCur: " + isVowelsCur);
            } while (isVowelsPrev == isVowelsCur);
            name += letter;
            if (i == 0) name = name.toUpperCase();
        }
        return name;
    }

    public static void lesson_3(){

        Cat cat_1 = new Cat("cat_1");
        Cat cat_2 = new Cat("cat_2");
        Cat cat_3 = new Cat("cat_3");
        Cat cat_4 = new Cat("cat_4");
        Cat cat_5 = new Cat("cat_5");
        Cat cat_6 = new Cat("cat_6");
        Cat cat_7 = new Cat("cat_7");
        System.out.println(Cat.getCount());

        System.out.println(cat_3);
        while(!cat_3.getStatus().equals("Dead")){
            cat_3.meow();
            if (cat_3.getWeight()%500d < 1) System.out.println(cat_3);
        }
        System.out.println(cat_3);

        System.out.println(Cat.getCount());
    }
    public static void lesson_2(){

        Cat cat_1 = new Cat("cat_1");
        Cat cat_2 = new Cat("cat_2");
        Cat cat_3 = new Cat("cat_3");
        Cat cat_4 = new Cat("cat_4");
        Cat cat_5 = new Cat("cat_5");
        Cat cat_6 = new Cat("cat_6");
        Cat cat_7 = new Cat("cat_7");

        System.out.println(cat_1);
        cat_1.drink(50d);
        System.out.println(cat_1.getName() + " has drink.");
        System.out.println(cat_1);
        cat_1.peePee();
        cat_1.peePee();
        System.out.println(cat_1);

        System.out.println();

        System.out.println(cat_2);
        cat_2.feed(500d);
        System.out.println(cat_2.getName() + " has feed.");
        System.out.println(cat_2);
        System.out.println(cat_2.getName() + "feeded for: " + cat_2.feededWeight());
    }
    public static void lesson_1(){

        Cat cat_1 = new Cat("cat_1");
        Cat cat_2 = new Cat("cat_2");
        Cat cat_3 = new Cat("cat_3");
        Cat cat_4 = new Cat("cat_4");
        Cat cat_5 = new Cat("cat_5");
        Cat cat_6 = new Cat("cat_6");
        Cat cat_7 = new Cat("cat_7");

        System.out.println(cat_1);
        cat_1.drink(50d);
        System.out.println(cat_1.getName() + " has drink.");
        System.out.println(cat_1);

        System.out.println(cat_2);
        cat_2.feed(500d);
        System.out.println(cat_2.getName() + " has feed.");
        System.out.println(cat_2);

        System.out.println(cat_3);
        while(!cat_3.getStatus().equals("Dead")){
            cat_3.meow();
            if (cat_3.getWeight()%500d < 1) System.out.println(cat_3);
        }
        System.out.println(cat_3);

        System.out.println(cat_4);
        while(!cat_4.getStatus().equals("Exploded")){
            cat_4.feed(25d);
            System.out.println(cat_4.getName() + " has feed.");
            if (cat_4.getWeight()%500d < 25) System.out.println(cat_4);
        }
        System.out.println(cat_4);

        System.out.println(cat_5);
        while(!cat_5.getStatus().equals("Exploded")){
            cat_5.meow();
            cat_5.feed(500d);
            System.out.println(cat_5.getName() + " has feed.");
            if (cat_5.getWeight()%500d < 25) System.out.println(cat_5);
        }
        System.out.println(cat_5);
    }
}