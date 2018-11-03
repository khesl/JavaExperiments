package src.utils;

/*public enum TypeObjectEnum{
    wall, interract, empty, air, movableObj;
    @SuppressWarnings("compatibility:-4312889595384708219")
    private static final long serialVersionUID = 1L;
}*/
public enum TypeObjectEnum {
    // nameAttr         (movable,   danger,     moveThrough,    interacting,    touchable,  description),
    wall                (false,     false,      false,          false,          false,      "стены"),
    interract           (false,     false,      true,           true),
    air                 (false,     false,      true),
    movableObj          (true,      false,      false),
    empty;

    @SuppressWarnings("compatibility:3558281649919856609")
    private static final long serialVersionUID = 1L;

    private boolean movable = false;        // движется
    private boolean danger = false;         // может наносить урон
    private boolean moveThrough = false;    // можно проходить сквозь
    private boolean interacting = false;    // можно взаимодействовать
    private boolean touchable = false;      // толкает от прикосновения
    private String description = "";        // описание
    TypeObjectEnum(){}
    TypeObjectEnum(boolean movable){
        this.movable = movable;
    }
    TypeObjectEnum(boolean movable, boolean danger) {
        this(movable);
        this.danger = danger;
    }
    TypeObjectEnum(boolean movable, boolean danger, boolean moveThrough) {
        this(movable, danger);
        this.moveThrough = moveThrough;
    }
    TypeObjectEnum(boolean movable, boolean danger, boolean moveThrough, boolean interacting) {
        this(movable, danger, moveThrough);
        this.interacting = interacting;
    }
    TypeObjectEnum(boolean movable, boolean danger, boolean moveThrough, boolean interacting, boolean touchable) {
        this(movable, danger, moveThrough, interacting);
        this.touchable = touchable;
    }
    TypeObjectEnum(boolean movable, boolean danger, boolean moveThrough, boolean interacting, boolean touchable, String description) {
        this(movable, danger, moveThrough, interacting, touchable);
        this.description = description;
    }

    public boolean isMovable() {
        return movable;
    }
    public boolean isDanger() {
        return danger;
    }
    public boolean isMoveThrough() {
        return moveThrough;
    }
    public String getDescription() {
        return description;
    }
    public boolean isInteracting() {
        return interacting;
    }
    public boolean isTouchable() {
        return touchable;
    }
}
