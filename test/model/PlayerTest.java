package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() {
		Player testPlayer=new Player("Teste","white",0);
		testPlayer.add_score(4);
		assertTrue("Esperava os valores definidos na instancia de teste.", testPlayer.get_lPawn().length==6 && testPlayer.get_name()=="Teste" && testPlayer.get_score()==4 && testPlayer.get_color()=="white" && testPlayer.get_pole()==0);
	}
	
	@Test
	public void testGameover() {
		Player testPlayer=new Player("Pedro","orange",1);
		for (int i=0;i<6;i++) {
			testPlayer.remove_pawn(i);
		}
		assertTrue("Esperava true",testPlayer.isGameOver());
	}
	@Test
	public void testIsConquered() {
		Player testPlayer=new Player("Pedro","orange",1);
		Pawn aux = testPlayer.get_Pawnbyid(0);
		aux.set_pole(0);
		assertTrue("Esperava true", testPlayer.isConquered(aux));
		
	}
}
