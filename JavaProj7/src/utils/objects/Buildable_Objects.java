package src.utils.objects;

public abstract class Buildable_Objects {
    protected static String parentTag;
    protected static String classTag;

    public Buildable_Objects() { super();}

    public abstract Buildable_Objects getInstance();

    public abstract void setParam(Params param, Object value);

    public abstract Object getParam(Params param);

    /**
     * проверка есть ли параметр в этом классе
     * */
    public abstract boolean isParam(Params param);

    public abstract boolean isSimpleField(Params param);

    public static String getParentClassTag() { return parentTag; }
    public static String getClassTag() { return classTag; }

    protected abstract String serializeField(Params param);
    protected abstract String serializeField(Params param, int ierarh);
    public abstract String serializeObject();
    public abstract String serializeObject(int ierarh);

    public String toString() {
        String str = "";
        for (Params param : Params.values())
            if (isParam(param)){
                if (getParam(param) != null)
                    str += param.toString() + ": " + (getParam(param).toString().length() > 50 ? getParam(param).getClass().getName() : getParam(param).toString()) + ", ";
                else str += param.toString() + ": empty, ";
            }
        return str;
    }
}
