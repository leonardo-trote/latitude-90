package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModeTest {

	@Test
	public void testCompetitive() {
		Mode doisvdois = new Mode(true);
		assertTrue("Esperado tamanho 4 e booleana true",doisvdois.get_lPlayer().length==4 && doisvdois.get_isTeams()==true);
	}
	@Test
	public void testSingle() {
		Mode umvum = new Mode(false);
		assertTrue("Esperado tamanho 2 e booleana false",umvum.get_lPlayer().length==2 && umvum.get_isTeams()==false);
	}
}
