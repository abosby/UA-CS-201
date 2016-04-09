
public class SinglyLinkedList {

	private LinkedListNode front;
	private LinkedListNode back;
	private int label;
	private int size;
	
	private class LinkedListNode{
		private Vertex value;
		private int weight;
		private LinkedListNode next;
		public Vertex getValue() {
			return value;
		}
		public void setValue(Vertex value) {
			this.value = value;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public LinkedListNode getNext() {
			return next;
		}
		public void setNext(LinkedListNode next) {
			this.next = next;
		}
		private LinkedListNode(Vertex v, int w){
			this.setValue(v);
			this.setWeight(w);
			this.setNext(null);
		}
	}
	
	public SinglyLinkedList(int l){
		this.label = l;
		this.front = null;
		this.size = 0;
	}
	public int getLabel(){
		return label;
	}
	
	public void setLabel(int L){
		this.label = L;
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
	
	public void addItem(Vertex v, int w){
		LinkedListNode newNode = new LinkedListNode(v,w);
		if(this.size == 0){
			newNode.setNext(null);
			this.front = newNode;
			this.size++;
		}
		else{
			newNode.setNext(null);
			this.back.setNext(newNode);
			this.back = newNode;
			this.size++;
		}
	}
	
	public LinkedListNode removeItem(){
		if(size ==0){
			System.out.println("Remove: The Linked-List is empty");
			return null;
		}
		else if (size ==1){
			LinkedListNode temp = front;
			front = null;
			back = null;
			size--;
			return temp;
		}
		else{
			LinkedListNode temp = front;
			front = front.getNext();
			size--;
			return temp;
		}
	}
	
	public void printList(){
		if(front != null){
			LinkedListNode temp = front;
			while(temp != back){
				System.out.printf("[V:"+temp.getValue()+"|W:"+temp.getWeight()+ "] -->");
				temp = temp.getNext();
			}
			System.out.printf(temp.getValue().toString()+"\n");
		}
		else{
			System.out.println("PrintList: The Singly Linked-List is empty");
		}
	}
	
}
