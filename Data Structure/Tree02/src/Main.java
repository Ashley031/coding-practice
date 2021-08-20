public class Main {
       public static void main(String[] args) {
              MyBinTree math = new MyBinTree('+');
              math.addNode('*');
              math.addNode('*');

              math.insertLeft(math.root().left(), 2);
              math.insertRight(math.root().left(), '-');
              math.insertLeft(math.root().left().right(), 3);
              math.insertRight(math.root().left().right(), 1);
              math.insertLeft(math.root().right(), 3);
              math.insertRight(math.root().right(), 2);

//              System.out.println(math.root().element());
//              System.out.println(math.root().left().element());
//              System.out.println(math.root().right().element());
//              System.out.println(math.root().left().left().element());
//              System.out.println(math.root().left().right().element());
//              System.out.println(math.root().right().left().element());
//              System.out.println(math.root().right().right().element());
//              System.out.println(math.root().left().right().left().element());
//              System.out.println(math.root().left().right().right().element());
//
//              .
//              System.out.println(math.root().right().right().left().element());
//              System.out.println(math.root().right().right().right().element());

              math.inOrder(math.root());
              System.out.print("=");
              System.out.println(math.postOrder(math.root()));
       }
}