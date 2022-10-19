package urna.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import urna.model.Candidato;

class CandidatoTest {

	@Test
	void testGetNumeroIdentificador() {
		Candidato candidato = new Candidato();
		candidato.setNumeroIdentificador(29);
		assertEquals(candidato.getNumeroIdentificador(), 29);
	}

	@Test
	void testGetVotos() {
		Candidato candidato = new Candidato();
		assertEquals(candidato.getVotos(), 0);
	}
	
	@Test
	void testIsVotou() {
		Candidato candidato = new Candidato();
		assertFalse(candidato.isVotou());
	}
	
}
