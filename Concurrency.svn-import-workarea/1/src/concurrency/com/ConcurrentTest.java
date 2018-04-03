package concurrency.com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTest {
    static SummProc procs;
    static private volatile List<SummProc> listProcs = new ArrayList<SummProc>();
    
    public int[] a = {5, 4, 8, 6, 2, 7, 8, 10, 4, 2, 6, 8, 1};
    public int[] b = {7, 8, 10, 4, 2, 6, 8, 1, 8, 6, 9, 18, 6};
    
    private volatile int procCount = 0;
    public volatile int calculateCount = 0;
    public volatile boolean done = false;
    public int procLimit = 5;
    
    public ConcurrentTest() {}
    
    public static void main(String[] args) {   
        ConcurrentTest m = new ConcurrentTest();
        m.start();
    }
    
    public void start() {

        List<Vector> vectors = new ArrayList<Vector>();
        for (int i = 0; i<20; i++){
            vectors.add(new Vector((int)(Math.random()*10)+1, (int)(Math.random()*10)+1));
        }
        Processor processor = new Processor("my Proc", vectors);
        processor.start();

        System.out.println("main is done.");
        try {                
            processor.join();
        } catch (InterruptedException e) {
        }

    }
    
    public SummProc getFreeProc(){
        for (SummProc proc : listProcs)
            if (proc.isFree()){
                System.out.println("* free is - " + proc.getName() + " *");
                return proc;
            }
        if (procCount < procLimit) return createNewProc();
        return null;
    }
    public SummProc createNewProc(){
            SummProc proc = new SummProc(String.valueOf(procCount)); //Создание потока
            System.out.println("\tThread started... thead count = " + procCount);
            procCount++;
            proc.start();
            listProcs.add(proc);
        return proc;
    }
    public boolean getResult(){
        for (SummProc proc : listProcs)
            if (proc.isCalculate()) { 
                Vector result = new Vector(proc.getResult());
                System.out.println("result " + result.getA() + " + " + result.getB() + " = " + result.getC() + ", vC = " + calculateCount + ": " + proc.getName());
                return true;
            }
        return false;        
    }
    public void deleteUseless(){
        for (SummProc proc : listProcs){
            System.out.println("** " + proc.getName() + " isAlive - " + proc.isAlive() + ", isSet" + proc.getVector().isSet());
            System.out.println("** " + proc.getName() + " " + proc.getVector().getA() + " " + proc.getVector().getB());
            if (proc.isFree()) {
                System.out.println("** " + proc.getName() + " stoped ");
                proc.interrupt();
    }
        }
    }

    private class Vector {
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private boolean set = false;
        private boolean calculate = false;

        public Vector(int a, int b){
            this.a = a;
            this.b = b;  
            this.set = true;
        }
        public Vector(Vector vector){
            this.a = vector.getA();
            this.b = vector.getB();  
            this.c = vector.getC();
            this.set = vector.isSet();
            this.calculate = vector.isCalculate();
        }

        public int getA() {
            return a;
        }
        public int getB() {
            return b;
        }
        public void setC(int c) {
            this.c = c;
            this.calculate = true;
        }
        public int getC() {
            return c;
        }
        public boolean isCalculate() {
            return calculate;
        }
        public boolean isSet() {
            return set;
        }
        public void clear(){
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.set = false;
            this.calculate = false;
        }
    }
    private class Processor extends Thread {
        private String procName;
        private Vector vector;
        private volatile List<Vector> vectors = new ArrayList<Vector>();
        private int calculateStart = 0;
        
        public Processor(String procName){
            this.procName = procName;
        }
        public Processor(String procName, List<Vector> vectors){
            this(procName);
            setVectors(vectors);
        }
        
        public void run() {
            do {
                if (getResult()) calculateCount++;
                if (vectors.size() > 0){
                    int iterator = vectors.size() - 1;
                    SummProc tempProc = getFreeProc();
                    if ((tempProc != null)) {
                        tempProc.setVector(vectors.get(iterator));
                        System.out.println("new calculate Start, thread name '" + tempProc.getName() + "', value " + vectors.get(iterator).getA() + ", " +vectors.get(iterator).getB());
                        calculateStart++;
                        System.out.print("calculateStart - " + calculateStart + ", calculateCount - " + calculateCount);
                        vectors.remove(iterator);
                        System.out.println(", datasize - " + vectors.size());
                    } /*else {
                        System.out.print("processors are busy");
                    }*/
                } else {
                    deleteUseless();
                    if (calculateStart == calculateCount){
                        System.out.println("All calculated: calculateStart" + calculateStart + ", calculateCount - " + calculateCount);
                        this.interrupt();
                    }
                }
                
            } while (calculateCount < 500);
        }
        
        public void setVector(ConcurrentTest.Vector vector) {
            this.vector = vector;
        }
        public ConcurrentTest.Vector getVector() {
            return vector;
        }
        public void setVectors(List<ConcurrentTest.Vector> vectors) {
            this.vectors = vectors;
        }
        public List<ConcurrentTest.Vector> getVectors() {
            return vectors;
        }
        public void addListValue(Vector vector){
            this.vectors.add(vector);
        }
    }

    private class SummProc extends Thread {
        private String name;
        private volatile boolean free = true;
        private volatile Vector vector;
        private boolean interrupted = false;
        private boolean read = false;

        public SummProc(String name){
            this.name = name;
        }

        @Override
        public void run() {
            do {
                try {
                    if (isFree()) {
                        setFree(false);
                    int minFreeze = 2;
                    int maxFreeze = 6;
                        System.out.println("** " + this.getName() + " here 1");
                    if (vector.isSet()) {
                        //System.out.println("here 2");
                        int range = (maxFreeze - minFreeze) + 1;
                        int r = (int)(Math.random() * range) + minFreeze;
                        sleep(r * 500); //Приостанавливает поток инитация вычисления
                        System.out.println("-> " + vector.getA() + " " + vector.getB());
                        vector.setC(vector.getA() + vector.getB());
                    }
                    }
                    if (isRead()) {
                        sleep(100);
                        setFree(true);
                        this.read = false;
                        System.out.println("\ttread is free..");
                        //this.interrupted = true;
                        //this.interrupt();
                    }   
                } catch (InterruptedException e) {
                    System.out.println("truble..");
                }
                //System.out.println("\t is read - " + isRead());
                
            } while (!interrupted);
            System.out.println("end!");
        }

        public Vector getResult(){
            if (isCalculate()){
                this.read = true;
                Vector temp = new Vector(vector);
                //System.out.println("\t temp result " + temp.getA() + " + " + temp.getB() + " = " + temp.getC() + ", vC = " + calculateCount + ": " + this.getName());
                vector.clear();
                return temp;
            }
            return null;
        }
        public void setFree(boolean isFree) {
            this.free = isFree;
        }
        public boolean isFree() {
            return free;
        }
        public boolean isCalculate() {
            return vector.isCalculate();
        }
        public void setVector(ConcurrentTest.Vector vector) {
            this.vector = new Vector(vector);
        }
        public ConcurrentTest.Vector getVector() {
            return vector;
        }

        public boolean isRead() {
            return read;
        }

        public String getNum() {
            return name;
        }
        }
    }


