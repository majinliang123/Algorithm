package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * ʵ�ֶ��ַ����ļ���
 * ������Ǻ�׺��ʾ
 * ABCD-*+EF/-
 * ��׺���ʽΪ:
 * A+B*(C-D)-E/F
 * �������û�н��д������
 * �����ַ���ʱһ��Ҫ���ձ�׼����
 * ���򽫻���ִ���
 * @author majinliang
 *
 */
public class Calculator {
	
	public StringBuffer  phrase;//�洢��ǰ�����γɵĴ�,���ڴʷ�����
	public String str;//�洢�����ַ��������ڴʷ�����
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		String str = null;
		//�ӿ���̨����һ���ַ���
		//��������ֻ��������
		//��Ϊ�ʷ�����ʱ��ֻ�Ƕ����������˿���
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
	 * ���дʷ�����
	 * �������ڵ�����֮���ÿո����
	 * �����������ʹ�ÿո������Ҳ���Բ����ÿո����
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
	 * �ж�һ���ַ����Ƿ�Ϊ����
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
