package polynomial;
import polynomial.Polynomial.Term;
/**
 * ʹ�ö���ʽ����
 * ��ɳ˷��ͼӷ��Ĳ���
 * @author majinliang
 *
 */
public class AddMultiply {
	
	//����ʵ�ֶ���ʽ�ļӷ�
	public Polynomial add(Polynomial p1,Polynomial p2){
		Term current1 = p1.getHead();
		Term current2 = p2.getHead();
		Polynomial p = new Polynomial();
		//������һ�κ͹鲢��������
		while(current1!=null && current2!=null){
			if(current1.getExp() == current2.getExp()){
				p.insert(current1.getCoef() + current2.getCoef(), current1.getExp());
				current1 = current1.getNext();
				current2 = current2.getNext();
			}else{
				if(current1.getExp() < current2.getExp()){
					p.insert(current1.getCoef(), current1.getExp());
					current1 = current1.getNext();
				}else{
					p.insert(current2.getCoef(), current2.getExp());
					current2 = current2.getNext();
				}
			}
		}
		
		while(current1 != null){
			p.insert(current1.getCoef(), current1.getExp());
			current1 = current1.getNext();
		}
		
		while(current2 != null){
			p.insert(current2.getCoef(), current2.getExp());
			current2 = current2.getNext();
		}
		
		p.print();
		return p;
	}
	
	//ʵ�ֶ���ʽ�ĳ˷�
	public Polynomial multiply(Polynomial p1,Polynomial p2){
		
		int max1 = p1.getMax();
		int max2 = p2.getMax();
		int max = max1 + max2 + 1;
		double[] t = new double[max];
		
		Term current1 = p1.getHead();
		Term current2 = p2.getHead();
		
		Polynomial p = new Polynomial();
		
		for(int i = 0; i < max; i++){
			t[i] = 0;
		}
		while(current1!=null){
			while(current2!=null){
				int k = current1.getExp()+current2.getExp();
				t[k] = t[k] + current1.getCoef()*current2.getCoef();
				current2 = current2.getNext();
			}
			current2 = p2.getHead();
			current1 = current1.getNext();
		}
		for(int i = 0; i < max; i++){
			if(t[i] > 0){
				p.insert(t[i], i);
			}
		}
		p.print();
		return p;
	}
	
	
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Polynomial p1 = new Polynomial();
		p1.insert(2, 2);
		p1.insert(3.5, 3);
		p1.print();
		
		Polynomial p2 = new Polynomial();
		p2.insert(2, 2);
		p2.insert(3.5, 4);
		p2.print();
		
		AddMultiply add = new AddMultiply();
		System.out.println("�ӷ���");
		add.add(p1, p2);
		System.out.println("�˷���");
		add.multiply(p1, p2);
	}

}
