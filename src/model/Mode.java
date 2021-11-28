/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

class Mode {
	//Controla a quantidade de jogadores. A maior parte do que está aqui vai para o Control, foi feita apenas para testes
	private Player lPlayer[];
	private boolean isTeams;
	Mode(boolean mode){
		isTeams=mode;
		Player p1= new Player("leonardo","yellow",0);
		Player p2= new Player("Ricardo","green",1);
		if(!isTeams) {
			lPlayer = new Player[2];
		}
		else {
			lPlayer = new Player[4];
			Player p3= new Player("Kaio","blue",0);
			Player p4= new Player("Matheus","red",1);
			lPlayer[2]=p3;
			lPlayer[3]=p4;
		}
		lPlayer[0]=p1;
		lPlayer[1]=p2;
		
	}
	public Player[] get_lPlayer() {
		return lPlayer;
	} 
	public boolean get_isTeams() {
		return isTeams;
	}
}
