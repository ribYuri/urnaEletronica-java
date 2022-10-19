package urna.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import urna.model.Candidato;
import urna.model.Eleitor;
import urna.model.UrnaEletronica;

class UrnaEletronicaTest {

	@Test
	void testGetInstance() {
		assertNotNull(UrnaEletronica.GetInstance());
	}

	@Test
	void testIsVotacaoIniciada() {
		UrnaEletronica urna = UrnaEletronica.GetInstance();
		assertFalse(urna.isVotacaoIniciada());
		urna.setVotacaoIniciada(true);
	}

	@Test
	void testSetVotacaoIniciada() {
		UrnaEletronica urna = UrnaEletronica.GetInstance();
		assertTrue(urna.isVotacaoIniciada());
	}
	
	@Test
	void testEleitorBuscaPeloTitulo() {
		UrnaEletronica urna = UrnaEletronica.GetInstance();
		Eleitor eleitor = new Eleitor();
		eleitor = urna.buscaEleitorPeloTitulo(0);
		assertEquals(eleitor.getNome(), "Branco");
	}
	
	@Test
	void testeBuscaCandidatoPeloNumero() {
		UrnaEletronica urna = UrnaEletronica.GetInstance();
		Candidato candidato = new Candidato();
		candidato = urna.buscaCandidatoPeloNumero(0);
		assertEquals(candidato.getNome(), "Nulo");
	}

}
