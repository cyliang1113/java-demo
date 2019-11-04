package cn.leo.java.demo.sort;

import java.util.Arrays;

public class FastSortDemo {
    public static void main(String[] args) {
//        int[] src = {4, 3, 12, 2, 8, 1};
        int[] src = {4, 3};
        System.out.println(Arrays.toString(src));
        fastSort(src, 0, src.length - 1);
        System.out.println(Arrays.toString(src));
    }

    private static void fastSort(int[] src, int left, int right) {
        if (left >= right) {
            return;
        }
        int lP = left;
        int rP = right;
        // [3, 1, 4, 2]
        int pivot = src[lP];
        while (lP < rP) {
            while (lP < rP && src[rP] >= pivot) {
                rP--;
            }
            if (lP < rP && src[rP] < pivot) {
                src[lP] = src[rP];
                src[rP] = pivot;
            }
            while (lP < rP && src[lP] <= pivot) {
                lP++;
            }
            if (lP < rP && src[lP] > pivot) {
                src[rP] = src[lP];
                src[lP] = pivot;
            }
        }
        fastSort(src, left, lP - 1);
        fastSort(src, lP + 1, right);
    }

}
