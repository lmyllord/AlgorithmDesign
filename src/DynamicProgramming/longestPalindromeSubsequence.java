/**
 *FileName:longestPalindromeSubsequence.java
 * @author:lmy
 *Creatdate:2018��12��21������1:46:48
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
	 *Creatdate:2018��12��21������1:46:48
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	
	static char[] chars;//���s��ֺ��
	static char[] sub;//��Ż���������

	static int[][] lps;//lps[i][j]��ʾ���д��±�i��j֮������������
	
	static String s = "bcddxca";
	public static void main(String[] args) {
		
		System.out.println("ԭ�ַ���Ϊ��"+s);
		System.out.println();
        chars = s.toCharArray();//��s��ֿ������ڼ���
        int length = chars.length;
        lps = new int[length][length];
        for (int i = 0; i < length; i++) {
            lps[i][i] = 1; // �����ַ�������������г���Ϊ1
        }

        int lspLength = getLPSLength(0,length-1);
        System.out.println("��������еĳ���Ϊ��"+lspLength);
        System.out.println();     
        char[] subResult = getLPS(lspLength);
        System.out.print("���������Ϊ��");
        for(int i=0;i<subResult.length;i++){
        	System.out.print(subResult[i]+" ");
        }
        System.out.println();System.out.println();
        
        System.out.println("��ʾ����������lps��");
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
        	//����������ȣ��������֮���
            lps[i][j] = Math.max(getLPSLength(i+1,j),getLPSLength(i,j-1));
        }
		return lps[i][j];		
	}
	
	public static char[] getLPS(int subLength){
		sub = new char[subLength];//��������������
		int m=0,n=sub.length-1;//��Ϊ����sub�������������࿪ʼ����Ľ�ȥ
		
		int i=0,j=chars.length-1;//��Ϊ����lps�������������࿪ʼ����ԭ�ַ���
		
		
		while(i<=j){//������������
			if(lps[i][j]==lps[i][j-1]){//��ʾ��ԭ�ַ������У�����ӣ�i,j-1������i,j����û�л���
				j--;
			}
			else if(lps[i][j]==lps[i+1][j]){//��ʾ��ԭ�ַ������У�����ӣ�i,j������i+1,j����û�л���
				i++;
			}
			else{//����i,j���ǻ����е�������ͬ���ַ�
				//���ַ�д����Ĵ�
				sub[m] = chars[i];
				sub[n] = chars[j];
				i++;j--;//�������м���
				m++;n--;
				
			}
		}
		
		return sub;		
	}
      
    
   
     
}
