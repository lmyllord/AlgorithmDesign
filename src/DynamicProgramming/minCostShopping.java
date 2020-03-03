/**
 *FileName:minCostShopping.java
 * @author:lmy
 *Creatdate:2018年12月21日下午9:11:05
 */
package DynamicProgramming;

import java.util.Scanner;

/**
 * @author lmy
 *
 */

//为减少计算，规定商品编码不超过5，购买每种商品数量不超过5
public class minCostShopping {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月21日下午9:11:05
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static int maxCode = 5;//商品种类最大值
	static int maxComb = 5;//优惠种类最大值
	
	static int discountNum;//当前优惠种类数
	static int speciesNum;//当前购买商品种数
	
	static int[] num = new int[maxCode+1];//记录购买的商品与商品编号的关系
	static int[][] preferentialComb = new int[maxComb+1][maxCode+1];//优惠组合
	static int[] product = new int[maxCode+1];//记录不同种类商品的购买数量
	static Goods[] buyGoods = new Goods[maxCode+1];//记录不同商品的购买数量和购买价格
	static int[][][][][] minCost = new int[maxCode+1][maxCode+1][maxCode+1][maxCode+1][maxCode+1];//记录本次购买的总花费
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		
		//输入商品种类数，以及商品编码，购买数量，正常售价
		System.out.println("请输入商品种数：");
		speciesNum = input.nextInt();		
        for(int i=1; i<=speciesNum; i++){
        	System.out.println("请输入第"+i+"种商品的商品编码，购买数量，正常售价");
            int code = input.nextInt();
            buyGoods[i] = new Goods();
            buyGoods[i].quantity = input.nextInt();
            buyGoods[i].price = input.nextInt();
            num[code] = i;
        }
        
        //记录优惠
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
        
        System.out.println("preferentialComb数组");
        for(int i=0;i<preferentialComb.length;i++){
        	for(int j=0;j<preferentialComb[i].length;j++){
        		System.out.print(preferentialComb[i][j]+"\t");
        	}
        	System.out.println();
        }
        
        getCost(1);//从编号为1的商品开始计算
        
        System.out.println("product数组");
        for(int i=0;i<product.length;i++){
        	System.out.print(product[i]+"\t");    	
        }
        System.out.println();
        System.out.println("最终付款金额为："+minCost[product[1]][product[2]][product[3]][product[4]][product[5]]);
        
        
	}
	private static void getCost(int i){
		
        if(i > speciesNum){
            getMinicost();
            return;
        }
        //每次每种商品只加1个进去计算
        for(int j=0; j<=buyGoods[i].quantity; j++){//每次增加编号为i的商品的一个
            product[i] = j;
            getCost(i+1);
        }       
    }
	
	private static void getMinicost(){
        int buy1,buy2,buy3,buy4,buy5;//记录商品使用优惠后剩下的数量
        int minMoney = 0;
        //计算不适用优惠的价格
        for(int i=1; i<=speciesNum; i++)
        	minMoney += product[i]*buyGoods[i].price;

        for(int i=1; i<=discountNum; i++){
        	//筛选优惠
        	buy1 = product[1] - preferentialComb[i][1];
        	buy2 = product[2] - preferentialComb[i][2];
        	buy3 = product[3] - preferentialComb[i][3];
        	buy4 = product[4] - preferentialComb[i][4];
        	buy5 = product[5] - preferentialComb[i][5];
        	
        	//若商品数量达到优惠规定数量，且使用优惠后价格变低，就使用该优惠
            if(buy1>=0 && buy2>=0 && buy3>=0 && buy4>=0 && buy5>=0 
            	&& minCost[buy1][buy2][buy3][buy4][buy5]+preferentialComb[i][0] < minMoney){
            	//优惠价格等于部分商品使用了该优惠的价格加上未使用优惠的商品的最低价
            	minMoney = minCost[buy1][buy2][buy3][buy4][buy5] + preferentialComb[i][0];
            }
        }
        minCost[product[1]][product[2]][product[3]][product[4]][product[5]] = minMoney;//记录下当前物品搭配最便宜的金额
        
        System.out.println("此时product数组：");
        for(int i=0;i<product.length;i++){
        	System.out.print(product[i]+"\t");    	
        }
        System.out.println("当前最少花费："+minCost[product[1]][product[2]][product[3]][product[4]][product[5]]);
    }


}

class Goods{
	int quantity;//购买数量
    int price;//购买价格
}
