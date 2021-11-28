package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {

	@Test
	public void testPawn() {
		Pawn peao= new Pawn(37,1,"yellow");
		peao.set_pole(0);
		peao.set_x(3);
		peao.set_y(2);
		assertTrue("Esperava os valores definidos na instancia de teste.",peao.get_color()=="yellow" && peao.get_id()==37 && peao.get_pole()==0 && peao.get_x()==3 && peao.get_y()==2);
	}

}
