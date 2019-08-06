package com.ying;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ying.model.Person;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayTest {

    /**
     * 1，通过Sets.difference(set1, set2)获取set1存在的set2中不存在的数据；
     * 2，通过Sets.intersection(set1, set2)判断两个集合的交集
     *
     */
    @Test
    public void testDiff() {
        Set<String> set1 = Sets.newHashSet("1", "2", "3", "4");
        Set<String> set2 = Sets.newHashSet("3", "5", "2", "8");
        System.out.println("set1" + Sets.difference(set1, set2));
        System.out.println("交集" + Sets.intersection(set1, set2));
        System.out.println("并集" + Sets.union(set1,set2));
    }

    /**
     * 测试
     * 1，通过List.newArrayList构造集合
     * 2，通过Predicate进行List的数据的过滤
     */
    @Test
    private void testFilter() {

        Person lilei = new Person("lilei", 20);
        Person hanmeimei = new Person("hanmeimei", 23);
        Person wangsan = new Person("wangsan", 26);
        List<Person> persons = Lists.newArrayList(lilei, hanmeimei, wangsan);

        FluentIterable<Person> filter = FluentIterable.from(persons).filter(new Predicate<Person>() {
            @Override
            public boolean apply(@Nullable Person person) {
                return person.getAge() < 25;
            }

            @Override
            public boolean test(@Nullable Person input) {
                return false;
            }
        });
        Iterator<Person> iterator = filter.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }
    }
}
