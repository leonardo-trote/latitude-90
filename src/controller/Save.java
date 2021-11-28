package controller;

import java.io.*;
import java.util.*;
import java.io.BufferedWriter;

public class Save {
	public static void main (ArrayList<ArrayList<Integer>> args,int vez, int nPlayers) throws IOException{
		FileWriter outputStream=null;
		BufferedWriter WriteFileBuffer=null;
		try {
			outputStream = new FileWriter("saida.txt");
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
		} finally {
			if(WriteFileBuffer!= null) {
				WriteFileBuffer.close();
			}
		}
	}
}
