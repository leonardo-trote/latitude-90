package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;

import model.Latitude;
import view.MainFrame;

public class Controller {
	public Latitude api;
	public boolean NotRolled=true, die1Used=false, die2Used=false, drawDice1=false,drawDice2=false;
	public Map<String, String> map;
	public ArrayList<ArrayList<Integer>> LPCoord,CurrentPlayerPawns;
	public Controller(boolean mode) {
		api=new Latitude(mode);
		map=api.get_hashmap();
		LPCoord=api.CreatePawnArray();
	}
	public void pass()	{
		NotRolled=true;
		api.nextTurn();
	}
	//recebe o id do player, e o mapa com todos peões
	public ArrayList<ArrayList<Integer>> playerPawnCoord(int id,ArrayList<ArrayList<Integer>> LPCoord ){
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for (int i=0;i<LPCoord.size();i++) {
			if(LPCoord.get(i).get(0)==id && LPCoord.get(i).get(4)!=-1) {
				ArrayList<Integer> array = new ArrayList<Integer>();
				array.add(LPCoord.get(i).get(1));
				array.add(LPCoord.get(i).get(2));
				array.add(LPCoord.get(i).get(3));
				array.add(LPCoord.get(i).get(4));
				String str = "("+array.get(1)+","+array.get(0)+","+array.get(2)+")";
				String[] coord=map.get(str).split(",");
				String strx=coord[0];
				String stry=coord[1];
				Integer x=Integer.parseInt(strx);
				Integer y=Integer.parseInt(stry);
				array.add(x);
				array.add(y);				
				temp.add(array);
			}
		}
		return temp;
	}
	//Seta os dados
	public void forced_rolls(int id, int d1, int d2){
		if(NotRolled){
			drawDice1=true;
			drawDice2=true;
			LPCoord=api.CreatePawnArray();
			CurrentPlayerPawns=playerPawnCoord(api.get_vez(),LPCoord);
			api.set_dice1(d1);
			api.set_dice2(d2);
			if(api.checkEqual()) {
				api.roll3();
			}
			NotRolled=false;
		}
	}
	
	//recebe o id do player e rola os dados aleatóriamente
	public void rolls(int id) {
		if(NotRolled) {
			drawDice1=true;
			drawDice2=true;
			LPCoord=api.CreatePawnArray();
			CurrentPlayerPawns=playerPawnCoord(api.get_vez(),LPCoord);
			api.roll1();
			api.roll2();
			if(api.checkEqual()) {
				api.roll3();
			}
			NotRolled=false;
		}
		//pass();
		//get_MovementsCoordinates(ArrayList<ArrayList<Integer>> LPCoord, Player player,Player[] lplayer, int id, int die1, int die2)
	}
	//Atualizar o array de coordenadas
	public void updateLPCoord() {
		LPCoord=api.CreatePawnArray();
	}//Gera o array de Coordenadas de um player
	public void generateCPP() {
		CurrentPlayerPawns=playerPawnCoord(api.get_vez(),LPCoord);
	}
	//recebe o id do player, x, y e pole
	public void ResetPawn(ArrayList<ArrayList<Integer>> LPCoord, int id,int x, int y, int pole) {
		for(int i=0;i<api.get_playersnumber();i++) {
			int enemypole=api.getPlayerpole(i);
			if(enemypole!=api.getPlayerpole(id)) {
				int enemyid=i;
				if(api.GetCheckAmount(enemyid,x,y,pole)==1) {
					for(int j=0;j<LPCoord.size();j++) {
						if(LPCoord.get(j).get(0)==enemyid&&LPCoord.get(j).get(1)==x&&LPCoord.get(j).get(2)==y&&LPCoord.get(j).get(3)==pole) {
							api.Set_PawnCoord(enemyid, LPCoord.get(j).get(4),0,0,enemypole);
							break;
						}
					}
				}
			}
		}
		//if(checkAmount(lplayer[enemyid],x,j,pawn.get_pole())>=2)
	}//salva o jogo
	public void saveGame(ArrayList<ArrayList<Integer>> LPCoord,int vez) throws IOException{
		try{
			Save.main(LPCoord, vez, api.get_playersnumber());
		} catch (IOException e) {
			System.out.println("Deu erro ao salvar partida!");
		}
	}//Carrega um jogo salvo
	public void loadGame() throws IOException{
		
	}
	
}
