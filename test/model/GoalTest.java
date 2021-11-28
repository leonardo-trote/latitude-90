package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoalTest {

	@Test
	public void testGoal() {
		Goal meta= new Goal(4,3,4,0);
		meta.set_pole(1);
		meta.set_x(1);
		meta.set_y(2);
		assertTrue("Esperava os valores definidos na instancia de teste.",meta.get_id()==4 && meta.get_pole()==1 && meta.get_x()==1 && meta.get_y()==2);
	}

}
