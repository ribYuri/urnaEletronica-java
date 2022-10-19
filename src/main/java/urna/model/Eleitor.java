package urna.model;


public class Eleitor {
	
	private String nome;
	private String sobrenome;
	private int idade;
	private int titulo;
	private boolean votou = false;
	
	
	public Eleitor () {
		
	}

	public Eleitor (String nome, String sobrenome, int idade, int titulo) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.titulo = titulo;
	}

	
	public boolean isVotou() {
		return votou;
	}
	
	
	public void setVotou(boolean votou) {
		this.votou = votou;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getTitulo() {
		return titulo;
	}
	public void setTitulo(int titulo) {
		this.titulo = titulo;
	}
	
	public Boolean isIdadeMinima() {
		return idade >= this.idade;
	}
	
	@Override
	public String toString() {
		return "Nome: " +this.nome+ " " +this.sobrenome + " || Titulo: " +this.titulo + " || Idade: "+this.idade 
				+ " || Votou: " +this.votou;
	}

}
