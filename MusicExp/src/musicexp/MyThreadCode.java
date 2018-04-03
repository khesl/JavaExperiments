package musicexp;

public class MyThreadCode implements Runnable{
    private Thread T;
    private boolean interrupt = false;
    
    public MyThreadCode(String threadName) {
        T = new Thread(threadName);
    }
    
    public void run(){
        while (!interrupt){
            System.out.println("here " + T.getName());
        }
    }
    
    public void interrupted(){
    
    }
}
