package views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controllers.UserController;
import models.User;

public class LoginForm {

	private JFrame frmChatUfg;
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtCurso;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmChatUfg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmChatUfg = new JFrame();
		frmChatUfg.setTitle("Chat UFG 1.0");
		frmChatUfg.setResizable(false);
		frmChatUfg.setAlwaysOnTop(true);
		frmChatUfg.setBounds(100, 100, 300, 394);
		frmChatUfg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

frmChatUfg.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 10, 286, 146);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/assets/logo.png")));

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 174, 299, 2);
		frmChatUfg.getContentPane().setLayout(null);
		frmChatUfg.getContentPane().add(lblNewLabel);
		frmChatUfg.getContentPane().add(separator);

		JPanel panel = new JPanel();
		panel.setBounds(10, 178, 277, 176);
		frmChatUfg.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Seu nome:");
		lblNewLabel_1.setBounds(49, 7, 181, 13);
		panel.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(49, 26, 181, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().length() < 3 || txtCurso.getText().length() < 1
						|| txtMatricula.getText().length() < 3) {
					JOptionPane.showMessageDialog(null, "Todos os campos são necessários. Min: 4 caractéres");
				} else {
					new UserController(
							new User(txtNome.getText(), Integer.parseInt(txtMatricula.getText()), txtCurso.getText()));
					frmChatUfg.dispose();
					new MainForm();
				}
			}
		});
		btnLogin.setBounds(49, 141, 181, 23);
		panel.add(btnLogin);

		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(49, 70, 181, 19);
		panel.add(txtMatricula);

		JLabel lblNewLabel_1_1 = new JLabel("Matricula:");
		lblNewLabel_1_1.setBounds(49, 51, 181, 13);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Curso:");
		lblNewLabel_1_1_1.setBounds(49, 96, 181, 13);
		panel.add(lblNewLabel_1_1_1);

		txtCurso = new JTextField();
		txtCurso.setColumns(10);
		txtCurso.setBounds(49, 115, 181, 19);
		panel.add(txtCurso);
	}
}
