package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

import controllers.Receiver;
import controllers.UserController;
import javax.swing.ImageIcon;

public class ChatForm {
	static Boolean isOpened = false;
	JFrame frmChatMenma;
	public void sendMessageInterface(JTextArea txtArea) {

		if (txtArea.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Informe um texto antes de enviar", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			UserController.getInstance().enviarMensagem(txtArea.getText());
			txtArea.setFocusable(true);
			txtArea.setText("");
		}

	}


	public ChatForm() {
		if (!isOpened)
			initialize();
		
	}
	public JFrame getJFrame() {
		return frmChatMenma;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmChatMenma = new JFrame();
		ChatForm.isOpened = true;
		SwingUtilities.updateComponentTreeUI(frmChatMenma);

		frmChatMenma.setAlwaysOnTop(true);
		frmChatMenma.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmChatMenma.dispose();
				UserController.getInstance().encerrar();
				ChatForm.isOpened = false;
			}
		});

		frmChatMenma.setVisible(true);

		frmChatMenma.setTitle("Chat - Meiko Honma");
		frmChatMenma.getContentPane().setBackground(new Color(240, 248, 255));
		frmChatMenma.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(114, 0, 7, 416);
		frmChatMenma.getContentPane().add(separator);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(120, 300, 564, 105);
		frmChatMenma.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 11, 560, 278);
		frmChatMenma.getContentPane().add(scrollPane);

		JTextArea txtMessageBox = new JTextArea();
		DefaultCaret caret = (DefaultCaret) txtMessageBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(txtMessageBox);

		txtMessageBox.setRows(10);
		txtMessageBox.setWrapStyleWord(true);

		txtMessageBox.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
		txtMessageBox.setEditable(false);
		txtMessageBox.setLineWrap(true);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 561, 105);
		panel_2.add(scrollPane_1);
		JTextArea txtSendMessage = new JTextArea();
		txtSendMessage.setFont(new Font("Open Sans", Font.PLAIN, 11));
		txtSendMessage.setTabSize(0);
		txtSendMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessageInterface(txtSendMessage);
				}
			}
		});
		scrollPane_1.setViewportView(txtSendMessage);

		JLabel lblNewLabel_3 = new JLabel("Digite sua mensagem:");
		lblNewLabel_3.setFont(new Font("Open Sans", Font.BOLD, 11));
		scrollPane_1.setColumnHeaderView(lblNewLabel_3);

		Receiver.setJTextArea(txtMessageBox);

		JLabel lblChat = new JLabel("Chat");
		scrollPane.setColumnHeaderView(lblChat);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(5, 11, 106, 394);
		frmChatMenma.getContentPane().add(scrollPane_2);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rios online:");
		scrollPane_2.setColumnHeaderView(lblNewLabel);

		// ChatForm.class.getResource()
		JTree tree = new JTree();
		tree.setRootVisible(false);
		tree.setForeground(Color.BLACK);
		Receiver.setTree(tree);

		scrollPane_2.setViewportView(tree);
		frmChatMenma.setResizable(false);
		frmChatMenma.setBounds(100, 100, 696, 445);
		frmChatMenma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frmChatMenma.setLocationRelativeTo(null);
	}
}
