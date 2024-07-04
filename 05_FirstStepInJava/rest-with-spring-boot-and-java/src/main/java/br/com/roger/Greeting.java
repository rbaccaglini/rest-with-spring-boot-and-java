package br.com.roger;

public class Greeting {
	private final long id;
	private final String content;
	
	public Greeting(long id, String contente) {
		super();
		this.id = id;
		this.content = contente;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	
}
