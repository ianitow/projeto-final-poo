package controllers;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import models.ServerClosedException;

public class SocketClientController {

	private Socket socketClient;
	private PrintStream output;
	private Scanner sc;
	private ReceiverController receiver;

	public void startReceiver() {
		try {
			receiver = new ReceiverController(this.socketClient.getInputStream());
		} catch (IOException e) {
			e.getMessage();
		}
		new Thread(receiver).start();
	}

	public void sendToServer(String text) {
		output.println("[SERVER]" + text);
	}

	public void sendToServer(String text, boolean isUser) {
		if (isUser)
			output.println("<b>"+UserController.getUserInstance().getNome() + "</b> diz: " + text);
		else
			output.println(text);
	}

	public void execute(Socket client) throws ServerClosedException {
		try {
			this.socketClient = client;
			// Thread recebe mensagem do servidor
			this.startReceiver();
			output = new PrintStream(socketClient.getOutputStream());
		} catch (IOException e) {
			throw new ServerClosedException();
		}

	}

	private void secureClose(final Closeable resource) {

		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void encerrar() {
		secureClose(output);
		secureClose(socketClient);
		secureClose(sc);

	}

}
