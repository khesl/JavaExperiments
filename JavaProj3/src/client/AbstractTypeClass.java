package client;

public abstract class AbstractTypeClass implements Runnable{
    Thread T;
    String ThreadName = "class Thread";
    private commandClass command;
    private boolean isCommand = false;
    private boolean wait = true;
    private boolean exit = false;
    int count = 0;

    public AbstractTypeClass() {
        T = new Thread(this, ThreadName);
        System.out.println(ThreadName + T);
        T.start(); 
    }

    public void run(){        
        System.out.println(Thread.currentThread().getName() + " running.");
        while (!exit){            
            if (T.interrupted()) OutcomeInterraction();    
            if (isCommand) OutcomeInterraction();
            if (wait) waitClass();
            //lifeCycle();  
           initCount();
        }
    }
    
    protected abstract void lifeCycle();
    
    private int initCount(){
        return this.count++;
    }
    
    public boolean killObject() {
        try {
            this.finalize();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
    
    protected void waitClass(){
        try {
            System.out.println(Thread.currentThread().getName() + " going wait..");
            this.wait();
        } catch (InterruptedException e) {
            if (T.interrupted()) OutcomeInterraction();    
        }
    }
    
    public abstract void setCommand();
    
    public String getMyName(){
        return this.getClass().getName();
    }
    
    public abstract void viewParams();
    
    public abstract void setCommand(String str);
    
    public abstract void OutcomeInterraction();

    public Thread getTObj(){
        return T;
    }

    public commandClass getCommand() {
        return command;
    }
    
    public int getCount(){
        return count;
    }
}
