package DS.Tree02.src;
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

              math.inOrder(math.root());
              System.out.print("=");
              System.out.println(math.postOrder(math.root()));
       }
}