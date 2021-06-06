package controllers;

import java.io.IOException;
import java.net.Socket;

import models.SERVER_INFO;
import models.User;

public class UserController {
	private static User userInstance;
	private static UserController instance;
	private static Socket clientSocket;
	public static SocketClient soc = new SocketClient();

	public UserController(User user) {
		userInstance = user;
		instance = this;
	}

	public static User getUserInstance() {
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
			soc.showAlertToAll(userInstance.getNome() + " entrou no chat");
			soc.sendSomething("ADD_USER:" + userInstance.getNome());
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void encerrar() {

		UserController.soc.showAlertToAll(userInstance.getNome() + " saiu do chat.");
		UserController.soc.sendSomething("REMOVE_USER:" + userInstance.getNome());
		UserController.soc.encerrar();

	}

	public void enviarMensagem(String mensagem) {
		UserController.soc.enviarTexto(mensagem.trim());
	}
}
