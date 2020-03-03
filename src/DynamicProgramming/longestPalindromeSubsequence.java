/**
 *FileName:longestPalindromeSubsequence.java
 * @author:lmy
 *Creatdate:2018年12月21日下午1:46:48
 */
package DynamicProgramming;

import java.util.List;
import java.util.Stack;

/**
 * @author lmy
 *
 */
public class longestPalindromeSubsequence {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月21日下午1:46:48
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	
	static char[] chars;//存放s拆分后的
	static char[] sub;//存放回文子序列

	static int[][] lps;//lps[i][j]表示序列从下标i到j之间的最长回文序列
	
	static String s = "bcddxca";
	public static void main(String[] args) {
		
		System.out.println("原字符串为："+s);
		System.out.println();
        chars = s.toCharArray();//将s拆分开，便于计算
        int length = chars.length;
        lps = new int[length][length];
        for (int i = 0; i < length; i++) {
            lps[i][i] = 1; // 单个字符的最长回文子序列长度为1
        }

        int lspLength = getLPSLength(0,length-1);
        System.out.println("最长回文序列的长度为："+lspLength);
        System.out.println();     
        char[] subResult = getLPS(lspLength);
        System.out.print("最长回文序列为：");
        for(int i=0;i<subResult.length;i++){
        	System.out.print(subResult[i]+" ");
        }
        System.out.println();System.out.println();
        
        System.out.println("表示回文数量的lps表：");
        for(int i=0;i<lps.length;i++){
        	for(int j=0;j<lps[i].length;j++){
        		System.out.print(lps[i][j]+" ");
        	}
        	System.out.println();
        }
              
    }
	
	public static int getLPSLength(int i,int j){
		if(j<i)
			return 0;
		if(j==i){
			return 1;
		}
		if (chars[i] == chars[j]) {
            lps[i][j] = getLPSLength(i+1,j-1) + 2;
            //System.out.println(chars[j]);
        } else {
        	//若两数不相等，则继续算之后的
            lps[i][j] = Math.max(getLPSLength(i+1,j),getLPSLength(i,j-1));
        }
		return lps[i][j];		
	}
	
	public static char[] getLPS(int subLength){
		sub = new char[subLength];//存放最长回文子序列
		int m=0,n=sub.length-1;//作为访问sub的索引，从两侧开始填回文进去
		
		int i=0,j=chars.length-1;//作为访问lps的索引，从两侧开始访问原字符串
		
		
		while(i<=j){//从两侧访问完成
			if(lps[i][j]==lps[i][j-1]){//表示在原字符数组中，坐标从（i,j-1）到（i,j）中没有回文
				j--;
			}
			else if(lps[i][j]==lps[i+1][j]){//表示在原字符数组中，坐标从（i,j）到（i+1,j）中没有回文
				i++;
			}
			else{//坐标i,j就是回文中的两个相同的字符
				//将字符写入回文串
				sub[m] = chars[i];
				sub[n] = chars[j];
				i++;j--;//坐标向中间移
				m++;n--;
				
			}
		}
		
		return sub;		
	}
      
    
   
     
}
