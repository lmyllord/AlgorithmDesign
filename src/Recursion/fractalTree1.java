/**
 *FileName:fractalTree1.java
 * @author:lmy
 *Creatdate:2018��12��20������8:31:56
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
	//ֱ���½�һ����
		private Tree t = new Tree();
		private JButton creaseButton = new JButton("������֦");
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
			t.setTitle("�ݹ������");
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
			    String content = "��֦���࣬�޷�����";
				JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//�ػ���������壬ÿ�ε���paintComponent()
			repaint();
			//paintComponent(graphics);
		}
		private void drawLine(Graphics graphics,int n,Point p1,Point p2){
			if (n>=0){
				//����ɫʱ��ȫ��һ��䣿
				//if(n>8)graphics.setColor(Color.red);
				graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
				Point p3 = mid1(p1,p2);
				Point p4 = mid2(p1,p2);
				drawLine(graphics, n-1, p2, p3);
				drawLine(graphics, n-1, p2, p4);			
			}
			
		}
		//�����֧���һ����������
		private Point mid1(Point p1,Point p2){
			Point p = new Point();
			//ʹ��Math.atan��������
			A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
			//A = Math.atan((double)(p2.x-p1.x)/(p1.y-p2.y));
			//�нǴ�С
			B = A - PI/4;
			//�󳤶�
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/4;
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))-C;
			p.x= (int)(p2.x + C*Math.sin(B));
			p.y= (int)(p2.y - C*Math.cos(B));
			return p;
		}
		//�����֧�����һ����������
		private Point mid2(Point p1,Point p2){
			Point p = new Point();
			A = Math.atan2(p2.x-p1.x,p1.y-p2.y);
			//�Ƕ����ó�����һ��֧��ͬ��ʹ������ȫ�Գ�
			B = A + PI/5;
			//�������ó�����һ��֧��ͬ��ʹ������ȫ�Գ�
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))/5;
			C = Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y))-C;
			p.x= (int)(p2.x + C*Math.sin(B));
			p.y= (int)(p2.y - C*Math.cos(B));
			
			return p;
		}
	}
