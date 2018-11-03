package src.utils.objects;

import src.utils.Anim_TypeObjectEnum;
import src.utils._BindObjectEnum;

import java.awt.*;

/**
 * Класс определяющий спрайты как отдельные элементы...
 * */
public class Anim_Sprite extends Buildable_Objects {
    private int id;
    private int id_in_seq = 0;
    private boolean sprite_solo_file_type = false; // если true - единичный спрайт, false - это набор
    private int width = 0;  //задаются размеры ассетов, если sprite_file_type = true
    private int height = 0; //задаются размеры ассетов, если sprite_file_type = true
    private int seq_max_val;
    private Image image;
    private Anim_TypeObjectEnum typeObject;
    private _BindObjectEnum binded_object;
    private String link;

    protected final static String parentTag = "sprites";
    protected final static String classTag = "sprite";

    public static String getParentClassTag() { return parentTag; }
    public static String getClassTag() { return classTag; }

    public Anim_Sprite() { }

    public Anim_Sprite(int id, int id_in_seq, boolean sprite_solo_file_type, int width,
                       int height, int seq_max_val, Image image, Anim_TypeObjectEnum typeObject, _BindObjectEnum binded_object){
        this.id = id;
        this.id_in_seq = id_in_seq;
        this.sprite_solo_file_type = sprite_solo_file_type;
        this.height = height;
        this.width = width;
        this.seq_max_val = seq_max_val;
        this.image = image;
        this.typeObject = typeObject;
        this.binded_object = binded_object;
        this.link = "";
    }

    @Override
    public Buildable_Objects getInstance() {
        return new Anim_Sprite();
    }

    public void setParam(Params param, Object value){
        if (value == null) return;
        switch (param)
        {
            case id: setId(Integer.valueOf((String)value)); break;
            case id_in_seq: setId_in_seq(Integer.valueOf((String)value)); break;
            case sprite_solo_file_type: setSprite_solo_file_type(Boolean.getBoolean((String)value)); break;
            case height: setHeight(Integer.valueOf((String)value)); break;
            case width: setWidth(Integer.valueOf((String)value)); break;
            case seq_max_val: setSeq_max_val(Integer.valueOf((String)value)); break;
            case image: setImage((Image)value); break;
            case typeObject: setTypeObject((Anim_TypeObjectEnum.valueOf(value.toString()))); break;
            case binded_object: setBinded_object(_BindObjectEnum.valueOf(value.toString())); break;
            case link: setLink((String)value); break;
        }
    }

    public Object getParam(Params param) {
        switch (param)
        {
            case id: return getId();
            case id_in_seq: return getId_in_seq();
            case sprite_solo_file_type: return isSprite_solo_file_type();
            case height: return getHeight();
            case width: return getWidth();
            case seq_max_val: return getSeq_max_val();
            case image: return getImage();
            case typeObject: return getTypeObject();
            case binded_object: return getBinded_object();
            case link: return getLink();
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
            case id_in_seq: return true;
            case sprite_solo_file_type: return true;
            case height: return true;
            case width: return true;
            case seq_max_val: return true;
            case image: return true;
            case typeObject: return true;
            case binded_object: return true;
            case link: return true;
            default: return false;
        }
    }

    public boolean isSimpleField(Params param) {
        switch (param)
        {
            case id: return true;
            case id_in_seq: return true;
            case sprite_solo_file_type: return true;
            case height: return true;
            case width: return true;
            case seq_max_val: return true;
            case image: return true;
            case typeObject: return true;
            case binded_object: return true;
            case link: return true;
            default: return true;
        }
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getId_in_seq() { return id_in_seq; }
    public void setId_in_seq(int id_in_seq) { this.id_in_seq = id_in_seq; }
    public boolean isSprite_solo_file_type() { return sprite_solo_file_type; }
    public void setSprite_solo_file_type(boolean sprite_solo_file_type) { this.sprite_solo_file_type = sprite_solo_file_type; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public Anim_TypeObjectEnum getTypeObject() { return typeObject; }
    public void setTypeObject(Anim_TypeObjectEnum typeObject) { this.typeObject = typeObject; }
    public int getSeq_max_val() { return seq_max_val; }
    public void setSeq_max_val(int seq_max_val) { this.seq_max_val = seq_max_val; }
    public _BindObjectEnum getBinded_object(){ return binded_object; }
    public void setBinded_object(_BindObjectEnum binded_object) { this.binded_object = binded_object; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

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