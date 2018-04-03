package javaProj6.utils;

/*public enum TypeObject{
    wall, interract, empty, air, movableObj;
    @SuppressWarnings("compatibility:-4312889595384708219")
    private static final long serialVersionUID = 1L;
}*/
public enum TypeEntityObject {
    // nameAttr         (friend,    destroyable,interacting,touchable,  description),
    candy               (true,      false,      true,       false,      "конфеты"),
    badCandy            (false,     false,      true,       false,      "плохие конфеты"),
    empty;
    
    @SuppressWarnings("compatibility:9129232239463029107")
    private static final long serialVersionUID = 1L;

    private boolean friend = false;        // срюзный
    private boolean destroyable = false;   // может получать урон
    private boolean interacting = false;   // можно взаимодействовать
    private boolean touchable = false;     // можно прикасаться?
    private String description = "";       // описание
    TypeEntityObject(){}
    TypeEntityObject(boolean friend, boolean destroyable, boolean interacting, boolean touchable, String description){
        this.friend = friend;
        this.destroyable = destroyable;
        this.interacting = interacting;
        this.touchable = touchable;
        this.description = description;
    }

    public boolean isFriend() {
        return friend;
    }
    public boolean isDestroyable() {
        return destroyable;
    }
    public boolean isInteracting() {
        return interacting;
    }
    public boolean isTouchable() {
        return touchable;
    }
    public String getDescription() {
        return description;
    }
}
