package hashtable;
/**
 * ��Ҫ����ʵ��hashtable
 * �������
 * ɾ������
 * ���Ҳ���
 * @author majinliang
 *
 */

/*
 * ����ֻ��ʵ����hash��ı�ɢ�г�ͻ������
 * ��ɢ�г�ͻ����취�кܶ���
 * 1.����̽�鷨
 * 2.����̽�鷨
 * 3.˫ɢ�з�
 * ����ֻʵ������õ�����̽�鷨
 */
public class HashTable1 {

	private int divitor;//ɢ�к����ĳ���
	private int maxSize;//��ʼ��ʱ�涨��ɢ�б�Ĵ�С
	private int[] ht;//ɢ�б�
	private String[] info;//ÿ��Ԫ��������״̬��empty, active, delete
	
	public HashTable1(int d, int size){
		divitor = d;
		maxSize = size;
		ht = new int[maxSize];
		info = new String[maxSize];
		for(int i = 0; i < maxSize; i++){
			info[i] = "empty";
		}
	}

	/*
	 * ������һ��ɢ�б��йؼ�����key��ƥ���Ԫ��
	 * ���ƥ��ɹ��򷵻ظ�Ԫ�ص�λ��
	 * ������ɹ����򷵻�һ��û�б�ʹ�õ�λ��
	 */
	private int findPos(int key){
		int i = key % divitor;
		int j = i;
		do{
			if(info[j].equals("empty")||(info[j].equals("active") && ht[j] == key)){
				return j;//��֪�Ƿ���ڣ����ǲ����ʱ����õ�������ʱ��Ҫ�����ж�
			}
			j = (j + 1) % divitor;
		}while(j != i);
		return j;//����ʧ��,���ǲ����ʱ����õ�
	}
	
	/*
	 * ��ht��������e
	 * ����ҵ��Ͳ��ڲ���
	 * ����Ҳ����ұ��Ѿ����ˣ����ٲ��룬����false
	 * ����ҵ���λ�õ�info��empty����delete������룬����true
	 */
	public boolean insert(int e){
		int i = findPos(e);
		if(!info[i].equals("active")){
			ht[i] = e;
			info[i] = "active";
			return true;
		}
		if(info[i].equals("active") && ht[i] == e){
			System.out.println("�����Ѵ��ڴ�Ԫ�أ����ܲ���");
			return false;
		}
		System.out.println("���Ѿ����ˣ����ܲ���");
		return false;
	}
	
	/*
	 * �ڱ��в���Ԫ�أ�������ҳɹ����򷵻�true
	 * ����ʧ�ܣ�����false
	 */	
	public boolean search(int e){
		int i = findPos(e);
		if(!info[i].equals("active") || ht[i]!=e)
			return false;
		else
			return true;
	}
	
	/*
	 * �ڱ���ɾ��ĳ��Ԫ��
	 * �ɹ�����true
	 * ʧ�ܷ���false
	 */
	public boolean remove(int e){
		if(search(e)){
			int i = findPos(e);
			info[i] = "delete";
			return true;
		}else
			return false;
	}
}
