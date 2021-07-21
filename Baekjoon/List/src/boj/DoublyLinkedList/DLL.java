package boj.DoublyLinkedList;


/*
 * Doubly Linked List
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
	
	public void printAll() {
		Node cur = this.header.getNext();
		System.out.println("===== print start =====");
		while(cur != this.trailer) {
			System.out.println(cur.getElement());
			cur = cur.getNext();
		}
	}
}

public class DLL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* TEST */
		
		MyLinkedList list = new MyLinkedList();
		
		list.addFirst(2);
		list.printAll();
		list.addFirst(4);
		list.printAll();
		list.addLast(7);
		list.printAll();
		list.addLast(8);
		list.printAll();
		list.addAfter(list.header.getNext().getNext(), 5);
		list.addBefore(list.trailer.getPrev().getPrev(), 9);
		list.printAll();
		System.out.println("Remove First : " + list.removeFirst());
		list.printAll();
		System.out.println("Remove Last : " + list.removeLast());
		list.printAll();
		System.out.println("List Size : " + list.size());
		System.out.println("Is List Empty? : " + list.isEmpty());
		
		
	}

}
