package objects;

import client.AbstractTypeClass;
import client.MainTestThreadClass;
import client.commandClass;

import java.util.Arrays;
import java.util.Scanner;

public class FirstClass extends AbstractTypeClass implements Runnable {
    Thread T;
    private String sendParam;
    private commandClass command;
    private boolean changeParam = false;
    private boolean isCommand = false;
    private MainTestThreadClass main = new MainTestThreadClass();
    private boolean wait = true;
    private boolean exit = false;
    int count = 0;
    
    private String Param1 = "firstClass";

    FirstClass() {
        T = new Thread(this, getMyName());
        System.out.println(getMyName() + " thread created: " + T);
        T.start(); 
    }

    public void run(){        
        System.out.println("You are in "+ Thread.currentThread().getName() + "  Thread!");
        while (!exit){
            
            if (T.interrupted()) OutcomeInterraction();    
            if (isCommand) OutcomeInterraction();
            if (!wait) waitClass();// !wait
            //lifeCycle();  
           initCount();
        }
    }
    
    protected void lifeCycle(){
        Scanner in = new Scanner(System.in);
        
        System.out.println(getMyName());
        System.out.print("write your command (type of request: [number of thread]:[command]): ");
        String type = String.valueOf(in.nextLine()).toLowerCase();
        setCommand(type);
        if (isCommand){
            isCommand = !isCommand;
            switch (command) {
            case start: {
                    System.out.println("command start ->");
                    break;
                }
            case whoareyou: {
                    System.out.println("command whoareyou ->");
                    System.out.println("  ->  First ->  ");
                    break;
                    }
            case count: {
                    System.out.println("command count ->");
                    System.out.println("        count: " + getCount());
                    break;
                    }
            case wait: {
                    System.out.println("command wait ->");
                    waitClass();
                    break;
                    }
            case release: {
                    System.out.println("command exit ->");
                    T.yield();
                    break;
                }
            case exit: {
                    System.out.println("command exit ->");
                    //T.interrupt();
                    break;
                }
            default:
                {
                    System.out.println("Unknown Type, try more!");
                    System.out.println("You can choose one of the next values:");
                    System.out.println(Arrays.toString(commandClass.values()));
                }
            }
        }
    }
    
    private int initCount(){
        return this.count++;
    }
    
    public void viewParams(){
        System.out.println("param: " + Param1);
    }
    
    public boolean killObject(){
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
            T.wait();
        } catch (InterruptedException e) {
            if (T.interrupted()) OutcomeInterraction();    
        }
    }
    
    public void setSendParam(String sendParam){
        this.sendParam = sendParam;
        changeParam = true;
    }

    public String getSendParam() {
        return sendParam;
    }
    
    public void setCommand(commandClass command) {
        this.command = command;
        isCommand = true;
    }
    
    public String getMyName(){
        return this.getClass().getName();
    }
    
    public void setCommand(String str) {
        commandClass command = commandClass.valueOf("unknown");
        this.command = command;
        String st[] = str.split(":");
        System.out.println("{" + st[0] + "} and {" + st[1] + "}");
        try {
            if (!st[1].isEmpty())
                command = commandClass.valueOf(st[1]);
        } catch (Exception e) {
            System.out.println("unCommon structure, [number : command]");
        }
        System.out.println(command.toString());
        if (Integer.parseInt(st[0]) == 1) { 
            this.command = command;
        }
        isCommand = true;
    }
    
    public void OutcomeInterraction(){
        lifeCycle();
        if (isCommand){            
            if (commandClass.exit.equals(this.command))
                exit = true;             
        }            
    }

    public Thread getTObj(){
        return T;
    }

    public commandClass getCommand() {
        return command;
    }
    
    public int getCount(){
        if (count > 100000 ) count = 0;
        return count;
    }

    public void setCommand() {
        System.out.println("use function with parameters 'setCommand(String str)'" +
                 " 'setCommand(commandClass command)'");
    }
}
