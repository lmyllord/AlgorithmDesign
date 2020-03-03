/**
 *FileName:fractalTree.java
 * @author:lmy
 *Creatdate:2018��12��18������8:51:31
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
	 *Creatdate:2018��12��18������8:51:31
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
	    Application.setTitle("�ݹ������"); 	// ����
	    Application.setScene(scene); 		// ����
	    Application.show(); 				// չʾ
	}
}

class fTree extends Pane {
	double PI = Math.PI;

	public fTree(){
		Line rootLine = new Line(400,580,400,400);
		rootLine.setStroke(Color.BROWN);
		getChildren().add(rootLine);
		drawLine(400,400,200,0);//��Ϊ����
	}
	
	private void drawLine(double x1,double y1,double length,double k){
		length = length * 0.65;//�������߶εĳ���
		double k1 = k-PI/4;//�����֦�Ƕ�
		double k2 = k+PI/5;//�ұ���֦�Ƕ�
		
		if(length<3)return;//��ֹ����
		
		//�����֦������
		double x2 = x1+length*Math.sin(k1);
		double y2 = y1-length*Math.cos(k1);
		//�ұ���֦������
		double x3 = x1+length*Math.sin(k2);
		double y3 = y1-length*Math.cos(k2);
		
		Line line1 = new Line(x1,y1,x2,y2);//�����֦
		Line line2 = new Line(x1,y1,x3,y3);//�ұ���֦
		
		//������ɫ
		line1.setStroke(Color.BROWN);//����Ϊ��ɫ
		line2.setStroke(Color.BROWN);
		if(length<100){
			line1.setStroke(Color.GREEN);//��֦��ɫ
			line2.setStroke(Color.GREEN);
		}
		if(length<10){
			line1.setStroke(Color.PINK);//��Ϊ��ɫ
			line2.setStroke(Color.PINK);
		}
				
		getChildren().addAll(line1,line2);
		
		//�ݹ����
		drawLine(x2,y2,length,k1);
		drawLine(x3,y3,length,k2);
	
	}
}