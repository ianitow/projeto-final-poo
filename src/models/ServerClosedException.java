package models;

public class ServerClosedException  extends Exception{
	public ServerClosedException(){
		super("Servidor não esta ligado");
	}
}
