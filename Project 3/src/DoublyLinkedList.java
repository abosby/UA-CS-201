import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item>{

	private ArrayNode front;
	private ArrayNode back;
	private int size;

	private class ArrayNode{

		//Variables
		private Item value;
		private ArrayNode next;
		private ArrayNode prev;

		//Constructor
		private ArrayNode(Item v){
			this.setValue(v);
		}

		//Methods
		public Item getValue() {
			return value;
		}

		public void setValue(Item value) {
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

	public void addItem(Item v){
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
	public Item removeItem(){
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
	
	public Item peek(){
		if(size == 0){
			System.out.println("Peek: The Doubly Linked-List is empty");
			return null;
		}
		else{
			return front.getValue();
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>{
		private ArrayNode cur = front;

		@Override
		public boolean hasNext() {
			return (front.getNext() != null);
		}

		@Override
		public Item next() {
			return front.getNext().value;
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
}
