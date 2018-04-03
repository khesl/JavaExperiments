package synctestthread;

public class MonitorCaptureTest {

    public static void main(String[] args) {
        Object sync = new Object();
        Thread t = new Thread(new WaitingThread(sync));
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.err.println("main::Interrupted: " + ex.getMessage());
        }
        synchronized (sync) {
            System.out.println("main::Calling notify");
            sync.notify();
            System.out.println("main::Sleeping for 5 seconds");
            try {
                Thread.sleep(5000);                
            } catch (InterruptedException ex) {
                System.err.println("main::Interrupted: " + ex.getMessage());
            }
            System.out.println("main::Exiting synchronized block");
        }
    }

    static class WaitingThread implements Runnable {

        private Object sync;

        public WaitingThread(Object sync) {
            this.sync = sync;
        }

        public void run() {
            synchronized (sync) {
                System.out.println("own:: Waiting");
                try {
                    sync.wait();                  
                } catch (InterruptedException ex) {
                    System.err.println("own:: Interrupted: " + ex.getMessage());
                }
                System.out.println("own:: Running again");
            }
        }
    }
}