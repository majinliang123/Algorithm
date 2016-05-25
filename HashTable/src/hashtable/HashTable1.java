package hashtable;
/**
 * 主要用于实现hashtable
 * 插入操作
 * 删除操作
 * 查找操作
 * @author majinliang
 *
 */

/*
 * 这里只是实现了hash表的闭散列冲突处理方法
 * 闭散列冲突处理办法有很多种
 * 1.线性探查法
 * 2.二次探查法
 * 3.双散列法
 * 这里只实现了最常用的线性探查法
 */
public class HashTable1 {

	private int divitor;//散列函数的除数
	private int maxSize;//初始化时规定的散列表的大小
	private int[] ht;//散列表
	private String[] info;//每个元素有三种状态：empty, active, delete
	
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
	 * 搜索在一个散列表中关键码与key想匹配的元素
	 * 如果匹配成功则返回该元素的位置
	 * 如果不成功，则返回一个没有被使用的位置
	 */
	private int findPos(int key){
		int i = key % divitor;
		int j = i;
		do{
			if(info[j].equals("empty")||(info[j].equals("active") && ht[j] == key)){
				return j;//不知是否存在，但是插入的时候会用到，插入时需要进行判断
			}
			j = (j + 1) % divitor;
		}while(j != i);
		return j;//查找失败,但是插入的时候会用到
	}
	
	/*
	 * 在ht表中搜索e
	 * 如果找到就不在插入
	 * 如果找不到且表已经满了，不再插入，返回false
	 * 如果找到的位置的info是empty或者delete，则插入，返回true
	 */
	public boolean insert(int e){
		int i = findPos(e);
		if(!info[i].equals("active")){
			ht[i] = e;
			info[i] = "active";
			return true;
		}
		if(info[i].equals("active") && ht[i] == e){
			System.out.println("表中已存在此元素，不能插入");
			return false;
		}
		System.out.println("表已经满了，不能插入");
		return false;
	}
	
	/*
	 * 在表中查找元素，如果查找成功，则返回true
	 * 查找失败，返回false
	 */	
	public boolean search(int e){
		int i = findPos(e);
		if(!info[i].equals("active") || ht[i]!=e)
			return false;
		else
			return true;
	}
	
	/*
	 * 在表中删除某个元素
	 * 成功返回true
	 * 失败返回false
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
