package view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;

class StartPanel extends JPanel{
	
	Image backgroundImage;
	
	StartPanel (){
		backgroundImage = new ImageIcon("images/background.png").getImage();
		
		
		
	}	
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.white);
		g2d.drawImage(backgroundImage, 0 ,0, 1200, 700, null);
		
		g2d.setFont(new Font("Arial",Font.BOLD,50));
		g2d.drawString("Latitude 90 Graus",390,200);
	
		g2d.setFont(new Font("Arial",Font.PLAIN,20));
		g2d.drawString("Escolha um modo de jogo:",470,270);
		this.setVisible(true);
	}
	
}

