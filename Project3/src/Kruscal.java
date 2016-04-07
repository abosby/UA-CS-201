import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Kruscal {

	static Forrest FList = new Forrest();
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
			if(weight.equals(";")){
				Edge e = new Edge(vertex1, vertex2);
				FList.insertEdge(e);
			}
			else{
				Edge e = new Edge(vertex1, vertex2, Integer.valueOf(weight));
				FList.insertEdge(e);
				String semiColon = descriptionQueue.dequeue();
			}
		}
		
		FList.printForrest();
		FList.EList.mergeSort(FList.EList.getFront());
		FList.printForrest();
	}
	
}
