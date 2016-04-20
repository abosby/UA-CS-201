
public class EdgeDoublyLinkedList{

	private EdgeDoublyLinkedListNode front;
	private EdgeDoublyLinkedListNode back;
	private int size;

	private class EdgeDoublyLinkedListNode{

		//Variables
		private Edge value;
		private EdgeDoublyLinkedListNode next;
		private EdgeDoublyLinkedListNode prev;

		//Constructor
		private EdgeDoublyLinkedListNode(Edge v){
			this.setValue(v);
		}

		public Edge getValue() {
			return value;
		}

		public void setValue(Edge value) {
			this.value = value;
		}

		public EdgeDoublyLinkedListNode getNext() {
			return next;
		}

		public void setNext(EdgeDoublyLinkedListNode next) {
			this.next = next;
		}

		public EdgeDoublyLinkedListNode getPrev() {
			return prev;
		}

		public void setPrev(EdgeDoublyLinkedListNode prev) {
			this.prev = prev;
		}

	}
	
	//Constructor
	public EdgeDoublyLinkedList(){
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	public int getSize(){
		return this.size;
	}
	
	public EdgeDoublyLinkedListNode getFront(){
		return front;
	}
	
	public EdgeDoublyLinkedListNode getBack(){
		return back;
	}

	public boolean isEmpty(){
		return (this.size == 0);
	}

	public void addItem(Edge v){
		EdgeDoublyLinkedListNode newNode = new EdgeDoublyLinkedListNode(v);
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
			EdgeDoublyLinkedListNode temp = front;
			while(temp != null){
				if((temp.getValue().getVertex1().getValue() == v1.getValue()) && (temp.getValue().getVertex2().getValue() == v2.getValue()) ){
					EdgeDoublyLinkedListNode temp2 = temp.next;
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
			EdgeDoublyLinkedListNode temp = front;
			front = null;
			back = null;
			size--;
			return temp.value;
		}

		else{
			EdgeDoublyLinkedListNode temp = front;
			front = front.getNext();
			size--;
			return temp.getValue();
		}
	}

	public boolean contains(Edge x){
		EdgeDoublyLinkedListNode temp = front;
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
			EdgeDoublyLinkedListNode temp = front;
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
			EdgeDoublyLinkedListNode temp = front;
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
	public EdgeDoublyLinkedListNode mergeSort(EdgeDoublyLinkedListNode node){

		if(node == null || node.next == null){
			return node;
		}

		EdgeDoublyLinkedListNode second = splitList(node);
		node = mergeSort(node);
		second = mergeSort(second);
		EdgeDoublyLinkedListNode result = merge(node,second);
		//resetBackNode();
		resetFrontNode();
		return result;
	}

	private void resetBackNode() {
		EdgeDoublyLinkedListNode temp = front;
		while(temp.getNext() != null){
			temp = temp.getNext();
		}
		back = temp;
	}
	
	private void resetFrontNode(){
		EdgeDoublyLinkedListNode temp = back;
		while(temp.getPrev() != null){
			temp = temp.getPrev();
		}
		front = temp;
	}
	private EdgeDoublyLinkedListNode merge(EdgeDoublyLinkedListNode one, EdgeDoublyLinkedListNode two) {
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

	private EdgeDoublyLinkedListNode splitList(EdgeDoublyLinkedListNode head) {
		EdgeDoublyLinkedListNode fast = head;
		EdgeDoublyLinkedListNode slow = head;
		while((fast.next != null) && (fast.next.next != null)){
			fast = fast.next.next;
			slow = slow.next;
		}
		EdgeDoublyLinkedListNode temp = slow.next;
		slow.next = null;
		return temp;
	}
}
