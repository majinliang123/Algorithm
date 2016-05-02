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
	
	
	public StringBuffer  phrase;//存储当前正在形成的词
	public String str;//存储整个字符串
	public String out;
	
	public static void main(String[] args) {
		Syntactic s = new Syntactic();
		s.out="";
		s.scanner();
	}
	

	/*
	 * 用于输出字符串到文件中
	 */
	public void TextOut(String str){
		try {
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter("tokenOut.txt",true));
			String input = str+"\r\n ";
			bufWriter.write(input);
			bufWriter.close(); 
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
	}
	
	
	
	/*
	 * 用于从文件中读取文字
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
			// TODO 自动生成的 catch 块
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
	 * 用于遍历字符串
	 */
	void scanner(){
		//(- 34 (* 3 42)),要输入的字符串
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
			TextOut("最后的结果为：  "+lexp(t));
			TextOut("---------------------------------\r\n"
				  + "----------------------------------\r\n"
				  + "----------------------------------\r\n");
			System.out.println(lexp(t));
		}else{
			TextOut("输入错误");
			TextOut("---------------------------------\r\n"
				  + "----------------------------------\r\n"
				  + "----------------------------------\r\n");
		}
		
		
	}
	
	/*
	 * 创建树
	 * 递归
	 */
	Tree createTree(Vector<String> vector){
		Tree tree = null;
		Tree t = null;
		
		//初始化时去掉第一层括号
		if(vector.get(0).equals("(")){
			vector.removeElementAt(0);
			vector.removeElementAt(vector.size()-1);
		}
		
		//对于括号中的部分进行递归
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
	 * 用于打印生成的树
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
	 * 构造用于存储节点的树
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
	
	
	/*
	 * 进行语法分析
	 * 将获取的数据存入vector
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
	 * 用于检查输入是否有错误
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
