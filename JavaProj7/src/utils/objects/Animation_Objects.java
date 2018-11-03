package src.utils.objects;

import src.utils.Anim_TypeObjectEnum;
import src.utils._BindObjectEnum;

import java.util.List;
import java.util.ArrayList;

public class Animation_Objects extends Buildable_Objects  {
    private int id;
    private Anim_TypeObjectEnum type_prev;
    private List<Anim_TypeObjectEnum> type_nexts = new ArrayList<Anim_TypeObjectEnum>();
    private Anim_TypeObjectEnum typeObject;
    private _BindObjectEnum binded_object;

    protected final static String parentTag = "animations";
    protected final static String classTag = "animation";

    public static String getParentClassTag() { return parentTag; }
    public static String getClassTag() { return classTag; }

    public Animation_Objects() { }

    public Animation_Objects(int id, Anim_TypeObjectEnum type_prev, List<Anim_TypeObjectEnum> type_nexts, Anim_TypeObjectEnum typeObject, _BindObjectEnum binded_object){
        this.id = id;
        this.type_prev = type_prev;
        this.type_nexts = type_nexts;
        this.typeObject = typeObject;
        this.binded_object = binded_object;
    }

    @Override
    public Buildable_Objects getInstance() {
        return new Animation_Objects();
    }

    public void setParam(Params param, Object value){
        if (value == null) return;
        switch (param)
        {
            case id: setId(Integer.valueOf((String)value)); break;
            case type_prev: setType_prev((Anim_TypeObjectEnum.valueOf(value.toString()))); break;
            case type_nexts: setType_nexts((Anim_TypeObjectEnum.valueOf(value.toString()))); break;
            case typeObject: setTypeObject((Anim_TypeObjectEnum.valueOf(value.toString()))); break;
            case binded_object: setBinded_object(_BindObjectEnum.valueOf(value.toString())); break;
        }
    }

    public Object getParam(Params param) {
        switch (param)
        {
            case id: return getId();
            case type_prev: return getType_prev();
            case type_nexts: return getType_nexts();
            case typeObject: return getTypeObject();
            case binded_object: return getBinded_object();
        }
        return null;
    }

    /**
     * проверка есть ли параметр в этом классе
     * */
    public boolean isParam(Params param){
        switch (param)
        {
            case id: return true;
            case type_prev: return true;
            case type_nexts: return true;
            case typeObject: return true;
            case binded_object: return true;
            default: return false;
        }
    }

    public boolean isSimpleField(Params param) {
        switch (param)
        {
            case id: return true;
            case type_prev: return true;
            case type_nexts: return false;
            case typeObject: return true;
            case binded_object: return true;
            default: return false;
        }
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Anim_TypeObjectEnum getType_prev() { return type_prev; }
    public void setType_prev(Anim_TypeObjectEnum type_prev) { this.type_prev = type_prev; }
    public List<Anim_TypeObjectEnum> getType_nexts() { return type_nexts; }
    public void setType_nexts(Anim_TypeObjectEnum type_next) { this.type_nexts.add(type_next); }
    public Anim_TypeObjectEnum getTypeObject() { return typeObject; }
    public void setTypeObject(Anim_TypeObjectEnum typeObject) { this.typeObject = typeObject; }
    public _BindObjectEnum getBinded_object(){ return binded_object; }
    public void setBinded_object(_BindObjectEnum binded_object) { this.binded_object = binded_object; }

    @Override
    protected String serializeField(Params param) {
        return null;
    }

    @Override
    protected String serializeField(Params param, int ierarh) {
        return null;
    }

    public String serializeObject() {
        return serializeObject(1);
    }

    public String serializeObject(int ierarh) {
        String ser = "<" + getClassTag() + ">\n";
        String space = "";
        for (int i = 0; i < ierarh; i++) space += "\t";

        for (Params param : Params.values())
        {
            Object obj = getParam(param);
            if (obj == null) obj = serializeField(param, ierarh + 1) + space;
            if (!obj.toString().equals(space))
                ser += space + "<" + param.toString() + ">" + obj + "</" + param.toString() + ">\n";
        }
        ser += "</" + getClassTag() + ">\n";

        return ser;
    }

}
