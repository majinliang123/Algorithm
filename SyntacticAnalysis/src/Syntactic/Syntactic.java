package Syntactic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Syntactic {
	
	
	public StringBuffer  phrase;//�洢��ǰ�����γɵĴ�
	public String str;//�洢�����ַ���
	public String out;
	
	public static void main(String[] args) {
		Syntactic s = new Syntactic();
		s.out="";
		s.scanner();
	}
	

	/*
	 * ��������ַ������ļ���
	 */
	public void TextOut(String str){
		try {
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter("tokenOut.txt",true));
			String input = str+"\r\n ";
			bufWriter.write(input);
			bufWriter.close(); 
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} 
	}
	
	
	
	/*
	 * ���ڴ��ļ��ж�ȡ����
	 */
	public String TextIn(String str){
		StringBuffer sb = null;
        try {
        	File file=new File(str);
        	if(!file.exists()||file.isDirectory())
                throw new FileNotFoundException();
            BufferedReader br=new BufferedReader(new FileReader(file));
            String temp=null;
            sb=new StringBuffer();
            temp=br.readLine();
            while(temp!=null){
                sb.append(temp+" ");
                temp=br.readLine();
            }
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        return sb.toString();
	}
	
	int lexp(Tree tree){
		if(tree.getNode().equals("+")||tree.getNode().equals("-")||tree.getNode().equals("*")){
			return op(tree.getNode(),tree.getLeftChild(),tree.getRightChild());
		}
		return 0;
	}
	
	int op(String str, Tree leftTree,  Tree rightTree){
		if(str.equals("+")){
			return lexpseq(leftTree)  + lexpseq(rightTree);
		}else if(str.equals("-")){
			return lexpseq(leftTree)  - lexpseq(rightTree);
		}else{
			return lexpseq(leftTree)  * lexpseq(rightTree);
		}
	}
	
	int lexpseq(Tree tree){
		if(isNumber(tree.getNode())){
			return Integer.parseInt(tree.getNode());
		}else{
			return lexp(tree);
		}
	}
	
	
	
	/**
	 * ���ڱ����ַ���
	 */
	void scanner(){
		//(- 34 (* 3 42)),Ҫ������ַ���
		Vector<String> vector = new Vector<String>();
		String in = TextIn("in.txt");
		vector = phraseAnalysis(in);
		/*for(int i = 0; i < vector.size(); i++){
			System.out.println(vector.get(i));
		}*/
		Tree t = createTree(vector);
		//System.out.println(printTree(t,0));
		System.out.println(check(t));
		if(check(t)){
			TextOut(printTree(t,0));
			TextOut("���Ľ��Ϊ��  "+lexp(t));
			TextOut("---------------------------------\r\n"
				  + "----------------------------------\r\n"
				  + "----------------------------------\r\n");
			System.out.println(lexp(t));
		}else{
			TextOut("�������");
			TextOut("---------------------------------\r\n"
				  + "----------------------------------\r\n"
				  + "----------------------------------\r\n");
		}
		
		
	}
	
	/*
	 * ������
	 * �ݹ�
	 */
	Tree createTree(Vector<String> vector){
		Tree tree = null;
		Tree t = null;
		
		//��ʼ��ʱȥ����һ������
		if(vector.get(0).equals("(")){
			vector.removeElementAt(0);
			vector.removeElementAt(vector.size()-1);
		}
		
		//���������еĲ��ֽ��еݹ�
		if(vector.contains("(")){
			for(int i = 0; i < vector.size(); i++){	
				if(vector.get(i).equals("(")){
					for(int a = vector.size() - 1; a > i; a--){			
						if(vector.get(a).equals(")")){
							Vector<String> v = new Vector<String>();
							for(int b = i+1 ; b < a ; b++){
								v.add(vector.get(b));
								//System.out.println(vector.get(b));
							}
							t = createTree(v);
							tree = new Tree(vector.get(0));
							//System.out.println(vector.get(0));
							if(i == 1){
								tree.addLeftChild(t);
								tree.addRightChild(new Tree(vector.get(vector.size()-1)));
							}else{
								tree.addLeftChild(new Tree(vector.get(1)));
								tree.addRightChild(t);
							}
							break;
						}
					}
				break;
				}
			}
		}else{
			tree = new Tree(vector.get(0));
			tree.addLeftChild(new Tree(vector.get(1)));
			tree.addRightChild(new Tree(vector.get(2)));
		}
		return tree;
	}
	
	
	/*
	 * ���ڴ�ӡ���ɵ���
	 */
	public String printTree(Tree t, int blk){
		for(int i=0;i<blk;i++){
			out+="    ";
			System.out.print("    ");
		}
		if(t != null){
			out+=("|--<"+t.getNode()+">\r\n");
			System.out.println("|--<"+t.getNode()+">");
			if(t.getLeftChild() != null)
				printTree(t.getLeftChild(),blk+1);
			if(t.getRightChild() != null)
				printTree(t.getRightChild(),blk+1);		
		}
		return out;
	}
	
	
	/**
	 * �������ڴ洢�ڵ����
	 * @author majinliang
	 *
	 */
	public class Tree{
		
		private String node = null;
		private Tree leftChild = null;
		private Tree rightChild = null;
		private Tree father = null;
		
		public Tree(String root){
			node = root;
		}
		
		public void addLeftChild(Tree root){
			leftChild = root;
		}
		
		public Tree getLeftChild(){
			return leftChild;
		}
		
		public Tree getRightChild(){
			return rightChild;
		}
		
		public String getNode(){
			return node;
		}
		
		public void addRightChild(Tree root){
			rightChild = root;
		}
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
	
	
	/*
	 * �����﷨����
	 * ����ȡ�����ݴ���vector
	 */
	Vector<String> phraseAnalysis(String in){
		Vector<String> phraseVector = new Vector<String>();
		str = in;
		int position = 0;
		phrase = new StringBuffer();
		char ch = str.charAt(position);
		while(position < str.length()-1){
			if(ch == '('||ch == ')'||ch == '*'||ch == '-'||ch == '+'){
				phrase.append(ch);
				position= position + 1;
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
					ch = str.charAt(position);
				}
			}		
		}
		return phraseVector;
	}
	
	
	/*
	 * ���ڼ�������Ƿ��д���
	 */
	boolean check(Tree tree){
		if(isNumber(tree.getNode())){
			if(tree.getLeftChild()!=null || tree.getRightChild()!=null){
				return false; 
			}
		}else{
			if(tree.getLeftChild()==null || tree.getRightChild()==null || tree.getNode().equals("(") || tree.getNode().equals(")")){
				return false; 
			}else{
				return (check(tree.getRightChild())&&check(tree.getLeftChild()));
			}
		}
		return true;
	}
}
