/**
 *FileName:sudoku.java
 * @author:lmy
 *Creatdate:2018��12��19������10:06:02
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
	 *Creatdate:2018��12��19������10:06:02
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
		System.out.println("δ���ԭ����Ϊ��");
		printSudoku(sudoku);

		
		fillNumber(0, 0);

		
        if(!haveSolution){
        	System.out.println("�������޽�");
        }

        

	}
	
    private static void fillNumber(int i, int j) {
    	//����Ѿ����������һ����8,8��֮���ˣ��ͱ�ʾ�Ѿ�������
        if (i==8&&j==9) {
        	System.out.println("���������Ϊ��");
        	printSudoku(sudoku);
            //�Ѿ��ɹ��ˣ���ӡ���鼴��
        	haveSolution = true;
            return;
        }
        
        //�Ѿ�����ÿ��ĩβ���ͻ��в��к�����Ϊ0
        if (j == 9) {
        	j=0;
            i++;
        }
 
        //���i��j���ǿյ�
        if (sudoku[i][j]==0){
        	//����1-9�п��Է������
            for (int k=1; k<=9; k++) {
                //�жϸ�i��j�з�һ�����Ƿ����������
                if (canPut(i,j,k)) {
                    //����ֵ�����ÿո�Ȼ�������һ���ո�
                	sudoku[i][j] = k;
                	fillNumber(i,j+1);
                    //��ʼ���ÿո�,�Ա���������ʱ��������
                    sudoku[i][j] = 0;
                }
            }
        } 
        else{
            //�����λ���Ѿ���ֵ�ˣ��ͽ�����һ���ո���м���
        	fillNumber(i,j+1);
        }
    }
 
    
    private static boolean canPut(int i, int k, int number) {
        //�жϸ��и����Ƿ����ظ�����
        for (int n=0;n<9;n++) {
        	//�жϸ����Ƿ��й�����
            if (sudoku[i][n]==number){
                return false;
            }
            //�жϸ����Ƿ��й�����
            if(sudoku[n][k]==number){
                return false;
            }
        }
        //�ж�С�Ź����Ƿ����ظ�
        int row = i/3;//�õ�С�Ź����λ��
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
