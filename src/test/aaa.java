/**
 *FileName:aaa.java
 * @author:lmy
 *Creatdate:2018��12��24������8:03:30
 */
package test;

import java.util.ArrayList;
import java.util.List;



public class aaa {
    static int[] water={0,0,8};
    static int[] maxwater={3,5,8};

    /*
    * �ж��Ƿ�����ĳ��״̬�´�index��targetˮ����ˮ
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
    * �ж�list�Ƿ���s
    * */
    public static boolean judge_exist(List<String> list,String s){
        for(String str:list){
            if(str.equals(s))
                return true;
        }
        return false;

    }
    static List<String> allstate=new ArrayList<>();//��Ź�����ȱ�������
    static List<String> list=new ArrayList<>();//���ڹ�����ȱ�������

    /*
    * ����ĳ��״̬���е�ˮ
    * */
    public static void pour_water(int[] num){

        int[] nownum=num;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i!=j){
                    if(judge_pour(i,j,nownum)) {
                        int m=nownum[i],n=nownum[j];
                        /*
                        * ���iˮ�����ܵ���
                        * */
                        if (nownum[i] + nownum[j] > maxwater[j]) {
                            nownum[i] = nownum[i] - (maxwater[j] - nownum[j]);
                            nownum[j] = maxwater[j];
                        }
                        /*
                        * iˮ���ܵ���
                        * */
                        else {
                            nownum[j] = nownum[i]+nownum[j];
                            nownum[i] = 0;


                        }
                        /*
                        * ����һ��ˮ��Ϊ4�����״̬�ӵ�allstate�����ҽ���һ�μӵ�list��״̬�Ƴ�
                        * */
                        if (nownum[0] == 4 || nownum[1] == 4 || nownum[2] == 4) {
                            allstate.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);
                            list.remove(list.size()-1);
                            return;
                        }
                        /*
                        * allstate�����ڸ�״̬ʱ����״̬�ֱ�ӵ�allstate��list
                        * */
                        if(!judge_exist(allstate,nownum[0] + "" + nownum[1] + "" + nownum[2])){
                           /* System.out.println(nownum[0] + "" + nownum[1] + "" + nownum[2]);*/
                            allstate.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);
                            list.add(nownum[0] + "" + nownum[1] + "" + nownum[2]);

                        }
                        /*
                        * ״̬��ԭ
                        * */
                        nownum[i]=m;
                        nownum[j]=n;
                    }
                }

            }
        }


    }



    public static void main(String[] args){
        allstate.add(water[0]+""+water[1]+""+water[2]);//��ʼ״̬

        pour_water(water);
        /*
        * list�Ĵ�С�ڷ���pour_water�ж�̬�仯ֱ��һ��ˮ��װ��4
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
