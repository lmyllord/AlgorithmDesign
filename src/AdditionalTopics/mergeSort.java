/**
 *FileName:mergeSortGUI.java
 * @author:lmy
 *Creatdate:2018年12月25日上午10:00:21
 */
package AdditionalTopics;

import java.util.ArrayList;

/**
 * @author lmy
 *
 */
public class mergeSort {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月25日上午10:00:21
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	static ArrayList<ArrayList<Integer>> recordState = new ArrayList<ArrayList<Integer>>();
	static int[] array1=new int[]{97,49,38,65,76,27,13,22,34};
	static int[] array=new int[]{8,3,2,9,7,1,5,4};
	static int count = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		showArray(array);
		mergeSort(array, 0, array.length-1);
        //showArray(array);
		System.out.print(count);
		
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
		mergeSort(array, 0, array.length-1);
               
	}
	
	public static int[] mergeSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
        	mergeSort(nums, low, mid);
            // 右边
        	mergeSort(nums, mid + 1, high);
            // 左右归并
        	merge(nums, low, mid, high);
            showArray(nums);
        }
        return nums;
    }

    /**
     * 将数组中low到high位置的数进行排序
     * @param nums 待排序数组
     * @param low 待排的开始位置
     * @param mid 待排中间位置
     * @param high 待排结束位置
     */
    public static void merge(int[] nums, int low, int mid, int high) {
    	count++;
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int q = 0; q < temp.length; q++) {
            nums[low+q] = temp[q];
        }

        
    }
    
    private static void showArray(int[] arr){
    	ArrayList<Integer> a = new ArrayList();
		for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
            a.add(arr[i]);
        }
		System.out.println();
		recordState.add(a);
	}
    public static ArrayList<ArrayList<Integer>> getHistoryState(){
		return recordState;
	}
}
