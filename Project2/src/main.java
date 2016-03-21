import java.io.File;
import java.io.FileNotFoundException;
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
		readCorpus("inputCorpus.txt",treeType);
		//readCommands(args[2]);

	}

	private static void readCommands(String commandFile) {
		String commandPath = new File(commandFile).getAbsolutePath();
		Scanner sc = null;
		String line;
		try {
			sc = new Scanner(new File (commandPath));
		} catch (FileNotFoundException e) {
			System.out.println("Corpus File not found");
			e.printStackTrace();
		}
		while(sc.hasNextLine()){
			line = sc.nextLine();
			String[] lWords = line.split(" ");
			for (int i = 0; i < lWords.length; i++){
				System.out.println(lWords[i]);
			}
		}
		sc.close();


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
		}
		if(treeType == 2){
			RBT.preOrderTraversal(RBT.getRoot());
			RBT.printBreadthTraversal(RBT.getRoot());
		}
		sc.close();


	}

	/** Returns the 'Scrubbed' String
	 * 
	 * @param string String to be scrubbed.
	 * @return The 'Scrubbed' word that has been reduce to lowercase letters
	 */
	private static String scrubWord(String string) {
		String s = "";
		char c = 0;
		for (int i = 0; i < string.length(); i++){
			c = string.charAt(i);
			if((Character.isUpperCase(c))){
				s = s + Character.toLowerCase(c);
			}
			if((Character.isAlphabetic(c)) && (Character.isLowerCase(c))){
				s = s + c;
			}
		}
		return s;
	}

}
