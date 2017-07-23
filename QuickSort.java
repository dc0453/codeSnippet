import java.util.Arrays;
import java.util.Stack;

/**
 * Author: dc0453
 * Since: 2017/3/10 下午2:50
 * quickSort
 */
public class QuickSort {

    private int partition(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while (low < high && pivot < array[high]) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] < pivot) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }

    private void quickSortRecursively(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortRecursively(array, low, pivotIndex - 1);
            quickSortRecursively(array, pivotIndex + 1, high);
        }
    }

    private void quickSortNonRecursively(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            if (pivotIndex - 1 > low) {
                stack.push(low);
                stack.push(pivotIndex - 1);
            }
            if (pivotIndex + 1 < high) {
                stack.push(pivotIndex + 1);
                stack.push(high);
            }

            while (!stack.empty()) {

                high = stack.pop();
                low = stack.pop();

                pivotIndex = partition(array, low, high);

                if (pivotIndex - 1 > low) {
                    stack.push(low);
                    stack.push(pivotIndex - 1);
                }

                if (pivotIndex + 1 < high) {
                    stack.push(pivotIndex + 1);
                    stack.push(high);
                }
            }
        }
    }

    public void quickSort(int[] array) {
//        quickSortRecursively(array, 0, array.length - 1);
        quickSortNonRecursively(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] numbers = {10,20,15,0,6,7,2,1,-5,55};
        System.out.println(Arrays.toString(numbers));
        quickSort.quickSort(numbers);
        System.out.println(Arrays.toString(numbers));

    }
}
