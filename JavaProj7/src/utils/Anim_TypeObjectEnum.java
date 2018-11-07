package src.utils;

public enum Anim_TypeObjectEnum {
    // nameAttr         (description),
    intro               ("положение входа в контроллер"),
    left                ("анимация движения влево"),
    left_release        ("окончание анимации влево"),
    right               ("анимация движения вправо"),
    right_release       ("окончание анимации вправо"),
    idle                ("анимация движения покоя"),
    idle2               ("анимация движения покоя"),
    idle3               ("анимация движения покоя"),
    jump                ("анимация движения прыжка"),
    attack              ("анимация атаки"),
    attack2             ("анимация атаки"),
    attack3             ("анимация атаки"),
    falling             ("анимация падения"),
    hurt                ("анимация получения урона"),
    die                 ("анимация смерти"),
    run                 ("анимация бега"),
    tile                ("тайлы"),
    any                 ("any_state анимация"),
    stop                ("stop"),
    empty;

    private String description = "";        // описание
    Anim_TypeObjectEnum(){}
    Anim_TypeObjectEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
