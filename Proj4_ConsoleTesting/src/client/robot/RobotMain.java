package client.robot;


public class RobotMain {
    
    public RobotMain() {
        super();
    }
    
    private static MyThread myThread;

    public static void main(String[] args) {
        RobotMain rM = new RobotMain();
        //rob.setPos(5, 5);
        
        rM.run2();
        
        
    }
    
    public void run2(){
        myThread = new MyThread();       //Создание потока
        System.out.println("programm started...");
        myThread.start(); 
    }
    
    public static void printMap(char[][] map){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }
    
    private class MyThread extends Thread {
        private char[][] map = {{'#', '#', '#','#', '#', '#', '#', '#'}, 
                                {'#', '.', '.','.', '.', '@', '.', '#'}, 
                                {'#', '.', '.','.', '.', '#', '.', '#'},
                                {'#', '.', '.','.', '.', '.', '.', '#'},
                                {'#', '.', '.','@', '.', '.', '.', '#'},    
                                {'#', '#', '#','#', '#', '#', '#', '#'}};
        public MyThread(){
        }
        
        @Override
        public void run() {
            char[][] map = this.map;

            Robot rob = new Robot(map);
            rob.setPos(1, 1);

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++)
                    System.out.print(map[i][j]);
                System.out.println();
            }
            int i = 1;
            while (rob.move()) {
                
                map = rob.getMap();
                printMap(map);
                System.out.println("step " + i);
                try{
                        Thread.sleep(500);             //Приостанавливает поток на 1 секунду
                }catch(InterruptedException e){
                    System.out.println("error with comand: Thread.sleep(400);");
                }
                //String str;
                //str = sc.nextLine();
                //System.console().readLine();
                // если экран можно чистить здесь.
                i++;
            }
        }
    }
}
