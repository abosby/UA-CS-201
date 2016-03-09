import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResturantSpinnerAdminFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ResturantSpinnerAdminFrame(String[] styles, String[] group) {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton addResturantButton = new JButton("Add Resturant");
		addResturantButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddResturantBox aBox = new AddResturantBox();
				aBox.setVisible(true);
			}
		});
		addResturantButton.setBounds(168, 169, 138, 23);
		panel.add(addResturantButton);
		
		JButton editResturantButton = new JButton("Edit Resturant");
		editResturantButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditResturantBox eBox = new EditResturantBox();
				eBox.setVisible(true);
			}
		});
		editResturantButton.setBounds(168, 203, 138, 23);
		panel.add(editResturantButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Frame f;
				f = getActiveFrame();
				f.setVisible(false);
				f.dispose();
			}
		});
		
		JButton deleteResturantButton = new JButton("Delete Resturant");
		deleteResturantButton.setBounds(168, 237, 138, 23);
		panel.add(deleteResturantButton);
		closeButton.setBounds(192, 365, 89, 23);
		panel.add(closeButton);
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
