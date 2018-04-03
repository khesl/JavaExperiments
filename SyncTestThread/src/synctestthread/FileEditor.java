package synctestthread;

import javax.swing.*;

/*
 * Это пример гипотетического редактора файла, вернее, его фрагмент. 
 * В конструкторе мы запускаем поток, который загрузит содержимое этого файла. 
 * После окончания загрузки содержимое устанавливается в качестве текста компоненты, 
 * причем с гарантией выполнения этого действия в GUI-потоке. 
 * Замечу, что проверка if (SwingUtilities.isEventDispatchThread()) 
 * в данном случае всегда даст false, т.к. код всегда будет выполняться 
 * в одном, не-GUI, потоке. Однако, если есть вероятность выполнения кода как 
 * в GUI-, так и в обычном потоке, – стоит применять этот прием.
 */

public class FileEditor extends JFrame{

    private JTextArea dataField;
    private String fileName;

    public FileEditor(String fileName){
        super();
        this.fileName = fileName;
        dataField = new JTextArea(40,20);

        // ... some other initialisation

        Thread dataLoader = new Thread(new FileLoader());
        dataLoader.start();
    }

    private class FileLoader implements Runnable{

        public void run(){
            final StringBuffer content = new StringBuffer();

            //... loading file data here into buffer

            if (SwingUtilities.isEventDispatchThread()){
                dataField.setText(content.toString());
            }else{
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        dataField.setText(content.toString());
                    }
                });
            }
        }
    }
}