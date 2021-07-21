package boj._1406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/*
 * Linked List
 * 
 * BaekJoon 1406 - Editor
 * 
 * Coded by NANAEU	(2021.07.22)
 */

class Node {
	private Node prev;
	private Object element;
	private Node next;

	
	public void setPrev(Node n) {
		this.prev = n;
	}
	
	public void setNext(Node n) {
		this.next = n;
	}
	
	public void setElement(Object e) {
		this.element = e;
	}

	public Node getPrev() {
		return this.prev;
	}

	public Object getElement() {
		return this.element;
	}

	public Node getNext() {
		return this.next;
	}
	
	
}

class MyLinkedList{
	
	public Node header;
	public Node trailer;
	private int size;
	
	MyLinkedList(){
		this.header = new Node();
		this.trailer = new Node();
		this.header.setNext(this.trailer);
		this.trailer.setPrev(this.header);
		this.size = 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public int isEmpty() {
		if(size == 0) return 1;
		else return 0;
	}
	
	public Object getFirst() {
		return this.header.getNext().getElement();
	}
	
	public Object getLast() {
		return this.trailer.getPrev().getElement();
	}
	
	public void addFirst(Object e) {
		
		Node newNode = new Node();
		newNode.setElement(e);
		
		newNode.setPrev(this.header);
		newNode.setNext(this.header.getNext());
		
		this.header.getNext().setPrev(newNode);
		this.header.setNext(newNode);
		
		this.size++;
		
	}
	
	public void addLast(Object e) {
		Node newNode = new Node();
		newNode.setElement(e);
		
		newNode.setNext(this.trailer);
		newNode.setPrev(this.trailer.getPrev());
		
		(this.trailer.getPrev()).setNext(newNode);
		this.trailer.setPrev(newNode);
		
		this.size++;
	}
	
	public void addBefore(Node p, Object e) {
		
		Node newNode = new Node();
		newNode.setElement(e);
		
		newNode.setNext(p);
		newNode.setPrev(p.getPrev());
		
		(p.getPrev()).setNext(newNode);
		p.setPrev(newNode);
		
		this.size++;
		
	}
	
	public void addAfter(Node p, Object e) {
		
		Node newNode = new Node();
		newNode.setElement(e);
		
		newNode.setPrev(p);
		newNode.setNext(p.getNext());
		
		(p.getNext()).setPrev(newNode);
		p.setNext(newNode);
		
		this.size++;
	}
	
	public Object removeFirst() {
		
		if(size == 0) return -1;
		
		Object oldElement = this.header.getNext().getElement();
		
		this.header.getNext().getNext().setPrev(this.header);
		this.header.setNext(this.header.getNext().getNext());
		
		this.size--;
		
		return oldElement;
	}
	
	public Object removeLast() {
		
		if(size == 0) return -1;
		
		Object oldElement = this.trailer.getPrev().getElement();
		
		this.trailer.getPrev().getPrev().setNext(this.trailer);
		this.trailer.setPrev(this.trailer.getPrev().getPrev());
		
		this.size--;
		
		return oldElement;
	}
	
	public Object removeAfter(Node p) {
		if(this.size == 0) return -1;
		
		Object oldElement = p.getNext().getElement();
		
		p.getNext().getNext().setPrev(p);
		p.setNext(p.getNext().getNext());
		
		this.size--;
		return oldElement;
	}
	
	public Object removeBefore(Node p) {
		if(this.size == 0) return -1;
		
		Object oldElement = p.getPrev().getElement();
		
		p.getPrev().getPrev().setNext(p);
		p.setPrev(p.getPrev().getPrev());
		
		this.size--;
		return oldElement;
	}
	
	
	public String printAll() {
		Node cur = this.header.getNext();
		StringBuilder res = new StringBuilder();
		while(cur != this.trailer) {
			res.append(cur.getElement());
			cur = cur.getNext();
		}
		
		return res.toString();
	}
	
}



public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		MyLinkedList editor = new MyLinkedList();
		
		String str = br.readLine();
		for(int i=0 ; i<str.length() ; i++)
			editor.addLast(str.charAt(i));

		Node curNode = editor.trailer.getPrev();
		
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		String cmd = "";
		
		for(int i=0 ; i<M ; i++) {
			cmd = br.readLine();
			st = new StringTokenizer(cmd);
			String edit = st.nextToken();
			
			if(edit.equals("L")) {
				if(curNode != editor.header)
					curNode = curNode.getPrev();
			}
			else if(edit.equals("D")) {
				if(curNode.getNext() != editor.trailer)
					curNode = curNode.getNext();
			}
			else if(edit.equals("B")) {
				if(curNode != editor.header) {
					editor.removeBefore(curNode.getNext());
					curNode = curNode.getPrev();
				}
			}
			else if(edit.equals("P")) {
				char addChar = st.nextToken().charAt(0);
				editor.addAfter(curNode, addChar);
				curNode = curNode.getNext();
			}
			
		}
		
		bw.write(editor.printAll());
		bw.flush();
		bw.close();
		br.close();
	}
}
