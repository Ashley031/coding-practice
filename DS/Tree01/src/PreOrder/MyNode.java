package DS.Tree01.src.PreOrder;
import java.util.ArrayList;

public class MyNode {

    private Object element;
    private MyNode parent;
    private ArrayList<MyNode> children;

    // constructors
    MyNode() {
        element = null;
        parent = null;
        children = null;
    }

    MyNode(Object e) {
        element = e;
        parent = null;
        children = null;
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

    public void setElement(Object e){
        this.element = e;
    }

    public void setParent(MyNode p){
        this.parent = p;
    }

    public void setChildren(ArrayList<MyNode> c){
        this.children = c;
    }


    public void preOrder(int level){
        for(int i=0 ; i<level ; i++)
            System.out.print("\t");

        System.out.println(this.element);

        if(this.children == null) return;

        for(int i=0 ; i<this.children.size() ; i++)
            this.children.get(i).preOrder(level+1);
    }
}
