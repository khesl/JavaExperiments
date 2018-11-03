package src.utils.objects;

public class Level extends Buildable_Objects {
    private int id;
    private int height;
    private int width;
    private String map;



    protected final static String parentTag = "levels";
    protected final static String classTag = "level";

    public static String getParentClassTag() { return parentTag; }
    public static String getClassTag() { return classTag; }

    public Level() { }

    @Override
    public Buildable_Objects getInstance() {
        return new Level();
    }

    public Level(int id, int height, int width, String map)
    {
        this.id = id;
        this.height = height;
        this.width = width;
        this.map = map;
    }

    public void setParam(Params param, Object value)
    {
        if (value == null) return;
        switch (param)
        {
            case id: setId(Integer.valueOf((String)value)); break;
            case height: setHeight(Integer.valueOf((String)value)); break;
            case width: setWidth(Integer.valueOf((String)value)); break;
            case map: setMap((String)value); break;
        }
    }

    public Object getParam(Params param)
    {
        switch (param)
        {
            case id: return getId();
            case height: return getHeight();
            case width: return getWidth();
            case map: return getMap();
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
            case map: return true;
            default: return false;
        }
    }

    protected String serializeField(Params param)
    {
        return serializeField(param, 0);
    }
    protected String serializeField(Params param, int ierarh)
    {
        String space = "";
        for (int i = 0; i < ierarh; i++) space += "\t";
        switch (param)
        {

        }
        return null;
    }
    public boolean isSimpleField(Params param)
    {
        switch (param)
        {
            case id: return true;
            case height: return true;
            case width: return true;
            case map: return true;
        }
        return true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public String getMap() { return map; }
    public void setMap(String map) {
        map = map.replace("\t", "");
        map = map.replaceAll(System.getProperty("line.separator"), "");
        map = map.replace(" ", "");
        this.map = map;
    }

    public String serializeObject()
    {
        return serializeObject(1);
    }

    public String serializeObject(int ierarh)
    {
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
