import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//readCorpus(args[1]);
		readCorpus("inputCorpus.txt");
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

	private static void readCorpus(String corpusFile) {
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
				scrubWord(lWords[i]);
				System.out.println(lWords[i]);
			}
		}
		sc.close();



	}

	private static void scrubWord(String string) {
		String s = "";
		char c = 0;
		for (int i = 0; i < string.length(); i++)
			c = string.charAt(i);
			if(Character.isDigit(c)){
				
			}
	}

}
