package BTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * 实现B树
 *  是一种多路搜索树（并不是二叉的）：
 *
 *   1.定义任意非叶子结点最多只有M个儿子；且M>2；

    2.根结点的儿子数为[2, M]；

    3.除根结点以外的非叶子结点的儿子数为[M/2, M]；

    4.每个结点存放至少M/2-1（取上整）和至多M-1个关键字；（至少2个关键字）

    5.非叶子结点的关键字个数=指向儿子的指针个数-1；

    6.非叶子结点的关键字：K[1], K[2], …, K[M-1]；且K[i] < K[i+1]；

    7.非叶子结点的指针：P[1], P[2], …, P[M]；其中P[1]指向关键字小于K[1]的子树，P[M]指向关键字大于K[M-1]的子树，其它P[i]指向关键字属于(K[i-1], K[i])的子树；

    8.所有叶子结点位于同一层；
    
    B-树的搜索，从根结点开始，对结点内的关键字（有序）序列进行二分查找，如果

命中则结束，否则进入查询关键字所属范围的儿子结点；重复，直到所对应的儿子指针为

空，或已经是叶子结点；
	
	B-树的特性：

       1.关键字集合分布在整颗树中；

       2.任何一个关键字出现且只出现在一个结点中；

       3.搜索有可能在非叶子结点结束；

       4.其搜索性能等价于在关键字全集内做一次二分查找；

       5.自动层次控制；

_______________________________________________________________________

	数据结构树中对B树的定义为
	1.根节点至少有两个子女
	2.除根节点外的所有节点（不包括失败节点）至少有m/2向上取整个子女节点
	3.所有的失败节点都位于同一层
	//我也不太清楚失败节点是啥意思
	
	节点的分裂：(递归向上)
	分裂的方式是将该节点拆分成两个节点，
	然后，将中间节点插入到父节点当中，拆分后的两个节点，
	分别作为插入到父节点的中间节点的左右子。
	如果中间节点插入到父节点后，仍然需要分裂，则继续分裂，直到根节点。
	如果仍然需要分裂，则新建一个根节点，将分裂后的两个节点分别作为新根节点的两个子节点。
 * @author majinliang
 *
 */
public class BTree {
	
	private BTreeNode root;
	private int t;//最小度数
	private int minKeyNum;//非根节点的最少关键字个数
	private int maxKeyNum;//非根节点的最多关键字个数
	
	
	public class BTreeNode{
		private int n = 0;//关键字个数
		public List<Integer> keys =new ArrayList<Integer>();
		public List<BTreeNode> children = new ArrayList<BTreeNode>(maxKeyNum + 1);
		public boolean isLeaf = true;
		
		public void insertKey(int index, int key){
			keys.add(index, key);
			n++;
			if(keys.size() > maxKeyNum){
				keys.remove(maxKeyNum);
			}
		}
		
		public int removeKey(int index){
			int key = keys.remove(index);
			n--;
			return key;
		}
		
		public void insertChild(int index, BTreeNode child){
			children.add(index, child);
			if(children.size() > maxKeyNum + 1){
				children.remove(maxKeyNum + 1);
			}
		}
		
		public BTreeNode removeChild(int index){
			BTreeNode child = children.remove(index);
			return child;
		}
	}
	
	public BTree(int degree){
		this.t = degree;
		this.minKeyNum = degree - 1;
		this.maxKeyNum = 2 * degree - 1;
		this.root = new BTreeNode();
	}
	
	//分裂节点
	private void sliptChild(BTreeNode x, int index){
		BTreeNode z = new BTreeNode();
		BTreeNode y = x.children.get(index);
		z.isLeaf = y.isLeaf;
		for(int j = 0; j < minKeyNum; j++){
			z.insertKey(j, y.keys.get(j + t));
		}
		if(!y.isLeaf){
			for(int j = 0; j < t; j++){
				z.insertChild(j, y.children.get(j + t));
			}
		}
		z.n = minKeyNum;
		y.n = minKeyNum;
		x.insertChild(index + 1, z);
		x.insertKey(index, y.keys.get(minKeyNum));
	}
	
	//在节点没满的情况下，进行插入
	private void insertNoFull(BTreeNode x, int key){
		int i = x.n - 1;
		if(x.isLeaf){
			//按从小到大的顺序插入children中
			while(i >= 0 && key < x.keys.get(i))
				i--;
			x.insertKey(i + 1, key);
			
		}else{
			while(i >= 0 && key < x.keys.get(i))
				i--;
			i = i + 1;
			if(x.children.get(i).n == maxKeyNum){
				sliptChild(x, i);
				if(key > x.keys.get(i))
					i = i + 1;
			}
			insertNoFull(x.children.get(i), key);
		}
	}
	
	public void insert(int key){
		BTreeNode r = root;
		if(root.n == maxKeyNum){
			BTreeNode newRoot = new BTreeNode();
			root = newRoot;
			newRoot.isLeaf = false;
			newRoot.insertChild(0, r);
			sliptChild(newRoot, 0);
			insertNoFull(newRoot, key);
		}else
			insertNoFull(r, key);
	}
	
	//删除节点
	// 该过程需要保证，对非根节点执行删除操作时，其关键字个数至少为t
	public void delete(int key){
		delete(root, key);
	}
	
	private void delete(BTreeNode x, int key){
		int n = x.n;
		assert n >= t || x == root;
		int i = 0;
		while(i < n && key > x.keys.get(i))
			i++;
		if(i < n && key == x.keys.get(i)){
			//如果是叶节点就直接删除
			//如果不是叶节点，就else
			if(x.isLeaf){
				x.removeKey(i);
			}else{
				BTreeNode y = x.children.get(i);
				BTreeNode z = x.children.get(i + 1);
				if(y.n >= t){
					int preKey = deleteMaxKey(y);
					x.keys.set(i, preKey);
				}else if(z.n >= t ){
					int nextKey = deleteMinKey(z);
					x.keys.set(i, nextKey);
				}else{
					int ySize = y.n;
					int zSize = z.n;
					y.insertKey(ySize, key);
					ySize++;
					boolean isChildLeaf = y.isLeaf;
					for(int j = 0; j < zSize; j++){
						y.insertKey(ySize, z.keys.get(j));
						if(!isChildLeaf){
							y.insertChild(ySize, z.children.get(j));
						}
						ySize++;
					}
					if(!isChildLeaf){
						y.insertChild(ySize, z.children.get(zSize - 1));
					}
					x.removeKey(i);
					x.removeChild(i + 1);
					if(x.n == 0){
						root = y;
					}
					delete(y, key);
				}
			}
		}else if(x.isLeaf){
			return ;
		}else{
			BTreeNode child = x.children.get(i);
			boolean isChildLeaf = child.isLeaf;
			if(child.n >= t){
				delete(child, key);
			}else if(i > 0 && x.children.get(i - 1).n >= t){
				BTreeNode leftBrother = x.children.get(i - 1);
				int leftBrotherKeyNum = leftBrother.n;
				int leftBrotherLastKey = leftBrother.keys.get(leftBrotherKeyNum - 1);
				child.insertKey(0, leftBrotherLastKey);
				x.keys.set(i - 1, leftBrotherLastKey);
				if(!isChildLeaf){
					BTreeNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
					child.insertChild(0, leftBrotherLastChild);
					leftBrother.removeChild(leftBrotherKeyNum);
				}
				leftBrother.removeKey(leftBrotherKeyNum - 1);
				delete(child, key);
			}else if(i < x.n && x.children.get(i + 1).n >= t){
				BTreeNode rightBrother = x.children.get(i + 1);
				int rightBrotherFirstKey = rightBrother.keys.get(0);
				int childKeyNum = child.n;
				child.insertKey(childKeyNum, x.keys.get(i));
				x.keys.set(i, rightBrotherFirstKey);
				if(!isChildLeaf){
					BTreeNode rightBrotherFirstChild = rightBrother.children.get(0);
					child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
					rightBrother.removeChild(0);
				}
				rightBrother.removeChild(0);
				delete(child, key);
			}else if(i > 0){
				BTreeNode leftBrother = x.children.get(i - 1);
				int leftBrotherKeyNum = leftBrother.n;
				leftBrotherKeyNum ++;
				for(int j = 0; j < t - 1; j++){
					leftBrother.insertKey(leftBrotherKeyNum, child.keys.get(j));
					if(!isChildLeaf){
						leftBrother.insertChild(leftBrotherKeyNum, child.children.get(j));
					}
					leftBrotherKeyNum++;
					
				}
				if(!isChildLeaf){
					leftBrother.insertChild(leftBrotherKeyNum, child.children.get(t - 1));
				}
				x.removeChild(i);
				x.removeChild(i-1);
				if(x.n == 0){
					root = leftBrother;
				}
				delete(leftBrother, key);
			}else{
				BTreeNode rightBrother = x.children.get(i + 1);
				int childKeyNum = child.n;
				child.insertKey(childKeyNum, x.keys.get(i));
				childKeyNum++;
				for(int j = 0; j < t -1; j++){
					child.insertKey(childKeyNum, rightBrother.keys.get(j));
					if(!isChildLeaf){
						child.insertChild(childKeyNum, rightBrother.children.get(j));
						
					}
					childKeyNum++;
				}
				if(!isChildLeaf){
					child.insertChild(childKeyNum, rightBrother.children.get(t-1));
				}
				x.removeKey(i);
				x.removeChild(i+1);
				if(x.n == 0){
					root = child;
				}
				delete(child, key);
			}
		}
	}
	
	private int deleteMinKey(BTreeNode x){
		if(x.isLeaf){
			return x.removeKey(0);
		}else{
			BTreeNode child = x.children.get(0);
			boolean isChildLeaf = child.isLeaf;
			BTreeNode rightBrother = x.children.get(1);
			if(child.n >= t){
				return deleteMinKey(child);
			}else if(rightBrother.n >= t){
				int rightBrotherFirstKey = rightBrother.keys.get(0);
				int childKeyNum = child.n;
				child.insertKey(childKeyNum, x.keys.get(0));
				x.keys.set(0, rightBrotherFirstKey);
				if(!isChildLeaf){
					BTreeNode rightBrotherFirstChild = rightBrother.children.get(0);
					child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
					rightBrother.removeChild(0);
				}
				rightBrother.removeChild(0);
				return deleteMinKey(child);
			}else{
				int childKeyNum = child.n;
				child.insertKey(childKeyNum, x.keys.get(0));
				childKeyNum++;
				for(int j = 0; j < t-1; j++){
					child.insertKey(childKeyNum, rightBrother.keys.get(j));
					if(!isChildLeaf){
						child.insertChild(childKeyNum, rightBrother.children.get(j));
					}
					childKeyNum++;
				}
				if(!isChildLeaf){
					child.insertChild(childKeyNum, rightBrother.children.get(t - 1));
				}
				x.removeChild(1);
				x.removeKey(0);
				return deleteMinKey(child);
			}
		}
	}
	
	private int deleteMaxKey(BTreeNode x){
		int keyNum = x.n;
		if(x.isLeaf){
			return x.removeKey(keyNum - 1);
		}else{
			BTreeNode child = x.children.get(keyNum);
			boolean isChildLeaf = child.isLeaf;
			BTreeNode leftBrother = x.children.get(keyNum - 1);
			int leftBrotherKeyNum = leftBrother.n;
			if(child.n >= t){
				return deleteMaxKey(child);
			}else if(leftBrother.n >= t){
				int leftBrotherLastKey = leftBrother.keys.get(keyNum - 1);
				child.insertKey(keyNum - 1, leftBrotherLastKey);
				x.keys.set(keyNum - 1, leftBrotherLastKey);
				if(!isChildLeaf){
					BTreeNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
					child.insertChild(0, leftBrotherLastChild);
					leftBrother.removeChild(leftBrotherKeyNum);
				}
				leftBrother.removeChild(leftBrotherKeyNum);
				return deleteMaxKey(child);
			}else{
				leftBrother.insertKey(leftBrotherKeyNum, x.keys.get(keyNum - 1));
				leftBrotherKeyNum++;
				for(int j = 0; j < t - 1; j++){
					leftBrother.insertKey(leftBrotherKeyNum, child.keys.get(j));
					if(!isChildLeaf){
						leftBrother.insertChild(leftBrotherKeyNum, child.children.get(t - 1));
					}
					leftBrotherKeyNum++;
				}
				if(!isChildLeaf){
					leftBrother.insertChild(leftBrotherKeyNum, child.children.get(t - 1));
				}
				x.removeChild(keyNum);
				x.removeKey(keyNum - 1);
				return deleteMaxKey(leftBrother);
			}
		}
	}
	//打印B树,层次遍历
	//每次打印完每个节点的key值后就会换行
	public void print() {
        Queue<BTreeNode> queue = new LinkedList<BTreeNode>();;
        queue.add(root);
        while (!queue.isEmpty()) {
            BTreeNode node =  queue.poll();
            for (int i = 0; i < node.n; i ++) {
                System.out.print(node.keys.get(i) + " ");
            }
            System.out.println();
            if (!node.isLeaf) {
                for (int i = 0; i < node.n + 1; i ++) {
                    queue.add(node.children.get(i));
                }
            }
        }
        
    }
}
