package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

import javax.swing.*;

import controller.Controller;
public class StartFrame extends JFrame implements ActionListener{
	public final int LARG_DEFAULT=1200;
	public final int ALT_DEFAULT=700;
	JButton NewGame = new JButton("Novo jogo");
	JButton LoadGame = new JButton("Carregar jogo");
	JButton twoPlayersButton = new JButton("2 Jogadores");
	JButton fourPlayersButton = new JButton("4 Jogadores");
	Controller cont;
	
	
	public StartFrame() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		twoPlayersButton.setBounds(520, 290, 120, 25);
		fourPlayersButton.setBounds(520, 330, 120, 25);
		LoadGame.setBounds(520, 370, 120, 25);
		twoPlayersButton.addActionListener(this);
		fourPlayersButton.addActionListener(this);
		LoadGame.addActionListener(this);
		this.add(twoPlayersButton);
		this.add(fourPlayersButton);
		this.add(LoadGame);
		getContentPane().add(new StartPanel());
		
		}
		public void loadGame() throws IOException{
			ArrayList<String> infos = controller.Load.main(null);
			
			try {
				this.setVisible(false);
				int vez=Integer.valueOf(infos.get(0));
				int nPlayers=Integer.valueOf(infos.get(1));
				if (nPlayers==2) {
					cont=new Controller(false);
					MainFrame mainFrame = new MainFrame(false, cont);
					mainFrame.setVisible(true);
				}
				else {
					cont=new Controller(true);
					MainFrame mainFrame = new MainFrame(true, cont);
					mainFrame.setVisible(true);
				}
				cont.api.set_vez(vez);
				for(int i=2;i<infos.size();i++) {
					int idPlayer,x,y,pole,idPawn;
					String str=infos.get(i);
					String newstr=str.substring(1,str.length()-1);
					String[] splitted=newstr.split(",");
					idPlayer=Integer.valueOf(splitted[0].strip());
					x=Integer.valueOf(splitted[1].strip());
					y=Integer.valueOf(splitted[2].strip());
					pole=Integer.valueOf(splitted[3].strip());
					idPawn=Integer.valueOf(splitted[4].strip());
					cont.api.Set_PawnCoord(idPlayer, idPawn, x, y, pole);					
					//SETAR TODOS OS PEÕES DO INFOS PARA AS COORDENADAS SALVAS USANDO Set_PawnCoord//
					//Cada peão foi salvo como [Playerid,x,y,pole,Pawnid,Playerpole]//
					//SE O PEÃO NÃO TIVER, REMOVER ELE DA LISTA DE PEÕES DO JOGADOR COM remove_pawn//
				}
				for(int i=0;i<nPlayers;i++) {
					boolean remove=true;
					for(int j=0;j<6;j++) {
						for(int k=2;k<infos.size();k++) {
							if(i==Integer.valueOf(infos.get(k).substring(1,infos.get(k).length()-1).split(",")[0].strip()) && j==Integer.valueOf(infos.get(k).substring(1,infos.get(k).length()-1).split(",")[4].strip())) {
								remove=false;
							}
						}
						if(remove) {
							cont.api.RemovePawn(i,j);
						}
					}
				}
				cont.updateLPCoord();
			} finally {
				//
			}
		}
		public void actionPerformed(ActionEvent e){
		if (e.getSource() == twoPlayersButton){
			cont=new Controller(false);
			MainFrame mainFrame = new MainFrame(false, cont);
			mainFrame.setVisible(true);
			this.setVisible(false);
			
		}
		if (e.getSource() == fourPlayersButton){
			cont=new Controller(true);
			MainFrame mainFrame = new MainFrame(true, cont);
			mainFrame.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource() == LoadGame) {
			try{loadGame();}
			catch(IOException e1) {
				System.out.println("Não foi possível carregar o jogo");
			}
		}
		}
}