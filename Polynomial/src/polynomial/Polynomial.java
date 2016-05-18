package polynomial;
/**
 * 这是一个多项式类
 * 用于实现多项式的加法和乘法
 * @author majinliang
 *
 */
public class Polynomial {

	/*
	 * 这是一个类
	 * 定义多项式的节点
	 */
	public class Term{
		private double coef;//多项式中的系数
		private int exp;//定义多项式的幂
		private Term next;//下一个节点
		
		
		public Term(double c, int e ){
			coef = c;
			exp = e;
			next = null;
		}
			
		public Term getNext(){
			return next;
		}
		
		public double getCoef(){
			return coef;
		}
		
		public int getExp(){
			return exp;
		}
		
		public void setCoef(double c){
			coef = c;
		}
		
		public void set(int e){
			exp = e;
		}
	}
	
	/*
	 *开始定义Polynomial中的一些参数 
	 */
	private Term head;
	private Term current; 
	private int max;
	public Polynomial(){
		current = head = null;
	}
	
	//插入节点，只考虑顺序插入数据
	public void insert(double c, int e){
		Term term = new Term(c, e);
		current = head;
		if(head == null){
			head = term;
		}else{
			while(current.next != null){
				current = current.next;
			}
			current.next = term;
		};
		max = e;
	}
	
	//打印多项式
	public void print(){
		current = head;
		String str="";
		while(current!=null){
			str += current.coef;
			str += " x";
			str += "^";
			str += current.exp;
			if(current.next!=null){
				str += " + ";
			}
			current = current.next;
		}
		System.out.println("多项式为："+str);
	}
	
	public Term getHead(){
		return head;
	}
	
	public int getMax(){
		return max;
	}
	
}
