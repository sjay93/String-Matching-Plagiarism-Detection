package com.algo.smpd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class BoyerMoore  {

	//private static String outputFile = "BoyerMooreResult.txt"; 
	//private static FileWriter fw = new FileWriter(outputFile);
	//private static BufferedWriter output=null;
	//private static PrintWriter pw=null;
	public static double startTime = 0;
	public static double endTime = 0;
	public static int counterForTime = 0;
	
	public double timeTaken(){
		return endTime - startTime;
	}
	
	public int getCounterForTime(){
		return counterForTime;
	}

	public void setCounterForTime(){
		counterForTime=0;
	}

public static void main(String[] args) {
		// TODO Auto-generated method stub

		String src = "abc def xyz pqr";
		String pat = "abc xyz qwerty";
		//boyerMoore()
		//double simliarityRatio = boyerMooreMatchingSentences(src,pat);
		//System.out.println(simliarityRatio);

	}
public double boyerMooreMatchingSentences(String sourceSent, String patSent) {

		//System.out.println("s1 :  "+s1+"    pat: "+pat);
		// TODO Auto-generated method stub
		int matchCount=0;
		String srcWords[] = sourceSent.split("\\s+");
		String patWords[] = patSent.split("\\s+");
		
		int patLength = patWords.length;
		int matchwordCount  = boyerMoore(sourceSent, patSent);
		for(int i=0;i<srcWords.length-1;i+=2){
			
			for(int j=0;j<patWords.length;j++){
				matchCount += boyerMoore(srcWords[i]+srcWords[i+1],patWords[j]);
			}
			
		}
		
//System.out.println("match Count  "+matchCount);
if(matchwordCount<1){
double matchC = (double)matchCount/2;
double denom = patLength*srcWords.length/2;
//System.out.println("denom : "+denom);
double ratio = matchC/denom;
//System.out.println("ratio : "+ratio);
if(ratio>0.9)
return ratio;
else return 0;

}
else{
	return 1;
}
//System.out.println("ratio "+ratio);

}
	public int boyerMoore(String src, String pat){
		char pattern[] = pat.toCharArray();
		char str[]=src.toCharArray();
		Map<Character, Integer> badMatchTable = new HashMap<Character, Integer>();

		for (int i = 0; i < pat.length(); i++) {
			badMatchTable.put(pattern[i], (pat.length() - i - 1));
		}

		badMatchTable.put(pattern[pat.length() - 1], pat.length());
		badMatchTable.put('*', pat.length());
		int i =pat.length()-1;

		startTime = System.nanoTime();
		while(i<src.length()){
			int tempI = i;
			for(int j = pat.length()-1;j>=0;j--){
				//System.out.println(pattern[j]+"    out   "+str[i]);
				if(pattern[j] == str[i]){
					//System.out.println(pattern[j]+"       "+str[i]);
					i--;

					if(j==0){
						//System.out.println("Found");


						return 1;

					}
				}
				else{
					//System.out.println("Else  "+i);
					if(badMatchTable.containsKey(str[tempI])){
						i = i+badMatchTable.get(str[tempI]);
						break;

					}
					else{
						//i = i+badMatchTable.get('*');
						i = i+pat.length();
						break;
					}
					//System.out.println("Else  "+i);
				}
				++counterForTime;
			}
			

		}
		endTime = System.nanoTime();

		return 0;	

	}
	}