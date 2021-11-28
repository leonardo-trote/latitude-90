package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	@Test
	public void testBlock() {
		Board board=new Board();
		Mode mode= new Mode(false);
		Player tplayer1= mode.get_lPlayer()[0];
		Player tplayer2= mode.get_lPlayer()[1];
		Pawn enemy1=tplayer2.get_Pawnbyid(0);
		Pawn enemy2=tplayer2.get_Pawnbyid(1);
		enemy1.set_x(1);
		enemy1.set_y(1);
		enemy2.set_x(1);
		enemy2.set_y(1);
		Move.movement(tplayer2, 6, enemy1, 1, true, board, mode);
		Move.movement(tplayer2, 6, enemy2, 1, true, board, mode);
		assertFalse("Era pra ser falso",Move.checkValidfromCenter(tplayer1, 6, mode, 1, 0));
	}
	@Test
	public void testOutBoard() {
		Mode mode= new Mode(false);
		Player tplayer1= mode.get_lPlayer()[0];
		Pawn aux=tplayer1.get_Pawnbyid(0);
		aux.set_x(4);
		aux.set_y(3);
		assertFalse("Era pra ser falso",Move.checkValid(tplayer1, 5, aux, 1, true, mode));
		
	}
	@Test
	public void testChangePole() {
		Board board=new Board();
		Mode mode= new Mode(false);
		Player tplayer1= mode.get_lPlayer()[0];
		Pawn aux=tplayer1.get_Pawnbyid(0);
		aux.set_x(1);
		aux.set_y(4);
		Move.movement(tplayer1, 6, aux, 1, true, board, mode);
		assertTrue("Coordenada esperada: x=1,y=3,polo=1",aux.get_y()==3 && aux.get_x()==1 && aux.get_pole()==1);
	}
	

}
