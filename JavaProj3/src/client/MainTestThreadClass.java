package client;

import console.ConsoleEntity;

import java.util.HashMap;
import java.util.Map;

import objects.InitClass;
import objects.TypeObj;

/**
 * реализация некоторой консоли v2.. с переходами, 
 * тут рассчитано на более высокий уровень взаимодействия, многоуровневость консоли.
 * есть баги
 * 
 * теоретически можно модульно использовать где-то ещё =)
 * 
 * @author VassinAK
 * */
public class MainTestThreadClass {
    static ConsoleEntity console = ConsoleEntity.INSTANCE;
    static InitClass initClass = InitClass.INSTANCE;
    TypeObj typeObj;

    public MainTestThreadClass() {
        super();
    }
    
    public static void main(String[] args) {
        
        //initClass.initObjehelct(TypeObj.fClass);

        try {
            console.getTObj().join();
        } catch (InterruptedException e) {
            System.out.println("some error");
        }
                
        System.out.println("finish!");	
    }
    
    public ConsoleEntity getConsole(){
        return console;
    }
    public InitClass getInitClass(){
        return initClass;
    }
}
