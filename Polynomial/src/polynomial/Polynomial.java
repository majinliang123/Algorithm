package polynomial;
/**
 * ����һ������ʽ��
 * ����ʵ�ֶ���ʽ�ļӷ��ͳ˷�
 * @author majinliang
 *
 */
public class Polynomial {

	/*
	 * ����һ����
	 * �������ʽ�Ľڵ�
	 */
	public class Term{
		private double coef;//����ʽ�е�ϵ��
		private int exp;//�������ʽ����
		private Term next;//��һ���ڵ�
		
		
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
	 *��ʼ����Polynomial�е�һЩ���� 
	 */
	private Term head;
	private Term current; 
	private int max;
	public Polynomial(){
		current = head = null;
	}
	
	//����ڵ㣬ֻ����˳���������
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
	
	//��ӡ����ʽ
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
		System.out.println("����ʽΪ��"+str);
	}
	
	public Term getHead(){
		return head;
	}
	
	public int getMax(){
		return max;
	}
	
}
