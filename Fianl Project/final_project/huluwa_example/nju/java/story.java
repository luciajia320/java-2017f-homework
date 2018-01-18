package nju.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class story {
	private static String myStory = new String("");
	story() {
		super();
	}
	public static void writeStory(String s) {
		myStory+=s;

	}
	public static void writeToFile() {
		//File outfile = new File("resources/story.txt");
		try {
			FileWriter fw = new FileWriter("resources/story.txt");
			fw.write(myStory);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readFile() {
		try {
			FileReader fr = new FileReader("resources/story.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			try {
				line = br.readLine();
				while(line!=null) {
					System.out.println(line);
					line = br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}