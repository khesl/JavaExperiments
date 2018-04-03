package objects;

import java.util.List;

public abstract class UniqueObject{
    
    public UniqueObject() {
        super();
               
    }
    
    public abstract void KillObject();
    
    public abstract void WriteAllParam();    
    protected abstract void ForcedConstruct();
    public abstract List<List<Object>> getAllParam();

}

