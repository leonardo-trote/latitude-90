package controller;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedWriter;

public class Save {
	public static void main (ArrayList<ArrayList<Integer>> args,int vez, int nPlayers) throws IOException{
		FileWriter outputStream=null;
		BufferedWriter WriteFileBuffer=null;
		try {
			JFileChooser j = new JFileChooser("c:");
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apenas .txt", "txt");
			j.setAcceptAllFileFilterUsed(false);
			j.addChoosableFileFilter(filtro);
			int respostDoFileChooser = j.showSaveDialog(null);
			if(respostDoFileChooser == JFileChooser.APPROVE_OPTION) {
				outputStream = new FileWriter(j.getSelectedFile());
				WriteFileBuffer = new BufferedWriter(outputStream);
				WriteFileBuffer.write(Integer.toString(vez));
				WriteFileBuffer.newLine();
				WriteFileBuffer.write(Integer.toString(nPlayers));
				WriteFileBuffer.newLine();
				for(int i=0;i<args.size();i++) {
					String l=Arrays.toString(args.get(i).toArray());
					WriteFileBuffer.write(l);
					WriteFileBuffer.newLine();
				}
			}
		} finally {
			if(WriteFileBuffer!= null) {
				WriteFileBuffer.close();
			}
		}
	}
}
