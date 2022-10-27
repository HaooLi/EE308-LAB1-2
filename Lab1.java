import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Lab1  {       
  
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	        String code="D:\\lab2Cf\\LAB1-2.cpp";
	       
	        int level=3;
	        FileReader fileReader = new FileReader(code);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        String line=bufferedReader.readLine();
	        while(line!=null) {
	            code+=line;
	            line=bufferedReader.readLine();
        }
	  }
	String keywords="abstract、assert、boolean、break、byte、case、"
            + "catch、char、class、continue、default、do、double、else、"
            + "enum、extends、final、finally、float、for、if、implements、"
            + "import、int、interface、instanceof、long、native、new、"
            + "package、private、protected、public、return、short、static、"
            + "strictfp、super、switch、synchronized、this、throw、throws、"
            + "transient、try、void、volatile、while";//all keywords
String []keyArr=keywords.split("、");
     
public static void Find_All(String[] keyArr,String code){
    int total_num = 0;
	for(int i=0;i<keyArr.length;i++) {
        Pattern p=Pattern.compile("[^a-z]"+keyArr[i]+"[^a-z]");
        Matcher matcher=p.matcher(code);
        while(matcher.find()) {
            total_num++;
        }
    }
    System.out.println("total num is "+total_num);
}
public static void Find_Switch(String code){
    //check switch
    Pattern p=Pattern.compile("switch");
    Matcher matcher=p.matcher(code);
    int switch_num=0;
	while(matcher.find()) {
        switch_num++;
    }

    //check case
    p=Pattern.compile("switch.*?}");
    matcher=p.matcher(code);
    List case_num=new ArrayList();
    while(matcher.find()) {
        String tempText=matcher.toString();//get one switch section
        Pattern temp_p=Pattern.compile("case");
        Matcher temp_matcher=temp_p.matcher(tempText);
        int temp_case_num=0;
        while(temp_matcher.find()) {
            temp_case_num++;
        }
        case_num.add(temp_case_num);
    }
    System.out.println("switch num is "+switch_num);
    System.out.print("case num are ");
    for(int i=0;i<case_num.size();i++) {
        System.out.print(case_num.get(i)+" ");
    }
    System.out.println();
}

public static void processElse(String code) {
    Pattern p = Pattern.compile("else\\s*if|else|if");
    Matcher matcher=p.matcher(code);
    Stack<String> s = new Stack();
    while(matcher.find()) {
        String temp=code.substring(matcher.start(),matcher.end());
        s.push(temp);
    }
    Stack<String> res = new Stack<String>();
    boolean flag = false;
    int ifElseIfNum=0;
    int ifElseNum=0;
    while (!s.isEmpty()) {
        String temp = s.pop();
        if (temp.equals("else")) {
            res.push(temp);
        } else if (temp.equals("else if")) {
            res.push(temp);

        } else {//we get if
            //When the top of the res stack is the else if we need to loop out the else-if all the way to the else
            while (res.peek().equals("else if")) {
                res.pop();
                // make a little notation that its from else if, not else
                flag = true;
            }
            if (res.peek().equals("else")) {
                res.pop();
            }
            //if it's elseif ,add to ifElseIfNum
            if (flag) {
                ifElseIfNum++;
                flag = false;
            } else {//or add to ifElseNum
                ifElseNum++;
            }
        }
    }
    System.out.println("if-else num: " + ifElseNum);
    System.out.println("if-elseif-else: " + ifElseIfNum);
}

}

