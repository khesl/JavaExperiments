package src.game_objects;

import src.utils.Anim_TypeObjectEnum;
import src.utils.ConsoleColor;
import src.utils.objects.Animation_Objects;

import java.util.*;

/**
 * Класс по функционалу повторяющий обноимённый Animator_controller в Unity.
 * позволяет перенести в него сетку анимаций и переключаться по ним через @see goNext() и @see goNext(key)
 *
 * @author khesl 10.10.2018
 * */
public class Animator_controller {
    private int id;
    private Node first;
    private Map<Anim_TypeObjectEnum, Node> key_table = new HashMap<Anim_TypeObjectEnum, Node>();
    private Node currentNode;
    private int count = 0;
    public class Node {
        Animation item;
        int key;
        private int N;
        Node back;
        private Node guessNextNode;
        List<Node> next = new ArrayList<Node>();

        public Node(){}
        public Node(Animation item){
            this.item = item;
            this.key = new Anim_control_module().getInstance().getSeq_Controller_component();
            this.back = null;
        }

        protected boolean isEmpty(){return next.size() == 0;}

        public Animation getItem(int key) {
            if (!this.isEmpty()) {
                if (this.key == key) return item;
                for (Node node : next)
                    return node.getItem(key);
            }
            return null;
        }
        public Animation getItem() {
            return item;
        }
        public Node getNode(int key){
            if (!this.isEmpty()) {
                if (this.key == key) return this;
                for (Node node : next)
                    return node.getNode(key);
            }
            return null;
        }
        public Node getNode(){return this;}

        public int size()           { return N;}
        public void addNext(Animation item){
            Node newnode = new Node();
            newnode.item = item;
            newnode.key = new Anim_control_module().getInstance().getSeq_Controller_component();
            newnode.back = this;

            this.next.add(newnode);
            setGuessNextNode(newnode);
            System.out.println("add new node to key[" + this.key + "]: " + newnode.toString());
            key_table.put(newnode.item.getTypeObject(), newnode);
            key_table.put(this.item.getTypeObject(), this);
            N++;
            count++;
        }
        public void addNext(Node newnode){
            this.next.add(newnode);
            setGuessNextNode(newnode);
            System.out.println("add new node to key[" + this.key + "]: " + newnode.toString());
            key_table.put(this.item.getTypeObject(), this);
            N++;
            count++;
        }
        private void setGuessNextNode(Node nextNode){
            if (guessNextNode == null) {guessNextNode = nextNode; return;}
            int key = nextNode.key;
            for (Node node : next)
                if (node.key < key) key = node.key;
            if (key == nextNode.key) guessNextNode = nextNode;
            else guessNextNode = getNode(key);
            return;
        }
        public Node getGuessNextNode(){return guessNextNode;}
        public void setBack(Node back){
            this.back = back;
        }

        public String toString(){
            return ConsoleColor.ANSI_BLUE +"key: " + key + ";" + ConsoleColor.ANSI_RESET +
                    " anim_type: " + ConsoleColor.setColor(item.getTypeObject().toString(), ConsoleColor.Color.ANSI_GREEN) +
                    " size: " + size() + "; " +
                    ConsoleColor.setColor("\t\tback_animation", ConsoleColor.Color.ANSI_RED) + " [" +
                    (back != null ? back.key == first.key ? ConsoleColor.setColor(" next back to root Node key: [" + back.key + "]", ConsoleColor.Color.ANSI_RED) : back.toString() : null) + "]";
        }
        public String toStringAll(){
            return toStringAll("");
        }
        private String toStringAll(String space){
            String str = space + this.toString() + "\n";
            for (Node node : next)
                if (node.key == first.key)
                    str += space + "   " + ConsoleColor.setColor("->", ConsoleColor.Color.ANSI_BLUE) + ConsoleColor.setColor(" next back to root Node key: [" + node.key + "]", ConsoleColor.Color.ANSI_RED) + "\n";
                else str += node.toStringAll(space + "   ");
            return str;
        }
    }

    public Animator_controller(){}
    public Animator_controller(Animation item){
        first = new Node(item);
        currentNode = first;
        first.back = first;
        key_table.put(first.getNode().getItem().getTypeObject(), first);
    }
    public Animator_controller(List<Animation_Objects> animation_Objects) {
        Animation_Objects rootObj = animation_Objects.get(0);
        for (Animation_Objects objects : animation_Objects)
            if (objects.getType_prev() == objects.getTypeObject()) rootObj = objects;
        if (rootObj.getType_prev() != rootObj.getTypeObject()) throw new NullPointerException("No one root animations.");

        first = new Node(new Resources_manager().getInstance().getSpriteByBindandType(rootObj.getBinded_object(), rootObj.getTypeObject()));
        currentNode = first;
        first.back = first;
        key_table.put(first.getNode().getItem().getTypeObject(), first);

        System.out.println("root: " + first.toString());
        System.out.print(findNode(Anim_TypeObjectEnum.idle));

        List<Animation_Objects> temp = new ArrayList<Animation_Objects>();
        temp.addAll(animation_Objects);
        while (!temp.isEmpty()){
            for (Animation_Objects objects : animation_Objects){
                // предок должен быть! а вот сам элемент.. уже может быть создан, причём если предок это рут и он равен
                // current, то изи создаётся для current
                System.out.println("\t" + objects.toString());
                if (objects.getType_prev() == objects.getTypeObject())
                    temp.remove(objects);
                else {
                    // если это не рут то продолжаем
                    if (findNode(objects.getType_prev()))
                        //если предок существует
                        if (!findNode(objects.getTypeObject())) {
                            getNode(objects.getType_prev())
                                .addNext(new Resources_manager().getInstance().getSpriteByBindandType(objects.getBinded_object(), objects.getTypeObject()));
                            temp.remove(objects);
                        }

                }
            }
        }

    }
    public Node getFirstNode(){return first;}

    public boolean findNode(Anim_TypeObjectEnum anim_typeObject){
        Node needNode = key_table.get(anim_typeObject);
        return needNode != null ? true : false;
    }
    public boolean findNode(int key){
        return first.getNode(key) != null ? true : false;
    }
    public int getId(){return id;}
    public boolean isEmpty()    { return first == null; }

    public Animation getItem(Anim_TypeObjectEnum anim_typeObject){
        Node needNode = key_table.get(anim_typeObject);
        return needNode.item;
    }
    public Node getNode(Anim_TypeObjectEnum anim_typeObject){
        Node needNode = key_table.get(anim_typeObject);
        return needNode;
    }
    public Node getCurrentNode(){return currentNode;}
    public void goNextNode() throws TooManyListenersException {
        if (currentNode.size() == 0) { throw new NullPointerException("No have next Node.");}
        if (currentNode.size() == 1) currentNode = currentNode.next.get(0);
        else {
            String ids = "";
            for (Node node : currentNode.next)
                ids += "\tkey[" + node.key + "],"+ " type[" + ConsoleColor.setColor(node.item.getTypeObject().toString(), ConsoleColor.Color.ANSI_GREEN)  + "]\n";
            throw new TooManyListenersException("There has more than 1 way to proceed! Please check and choose one of these:\n" + ids);
        }
    }
    public void goNextNode(int key) {
        if (first.getNode(key) != null){ currentNode = first.getNode(key); return; }
        throw new NullPointerException("Node by key[" + key + "] not found!");
    }
    public void goNextNode(Anim_TypeObjectEnum anim_typeObject) {
        if (getNode(anim_typeObject) != null){ currentNode = getNode(anim_typeObject); return; }
        throw new NullPointerException("Node by type[" + anim_typeObject + "] not found!");
    }
    public void goPrevNode (){
        currentNode = currentNode.back;
    }
}
