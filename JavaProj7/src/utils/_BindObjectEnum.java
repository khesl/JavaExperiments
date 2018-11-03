package src.utils;

public enum _BindObjectEnum {
    player              ("объект игрок"),
    global              ("объект Мир"),
    empty;

    private String description = "";        // описание
    _BindObjectEnum(){}
    _BindObjectEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
