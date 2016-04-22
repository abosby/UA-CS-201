import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Kruscal {

	//static EdgeDoublyLinkedList EList = new EdgeDoublyLinkedList();
	static ArrayList<Edge> EList = new ArrayList<Edge>();
	static ArrayList<Integer> VerticesList = new ArrayList<Integer>();
	static DisjointSet FList = new DisjointSet();
	static EdgeRedBlackTree ETree = new EdgeRedBlackTree();
	static HashSet<String> vSet = new HashSet<>();
	static int weightArg;
	static int rootArg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		weightArg = 0;
		rootArg = 0;
		String fileArg = "testGraph.txt";
		//String fileArg = "g5";
		//String fileArg = "graph3.txt";
		//String fileArg = "g3";

		readGraphFromFile(weightArg,fileArg);
	}

	private static void readGraphFromFile(int weightArg, String fileArg) {
		long startTime = System.currentTimeMillis();

		String filePath = new File(fileArg).getAbsolutePath();
		BufferedReader br = null;
		BQueue<String> descriptionQueue = new BQueue<String>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		Scanner sc2 = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc = new Scanner(inputStream,"UTF-8");
		while(sc.hasNextLine()){
			sc2 = new Scanner(sc.nextLine());
			while(sc2.hasNext()){
				String v1 = sc2.next();
				String v2 = sc2.next();
				String w = sc2.next();

				if(!vSet.contains(v1)){
					vSet.add(v1);
					VerticesList.add(Integer.valueOf(v1));
				}
				if(!vSet.contains(v2)){
					vSet.add(v2);
					VerticesList.add(Integer.valueOf(v2));
				}
				if(w.equals(";")){
					Edge e = new Edge(v1, v2);
					EList.add(e);
				}
				else{
					Edge e = new Edge(v1, v2, Integer.valueOf(w));
					EList.add(e);
					String semiColon = sc2.next();
				}
			}
		}
		sc2.close();
		sc.close();

		long endTime = System.currentTimeMillis();
		long result = endTime-startTime;
		float fResult = (float) result;
		System.out.printf("\nRead File Time: ");
		System.out.format("%.3f\n",fResult/1000);


		processKruskal();
	}

	@SuppressWarnings("unchecked")
	private static void processKruskal() {


		long startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis();
		long result = endTime-startTime;
		float fResult = (float) result;
		System.out.printf("\nKrusckal Process Time: ");
		System.out.format("%.3f\n",fResult/1000);


		//FList.printDisjointSet();
		//ETree.printBreadthTraversal(ETree.root);

		long sPTime = System.currentTimeMillis();

		FList.printDisjointTree(rootArg,ETree,VerticesList,tempEdges);

		long ePTime = System.currentTimeMillis();
		long pResult = ePTime - sPTime;
		float fPResult = (float) pResult;
		System.out.printf("\nPrint BFS Time: ");
		System.out.format("%.3f",fPResult/1000);



	}

}
