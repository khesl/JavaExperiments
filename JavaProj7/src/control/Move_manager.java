package src.control;

import src.game_objects.Entity;
import src.game_objects.Objects_manager;
import src.game_objects.Resources_manager;
import src.utils.Anim_TypeObjectEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *  Это класс только для игрока или для всех объектов? т.к. это синглетон.. то curMove должен идти от объекта.
 * */
public class Move_manager {
    private static Move_manager instance = null;
    private boolean keyDebugMode = MyPlayState_Modificate.keyDebugMode;
    private boolean debugMode = MyPlayState_Modificate.debugMode;
    private Resources_manager resources_manager;
    private Objects_manager objects_manager;

    public Move_manager(InputMap im_, ActionMap am_){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            resources_manager = new Resources_manager().getInstance();
            objects_manager = resources_manager.getObjects_manager();
            initMove_manager(im_, am_);
        }
    }
    public Move_manager getInstance(){
        if (instance == null){
            throw new NullPointerException("Move_manager not initialized yet.");
            //new Move_manager();
            //initMove_manager();
        }
        return instance;
    }

    /** Метод для создания объектов
     * */
    private void initMove_manager(InputMap im_, ActionMap am_){
        InputMap im = im_;// (WHEN_FOCUSED);
        ActionMap am = am_;

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "onEnter");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "onUp");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "onDown");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "onLeft");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "onLeftReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "onRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "onRightReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "onSpace");


        am.put("onEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("any pressed?");
            }
        });
        am.put("onSpace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'_jump_'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" _jump_");
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.jump);
            }
        });
        am.put("onLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'<-'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" <- left?");
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.left);
            }
        });
        am.put("onLeftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'<-*'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" <- leftReleased");
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.left_release);
            }
        });
        am.put("onRight", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'->'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" -> right?");
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.right);
            }
        });
        am.put("onRightReleased", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'->*'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" -> rightReleased");
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.right_release);
            }
        });
        am.put("onUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'/|\\'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" /|\\ up?");
                //setRender(true);
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.jump);
            }
        });
        am.put("onDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (keyDebugMode && !debugMode) System.out.print("'\\|/'");
                if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode))System.out.println(" \\|/ down?");
                //setRender(false);
                move(objects_manager.getPlayer(), Anim_TypeObjectEnum.idle);
            }
        });
    }

    public Anim_TypeObjectEnum getCurMove(Entity entity) {
        return entity.getAnimator_controller().getCurrentAnimation();
    }

    public void move(Entity entity, Anim_TypeObjectEnum action) {
        if (keyDebugMode) System.out.println(action.getDescription());
        entity.move(action);
    }
}
