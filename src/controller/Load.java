package controller;

import java.io.*;
import java.util.*;

public class Load {
	public static ArrayList<String> main (String[] args) throws IOException{
		ArrayList<String> infos = new ArrayList<String>();
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader("saida.txt")));
			while (s.hasNext()) {
				infos.add(s.nextLine());
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
