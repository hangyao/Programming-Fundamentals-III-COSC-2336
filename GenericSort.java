/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericSort;

/**
 * This is a program for Assignment 4: Generic Quicksort of COSC-2336
 * @author HangYao
 */
public class GenericSort {
    public static void main(String[] args) {
        // Quicksort an array with Integers
        Comparable[] array1 = {0, 43, 33, 16, 99};
        quicksort(array1, 0, array1.length - 1);
        System.out.println("Quicksorted Integer Array:");
        for(Comparable i: array1) {
            System.out.println(i);
        }
        
        // Quicksort an array with Doubles
        Comparable[] array2 = {0.5, 4.3, 3.3, 16., 9.9};
        quicksort(array2, 0, array2.length - 1);
        System.out.println("\nQuicksorted Double Array:");
        for(Comparable i: array2) {
            System.out.println(i);
        }
        
        // Quicksort an array with Strings
        Comparable[] array3 = {"Mike", "Chris", "John", "Wayne", "Bill"};
        quicksort(array3, 0, array3.length - 1);
        System.out.println("\nQuicksorted String Array:");
        for(Comparable i: array3) {
            System.out.println(i);
        }
    }
    
    /**
     * The quicksort method uses the QuickSort algorithm to sort array, 
     * from the start element through the end element.
     * @param <E>
     * @param array The array to sort.
     * @param start The starting index of the region to sort.
     * @param end The ending index of the region to sort.
     */
    public static < E extends Comparable<E> > void 
        quicksort(E[] array, int start, int end) {
        int pivotPoint;
        if (start < end) {
            // Get the pivot point.
            pivotPoint = partition(array, start, end);
            // Sort the first sub list.
            quicksort(array, start, pivotPoint - 1);
            // Sort the second sub list.
            quicksort(array, pivotPoint + 1, end);
        }
    }
        
    /**
     * The partition method selects the value in the middle of the array as the
     * pivot. The array is rearranged so all the elements less than the pivot
     * are on its left and all the elements greater than pivot are on its right.
     * @param <E>
     * @param array The array to sort.
     * @param start The starting index of the region to sort.
     * @param end The ending index of the region to sort.
     * @return 
     */
        private static < E extends Comparable<E> > int 
        partition(E[] array, int start, int end) {
        int mid = (start + end) / 2;
        swap(array, start, mid);
        int pivotIndex = start;
        E pivotValue = array[start];
        for (int scan = start + 1; scan <= end; scan++) {
            if (array[scan].compareTo(pivotValue) < 0) {
                pivotIndex++;
                swap(array, pivotIndex, scan);
            }
        }
        swap(array, start, pivotIndex);
        return pivotIndex;
    }
        
    /**
     * The swap method swaps the a element in array with the b element.
     * @param <E>
     * @param array The array to sort.
     * @param a The first element to swap.
     * @param b The second element to swap.
     */
    private static < E extends Comparable<E> > void 
        swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
}
