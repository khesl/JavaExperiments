
public class Main {
    int age_1 = 50;
    int age_2 = 25;
    int age_3 = 80;
    int minAge;
    int maxAge;
    double averageAge;

    public static void main(String[] args) {
        Main main = new Main();
        main.issue_2_6();
    }

    /** Глава 2, Задание 5
     * */
    public void compare(){

        if (age_1 > age_2 && age_1 > age_3) {
            maxAge = age_1;
            minAge = age_2 < age_3 ? age_2 : age_3;
        }
        else if (age_2 > age_1 && age_2 > age_3){
            maxAge = age_2;
            minAge = age_1 < age_3 ? age_1 : age_3;
        }
        else if (age_3 > age_1 && age_3 > age_2){
            maxAge = age_3;
            minAge = age_1 < age_2 ? age_1 : age_2;
        }
        averageAge = (age_1 + age_2 + age_3)/3;

        System.out.println("самый старый : " + maxAge);
        System.out.println("самый молодой : " + minAge);
        System.out.println("средний возраст: " + averageAge);
    }

    /** Глава 2, Задание 6
     *
     * while - циклическая структура предусловия, сначала проверяется условие, потом запускается тело цикла.
     * while (условие) {
     *     //тело цикла
     * }
     * do_while - циклическая структура постусловия, сначала выполняется тело, потом проверяется условие цикла.
     * do {
     *     //тело цикла
     * } while (условие);
     * */
    public void issue_2_6(){
        int count = 200000;
        do {
            System.out.println("count " + count++);
        } while (count < 210000);
    }

}
