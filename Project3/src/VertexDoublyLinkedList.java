public class VertexDoublyLinkedList {

	private VertexDoublyLinkedListNode front;
	private VertexDoublyLinkedListNode back;
	private int size;

	private class VertexDoublyLinkedListNode{

		//Variables
		private Vertex value;
		private VertexDoublyLinkedListNode next;
		private VertexDoublyLinkedListNode prev;

		//Constructor
		private VertexDoublyLinkedListNode(Vertex v){
			this.setValue(v);
		}

		public Vertex getValue() {
			return value;
		}

		public void setValue(Vertex value) {
			this.value = value;
		}

		public VertexDoublyLinkedListNode getNext() {
			return next;
		}

		public void setNext(VertexDoublyLinkedListNode next) {
			this.next = next;
		}

		public VertexDoublyLinkedListNode getPrev() {
			return prev;
		}

		public void setPrev(VertexDoublyLinkedListNode prev) {
			this.prev = prev;
		}

	}
	
	//Constructor
	public VertexDoublyLinkedList(){
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	public int getSize(){
		return this.size;
	}
	
	public VertexDoublyLinkedListNode getFront(){
		return front;
	}
	
	public VertexDoublyLinkedListNode getBack(){
		return back;
	}

	public boolean isEmpty(){
		return (this.size == 0);
	}

	public void addItem(Vertex v){
		VertexDoublyLinkedListNode newNode = new VertexDoublyLinkedListNode(v);
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

	public Vertex removeEdge(Vertex v1, Vertex v2) {
		if(size ==0){
			System.out.println("RemoveEdge: The Doubly Linked-List is empty");
			return null;
		}
		else{
			VertexDoublyLinkedListNode temp = front;
			while(temp != null){
				if((temp.getValue().getValue() == v1.getValue()) && (temp.getValue().getValue() == v2.getValue()) ){
					VertexDoublyLinkedListNode temp2 = temp.next;
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

	public Vertex removeItem(){
		if(size == 0){
			System.out.println("Remove: The Doubly Linked-List is empty");
			return null;
		}
		else if(size == 1){
			VertexDoublyLinkedListNode temp = front;
			front = null;
			back = null;
			size--;
			return temp.value;
		}

		else{
			VertexDoublyLinkedListNode temp = front;
			front = front.getNext();
			size--;
			return temp.getValue();
		}
	}

	public boolean contains(Vertex x){
		VertexDoublyLinkedListNode temp = front;
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

	public Vertex peek(){
		if(size == 0){
			System.out.println("Peek: The Doubly Linked-List is empty");
			return null;
		}
		else{
			return front.getValue();
		}
	}
	
	public Vertex getVertex(Vertex v1){
		if(size ==0){
			//System.out.println("GetEdge: The Doubly Linked-List is empty");
			return null;
		}
		else{
			VertexDoublyLinkedListNode temp = front;
			while(temp != null){
				if((temp.getValue().getValue() == v1.getValue())){
					return temp.getValue();
				}
				temp = temp.next;
			}
			return null;
		}
	}

	public void printList() {
		if(front != null){
			VertexDoublyLinkedListNode temp = front;
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
	
	public void combine(VertexDoublyLinkedList oList){
		if(this.size == 0){
			return;
		}
		else if(oList.size == 0){
			return;
		}
		else{
			this.back.next = oList.front;
		}
	}

	// Inspired by C-Based code from
	// http://www.geeksforgeeks.org/merge-sort-for-doubly-linked-list/ 
	public VertexDoublyLinkedListNode mergeSort(VertexDoublyLinkedListNode node){

		if(node == null || node.next == null){
			return node;
		}

		VertexDoublyLinkedListNode second = splitList(node);
		node = mergeSort(node);
		second = mergeSort(second);
		VertexDoublyLinkedListNode result = merge(node,second);
		//resetBackNode();
		resetFrontNode();
		return result;
	}

	@SuppressWarnings("unused")
	private void resetBackNode() {
		VertexDoublyLinkedListNode temp = front;
		while(temp.getNext() != null){
			temp = temp.getNext();
		}
		back = temp;
	}
	
	private void resetFrontNode(){
		VertexDoublyLinkedListNode temp = back;
		while(temp.getPrev() != null){
			temp = temp.getPrev();
		}
		front = temp;
	}
	private VertexDoublyLinkedListNode merge(VertexDoublyLinkedListNode one, VertexDoublyLinkedListNode two) {
		if(one == null){
			return two;
		}
		else if(two == null){
			return one;
		}

		if(one.getValue().getValue() <= two.getValue().getValue()){
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

	private VertexDoublyLinkedListNode splitList(VertexDoublyLinkedListNode head) {
		VertexDoublyLinkedListNode fast = head;
		VertexDoublyLinkedListNode slow = head;
		while((fast.next != null) && (fast.next.next != null)){
			fast = fast.next.next;
			slow = slow.next;
		}
		VertexDoublyLinkedListNode temp = slow.next;
		slow.next = null;
		return temp;
	}
}
