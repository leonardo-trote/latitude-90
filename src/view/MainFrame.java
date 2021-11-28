package view;

import java.awt.*;
import java.io.IOException;
import controller.Controller;
import javax.swing.*;

import controller.Controller;
public class MainFrame extends JFrame {
	public final int LARG_DEFAULT=1280;
	public final int ALT_DEFAULT=700;
	
	public MainFrame(boolean mode, Controller cont){
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new MainPanel(mode, cont));
		}
}