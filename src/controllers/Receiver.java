package controllers;

import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Receiver implements Runnable {

	private Scanner sc;
	InputStream input;
	public static JTextArea messageBox;
	public static JTree tree;

	public Receiver(InputStream input) {
		this.input = input;
	}

	public static void setJTextArea(JTextArea jtext) {
		messageBox = jtext;
	}
	public static void setTree(JTree jtree) {
		tree = jtree;
	}
	public static void updateWhoOnline(String msg) {
		String splitted[] = msg.split(":");
		System.out.println(splitted[0]);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Online") {
			{
				for (String nome : Utils.fromString(splitted[1])) {
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
			
				if(msg.contains("[SERVER]WHO_ONLINE")) {
					Receiver.updateWhoOnline(msg);
					
				}else {
					messageBox.append(msg+"\n");
					UserController.soc.sendSomething("WHO_ONLINE");
				}
			}
		}

	}

}
