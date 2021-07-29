package PostOrder;
import java.util.ArrayList;

public class MyNode {

    private Object element;
    private MyNode parent;
    private ArrayList<MyNode> children;
    private int size;

    // constructors
    MyNode() {
        element = null;
        parent = null;
        children = null;
        size = 0;
    }

    MyNode(Object e){
        element = e;
        parent = null;
        children = null;
        size = 0;
    }

    MyNode(Object e, int s) {
        element = e;
        parent = null;
        children = null;
        size = s;
    }

    // methods
    public Object element() {
        return this.element;
    }

    public MyNode parent() {
        return this.parent;
    }

    public ArrayList<MyNode> children() {
        return this.children;
    }

    public int degree() {
        return this.children.size();
    }

    public int getSize() { return this.size; }

    public void setElement(Object e){
        this.element = e;
    }

    public void setSize(int s) { this.size = s; }

    public void setParent(MyNode p){
        this.parent = p;
    }

    public void setChildren(ArrayList<MyNode> c){
        this.children = c;
    }

    public int postOrder(){
        int size_sum = 0;

        if(children == null) return this.size;

        for(int i=0 ; i<children.size() ; i++){
            size_sum += children.get(i).postOrder();
        }

        System.out.println(this.element + " = " + size_sum + "KB");

        return size_sum;
    }

}
