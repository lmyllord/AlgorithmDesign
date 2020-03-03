/**
 *FileName:binomialIteration.java
 * @author:lmy
 *Creatdate:2018��12��21������4:40:45
 */
package Recursion;

/**
 * @author lmy
 *
 */
public class binomialIteration {

	/**
	 * �������������ʽ
	 * 
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018��12��21������4:40:45
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
			System.out.println("����ʽC("+n+","+k+")�Ľ��Ϊ��"+result+"   "+"�������Ϊ��"+count);
			//System.out.println(count);
		}
	}
	

	public static int binomial_iteration(int n,int k){	
		int[][] arr = new int[n+1][k+1];
		//��ʼ������
		for(int i=1;i<arr.length;i++){
			arr[i][0]=1;
		}
		arr[1][1]=1;
		
		
		//����C(n,k)=C(n-1,k)+C(n-1,k-1)��������
		for(int i=2;i<=n;i++){
			for(int j=1;j<=k;j++){
				arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
				count++;
			}
		}
		
		
		return arr[n][k];
		
	}

}
