package cn.leo.java.demo.sort;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
        int[] src = {4, 3, 12, 6, 8, 1};
        System.out.println(Arrays.toString(src));
        mergeSort(src);
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

//    private static int[] mergeSort(int[] src) {
//        if (src == null) {
//            throw new IllegalArgumentException("");
//        }
//        if (src.length == 0) {
//            return new int[0];
//        }
//
//        return sortProcess(src);
//
//    }

//    private static int[] sortProcess(int[] src) {
//        int srcLen = src.length;
//
//        if (srcLen == 1) {
//            return Arrays.copyOf(src, srcLen);
//        }
//        if (srcLen == 2) {
//            if (src[0] <= src[1]) {
//                return Arrays.copyOf(src, srcLen);
//            } else{
//                return new int[]{src[1], src[0]};
//            }
//        }
//        int middleIndex = srcLen / 2;
//        int[] frontSegment = sortProcess(Arrays.copyOfRange(src, 0, middleIndex));
//        int[] backSegment = sortProcess(Arrays.copyOfRange(src, middleIndex, srcLen));
//        int[] target = new int[srcLen];
//        int fP = 0;
//        int bP = 0;
//        for (int i = 0; i < srcLen; i++) {
//            if (fP < frontSegment.length && bP < backSegment.length) {
//                int fV = frontSegment[fP];
//                int bV = backSegment[bP];
//                if (fV < bV) {
//                    target[i] = fV;
//                    fP++;
//                } else if (bV < fV) {
//                    target[i] = bV;
//                    bP++;
//                } else {
//                    target[i] = fV;
//                    fP++;
//                    i++;
//                    target[i] = bV;
//                    bP++;
//                }
//            } else if ((fP < frontSegment.length && bP == backSegment.length)) {
//                int fV = frontSegment[fP];
//                target[i] = fV;
//                fP++;
//            } else if (fP == frontSegment.length && bP < backSegment.length) {
//                int bV = backSegment[bP];
//                target[i] = bV;
//                bP++;
//            } else {
//                throw new IllegalStateException("");
//            }
//        }
//        return target;
//    }
}
