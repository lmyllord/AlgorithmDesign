/**
 *FileName:binomialIteration.java
 * @author:lmy
 *Creatdate:2018年12月21日下午4:40:45
 */
package Recursion;

/**
 * @author lmy
 *
 */
public class binomialIteration {

	/**
	 * 迭代方法求二项式
	 * 
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月21日下午4:40:45
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static int count;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		int k;
		for(int i=1;i<=15;i++){
			count=0;
			k=i;
			n=30;
			int result = binomial_iteration(n,k);
			System.out.println("二项式C("+n+","+k+")的结果为："+result+"   "+"计算次数为："+count);
			//System.out.println(count);
		}
	}
	

	public static int binomial_iteration(int n,int k){	
		int[][] arr = new int[n+1][k+1];
		//初始化数组
		for(int i=1;i<arr.length;i++){
			arr[i][0]=1;
		}
		arr[1][1]=1;
		
		
		//利用C(n,k)=C(n-1,k)+C(n-1,k-1)来填表计算
		for(int i=2;i<=n;i++){
			for(int j=1;j<=k;j++){
				arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
				count++;
			}
		}
		
		
		return arr[n][k];
		
	}

}
