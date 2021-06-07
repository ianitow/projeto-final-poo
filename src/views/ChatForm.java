package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import controllers.ReceiverController;
import controllers.UserController;
import java.awt.Point;

public class ChatForm {

	static JFrame frmChatMenma;
	public static Boolean isOpened = false;

	public void sendMessageInterface(String text) {

		if (text.length() == 0) {
			JOptionPane.showMessageDialog(null, "Informe um texto antes de enviar", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			UserController.getInstance().enviarMensagem(text);

		}

	}

	public ChatForm() {
		if (!isOpened)
			initialize();

	}

	public static JFrame getJFrame() {
		return frmChatMenma;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmChatMenma = new JFrame();
		ChatForm.isOpened = true;
		SwingUtilities.updateComponentTreeUI(frmChatMenma);

		frmChatMenma.setVisible(false);

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
		JTextPane txtMessageBox = new JTextPane();
		txtMessageBox.setContentType("text/html");
		txtMessageBox.setText("<html> oi</html>\t");
		DefaultCaret caret = (DefaultCaret) txtMessageBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(txtMessageBox);

		txtMessageBox.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
		txtMessageBox.setEditable(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 561, 105);
		panel_2.add(scrollPane_1);

		JLabel lblNewLabel_3 = new JLabel("Digite sua mensagem:");
		lblNewLabel_3.setFont(new Font("Open Sans", Font.BOLD, 11));
		scrollPane_1.setColumnHeaderView(lblNewLabel_3);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Open Sans Light", Font.PLAIN, 12));
		int condition = JComponent.WHEN_FOCUSED;
		// get our maps for binding from the chatEnterArea JTextArea
		InputMap inputMap = textArea.getInputMap(condition);
		ActionMap actionMap = textArea.getActionMap();

		// the key stroke we want to capture
		KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

		// tell input map that we are handling the enter key
		inputMap.put(enterStroke, enterStroke.toString());

		// tell action map just how we want to handle the enter key
		actionMap.put(enterStroke.toString(), new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = textArea.getText();
				textArea.setText("");
				sendMessageInterface(text);

			}
		});

		scrollPane_1.setViewportView(textArea);
		ReceiverController.setContainerMessages(txtMessageBox);

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
		ReceiverController.setTree(tree);

		scrollPane_2.setViewportView(tree);
		frmChatMenma.setResizable(false);
		frmChatMenma.setBounds(100, 100, 696, 445);
		frmChatMenma.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		frmChatMenma.setLocationRelativeTo(null);
		frmChatMenma.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frmChatMenma.dispose();
				UserController.getInstance().encerrar();
				ChatForm.isOpened = false;
				int option = JOptionPane.showConfirmDialog(null, "Gostaria de salvar as mensagens?", "Deseja salvar?",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					try {
						FileWriter out = new FileWriter("conversas.html");
						out.write(txtMessageBox.getText());
						out.close();
						JOptionPane.showMessageDialog(null, "Salvo em: conversas.html");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "ERRO AO SALVAR!!");
						e1.printStackTrace();
					}
				}

				txtMessageBox.setText("");
			}
		});

	}
}
