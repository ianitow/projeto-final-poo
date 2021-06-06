package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterForm {

	private JFrame frmChatUfg;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm window = new RegisterForm();
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
	public RegisterForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatUfg = new JFrame();
		frmChatUfg.setTitle("Chat UFG 1.0");
		frmChatUfg.setResizable(false);
		frmChatUfg.setAlwaysOnTop(true);
		frmChatUfg.setBounds(100, 100, 300, 394);
		frmChatUfg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		panel.setBounds(10, 178, 277, 182);
		frmChatUfg.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setBounds(49, 7, 181, 13);
		panel.add(lblNewLabel_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(49, 26, 181, 19);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(49, 51, 181, 13);
		panel.add(lblSenha);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(49, 70, 181, 19);
		panel.add(txtPassword);
		
		JButton btnRegister = new JButton("Registrar");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(49, 150, 181, 21);
		panel.add(btnRegister);
		
		JLabel lblRepetirSenha = new JLabel("Repetir senha");
		lblRepetirSenha.setBounds(49, 97, 181, 13);
		panel.add(lblRepetirSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(49, 116, 181, 19);
		panel.add(passwordField);
	}
}
