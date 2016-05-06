package mergesort;
public class MergeSortTest {

	public static void main(String[] args) {
		int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
		MergeSortTest m = new MergeSortTest();
		m.sort(data, 0, data.length - 1);;
		System.out.println("排序后的数组：");
		print(data);
	}

	void sort(int[] data, int left, int right) {
		if(left<right){
			int mid = ( left + right)/2;
			sort( data, left, mid);
			sort( data, mid + 1, right);
			merge(data, left, mid+1, right);
		}
	}


	void merge(int[] data, int left, int center, int right) {
		int i;
		int j = left;
		int k = center;
		int[] temp = new int[data.length];
		for(i = left; (j<center)&&(k<right+1);i++){
			if(data[j]<data[k]){
				temp[i] = data[j];
				j++;
			}else{
				temp[i] = data[k];
				k++;
			}
		}
		while(j<center){
			temp[i] = data[j];
			j++;
			i++;
		}
		while(k<right+1){
			temp[i] = data[k];
			k++;
			i++;
		}
		for(int a = left; a<right+1; a++){
			data[a] = temp[a];
		}
	}

	public static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

}