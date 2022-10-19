package urna.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import urna.model.Eleitor;

class EleitorTest {

	@Test
	void testIsVotou() {
		Eleitor eleitor = new Eleitor();
		assertFalse(eleitor.isVotou());
	}

	@Test
	void testGetTitulo() {
		Eleitor eleitor = new Eleitor("Teste", "Testando", 16, 33);
		assertEquals(eleitor.getTitulo(), 33);
	}
	
	@Test
	void testIsIdadeMinima() {
		Eleitor eleitor = new Eleitor("Teste", "Testando", 16, 33);
		assertTrue(eleitor.isIdadeMinima());
	}

}
