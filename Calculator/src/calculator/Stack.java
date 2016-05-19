package calculator;
/*
 * 栈
 */
public class Stack {

	/*
	 * 栈中的节点
	 */
	private class StackNode{
		private String data;
		private StackNode next;
		
		public StackNode(String d, StackNode n){
			data = d;
			next = n;
		}
	}
	
	//开始栈的部分
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
