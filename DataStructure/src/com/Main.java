package com;

import com.sorting.Insertion;
import com.sorting.Merge;
import com.sorting.Quick;
import com.sorting.Quick3way;
import com.sorting.Selection;
import com.sorting.Shell;

import com.utils.ArrayST;
import com.utils.BinarySearchST;
import com.utils.Buffer;
import com.utils.MaxPQ;
import com.utils.OrderedSequentialSearchST;
import com.utils.Queue;

import com.utils.Stopwatch;

import com.utils.UF;

import com.utils.WeightedQuickUnionUF;

import java.awt.geom.Point2D;

import java.io.File;

import java.io.IOException;

import java.lang.reflect.Array;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;


public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Stopwatch time = new Stopwatch();
        //double a = 0, b = 0;
        Scanner in = new Scanner(System.in);
        //a =  Double.valueOf(in.nextLine());
        //b =  Double.valueOf(in.nextLine());
    
        //deepIntoTheFile(new File("C:\\Users\\vassina\\Desktop\\project\\JavaExperiments"));
        double timer = time.elapsedTime(); 
        
        System.out.println(" eded " + fact(5));
        
        
        //System.out.println(String.valueOf((int)(Math.random()*100000000)));
        task_3_1_3();
        
        System.out.println("programm Shell.sort() time '" + (time.elapsedTime() - timer) + "'");
    }
    
    public static int fact(int x){
            if (x > 1) return x*fact(x-1);
            else return 1;
    }
    
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
    
    private static void exch(Comparable[] a, int i, int j)
    { Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
    
    
    public static class Point2DSort implements Comparable<Point2D>{
        public Point2D val;
        Point2DSort (Point2D val){
            this.val = val;
        }
        
        public int compareTo(Point2D that) {
            return 0;
        }        
        public int compareToByX(Point2D that) {
            if (this.val.getX() > that.getX()) return +1;
            if (this.val.getX() < that.getX()) return -1;
            return 0;
        }        
        public int compareToByY(Point2D that) {
            if (this.val.getY() > that.getY()) return +1;
            if (this.val.getY() < that.getY()) return -1;
            return 0;
        }        
        public int compareToByDistance(Point2D that) {
            if (this.val.distance(0, 0) > that.distance(0, 0)) return +1;
            if (this.val.distance(0, 0) < that.distance(0, 0)) return -1;
            return 0;
        }
    }
    
    static{
        System.out.println("start"); // воу
    }
    
    public static void print(Object str){
        System.out.println(str);
    }
    
    int factorial(int n){
      // Факториал отрицательного числа не считается
        assert(n >= 0) : false;
        System.out.println("first");
        assert (false);
        
      // Если n превысит 10, то это может привести либо к целочисленному
      // переполнению результата, либо к переполнению стэка.
        assert(n <= 10) : "dgfd";
        System.out.println("sec");
      if (n < 2) {
        return 1;
      }

      return factorial(n - 1) * n;
    }
    
    public void a(){
        
        TreeMap tm = new TreeMap();
        for (int i = 0;i < 10; i++){
            int key = (int)(Math.random()*100)+1;
            tm.put(new MyKey(key), "treee value # " + i);
            System.out.println("key " + key + ", num: " + i);
        }
        for (Object key: tm.keySet()){
            MyKey mk =  (MyKey)key;
            System.out.println(mk.value + " " + tm.get(key));
        }
        Double a1 = 10d;
        Double a2 = 10d;
        System.out.println(a1 == a2);
    }
    
    public class MyKey implements Comparable{
        private int value;
        
        public MyKey(int value){
            this.value = value;
        }

        public int compareTo(MyKey key) {
            int comparison = 0;
            if (this.value < key.value) comparison = -1;
            if (this.value == key.value) comparison = 0;
            if (this.value > key.value) comparison = 1;
            return comparison;
        }

        public int compareTo(Object o) {
            MyKey mk;
            if (o.getClass() == MyKey.class){
                mk = (MyKey)o;
                return compareTo(mk);
            }
            return 0;
        }
        
        public int hashCode(){
            return (this.value*199);
        }
    }
    
    /** книга алгоритмы Java упражнение 1.3.4 вычисление баланса скобок на основе стеков
     * 
     *         String parentheses = "[(]])";
     *         System.out.println(b(parentheses));
     * */
    public static boolean b(String value){
        Stack<String> stack1 = new Stack<String>(); // ()
        Stack<String> stack2 = new Stack<String>(); // {}
        Stack<String> stack3 = new Stack<String>(); // []
        
        for (int i = 0; i< value.length(); i++){
            if (value.charAt(i) == '(') stack1.push(String.valueOf(value.charAt(i)));
            else if (value.charAt(i) == ')')
                if (!stack1.isEmpty()) stack1.pop(); 
                else return false;
            if (value.charAt(i) == '{') stack2.push(String.valueOf(value.charAt(i)));
            else if (value.charAt(i) == '}') 
                if (!stack2.isEmpty()) stack2.pop(); 
                else return false;
            if (value.charAt(i) == '[') stack3.push(String.valueOf(value.charAt(i)));
            else if (value.charAt(i) == ']') 
                if (!stack3.isEmpty()) stack3.pop(); 
                else return false;
        }
        return true;
    }
    
    public static int max(com.utils.Stack<Integer> stack){
        int max = 0;
        for (int i : stack)
            if (i > max) max = i;
        return max;
    }
    
    public static com.utils.Queue Josephus(int N, int M){
        com.utils.Stack<Integer> stack = new com.utils.Stack<Integer>();
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = N-1;i >= 0; i--)
            stack.push(i);
        int n = -1;
        int curSize = stack.size();
        while (stack.size() > 0){
            System.out.println("size " + stack.size());
            for (int i = 0; i < M; i++){
                n++;
                System.out.print(n + ": ");
                if (n >= stack.size()) n = n - stack.size();
            }
            System.out.print(n + "; ");
            System.out.println();
            for (int i : stack){
                System.out.print(i + ", ");
            }System.out.print("- oldstack");
            int delVal = stack.delete(n);
            System.out.println(", delVal - " + delVal);
            queue.enqueue(delVal);
            curSize--;
            //System.out.println();
            for (int i : stack){
                System.out.print(i + ", ");
            }System.out.println("- stack");
        }
        System.out.println();
        return queue;            
    }
    
    /** задача 1.3.40 Сдвиг в начало. удаление из списка повторяющихся вводимых элементов
     *  и вставка их в начала списка, показывает уникальные элементы по их 
     *  вероятности появления 
     *  
     *  main.MoveToFront("a b c a d b c r t y a d b c a");
     * */
    public void MoveToFront(String input){
        String[] values = input.split(" ");
        com.utils.Stack<String> stack = new com.utils.Stack<String>();
        for (String val : values){
            stack.findAndDelete(val);
            stack.push(val);
            System.out.println(stack);
        }
        
    }
    
    /** задача 1.3.43 Список файлов. со сдвигом при вхождении с папку
     * */
    public static Queue<File> deepIntoTheFile(File file){
        return deepIntoTheFile(file, 0);
    }
    public static Queue<File> deepIntoTheFile(File file, int deep){
        String tab = "";
        for (int i = 0; i<deep; i++) tab += "\t";
        Queue<File> queue = new Queue<File>();
        System.out.println(tab + file.getName());
        for (File f : file.listFiles()){
            queue.enqueue(f);
            if (f.isDirectory()) deepIntoTheFile(f, deep+1);
        }
        return queue;
    }

    public void runConsole(Buffer buf) {
        BufConsole console = new BufConsole();
        console.runConsole(buf);
    }
    /** задание 1.3.44. Буфер текстового редактора.
     *  реализация под консольный ввод с командами.
     *  
     *          Buffer buf = new Buffer();
     *          main.runConsole(buf);
     * */
    public static class BufConsole {
        private boolean exit = false;
        private consoleCommand command;
        private boolean isCommand = false;

        public BufConsole(){}

        public void runConsole(Buffer buf) {
            while (!exit) {
                console(buf);
            }
        }

        public void console(Buffer buf) {
            Scanner in = new Scanner(System.in);
            System.out.println(".....write your command:");
            isCommand = false;
            String type = "";
            do {
                type = String.valueOf(in.nextLine()).toLowerCase();
                setCommand(type);
            } while (!isCommand);
            if (isCommand) {
                isCommand = !isCommand;
                try {
                    Runtime.getRuntime().exec("cls");
                } catch (IOException e) {
                }
                switch (command) {
                case charSet:
                    {
                        buf.insert(type.charAt(0));
                        System.out.print(buf);
                        break;
                    }
                case left:
                    {
                        buf.left(1);
                        System.out.print(buf);
                        break;
                    }
                case right:
                    {
                        buf.right(1);
                        System.out.print(buf);
                        break;
                    } 
                case delete:
                    {
                        buf.delete();
                        System.out.print(buf);
                        break;
                    }
                case exit: {
                    System.out.println("exit ->");
                    exit = true;
                    break;
                }
                default:
                    {
                        System.out.println("Unknown Command, try more!");
                        System.out.println("You can choose one of the next values:");
                    }
                }
            }
        }

        public enum consoleCommand {
            charSet, left, right, delete, unknown, exit;
            @SuppressWarnings("compatibility:-6281473980555484343")
            private static final long serialVersionUID = 1L;
        }

        public void setCommand(String str) {
            consoleCommand command;
            try {
                command = consoleCommand.valueOf(str);
                isCommand = true;
            } catch (Exception e) {
                if (str.length() > 1) {
                    command = consoleCommand.unknown;
                    isCommand = false;
                    System.out.println("unknown command. Write 'help' for list of available command.");
                } else {
                    command = consoleCommand.charSet;
                    isCommand = true;
                }
            }
            this.command = command;
        }
    }
 
    /** задание 2.2.14 слияние упорядоченых очередей
     * 
     *         
        Queue<Integer> queue = new Queue<Integer>();
        Queue<Integer> queue2 = new Queue<Integer>();
        for (int i = 0; i < 20; i+=2){
            queue.enqueue(i);
            queue2.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(queue2);
        System.out.println(merge(queue, queue2));
     * */
    private static Queue<Integer> merge(Queue<Integer> q1, Queue<Integer> q2){
        Queue<Integer> queue = new Queue<Integer>();
        int temp1 = q1.dequeue(), temp2 = q2.dequeue(), num = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if (num == 1) temp1 = q1.dequeue();
            if (num == 2) temp2 = q2.dequeue();
            if (less(temp1, temp2)){ 
                queue.enqueue(temp1);
                num = 1;
            } else {
                queue.enqueue(temp2);
                num = 2;
            }
        }
        if (num == 1) queue.enqueue(temp2);
        if (num == 2) queue.enqueue(temp1);
        while (!q1.isEmpty()) queue.enqueue(q1.dequeue());
        while (!q2.isEmpty()) queue.enqueue(q2.dequeue());
        return queue;
    } 
    
    /** Алгоритм евклида, поиск наибольшего общего делителя 
     */
    public static int gcd(int p, int q){
        if (q==0) return p;
        int r = p % q;
        System.out.println("deep - " + q + ", " + r);
        return gcd(q,r);
    }
    
    /** Реализация бинарного поиска
     */
    public static int rank(int key, int[]a){
        // Массив должен быть отсортирован
        int lo = 0;
        int hi = a.length -1;
        while (lo <= hi)
        {   // Key находится в a[lo..hi] или отсутствует
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /** мой Алгоритм Дейкстры
     *  System.out.println(main.dexstra("(1+((2+3)*(4*5)))"));
     * */
    private Stack<Double> numStack = new Stack<Double>();
    private Stack<String> charStack = new Stack<String>();
    public double dexstra(String val){
        System.out.println(val);
        System.out.print("\t");
        for (double d : numStack) System.out.print(d + " ");
        System.out.println("\t");
        for (String s : charStack) System.out.print(s + " ");
        System.out.println();
        
        
        if (val.length() == 0) return numStack.pop();
        if (val.substring(0, 1).equals("(") || val.substring(0, 1).equals(" ")) return dexstra(val.substring(1, val.length()));
        if (val.substring(0, 1).equals(")")) {
            double b = numStack.pop();
            double a = numStack.pop();
            String act = charStack.pop();
            numStack.add(myMath(a, b, act));
            return dexstra(val.substring(1, val.length()));
        }
        if ((val.substring(0, 1).equals("+")) || (val.substring(0, 1).equals("-")) ||
            (val.substring(0, 1).equals("*")) || (val.substring(0, 1).equals("/"))) {
            charStack.add(val.substring(0, 1));
            return dexstra(val.substring(1, val.length()));  
        }
        /*int i = 1;
        while ((val.charAt(i) != '(') || (val.charAt(i) != ')') || (val.charAt(i) != '+') || 
               (val.charAt(i) != '-') || (val.charAt(i) != '*') || (val.charAt(i) != '/'))
            i++;*/
        numStack.add(Double.valueOf(val.substring(0, 1)));        
        return dexstra(val.substring(1, val.length()));
    }
    public double myMath(double a, double b, String act){
        if (act.equals("+")) return a + b;
        if (act.equals("-")) return a - b;
        if (act.equals("*")) return a * b;
        if (act.equals("/")) return a / b;
        return 0;
    }
    
    /** Задача 3.1.1, клиент таблицы имен для перевода буквенных оценов в числовые и вывод среднего.
     * */
    public static double task_3_1_1(){
        double average = 0.00;

        Scanner in = new Scanner(System.in);
        String line;
        
        ArrayST<String, Double> st;
        st = new ArrayST<String, Double>();
        st.put("A+", 4.33);
        st.put("A", 4d);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3d);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2d);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);
        System.out.print("done?");
        System.out.print(st.get("B"));
        while (!(line = in.nextLine()).equals("done")){
            if (st.get(line) != null){
                System.out.print("ave = (" + average + " + " + st.get(line) + ")/ 2 = ");
                average = (average + st.get(line))/ (average == 0 ? 1 : 2);
                System.out.println(average);
            }
            System.out.println("not found in library!");
        }

        return average;
    }
    
    /** Задача 3.1.3, клиент Реализации OrderedSequentialSearchST упорядоченной таблицы имен,
     * на основе связного списка
     * */
    public static double task_3_1_3(){
        double average = 0.00;

        System.out.println("done?");
        Scanner in = new Scanner(System.in);
        String line;
        
        OrderedSequentialSearchST<String, Double> st;
        st = new OrderedSequentialSearchST<String, Double>();
        st.put("B", 3d);
        st.put("A", 4d);
        st.put("F", 0.00);
        Iterator it_ = st.iterator();
        while (it_.hasNext()) System.out.print(it_.next() + " ");
        st.put("C+", 2.33);
        st.put("C", 2d);
        st.put("C-", 1.67);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B-", 2.67);
        st.put("A+", 4.33);
        st.put("D", 1.00);
        System.out.println(st.get("B"));
        Iterator it = st.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.print("done?");
        while (!(line = in.nextLine()).equals("done")){
            if (st.get(line) != null){
                System.out.print("ave = (" + average + " + " + st.get(line) + ")/ 2 = ");
                average = (average + st.get(line))/ (average == 0 ? 1 : 2);
                System.out.println(average);
            }
            System.out.println("not found in library!");
        }

        return average;
    }
    
}
