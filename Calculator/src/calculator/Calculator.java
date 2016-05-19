package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * 实现对字符串的计算
 * 输入的是后缀表示
 * ABCD-*+EF/-
 * 中缀表达式为:
 * A+B*(C-D)-E/F
 * 这个程序没有进行错误控制
 * 输入字符串时一定要按照标准输入
 * 否则将会出现错误
 * @author majinliang
 *
 */
public class Calculator {
	
	public StringBuffer  phrase;//存储当前正在形成的词,用于词法分析
	public String str;//存储整个字符串，用于词法分析
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		String str = null;
		//从控制台读入一行字符串
		//输入数字只允许整数
		//因为词法分析时，只是对整数进行了考虑
		try {
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			str = stdin.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Vector<String> v = c.phraseAnalysis(str);
		/*for(int i = 0; i < v.size(); i++){
			System.out.println(v.get(i));
		}*/
		c.run(v);
	}
	
	/*
	 * 进行词法分析
	 * 两个相邻的数字之间用空格隔开
	 * 其他情况可以使用空格隔开，也可以不适用空格隔开
	 */
	public Vector<String> phraseAnalysis(String in){
		Vector<String> phraseVector = new Vector<String>();
		str = in;
		int position = 0;
		phrase = new StringBuffer();
		char ch = str.charAt(position);
		while(position < str.length()){
			ch = str.charAt(position);
			if(ch == '*'||ch == '-'||ch == '+'||ch == '/'){
				phrase.append(ch);
				position= position + 1;
				if(position < str.length())
					ch = str.charAt(position);
				phraseVector.add(phrase.toString());
				phrase.setLength(0);
			}else{
				if(ch >= '0' && ch <= '9'){	
					while(ch>='0'&&ch<='9'){
						ch = str.charAt(position);
						phrase.append(ch);
						position= position + 1;
						ch = str.charAt(position);
					}
					phraseVector.add(phrase.toString());
					phrase.setLength(0);
				}else{
					position= position + 1;
					
				}
			}		
		}
		return phraseVector;
	}
	
	
	/*
	 * 判读一个字符串是否为数字
	 */
	boolean isNumber(String str){
		int temp = 0; 
		try{
		    temp = Integer.parseInt(str);
		}catch(Exception e){
		    return false;
		}
		return true;
	}
	
	
	public void run(Vector<String> v){
		Stack stack = new Stack(v.get(0));
		for(int i = 1; i < v.size(); i++){
			if(isNumber(v.get(i))){
				stack.push(v.get(i));
			}else{
				if(v.get(i).equals("+")){
					String str1 = stack.pop();
					String str2 = stack.pop();
					double jieguo = Double.parseDouble(str2) + Double.parseDouble(str1);
					stack.push(String.valueOf(jieguo));
				}
				if(v.get(i).equals("-")){
					String str1 = stack.pop();
					String str2 = stack.pop();
					double jieguo = Double.parseDouble(str2) - Double.parseDouble(str1);
					stack.push(String.valueOf(jieguo));
				}
				if(v.get(i).equals("*")){
					String str1 = stack.pop();
					String str2 = stack.pop();
					double jieguo = Double.parseDouble(str2) * Double.parseDouble(str1);
					stack.push(String.valueOf(jieguo));
				}
				if(v.get(i).equals("/")){
					String str1 = stack.pop();
					String str2 = stack.pop();
					double jieguo = Double.parseDouble(str2) / Double.parseDouble(str1);
					stack.push(String.valueOf(jieguo));
				}
			}
		}
		
		System.out.println(stack.pop());
	}

}
