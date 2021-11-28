/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

class Goal {
	private int id;
	private int x;
    private int y;
    private int pole;

    public Goal(int id, int x, int y, int pole){
        this.id = id;
        this.x = x;
        this.y = y;
        this.pole = pole;
    }

    //metodos que retornam ou modificam informações sobre meta
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
