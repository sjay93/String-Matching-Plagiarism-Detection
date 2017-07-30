package com.algo.smpd;
import java.io.*;
import java.util.*;
public class LongestCommonSubsequence {
	
	public double startTime = 0;
	public double endTime = 0;
	public int counterForTime = 0;
	
	public int getCounterforTime(){
	 	   return counterForTime;
	    }
	public void setCounterForTime(){
		counterForTime=0;
	}
    public int lcs(char str1[],char str2[],int len1, int len2){
        
        if(len1 == str1.length || len2 == str2.length){
            return 0;
        }
        if(str1[len1] == str2[len2]){
            return 1 + lcs(str1,str2,len1+1,len2+1);
        }
        else{
            return Math.max(lcs(str1,str2,len1+1,len2),lcs(str1,str2,len1,len2+1));
        }
    }
    
    public double getTime(){
    	return endTime - startTime;
    }

    public int lcsDynamic(char str1[],char str2[]){
    
        int temp[][] = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        
        startTime = System.nanoTime();
        for(int i=1; i < temp.length; i++){
            for(int j=1; j < temp[i].length; j++){
                if(str1[i-1] == str2[j-1]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                }
                else
                {
                    temp[i][j] = Math.max(temp[i][j-1],temp[i-1][j]);
                }
                if(temp[i][j] > max){
                    max = temp[i][j];
                }
                ++counterForTime;
            }
        }
        endTime = System.nanoTime();
        return max;
    
    }
    
    public static void main(String args[]){
		 Scanner in=new Scanner(System.in);

        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		System.out.println("Enter the first string ");
		String str1=in.nextLine();
		System.out.println("Enter the second string ");
		String str2=in.nextLine();
		String src = "abc def xyz pqr";
		String pat = "abc xyz qwerty";
		int count = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
		System.out.println(count);
		double simliarityRatio = lcs.lcssMatchingSentences(src,pat);
        
        //int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
       // System.out.print(result);
    }

	public double lcssMatchingSentences(String srcSentence, String patSentence) {
		// TODO Auto-generated method stub
		int matchCount=0;
		String srcWords[] = srcSentence.split("\\s+");
		String patWords[] = patSentence.split("\\s+");
		
		char patSentenceArray[] = patSentence.toCharArray();
		
		int patLength = patWords.length;;
		//System.out.println(patLength);
		
		for(int i=0;i<srcWords.length;i++){
			
			for(int j=0;j<patWords.length;j++){
				if(patWords[j].length()==lcsDynamic(srcWords[i].toCharArray(),patWords[j].toCharArray())){				
				matchCount++;
				}
			}

		}
			//System.out.println("patLength    "+patLength);
			//System.out.println("match Count    "+matchCount);
			//System.out.println("match ratio    "+(double)((double)(matchCount)/(double)(patLength)));
			//return (double)((double)(matchCount)/(double)(patLength));
			double matchC = (double)matchCount;
			double denom = patLength*srcWords.length;
		//System.out.println("denom : "+denom);
			double ratio = matchC/denom;

			return ratio;
			
			
		
	}
    
    
    
}