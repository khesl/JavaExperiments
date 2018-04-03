package synctestthread;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReentranceTest {

    static final DateFormat FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void main(String[] args) {
        SynchronizedObject object = new SynchronizedObject();
        object.setValue(1);
        Thread t = new Thread(new ProcessingThread(object));
        t.start();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){
            System.err.println("main::Interrupted: "+ex.getMessage());
        }
        log("main::Setting value");
        object.setValue(2);
        log("main::Value set");
    }

    static void log(String msg){
        System.out.println(FORMAT.format(new Date())+": "+msg);
    }

    static class SynchronizedObject{

        private int value;

        public synchronized void setValue(int value){
            this.value = value;
        }

        public synchronized void process(){
            log("own:: value: "+value);
            try{
                log("own:: Sleeping");
                Thread.sleep(2000);
                log("own:: Waiting");
                wait(1000);
            }catch(InterruptedException ex){
                System.err.println("own:: Interrupted: "+ex.getMessage());
            }
            log("own:: value: "+value);
        }
    }

    static class ProcessingThread implements Runnable{

        private SynchronizedObject object;

        public ProcessingThread(SynchronizedObject object){
            this.object = object;
        }

        public void run() {
            object.process();
        }
    }
}