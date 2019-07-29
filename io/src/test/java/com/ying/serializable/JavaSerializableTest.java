package com.ying.serializable;

import com.alibaba.fastjson.JSON;
import com.ying.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * JAVA自带的序列化测试
 */
public class JavaSerializableTest {

    private static final String FILE_PATH = "D:\\datas\\Person.txt";

    /**
     * 测试JavaBean的序列化
     * @throws IOException
     */
    @Test
    public void testSerializable() throws IOException {
        Person person = new Person();
        person.setName("ying");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File(FILE_PATH)));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    /**
     * 测试文件反序列化为JavaBean
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void testDeserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File(FILE_PATH)));
        Person person = (Person) ois.readObject();
        Assert.assertEquals(person.getName(), "ying");
        System.out.println("Person对象反序列化成功！");
        System.out.println(JSON.toJSONString(person));
    }

}
