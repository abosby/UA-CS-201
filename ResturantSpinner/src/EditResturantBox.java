import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditResturantBox extends JFrame {

	private JPanel contentPane;
	private JTextField addressTxtField;
	private Integer rIndex;
	private String[] resturantList;
	private Resturant prevResturant;
	private Resturant initialResturant;

	/**
	 * Create the frame.
	 */
		public EditResturantBox() {
		setTitle("Edit Resturant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String[] styles = ResturantSpinnerFrame.getResturantStylesList();
		String[] group = ResturantSpinnerFrame.getResturantGroupsList();
		resturantList = ResturantSpinnerFrame.getResturantList();

		initialResturant = ResturantSpinnerFrame.getResturant(resturantList[0]);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel resturantNameLabel = new JLabel("Resturant Name");
		resturantNameLabel.setBounds(10, 11, 109, 14);
		panel.add(resturantNameLabel);
		
		addressTxtField = new JTextField(initialResturant.getAddress());
		addressTxtField.setBounds(10, 72, 257, 20);
		panel.add(addressTxtField);
		addressTxtField.setColumns(10);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(10, 58, 89, 14);
		panel.add(addressLabel);
		
		JLabel styleLabel = new JLabel("Style");
		styleLabel.setBounds(10, 103, 89, 14);
		panel.add(styleLabel);
		
		JComboBox styleComboBox = new JComboBox(styles);
		styleComboBox.setSelectedIndex(0);
		styleComboBox.setBounds(10, 117, 152, 20);
		panel.add(styleComboBox);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(10, 146, 89, 14);
		panel.add(priceLabel);
		
		JRadioButton radioButton$ = new JRadioButton("$");
		if(initialResturant.getPrice().equals("$")){
			radioButton$.setSelected(true);
		}
		radioButton$.setBounds(10, 159, 46, 23);
		panel.add(radioButton$);
		
		JRadioButton radioButton$$ = new JRadioButton("$$");
		if(initialResturant.getPrice().equals("$$")){
			radioButton$.setSelected(true);
		}
		radioButton$$.setBounds(10, 185, 109, 23);
		panel.add(radioButton$$);
		
		JRadioButton radioButton$$$ = new JRadioButton("$$$");
		if(initialResturant.getPrice().equals("$$$")){
			radioButton$.setSelected(true);
		}
		radioButton$$$.setBounds(10, 211, 109, 23);
		panel.add(radioButton$$$);
		
		//Grouping the RadioButtons
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(radioButton$$$);
		bGroup.add(radioButton$$);
		bGroup.add(radioButton$);
		
		JComboBox userGroupComboBox = new JComboBox(group);
		userGroupComboBox.setSelectedIndex(0);
		userGroupComboBox.setBounds(10, 259, 152, 20);
		panel.add(userGroupComboBox);
		
		JLabel userGroupLabel = new JLabel("User Group");
		userGroupLabel.setBounds(10, 241, 89, 14);
		panel.add(userGroupLabel);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox resturantComboBox = new JComboBox(resturantList);
		resturantComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Resturant r = ResturantSpinnerFrame.getResturant((String)resturantComboBox.getSelectedItem());
				addressTxtField.setText(r.getAddress());
				styleComboBox.setSelectedItem(r.getStyle());
				if (r.getPrice().equals("$")){
					radioButton$.setSelected(true);
					radioButton$$.setSelected(false);
					radioButton$$$.setSelected(false);
				}else if (r.getPrice().equals("$$")){
					radioButton$$.setSelected(true);
					radioButton$.setSelected(false);
					radioButton$$$.setSelected(false);
				}else{
					radioButton$$$.setSelected(true);
					radioButton$$.setSelected(false);
					radioButton$.setSelected(false);
				}
				userGroupComboBox.setSelectedItem(r.getGroup());
			}
		});
		resturantComboBox.setBounds(10, 27, 152, 20);
		panel.add(resturantComboBox);

		JButton saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//If different
				String rName= (String) resturantComboBox.getSelectedItem();
				prevResturant = ResturantSpinnerFrame.getResturant(rName);
				rIndex = resturantComboBox.getSelectedIndex();
				String rAddress = addressTxtField.getText();
				String rStyle = styles[styleComboBox.getSelectedIndex()];
				String rGroup = group[userGroupComboBox.getSelectedIndex()];

				String rPrice;
				if(radioButton$$$.isSelected()){
					rPrice = "$$$";
				}
				else if (radioButton$$.isSelected()){
					rPrice = "$$";
				}else{
					rPrice = "$";
				}
				
				ResturantSpinnerFrame.editResturant(prevResturant, rIndex, rName, rAddress, rStyle, rPrice, rGroup);
				Frame f;
				f = getActiveFrame();
				f.setVisible(false);
				f.dispose();

			}
		});
		
		
		
		
		
		
		saveButton.setBounds(331, 258, 89, 23);
		panel.add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			Frame f;
			f = getActiveFrame();
			f.setVisible(false);
			f.dispose();
			}
		});
		cancelButton.setBounds(232, 258, 89, 23);
		panel.add(cancelButton);
	}
	
	public Frame getActiveFrame(){
		Frame[] f = Frame.getFrames();
		Frame active = null;
		for (int i = 0; i < f.length; i++) {
			if (f[i].isActive()) {
				active = f[i]; 
			}
		}
		return active;
	}
}
