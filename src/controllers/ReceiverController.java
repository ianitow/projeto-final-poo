package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument.ElementSpec;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import views.ChatForm;

public class ReceiverController implements Runnable {

	private Scanner sc;
	InputStream input;
	public static JEditorPane messageBox;
	public static JTree tree;
	private static HTMLEditorKit kit = new HTMLEditorKit();
	private static HTMLDocument doc = new HTMLDocument();

	public ReceiverController(InputStream input) {
		this.input = input;
	}

	public static void setContainerMessages(JEditorPane jtext) {
		messageBox = jtext;

		messageBox.setEditorKit(kit);
		messageBox.setDocument(doc);
	}

	public static void setTree(JTree jtree) {
		tree = jtree;
	}

	public static int countOnline(String msg) {
		String splitted[] = msg.split(":");
		return UtilsController.fromString(splitted[1]).size();

	}

	public static void updateWhoOnline(String msg) {
		String splitted[] = msg.split(":");
		System.out.println(splitted[0]);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Online") {
			{
				for (String nome : UtilsController.fromString(splitted[1])) {
					add(new DefaultMutableTreeNode(nome));
				}

			}
		}));
	}

	@Override
	public void run() {

		// Recebe Mesagens do servidor e imprime na tela
		sc = new Scanner(this.input);
		while (sc.hasNextLine()) {
			if (messageBox != null) {
				String msg = sc.nextLine();

				if (msg.contains("[SERVER]WHO_ONLINE")) {
					ReceiverController.updateWhoOnline(msg);
					ChatForm.getJFrame()
							.setTitle(String.format("Chat - Programação orientada a objetos - %d usuários online",
									ReceiverController.countOnline(msg)));

				} else {

					try {
						kit.insertHTML(doc, doc.getLength(), String.format("%s", msg), ElementSpec.EndTagType, 2,
								null);
					} catch (BadLocationException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					UserController.soc.sendToServer("WHO_ONLINE");
				}
			}
		}

	}

}
