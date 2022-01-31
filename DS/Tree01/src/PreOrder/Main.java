package DS.Tree01.src.PreOrder;

/*
* Data Structure - Tree
*
* Coded by NANAEU(2021.07.29)
*/

public class Main {

    public static void main(String[] args) {

        // Preorder Traversal
        MyTree money = new MyTree();
        money.addRoot("Make Money Fast!");

        money.addNode("1. Motivations");
        money.addNode("2. Methods");
        money.addNode("References");
        MyNode[] lv1 = new MyNode[3];
        for(int i=0 ; i<3 ; i++)
            lv1[i] = money.root().children().get(i);

        money.addChild(lv1[0], "1.1 Greed");
        money.addChild(lv1[0], "1.2 Avidity");
        MyNode[] lv2_1 = new MyNode[2];
        for(int i=0 ; i<2 ; i++)
            lv2_1[i] = lv1[0].children().get(i);

        money.addChild(lv1[1], "2.1 Stock Fraud");
        money.addChild(lv1[1], "2.2 Ponzi Scheme");
        money.addChild(lv1[1], "2.3 Bank Robbery");
        MyNode[] lv2_2 = new MyNode[3];
        for(int i=0 ; i<3 ; i++)
            lv2_2[i] = lv1[1].children().get(i);

        money.root().preOrder(0);

        // Postorder Traversal
        MyTree cs = new MyTree();
        cs.addRoot("cs16/");


    }
}
