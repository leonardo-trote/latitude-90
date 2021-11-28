package model;

import java.util.*;
import controller.ObservedIF;
import controller.ObserverIF;


public class Latitude implements ObservedIF{
	
	private static Latitude latitude = null;
	
	private Latitude(){	
	}
	
	public static Latitude getLatitude(){
		if (latitude == null)
			latitude = new Latitude();
		return latitude;
	}
	
private List<ObserverIF> lst = new ArrayList<ObserverIF>();
	
	
	public void add(ObserverIF o){
		lst.add(o);
		
	}
	
	public void remove(ObserverIF o){
		lst.remove(o);
	}
	
	public int getNumber(int n){
		return n;
	}
	
	private void update(){
		ListIterator<ObserverIF> li = lst.listIterator();
		
		while(li.hasNext()){
			li.next().notify(this);
		}
		
	}
	
	
	int vez=0;
	Dice die1=new Dice();
	Dice die2=new Dice();
	Player lPlayer[];
	int playersnumber;
	Dice coloreddie=new Dice();
	Board board= new Board();
	Card cards = new Card();
	boolean isTeams;
	//Mode game=new Mode(false); //AQUI TA FORÇANDO O JOGO A SER ENTRE 2 PLAYERS, MUDAR DEPOIS
	
	public Latitude(boolean mode){
		roll1();
		roll2();
		isTeams=mode;
		Player p1= new Player("leonardo","Vermelho",0);
		Player p2= new Player("Ricardo","Verde",1);
		if(!isTeams) {
			lPlayer = new Player[2];
			playersnumber=2;
		}
		else {
			lPlayer = new Player[4];
			playersnumber=4;
			Player p3= new Player("Kaio","Azul",0);
			Player p4= new Player("Matheus","Amarelo",1);
			lPlayer[2]=p3;
			lPlayer[3]=p4;
		}
		lPlayer[0]=p1;
		lPlayer[1]=p2;
	}
	
	public int get_playersnumber() {
		return playersnumber;
	}
	public void set_dice1(int n){
		die1.set_dice(n);
	}
	
	public void set_dice2(int n){
		die2.set_dice(n);
	}
	
	public void set_dice3(int n){
		coloreddie.set_dice(n);
	}
	
	public void roll1() {
		die1.dice_roll();
	}
	public void roll2() {
		die2.dice_roll();
	}
	public void roll3() {
		coloreddie.dice_roll();
	}
	public int get_die1_value() {
		return die1.get_value();
	}
	public int get_die2_value() {
		return die2.get_value();
	}
	public int get_coloreddie_value() {
		return coloreddie.get_value();
	}
	public int get_vez() {
		return vez;
	}
	public void set_vez(int i) {
		this.vez=i;
	}
	public boolean checkEqual() {
		return die1.equals(die2);
		}
	public Player[] getPlayers() {
		Player temp[];
		temp= new Player[lPlayer.length];
		for(int i=0;i<lPlayer.length;i++) {
			temp[i]=lPlayer[i];
		}
		return temp;
	}
	public String getPlayerColor(int id) {
		return getPlayers()[id].get_color();
	}
	public int getPlayerpole(int id) {
		return getPlayers()[id].get_pole();
	}//Checa se um peão chegou ao polo e remove
	public void ChecknRemovePawn(int idPlayer,int idPawn) {
		Player player=getPlayers()[idPlayer];
		Pawn pawn=player.get_Pawnbyid(idPawn);
		if(player.isConquered(pawn)) {
			player.remove_pawn(idPawn);
		}
		return;
	}//Remove um peão
	public void RemovePawn(int idPlayer, int idPawn) {
		Player player=getPlayers()[idPlayer];
		player.remove_pawn(idPawn);
	}
	public Pawn[] get_lPawn(int idPlayer) {
		return getPlayers()[idPlayer].get_lPawn();
	}
	public Map<String, String> get_hashmap(){
		return board.get_hashmap(); 
	}//Cria lista com as coordenadas dos peões de todos jogadores
	public ArrayList<ArrayList<Integer>> CreatePawnArray(){
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<lPlayer.length;i++) {
			Player player = lPlayer[i];
			for(int j=0;j<player.get_lPawn().length;j++) {
				Pawn pawn=player.get_lPawn()[j];
				if(pawn.get_id()!=-1) {
				ArrayList<Integer> array = new ArrayList<Integer>();
				array.add(i); //Player id
				array.add(pawn.get_x()); //Pawn x
				array.add(pawn.get_y()); //Pawn y
				array.add(pawn.get_pole()); //Pawn pole
				array.add(pawn.get_id());//Pawn id
				array.add(player.get_pole());//Player pole
				temp.add(array);
			}
			}
		}
		return temp;
	}
	//cartas
	public Stack <Integer> shuffle(){
		return cards.shuffle_cards();	
	}
	
	public String sort_card(Stack <Integer> stack){
		return cards.pick_card(stack);
	}
	//Turnos e movimentação
	public int GetCheckAmount(int id, int x, int y, int p) {
		return Move.checkAmount(getPlayers()[id], x, y,p);
	}
	public void nextTurn() {
		if(vez==playersnumber-1) {
			vez=0;
		}
		else {
			vez++;
		}
		return;
	}
	public ArrayList<ArrayList<Integer>> get_MovementsCoordinates(ArrayList<ArrayList<Integer>> LPCoord, Player player,Player[] lplayer, int id, int die1, int die2) {
		return Move.MovementsCoordinates(LPCoord,player,lplayer,id,die1,die2);
	}
	//Recebe o id do player e o id do Peão, e as coordenadas
	public void Set_PawnCoord(int idPlayer, int idPawn,int x,int y,int pole) {
		Pawn pawn =lPlayer[idPlayer].get_Pawnbyid(idPawn);
		pawn.set_x(x);
		pawn.set_y(y);
		pawn.set_pole(pole);
		return;
	}
	
}


