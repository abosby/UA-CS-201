import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AdminPasswordFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 * @param strings2 
	 * @param strings 
	 */
	public AdminPasswordFrame(String[] resturantListSize, String[] ResturantListContent) {
		setTitle("Administrator Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPassword = new JLabel("Enter Admin Password");
		labelPassword.setBounds(29, 15, 152, 14);
		contentPane.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(29, 40, 152, 20);
		contentPane.add(passwordField);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isPasswordCorrect(passwordField.getPassword())){
					ResturantSpinnerAdminFrame adminFrame = new ResturantSpinnerAdminFrame(resturantListSize,ResturantListContent);
					setVisible(false);
					adminFrame.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(getParent(), "Invalid password. Try again.","Error Message",JOptionPane.ERROR_MESSAGE);
				}
				passwordField.setText("");
				passwordField.selectAll();
			}
		});
		btnEnter.setBounds(203, 29, 89, 23);
		contentPane.add(btnEnter);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(203, 52, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private static boolean isPasswordCorrect(char[] input){
		boolean isCorrect = true;
		char[] correctPassword = {'R','e','s','t','u','r','a','n','t'};
		if(input.length != correctPassword.length){
			isCorrect = false;
		}
		else{
			isCorrect = Arrays.equals(input, correctPassword);
		}
		
		//Zero out the password.
		Arrays.fill(correctPassword, '0');
		return isCorrect;
	}
}
