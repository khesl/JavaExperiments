package musicexp;

import java.io.File;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.Future;

import javax.swing.JFileChooser;

public class Main {
   //FirstForm form = new FirstForm();
    
    public Main() {
        super();
    }

    public static void main(String[] args) {
        /*FirstForm form = new FirstForm();
        try {
            form.getThread().join();
            form.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i < 2; i++) {
            service.submit(new FirstForm());    
        }
        
        //FileChooserTest form2 = new FileChooserTest();
        //form2.run(new FileChooserTest(), 250, 110);
        

    }
}
