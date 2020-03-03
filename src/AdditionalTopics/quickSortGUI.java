/**
 *FileName:quickSortGUI.java
 * @author:lmy
 *Creatdate:2018年12月25日下午3:03:35
 */
package AdditionalTopics;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author lmy
 *
 */
public class quickSortGUI extends Application{

	/**
	 *Title:main 
	 * @author:lmy
	 *Creatdate:2018年12月25日下午3:03:35
	 *@praram:@param args
	 *return:void
	 *@throws
	 */
	BorderPane pane = new BorderPane();
	GridPane gridpane = new GridPane();
	VBox vBox = new VBox();
	int count=0;
	ArrayList<ArrayList<Integer>> recordState;
	ArrayList<ArrayList<Integer>> recordState1;
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage Application) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(gridpane,600,500);
	    Application.setTitle("快速排序演示"); 	// 标题
	    Application.setScene(scene); 		// 场景
	    Application.show(); 				// 展示
	    
	    final Text text = new Text("以下演示快速排序");
		final Button nextBt = new Button("下一步");
		gridpane.add(text, 2, 0);
		gridpane.add(nextBt, 2, 1);
		vBox.setSpacing(20);
	    
		quickSort.sort();
		recordState = quickSort.getHistoryState();//得到快速排序的所有状态
		recordState1 = getUnRepetitive();//得到其中不重复的
		
		System.out.println();
		for(int i=0;i<recordState1.size();i++){
			ArrayList a = recordState1.get(i);
        	for(int j=0;j<recordState1.get(i).size();j++){
        		System.out.print(a.get(j)+"  ");
        	}
        	System.out.println();
        }

		gridpane.setPadding(new Insets(5));
	    gridpane.setHgap(5);
	    gridpane.setVgap(5);
	    gridpane.setPadding(new Insets(10, 20, 10, 20));
	    gridpane.add(vBox, 5, 2);
	    
	    nextBt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	if(count>=recordState1.size()){
            		alert.setContentText("排序已经完成！");
                    alert.show();          		
            	}
            	else{
            		count++;
	        		draw(count);
	        		
            	}
            	System.out.println(count);        		
            }
        });
				
	}
	
	public void draw(int n){
		if(n<=recordState1.size()){
			vBox.getChildren().clear();
			for(int i=0;i<n;i++){
				ArrayList a = recordState1.get(i);
				HBox hBox = new HBox();
				hBox.setSpacing(15);
	        	for(int j=0;j<a.size();j++){
        			Button button = new Button();
            		button.setStyle("-fx-border-color: inherit");
            		button.setText(String.valueOf(a.get(j)));
            		hBox.getChildren().add(button);
	        	}
	        	vBox.getChildren().add(hBox);
        		//gridpane.add(hBox, 5, n);
	        }
		}
		
		
	}
	
	//得到不重复的序列
	public ArrayList<ArrayList<Integer>> getUnRepetitive(){
		ArrayList<ArrayList<Integer>> x = new ArrayList();
		x.add(recordState.get(0));
		for(int i=1;i<recordState.size();i++){
			ArrayList a = recordState.get(i);
			int change = hadChanged(a,i);
			if(change==1){
				x.add(a);
			}
		}
		return x;
	}
	
	public int hadChanged(ArrayList a,int n){
		ArrayList b = recordState.get(n-1);
		for(int i=0;i<a.size();i++){
			if(!a.get(i).equals(b.get(i))){
				return 1;
			}
		}
		return 0;
	}

}

