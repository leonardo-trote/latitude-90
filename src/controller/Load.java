package controller;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Load {
	public static ArrayList<String> main (String[] args) throws IOException{
		ArrayList<String> infos = new ArrayList<String>();
		Scanner s = null;
		try {
			JFileChooser j = new JFileChooser("c:");
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas .txt", "txt");
			j.setAcceptAllFileFilterUsed(false);
			j.addChoosableFileFilter(filtro);
			int respostDoFileChooser = j.showSaveDialog(null);
			if(respostDoFileChooser == JFileChooser.APPROVE_OPTION) {
			s = new Scanner(new BufferedReader(new FileReader(j.getSelectedFile())));
			while (s.hasNext()) {
				infos.add(s.nextLine());
			}
			}
		}
		finally {
			if (s != null) {
				s.close();
			}
			else {
				infos = null;
			}
		}
		return infos;
	}
}
