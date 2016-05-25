package hashtable;


/*
 * 检测hash表
 */
public class TestHashTable {

	public static void main(String[] args) {
		
		//对闭散列的hashtable进行测试
		HashTable1 h1 = new HashTable1(4, 5);
		h1.insert(3);
		h1.insert(3);
		h1.search(3);
		h1.remove(3);
		
		//对开散列的hashtable进行测试
		HashTable2 h2 = new HashTable2(4, 5);
		h2.insert(3);
		h2.insert(3);
		h2.search(3);
		h2.remove(3);
	}

}
