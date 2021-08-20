package PostOrder;

/*
* Data Structure - Tree
*
* Coded by NANAEU(2021.07.29)
*/

public class Main {

    public static void main(String[] args) {

        // Postorder Traversal
        MyTree cs = new MyTree();
        cs.addRoot("cs16/");

        MyNode hw = cs.addNode("homeworks/");
        MyNode programs = cs.addNode("programs/");
        MyNode todo = cs.addNode("todo.txt", 1);

        MyNode hw1 = cs.addChild(hw, "h1c.doc", 3);
        MyNode hw2 = cs.addChild(hw, "h1nc.doc", 2);

        MyNode ddr = cs.addChild(programs, "DDR.java", 10);
        MyNode stocks = cs.addChild(programs, "Stocks.java", 25);
        MyNode robot = cs.addChild(programs, "Robot.java", 20);

        cs.root().postOrder();
    }
}
