package src.utils.objects;

public class Screen_Object extends Buildable_Objects{
    private int id;
    private int width;
    private int height;
    private String title;

    protected final static String parentTag = "screens";
    protected final static String classTag = "screen";

    public static String getParentClassTag() { return parentTag; }
    public static String getClassTag() { return classTag; }

    public Screen_Object() { }

    public Screen_Object(int id, int width, int height, String title){
        this.id = id;
        this.height = height;
        this.width = width;
        this.title = title;
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
            case height: setHeight(Integer.valueOf((String)value)); break;
            case width: setWidth(Integer.valueOf((String)value)); break;
            case title: setTitle((String)value); break;
        }
    }

    public Object getParam(Params param) {
        switch (param)
        {
            case id: return getId();
            case height: return getHeight();
            case width: return getWidth();
            case title: return getTitle();
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
            case height: return true;
            case width: return true;
            case title: return true;
            default: return false;
        }
    }

    public boolean isSimpleField(Params param) {
        switch (param)
        {
            case id: return true;
            case height: return true;
            case width: return true;
            case title: return true;
            default: return true;
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

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
