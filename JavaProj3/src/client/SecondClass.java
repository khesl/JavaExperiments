package client;

import java.util.Arrays;
import java.util.Scanner;

public class SecondClass extends Thread{
    private String sendParam;
    private commandClass command;
    private boolean changeParam = false;
    private boolean isCommand = false;
    private MainTestThreadClass main = new MainTestThreadClass();
    private boolean exit = false;

    public void run(){
        while (!this.isInterrupted()){
            System.out.println("You are in second Thread! '" + Thread.currentThread().getName() + "'");
            
            lifeCycle();            
        }
    }
    
    private void lifeCycle(){
        Scanner in = new Scanner(System.in);
        
        System.out.print("write your command: ");
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
                    System.out.println("  ->  Second ->  ");
                    break;
                }  
            case release: {
                    System.out.println("command exit ->");
                    this.yield();
                    break;
                }
            case exit: {
                    System.out.println("command exit ->");
                    this.interrupt();
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
        if (Integer.parseInt(st[0]) == 2) {
            this.command = command;            
        }
        isCommand = true;
    }
    
    private void OutcomeInterraction(){
        if (isCommand & (commandClass.exit.equals(this.command)))
            exit = true;    
    }

    public commandClass getCommand() {
        return command;
    }
}
