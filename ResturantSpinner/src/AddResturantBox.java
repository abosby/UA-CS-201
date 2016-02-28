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
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class AddResturantBox extends JFrame {

	private JPanel contentPane;
	private JTextField resturantNameTxtField;
	private JTextField addressTxtField;

	/**
	 * Create the frame.
	 */
	public AddResturantBox() {
		setTitle("Add Resturant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String[] styles = ResturantSpinnerFrame.getResturantStylesList();
		String[] group = ResturantSpinnerFrame.getResturantGroupsList();

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		resturantNameTxtField = new JTextField();
		resturantNameTxtField.setBounds(10, 27, 152, 20);
		panel.add(resturantNameTxtField);
		resturantNameTxtField.setColumns(10);
		
		JLabel resturantNameLabel = new JLabel("Resturant Name");
		resturantNameLabel.setBounds(10, 11, 109, 14);
		panel.add(resturantNameLabel);
		
		addressTxtField = new JTextField();
		addressTxtField.setBounds(10, 72, 198, 20);
		panel.add(addressTxtField);
		addressTxtField.setColumns(10);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(10, 58, 89, 14);
		panel.add(addressLabel);
		
		JLabel styleLabel = new JLabel("Style");
		styleLabel.setBounds(10, 103, 64, 14);
		panel.add(styleLabel);
		
		JComboBox styleComboBox = new JComboBox(styles);
		styleComboBox.setBounds(10, 117, 152, 20);
		panel.add(styleComboBox);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(10, 146, 75, 14);
		panel.add(priceLabel);
		
		JRadioButton radioButton$ = new JRadioButton("$");
		radioButton$.setBounds(10, 159, 46, 23);
		panel.add(radioButton$);
		
		JRadioButton radioButton$$ = new JRadioButton("$$");
		radioButton$$.setBounds(10, 185, 109, 23);
		panel.add(radioButton$$);
		
		JRadioButton radioButton$$$ = new JRadioButton("$$$");
		radioButton$$$.setBounds(10, 211, 109, 23);
		panel.add(radioButton$$$);
		
		//Grouping the RadioButtons
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(radioButton$$$);
		bGroup.add(radioButton$$);
		bGroup.add(radioButton$);
		
		JComboBox userGroupComboBox = new JComboBox(group);
		userGroupComboBox.setBounds(10, 259, 152, 20);
		panel.add(userGroupComboBox);
		
		JLabel userGroupLabel = new JLabel("User Group");
		userGroupLabel.setBounds(10, 241, 94, 14);
		panel.add(userGroupLabel);
		
		JButton saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			//add resturant to database
			public void mouseClicked(MouseEvent e) {

				String rName = resturantNameTxtField.getText();
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
				
				ResturantSpinnerFrame.addResturant(rName, rAddress, rStyle, rPrice, rGroup);

			}
		});
		saveButton.setBounds(331, 258, 89, 23);
		panel.add(saveButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Frame f;
				f = getActiveFrame();
				f.setVisible(false);
				f.dispose();
			}
		});
		btnNewButton_1.setBounds(232, 258, 89, 23);
		panel.add(btnNewButton_1);
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
