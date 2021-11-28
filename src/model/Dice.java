/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

import java.util.*;

interface DiceInterface{
	
	int get_value();
	void dice_roll();
	
}

class Dice implements DiceInterface{
	private int value;
	
	public Dice() {
		value = 1;
	}
	
	public void set_dice(int n){
		this.value  = n;
	}

	public int get_value() {
		return this.value;
	}
	
	//set value como um número inteiro entre 1 e 6
	public void dice_roll() {
		Random r = new Random();
		this.value = r.nextInt(6) + 1;
	}
	
	//verifica se os dois lançamentos foram iguais
	public boolean equals(Dice d) {
		if (this.value == d.value)
			return true;
		return false;
	}
	
	//retorna o valor associado a determinada cor
	 public int get_color() {
		this.dice_roll();
		return this.get_value();
	}
	 public Dice get_Die() {
		 return this;
	 }
	
}