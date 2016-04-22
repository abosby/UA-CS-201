
public class SinglyLinkedList {

	private LinkedListNode front;
	private LinkedListNode back;
	private int size;
	
	private class LinkedListNode{
		private DSRBT.RedBlackNode value;
		private LinkedListNode next;

		public DSRBT.RedBlackNode getValue() {
			return value;
		}
		public void setValue(DSRBT.RedBlackNode value) {
			this.value = value;
		}
		public LinkedListNode getNext() {
			return next;
		}
		public void setNext(LinkedListNode next) {
			this.next = next;
		}
		
		private LinkedListNode(DSRBT.RedBlackNode value){
			this.setValue(value);
			this.setNext(null);
		}
	}
	
	public SinglyLinkedList(){
		this.front = null;
		this.back = null;
		this.size = 0;
	}
	
	public LinkedListNode getFront() {
		return front;
	}

	public void setFront(LinkedListNode front) {
		this.front = front;
	}
	
	public LinkedListNode getBack(){
		return back;
	}
	
	public void setBack(LinkedListNode back){
		this.back = back;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void addItem(DSRBT.RedBlackNode v){
		LinkedListNode newNode = new LinkedListNode(v);
		if(this.size == 0){
			newNode.setNext(null);
			this.front = newNode;
			this.back = newNode;
			this.size++;
		}
		else{
			newNode.setNext(null);
			this.back.setNext(newNode);
			this.back = newNode;
			this.size++;
		}
	}
	
	public DSRBT.RedBlackNode removeItem(){
		if(size ==0){
			System.out.println("Remove: The Linked-List is empty");
			return null;
		}
		else if (size ==1){
			LinkedListNode temp = front;
			front = null;
			back = null;
			size--;
			return temp.getValue();
		}
		else{
			LinkedListNode temp = front;
			front = front.getNext();
			size--;
			return temp.getValue();
		}
	}
	
	public void printList(){
		if(front != null){
			LinkedListNode temp = front;
			while(temp != back){
				System.out.printf("[V:"+temp.getValue()+"] -->");
				temp = temp.getNext();
			}
			System.out.printf(temp.value +"\n");
		}
		else{
			System.out.println("PrintList: The Singly Linked-List is empty");
		}
	}
	
}
