package src.game_objects;

import java.awt.Rectangle;
import src.math.Vector2;

public class Camera extends Thread {
    private Resources_manager resources_manager = new Resources_manager().getInstance();

    public Camera(){super();}

    public void run(){
        System.out.println("-Camera_start_stalking-");
        while (!isInterrupted()) {
            Rectangle playerRect = new Rectangle(
                    (int)resources_manager.getObjects_manager().getPlayer().getPosition().x,
                    (int)resources_manager.getObjects_manager().getPlayer().getPosition().x,
                    resources_manager.getObjects_manager().getPlayer().getImage().getWidth(),
                    resources_manager.getObjects_manager().getPlayer().getImage().getHeight());

            Rectangle newInsideRect = resources_manager.getScreen_manager().getCameraRect();
            System.out.println("newInsideRect (" + newInsideRect.x +":" + newInsideRect.y + "):(" + newInsideRect.width + ":" + newInsideRect.height + ")");
            if (overlaps(newInsideRect, playerRect)) continue;
            int deltaX = playerRect.x < newInsideRect.x ? newInsideRect.x - playerRect.x :
                    playerRect.x + playerRect.width > newInsideRect.x + newInsideRect.width ?
                            (playerRect.x + playerRect.width - newInsideRect.x - newInsideRect.width):0 ;
            //int deltaY = playerRect.y < screenRect.y ?  (int)(screenRect.y - playerRect.y) :
            //        playerRect.y + playerRect.height > screenRect.y + screenRect.height ? 0:0 ;

            System.out.print(" " + deltaX + ":" );

            resources_manager.getScreen_manager().fillCurrentScreenMap(new Vector2(deltaX, 0));
            try {
                this.sleep(400); // 200 - норма движение, больше для тестирования.
            } catch (InterruptedException e) {
            }
        }
        return;
    }

    public boolean overlaps (Rectangle r, Rectangle k) {
        return k.x < r.x + r.width && k.x + k.width > r.x && k.y < r.y + r.height && k.y + k.height > r.y;
    }
}
