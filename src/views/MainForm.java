package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import controllers.UserController;

public class MainForm {

	private JFrame frmChatUfg;

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatUfg = new JFrame();

		;
		frmChatUfg.addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent e) {
				UserController.encerrar();
			}
		});
		frmChatUfg.setVisible(true);
		frmChatUfg.setTitle("Chat UFG - Logado");
		frmChatUfg.setResizable(false);
		frmChatUfg.getContentPane().setBackground(new Color(240, 248, 255));
		frmChatUfg.setBounds(100, 100, 308, 550);
		frmChatUfg.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmChatUfg.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(10, 11, 100, 100);
		frmChatUfg.getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainForm.class.getResource("/assets/avatar.png")));
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 122, 855, 2);
		frmChatUfg.getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel(UserController.getUserInstance().getNome());
		lblNewLabel_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(120, 11, 115, 23);
		frmChatUfg.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Curso: " + UserController.getUserInstance().getCurso());
		lblNewLabel_2.setFont(new Font("Open Sans Light", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(120, 33, 176, 14);
		frmChatUfg.getContentPane().add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(214, 84, 75, 40);
		frmChatUfg.getContentPane().add(panel_1);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.LEADING, 10, 8);
		panel_1.setLayout(fl_panel_1);

		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,
						"<html><b>Equipe</b><br>Iaan Mesquita de Souza - 201904219<br>Wallace Patrick Lima Cardoso - 202003623<br>Marcos Paulo da Silva Santiago - 201802844<br><html> ");
			}
		});
		lblNewLabel_3_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(lblNewLabel_3_2);
		lblNewLabel_3_2.setIcon(new ImageIcon(MainForm.class.getResource("/assets/info.png")));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserController.encerrar();
				frmChatUfg.dispose();
				System.exit(2);

			}
		});
		lblNewLabel_3_1_1.setIcon(new ImageIcon(MainForm.class.getResource("/assets/logout.png")));
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_4 = new JLabel("Online");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(240, 16, 46, 14);
		frmChatUfg.getContentPane().add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 280, 375);
		frmChatUfg.getContentPane().add(scrollPane);

		JList list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					if(!ChatForm.isOpened) {
						new ChatForm();
						try
						{
							frmChatUfg.setCursor(new Cursor(Cursor.WAIT_CURSOR));
							UserController.conectarUser();
							
						}finally {
							frmChatUfg.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
						
						
					}

				}
			}
		});
		list.setFont(UIManager.getFont("Label.font"));
		list.setForeground(new Color(70, 130, 180));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "\uD83D\uDEB9   Programa\u00E7\u00E3o Orientada a Objetos" };

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);

		JLabel lblNewLabel_5 = new JLabel("Minhas Salas");
		scrollPane.setColumnHeaderView(lblNewLabel_5);

		JLabel lblNewLabel_2_1 = new JLabel(
				"Matricula: " + String.valueOf(UserController.getUserInstance().getMatricula()));
		lblNewLabel_2_1.setFont(new Font("Open Sans Light", Font.PLAIN, 10));
		lblNewLabel_2_1.setBounds(120, 48, 130, 14);
		frmChatUfg.getContentPane().add(lblNewLabel_2_1);
	}
}
