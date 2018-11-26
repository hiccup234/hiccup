package top.hiccup.arithmetic;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：求微信群的覆盖
 * <p>
 * 微信有很多群，现进行如下抽象：
 * (1) 每个微信群由一个唯一的gid标识；
 * (2) 微信群内每个用户由一个唯一的uid标识；
 * (3) 一个用户可以加入多个群；
 * (4) 群可以抽象成一个由不重复uid组成的集合，例如：
 * g1{u1, u2, u3}
 * g2{u1, u4, u5}
 * 可以看到，用户u1加入了g1与g2两个群。
 * <p>
 * 画外音，注意：
 * gid和uid都是uint64；
 * 集合内没有重复元素；
 * <p>
 * 假设微信有M个群(M为亿级别)，每个群内平均有N个用户(N为十级别).
 * <p>
 * 现在要进行如下操作：
 * (1)  如果两个微信群中有相同的用户，则将两个微信群合并，并生成一个新微信群；
 * 例如，上面的g1和g2就会合并成新的群：
 * g3{u1, u2, u3, u4, u5}；
 * 画外音：集合g1中包含u1，集合g2中包含u1，合并后的微信群g3也只包含一个u1。
 * (2) 不断的进行上述操作，直到剩下所有的微信群都不含相同的用户为止；
 * 将上述操作称：求群的覆盖。
 * <p>
 * 设计算法，求群的覆盖，并说明算法时间与空间复杂度。
 * <p>
 * 解题思路：
 * 1、暴力法
 * 2、染色法
 * 3、链表法
 * 4、并查集
 * 5、倒排索引
 *
 * @author wenhy
 * @date 2018/11/26
 */
public class GroupCoverTest {

//    private static int groupSize = 100_000_000;
    private static int groupSize = 5;

    private static Set<Integer>[] groups = new Set[groupSize];

//    static {
//        long startTime = System.currentTimeMillis();
//        Random setRandom = new Random(37);
//        Random eleRandom = new Random();
//        for (int i = 0; i < groups.length; i++) {
//            int setSize = setRandom.nextInt(50);
//            // 这里用HashSet，查找和插入平均时间复杂度O(1)，TreeSet的为O(lg(n))，但是可以实现有序查找
//            Set temp = new HashSet<>();
//            for (int j = 0; j < setSize; j++) {
//                int element = eleRandom.nextInt(groupSize * 100);
//                if (element == 0) {
//                    System.out.println(element);
//                }
//                temp.add(element);
//            }
//            groups[i] = temp;
//        }
//        System.out.println("原始集合初始化完成，耗时：" + (System.currentTimeMillis() - startTime));
//    }

    static {
        long startTime = System.currentTimeMillis();
        groups[0] = new HashSet<>();
        groups[0].add(1);
        groups[0].add(5);
        groups[0].add(13);
        groups[0].add(9);
        groups[1] = new HashSet<>();
        groups[1].add(1);
        groups[1].add(2);
        groups[1].add(6);
        groups[2] = new HashSet<>();
        groups[2].add(45);
        groups[3] = new HashSet<>();
        groups[3].add(66);
        groups[3].add(23);
        groups[4] = new HashSet<>();
        groups[4].add(66);
        groups[4].add(99);

        System.out.println("原始集合初始化完成，耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 1、暴力法
     * 优化思路：
     * (1) 能不能快速通过元素定位集合？
     *      通过集合查元素，哈希型set时间复杂度是O(1);
     *      通过元素查集合（句柄），如何来实现呢？
     * (2) 能不能快速进行集合合并？
     * (3) 能不能一次合并多个集合？
     */
    public static void violence() {
        for (int i = 0; i < groups.length; i++) {
            Set<Integer> a = groups[i];
            if (null == a) {
                continue;
            }
            for (int j = i + 1; j < groups.length; j++) {
                // 这里不用再从0开始遍历（对称矩阵？）
//            for (int j = 0; j < groups.length && i != j; j++) {
                Set<Integer> b = groups[j];
                if (null == b) {
                    continue;
                }
                // 判断a与b是否需要合并
                for (Integer element : b) {
                    if (a.contains(element)) {
                        // 合并集合：只要把一个集合中的元素插入到另一个集合中即可
                        a.addAll(b);
                        groups[j] = null;
                        break;
                    }
                }

            }
        }
    }

    /**
     * 2、染色法
     * “排序之后相同的元素会相邻”，就是一次性找出所有可合并集合的关键，这是染色法的核心
     */
    public static void dyeing() {

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        violence();
        System.out.println("算法耗时：" + (System.currentTimeMillis() - startTime));
        for (Set g : groups) {
            if (null != g) {
                System.out.println(g);
            }
        }
        System.out.println("总计耗时：" + (System.currentTimeMillis() - startTime));
    }


}
