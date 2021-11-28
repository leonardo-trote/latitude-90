/*Kaio Abreu de Freitas - 1913003, Leonardo Trote Martins - 1620572, Ricardo Matheus de Oliveira Amaral - 1621644*/

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Move{

	//verifica se a uma casa do tabuleiro é uma meta
	public static boolean isGoal(Board board, int x, int y, int pole){
		Goal[] listGoals = board.get_lGoal();
		int i;
		
		for (i=0; i < listGoals.length; i++) {
			if (listGoals[i].get_x() == x && listGoals[i].get_y() == y && listGoals[i].get_pole() == pole) {
				return true;
			}
		}
		return false;
	}
	//verifica a quantidade de peões de determinado jogador na casa
	public static int checkAmount(Player player, int x, int y, int pole){
		Pawn[] listPawns = player.get_lPawn();
		int i;
		int amount = 0;
		
		for (i=0; i < listPawns.length; i++) {
			if (listPawns[i].get_x() == x && listPawns[i].get_y() == y && listPawns[i].get_pole() == pole) {
				amount ++;
			}
		}
		return amount;
	}
	public static boolean checkValidMovefromCenter(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int x, int n) {
		Pawn pawn= player.get_Pawnbyid(id);
		for (int i=0; i<LPCoord.size();i++) {
			if(player.get_pole()!=LPCoord.get(i).get(5)) {
				int enemyid=LPCoord.get(i).get(0);
				for(int j=1; j<=n;j++) {
					if(checkAmount(lplayer[enemyid],x,j,pawn.get_pole())>=2) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean checkValidHorizontal(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int n,int dir) {
		Pawn pawn= player.get_Pawnbyid(id);
		for (int i=0; i<LPCoord.size();i++) {
			if(player.get_pole()!=LPCoord.get(i).get(5)) {
				int enemyid=LPCoord.get(i).get(0);
				for(int j=1; j<=n;j++) {
					if(checkAmount(lplayer[enemyid],XSum(pawn.get_x(),j),pawn.get_y(),pawn.get_pole())>=2) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean checkValidVertical(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int n,int dir) {
		Pawn pawn= player.get_Pawnbyid(id);
		int x=pawn.get_x(), y=pawn.get_y(), pole=pawn.get_pole();
		for (int i=0; i<LPCoord.size();i++) {
			if(!(x==6 || x==7 || x==1 || x==12) && y+n>6 && dir==1) {
				return false;
			}
			if(player.get_pole()!=LPCoord.get(i).get(5)) {
				int enemyid=LPCoord.get(i).get(0);
				for(int j=1; j<=n; j++) {
					//Direção crescente e não saindo do polo
					if (dir==1 && y+n<=6) {
						if(checkAmount(lplayer[enemyid],x,y+j,pole)>=2) {
							return false;
						}
					}
					//Direção crescente e saindo do polo
					else if(dir==1 && y+n>6) {
						if(y+j<=6) {
							if(checkAmount(lplayer[enemyid],x,y+j,pole)>=2) {
								return false;
							}
						}
						else {
							if (pole==1) {
								if(checkAmount(lplayer[enemyid],x,13-(y+j),0)>=2) {
									return false;
								}	
							}
							else {
								if(checkAmount(lplayer[enemyid],x,13-(y+j),1)>=2) {
									return false;
								}
							}
						}
					}
					//Direção decrescente e não chegando ao centro
					else if(dir==-1 && y-n>0) {
						if(checkAmount(lplayer[enemyid],x,y-j,pole)>=2) {
							return false;
						}
					}
					//Direção decrescente chegando ao centro
					else {
						if(y-j>0) {
							if(checkAmount(lplayer[enemyid],x,y-j,pole)>=2) {
								return false;
							}
						}
						}
					}
				}
			}
		return true;
	}
	
	public static boolean checkValid(Player player, int n, Pawn p, int dir, boolean Isvertical, Mode game)
	{
		int y=p.get_y(),x=p.get_x(),pole=p.get_pole();
		if(Isvertical) {
			if(!(x==6 || x==7 || x==1 || x==12) && dir==1 && y+n>6) {
				return false;
			}
			for(int i=0;i<game.get_lPlayer().length;i++) {
				if(player.get_pole()!=game.get_lPlayer()[i].get_pole()) {
					for(int j=1; j<=n;j++) {
						if (y+dir*j<=6) {
							if(checkAmount(game.get_lPlayer()[i],x,y+dir*j,pole)>=2){
								return false;
							}
						}
						else {
							if (pole==1) {
								if(checkAmount(game.get_lPlayer()[i],x,13-(y+dir*j),0)>=2){
									return false;
								}
							}
							else {
								if(checkAmount(game.get_lPlayer()[i],x,13-(y+dir*j),1)>=2){
									return false;
								}
							}
						}
					}
				}
			}
		}
		else {
			for(int i=0;i<game.get_lPlayer().length;i++) {
				if(player.get_pole()!=game.get_lPlayer()[i].get_pole()) {
					for(int j=1; j<=n;j++) {
						if(x+dir*j<1) {
							if(checkAmount(game.get_lPlayer()[i],12-(x+dir*j),y,pole)>=2) {
								return false;
							}
						}
						else if(x+dir*j>12) {
							if(checkAmount(game.get_lPlayer()[i],(x+dir*j)-12,y,pole)>=2) {
								return false;
							}
						}
						else {
							if(checkAmount(game.get_lPlayer()[i],x+dir*j,y,pole)>=2){
								return false;
						}
						}
					}
				}
			}
		}
		return true;
	}
	public static boolean checkValidfromCenter(Player player, int n, Mode game, int x, int pole) {
		for (int i=0; i<game.get_lPlayer().length; i++) {
			if(player.get_pole()!=game.get_lPlayer()[i].get_pole()) {
				for(int j=1; j<=n;j++) {
					if(checkAmount(game.get_lPlayer()[i],x,j,pole)>=2) {
						return false;
					}
				}
			}
		}
		return true;
	}
	//Faz uma soma entre 0 e 12
	public static int XSum(int a,int b) {
		if(a+b>12) {
			return a+b-12;
		}
		if(a+b<1) {
			return a+b+12; 
		}
		return a+b;
	}
	//Faz uma soma entre 0 e 6
	public static int XSum6(int a,int b) {
		if (a+b>6) {
			return 13-(a+b);
		}
		return a+b;
	}
	
	//faz a movimentação casa a casa
	public static void movement(Player player, int n,Pawn p,int dir, boolean Isvertical, Board board, Mode game)
	{
		int y=p.get_y(),x=p.get_x(),pole=p.get_pole();
		System.out.printf("%d,%d,%d",x,y,pole);
		System.out.println();
		if(n==0) {
			if (player.isConquered(p)) {
				player.add_score(1);
	    		player.remove_pawn(p.get_id());
	    		System.out.println("Peão chegou no Polo inimigo!");
			}
			if (isGoal(board,x,y,pole) && checkAmount(player,x,y,pole)==2)
			{
				System.out.println("Conquistou uma meta!");
				player.add_score(1);
				board.remove_goal(x,y,pole);
			}
			if (player.isGameOver()) {
				System.out.println("Acabou o jogo");
			}
			return;
		}
		if (y==0) {
			Scanner reader = new Scanner(System.in);
			System.out.println("Voce esta no meio, escolha um sentido de 1 a 12: ");
			int sentido = reader.nextInt();
//			while (!checkValidfromCenter(player,n,game,sentido,pole)){
	//			System.out.println("Movimento invalido, escolha outro sentido: ");
		//		sentido = reader.nextInt();
			//}
			p.set_x(sentido);
			p.set_y(1);
			movement(player,n-1,p,1,true,board,game);
			
			return;
		}
		if(!checkValid(player,n,p,dir,Isvertical,game)) {
			Scanner reader = new Scanner(System.in);
			System.out.println("Movimento invalido, deseja continuar movendo este peão? 0 para não, 1 para sim");
			int acao = reader.nextInt();
			
			while (acao!=0 && acao!=1) { 
				System.out.println("Entrada invalida, deseja continuar movendo este peão? 0 para não, 1 para sim");
				acao = reader.nextInt();
			}
			if (acao==1) {
				System.out.println("Ir na vertical com sentido negativo(0), para horizontal com sentido positivo(1), ou horizontal com sentido negativo(2)?");
				acao = reader.nextInt();
				if (acao==0) {
					movement(player,n,p,-dir,true,board,game);
					return;
				}
				if (acao==1) {
					movement(player,n,p,1,false,board,game);
					return;
				}
				if (acao==2) {
					movement(player,n,p,-1,false,board,game);
					return;
				}
			}
			System.out.println("Escolha o peão pelo id (0,1,2,3,4 ou 5)");
			int id = reader.nextInt();
			while (player.get_PawnIndex(id)==-1) {
				System.out.println("Entrada invalida, Escolha o peão pelo id (0,1,2,3,4 ou 5)");
			}
				Pawn newPawn = player.get_Pawnbyid(id);
				System.out.println("Vertical com sentido positivo(0), Vertical com sentido negativo(1), Horizontal com sentido positivo(3), Horizontal com sentido negativo(4)");
				acao = reader.nextInt();
				while (acao<0 || acao>4) {
					System.out.println("Entrada invalida. Vertical com sentido positivo(0), Vertical com sentido negativo(1), Horizontal com sentido positivo(3), Horizontal com sentido negativo(4)");
					acao = reader.nextInt();
				}
				if (acao==0) {
					movement(player,n,newPawn,1,true,board,game);
					return;
				}
				if (acao==1) {
					movement(player,n,newPawn,-1,true,board,game);
					return;
				}
				if (acao==2) {
					movement(player,n,newPawn,1,false,board,game);
					return;
				}
				if (acao==3) {
					movement(player,n,newPawn,-1,false,board,game);
					return;
				}
				
		}
		if(!Isvertical) {
			if (dir==1) {
				if(x==12) {
					p.set_x(1);
				}
				else {
					p.set_x(x+1);
				}
			}
			if (dir==-1) {
				if(x==1) {
					p.set_x(12);
				}
				else {
					p.set_x(x-1);
				}
			}
			movement(player,n-1,p,dir,Isvertical,board,game);
			return;
		}
		if(y==6 && (x==6 || x==7 || x==1 || x==12) && dir==1) {
			if (pole==0) {
				p.set_pole(1);
			}
			else {
				p.set_pole(0);
			}
			movement(player,n-1,p,-1,Isvertical,board,game);
			return;
		}
		if(dir==1) {
			p.set_y(y+1);
		}
		else {
			p.set_y(y-1);
		}
		movement(player,n-1,p,dir,Isvertical,board,game);
		return;
	}
	public static ArrayList<ArrayList<Integer>> MovementsFromCenter(ArrayList<ArrayList<Integer>> LPCoord, Player player,Player[] lplayer, int id, int n, int die){
		Pawn pawn=player.get_Pawnbyid(id);
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for(int i=1;i<13;i++) {
			if((checkValidMovefromCenter(LPCoord,player,lplayer,id,i,n))){
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(i);
				array.add(n);
				array.add(pawn.get_pole());
				array.add(die);
				temp.add(array);
			}
		}
		return temp;
	}
	public static ArrayList<ArrayList<Integer>> MovementsCoordinates(ArrayList<ArrayList<Integer>> LPCoord, Player player,Player[] lplayer, int id, int die1, int die2){
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		Pawn pawn=player.get_Pawnbyid(id);
		//A partir do centro
		if(pawn.get_y()==0) {
			for(int i=1;i<13;i++) {
				//Dado 1
				if((checkValidMovefromCenter(LPCoord,player,lplayer,id,i,die1))&&(die1!=-1)) {
					ArrayList<Integer> array= new ArrayList<Integer>();
					array.add(i);
					array.add(die1);
					array.add(pawn.get_pole());
					array.add(1);
					temp.add(array);
				}
				//Dado 2
				if(checkValidMovefromCenter(LPCoord,player,lplayer,id,i,die2)&&(die2!=-1)) {
					ArrayList<Integer> array= new ArrayList<Integer>();
					array.add(i);
					array.add(die2);
					array.add(pawn.get_pole());
					array.add(2);
					temp.add(array);
				}
			}
		}
		//Se não for do centro
		else {
			//Dado 1
				//Horizontal
			if((checkValidHorizontal(LPCoord,player,lplayer,id,die1,1)&&(die1!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add((XSum(pawn.get_x(),die1)));
				array.add(pawn.get_y());
				array.add(pawn.get_pole());
				array.add(1);
				temp.add(array);
			}
			if((checkValidHorizontal(LPCoord,player,lplayer,id,die1,-1)&&(die1!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(XSum(pawn.get_x(),-die1));
				array.add(pawn.get_y());
				array.add(pawn.get_pole());
				array.add(1);
				temp.add(array);
			}
			//Dado 2
				// Horizontal
			if((checkValidHorizontal(LPCoord,player,lplayer,id,die2,1)&&(die2!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(XSum(pawn.get_x(),die2));
				array.add(pawn.get_y());
				array.add(pawn.get_pole());
				array.add(2);
				temp.add(array);
			}
			if((checkValidHorizontal(LPCoord,player,lplayer,id,die2,-1)&&(die2!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(XSum(pawn.get_x(),-die2));
				array.add(pawn.get_y());
				array.add(pawn.get_pole());
				array.add(2);
				temp.add(array);
			}
			//Dado 1
				//Vertical
			if((checkValidVertical(LPCoord,player,lplayer,id,die1,1)&&(die1!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(pawn.get_x());
				array.add(XSum6(pawn.get_y(),die1));
				if(pawn.get_y()+die1<=6) {
					array.add(pawn.get_pole());
				}
				else {
					if(pawn.get_pole()==0) {
						array.add(1);
					}
					else {
						array.add(0);
					}
				}
				array.add(1);
				temp.add(array);
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die1,-1)&&(die1!=-1)&&(pawn.get_y()-die1>0))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(pawn.get_x());
				array.add(pawn.get_y()-die1);
				array.add(pawn.get_pole());
				array.add(1);
				temp.add(array);
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die1,-1)&&(die1!=-1)&&(pawn.get_y()-die1==0))){
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(0);
				array.add(0);
				array.add(pawn.get_pole());
				array.add(1);
				temp.add(array);
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die1,-1)&&(die1!=-1)&&(pawn.get_y()-die1<0))) {
				ArrayList<ArrayList<Integer>> temp2 = new ArrayList<ArrayList<Integer>>();
				temp2= MovementsFromCenter(LPCoord,player,lplayer,id,die1-pawn.get_y(),1);
				for(int i=0;i<temp2.size();i++) {
					temp.add(temp2.get(i));
				}
			}
			//Dado 2
				//Vertical
			if((checkValidVertical(LPCoord,player,lplayer,id,die2,1)&&(die2!=-1))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(pawn.get_x());
				array.add(XSum6(pawn.get_y(),die2));
				if(pawn.get_y()+die2<=6) {
					array.add(pawn.get_pole());
				}
				else {
					if(pawn.get_pole()==0) {
						array.add(1);
					}
					else {
						array.add(0);
					}
				}
				array.add(2);
				temp.add(array);
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die2,-1)&&(die2!=-1)&&(pawn.get_y()-die2>0))) {
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(pawn.get_x());
				array.add(pawn.get_y()-die2);
				array.add(pawn.get_pole());
				array.add(2);
				temp.add(array);
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die2,-1)&&(die2!=-1)&&(pawn.get_y()-die2==0))){
				ArrayList<Integer> array= new ArrayList<Integer>();
				array.add(0);
				array.add(0);
				array.add(pawn.get_pole());
				array.add(2);
				temp.add(array);	
			}
			if((checkValidVertical(LPCoord,player,lplayer,id,die2,-1)&&(die2!=-1)&&(pawn.get_y()-die2<0))) {
				ArrayList<ArrayList<Integer>> temp2 = new ArrayList<ArrayList<Integer>>();
				temp2=MovementsFromCenter(LPCoord,player,lplayer,id,die2-pawn.get_y(),2);
				for(int i=0;i<temp2.size();i++) {
					temp.add(temp2.get(i));
				}
			}
			
		}
		return temp;
	}
	//checkValidVertical(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int n,int dir) 
	//public static boolean checkValidHorizontal(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int n,int dir) 
	//checkValidMovefromCenter(ArrayList<ArrayList<Integer>> LPCoord,Player player,Player[] lplayer, int id, int x, int n)
}

	