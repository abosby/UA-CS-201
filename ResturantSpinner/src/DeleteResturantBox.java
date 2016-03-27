import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteResturantBox extends JFrame {

	private JPanel contentPane;
	private String[] resturantList;
	private Resturant selectedResturant;

	/**
	 * Create the frame.
	 */
	public DeleteResturantBox() {
		setTitle("Delete Resturant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		resturantList = ResturantSpinnerFrame.getResturantList();
		
		JComboBox comboBoxResturant = new JComboBox(resturantList);
		comboBoxResturant.setBounds(83, 37, 176, 20);
		contentPane.add(comboBoxResturant);
		selectedResturant = ResturantSpinnerFrame.getResturant((String)comboBoxResturant.getSelectedItem());
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResturantSpinnerFrame.deleteResturant(selectedResturant);
			}
		});
		btnDelete.setBounds(127, 95, 89, 23);
		contentPane.add(btnDelete);
		
	}
}
