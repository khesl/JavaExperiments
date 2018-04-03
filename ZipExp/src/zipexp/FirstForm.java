package zipexp;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;


public class FirstForm extends JFrame implements Runnable{
    private Thread T;
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JLabel label1 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private XYLayout xYLayout1 = new XYLayout();
    private JTextField filename = new JTextField(), dir = new JTextField();
    JFileChooser jFC = new JFileChooser();
    private File archive;
    private File txt;

    public FirstForm() {
        T = new Thread(this.getName());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();        
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void run (){
        System.out.println("in form");
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    public void jButton1_actionPerformed(ActionEvent e){
        System.out.println("Click");
        JFileChooser c = new JFileChooser();
        c.setMultiSelectionEnabled(true);
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(FirstForm.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            label1.setText(c.getSelectedFile().getName());
            System.out.println(c.getCurrentDirectory());
            jFC.setSelectedFiles(c.getSelectedFiles());
            //txt = c.getSelectedFile();
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            label1.setText("You pressed cancel");
            
        }
    } 
    
    public void jButton3_actionPerformed(ActionEvent e){
        System.out.println("Click3 archive");
        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(FirstForm.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            label1.setText(c.getSelectedFile().getName());
            System.out.println(c.getCurrentDirectory());
            archive = c.getSelectedFile();
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            label1.setText("You pressed cancel");
            
        }
    }
    
    public void jButton2_actionPerformed(ActionEvent e){
        System.out.println("Click2");
        //String filename = "C:\\Users\\vassina\\Desktop\\project\\JavaExperiments";
               
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("tempZip.zip"));            
            for (File f : jFC.getSelectedFiles()){
                //addFileToZip(zos, f.getPath(), f.getName()); 
                add(archive.getPath(), f.getPath());
            }          
        } catch (FileNotFoundException f) {
            System.out.println(f.toString());
        } catch (IOException f) {
            System.out.println(f.toString());
        } catch (Exception f) {
            System.out.println(f.toString());
        }


        /*try {
            addFilesToExistingZip(archive, jFC.getSelectedFiles());
        } catch (IOException f) {
        }*/
        
    }

    static void add(String zipFileName, String fileName) throws IOException {
        // хитрожопый гад, создаёт новый архив, 
        // и заменяет тот что был до этого с тем же именем
        File zipFile = new File(zipFileName);
        File tmpFile = File.createTempFile("zip", "tmp");
        File newFile = new File(fileName);

        byte[] buffer = new byte[8192];
        int readed;

        ZipOutputStream zipOutputStream =
            new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFile)));
        try {
            // проверка на наличие в архиве записываемых файлов
            // почти проверка, перезаписывает внутренности архива кроме добавляемого файла
            //                                      (всё равно он его перезапишет)
            if (zipFile.exists()) {
                ZipInputStream zipInputStream = new ZipInputStream(
                    new BufferedInputStream( new FileInputStream(zipFile)));     
                try {
                    ZipEntry entry;     
                    while ((entry = zipInputStream.getNextEntry()) != null){
                        if (entry.getName().equals(newFile.getName())) {
                            System.out.println("   pass rewrite " + entry.getName());
                            continue;
                        }     
                        System.out.println("   rewriting " + entry.getName());
                        ZipEntry newEntry = new ZipEntry(entry);
                        zipOutputStream.putNextEntry(newEntry);
            
                        while ((readed = zipInputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, readed);
                        }     
                        zipOutputStream.closeEntry();
                    }
                } catch (Exception e){
                    System.out.println(e.toString());
                }
            }   
            InputStream fileInputStream =
                new BufferedInputStream(new FileInputStream(newFile));
            try {
                System.out.printf("Adding %s\n", fileName);

                ZipEntry newEntry = new ZipEntry(newFile.getName());
                newEntry.setSize(newFile.length());
                newEntry.setTime(newFile.lastModified());

                zipOutputStream.putNextEntry(newEntry);

                while ((readed = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, readed);
                }
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                System.out.println(e.toString());
            } 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        if (zipFile.exists()) { zipFile.delete(); }
        
        tmpFile.renameTo(zipFile);
    }
   
    public static void addFilesToExistingZip(File zipFile, File[] files) throws IOException {
            // get a temp file
        File tempFile = File.createTempFile(zipFile.getName(), null);
            // delete it, otherwise you cannot rename your existing zip to it.
        tempFile.delete();

        byte[] buf = new byte[1024];

        ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            boolean notInFiles = true;
            for (File f : files) {
                if (f.getName().equals(name)) {
                    notInFiles = false;
                    break;
                }
            }
            if (notInFiles) {
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(name));
                // Transfer bytes from the ZIP file to the output file
                int len;
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            entry = zin.getNextEntry();
        }
        // Close the streams        
        zin.close();
        // Compress the files
        for (int i = 0; i < files.length; i++) {
            InputStream in = new FileInputStream(files[i]);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(files[i].getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
        tempFile.delete();
    }


    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );

        this.setSize(new Dimension(210, 139));
        jPanel1.setBounds(new Rectangle(5, 5, 195, 105));

        jPanel1.setLayout(xYLayout1);
        button1.setSize(new Dimension(418, 300));
        label1.setSize(120, 20);
        label1.setText("filePath");
        label1.setVisible(true);
        
        button1.setText("выбрать");
        button1.setSize(120, 20);
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        
        button2.setText("архивировать");
        button2.setSize(120, 20);
        button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton2_actionPerformed(e);
                }
            });
        
        button3.setText("выбрать архив");
        button3.setSize(120, 20);
        button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton3_actionPerformed(e);
                }
            });
        
        jPanel1.add(button1, new XYConstraints(5, 25, 105, 20));
        jPanel1.add(label1, new XYConstraints(5, 5, 90, 15));
        jPanel1.add(button2, new XYConstraints(5, 75, 105, 20));
        jPanel1.add(button3, new XYConstraints(5, 50, 105, 20));
        this.getContentPane().add(jPanel1, null);
    }
    
    public Thread getThread(){
        return T;
    }
}
