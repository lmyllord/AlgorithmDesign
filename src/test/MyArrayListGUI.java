package test;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyArrayListGUI extends Application {

    static MyArrayList<Integer> list = new MyArrayList<Integer>();

    Button btSearch = new Button("Search");
    Button btInsert = new Button("Insert");
    Button btDelete = new Button("Delete");
    Button btTrimToSize = new Button("TrimToSize");
    static TextField inputValue = new TextField();
    static TextField inputIndex = new TextField();

    static Alert alert = new Alert(Alert.AlertType.INFORMATION);

    BorderPane pane = new BorderPane();
    static GridPane pane1 = new GridPane();//存放arraylist的各个元素即按钮
    static HBox hBox = new HBox();

    static int value = 0;
    static int index = 0;
    static Button[] btList;
    //Rectangle[] reList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text text1 = new Text("Enter a value:");

        Text text2 = new Text("Enter a index:");

        pane1.setAlignment(Pos.CENTER);
        //Pane pane1 = new Pane();


        //存放底部输入框及按钮
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(text1, inputValue, text2, inputIndex, btSearch, btInsert, btDelete, btTrimToSize);

        //用按钮做
        btList = new Button[list.getCapacitysize()];
        //用长方形做，后来发现按钮更有实用性
        /*reList = new Rectangle[capacity];*/
        for (int i = 0; i < list.getCapacitysize(); i++) {
            btList[i] = new Button("/");
            pane1.add(btList[i], i, 2);
            /*reList[i] = new Rectangle(30*(i+1),0,30,20);
            reList[i].setFill(Color.WHITE);
            reList[i].setStroke(Color.BLACK);
            pane1.getChildren().add(reList[i]);
            pane1.getChildren().add(new Line(reList[i].getX()+reList[i].getWidth(),reList[i].getY(),reList[i].getX(),reList[i].getY()+reList[i].getHeight()));*/
        }

        Text text3 = new Text("             size =  " + list.size + "        capacity =  " + list.getCapacitysize());
        Text text4 = new Text("1.请输入一个下标或者一个值来查询(如果同时输入，将按下标为准)。");
        Text text5 = new Text("2.请输入一个下标和一个值来插入（当输入下标不合理时，直接插入末尾）。");
        Text text6 = new Text("3.请输入一个下标或点击按钮来删除(如果同时输入，将按下标为准)。");
        Text text7 = new Text("4.点击TrimToSize按钮会删除多余空间。");
        Text text9 = new Text("——————————————————————————————————————-————");

        //存放顶部提示语句
        VBox vbox1 = new VBox();
        vbox1.setSpacing(5);
        vbox1.getChildren().addAll(text4, text5, text6, text7, text9);

        hBox.getChildren().addAll(text3, pane1);

        btInsert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                insert();
            }
        });


        btSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	search();
            }
        });

        btDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	delete();
            }
        });

        btTrimToSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            trimToSize();
            }
        });

        //按钮点击事件做不了
        /*for(int i=0;i<list.size();i++){
            int finalI1 = i;
            btList[i].setOnAction(e->{
                int finalI = finalI1;
                inputValue.setText(btList[finalI].getText());
                inputIndex.setText(String.valueOf(finalI));
            });
        }*/

        pane.setTop(vbox1);
        pane.setBottom(hBox1);
        pane.setCenter(hBox);

        Scene scene = new Scene(pane, 800, 300);
        primaryStage.setTitle("MyArrayListGUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //插入方法
    public static void insert() {
        //判断是否输入为空，需要两个输入框都不为空
        if (inputValue.getText() == null || inputValue.getText().isEmpty()
                || inputIndex.getText() == null || inputIndex.getText().isEmpty()) {
            alert.setContentText("Input error!Input can not be empty.");
            alert.show();
        }
        else {
            //判断是否输入为数字
            try {
                value = Integer.parseInt(inputValue.getText());
                index = Integer.parseInt(inputIndex.getText());
            }
            catch (Exception e1) {
                alert.setContentText("Input error!Input must be numbers.");
                alert.show();
            }
            //判断输入数字是否为正数，当输入下标比arraylist的size大时，不报错，直接插入末尾
            if (index < 0) {
                alert.setContentText("Input error!Index can't be negative.");
                alert.show();
            }
            //清除原有内容以供更新
            hBox.getChildren().clear();
            pane1.getChildren().clear();
            //MyArrayList中的add方法
            list.add(index, value);

            btList = new Button[list.getCapacitysize()];
            for (int i = 0; i < list.size(); i++) {
                btList[i] = new Button(String.valueOf(list.get(i)));
                pane1.add(btList[i], i, 2);
            }
            for (int i = list.size(); i < list.getCapacitysize(); i++) {
                btList[i] = new Button("/");
                pane1.add(btList[i], i, 2);
            }

            Text text3 = new Text("             size =  " + list.size + "        capacity =  " + list.getCapacitysize());
            hBox.getChildren().addAll(text3, pane1);
        }

        for(int i=0;i<list.size();i++){
            final int finalI1 = i;
            btList[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                int finalI = finalI1;
                inputValue.setText(btList[finalI].getText());
                inputIndex.setText(String.valueOf(finalI));
                }
            });
        }

    }

    //查找方法
    public static void search() {

        for (int i = 0; i < btList.length; i++) {
            btList[i].setStyle("-fx-border-color: inherit");
        }

        //判断输入框是否都为空，其中之一不为空就可，若都不为空，计算以下标框为准
        if ((inputValue.getText() == null || inputValue.getText().isEmpty()) && (inputIndex.getText() == null || inputIndex.getText().isEmpty())) {
            alert.setContentText("Input error!Input can not be empty.");
            alert.show();
        }
        //若下标不为空，则可计算
        if (inputIndex.getText() != null && !inputIndex.getText().isEmpty()) {
            //判断是否输入数字
            try {
                index = Integer.parseInt(inputIndex.getText());
            } catch (Exception e1) {
                alert.setContentText("Input error!Input must be numbers.");
                alert.show();
            }
            //判断是否输入合法数字
            if (index < 0 || index > list.size()) {
                alert.setContentText("Input error!Index must be more than 0 and less than list.size.");
                alert.show();
            }
            //若输入无误，就将该下标处元素对应位置的按钮变色
            else {
                btList[index].setStyle("-fx-border-color: indianred");
            }
        }
        //若只输入了一个value
        else {
            if (inputValue.getText() != null || !inputValue.getText().isEmpty()) {
                boolean wrong = false;
                int searchValue = 0;

                searchValue = Integer.parseInt(inputValue.getText());
                //若输入无误
                if (wrong == false) {
                    //使用MyArrayList中的indexOf方法
                    int index = list.indexOf(searchValue);
                    //返回-1，则不存在
                    if (index == -1) {
                        alert.setContentText("This element does not in the list ");
                        alert.show();
                    }
                    else {
                        btList[index].setStyle("-fx-border-color: indianred");
                    }
                }
            }
        }
        for(int i=0;i<list.size();i++){
            final int finalI1 = i;
            btList[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                int finalI = finalI1;
                inputValue.setText(btList[finalI].getText());
                inputIndex.setText(String.valueOf(finalI));
                }
            });
        }
    }

    //删除方法
    public static void delete() {
        //删除方法只能用下标来操作，判断下标是否输入为空
        if (inputIndex.getText() == null || inputIndex.getText().isEmpty()) {
            alert.setContentText("Input error!Index can't be empty.");
            alert.show();
        }
        //其余操作与前面两个方法差不多
        else {
            try {
                index = Integer.parseInt(inputIndex.getText());
            } catch (Exception e1) {
                alert.setContentText("Input error!Index must be numbers.");
                alert.show();
            }
            if (index < 0 || index >= list.size()) {
                alert.setContentText("Input error!Index must be more than 0 and less than list.size.");
                alert.show();
            } else {
                hBox.getChildren().clear();
                pane1.getChildren().clear();
                list.remove(index);
                btList = new Button[list.getCapacitysize()];

                for (int i = 0; i < list.size(); i++) {
                    btList[i] = new Button(String.valueOf(list.get(i)));
                    pane1.add(btList[i], i, 2);
                }
                for (int i = list.size(); i < list.getCapacitysize(); i++) {
                    btList[i] = new Button("/");
                    pane1.add(btList[i], i, 2);
                }
                for (int i = 0; i < btList.length; i++) {
                    btList[i].setStyle("-fx-border-color: inherit");
                }

                Text text3 = new Text("             size =  " + list.size + "        capacity =  " + list.getCapacitysize());
                hBox.getChildren().addAll(text3, pane1);
            }
        }
        for(int i=0;i<list.size();i++){
            final int finalI1 = i;
            btList[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                int finalI = finalI1;
                inputValue.setText(btList[finalI].getText());
                inputIndex.setText(String.valueOf(finalI));
                }
            });
        }
    }

    //去除多余容量
    public static void trimToSize() {
        hBox.getChildren().clear();
        pane1.getChildren().clear();

        btList = new Button[list.getCapacitysize() + 1];

        for (int i = 0; i < list.size(); i++) {
            btList[i] = new Button(String.valueOf(list.get(i)));
            pane1.add(btList[i], i, 2);
        }

        Text text3 = new Text("             size =  " + list.size + "        capacity =  " + list.size);
        hBox.getChildren().addAll(text3, pane1);
        for(int i=0;i<list.size();i++){
            final int finalI1 = i;
            btList[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                int finalI = finalI1;
                inputValue.setText(btList[finalI].getText());
                inputIndex.setText(String.valueOf(finalI));
                }
            });
        }
    }
}