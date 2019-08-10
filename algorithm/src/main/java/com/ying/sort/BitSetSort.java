package com.ying.sort;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

public class BitSetSort {

    /**
     * 随机生成size个整数
     *
     * @param size
     * @return
     */
    public static int[] generateNums(int size) {
        long start = System.currentTimeMillis();
        int[] nums = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(size);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("generateNums花费时间%sms", (end - start)));
        return nums;
    }

    /**
     * 通过Java的Bitset实现排序
     * @param nums
     */
    public static void sortNums(int[] nums) {
        long start = System.currentTimeMillis();
        System.out.println("开始排序");
        int len = nums.length;
        StringBuilder sb = new StringBuilder();
        BitSet bitSet = new BitSet(len);
        bitSet.set(0, len, false);
        for (int i = 0; i < len; i++) {
            bitSet.set(nums[i], true);
        }
        for (int i = 0; i < len; i++) {
            if (bitSet.get(i)) {
                sb.append(i).append(",");
            }
        }
        System.out.println("sortNums耗时:" + (System.currentTimeMillis() - start) + "毫秒");
        System.out.println(sb.toString());
    }

    /**
     * 通过Arrays实现排序
     * @param nums
     */
    public static void sortByArrays(int[] nums) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        Arrays.sort(nums);
        long end = System.currentTimeMillis();
        for (int num : nums) {
            sb.append(num).append(",");
        }
        System.out.println(String.format("sortByArrays花费时间%s毫秒", (end - start)));
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        int size = 10000000;
        int[] ints = BitSetSort.generateNums(size);
        sortNums(ints);
        sortByArrays(ints);
    }

}
