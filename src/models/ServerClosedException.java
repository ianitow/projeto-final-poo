package models;

public class ServerClosedException  extends Exception{
	public ServerClosedException(){
		super("Servidor n�o esta ligado");
	}
}
