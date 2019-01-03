package mergesort;

/**
 * 归并排序
 * <p>
 * 第一, 分解: 把待排序的 n 个元素的序列分解成两个子序列, 每个子序列包括 n/2 个元素.
 * 第二, 治理: 对每个子序列分别调用归并排序MergeSort, 进行递归操作
 * 第三, 合并: 合并两个排好序的子序列,生成排序结果.
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] data = new int[]{5, 3, 6, 2, 1, 9, 4, 8, 7};
        sort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] arr, int left, int right) {
        int center = ((right - left) >> 1) + left;
        if (left < right) {
            sort(arr, left, center);
            sort(arr, center + 1, right);
            merge(arr, left, center, right);
        }
    }


    private static void merge(int[] arr, int left, int center, int right) {
        int[] tmp = new int[right - left + 1];
        int leftHeader = left;
        int rightHeader = center + 1;
        int tmpIndex = 0;
        while (leftHeader <= center && rightHeader <= right) {
            if (arr[leftHeader] < arr[rightHeader]) {
                tmp[tmpIndex] = arr[leftHeader];
                leftHeader++;
            } else {
                tmp[tmpIndex] = arr[rightHeader];
                rightHeader++;
            }
            tmpIndex++;
        }

        while (leftHeader <= center) {
            tmp[tmpIndex] = arr[leftHeader];
            leftHeader++;
            tmpIndex++;
        }
        while (rightHeader <= right) {
            tmp[tmpIndex] = arr[rightHeader];
            rightHeader++;
            tmpIndex++;
        }
        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i - left];
        }
    }

}
