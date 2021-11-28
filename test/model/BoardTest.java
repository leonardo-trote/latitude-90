package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testGet_lGoal() {
		Board tabuleiro = new Board();
		assertEquals("Esperava tamanho 12",12,tabuleiro.get_lGoal().length);
	}

	@Test
	public void testRemove_goal() {
		Board tabuleiro = new Board();
		tabuleiro.remove_goal(10, 5, 1);
		assertEquals("Esperava tamanho 11",11,tabuleiro.get_lGoal().length);
	}

}
