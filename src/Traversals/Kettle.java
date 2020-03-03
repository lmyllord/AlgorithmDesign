/**
 *FileName:Kettle.java
 * @author:lmy
 *Creatdate:2018��12��24������9:04:01
 */
package Traversals;

/**
 * @author lmy
 *
 */
public class Kettle {
	private static int[] max= { 0, 0, 0 }; // �������
    public static int[][][] achievedState;//��¼�Ƿ��Ѿ��ﵽ����״̬
    private int[] kettles; //ˮ��

    //���ó�ʼˮ��
    public Kettle(int[] kettles) {
           this.kettles =kettles;
    }
    //����ˮ�����ˮ��
    public static void setMax(int[] max){
    	Kettle.max = max;
	}
    //�����Ѵ�״̬
	public static void setIsArrive(int[][][] achievedState){
		Kettle.achievedState = achievedState;
	}
	
	public int[] getKettles() {
        return kettles;
	}
	
	//���±�Ϊi��ˮ����ˮ���±�Ϊj��ˮ��
	public Kettle pureItoJ(int i,int j){
		
		achievedState[kettles[0]][kettles[1]][kettles[2]]= 1; //���õ�ˮǰ��״̬�Ѵ������״̬λ

        // ��ˮ
        int[] tempKettles= kettles.clone();
        //�ж��Ƿ���Խ��±�Ϊi��ˮ����ˮȫ�������±�Ϊj��ˮ����
        if (tempKettles[i] + tempKettles[j] > max[j]) {//����ȫ������
        	tempKettles[i]= tempKettles[i]+ tempKettles[j]-max[j];//iˮ��ʣ��
        	tempKettles[j]= max[j];//jˮ������
        }else {//����ȫ������
        	tempKettles[j]= tempKettles[i]+ tempKettles[j];//jˮ��Ϊ����ԭ����ˮ�����
        	tempKettles[i]= 0;//iˮ������
        }

        // �жϵ�ˮ���״̬�Ƿ��Ѿ��ﵽ��
        if (achievedState[tempKettles[0]][tempKettles[1]][tempKettles[2]]==1) { // �Ѿ��ﵽ��
               return null;
        }				
        /*for(int m=0;m<tempKettles.length;m++){
        	System.out.println(tempKettles[i]);
        }*/
        return new Kettle(tempKettles);
		
	}
}
