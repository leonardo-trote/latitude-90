/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;
 
class Player {
	
	private int score;
	private Pawn lPawn[];
	private String color, name;
	private int pole;
	
	public Player(String s, String color, int pole) {
		name = s;
		score = 0;
		this.color = color;
		this.pole = pole;
		lPawn = new Pawn[6];
		for (int i=0;i<6;i++){
			lPawn[i]=new Pawn(i,pole,color);
		}
	}

	//metodos que retornam ou modificam informações sobre jogadores
	public Pawn[] get_lPawn(){
		return lPawn;
	}

	public String get_name() {
		return name;
	}
		
	public int get_score() {
		return score;
	}
	
	public void add_score(int x) {
		score += x;
	}
		
	public String get_color() {
		return color;
	}
	
	public int get_pole() {
		return pole;
	}
	
	public int get_PawnIndex(int id) {
		for(int i=0;i<lPawn.length;i++) {
			if (lPawn[i].get_id()==id) {
				return i;
			}
		}
		return -1;
	}
	public Pawn get_Pawnbyid(int id){

        return lPawn[get_PawnIndex(id)];
    }
	public void remove_pawn(int id) {
		Pawn newList[]= new Pawn[lPawn.length-1];
		for (int i=0,j=0;i<lPawn.length;i++) {
			if(lPawn[i].get_id()!=id) {
				newList[j]=lPawn[i];
				j++;
			}
		}
		lPawn=newList;
	}
	public boolean isGameOver()
	{
		return lPawn.length==0;
	}
    public boolean isConquered(Pawn p) {
    	return (p.get_y()==0 && p.get_pole()!=pole);  	
    }
	
}
