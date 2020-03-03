/**
 *FileName:binomialMemorandum.java
 * @author:lmy
 *Creatdate:2018年12月18日下午7:39:45
 */
package Recursion;

/**
 * @author lmy
 *
 */

public class binomialMemorandum {

	/**
	 * 备忘录方法求二项式
	 * 
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月18日下午7:39:45
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	
	//static int[][] memorandu;
	static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n;
		int k;
		for(int i=1;i<=15;i++){
			count=0;
			k=i;
			n=30;
			int[][] memorandu = new int[n+1][k+1];
			int result = binomial_memorandum(n,k,memorandu);
			System.out.println("二项式C("+n+","+k+")的结果为："+result+"    "+"计算次数为："+count);
			//System.out.println(count);
		}
		
	}
	
	//如果第一个元素一定在结果中，那么就需要从剩下的n-1个元素中抓取k-1个元素C(n−1,k-1)
	//如果第一个元素不在结果中，就需要从剩下的n-1个元素中抓取k个元素C(n−1,k)
	public static int binomial_memorandum(int n,int k,int[][] memorandu){
		
		int value;
		if(k==0||k==n){
			return 1;
		}
		else if(memorandu[n][k]!=0){//查看备忘录，查看是否计算过，若计算过就不用计算了
			value = memorandu[n][k];
		}
		else{
			count++;
			value = binomial_memorandum(n-1,k-1,memorandu)+binomial_memorandum(n-1,k,memorandu);
		}
		
		memorandu[n][k] = value;
		
		return memorandu[n][k];
	}


}
