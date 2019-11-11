package com.wrh.sort;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:36 2019/10/12 0012
 * @Modified By:
 */
public class QuickSortOptimize {



        /**
         * 划分
         * @param arr
         * @param left
         * @param right
         * @return
         */
        public static int partition(int[] arr, int left, int right) {
            int pivotKey = arr[left];

            while(left < right) {
                while(left < right && arr[right] >= pivotKey)
                    right --;
                arr[left] = arr[right]; //把小的移动到左边
                while(left < right && arr[left] <= pivotKey)
                    left ++;
                arr[right] = arr[left]; //把大的移动到右边
            }
            arr[left] = pivotKey; //最后把pivot赋值到中间
            return left;
        }

        /**
         * 递归划分子序列
         * @param arr
         * @param left
         * @param right
         */
        public static void quickSort(int[] arr, int left, int right) {
            if(left >= right)
                return ;
            int pivotPos = partition(arr, left, right);
            quickSort(arr, left, pivotPos-1);
            quickSort(arr, pivotPos+1, right);
        }

        public static void sort(int[] arr) {
            if(arr == null || arr.length == 0)
                return ;
            quickSort(arr, 0, arr.length-1);
        }


}
