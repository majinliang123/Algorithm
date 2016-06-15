package BTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * ʵ��B��
 *  ��һ�ֶ�·�������������Ƕ���ģ���
 *
 *   1.���������Ҷ�ӽ�����ֻ��M�����ӣ���M>2��

    2.�����Ķ�����Ϊ[2, M]��

    3.�����������ķ�Ҷ�ӽ��Ķ�����Ϊ[M/2, M]��

    4.ÿ�����������M/2-1��ȡ������������M-1���ؼ��֣�������2���ؼ��֣�

    5.��Ҷ�ӽ��Ĺؼ��ָ���=ָ����ӵ�ָ�����-1��

    6.��Ҷ�ӽ��Ĺؼ��֣�K[1], K[2], ��, K[M-1]����K[i] < K[i+1]��

    7.��Ҷ�ӽ���ָ�룺P[1], P[2], ��, P[M]������P[1]ָ��ؼ���С��K[1]��������P[M]ָ��ؼ��ִ���K[M-1]������������P[i]ָ��ؼ�������(K[i-1], K[i])��������

    8.����Ҷ�ӽ��λ��ͬһ�㣻
    
    B-�����������Ӹ���㿪ʼ���Խ���ڵĹؼ��֣��������н��ж��ֲ��ң����

�������������������ѯ�ؼ���������Χ�Ķ��ӽ�㣻�ظ���ֱ������Ӧ�Ķ���ָ��Ϊ

�գ����Ѿ���Ҷ�ӽ�㣻
	
	B-�������ԣ�

       1.�ؼ��ּ��Ϸֲ����������У�

       2.�κ�һ���ؼ��ֳ�����ֻ������һ������У�

       3.�����п����ڷ�Ҷ�ӽ�������

       4.���������ܵȼ����ڹؼ���ȫ������һ�ζ��ֲ��ң�

       5.�Զ���ο��ƣ�

_______________________________________________________________________

	���ݽṹ���ж�B���Ķ���Ϊ
	1.���ڵ�������������Ů
	2.�����ڵ�������нڵ㣨������ʧ�ܽڵ㣩������m/2����ȡ������Ů�ڵ�
	3.���е�ʧ�ܽڵ㶼λ��ͬһ��
	//��Ҳ��̫���ʧ�ܽڵ���ɶ��˼
	
	�ڵ�ķ��ѣ�(�ݹ�����)
	���ѵķ�ʽ�ǽ��ýڵ��ֳ������ڵ㣬
	Ȼ�󣬽��м�ڵ���뵽���ڵ㵱�У���ֺ�������ڵ㣬
	�ֱ���Ϊ���뵽���ڵ���м�ڵ�������ӡ�
	����м�ڵ���뵽���ڵ����Ȼ��Ҫ���ѣ���������ѣ�ֱ�����ڵ㡣
	�����Ȼ��Ҫ���ѣ����½�һ�����ڵ㣬�����Ѻ�������ڵ�ֱ���Ϊ�¸��ڵ�������ӽڵ㡣
 * @author majinliang
 *
 */
public class BTree {
	
	private BTreeNode root;
	private int t;//��С����
	private int minKeyNum;//�Ǹ��ڵ�����ٹؼ��ָ���
	private int maxKeyNum;//�Ǹ��ڵ�����ؼ��ָ���
	
	
	public class BTreeNode{
		private int n = 0;//�ؼ��ָ���
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
	
	//���ѽڵ�
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
	
	//�ڽڵ�û��������£����в���
	private void insertNoFull(BTreeNode x, int key){
		int i = x.n - 1;
		if(x.isLeaf){
			//����С�����˳�����children��
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
	
	//ɾ���ڵ�
	// �ù�����Ҫ��֤���ԷǸ��ڵ�ִ��ɾ������ʱ����ؼ��ָ�������Ϊt
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
			//�����Ҷ�ڵ��ֱ��ɾ��
			//�������Ҷ�ڵ㣬��else
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
	//��ӡB��,��α���
	//ÿ�δ�ӡ��ÿ���ڵ��keyֵ��ͻỻ��
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
