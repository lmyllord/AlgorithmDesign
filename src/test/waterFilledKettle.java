/**
 *FileName:waterFilledKettle.java
 * @author:lmy
 *Creatdate:2018��12��24������9:02:13
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
	 *Creatdate:2018��12��24������9:02:13
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static int[] kettle_max = new int[3];
	static int maxA=8,maxB=5,maxC=3;
	static int[] init = new int[3];
	static int[][][] achievedState= new int[maxA + 1][maxB+ 1][maxC + 1];//��¼�Ѵ�״̬
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		kettle_max[0]=maxA;kettle_max[1]=maxB;kettle_max[2]=maxC;//����ˮ���������ֵ
		init[0]=8;init[1]=0;init[2]=0;//����ˮ����ʼֵ
		
		achievedState[8][0][0]=1;
		
		Kettle.setMax(kettle_max);              
		Kettle.setIsArrive(achievedState);

		bfs();
	}
	
	public static void bfs(){
		//Stack<Kettle> stack= new Stack<Kettle>();// ����ջ������ʼ������ջ
		Queue<Kettle> queue=new LinkedList<Kettle>();//���ɶ���
		Queue<Kettle> queue1=new LinkedList<Kettle>();//���ɶ���
		Kettle top = new Kettle(init);

		queue.offer(top);

		System.out.println("ˮ���ĵ�ˮ����Ϊ��");
        System.out.println("��8��"+"\t"+"��5��"+"\t"+"��3��");
        
        while(!queue.isEmpty()){

        	Kettle kettle=queue.poll();//��õ�һ��Ԫ��
        	int[] m = kettle.getKettles();
        	for(int j=0;j<3;j++){
	    		System.out.print(" "+m[j]+"\t");
	    	}
	    	System.out.println();


            int[] kettles= kettle.getKettles();//�õ�ˮ������״̬
            if (kettles[0]== 4 || kettles[1] == 4 || kettles[2] == 4){// ������Ŀ��״̬���˳�
                break;
            }
            
            //�������е�ˮ���
            for(int i=0;i<3;i++){
            	for(int j=0;j<3;j++){
            		if (i != j) {
            			Kettle canPour = null;
            			canPour = kettle.pureItoJ(i, j);
            			
            			// ���ܳɹ���ˮ�����½ڵ���ջ������סһ���µ�״̬
                        if (canPour !=null) {
                        	//�жϴ�״̬�Ƿ������
                        	int hasState = 0;
                        	int[] a = canPour.getKettles();

                        	if(achievedState[a[0]][a[1]][a[2]]==0){//����������û�г���
                        		achievedState[a[0]][a[1]][a[2]]=1;//��¼״̬
                        		queue.offer(canPour);//���
                        		
                        	}
                        		
                        }
            		}
            	}
            }
            
        }
        
	}

}
