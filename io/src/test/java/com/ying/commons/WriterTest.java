package com.ying.commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class WriterTest {

    /**
     * 写入字符串到文件中
     * @throws IOException
     */
    @Test
    public void testWriteString() throws IOException {
        String path = "D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt";
        for (int i = 0; i < 100; i++) {
            FileUtils.writeStringToFile(new File(path), "ceeecececec很霸道\n", "UTF-8", true);
        }
    }

    @Test
    public void testWriteWithSelectIO() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt");
        // 没有Append！
        IOUtils.write("尹贵水傻逼", fos, "UTF-8");
    }

    @Test
    public void testMergeFiles() throws IOException {
        // 将三个文件 写成一个文件
        // 创建集合 保存的是 FileInputStream
        Vector<FileInputStream> vector = new Vector<FileInputStream>();
        vector.add(new FileInputStream("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (11) - 副本.txt"));
        vector.add(new FileInputStream("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (12) - 副本.txt"));
        vector.add(new FileInputStream("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt"));
        // 获取迭代器
        Enumeration<FileInputStream> elements = vector.elements();
        // 构建合并流(把三个文件都读到一起)
        SequenceInputStream sis = new SequenceInputStream(elements);
        // 写入到一个文件中
        FileOutputStream fos = new FileOutputStream("D:\\ss\\qiyi_sysset.ini\\merge.txt");
        // 数组方式写入
        byte[] b = new byte[1024];
        int len = 0;
        while((len = sis.read(b)) != -1){
            // 写
            fos.write(b, 0, len);
        }
        // 关闭资源
        fos.close();
        sis.close();
    }

    /**
     * 分割图片
     * @throws IOException
     */
    @Test
    public void splitImage() throws IOException {
        FileInputStream fis = new FileInputStream(new File("D:\\ss\\image\\ssss.jpg"));
        // 定义一个数字 保存文件的名字
        int num = 1;
        FileOutputStream fos = null;
        // 这里代表 1M
        byte[] b = new byte[1024 * 100];
        int len = 0;
        while((len = fis.read(b)) != -1){
            // 写入多个文件 拼接文件的名字
            fos = new FileOutputStream("D:\\ss\\image\\ssss" + num++ + ".jpg");
            fos.write(b, 0, len);
            fos.close();
        }
        fis.close();
    }

    @Test
    public void mergeImage() throws IOException {
        //合并图片
        Vector<FileInputStream> vector = new Vector<FileInputStream>();
        // 循环拼接路径 并且添加到集合中
        for(int i = 1; i < 6; i++){
            // 拼接要合并的文件
            File file = new File("D:\\ss\\image\\ssss" + i + ".jpg");
            FileInputStream fis = new FileInputStream(file);
            // 添加到集合中
            vector.add(fis);
        }
        //获取迭代器
        Enumeration<FileInputStream> elements = vector.elements();
        // 创建合并流
        SequenceInputStream sis = new SequenceInputStream(elements);
        // 写到新路径下
        FileOutputStream fos = new FileOutputStream("D:\\ss\\image\\mergeSSS.jpg");
        // 进行读写
        byte[] b = new byte[1024 * 1024];
        int len = 0;
        while((len = sis.read(b)) != -1){
            fos.write(b, 0, len);
        }
        sis.close();
        fos.close();
    }
}
