/**
 *FileName:minimumCostShopping1.java
 * @author:lmy
 *Creatdate:2018��12��20������10:01:25
 */
package DynamicProgramming;

import java.util.Scanner;

/**
 * @author lmy
 *
 */
class Commodity{
    int piece;//��������
    int price;//����۸�
}
public class minimumCostShopping1 {

	
	private static int MAXCODE = 999;//��Ʒ��������ֵ
    private static int SALECOMB = 99;//�Ż���Ʒ�����
    private static int KIND = 5;     //��Ʒ����
    private static int QUANTITY = 5; //����ĳ����Ʒ���������ֵ

    private static int b;//������Ʒ������
    private static int s;//��ǰ�Ż������
    private static int[] num = new int[MAXCODE+1];//��¼��Ʒ��������Ʒ����Ķ�Ӧ��ϵ
    private static int[] product = new int[KIND+1];//��¼��ͬ������Ʒ�Ĺ�������
    private static int[][] offer = new int[SALECOMB+1][KIND+1];//offer[i][j]: ��Ʒ��ϵ��Żݼ�(j=0)��ĳ���Ż������ĳ����Ʒ��Ҫ���������(j>0)
    private static Commodity[] purch = new Commodity[KIND+1];//��¼��ͬ��Ʒ�Ĺ��������͹���۸�
    private static int[][][][][] cost = new int[QUANTITY+1][QUANTITY+1][QUANTITY+1][QUANTITY+1][QUANTITY+1];//��¼���ι�����ܻ���

    /**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018��12��20������10:01:25
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
   /* ���룺
    2
    1 3 2
    2 2 5
    2
    1 1 3 5
    2 1 1 2 2 10*/
    public static void main(String[] args){
        init();
        comp(1);
        out();

    }

    private static void minicost(){
        int i,j,k,m,n,p,minm;
        minm = 0;
        for(i=1; i<=b; i++)
            minm += product[i]*purch[i].price;

        for(p=1; p<=s; p++){
            i = product[1] - offer[p][1];
            j = product[2] - offer[p][2];
            k = product[3] - offer[p][3];
            m = product[4] - offer[p][4];
            n = product[5] - offer[p][5];
            if(i>=0 && j>=0 && k>=0 && m>=0 && n>=0 && cost[i][j][k][m][n]+offer[p][0] < minm)
                minm = cost[i][j][k][m][n] + offer[p][0];
        }
        cost[product[1]][product[2]][product[3]][product[4]][product[5]] = minm;
    }

    private static void init(){
        Scanner input = new Scanner(System.in);

        int i,j,n,p,t,code;
        for(i=0; i<100; i++)
            for(j=0; j<6; j++)
                offer[i][j] = 0;

        for(i=0; i<6; i++){
            purch[i] = new Commodity();
            purch[i].piece = 0;
            purch[i].price = 0;
            product[i] = 0;
        }
        
        System.out.println("��������Ʒ������");
        b = input.nextInt();
        for(i=1; i<=b; i++){
        	//System.out.println("�������"+i+"����Ʒ����Ʒ���룬���������������ۼ�");
            code = input.nextInt();
            purch[i].piece = input.nextInt();
            purch[i].price = input.nextInt();
            num[code] = i;
        }

        System.out.println("�������Ż�����������");
        s = input.nextInt();
        for(i=1; i<=s; i++){
        	//System.out.println("�������"+i+"���Żݵ��嵥��");
            t = input.nextInt();
            for(j=1; j<=t; j++){
                n = input.nextInt();
                p = input.nextInt();
                offer[i][num[n]] = p;
            }
            offer[i][0] = input.nextInt();
        }
        
        System.out.println("preferentialComb����");
        for(i=0;i<6;i++){
        	for(j=0;j<6;j++){
        		System.out.print(offer[i][j]+"\t");
        	}
        	System.out.println();
        }
    }

    private static void comp(int i){
        if(i > b){
            minicost();
            return;
        }

        for(int j=0; j<=purch[i].piece; j++){
            product[i] = j;
            comp(i+1);
        }
    }

    private static void out(){
        System.out.println(cost[product[1]][product[2]][product[3]][product[4]][product[5]]);
    }


}
