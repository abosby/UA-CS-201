import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;

public class Kruscal {

	//static EdgeDoublyLinkedList EList = new EdgeDoublyLinkedList();
	static ArrayList<Edge> EList = new ArrayList<Edge>();
	static ArrayList<Integer> VerticesList = new ArrayList<Integer>();
	static DisjointSet FList = new DisjointSet();
	static EdgeRedBlackTree ETree = new EdgeRedBlackTree();
	static int weightArg;
	static int rootArg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weightArg = 0;
		rootArg = 0;
		String fileArg = "testGraph.txt";
		//String fileArg = "g3";

		final float startTime = System.currentTimeMillis()/1000.0f;
		readGraphFromFile(weightArg,fileArg);
		final float endTime = System.currentTimeMillis()/1000.0f;
		System.out.printf("\nTime: ");
		System.out.format("%.3f",(endTime - startTime));

	}

	private static void readGraphFromFile(int weightArg, String fileArg) {
		String filePath= new File(fileArg).getAbsolutePath();
		BufferedReader br = null;
		BQueue<String> descriptionQueue= new BQueue<String>();
		String line;
		try {
			br = new BufferedReader(new FileReader(filePath));
			while((line = br.readLine()) != null){
				//while(sc.hasNextLine()){
				//sc = new Scanner(new File(filePath));
				/**
				Scanner sc2 = new Scanner(sc.nextLine());
				while(sc2.hasNext()){
					descriptionQueue.enqueue(sc2.next());
				}
				 */
				String[] tokens = line.split(" ");
				for (String token : tokens){
					if(!token.equals("")){
						descriptionQueue.enqueue(token);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Corpus File not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


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
				EList.add(e);
			}
			else{
				Edge e = new Edge(vertex1, vertex2, Integer.valueOf(weight));
				EList.add(e);
				@SuppressWarnings("unused")
				String semiColon = descriptionQueue.dequeue();
			}
		}
		processKruskal();
	}

	@SuppressWarnings("unchecked")
	private static void processKruskal() {
		Collections.sort(VerticesList);

		//EList.printList();
		ComparatorEdge comp = new ComparatorEdge();
		Collections.sort(EList, comp);
		//EList.printList();
		//Make Set for each vertices

		for(int i = 0; i < VerticesList.size(); i++){
			Vertex temp = new Vertex(VerticesList.get(i));
			FList.makeSet(temp);
		}

		EdgeDoublyLinkedList tempEdges = new EdgeDoublyLinkedList();
		while(EList.size() != 0){
			Edge temp = EList.remove(0);
			tempEdges.addItem(temp);
			if(ETree.findNode(temp) == null){
				DSRBT.RedBlackNode vert1 = FList.getNode(temp.getVertex1());
				DSRBT.RedBlackNode vert2 = FList.getNode(temp.getVertex2());

				if(FList.findSet(vert1) != FList.findSet(vert2)){
					FList.union(vert1,vert2);
					ETree.insertNode(temp);
				}
			}
		}
		//FList.printDisjointSet();
		//ETree.printBreadthTraversal(ETree.root);
		FList.printDisjointTree(rootArg,ETree,VerticesList,tempEdges);
	}

}
