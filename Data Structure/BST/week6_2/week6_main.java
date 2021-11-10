package week6_2;

public class week6_main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		MyBinTree mbt = new MyBinTree("+");
		mbt.addNode("X");
		mbt.addNode("X");
		mbt.inserLeft(mbt.root().left(), "2");
		mbt.inserRight(mbt.root().left(), "-");
		mbt.inserLeft(mbt.root().left().right(), "3");
		mbt.inserRight(mbt.root().left().right(), "1");		
		mbt.inserLeft(mbt.root().right(), "3");
		mbt.inserRight(mbt.root().right(), "2");
		
		mbt.inOrder(mbt.root());
		System.out.print(" = ");
		System.out.println(mbt.postOrder(mbt.root()));

	}

}
