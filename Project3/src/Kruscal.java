import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruscal {

	static DoublyLinkedList EList = new DoublyLinkedList();
	static ArrayList<Integer> VerticesList = new ArrayList<Integer>();
	static Forrest FList;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int weightArg = 0;
		String fileArg = "smallGraph.txt";
		
		readGraphFromFile(weightArg,fileArg);

	}

	private static void readGraphFromFile(int weightArg, String fileArg) {
		String filePath= new File(fileArg).getAbsolutePath();
		Scanner sc = null;
		BQueue<String> descriptionQueue= new BQueue<String>();
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("Corpus File not found");
			e.printStackTrace();
		}
		while(sc.hasNextLine()){
			Scanner sc2 = new Scanner(sc.nextLine());
			while(sc2.hasNext()){
				descriptionQueue.enqueue(sc2.next());
			}
		}
		sc.close();

		while(descriptionQueue.getSize()!=0){
			String vertex1 = descriptionQueue.dequeue();
			String vertex2 = descriptionQueue.dequeue();
			String weight= descriptionQueue.dequeue();
			if(!VerticesList.contains(Integer.valueOf(vertex1))){
				VerticesList.add(Integer.valueOf(vertex1));
			}
			if(!VerticesList.contains(Integer.valueOf(vertex2))){
				VerticesList.add(Integer.valueOf(vertex2));
			}
			if(weight.equals(";")){
				Edge e = new Edge(vertex1, vertex2);
				EList.addItem(e);
			}
			else{
				Edge e = new Edge(vertex1, vertex2, Integer.valueOf(weight));
				EList.addItem(e);
				@SuppressWarnings("unused")
				String semiColon = descriptionQueue.dequeue();
			}
		}
		
		Collections.sort(VerticesList);
		FList = new Forrest(VerticesList);
		EList.printList();
		EList.mergeSort(EList.getFront());
		EList.printList();
		FList.printForrest();
		createNewTree(EList,FList);
	}

	private static void createNewTree(DoublyLinkedList eList2, Forrest fList2) {
		
	}

	
}
