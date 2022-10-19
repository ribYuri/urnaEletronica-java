package urna.model;


public class Candidato extends Eleitor {
	
	private int votosRecebidos = 0;
	private int numeroIdentificador;
	private String partido;


	public Candidato () {
		
	}
	
	public Candidato(String nome, String sobrenome, String partido, int idade, int titulo, int numeroCandidato) {
		super(nome, sobrenome, idade, titulo);
		this.numeroIdentificador = numeroCandidato;
		this.partido = partido;
	}
	
	
	public String getPartido() {
		return partido;
	}
	
	
	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	public int getNumeroIdentificador() {
		return numeroIdentificador;
	}

	public void setNumeroIdentificador(int numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
	}

	public int getVotos() {
		return this.votosRecebidos;
	}
	
	public void setVotos(int votosRecebidos) {
		this.votosRecebidos = votosRecebidos;
	}
	
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + " " + this.getSobrenome() + " || Partido: "+this.partido+" || Numero: " + this.numeroIdentificador
				+ " || Votos: " + (this.votosRecebidos+1);
	}

}
