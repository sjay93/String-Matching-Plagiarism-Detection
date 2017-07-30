package com.algo.smpd;

/**
*
* @author Mayur
*/
public class Naive {
   
   public static double start_time=0;
   public static double stop_time=0;
   public static int counterForTime = 0;
   
   public int getCounterforTime(){
	   return counterForTime;
   }
	public void setCounterForTime(){
		counterForTime=0;
	}
   
   public int naiveSearch(String text, String pattern){
       
       int pattern_len = pattern.length(); //M
       int text_len = text.length(); //N
       int i;
       int pattern_count = 0;
       
       start_time = System.nanoTime();
       
       //System.out.println("pattern length = "+String.valueOf(pattern_len));
       for(i=0;i<text_len-pattern_len+1;i++){
           int j;
           for(j=0;j<pattern_len;j++){
//               System.out.println("j = "+String.valueOf(j));
//               System.out.println("Checking "+text.charAt(i+j)+" and "+pattern.charAt(j));
        	   ++counterForTime;
               if(text.charAt(i+j) != pattern.charAt(j) ){
                   pattern_count = Math.max(pattern_count, j);
                   
                   break;
               }  
               
           }
           if(j==pattern_len){
                   i = i + pattern_len;
                   pattern_count = pattern_len;
                   return 1;
           }
           
       }
       stop_time = System.nanoTime();
       
//       System.out.println("Max count is "+pattern_count);
       return 0;        
   }
   
   public double getTimeTaken(){
       return stop_time - start_time;
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       // TODO code application logic here
       
       String text = "AABAACAADAABAABA";
       String pattern = "AABA";
      // double simliarityRatio = naiveSearchMatchingSentences(text,pattern);
      // Naive ns = new Naive();
       //ns.search(text,pattern);
       //System.out.println("The length of the maxmium substring is " +ns.search(text, pattern));
       //System.out.println(getTimeCount());
   }
   
public double naiveSearchMatchingSentences(String sourceSent, String patSent) {

		//System.out.println("s1 :  "+s1+"    pat: "+pat);
		// TODO Auto-generated method stub
		int matchCount=0;
		String srcWords[] = sourceSent.split("\\s+");
		String patWords[] = patSent.split("\\s+");
		
		int patLength = patWords.length;
		int matchwordCount  = naiveSearch(sourceSent, patSent);
		for(int i=0;i<srcWords.length-1;i+=2){
			String tempSrc = srcWords[i]+srcWords[i+1];
			for(int j=0;j<patWords.length;j++){
				matchCount += naiveSearch(tempSrc,patWords[j]);
			}

		}
		if(matchwordCount!=1){
			double matchC = (double)matchCount/2;
			double denom = patLength*srcWords.length/2;
			//System.out.println("denom : "+denom);
			double ratio = matchC/denom;
			if(ratio>0.9)
			return ratio;
			else return 0;

			}
			else{
				return 1;
			}

}
}
