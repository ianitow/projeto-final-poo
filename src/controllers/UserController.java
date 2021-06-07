package controllers;

import java.awt.Cursor;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import models.SERVER_INFO;
import models.UserModel;
import views.ChatForm;
import views.MainForm;

public class UserController {
	private static UserModel userInstance;
	private static UserController instance;
	private static Socket clientSocket;
	public static SocketClientController soc = new SocketClientController();

	public UserController(String nome, int matricula, String curso) {
		userInstance = new UserModel(nome,matricula,curso);
		instance = this;
	}

	public static UserModel getUserInstance() {
		return userInstance;
	}

	public static UserController getInstance() {

		return instance;
	}

	public static void conectarUser() {
		try {
			// Conecta o cliente com o servidor
			clientSocket = new Socket(SERVER_INFO.SERVER_ADDRESS.get(), Integer.parseInt(SERVER_INFO.PORT.get()));
			soc.execute(clientSocket);
			soc.sendToServer(userInstance.getNome() + " entrou no chat", false);
			soc.sendToServer("ADD_USER:" + userInstance.getNome());
			ChatForm.getJFrame().setVisible(true);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "Não conectado!", JOptionPane.ERROR_MESSAGE);
			ChatForm.isOpened = false;
			ChatForm.getJFrame().dispose();
		
		}

	}

	public void encerrar() {

		UserController.soc.sendToServer(userInstance.getNome() + " saiu do chat.", false);
		UserController.soc.sendToServer("REMOVE_USER:" + userInstance.getNome());
		UserController.soc.encerrar();

	}

	public void enviarMensagem(String mensagem) {
		UserController.soc.sendToServer(mensagem.trim(), true);
	}
}
