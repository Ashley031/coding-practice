package MyTree;

/*
* Data Structure - Tree
*
* Coded by NANAEU(2021.07.29)
*/

public class Main {

    public static void main(String[] args) {

        // Test - MyTree


        MyTree tree = new MyTree();
        MyNode root = tree.addRoot("Computers'R Us");
        System.out.println("[Level 0]");
        System.out.println(root.element());

        MyNode sales = tree.addNode("Sales");
        MyNode manu = tree.addNode("Manufacturing");
        MyNode rnd = tree.addNode("R&D");
        System.out.println("[Level 1]");
        System.out.println(sales.element() + ", " + manu.element() + ", " + rnd.element());

        MyNode us = tree.addChild(sales, "US");
        MyNode inter = tree.addChild(sales, "International");

        MyNode laptop = tree.addChild(manu, "Laptops");
        MyNode desktop = tree.addChild(manu, "Desktops");

        System.out.println("[Level 2]");
        System.out.println(us.element() + ", " + inter.element() + ", " + laptop.element() + ", " + desktop.element());

        MyNode europe = tree.addChild(inter, "Europe");
        MyNode asia = tree.addChild(inter, "Asia");
        MyNode canada = tree.addChild(inter, "Canada");
        System.out.println("[Level 3]");
        System.out.println(europe.element() + ", " + asia.element() + ", " + canada.element());

        System.out.println();
        System.out.println("* Tree size = Total " + tree.size() + " Nodes");


    }
}
