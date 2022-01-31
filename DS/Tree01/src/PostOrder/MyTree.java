package DS.Tree01.src.PostOrder;

import java.util.ArrayList;

public class MyTree {

    private MyNode root;
    private int size;

    // constructors
    MyTree() {
        this.root = null;
        this.size = 0;
    }
    MyTree(Object e) {
        this.root = new MyNode(e);
        this.root.setChildren((new ArrayList<MyNode>()));
        this.size = 1;
    }

    // methods
    public int size() {
        return this.size;
    }

    public MyNode root() {
        return this.root;
    }

    public ArrayList children(MyNode v){
        return v.children();
    }

    public boolean isExternal(MyNode v){
        return v.children().size() == 0;
    }

    public MyNode addRoot(Object e){
        this.root = new MyNode(e);;
        this.root.setChildren(new ArrayList<MyNode>());
        this.size = 1;
        return this.root;
    }
    public MyNode addNode(Object e){
        MyNode newNode = new MyNode(e);
        newNode.setParent(this.root);
        this.root.children().add(newNode);
        this.size++;
        return newNode;
    }
    public MyNode addNode(Object e, int s){
        MyNode newNode = new MyNode(e,s);
        newNode.setParent(this.root);
        this.root.children().add(newNode);
        this.size++;
        return newNode;
    }


    public MyNode addChild(MyNode v, Object e){
        if(v.children() == null)
            v.setChildren(new ArrayList<MyNode>());

        MyNode newNode = new MyNode(e);
        newNode.setParent(v);
        v.children().add(newNode);
        this.size++;
        return newNode;
    }

    public MyNode addChild(MyNode v, Object e, int s){
        if(v.children() == null)
            v.setChildren(new ArrayList<MyNode>());

        MyNode newNode = new MyNode(e, s);
        newNode.setParent(v);
        v.children().add(newNode);
        this.size++;
        return newNode;
    }

    public MyNode addChild(MyNode v, int i, Object e){
        MyNode newNode = new MyNode(e);
        newNode.setParent(v);
        v.children().add(i, newNode);
        this.size++;
        return newNode;
    }

    public MyNode addChild(MyNode v, int i, Object e, int s){
        MyNode newNode = new MyNode(e, s);
        newNode.setParent(v);
        v.children().add(i, newNode);
        this.size++;
        return newNode;
    }

    public MyNode setChild(MyNode v, int i, Object e) {
        MyNode setNode = v.children().get(i);
        v.children().set(i, new MyNode(e));
        return setNode;
    }

    public MyNode setChild(MyNode v, int i, Object e, int s) {
        MyNode setNode = v.children().get(i);
        v.children().set(i, new MyNode(e, s));
        return setNode;
    }

    public MyNode removeChild(MyNode v, int i){
        MyNode removeNode = v.children().get(i);
        v.children().remove(i);
        this.size--;
        return removeNode;
    }
}
