import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ResturantSpinnerFrameOld extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4624654308139813674L;
	private JPanel groupDropDown;
	private static ArrayList<String> resturantGroupList;
	private static ArrayList<String> resturantStyleList;
	private static ArrayList<Resturant> resturantList;
	private static String selectedGroup;
	/**
	 * Create the frame.
	 */
	public ResturantSpinnerFrameOld() {
		super("SpinnerFrame");

		resturantGroupList = new ArrayList<String>();
		resturantStyleList = new ArrayList<String>();
		resturantList = new ArrayList<Resturant>();

		@SuppressWarnings("unused")
		ResturantSpinnerFrameOld frame = this;
		setTitle("Resturant Spinner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 643);
		groupDropDown = new JPanel();
		groupDropDown.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(groupDropDown);
		groupDropDown.setLayout(null);

		accumulateResturantGroupList();
		accumulateResturantStylelist();
		accumulateResturantList();

		JPanel spinnerPanel = new JPanel();
		spinnerPanel.setBounds(35, 83, 504, 427);
		groupDropDown.add(spinnerPanel);
		spinnerPanel.setLayout(null);

		JLabel resultLabel = new JLabel("");
		resultLabel.setBounds(380, 104, -266, -99);
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinnerPanel.add(resultLabel);
		spinnerPanel.setVisible(true);

		JButton btnSpin = new JButton("Spin");
		btnSpin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				spinWheel(resultLabel);
			}
		});
		btnSpin.setBounds(242, 521, 89, 23);
		groupDropDown.add(btnSpin);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox groupComboBox = new JComboBox(resturantGroupList.toArray(new String[resturantGroupList.size()]));
		groupComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedGroup = (String)groupComboBox.getSelectedItem();
			}
		});
		groupComboBox.setBounds(197, 52, 152, 20);
		groupDropDown.add(groupComboBox);
		selectedGroup = (String)groupComboBox.getSelectedItem();

		JLabel lblResturantGroup = new JLabel("Resturant Group");
		lblResturantGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblResturantGroup.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblResturantGroup.setBounds(231, 26, 89, 14);
		groupDropDown.add(lblResturantGroup);

		JButton adminPanelButton = new JButton("Admin Panel");
		adminPanelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdminPasswordFrame adminPassFrame = new AdminPasswordFrame(resturantStyleList.toArray(new String[resturantStyleList.size()]), resturantGroupList.toArray(new String[resturantGroupList.size()]));
				//ResturantSpinnerAdminFrame adminFrame = new ResturantSpinnerAdminFrame(resturantStyleList.toArray(new String[resturantStyleList.size()]), resturantGroupList.toArray(new String[resturantGroupList.size()]));
				adminPassFrame.setVisible(true);
			}
		});
		adminPanelButton.setBounds(465, 11, 109, 23);
		groupDropDown.add(adminPanelButton);
	}

	public void spinWheel(JLabel resultLabel) {

		Random r = new Random();
		for (long stop=System.nanoTime()+TimeUnit.SECONDS.toNanos(2);stop>System.nanoTime();) {
			Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
			resultLabel.setBackground(c);
			String rText = resturantList.get(r.nextInt(resturantList.size())).getName();
			resultLabel.setText(rText);
		}
		

	}

	private static void accumulateResturantList() {
		String csvFile = "F:\\Documents\\Programming\\Mars\\eclipse\\workspace\\ResturantSpinner\\src\\ResturantsDatabase.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String [] splitLine = line.split(cvsSplitBy);
				Resturant newR = new Resturant(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4]);
				resturantList.add(newR);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");		
	}

	public static void addResturant(String rName, String rAddress, String rStyle, String rPrice, String rGroup){
		Resturant newResturant = new Resturant(rName, rAddress, rStyle, rPrice, rGroup);
		resturantList.add(newResturant);

		//Physically change the database
		PrintWriter csvWriter;
		try {
			/*1. declare stringBuffer*/
			StringBuffer oneLineStringBuffer = new StringBuffer();

			File file = new File("F:\\Documents\\Programming\\Mars\\eclipse\\workspace\\ResturantSpinner\\src\\ResturantsDatabase.csv");
			csvWriter = new PrintWriter(new FileWriter(file, true));

			/*2. append to stringBuffer*/   
			oneLineStringBuffer.append(rName + '\t' + rAddress + '\t' + rStyle + '\t' + rPrice + '\t' + rGroup);
			oneLineStringBuffer.append("\n");

			/*3. print to csvWriter*/
			csvWriter.print(oneLineStringBuffer);

			csvWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void editResturant(Resturant prevRecord, Integer rIndex, String rName, String rAddress, String rStyle, String rPrice, String rGroup){

		Resturant rEdit = resturantList.get(rIndex);
		rEdit.editResturant(rName, rAddress, rStyle, rPrice, rGroup);

		//remove the previous entry
		PrintWriter csvWriter;
		try{
			//Create a delete the old database and write previous data to it
			File file = new File("F:\\Documents\\Programming\\Mars\\eclipse\\workspace\\ResturantSpinner\\src\\ResturantsDatabase.csv");
			String tempFile = file.toString();
			file.delete();
			file = new File(tempFile);
			csvWriter = new PrintWriter(new FileWriter(file,true));

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (Resturant r : resturantList){
				bw.write(r.asCSVRecord() + '\n');
			}
			bw.close();
		} catch (Exception e){
			e.printStackTrace();
		}

		System.out.println("Updated CSV \n");

		//Update All of our lists
		resturantGroupList = new ArrayList<String>();
		resturantStyleList = new ArrayList<String>();
		resturantList = new ArrayList<Resturant>();
		accumulateResturantGroupList();
		accumulateResturantStylelist();
		accumulateResturantList();

	}

	private static void accumulateResturantStylelist() {
		// TODO Auto-generated method stub
		String csvFile = "F:\\Documents\\Programming\\Mars\\eclipse\\workspace\\ResturantSpinner\\src\\ResturantsDatabase.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String [] splitLine = line.split(cvsSplitBy);
				if (resturantStyleList.contains(splitLine[2])){

				}
				else{
					String temp = splitLine[2];
					resturantStyleList.add(temp);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");	
	}

	public static String[] getResturantGroupsList(){
		return resturantGroupList.toArray(new String[resturantGroupList.size()]);
	}

	private static void accumulateResturantGroupList() {

		String csvFile = "F:\\Documents\\Programming\\Mars\\eclipse\\workspace\\ResturantSpinner\\src\\ResturantsDatabase.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String [] splitLine = line.split(cvsSplitBy);
				if (resturantGroupList.contains(splitLine[4])){

				}
				else{
					String temp = splitLine[4];
					resturantGroupList.add(temp);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

	@SuppressWarnings("null")
	public static String[] getResturantList() {
		ArrayList<String> tempList = new ArrayList<String>();
		String tempGroup = selectedGroup;
		String tempResturant;
		Integer count = 0;

		for (Resturant r : resturantList){
			if(r.getGroup().equals(tempGroup)){
				tempResturant = r.getName();
				tempList.add(tempResturant);
				count++;
			}
		}
		return tempList.toArray(new String[tempList.size()]);
	}

	public static Resturant getResturant(String selectedItem) {
		for (Resturant r : resturantList){
			if(r.getName().equals(selectedItem)){
				return r;
			}
		}
		return null;
	}


	public static String[] getResturantStylesList() {
		return resturantStyleList.toArray(new String[resturantStyleList.size()]);
	}
}
