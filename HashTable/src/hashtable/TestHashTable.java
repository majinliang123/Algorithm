package hashtable;


/*
 * ���hash��
 */
public class TestHashTable {

	public static void main(String[] args) {
		
		//�Ա�ɢ�е�hashtable���в���
		HashTable1 h1 = new HashTable1(4, 5);
		h1.insert(3);
		h1.insert(3);
		h1.search(3);
		h1.remove(3);
		
		//�Կ�ɢ�е�hashtable���в���
		HashTable2 h2 = new HashTable2(4, 5);
		h2.insert(3);
		h2.insert(3);
		h2.search(3);
		h2.remove(3);
	}

}
