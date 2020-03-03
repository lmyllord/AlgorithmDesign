/**
 *FileName:twentyFourPoint.java
 * @author:lmy
 *Creatdate:2018年12月19日下午8:23:45
 */
package Traversals;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author lmy
 *
 */
public class twentyFourPoint {

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月19日下午8:23:45
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[] numbers = {6,5,7,2};
		int[] numbers = new int[4];
		//随机产生四个数
		int n=0;
		while(n<4){
			int hadOne = 0;
			int a = 1+new Random().nextInt(9);			
			for(int j=0;j<numbers.length;j++){
				if(a==numbers[j]){
					hadOne=1;
				}
			}
			if(hadOne==0){
				numbers[n]=a;
				n++;
			}
		}
		
		System.out.print("随机产生的四个数为：");
		for(int j=0;j<numbers.length;j++){
			System.out.print(numbers[j]+" ");
		}
		
		System.out.println();
		ArrayList<String> solutions = new ArrayList<String>();
		solutions = findSolutions(numbers);
		for(int i=0;i<solutions.size();i++){
			System.out.println(solutions.get(i));
		}
		
	}
	public static ArrayList<String> findSolutions(int[] numbers) {
        ArrayList<String> solutions = new ArrayList<String>();

        String noSolution = "No solution";
        String solution;
        String[] operators = {"+", "-", "*", "/"};
        
        int n=0;
        int[][] allCombinations = new int[24][4];
        //共三个循环，且循环总次数为24
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){               
                for(int k=0;k<4;k++){
                	allCombinations[n][0] = numbers[k%4];
                	allCombinations[n][1] = numbers[(k+j%3+1)%4];
                	allCombinations[n][2] = numbers[(k+(j+i%2+1)%3+1)%4];
                	allCombinations[n][3] = numbers[(k+(j+(i+1)%2+1)%3+1)%4];
                	
                	n++;
                }
            }
        }


        for (String firstOp : operators){
            for (String secondOp : operators){
                for (String thirdOp : operators){
                    for (int[] num : allCombinations){
                    	for(int i=0;i<4;i++){//四种括号的组成
                    		if(i==0){
                    			solution = num[0] + firstOp
                                        + num[1] + secondOp
                                        + num[2] + thirdOp
                                        + num[3];
                                if (verificationResult.evaluateExpression(solution) == 24)
                                    solutions.add(solution);
                    		}
                    		else if(i==1){
                    			solution = "(" + num[0] + firstOp
                                        + num[1] + ")" + secondOp
                                        + "(" +num[2] + thirdOp
                                        + num[3] + ")";
                                if (verificationResult.evaluateExpression(solution) == 24)
                                    solutions.add(solution);
                    		}
                    		else if(i==2){
                    			solution = "(" + "(" + num[0] + firstOp
                                        + num[1] + ")" + secondOp
                                         +num[2] + ")"+ thirdOp
                                        + num[3] ;
                                if (verificationResult.evaluateExpression(solution) == 24)
                                    solutions.add(solution);
                    		}
                    		else if(i==3){
                    			solution = "(" + num[0] + firstOp
                    					+ "("+ num[1]  + secondOp
                                         +num[2] + ")"+ ")"+ thirdOp
                                        + num[3] ;
                                if (verificationResult.evaluateExpression(solution) == 24)
                                    solutions.add(solution);
                    		}
                    	}
                    }
                }
            }
        }
                    	

        if(solutions.size()==0){
            solutions.add(noSolution);
        }
        return solutions;
    }
}

/*for (int i = 0; i < 3; i++)//括号括住的数
for (int j = 0; j < 5; j++) {//括号组合
    if (i == 0) {
        if (j == 0) {
            solution = num[0] + firstOp
                    + num[1] + secondOp
                    + num[2] + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);

        }
        else if (j == 1) {
            solution = "(" + num[0] + firstOp
                    + num[1] + ")" + secondOp
                    + num[2] + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 2) {
            solution = num[0] + firstOp + "("
                    + num[1] + secondOp
                    + num[2] + ")" + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 3) {
            solution = num[0] + firstOp
                    + num[1] + secondOp + "("
                    + num[2] + thirdOp
                    + num[3] + ")";
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 4) {
            solution = "(" + num[0] + firstOp + num[1] + ")" + secondOp + "(" + num[2] + thirdOp + num[3] + ")";
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
    }
    else if (i == 1) {
        if (j == 0) {
            solution = "(" + num[0] + firstOp
                    + num[1] + secondOp
                    + num[2] + ")" + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 1) {
            solution = "((" + num[0] + firstOp
                    + num[1] + ")" + secondOp
                    + num[2] + ")" + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 2) {
            solution = "(" + num[0] + firstOp
                    + "(" + num[1] + secondOp
                    + num[2] + "))" + thirdOp
                    + num[3];
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
    }
    else if (i == 2) {
        if (j == 0) {
            solution = num[0] + firstOp + "("
                    + num[1] + secondOp
                    + num[2] + thirdOp
                    + num[3] + ")";
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 1) {
            solution = num[0] + firstOp + "(("
                    + num[1] + secondOp
                    + num[2] + ")" + thirdOp
                    + num[3] + ")";
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
        else if (j == 2) {
            solution = num[0] + firstOp + "("
                    + num[1] + secondOp + "("
                    + num[2] + thirdOp
                    + num[3] + "))";
            if (verificationResult.evaluateExpression(solution) == 24)
                solutions.add(solution);
        }
    }
}*/
