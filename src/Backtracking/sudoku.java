/**
 *FileName:sudoku.java
 * @author:lmy
 *Creatdate:2018年12月19日下午10:06:02
 */
package Backtracking;

/**
 * @author lmy
 *
 */
public class sudoku {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月19日下午10:06:02
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	private static int[][] sudoku;
	private static boolean haveSolution;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] sudoku2 = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};
		int[][] sudoku1 = {
                {0, 5, 0, 0, 6, 0, 0, 0, 0},
                {1, 2, 0, 8, 0, 0, 0, 0, 0},
                {0, 0, 9, 7, 0, 0, 6, 0, 0},
                {0, 7, 0, 0, 0, 3, 0, 0, 0},
                {5, 9, 0, 0, 0, 0, 0, 7, 2},
                {0, 0, 0, 4, 0, 0, 0, 5, 0},
                {0, 0, 3, 0, 0, 1, 5, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 1, 7},
                {0, 0, 0, 0, 2, 0, 0, 3, 0}};
		haveSolution = false;
		sudoku = sudoku1; 
		System.out.println("未解的原数独为：");
		printSudoku(sudoku);

		
		fillNumber(0, 0);

		
        if(!haveSolution){
        	System.out.println("此数独无解");
        }

        

	}
	
    private static void fillNumber(int i, int j) {
    	//如果已经遍历到最后一个（8,8）之后了，就表示已经填完了
        if (i==8&&j==9) {
        	System.out.println("解出的数独为：");
        	printSudoku(sudoku);
            //已经成功了，打印数组即可
        	haveSolution = true;
            return;
        }
        
        //已经到了每列末尾，就换行并列号设置为0
        if (j == 9) {
        	j=0;
            i++;
        }
 
        //如果i行j列是空的
        if (sudoku[i][j]==0){
        	//遍历1-9中可以放入的数
            for (int k=1; k<=9; k++) {
                //判断给i行j列放一个数是否能满足规则
                if (canPut(i,j,k)) {
                    //将该值赋给该空格，然后进入下一个空格
                	sudoku[i][j] = k;
                	fillNumber(i,j+1);
                    //初始化该空格,以备后续回溯时重新填数
                    sudoku[i][j] = 0;
                }
            }
        } 
        else{
            //如果该位置已经有值了，就进入下一个空格进行计算
        	fillNumber(i,j+1);
        }
    }
 
    
    private static boolean canPut(int i, int k, int number) {
        //判断该行该列是否有重复数字
        for (int n=0;n<9;n++) {
        	//判断该行是否有过此数
            if (sudoku[i][n]==number){
                return false;
            }
            //判断该列是否有过此数
            if(sudoku[n][k]==number){
                return false;
            }
        }
        //判断小九宫格是否有重复
        int row = i/3;//得到小九宫格的位置
        int column = k/3;
        for (int m=0;m<3;m++) {
            for (int n=0;n<3;n++) {
                if (sudoku[row*3+m][column*3+n]==number) {
                    return false;
                }
            }
        }
 
        return true;
    }
 
   
    public static void printSudoku(int[][] arr) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
