package models;

public enum SERVER_INFO {

	SERVER_ADDRESS("127.0.0.1"),
	PORT("4000");
	
	private String propertie;

	SERVER_INFO(String propertie) {
		this.propertie = propertie;
	}

	public String get() {
		return propertie;
	}
}