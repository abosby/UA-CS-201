import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Kruscal {

	static DoublyLinkedList<Edge> DList = new DoublyLinkedList<Edge>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int weightArg = 0;
		String fileArg = "testGraph.txt";
		
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
			if(weight.equals(";")){
				Edge e = new Edge(vertex1, vertex2);
				DList.addItem(e);
			}
			else{
				Edge e = new Edge(vertex1, vertex2, Integer.valueOf(weight));
				DList.addItem(e);
				String semiColon = descriptionQueue.dequeue();
			}
		}
		
		DList.printList();
	}
}
