/**
 *FileName:waterFilledKettle.java
 * @author:lmy
 *Creatdate:2018年12月24日下午9:02:13
 */
package test;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;


/**
 * @author lmy
 *
 */
public class waterFilledKettle {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月24日下午9:02:13
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static int[] kettle_max = new int[3];
	static int maxA=8,maxB=5,maxC=3;
	static int[] init = new int[3];
	static int[][][] achievedState= new int[maxA + 1][maxB+ 1][maxC + 1];//记录已达状态
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		kettle_max[0]=maxA;kettle_max[1]=maxB;kettle_max[2]=maxC;//设置水壶容量最大值
		init[0]=8;init[1]=0;init[2]=0;//设置水壶初始值
		
		achievedState[8][0][0]=1;
		
		Kettle.setMax(kettle_max);              
		Kettle.setIsArrive(achievedState);

		bfs();
	}
	
	public static void bfs(){
		//Stack<Kettle> stack= new Stack<Kettle>();// 创建栈，将初始顶点入栈
		Queue<Kettle> queue=new LinkedList<Kettle>();//生成队列
		Queue<Kettle> queue1=new LinkedList<Kettle>();//生成队列
		Kettle top = new Kettle(init);

		queue.offer(top);

		System.out.println("水壶的倒水过程为：");
        System.out.println("【8】"+"\t"+"【5】"+"\t"+"【3】");
        
        while(!queue.isEmpty()){

        	Kettle kettle=queue.poll();//获得第一个元素
        	int[] m = kettle.getKettles();
        	for(int j=0;j<3;j++){
	    		System.out.print(" "+m[j]+"\t");
	    	}
	    	System.out.println();


            int[] kettles= kettle.getKettles();//得到水壶现在状态
            if (kettles[0]== 4 || kettles[1] == 4 || kettles[2] == 4){// 若到达目标状态，退出
                break;
            }
            
            //遍历所有倒水情况
            for(int i=0;i<3;i++){
            	for(int j=0;j<3;j++){
            		if (i != j) {
            			Kettle canPour = null;
            			canPour = kettle.pureItoJ(i, j);
            			
            			// 若能成功倒水，则将新节点入栈，即记住一个新的状态
                        if (canPour !=null) {
                        	//判断此状态是否遍历过
                        	int hasState = 0;
                        	int[] a = canPour.getKettles();

                        	if(achievedState[a[0]][a[1]][a[2]]==0){//如果这种情况没有出现
                        		achievedState[a[0]][a[1]][a[2]]=1;//记录状态
                        		queue.offer(canPour);//入队
                        		
                        	}
                        		
                        }
            		}
            	}
            }
            
        }
        
	}

}
