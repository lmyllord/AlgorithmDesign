/**
 *FileName:fractalTree1.java
 * @author:lmy
 *Creatdate:2018年12月20日下午8:31:56
 */
package Recursion;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author lmy
 *
 */
public class fractalTree1 extends JFrame{
	//直接新建一个数
		private Tree t = new Tree();
		private JButton creaseButton = new JButton("增加树枝");
		public fractalTree1(){
			this.add(t);
			JPanel panel = new JPanel();
			panel.add(creaseButton);
			this.add(panel,BorderLayout.SOUTH);
			creaseButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					t.run();
				}
			});
		}
		public static void main(String[] args) {
			fractalTree1 t = new fractalTree1();
			t.setTitle("递归分形树");
			t.setSize(1300,1000);
			t.setLocationRelativeTo(null);
			t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			t.setVisible(true);
		}
	}



	class Tree extends JPanel{
		private int n = 0;
		private double A,B,C;
		private double PI = Math.acos(-1.0);
		protected void paintComponent(Graphics graphics){
			super.paintComponent(graphics);
			setBackground(Color.WHITE);
			graphics.setColor(Color.darkGray);
			Point p1 = new Point(this.getWidth()/2,this.getHeight()-20);
			Point p2 = new Point(this.getWidth()/2,700);
			drawLine(graphics,n,p1,p2);
			if(n<6){
				//n++;
				run();
			}
			
		}
		
		public void run(){
			n++;
			if(n>12){
				String title = "Message";
			    String content = "树枝过多，无法增加";
				JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//重画，更新面板，每次调用paintComponent()
			repaint();
			//paintComponent(graphics);
		}
		private void drawLine(Graphics graphics,int n,Point p1,Point p2){
			if (n>=0){
				//变颜色时会全树一起变？
				//if(n>8)graphics.setColor(Color.red);
				graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
				Point p3 = mid1(p1,p2);
				Point p4 = mid2(p1,p2);
				drawLine(graphics, n-1, p2, p3);
				drawLine(graphics, n-1, p2, p4);			
			}
			
		}
		//求出分支后的一个顶点坐标
		private Point mid1(Point p1,Point p2){
			Point p = new Point();
			//使用Math.atan出现问题
			A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
			//A = Math.atan((double)(p2.x-p1.x)/(p1.y-p2.y));
			//夹角大小
			B = A - PI/4;
			//求长度
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/4;
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))-C;
			p.x= (int)(p2.x + C*Math.sin(B));
			p.y= (int)(p2.y - C*Math.cos(B));
			return p;
		}
		//求出分支后的另一个顶点坐标
		private Point mid2(Point p1,Point p2){
			Point p = new Point();
			A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
			//角度设置成与另一分支不同，使树不完全对称
			B = A + PI/5;
			//长度设置成与另一分支不同，使树不完全对称
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/5;
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))-C;
			p.x= (int)(p2.x + C*Math.sin(B));
			p.y= (int)(p2.y - C*Math.cos(B));
			
			return p;
		}
	}
