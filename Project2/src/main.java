package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class main {

	static BinarySearchTree BST;
	static RBTree RBT;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int treeType;
		treeType = 2;
		if(treeType == 1){
			BST = new BinarySearchTree();
		}
		else if(treeType ==2){
			RBT = new RBTree();
		}
		//readCorpus(args[1]);
		//readCorpus("inputCorpus.txt",treeType);
		//readCorpus("mobyDickText.txt",treeType);
		readCorpus("mediumMobyDick.txt",treeType);
		//readCorpus("corpusPA",treeType);
		//readCorpus("smallMobyDick.txt",treeType);
		//readCommands(args[2]);

	}

	private static void readCommands(String commandFile, int treeType) {
		String commandPath = new File("src/"+commandFile).getAbsolutePath();
		Scanner sc = null;
		Queue<String> commandQ = new LinkedList<String>();
		try {
			sc = new Scanner(new File (commandPath));
		} catch (FileNotFoundException e) {
			System.out.println("Corpus File not found");
			e.printStackTrace();
		}
		while(sc.hasNextLine()){
			Scanner sc2 = new Scanner(sc.nextLine());
			while(sc2.hasNext()){
				commandQ.add(sc2.next());
			}
		}
		sc.close();

		String command1;
		String command2;
		if(treeType == 1){
			while(!commandQ.isEmpty()){
				command1 = commandQ.poll();
				if(command1.equals("i")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						BST.insertNode(command2);
					}
				}
				else if(command1.equals("d")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						BST.deleteNode(command2);
					}
				}
				else if(command1.equals("f")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						BST.findNode(command2);
					}
				}
				else if(command1.equals("s")){
					BST.printBreadthTraversal(BST.getRoot());
				}
				else if(command1.equals("r")){
					BST.printStatistics();
				}
				else{

				}
			}
		}
		else if(treeType == 2){
			while(!commandQ.isEmpty()){
				command1 = commandQ.poll();
				if(command1.equals("i")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						RBT.insertNode(command2);
					}
				}
				else if(command1.equals("d")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						RBT.deleteNode(command2);
					}
				}
				else if(command1.equals("f")){
					command2 = scrubWord(commandQ.poll());
					if(!(command2.equals(""))){
						RBT.findNode(command2);
					}
				}
				else if(command1.equals("s")){
					RBT.printBreadthTraversal(RBT.getRoot());
				}
				else if(command1.equals("r")){
					RBT.printStatistics();
				}
				else if(command1.equals("o")){
					RBT.outputBreadthTraversal(RBT.getRoot());
				}
				else{

				}
			}
		}
	}

	/** Reads in the corpus text file and inserts into the specified tree.
	 * 
	 * @param corpusFile The file to be read.
	 * @param treeType The type of tree to be constructed. 1 for BST, 2 for Red-Black Tree
	 */
	private static void readCorpus(String corpusFile, int treeType) {
		String corpusPath = new File("src/"+corpusFile).getAbsolutePath();
		//String corpusPath = corpusFile;
		Scanner sc = null;
		String line;
		try {
			sc = new Scanner(new File (corpusPath));
		} catch (FileNotFoundException e) {
			System.out.println("Corpus File not found");
			e.printStackTrace();
		}
		while(sc.hasNextLine()){
			line = sc.nextLine();
			String[] lWords = line.split(" ");
			for (int i = 0; i < lWords.length; i++){
				lWords[i] = scrubWord(lWords[i]);
				if ((treeType == 1) && (lWords[i] != "")){
					BST.insertNode(lWords[i]);
				}
				else if((treeType ==2) && (lWords[i] != "")){
					RBT.insertNode(lWords[i]);
				}
				//System.out.println(lWords[i]);

			}
		}
		//Interpreter

		if(treeType == 1){
			/**
			//BST.preOrderTraversal(BST.root);
			BST.printBreadthTraversal(BST.getRoot());
			BST.printStatistics();
			BST.deleteNode("the");
			BST.deleteNode("test");
			BST.deleteNode("the");
			BST.printBreadthTraversal(BST.getRoot());
			BST.deleteNode("girl");
			BST.printBreadthTraversal(BST.getRoot());
			BST.printStatistics();
			BST.findNode("brown");
			BST.findNode("girl");
			 */
		}
		if(treeType == 2){
			/**RBT.preOrderTraversal(RBT.getRoot());
			RBT.printStatistics();
			RBT.deleteNode("the");
			RBT.deleteNode("the");
			RBT.printBreadthTraversal(RBT.getRoot());
			RBT.findNode("brown");
			RBT.findNode("the");
			 */
			RBT.printBreadthTraversal(RBT.getRoot());
			System.out.println();
			readCommands("inputCommands.txt",treeType);
			RBT.outputBreadthTraversal(RBT.getRoot());
			//readCommands("commandsPA",treeType);
		}
		sc.close();


	}

	/** Returns the 'Scrubbed' String
	 * 
	 * @param string String to be scrubbed.
	 * @return The 'Scrubbed' word that has been reduce to lowercase letters
	 */
	private static String scrubWord(String string) {
		string = string.replaceAll("[^\\p{ASCII}]", "");
		String s = "";
		char c = 0;
		for (int i = 0; i < string.length(); i++){
			c = string.charAt(i);
			if( (Character.isUpperCase(c)) && (Character.isLetter(c)) ){
				s = s + Character.toLowerCase(c);
			}
			else if((Character.isLetter(c)) && (Character.isLowerCase(c))){
				s = s + c;
			}
		}
		return s;
	}

}
