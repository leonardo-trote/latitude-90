package view;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import java.util.*;
import controller.Controller;
import controller.ObservedIF;
import controller.ObserverIF;
import model.Latitude;

//lass TabuleiroView extends JPanel implements MouseListener

class MainPanel extends JPanel implements MouseListener, ObserverIF{
	
	//cartas
	Image cardBackground;
	Image cardText;
	Image tableImage;
	Image boardImage;
	Image dice1Image;
	Image dice2Image;
	Image dice3Image;
	Image diceTray;
	Image pawnImage,prevImage;
	Image pawnP1Image, pawnP2Image;
	String turnMessage;
	ArrayList<Integer> SelectedPawn;
	ArrayList<ArrayList<Integer>> ValidMovements;
	int Pole1x=208,Pole2x=540,Poley=369, tileW=23,PoleRad=33,PawnSize=15;	
	protected Controller controller;
	
	JButton rollForcedDiceButton = new JButton("Rolar dados escolhidos");
	JButton save = new JButton("Salvar Jogo");

	int forced1 = 1;
	int forced2 = 1;
	
	String [] messageStrings1 = {"1","2","3","4","5","6"};
	JComboBox cmbMessageList1 = new JComboBox(messageStrings1);
	
	String [] messageStrings2 = {"1","2","3","4","5","6"};
	JComboBox cmbMessageList2 = new JComboBox(messageStrings2);
	
	MainPanel(boolean mode, Controller cont){
		controller=cont;
		Latitude.getLatitude().add(this);
		
		this.add(cmbMessageList1);
		cmbMessageList1.setBounds(1100, 290, 50, 25);
		cmbMessageList1.setSelectedItem (1);
		cmbMessageList1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == cmbMessageList1){
					JComboBox cb1 = (JComboBox)e.getSource();
					String msg1 = (String)cb1.getSelectedItem();
					switch(msg1){
					case "1": forced1 = 1;
						break;
					case "2": forced1 = 2;
						break;
					case "3":  forced1 = 3;
						break;
					case "4":  forced1 = 4;
						break;
					case "5":  forced1 = 5;
						break;
					case "6":  forced1 = 6;
						break;
				default: System.out.println("erro");
					}
				}
			}	
		});
		
		this.add(cmbMessageList2);
		cmbMessageList2.setBounds(1155, 290, 50, 25);
		cmbMessageList2.setSelectedItem(1);
		cmbMessageList2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == cmbMessageList2){
					JComboBox cb1 = (JComboBox)e.getSource();
					String msg2 = (String)cb1.getSelectedItem();
					switch(msg2){
					case "1":  forced2 = 1;
						break;
					case "2": forced2 = 2;
						break;
					case "3": forced2 = 3;
						break;
					case "4": forced2 = 4;
						break;
					case "5": forced2 = 5;
						break;
					case "6": forced2 = 6;
						break;
				default: System.out.println("erro");
					}
				}
			}	
		});
		
		rollForcedDiceButton.setBounds(875, 380, 170, 25);
		rollForcedDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.forced_rolls(controller.api.get_vez(),forced1,forced2);
				repaint();}
			});
		this.add(rollForcedDiceButton);
		
		save.setBounds(1100,600,150,25);
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (!controller.drawDice1&&!controller.drawDice2) {
					try {
						controller.saveGame(controller.LPCoord, controller.api.get_vez());
						} catch (IOException e1) {
							System.out.println("Não foi possível salvar a partida!");
						}
				} else {
					System.out.println("Só pode salvar após finalizar o turno!");
				}
			}
		});
		this.add(save);			
		addMouseListener(this);
		this.setLayout(null);
		JButton rollDiceButton = new JButton("Rolar dados");
		rollDiceButton.setBounds(900, 350, 120, 25);
		rollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rolls(controller.api.get_vez());
				repaint();}
			});
		this.add(rollDiceButton);
		
	}
	
	public void notify(ObservedIF o) {
		o.getNumber(1);
		//repaint();
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Stack <Integer> pilhaDeCartas = controller.api.shuffle();
		cardBackground = new ImageIcon("images/Cartas-png/card_back.png").getImage();
		cardText = new ImageIcon("images/Cartas-png/C"+ controller.api.sort_card(pilhaDeCartas)+".png").getImage();
		tableImage = new ImageIcon("images/wooden.png").getImage();
		diceTray = new ImageIcon("images/diceTrayBlue.png").getImage();
		boardImage = new ImageIcon("images/Latitude90-Tabuleiro.png").getImage();
		dice1Image = new ImageIcon("images/die_face_"+ controller.api.get_die1_value() +".png").getImage();
		dice2Image = new ImageIcon("images/die_face_"+ controller.api.get_die2_value() +".png").getImage();
		dice3Image = new ImageIcon("images/colored_dice_"+ controller.api.get_coloreddie_value() +".png").getImage();
		
		pawnP2Image = new ImageIcon("images/pawn_" + controller.api.getPlayerColor(1)+ ".png").getImage();
		controller.generateCPP();
		Graphics2D g2d = (Graphics2D) g;
		//background
		g2d.setPaint(Color.gray);
		g2d.fillRect(0, 0, 1920, 1080);
		g2d.drawImage(tableImage, 0, 0, null);
		//Mensagem de turno
		g2d.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30)); 
		turnMessage="Jogando: "+controller.api.getPlayerColor(controller.api.get_vez());
		g2d.drawString(turnMessage,830,50);
		//tabuleiro
		g2d.drawImage(boardImage, 10 ,0,null);
		
//		//dados
		g2d.drawImage(diceTray, 825 , 70, 270, 270, null);
		if(controller.drawDice1) {
			g2d.drawImage(dice1Image, 950, 115, 50, 50, null);
		}
		if(controller.drawDice2) {
		g2d.drawImage(dice2Image, 880, 200, 50, 50, null);
		}
		if(controller.api.checkEqual()) {
			g2d.drawImage(dice3Image, 970, 230, 50, 50, null);
		}
		//Peões
		g2d.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20)); 
		for(int i=0;i<controller.api.get_playersnumber();i++) {
			ArrayList<ArrayList<Integer>> LPplayer= new ArrayList<ArrayList<Integer>>();
			LPplayer=controller.playerPawnCoord(i,controller.LPCoord);
			for(int j=0;j<LPplayer.size();j++) {
				ArrayList<Integer> pawn = LPplayer.get(j);
				int pawnamount=controller.api.GetCheckAmount(i,pawn.get(0),pawn.get(1),pawn.get(2));
				pawnImage = new ImageIcon("images/pawn_" + controller.api.getPlayerColor(i)+ ".png").getImage();
				int x=pawn.get(4);
				int y=pawn.get(5);
				g2d.drawImage(pawnImage,x-PawnSize/2,y-PawnSize/2,PawnSize,PawnSize,null);
				if (pawnamount>1) {
					String l=new String(Integer.toString(pawnamount));
					if(i<2) {
						g2d.drawString(l,x-15,y-15);
					} else {
						g2d.drawString(l,x+15,y+15);
					}
				}
			}
		}
		//Previsões
		if(ValidMovements!=null) {
		for(int i=0;i<ValidMovements.size();i++) {
			prevImage = new ImageIcon("images/pawn_" + controller.api.getPlayerColor(controller.api.get_vez())+ ".png").getImage();
			String str = "("+ValidMovements.get(i).get(1)+","+ValidMovements.get(i).get(0)+","+ValidMovements.get(i).get(2)+")";
			String[] coord=controller.map.get(str).split(",");
			String strx=coord[0];
			String stry=coord[1];
			Integer x=Integer.parseInt(strx);
			Integer y=Integer.parseInt(stry);
			g2d.drawImage(prevImage,x-PawnSize/2,y-PawnSize/2,10,10,null);
		}
		}
		//Cartas
		g2d.drawImage(cardBackground, 865 , 420, 200, 200, null);
	}
	public void mouseClicked(MouseEvent e) {
		int cx,cy;
		cx=e.getX();
		cy=e.getY();
		Point point = e.getPoint();
		
		//um botão para cada peão do jogador atual
		
		
		if(controller.CurrentPlayerPawns!=null && controller.NotRolled==false) {
			if(SelectedPawn==null) {
				for (int i=0;i<controller.CurrentPlayerPawns.size();i++) {
					int x=controller.CurrentPlayerPawns.get(i).get(4);
					int y=controller.CurrentPlayerPawns.get(i).get(5);
					if(Math.pow((cx-x),2)+Math.pow(cy-y,2)<=169) {
						//Seleciona o Peão
						SelectedPawn= new ArrayList<Integer>();
						SelectedPawn.add(controller.api.get_vez());
						SelectedPawn.add(controller.CurrentPlayerPawns.get(i).get(3));
						int dado1,dado2;
						if(controller.die1Used) {
							dado1=-1;
						}
						else {
							dado1=controller.api.get_die1_value();
						}
						if(controller.die2Used) {
							dado2=-1;
						}
						else {
							dado2=controller.api.get_die2_value();
						}
						ValidMovements=controller.api.get_MovementsCoordinates(controller.LPCoord,controller.api.getPlayers()[controller.api.get_vez()],controller.api.getPlayers(),controller.CurrentPlayerPawns.get(i).get(3),dado1,dado2);
						
						break;
					}
				}
			}
				//Após selecionar
			else {
				int idx=-1;
				for (int i=0;i<controller.CurrentPlayerPawns.size();i++) {
					if(SelectedPawn.get(1)==controller.CurrentPlayerPawns.get(i).get(3)) {
						idx=i;
					}
				}
				int x=controller.CurrentPlayerPawns.get(idx).get(4);
				int y=controller.CurrentPlayerPawns.get(idx).get(5);
				//Se clicar no próprio peão
				if(Math.pow((cx-x),2)+Math.pow(cy-y,2)<=169) {
					ValidMovements=null;
					SelectedPawn=null;
				}
				//Se não
				else {
					for(int i=0;i<ValidMovements.size();i++) {
						String str = "("+ValidMovements.get(i).get(1)+","+ValidMovements.get(i).get(0)+","+ValidMovements.get(i).get(2)+")";
						String[] coord=controller.map.get(str).split(",");
						String strx=coord[0];
						String stry=coord[1];
						Integer coordx=Integer.parseInt(strx);
						Integer coordy=Integer.parseInt(stry);
						if(Math.pow((cx-coordx),2)+Math.pow(cy-coordy,2)<=100) {
							if(ValidMovements.get(i).get(3)==1) {
								controller.die1Used=true;
								controller.drawDice1=false;
							}
							else if(ValidMovements.get(i).get(3)==2) {
								controller.drawDice2=false;
								controller.die2Used=true;
							}
							controller.api.Set_PawnCoord(SelectedPawn.get(0),SelectedPawn.get(1),ValidMovements.get(i).get(0),+ValidMovements.get(i).get(1),ValidMovements.get(i).get(2));
							controller.ResetPawn(controller.LPCoord,SelectedPawn.get(0),ValidMovements.get(i).get(0),+ValidMovements.get(i).get(1),ValidMovements.get(i).get(2));
							controller.api.ChecknRemovePawn(SelectedPawn.get(0),SelectedPawn.get(1));
							SelectedPawn=null;
							ValidMovements=null;
							controller.generateCPP();
							if(controller.die1Used&&controller.die2Used) {
								controller.pass();
								controller.die1Used=false;
								controller.die2Used=false;
							}
							controller.LPCoord=controller.api.CreatePawnArray();
							break;
						}
					}
					
				}
			}
		}
		
		Rectangle imageBounds = new Rectangle(865 , 420, 200, 200);
		if (imageBounds.contains(point)){
		}
		repaint();
	}
	public void mouseEntered(MouseEvent e) {} 
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
