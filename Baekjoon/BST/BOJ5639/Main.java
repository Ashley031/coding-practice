package BOJ5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Node root;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        root = new Node(Integer.parseInt(br.readLine()));

        String n;

        while(true){
            n = br.readLine();
            if(n == null) break;
            Node nextNode = new Node(Integer.parseInt(n));

            // 배열에 노드 배치
            putNode(nextNode, root);
        }

        postOrder(root);

    }

    static void putNode(Node newNode, Node compNode) {

        if(newNode.num < compNode.num){
            if(compNode.left == null){
                compNode.setLeft(newNode);
            }
            else putNode(newNode, compNode.left);
        }
        else{
            if(compNode.right == null){
                compNode.setRight(newNode);
            }
            else putNode(newNode, compNode.right);
        }

    }

    static void postOrder(Node node) {
        if(node.left != null) postOrder(node.left);
        if(node.right != null) postOrder(node.right);
        System.out.println(node.num);
    }

    static class Node {
        int num;
        Node left;
        Node right;

        Node(int num){
            this.num = num;
        }

        void setLeft(Node leftNode){
            this.left = leftNode;
        }

        void setRight(Node rightNode){
            this.right = rightNode;
        }
    }
}
