package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testGet_value() {
		Dice testDice = new Dice();
		assertSame("Esperava o valor 0",0,testDice.get_value());
	}

	@Test
	public void testDice_roll() {
		Dice testDice = new Dice();
		testDice.dice_roll();
		assertTrue("Esperava um numero de 1 a 6",testDice.get_value()>=1 && testDice.get_value()<=6);
	}

	@Test
	public void testEqualsDice() {
		Dice dado1 = new Dice();
		Dice dado2 = new Dice();
		assertTrue("Esperava dados com o mesmo valor",dado1.equals(dado2));
	}

	@Test
	public void testGet_color() {
		Dice testDice = new Dice();
		assertTrue("Esperava um numero de 1 a 6",testDice.get_color()>=1 && testDice.get_color()<=6);
		
	}

}
