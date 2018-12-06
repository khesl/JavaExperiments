package utils;

public class ServiceThread {
    public ServiceThread() {
        super();
    }

    public static void main(String[] args) {
        ServiceThread serviceThread = new ServiceThread();
        serviceThread.start();      
        
    }
    
    public void start(){


        for (int i =0; i<10; i++){
            servThread thread = new servThread(); 
            thread.start();
        }
        

        System.out.println("DiplomaProj end.");
    }
    
    
    public class servThread extends Thread{
        
        public servThread(){
        
        }
        
        public void run() {
            // вызов
            //this.getName();
            System.out.println(this.getName() + " run");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("This is: " + this.getName());
        }
    }
}
