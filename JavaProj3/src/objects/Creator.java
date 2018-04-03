package objects;

import client.AbstractTypeClass;

/*  часть шаблона Abstract factory
 *  позволяет создать объект определённого типа через классы ConcreteCreator
 *
 * */   
public abstract class Creator {
    public abstract AbstractTypeClass factoryMethod();
}