package zipexp;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        FirstForm form = new FirstForm();
        //SwingUtilities.invokeLater(new FirstForm());
        try {
            form.getThread().join();
            form.run();
        } catch (InterruptedException e) {
        }
    }
}
