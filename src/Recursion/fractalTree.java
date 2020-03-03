/**
 *FileName:fractalTree.java
 * @author:lmy
 *Creatdate:2018年12月18日下午8:51:31
 */
package Recursion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


/**
 * @author lmy
 *
 */

public class fractalTree extends Application{
	
	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月18日下午8:51:31
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage Application) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();	
		
		Scene scene = new Scene(new fTree(),800,600);
	    Application.setTitle("递归分形树"); 	// 标题
	    Application.setScene(scene); 		// 场景
	    Application.show(); 				// 展示
	}
}

class fTree extends Pane {
	double PI = Math.PI;

	public fTree(){
		Line rootLine = new Line(400,580,400,400);
		rootLine.setStroke(Color.BROWN);
		getChildren().add(rootLine);
		drawLine(400,400,200,0);//此为树干
	}
	
	private void drawLine(double x1,double y1,double length,double k){
		length = length * 0.65;//定义新线段的长度
		double k1 = k-PI/4;//左边树枝角度
		double k2 = k+PI/5;//右边树枝角度
		
		if(length<3)return;//终止条件
		
		//左边树枝的坐标
		double x2 = x1+length*Math.sin(k1);
		double y2 = y1-length*Math.cos(k1);
		//右边树枝的坐标
		double x3 = x1+length*Math.sin(k2);
		double y3 = y1-length*Math.cos(k2);
		
		Line line1 = new Line(x1,y1,x2,y2);//左边树枝
		Line line2 = new Line(x1,y1,x3,y3);//右边树枝
		
		//设置颜色
		line1.setStroke(Color.BROWN);//树干为棕色
		line2.setStroke(Color.BROWN);
		if(length<100){
			line1.setStroke(Color.GREEN);//树枝绿色
			line2.setStroke(Color.GREEN);
		}
		if(length<10){
			line1.setStroke(Color.PINK);//花为粉色
			line2.setStroke(Color.PINK);
		}
				
		getChildren().addAll(line1,line2);
		
		//递归调用
		drawLine(x2,y2,length,k1);
		drawLine(x3,y3,length,k2);
	
	}
}