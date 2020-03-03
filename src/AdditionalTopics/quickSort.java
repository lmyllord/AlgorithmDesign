/**
 *FileName:quicjSort.java
 * @author:lmy
 *Creatdate:2018��12��25������2:18:44
 */
package AdditionalTopics;

import java.util.ArrayList;


/**
 * @author lmy
 *
 */
public class quickSort{

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018��12��25������2:18:44
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static ArrayList<ArrayList<Integer>> recordState = new ArrayList<ArrayList<Integer>>();
	static int[] array1=new int[]{97,49,38,65,76,27,13,22,34};
	static int[] array=new int[]{5,3,1,9,8,2,4,7};
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		showArray(array);
        quickSort(array, 0, array.length-1);
        showArray(array);
        
        System.out.println();
		for(int i=0;i<recordState.size();i++){
			ArrayList a = recordState.get(i);
        	for(int j=0;j<recordState.get(i).size();j++){
        		System.out.print(a.get(j)+"  ");
        	}
        	System.out.println();
        }
        
	}
 	public static void sort() {
		// TODO Auto-generated method stub
		
		
		showArray(array);
        quickSort(array, 0, array.length-1);
        //showArray(array);
        
        /*System.out.println();
		for(int i=0;i<recordState.size();i++){
			ArrayList a = recordState.get(i);
        	for(int j=0;j<recordState.get(i).size();j++){
        		System.out.print(a.get(j)+"  ");
        	}
        	System.out.println();
        }*/
        
	}
	
	public static void quickSort(int[] array,int low,int high){
		if(low<high){
			int s = hoarePartition(array,low,high);
			quickSort(array,low,s-1);
			quickSort(array,s+1,high);
		}
	}
	
	//��������array[low]���֣��������array[low]��λ��
	public static int hoarePartition(int[] array,int low,int high){
		int p = array[low];//���ֵ�Ԫ��
		int i=low,j=high;
		while(i<j){
			//�ȿ��ұߣ���������ݼ�
            while (p<=array[j]&&i<j) {
                j--;
            }
            //�ٿ���ߣ��������ҵ���
            while (p>=array[i]&&i<j) {
                i++;
            }
            //������������򽻻�
            if (i<j) {
            	swap(array,i,j);
            }
		}
		//�������һ��
		swap(array,i,j);
		showArray(array);
		swap(array,low,j);
		showArray(array);
		return j;
	}
	
	public static void swap(int[] array,int i,int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	private static void showArray(int[] array){
		ArrayList<Integer> a = new ArrayList();
		for(int i=0;i<array.length;i++){
            System.out.print(array[i]+"  ");
            a.add(array[i]);
        }
		System.out.println();
		recordState.add(a);
	}
    
	public static ArrayList<ArrayList<Integer>> getHistoryState(){
		return recordState;
	}

}
