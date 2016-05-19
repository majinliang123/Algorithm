package calculator;
/*
 * ջ
 */
public class Stack {

	/*
	 * ջ�еĽڵ�
	 */
	private class StackNode{
		private String data;
		private StackNode next;
		
		public StackNode(String d, StackNode n){
			data = d;
			next = n;
		}
	}
	
	//��ʼջ�Ĳ���
	private StackNode top;
	
	public Stack(String d){
		top = new StackNode(d, null);
	}
	
	public void push(String str){
		StackNode node = new StackNode(str, top);
		top = node;
	}
	
	public String pop(){
		String str = top.data;
		top = top.next;
		return str;
	}	
}
