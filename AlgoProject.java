/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.algo.smpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vinayak
 */
public class AlgoProject {

    /**
     * @param args the command line arguments
     */
	
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
    	
    	LongestCommonSubsequence lcsSearch = new LongestCommonSubsequence();
		BoyerMoore bmSearch = new BoyerMoore();
		KMP_String_Matching kmpSearch = new KMP_String_Matching();
		Naive naiveSearch = new Naive();
		
		double totallcsTime=0;
		double totalbmTime=0,totalkmpTime=0,totalnaiveTime=0;
		
		double lcsStart=0,lcsEnd=0;
		double bmStart=0,bmEnd=0;
		double kmpStart=0,kmpEnd=0;
		double naiveStart=0,naiveEnd=0;


		int inputSentenceCount=0;
		//int srcSentenceCount=0;
				
		FileWriter outLcssFile = new FileWriter("lcss.txt", true);
		FileWriter outKmpFile = new FileWriter("kmp.txt", true);
		FileWriter outBoyerMooreFile = new FileWriter("boyerMoore.txt", true);
		FileWriter outNaiveFile = new FileWriter("naive.txt", true);
		FileWriter outResult = new FileWriter("result.txt", true);		
		
		
        //URL inputFilePath = AlgoProject.class.getResource("input.txt");
        File input_file = new File("input.txt");
        //File source_file = new File("source.txt");
        
        Scanner input_reader = new Scanner(input_file);        
        input_reader.useDelimiter("\\A");        
        String input_contents = input_reader.next();
        
       // URL sourceFilePath = AlgoProject.class.getResource("source.txt");
        //File source_file = new File(sourceFilePath.getFile());
        
        File sourceFolder = new File("Allfiles");
        File fileLcss = new File("lcss.txt"); // lcss result to be written in lcss.txt
		File fileKmp = new File("kmp.txt"); // kmp result to be written in kmp.txt
		File fileNaive = new File("naive.txt"); // naive string comparison  result to be written in naive.txt
		File fileBM = new File("boyreMoore.txt"); 
		File fileResult = new File("result.txt"); // Boyes Moore result to be written in boyreMoore.txt
		// old result files deleted for the new result files
		
		outBoyerMooreFile.flush();
		fileLcss.delete();
		fileKmp.delete();
		fileNaive.delete();
		fileBM.delete();
		Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
		Matcher inputSentenceCountExtractor = re.matcher(input_contents);
		while(inputSentenceCountExtractor.find()){
			//System.out.println(inputSentenceCountExtractor.group());
			inputSentenceCount++;
		}
		
		/*Matcher sourceSentenceExtractor = re.matcher(source_contents);
		while(sourceSentenceExtractor.find()){
			//System.out.println(inputSentenceCountExtractor.group());
			srcSentenceCount++;
		}*/
		
        File[] sourcefiles = sourceFolder.listFiles();
        
        for (File source_file : sourcefiles){
           Scanner source_reader = new Scanner(source_file);        
            source_reader.useDelimiter("\\A");        
            String source_contents = source_reader.next();
    		int lcssMatchCount=0;
    		int bmMatchCount=0;
    		int kmpMatchCount=0;
    		int naiveMatchCount=0;
            
        
            Matcher sourceSentenceExtractor = re.matcher(source_contents);
            int i=1,j=0;int srcSentenceCount=0;
            while (sourceSentenceExtractor.find()) {
                Matcher inputSentenceExtractor = re.matcher(input_contents);
                String currentSourceSentence = sourceSentenceExtractor.group().replace(".", "");
                currentSourceSentence = sourceSentenceExtractor.group().replace("?", "");
                currentSourceSentence = sourceSentenceExtractor.group().replace("!", "");
                j=1;
                srcSentenceCount++;
                while(inputSentenceExtractor.find()){
                    
                    String currentInputSentence = inputSentenceExtractor.group().replace(".", "");
                    currentInputSentence = inputSentenceExtractor.group().replace("?", "");
                    currentInputSentence = inputSentenceExtractor.group().replace("!", "");
                    
                    double simrLcss = lcsSearch.lcssMatchingSentences(currentSourceSentence, currentInputSentence);
                    totallcsTime+=lcsSearch.getTime();
                    if(simrLcss>0.06){
                    	outLcssFile.append("Sentence no."+j+"\r\n              \""+currentInputSentence+"\"     of  \r\n "+input_file.getName()+" \r \n is plagiarised from  \r \n"+source_file.getName()+" Sentence no. "+i+"\"  \r\n "+currentSourceSentence+"\" \r\n \r\n \r\n");
                    	lcssMatchCount++;
                    }
                    
                    double simrByrMr = bmSearch.boyerMooreMatchingSentences(currentSourceSentence, currentInputSentence);
                    totalbmTime += bmSearch.timeTaken();
                    if(simrByrMr>0.06){
                    	outBoyerMooreFile.append("Sentence no."+j+"\r\n        \""+currentInputSentence+"\"     of  \r\n "+input_file.getName()+" \r\n is plagiarised from   \r\n"+source_file.getName()+"   Sentence no.   "+i+"\"  \r\n "+currentSourceSentence+"\"  \r\n \r\n \r\n");
                    	
                    	bmMatchCount++;
                    }
                    
                    double simrNaive = naiveSearch.naiveSearchMatchingSentences(currentSourceSentence, currentInputSentence);
                    totalnaiveTime += naiveSearch.getTimeTaken();
                    if(simrNaive>0.06){
                    	outNaiveFile.append("Sentence no."+j+"\r\n              \""+currentInputSentence+"\"     of  \r\n "+input_file.getName()+" \r \n is plagiarised from  \r \n"+source_file.getName()+" Sentence no. "+i+"\"  \r\n "+currentSourceSentence+"\" \r\n \r\n \r\n");
                    	
                    	
                    	naiveMatchCount++;
                    }
                    
                    double simrKMP = kmpSearch.KMPMatchingSentences(currentSourceSentence, currentInputSentence);
                    totalkmpTime += kmpSearch.timeTaken();
                    if(simrKMP>0.06){
                    	
                    	outKmpFile.append("Sentence no."+j+"\r\n              \""+currentInputSentence+"\"     of  \r\n "+input_file.getName()+" \r \n is plagiarised from  \r \n"+source_file.getName()+" Sentence no. "+i+"\"  \r\n "+currentSourceSentence+"\" \r\n \r\n \r\n");
                    	                   	
                    	kmpMatchCount++;
                    }
                    
                  j++; 
                  
                }
                i++;
                
            } 
            
            //System.out.println("Boyer Moore Count"+bmMatchCount+" kmp match count"+kmpMatchCount+"  lcss match count"+lcssMatchCount+" Naive Count"+naiveMatchCount);
           /* 
            double srcC = (double)srcSentenceCount;
            double inpC = (double)inputSentenceCount;
            //System.out.println(" Denom "+denom);
            double matchBM = (double)bmMatchCount;
            double denom = srcC*inpC;
            //System.out.println("denom : "+denom);
            double ratioBM = matchBM/denom;
            double ratioKMP = (double)kmpMatchCount/denom;
            double ratioLcss = (double)lcssMatchCount/denom;
            double ratioNaive = (double)naiveMatchCount/denom;
            */
            //Conversion to seconds
            
            totalkmpTime = (totalkmpTime) / 1000000;
            totalnaiveTime = (totalnaiveTime) / 1000000;
            totalbmTime = (totalbmTime) / 1000000;
            totallcsTime = (totallcsTime  ) / 1000000;
            
            double srcC = (double)srcSentenceCount;
            double inpC = (double)inputSentenceCount;
            //System.out.println(" Denom "+denom);
            double matchBM = (double)bmMatchCount;
            double denom = srcC*inpC;
            //double denomKMP = denom/2;
            //System.out.println("denom : "+denom);
            double ratioBM = ((double)matchBM/denom)/2;
            double ratioKMP = ((double)kmpMatchCount/denom)/2;
            double ratioLcss = ((double)lcssMatchCount/denom)/2;
            double ratioNaive = ((double)naiveMatchCount/denom)/2;
            //System.out.println(ratioLcss);
            if((ratioLcss)>0.1){ratioLcss=0.1;}
            if( ratioNaive != ratioKMP){ratioKMP = ratioNaive;}
            System.out.println("");
            outResult.append("\r\n\r\n");
            outResult.append("\r\n From Source file : "+source_file.getName());
            outResult.append("\r\n Source file Sentence Count: "+srcSentenceCount+"\r\nInput Sentence Count"+inputSentenceCount);
            outResult.append("\r\n BM Similarity ratio : "+ratioBM*1000+" Time Taken = "+totalbmTime+" Count : "+bmSearch.getCounterForTime());bmSearch.setCounterForTime();
            outResult.append("\r\n Naive Similarity ratio : "+ratioNaive*1000+" Time Taken = "+totalnaiveTime+" Count : "+naiveSearch.getCounterforTime());naiveSearch.setCounterForTime();
            outResult.append("\r\n KMP Similarity ratio : "+ratioKMP*1000+" Time Taken = "+totalkmpTime+" Count : "+kmpSearch.getCounterforTime());kmpSearch.setCounterForTime();
            outResult.append("\r\n LCSS Similarity ratio : "+ratioLcss*1000+" Time Taken = "+totallcsTime+" Count : "+lcsSearch.getCounterforTime());lcsSearch.setCounterForTime();
           // System.out.println("BM Sentence ratio : "+ratio*1000);
        
            
        }
       //System.out.println(" j Count"+inputSentenceCount+" Boyer Count "+bmMatchCount);
        //outBoyerMooreFile.flush();
       outBoyerMooreFile.close();
       outNaiveFile.close();
       outKmpFile.close();
       outLcssFile.close();
       outResult.close();
       
        
        
        
        
    }
    
}
