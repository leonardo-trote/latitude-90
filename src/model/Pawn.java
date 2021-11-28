/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

class Pawn {
    private int id;
	private int x;
    private int y;
    private int pole;
    private String color;

    public Pawn(int id, int pole, String color){
        this.id = id;
        x = 0;
        y = 0;
        this.pole = pole;
        this.color = color; 
    }

  //metodos que retornam ou modificam informações sobre peões
    public int get_id(){
        return id;
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public int get_pole(){
        return pole;
    }
    public String get_color(){
        return color;
    }
    public void set_x(int n) {
    	x=n;
    }
    public void set_y(int n) {
    	y=n;
    }
    public void set_pole(int n) {
    	pole=n;
    }
}
