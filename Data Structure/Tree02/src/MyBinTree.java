import java.util.ArrayList;

class TwoChildrenException extends Exception{
    TwoChildrenException(String msg){
        super(msg);
    }
}

class NotExternalException extends Exception{
    NotExternalException(String msg){
        super(msg);
    }
}


public class MyBinTree extends MyTree {

    MyBinTree() {
        super();
    }

    MyBinTree(Object e){
        super(e);
        super.root().children().add(null);
        super.root().children().add(null);
    }

    public boolean isEmpty(){
        return super.size() == 0;
    }

    public boolean isRoot(MyBinNode v){
        return v.parent() == null;
    }

    public boolean isInternal(MyBinNode v){
        return !this.isExternal(v);
    }

    public boolean isExternal(MyBinNode v){
        if(!this.hasLeft(v) && !this.hasRight(v))
            return true;
        else
            return false;
    }

    public MyBinNode root(){
        return (MyBinNode) super.root();
    }

    public MyBinNode parent(MyBinNode v){
        return (MyBinNode) v.parent();
    }

    public MyBinNode left(MyBinNode v){
        return (MyBinNode) v.children().get(0);
    }

    public MyBinNode right(MyBinNode v){
        return (MyBinNode) v.children().get(1);
    }

    public boolean hasLeft(MyBinNode v){ return v.children().get(0) != null; }

    public boolean hasRight(MyBinNode v){
        return v.children().get(1) != null;
    }

    public MyBinNode addRoot(Object e){
        MyBinNode rootNode = (MyBinNode) super.addRoot(e);
        super.root().children().add(null);
        super.root().children().add(null);
        return rootNode;
    }

    public MyBinNode addNode(Object e){
        MyBinNode return_node = null;

        /*
        * Case 1 : Left, Right exist
        * Case 2 : Only Left exists
        * Case 3 : Only Right exists
        * Case 4 : No Children
        *
        * Rule : Binary Tree - ALWAYS LEFT CHILD FIRST
        *
        * Written bt NANAEU
        * */

        // Case 1 : Left, Right exist
        if(this.hasLeft((MyBinNode) super.root()) && this.hasRight((MyBinNode) super.root())){
            System.out.println("Case 1 : Cannot add more than 2 Child - Already FULL");
        }
        // Case 2 : Only Left exists
        else if(this.hasLeft((MyBinNode) super.root())) {
            System.out.println("Case 2 : Only Left child exists - add right child node");

            // null value remove
            super.removeChild(super.root(), 1);

            MyBinNode newNode = (MyBinNode) super.addNode(e);
            newNode.children().add(null);
            newNode.children().add(null);

            return_node = newNode;

        }
        // Case 3 : Only Right exists
        else if(this.hasRight((MyBinNode) super.root())){
            System.out.println("Case 3 : Only Right child exists - remove right node and add 2");

            MyBinNode temp = (MyBinNode) super.removeChild(super.root(), 1);

            MyBinNode newNode = (MyBinNode) super.addNode(e);
            newNode.children().add(null);
            newNode.children().add(null);

            MyBinNode rightNode = (MyBinNode) super.addNode(temp);
            rightNode.children().add(null);
            rightNode.children().add(null);

            return_node = newNode;
        }
        // Case 4 : No Children
        else {
            System.out.println("Case 4 : No Children");
            super.removeChild(super.root(), 0);
            super.removeChild(super.root(), 0);

            MyBinNode newNode = (MyBinNode) super.addNode(e);
            newNode.children().add(null);
            newNode.children().add(null);

            super.root().children().add(null);

            return_node = newNode;
        }

        return return_node;
    }

    public MyBinNode insertLeft(MyBinNode v, Object e){
        MyBinNode newLeft = null;

        if(!this.hasLeft(v)){
            newLeft = (MyBinNode) super.setChild(v, 0, e);
            newLeft.children().add(null);
            newLeft.children().add(null);
        }

        return newLeft;
    }

    public MyBinNode insertRight(MyBinNode v, Object e){
        MyBinNode newRight = null;

        if(!this.hasRight(v)){
            newRight = (MyBinNode) super.setChild(v, 1, e);
            newRight.children().add(null);
            newRight.children().add(null);
        }

        return newRight;
    }

    public Object replace(MyBinNode v, Object e){
        Object oldE = v.element();
        v.setElement(e);
        return oldE;
    }

    public MyBinNode remove(MyBinNode v) throws TwoChildrenException {

        MyBinNode parent = (MyBinNode) v.parent();
        int idx = 0;

        if(this.left(parent) == v)
            idx = 0;        // v가 parent의 left child
        else
            idx = 1;        // v가 parent의 right child

        // Case 1 : two children
        if(this.hasLeft(v) && this.hasRight(v)){
            throw new TwoChildrenException("TwoChildrenException...");
        }
        // Case 2 : only left
        else if(this.hasLeft(v)){
            MyBinNode child = (MyBinNode) v.children().get(0);
            parent.children().set(idx, child);
            child.setParent(parent);
        }
        // Case 3 : only right
        else if(this.hasRight(v)){
            MyBinNode child = (MyBinNode) v.children().get(1);
            parent.children().set(idx, child);
            child.setParent(parent);
        }
        // Case 4 : no children
        else {
            parent.children().set(idx, null);
        }
        return v;
    }

    public void attach(MyBinNode v, MyBinNode t1, MyBinNode t2) throws NotExternalException{
        if(this.isExternal(v)){
            this.insertLeft(v, t1.element());
            this.insertRight(v, t2.element());
        }
        else{
            throw new NotExternalException("NotExternalException...");
        }
    }

    public void inOrder(MyBinNode v) {
        if(this.hasLeft(v)){
            System.out.print('(');
            inOrder(this.left(v));
        }

        System.out.print(v.element()+"");

        if(this.hasRight(v)){
            inOrder(this.right(v));
            System.out.print(')');
        }
    }

    public int postOrder(MyBinNode v) {

        int result = 0;

        if(isExternal(v)){
            return Integer.parseInt(v.element().toString());
        }
        else {
            int x = postOrder(v.left());
            int y = postOrder(v.right());

            switch(v.element().toString()){
                case "+":
                    result = x + y;
                    break;
                case "-":
                    result = x - y;
                    break;
                case "*":
                    result = x * y;
                    break;
                case "X":
                    result = x * y;
                    break;
            }
            return result;
        }

    }
}
