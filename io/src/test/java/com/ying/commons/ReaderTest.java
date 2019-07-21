package com.ying.commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class ReaderTest {

    /**
     * 读取文件到字符串
     * @throws IOException
     */
    @Test
    public void testReadString() throws IOException {
        String str = FileUtils.readFileToString(new File("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt"), "UTF-8");
        System.out.println(str);
    }

    @Test
    public void testReadList() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt");
        List<String> readLines = IOUtils.readLines(fis, Charset.forName("UTF-8"));
        System.out.println(readLines);
    }


}
