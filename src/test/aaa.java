/**
 *FileName:aaa.java
 * @author:lmy
 *Creatdate:2018年12月24日下午8:03:30
 */
package test;

import java.util.ArrayList;
import java.util.List;



public class aaa {
    static int[] water={0,0,8};
    static int[] maxwater={3,5,8};

    /*
    * 判断是否能在某个状态下从index给target水壶倒水
    * */
    public static boolean judge_pour(int index,int target,int[] num){
        if(num[index]==0)
            return false;
        else{
            if(num[target]==maxwater[target])
                return false;
            else{
                return true;
            }
        }

    }


    /*
    * 判断list是否含有s
    * */
    public static boolean judge_exist(List<String> list,String s){
        for(String str:list){
            if(str.equals(s))
                return true;
        }
        return false;

    }
    static List<String> allstate=new ArrayList<>();//存放广度优先遍历序列
    static List<String> list=new ArrayList<>();//用于广度优先遍历查找

    /*
    * 传入某个状态进行倒水
    * */
    public static void pour_water(int[] num){

        int[] nownum=num;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i!=j){
                    if(judge_pour(i,j,nownum)) {
                        int m=nownum[i],n=nownum[j];
                        /*
                        * 如果i水壶不能倒光
                        * */
                        if (nownum[i] + nownum[j] > maxwater[j]) {
                            nownum[i] = nownum[i] - (maxwater[j] - nownum[j]);
                            nownum[j] = maxwater[j];
                        }
                        /*
                        * i水壶能倒光
                        * */
                        else {
                            nownum[j] = nownum[i]+nownum[j];
                            nownum[i] = 0;


                        }
                        /*
                        * 若有一个水壶为4则将这个状态加到allstate，并且将上一次加到list的状态移除
                        * */
                        if (nownum[0] == 4 || nownum[1] == 4 || nownum[2] == 4) {
                            allstate.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);
                            list.remove(list.size()-1);
                            return;
                        }
                        /*
                        * allstate不存在该状态时将该状态分别加到allstate和list
                        * */
                        if(!judge_exist(allstate,nownum[0] + "" + nownum[1] + "" + nownum[2])){
                           /* System.out.println(nownum[0] + "" + nownum[1] + "" + nownum[2]);*/
                            allstate.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);
                            list.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);

                        }
                        /*
                        * 状态还原
                        * */
                        nownum[i]=m;
                        nownum[j]=n;
                    }
                }

            }
        }


    }



    public static void main(String[] args){
        allstate.add(water[0]+""+water[1]+""+water[2]);//初始状态

        pour_water(water);
        /*
        * list的大小在方法pour_water中动态变化直到一个水壶装到4
        * */
        for(int k=0;k<list.size();k++){
            int[] newnum={Integer.parseInt(list.get(k).charAt(0)+""),Integer.parseInt(list.get(k).charAt(1)+""),Integer.parseInt(list.get(k).charAt(2)+"")};
            pour_water(newnum);
        }
        for(String str:allstate){
            System.out.println(str);
        }

    }
}
