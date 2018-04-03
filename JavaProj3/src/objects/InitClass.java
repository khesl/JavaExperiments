package objects;

import client.AbstractTypeClass;

import java.sql.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitClass {
    public static final InitClass INSTANCE = new InitClass();    
    private Map<String, AbstractTypeClass> classMap = new HashMap<String, AbstractTypeClass>();
    TypeObj typeObj;
    private int objID;
    
    private InitClass() {
        super();
    }    
    
    private AbstractTypeClass Creator(String type){
        AbstractTypeClass localObj;
        Creator creator = null;
        TypeObj typeL = TypeObj.valueOf("unknown");
        try {
            typeL = TypeObj.valueOf(type);
        } catch (Exception e) {
            System.out.println("Unknown Type, try more!");
        }
        switch (typeL) {
        case fClass: creator = new ConcreteCreatorFClass(); break;
        default:
            {
                System.out.println("Unknown Type, try more!");
                System.out.println("You can choose one of the next values:");
                System.out.println(TypeObj.values());
            }
        }
        localObj = creator.factoryMethod();
        return localObj;
    }
    class ConcreteCreatorFClass extends Creator {
        @Override
        public AbstractTypeClass factoryMethod() { return new FirstClass(); }
    }
    
    public void initObject(String type){
        classMap.put(String.valueOf(getNewObjID()), Creator(type));
        System.out.println(type + " Object was created.");
    }
    public void initObject(TypeObj type){
        classMap.put(String.valueOf(getNewObjID()), Creator(type.toString()));
        System.out.println(type + " Object was created.");
    }
    
    public ArrayList findAllClassMapObj(String className){
        ArrayList<Integer> outputArray = new ArrayList<Integer>();
        for(int i = 0; i <= objID; i++){
            if (String.valueOf(className).equals(getClassMap().get(i).getMyName()))
                outputArray.add(i++);
        }
        return outputArray; 
    }
    
    public void RemoveObj(int objID){
        getObject(objID).killObject();
    }
    
    public AbstractTypeClass getObject(int objID){
        System.out.println("getting object id=" + objID);
        System.out.println(getClassMap().size());
        AbstractTypeClass obj = getClassMap().get(String.valueOf(objID));
        System.out.println(obj.getMyName());
        return obj;
    }
        
    public Map<String, AbstractTypeClass> getClassMap(){
        return classMap;
    }
    
    public int getObjID(){
        return objID;
    }
    public int getNewObjID(){
        return objID++;
    }
    
    
}
