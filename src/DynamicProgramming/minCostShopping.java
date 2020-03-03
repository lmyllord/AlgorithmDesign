/**
 *FileName:minCostShopping.java
 * @author:lmy
 *Creatdate:2018��12��21������9:11:05
 */
package DynamicProgramming;

import java.util.Scanner;

/**
 * @author lmy
 *
 */

//Ϊ���ټ��㣬�涨��Ʒ���벻����5������ÿ����Ʒ����������5
public class minCostShopping {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018��12��21������9:11:05
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static int maxCode = 5;//��Ʒ�������ֵ
	static int maxComb = 5;//�Ż��������ֵ
	
	static int discountNum;//��ǰ�Ż�������
	static int speciesNum;//��ǰ������Ʒ����
	
	static int[] num = new int[maxCode+1];//��¼�������Ʒ����Ʒ��ŵĹ�ϵ
	static int[][] preferentialComb = new int[maxComb+1][maxCode+1];//�Ż����
	static int[] product = new int[maxCode+1];//��¼��ͬ������Ʒ�Ĺ�������
	static Goods[] buyGoods = new Goods[maxCode+1];//��¼��ͬ��Ʒ�Ĺ��������͹���۸�
	static int[][][][][] minCost = new int[maxCode+1][maxCode+1][maxCode+1][maxCode+1][maxCode+1];//��¼���ι�����ܻ���
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		
		//������Ʒ���������Լ���Ʒ���룬���������������ۼ�
		System.out.println("��������Ʒ������");
		speciesNum = input.nextInt();		
        for(int i=1; i<=speciesNum; i++){
        	System.out.println("�������"+i+"����Ʒ����Ʒ���룬���������������ۼ�");
            int code = input.nextInt();
            buyGoods[i] = new Goods();
            buyGoods[i].quantity = input.nextInt();
            buyGoods[i].price = input.nextInt();
            num[code] = i;
        }
        
        //��¼�Ż�
        discountNum = 2;
        int[][] discount = {{1,1,3,5},{2,1,1,2,2,10}};
        for(int i=1; i<=discount.length; i++){
            int t = discount[i-1][0];
            for(int j=1; j<=t; j++){
                int n = discount[i-1][j*2-1];
                int p = discount[i-1][j*2];
                preferentialComb[i][num[n]] = p;
            }
            preferentialComb[i][0] = discount[i-1][discount[i-1].length-1];
        }
        
        System.out.println("preferentialComb����");
        for(int i=0;i<preferentialComb.length;i++){
        	for(int j=0;j<preferentialComb[i].length;j++){
        		System.out.print(preferentialComb[i][j]+"\t");
        	}
        	System.out.println();
        }
        
        getCost(1);//�ӱ��Ϊ1����Ʒ��ʼ����
        
        System.out.println("product����");
        for(int i=0;i<product.length;i++){
        	System.out.print(product[i]+"\t");    	
        }
        System.out.println();
        System.out.println("���ո�����Ϊ��"+minCost[product[1]][product[2]][product[3]][product[4]][product[5]]);
        
        
	}
	private static void getCost(int i){
		
        if(i > speciesNum){
            getMinicost();
            return;
        }
        //ÿ��ÿ����Ʒֻ��1����ȥ����
        for(int j=0; j<=buyGoods[i].quantity; j++){//ÿ�����ӱ��Ϊi����Ʒ��һ��
            product[i] = j;
            getCost(i+1);
        }       
    }
	
	private static void getMinicost(){
        int buy1,buy2,buy3,buy4,buy5;//��¼��Ʒʹ���Żݺ�ʣ�µ�����
        int minMoney = 0;
        //���㲻�����Żݵļ۸�
        for(int i=1; i<=speciesNum; i++)
        	minMoney += product[i]*buyGoods[i].price;

        for(int i=1; i<=discountNum; i++){
        	//ɸѡ�Ż�
        	buy1 = product[1] - preferentialComb[i][1];
        	buy2 = product[2] - preferentialComb[i][2];
        	buy3 = product[3] - preferentialComb[i][3];
        	buy4 = product[4] - preferentialComb[i][4];
        	buy5 = product[5] - preferentialComb[i][5];
        	
        	//����Ʒ�����ﵽ�Żݹ涨��������ʹ���Żݺ�۸��ͣ���ʹ�ø��Ż�
            if(buy1>=0 && buy2>=0 && buy3>=0 && buy4>=0 && buy5>=0 
            	&& minCost[buy1][buy2][buy3][buy4][buy5]+preferentialComb[i][0] < minMoney){
            	//�Żݼ۸���ڲ�����Ʒʹ���˸��Żݵļ۸����δʹ���Żݵ���Ʒ����ͼ�
            	minMoney = minCost[buy1][buy2][buy3][buy4][buy5] + preferentialComb[i][0];
            }
        }
        minCost[product[1]][product[2]][product[3]][product[4]][product[5]] = minMoney;//��¼�µ�ǰ��Ʒ��������˵Ľ��
        
        System.out.println("��ʱproduct���飺");
        for(int i=0;i<product.length;i++){
        	System.out.print(product[i]+"\t");    	
        }
        System.out.println("��ǰ���ٻ��ѣ�"+minCost[product[1]][product[2]][product[3]][product[4]][product[5]]);
    }


}

class Goods{
	int quantity;//��������
    int price;//����۸�
}
