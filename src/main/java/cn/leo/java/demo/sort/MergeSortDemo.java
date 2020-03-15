package cn.leo.java.demo.sort;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] src = {4, 3, 1, 1, 0, 8, 1, 5, 8, 1000, -9};
        System.out.println(Arrays.toString(src));
        mergeSort1(src);
        System.out.println(Arrays.toString(src));
    }

    private static void mergeSort(int[] src) {
        if (src == null) {
            throw new IllegalArgumentException("");
        }

        if (src.length == 2) {
            if (src[0] > src[1]) {
                int temp = src[0];
                src[0] = src[1];
                src[1] = temp;
            }
        } else if (src.length > 2) {
            int srcLen = src.length;
            int middle = srcLen / 2;
            int[] front = Arrays.copyOfRange(src, 0, middle);
            int[] back = Arrays.copyOfRange(src, middle, srcLen);
            mergeSort(front);
            mergeSort(back);
            merge(front, back, src);
        }
    }

    private static void merge(int[] front, int[] back, int[] src) {
        if (front.length + back.length != src.length) {
            throw new IllegalStateException("");
        }
        int fP = 0;
        int bP = 0;
        for (int i = 0; i < src.length; i++) {
            if (fP < front.length && bP < back.length) {
                int fV = front[fP];
                int bV = back[bP];
                if (fV < bV) {
                    src[i] = fV;
                    fP++;
                } else if (bV < fV) {
                    src[i] = bV;
                    bP++;
                } else {
                    src[i] = fV;
                    fP++;
                    i++;
                    src[i] = bV;
                    bP++;
                }
            } else if ((fP < front.length && bP == back.length)) {
                int fV = front[fP];
                src[i] = fV;
                fP++;
            } else if (fP == front.length && bP < back.length) {
                int bV = back[bP];
                src[i] = bV;
                bP++;
            } else {
                throw new IllegalStateException("");
            }
        }
    }

    private static void mergeSort1(int[] src) {
        if (src.length > 1) {
            int mid = src.length / 2;
            int[] a = Arrays.copyOfRange(src, 0, mid);
            int[] b = Arrays.copyOfRange(src, mid, src.length);
            mergeSort1(a);
            mergeSort1(b);
            merge1(a, b, src);
        }
    }

    private static void merge1(int[] a, int[] b, int[] src) {
        int ai = 0, bi = 0;
        for (int i = 0; ; i++) {
            if (ai < a.length && bi < b.length) {
                if (a[ai] > b[bi]) {
                    src[i] = b[bi];
                    bi++;
                } else {
                    src[i] = a[ai];
                    ai++;
                }
            } else if (ai < a.length && bi >= b.length) {
                src[i] = a[ai];
                ai++;
            } else if (ai >= a.length && bi < b.length){
                src[i] = b[bi];
                bi++;
            } else {
                break;
            }
        }
    }
}
