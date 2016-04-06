
public class DoublyLinkedList{

	private ArrayNode front;
	private ArrayNode back;
	private int size;

	private class ArrayNode{

		//Variables
		private Edge value;
		private ArrayNode next;
		private ArrayNode prev;

		//Constructor
		private ArrayNode(Edge v){
			this.setValue(v);
		}

		public Edge getValue() {
			return value;
		}

		public void setValue(Edge value) {
			this.value = value;
		}

		public ArrayNode getNext() {
			return next;
		}

		public void setNext(ArrayNode next) {
			this.next = next;
		}

		public ArrayNode getPrev() {
			return prev;
		}

		public void setPrev(ArrayNode prev) {
			this.prev = prev;
		}

	}
	
	private class Pair{
		private ArrayNode one;
		private ArrayNode two;
		
		public Pair(ArrayNode first, ArrayNode second){
			this.one = first;
			this.two = second;
		}
		
		public ArrayNode getOne(){
			return this.one;
		}
		
		public ArrayNode getTwo(){
			return this.two;
		}
	}

	//Constructor
	public DoublyLinkedList(){
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	public int getSize(){
		return this.size;
	}

	public boolean isEmpty(){
		return (this.size == 0);
	}

	public void addItem(Edge v){
		ArrayNode newNode = new ArrayNode(v);
		if(this.size == 0){
			this.front = newNode;
			this.back = newNode;
			this.size++;
		}
		else{
			newNode.setPrev(this.back);
			newNode.setNext(this.front);
			this.back.setNext(newNode);
			this.back = newNode;
			this.size++;
		}
	}

	public Edge removeItem(){
		if(size == 0){
			System.out.println("Remove: The Doubly Linked-List is empty");
			return null;
		}
		else if(size == 1){
			ArrayNode temp = front;
			front = null;
			back = null;
			size--;
			return temp.value;
		}

		else{
			ArrayNode temp = back;
			back = back.getPrev();
			back.setNext(front);
			size--;
			return temp.getValue();
		}
	}

	public boolean contains(Edge x){
		ArrayNode temp = front;
		while(temp != back){
			if(temp.value == x){
				return true;
			}
			temp = temp.getNext();
		}
		if(back.value == x){
			return true;
		}
		return false;
	}

	public Edge peek(){
		if(size == 0){
			System.out.println("Peek: The Doubly Linked-List is empty");
			return null;
		}
		else{
			return front.getValue();
		}
	}

	public void printList() {
		if(front != null){
			ArrayNode temp = front;
			while(temp != back){
				System.out.printf(temp.getValue().toString() + "->");
				temp = temp.getNext();
			}
			System.out.printf(temp.getValue().toString()+"\n");
		}
		else{
			System.out.println("PrintList: The Doubly Linked-List is empty");
		}
	}

	// Inspired by C-Based code from
	// http://www.geeksforgeeks.org/merge-sort-for-linked-list/ 
	public ArrayNode mergeSort(ArrayNode f){
		ArrayNode head = f;
		ArrayNode a;
		ArrayNode b;

		if((head == null) || (head.getNext() == null){
			return null;
		}
	
		Pair aPair;
		aPair = splitList(head);
	}
	private Pair splitList(ArrayNode head) {
		ArrayNode front;
		ArrayNode back;
		ArrayNode fast;
		ArrayNode slow;
		
		//length < 2
		if((head == null) || (head.getNext() == null)){
			front = head;
			back = null;
		}
		else{
			slow = head;
			fast = head.getNext();
			
			while(fast != null){
				fast = fast.getNext();
				if(fast != null){
					slow = slow.getNext();
					fast = fast.getNext();
				}
			}
			
			front = head;
			back = slow.getNext();
			slow.setNext(null);
		}
		return new Pair(front, back);
	}
}
