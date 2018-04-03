package client.robot;

import java.awt.Point;

    public class Robot {
        private String name;
        //private Point pos;
        private int x;
        private int y;
        private boolean deadEnd = false;
        private int direct = 6; //  крч по клаве?˂ 4(706)˃ 6(707) ˅ 2(708) ˄ 8(709)
        private int[] direct1 = { 60, 62, 118, 94 };//{ 706, 707, 708, 709 };
        char[][] map;
        private int turnRate = -1; // определяем правило движения, 1 направо, -1 налево.
        private final static char wall = '#';
        private final static char empty = '.';
        private final static char goal = '@';
        private final static char pass = '+';
        
        public Robot(){}
        public Robot(char[][] map){
            setMap(map);
        }
        public Robot(char[][] map, int x, int y){
            setMap(map);
            setPos(x, y);
        }

        
        public void interact(){
            System.out.println("gotIt");
            //useIt
        }
        
        public char whatIsHere(int side){
            int x = this.x;  //(int)pos.getX();
            int y = this.y;  //(int)pos.getY();
            switch  (side){
                case 4: return map[y][x-1];
                case 2: return map[y+1][x];                
                case 6: return map[y][x+1];
                case 8: return map[y-1][x];
            }
            return ' ';
        }
 
        public boolean move(){
            if (whatIsHere(direct) == goal){
                interact();
                moveForward();
                setMapElem(getPosX(), getPosY(), getEntChar());  
                return true;
            }
            if (whatIsHere(direct) == wall){
                System.out.println("i can't move here!");
                searchWay(direct);
            }
            if (whatIsHere(direct) == pass){
                System.out.println("i was here!");
                searchWay(direct);
            }
            if (whatIsHere(direct) == empty){
                moveForward();
            }
            if (whatIsHere(direct) == goal){
                interact();
                moveForward();
            }
            
            setMapElem(getPosX(), getPosY(), getEntChar());  
            if (isDeadEnd()) return false;
            return true;
        }
        
        public void moveForward(){
            setMapElem(getPosX(), getPosY(), '+');
            switch (direct){
                case 4: setPos(getPosX() - 1, getPosY()); break;
                case 2: setPos(getPosX(), getPosY() + 1); break;              
                case 6: setPos(getPosX() + 1, getPosY()); break;
                case 8: setPos(getPosX(), getPosY() - 1); break;
            }
        }
        
        public boolean searchWay(int direct){
            System.out.println("try to findWay");
            do {
                if (turnRate > 0)
                    turnRight();
                else turnLeft();
                if ((whatIsHere(this.direct) == empty) || (whatIsHere(this.direct) == goal))
                    return true;
            }
                while (direct != this.direct);
            System.out.println(" -- there no more ways");
            setDeadEnd(true);
            return false;
        }
        
        public void turnLeft(){
            switch  (direct){
                case 4: setDirect(2); break;
                case 2: setDirect(6); break;               
                case 6: setDirect(8); break;
                case 8: setDirect(4); break;
            }
        }
        public void turnRight(){
            switch  (direct){
                case 4: setDirect(8); break;
                case 2: setDirect(4); break;
                case 6: setDirect(2); break;
                case 8: setDirect(6); break;
            }
        }
        public void turnAround(){
            switch  (direct){
                case 4: setDirect(6); break;
                case 2: setDirect(8); break;               
                case 6: setDirect(4); break;
                case 8: setDirect(2); break;
            }
        }
        
        public char getEntChar(){
            switch  (direct){
                case 4: return (char)direct1[0];
                case 2: return (char)direct1[2];
                case 6: return (char)direct1[1];
                case 8: return (char)direct1[3];
            }
            return '0';
        }

        public void setDirect(int direct) {
            this.direct = direct;
        }
        public int getDirect() {
            return direct;
        }

        public void setMap(char[][] map) {
            this.map = map;
        }
        public char[][] getMap() {
            return map;
        }
        public void setMapElem(int x, int y, char el) {
            this.map[y][x] = el;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        
        public Point getPos() {
            return new Point(this.x, this.y);
        }
        public void setPos(int x, int y) {
            this.x = x;
            this.y = y;
            //pos.setLocation(x, y);
        }
       /* public void setPos(Point point) {
            pos.setLocation(point);
        }*/
        public int getPosX(){
            return this.x;  //(int)pos.getX();
        }
        public int getPosY(){
            return this.y;  //(int)pos.getY();
        }

    public void setDeadEnd(boolean deadEnd) {
        this.deadEnd = deadEnd;
    }

    public boolean isDeadEnd() {
        return deadEnd;
    }
}
