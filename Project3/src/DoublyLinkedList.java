
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

	public Edge removeEdge(Vertex v1, Vertex v2) {
		if(size ==0){
			System.out.println("RemoveEdge: The Doubly Linked-List is empty");
			return null;
		}
		else{
			DoublyLinkedListNode temp = front;
			while(temp != null){
				if((temp.getValue().getVertex1().getValue() == v1.getValue()) && (temp.getValue().getVertex2().getValue() == v2.getValue()) ){
					DoublyLinkedListNode temp2 = temp.next;
					if(this.getSize() == 1){
						front = null;
						back = null;
						size--;
						return temp.getValue();
					}
					else if (temp == front){
						temp.next.prev = null;
						front = temp.next;
						size--;
						return temp.getValue();
					}
					else if(temp == back){
						temp.prev.next = null;
						back = temp.prev;
						size--;
						return temp.getValue();
					}
					else{
						if(temp.prev != null){
							if(temp2 != null){
								temp.prev.next = temp2;
							}
							else{
								temp.prev.next = null;
							}
						}
						if(temp2 != null){
							if(temp.prev != null){
								temp.next.prev = temp.prev;
							}
							else{
								temp.next.prev = null;
							}
						}
						size--;
						return temp.getValue();
					}
				}
				/**
				else if((temp.getValue().getVertex1().getValue() == v2.getValue()) && (temp.getValue().getVertex2().getValue() == v1.getValue())){
					DoublyLinkedListNode temp2 = temp.next;
					if(temp.prev != null){
						temp.prev.next = temp2;
					}
					if(temp.next != null){
						temp.next.prev = temp.prev;
					}
					return temp.getValue();
				}
				*/
				temp = temp.next;
			}
			return null;
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
	
	public Edge getEdge(Vertex v1, Vertex v2){
		if(size ==0){
			//System.out.println("GetEdge: The Doubly Linked-List is empty");
			return null;
		}
		else{
			DoublyLinkedListNode temp = front;
			while(temp != null){
				if((temp.getValue().getVertex1().getValue() == v1.getValue()) && (temp.getValue().getVertex2().getValue() == v2.getValue()) ){
					return temp.getValue();
				}
				else if((temp.getValue().getVertex1().getValue() == v2.getValue()) && (temp.getValue().getVertex2().getValue() == v1.getValue())){
					return temp.getValue();
				}
				temp = temp.next;
			}
			return null;
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
		//resetBackNode();
		resetFrontNode();
		return result;
	}

	private void resetBackNode() {
		DoublyLinkedListNode temp = front;
		while(temp.getNext() != null){
			temp = temp.getNext();
		}
		back = temp;
	}
	
	private void resetFrontNode(){
		DoublyLinkedListNode temp = back;
		while(temp.getPrev() != null){
			temp = temp.getPrev();
		}
		front = temp;
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
