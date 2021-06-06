package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
import javax.swing.WindowConstants;

import controllers.UserController;
import models.User;

public class LoginForm {

	private JFrame frmChatUfg;
	private JTextField txtNome;
	private JTextField txtMatricula;
	private JTextField txtCurso;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		UIManager.put("Tree.leafIcon", new ImageIcon(LoginForm.class.getResource("/assets/162294060624368557.png")));

		EventQueue.invokeLater(new Runnable() {
			@Override
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

		frmChatUfg = new JFrame();
		frmChatUfg.setTitle("Chat UFG 1.0");
		frmChatUfg.setResizable(false);
		frmChatUfg.setBounds(100, 100, 300, 394);
		frmChatUfg.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtNome.getText().length() < 4 || txtCurso.getText().length() < 4
						|| txtMatricula.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Todos os campos são necessários. Min: 4 caractéres", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				} //
				if (!Pattern.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", txtNome.getText())) {
					JOptionPane.showMessageDialog(null, "O campo nome aceita apenas letras", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!Pattern.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", txtCurso.getText())) {
					JOptionPane.showMessageDialog(null, "O campo curso aceita apenas letras", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!Pattern.matches("\\d*", txtMatricula.getText())) {
					JOptionPane.showMessageDialog(null, "O campo matricula aceita apenas números", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (txtNome.getText().length() < 4 || txtCurso.getText().length() < 4
						|| txtMatricula.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Todos os campos são necessários. Min: 4 caractéres", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				new UserController(
						new User(txtNome.getText(), Integer.parseInt(txtMatricula.getText()), txtCurso.getText()));
				frmChatUfg.dispose();
				new MainForm();
				JOptionPane.showMessageDialog(null,
						String.format("Bem vindo %s, respeite todos os membros!!", txtNome.getText()));

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
