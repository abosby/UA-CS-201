public class Forrest {	
	
	DoublyLinkedList EList;
	
	public Forrest(){
		EList = new DoublyLinkedList();
	}
	
	public void insertEdge(Edge e){
		EList.addItem(e);
	}

	public void printForrest() {
		EList.printList();
	}
	
}
