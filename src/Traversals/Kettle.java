/**
 *FileName:Kettle.java
 * @author:lmy
 *Creatdate:2018年12月24日下午9:04:01
 */
package Traversals;

/**
 * @author lmy
 *
 */
public class Kettle {
	private static int[] max= { 0, 0, 0 }; // 最大容量
    public static int[][][] achievedState;//记录是否已经达到过此状态
    private int[] kettles; //水壶

    //设置初始水量
    public Kettle(int[] kettles) {
           this.kettles =kettles;
    }
    //设置水壶最大水量
    public static void setMax(int[] max){
    	Kettle.max = max;
	}
    //设置已达状态
	public static void setIsArrive(int[][][] achievedState){
		Kettle.achievedState = achievedState;
	}
	
	public int[] getKettles() {
        return kettles;
	}
	
	//从下标为i的水壶倒水到下标为j的水壶
	public Kettle pureItoJ(int i,int j){
		
		achievedState[kettles[0]][kettles[1]][kettles[2]]= 1; //设置倒水前的状态已达，并更改状态位

        // 倒水
        int[] tempKettles= kettles.clone();
        //判断是否可以将下标为i的水壶的水全部倒到下标为j的水壶中
        if (tempKettles[i] + tempKettles[j] > max[j]) {//不能全部倒入
        	tempKettles[i]= tempKettles[i]+ tempKettles[j]-max[j];//i水壶剩余
        	tempKettles[j]= max[j];//j水壶倒满
        }else {//可以全部倒入
        	tempKettles[j]= tempKettles[i]+ tempKettles[j];//j水壶为两壶原本的水量相加
        	tempKettles[i]= 0;//i水壶倒空
        }

        // 判断倒水后的状态是否已经达到过
        if (achievedState[tempKettles[0]][tempKettles[1]][tempKettles[2]]==1) { // 已经达到过
               return null;
        }				
        /*for(int m=0;m<tempKettles.length;m++){
        	System.out.println(tempKettles[i]);
        }*/
        return new Kettle(tempKettles);
		
	}
}
