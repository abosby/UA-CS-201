import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruscal {

	static DoublyLinkedList EList = new DoublyLinkedList();
	static ArrayList<Integer> VerticesList = new ArrayList<Integer>();
	static DisjointSet FList = new DisjointSet();
	static RedBlackTree ETree = new RedBlackTree();
	static int weightArg;
	static int rootArg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weightArg = 0;
		rootArg = 0;
		String fileArg = "testGraph.txt";
		//String fileArg = 
		
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
		processKruskal();
	}

	private static void processKruskal() {
		Collections.sort(VerticesList);

		EList.printList();
		EList.mergeSort(EList.getFront());
		EList.printList();
		//Make Set for each vertices
		for(int i = 0; i < VerticesList.size(); i++){
			Vertex temp = new Vertex(VerticesList.get(i));
			FList.makeSet(temp);
		}
		DoublyLinkedList tempEdges = new DoublyLinkedList();
		while(EList.getSize() != 0){
			Edge temp = EList.removeItem();
			tempEdges.addItem(temp);
			DisjointSet.Node vert1 = FList.getNode(temp.getVertex1());
			DisjointSet.Node vert2 = FList.getNode(temp.getVertex2());
			/**
			if(ETree.findNode(temp) == temp){
				FList.union(vert1, vert2);
				ETree.deleteNode(temp);
			}
			*/
			if(FList.findSet(vert1) != FList.findSet(vert2)){
				FList.union(vert1,vert2);
				ETree.insertNode(temp);
			}
		}
		FList.printDisjointSet();
		ETree.printBreadthTraversal(ETree.root);
		FList.printDisjointTree(rootArg,ETree,tempEdges);
	}

}
