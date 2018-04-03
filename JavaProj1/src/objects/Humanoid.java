package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Humanoid extends UniqueObject {
    private static final int Damage = 1; // урон который наносит этот тип мобов
    private static final int AggroRad = 5; // агрорадиус когда заметит
    private static final int HealthPoint = 20; // агрорадиус когда заметит
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public Humanoid() {
        super(); 
        ForcedConstruct();
    }
    
    public void KillObject(){
        try {
            this.finalize();
        } catch (Throwable e) {
            log.log(Level.WARNING, "Cant Kill Object, " + this.getClass().getName() + " " + e);
        }
        log.log(Level.FINE, "Object was Killed.");
    }
    @Override
    public void WriteAllParam() {   
        System.out.println(this.getClass().getName());
        List<List<Object>> list = getAllParam();
        for (List<Object> list2 : list){
            if (!list2.isEmpty()) {
                System.out.println(list2.get(1) + ": " + list2.get(0));
            }
        }
    }
    
    @Override
    protected void ForcedConstruct(){     
        // в данном классе данные статичны, нет необходимости создавать
    }
    
    @Override
    public List<List<Object>> getAllParam() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getDamage());
        list.get(list.size()-1).add("Damage");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getAggroRad());
        list.get(list.size()-1).add("AggroRad");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getHealthPoint());
        list.get(list.size()-1).add("HealthPoint");
        
        return list;
    }
    
    public String getDescription() {
        return Damage + " " + AggroRad + " " + HealthPoint;
    }
    private int getDamage(){
        return this.Damage;
    }
    private int getAggroRad(){
        return this.AggroRad;
    }
    private int getHealthPoint(){
        return this.HealthPoint;
    }
}
