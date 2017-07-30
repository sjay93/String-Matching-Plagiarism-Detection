
package com.algo.smpd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class KMP_String_Matching
{
    public double startTime = 0;
    public double endTime = 0;
    public int counterForTime = 0;
    
    public double timeTaken(){
    	return endTime - startTime;
    }
    
    public int getCounterforTime(){
 	   return counterForTime;
    }
	
	public void setCounterForTime(){
		counterForTime=0;
	}
	public int KMPSearch(String pat, String txt)
    {
    	//System.out.println("Started");
        int M = pat.length();
        int N = txt.length();
         
        int lps[] = new int[M];
        int j = 0;  
         
        computeLPSArray(pat,M,lps);
        //for(int i: lps)
        //    System.out.println(i);
        int i = 0; 
        startTime = System.nanoTime();
        while(i < N)
        {
        	 
        	if(pat.charAt(j) == txt.charAt(i))
            {++counterForTime;
                j++;
                i++;
                if(i>=N){
                	break;
                }
                
            }
            if(j == M)
            {
                //System.out.println("Found pattern at index " + (i-j));
                j = lps[j-1];
                
                return 1;
            }
            else if(pat.charAt(j) != txt.charAt(i))
            {
                if(j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
               
                
 
            }
            //System.out.println("i = "+i+" j = "+j);
           
        }
        endTime = System.nanoTime();
        return 0;
    }
     
    void computeLPSArray(String pat, int M, int lps[])
    {
        int len = 0; 
        int i = 1;
        lps[0] = 0;  
         
        while(i<M)
        {
            if(pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[len] = len;
                i++;
            }
            else  
            {
                if(len != 0)
                {
                    len = lps[len-1];
                     
                }
                else  
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
         
    }
 
    public static void main(String args[])
    {
        //String txt = "AAAACAAAA";
        //String pat = "AAAA";
    	
    	 String txt = "";
         String pat = "";
        
    	FileReader f1,f2;
		try {
			f1 = new FileReader(args[0]);
			f2 = new FileReader(args[1]);
	        BufferedReader br = new BufferedReader(f1);
	        BufferedReader bs = new BufferedReader(f2);
	        txt = br.readLine();
	        pat = bs.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
		/*BufferedReader inputStream;
		String file1="",file2="";
		try {
			inputStream = new BufferedReader(new FileReader(args[0]));
			String currentLine;
			
			while((currentLine = inputStream.readLine())!= null){
				//System.out.println(currentLine);
				if(currentLine!=null){
				file1 = file1+" "+currentLine;
				
				System.out.println(file1);
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inputStream = new BufferedReader(new FileReader(args[1]));
			String currentLine;
			
			while((currentLine = inputStream.readLine())!=null){
				file2 = file2.concat((" "+currentLine));
				
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
        
        //new KMP_String_Matching().KMPSearch(pat,txt);
    }
    
    public double KMPMatchingSentences(String text, String pattern) {
		// TODO Auto-generated method stub
		int matchCount=0;
		String srcWords[] = text.split("\\s+");
		String patWords[] = pattern.split("\\s+");
		int matchwordCount=0;
		if(text.length()>pattern.length()){matchwordCount  = KMPSearch(pattern, text);}else{matchwordCount  = KMPSearch(text, pattern);}
		int patLength = patWords.length;
		
		for(int i=0;i<srcWords.length;i+=2){  //i+=2 to emulate two src concatenations at a time

			for(int j=0;j<patWords.length;j++){
				
				if(i+1<srcWords.length){
					matchCount += KMPSearch(patWords[j],srcWords[i]+srcWords[i+1]);
				}
				
				else{
					matchCount += KMPSearch(patWords[j],srcWords[i]);
				}
				
			}

		}

		if(matchwordCount<1){
			double matchC = (double)matchCount/2;
			double denom = patLength*srcWords.length/2;
			//System.out.println("denom : "+denom);
			double ratio = matchC/denom;
			//System.out.println("Ratio : "+ratio);
			if(ratio>0.07)
			return ratio;
			else return 0;

			}
			else{
				return 1;
			}

		
		
	}
}
 