
public class DoublyLinkedList{

	private DoublyLinkedListNode front;
	private DoublyLinkedListNode back;
	private int size;

	private class DoublyLinkedListNode{

		//Variables
		private Edge value;
		private DoublyLinkedListNode next;
		private DoublyLinkedListNode prev;

		//Constructor
		private DoublyLinkedListNode(Edge v){
			this.setValue(v);
		}

		public Edge getValue() {
			return value;
		}

		public void setValue(Edge value) {
			this.value = value;
		}

		public DoublyLinkedListNode getNext() {
			return next;
		}

		public void setNext(DoublyLinkedListNode next) {
			this.next = next;
		}

		public DoublyLinkedListNode getPrev() {
			return prev;
		}

		public void setPrev(DoublyLinkedListNode prev) {
			this.prev = prev;
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
	
	public DoublyLinkedListNode getFront(){
		return front;
	}
	
	public DoublyLinkedListNode getBack(){
		return back;
	}

	public boolean isEmpty(){
		return (this.size == 0);
	}

	public void addItem(Edge v){
		DoublyLinkedListNode newNode = new DoublyLinkedListNode(v);
		if(this.size == 0){
			newNode.setPrev(null);
			newNode.setNext(null);
			this.front = newNode;
			this.back = newNode;
			this.size++;
		}
		else{
			newNode.setPrev(this.getBack());
			newNode.setNext(null);
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
			DoublyLinkedListNode temp = front;
			front = null;
			back = null;
			size--;
			return temp.value;
		}

		else{
			DoublyLinkedListNode temp = front;
			front = front.getNext();
			size--;
			return temp.getValue();
		}
	}

	public boolean contains(Edge x){
		DoublyLinkedListNode temp = front;
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
			DoublyLinkedListNode temp = front;
			while(temp != back){
				System.out.printf(temp.getValue().toString() + "<-->");
				temp = temp.getNext();
			}
			System.out.printf(temp.getValue().toString()+"\n");
		}
		else{
			System.out.println("PrintList: The Doubly Linked-List is empty");
		}
	}

	// Inspired by C-Based code from
	// http://www.geeksforgeeks.org/merge-sort-for-doubly-linked-list/ 
	public DoublyLinkedListNode mergeSort(DoublyLinkedListNode node){

		if(node == null || node.next == null){
			return node;
		}

		DoublyLinkedListNode second = splitList(node);
		node = mergeSort(node);
		second = mergeSort(second);
		DoublyLinkedListNode result = merge(node,second);
		resetBackNode();
		return result;
	}

	private void resetBackNode() {
		DoublyLinkedListNode temp = front;
		while(temp.getNext() != null){
			temp = temp.getNext();
		}
		back = temp;
	}
	private DoublyLinkedListNode merge(DoublyLinkedListNode one, DoublyLinkedListNode two) {
		if(one == null){
			return two;
		}
		else if(two == null){
			return one;
		}

		if(one.getValue().getWeight() <= two.getValue().getWeight()){
			one.next = merge(one.next, two);
			one.next.prev = one;
			one.prev = null;
			return one;
		}
		else{
			two.next = merge(one, two.next);
			two.next.prev = two;
			two.prev = null;
			return two;
		}
	}

	private DoublyLinkedListNode splitList(DoublyLinkedListNode head) {
		DoublyLinkedListNode fast = head;
		DoublyLinkedListNode slow = head;
		while((fast.next != null) && (fast.next.next != null)){
			fast = fast.next.next;
			slow = slow.next;
		}
		DoublyLinkedListNode temp = slow.next;
		slow.next = null;
		return temp;
	}
}
