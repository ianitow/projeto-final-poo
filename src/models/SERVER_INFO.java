package models;

public enum SERVER_INFO {

	SERVER_ADDRESS("127.0.0.1"),
	PORT("4000"),
	COMMAND_WHO_ONLINE("[SERVER]WHO_ONLINE"),
	COMMAND_ADD_USER("[SERVER]ADD_USER:"),
	COMMAND_REMOVE_USER("[SERVER]REMOVER_USER");
	
	
	private String propertie;

	SERVER_INFO(String propertie) {
		this.propertie = propertie;
	}

	public String get() {
		return propertie;
	}
}