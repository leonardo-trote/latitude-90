package model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCardShuffle() {
		Card baralho = new Card();
		Stack <Integer> meu_baralho = baralho.shuffle_cards();
		Card baralho2 = new Card();
		Stack <Integer> meu_baralho2 = baralho2.shuffle_cards();
		boolean check=false;
		for(int i=0;i<18;i++) {
			if (!(meu_baralho.pop()==meu_baralho2.pop())) {
				check=true;
			}
		}
		assertTrue("Esperado 2 baralhos diferentes",check);
	}
	@Test
	public void testPickCard() {
		Card baralho = new Card();
		Stack <Integer> meu_baralho = baralho.shuffle_cards();
		baralho.pick_card(meu_baralho);
		baralho.pick_card(meu_baralho);
		assertEquals("Esperado o valor 16",16,meu_baralho.size());
	}

}
