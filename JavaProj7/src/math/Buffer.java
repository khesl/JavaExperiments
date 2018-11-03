package src.math;

public class Buffer {
    Stack allStack = new Stack();
    Stack curStack = new Stack();
    private int size;
    private int cursor;

    public Buffer() {
        super();
    }
    public int size(){ return size; }
    public void insert(char c){
        if (allStack.size() == curStack.size()){ 
            allStack.push(c);
            curStack.push(c);
            size++;
        }
        else {
            curStack.push(c);
            Stack temp = new Stack(curStack);            
            for (int i=0; i < allStack.size() - (curStack.size()-1); i++)
                temp.push(allStack.getItem(curStack.size() + i));
            allStack = temp;
            size++;
        } 
    }
    public void left(int k){
        for (int i = 0; i < k; i++)
            curStack.pop();
    }
    public void right(int k){
        if (curStack.size() < allStack.size()){
            for (int i = 0; i < k; i++)
                curStack.push(allStack.getItem(curStack.size()+1));
        }
    }
    public void delete(){
        if (allStack.size() != 0){
            Stack temp = new Stack(curStack);            
            for (int i=1; i < allStack.size() - (curStack.size()); i++)
                temp.push(allStack.getItem(curStack.size() + i));
            allStack = temp;
            size++;
        }
    }
    
    public String toString(){
        String str = "";
        {
            Stack temp = new Stack();
            for (Object i : allStack) temp.push(i);
            for (Object o : temp) str += o;
            str += "\n";
        }
        {
            Stack temp = new Stack();
            for (Object i : curStack) str += " ";
            str = str.substring(0, str.length()-1) + "^";
            //for (Object o : temp) str += " ";
            str += "\n";
        }
        return str;
    }
}
