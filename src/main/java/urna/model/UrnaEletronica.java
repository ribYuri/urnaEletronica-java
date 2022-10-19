package urna.model;

import java.util.ArrayList;

import urna.dao.UrnaDao;

public class UrnaEletronica {
	
	private static UrnaEletronica instance;
	private boolean votacaoIniciada = false;
	
	
	private UrnaEletronica() {
	}
	
	public static UrnaEletronica GetInstance() {
		if (UrnaEletronica.instance == null) {
			instance = new UrnaEletronica();
		}
		return UrnaEletronica.instance;
	}
	
	public boolean isVotacaoIniciada() {
		return votacaoIniciada;
	}

	public void setVotacaoIniciada(boolean votacaoIniciada) {
		this.votacaoIniciada = votacaoIniciada;
	}

	public Eleitor buscaEleitorPeloTitulo(int titulo) {
		return new UrnaDao().buscaEleitorPeloTitulo(titulo);
	}
	
	public void atualizaVotoEleitor(int titulo) {
		new UrnaDao().atualizaVotoEleitor(titulo);
	}
	
	public Candidato buscaCandidatoPeloNumero(int numero) {
		return new UrnaDao().buscaCandidatoPorNumero(numero);
	}
	
	public ArrayList<Candidato> buscarCandidatos(String cargo){
		return new UrnaDao().buscarCandidatos(cargo);
	}
	
	public ArrayList<Candidato> buscarBrancoNulo(){
		return new UrnaDao().buscarBrancoNulo();
	}
	
	public void atualizarVotos(int voto, int numero) {
		new UrnaDao().computarVoto(voto, numero);
	}
	
	public void reiniciarVotacao() {
		new UrnaDao().reiniciarVotacao();
	}
	
}
