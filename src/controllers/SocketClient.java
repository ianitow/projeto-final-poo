package controllers;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

	private Socket socketClient;
	private PrintStream output;
	private Scanner sc;
	private Receiver r;

	public void startReceiver() {
		try {
			r = new Receiver(this.socketClient.getInputStream());
		} catch (IOException e) {
			e.getMessage();
		}
		new Thread(r).start();
	}

	public void sendToServer(String text) {
		output.println("[SERVER]" + text);
	}

	public void sendToServer(String text, boolean isUser) {
		if (isUser)
			output.println(UserController.getUserInstance().getNome() + " diz: " + text);
		else
			output.println(text);
	}

	public void execute(Socket client) throws IOException {

		this.socketClient = client;
		System.out.println("Connected");
		// Thread recebe mensagem do servidor
		this.startReceiver();
		output = new PrintStream(socketClient.getOutputStream());

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
