public class Main {
    public static void main(String[] args) {
           MyBinTree math = new MyBinTree();

           MyBinNode root = math.addRoot('+');
           System.out.println("root" + root.element());

           math.addNode('*');
           math.addNode('*');

           System.out.println("level 1-1");

           math.insertLeft(math.root().left(), 2);
           math.insertRight(math.root().left(), '-');

           System.out.println("level 2-1");

           math.insertLeft(math.root().right(), 3);
           math.insertRight(math.root().right(), 2);

           System.out.println("level 2-2");

           System.out.println(math.root().left().right());

           math.insertLeft(math.root().left().right(), 3);
           math.insertRight(math.root().left().right(), 1);

           System.out.println("level 3");

           math.inOrder(math.root());

//           System.out.println(math.postOrder(math.root()));


    }
}
