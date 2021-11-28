/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

import java.util.*;

class Card{
    private int value;
    
    //valor aleatório entre 1 e 18 para sortear a carta
    public int random_card() {	
    	Random r = new Random();
		value = r.nextInt(18) + 1;
		return value;
	}
    
    public Stack <Integer> shuffle_cards(){
    	int value;
    	
    	Stack <Integer> card_stack  = new Stack<>();
    	
    	//enquanto o tamanho da pilha for menor que 18, adiciona uma carta aleatória na pilha (caso já não esteja lá)
    	while (card_stack.size() < 18){ 
    		value = random_card(); 
    		if (card_stack.search(value) == -1){
    			card_stack.push(value);
    		}		
    	}
    	
    	return card_stack;
    }

    public String pick_card(Stack <Integer> stack){
    
    	int picked_number = stack.pop();
    	
    	if (picked_number < 10){
    		
    		return "0"+Integer.toString(picked_number);
    	} 
    	return Integer.toString(picked_number);
    }
}