/**
 *FileName:binomialRecursion.java
 * @author:lmy
 *Creatdate:2018年12月18日下午8:37:31
 */
package Recursion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author lmy
 *
 */
public class binomialRecursion {

	/**
	 * 递归方法求二项式
	 * 
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月18日下午8:37:31
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static long count;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Writer file = new FileWriter("../diagramFile.csv");
		//file.write("数字,计算次数\n");
		String line;
		int n;
		int k;
		for(int i=1;i<=15;i++){
			count=0;
			k=i;
			n=30;
			int result = binomial_recursion(n,k);
			System.out.println("二项式C("+n+","+k+")的结果为："+result+"   "+"计算次数为："+count);
			//System.out.println(count);
			//line = Integer.toString(k)+","+Integer.toString(count)+"\n";
			//file.write(line);
		}
	}
	//如果第一个元素一定在结果中，那么就需要从剩下的n-1个元素中抓取k-1个元素C(n−1,k-1)
	//如果第一个元素不在结果中，就需要从剩下的n-1个元素中抓取k个元素C(n−1,k)
	public static int binomial_recursion(int n,int k){
		
		if(k==0||k==n){
			return 1;
		}
		else{
			count++;
			//考虑以上两种情况
			return binomial_recursion(n-1,k-1)+binomial_recursion(n-1,k);	
		}
		
	}
}
