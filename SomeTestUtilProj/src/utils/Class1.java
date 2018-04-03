public class Class1 {
    public Class1() {
        super();
    }

    public static void main(String[] args) {
        //Class1 class1 = new Class1();
        
        int pureOrder = 150000 + 50000;
        double d = 952153;
        //d=d*14/100/12;
        System.out.println("amount - " + d);
        double potentialAm = 0;
        
        for (int i = 0; i < 4+6; i++) {
            double dperc = d*14/100/12;
            d += dperc;
            d += pureOrder;
            potentialAm += 50000;
            
            System.out.println("month " + i + ", % - " + dperc + ", amount - " + d + ", potential - " + potentialAm + " +/- " + potentialAm/2);
            
        }
        
        System.out.println(d);
        
    }
}
