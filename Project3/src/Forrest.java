import java.util.ArrayList;

public class Forrest {	
	
	ArrayList<SinglyLinkedList> VList= new ArrayList<SinglyLinkedList>();
	
	public Forrest(ArrayList<Integer> iList){
		while(!iList.isEmpty()){
			SinglyLinkedList temp = new SinglyLinkedList(iList.remove(0));
			VList.add(temp);
		}
	}
	
	public void printForrest() {
		for(int i = 0; i < VList.size();i++){
			System.out.print("(" + VList.get(i).getLabel()+")");
			VList.get(i).printList();
			
		}
	}
	
}
