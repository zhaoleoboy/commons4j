package com.ying.commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;

public class FileTest {
    private final Log log = LogFactory.getLog(FileTest.class);

    /**
     * 复制文件或者目录,复制前后文件完全一样。
     * @throws IOException
     */
    @Test
    public void copyFile() throws IOException {
        File resFile = new File("D:\\MailMasterData");
        File destFile = new File("C:\\MailMasterData");
        if (resFile.isDirectory()) {
            FileUtils.copyDirectoryToDirectory(resFile, destFile);
        } else if (resFile.isFile()) {
            FileUtils.copyFileToDirectory(resFile, destFile, true);
        }
        assertEquals(destFile.exists(), true);
    }

    /**
     * 删除一个文件或者目录
     *
     * @throws IOException
     */
    @Test
    public void deleteFile() throws IOException {
        File targetFile = new File("D:\\ssss.jnt");
        if (targetFile.isDirectory()) {
            FileUtils.deleteDirectory(targetFile);
        } else if (targetFile.isFile()) {
            targetFile.delete();
        }
        assertEquals(targetFile.exists(), false);
    }

    /**
     * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
     *
     * @throws IOException
     */
    @Test
    public void moveFile() throws IOException {
        File sourceFile = new File("D:\\ttttt.txt");
        File distFile = new File("D:\\ss");
        if (sourceFile.isDirectory()) {
            FileUtils.moveDirectoryToDirectory(sourceFile, distFile, true);
        } else if (sourceFile.isFile()) {
            FileUtils.moveFileToDirectory(sourceFile, distFile, true);
        }
    }


    /**
     * 重命名文件或文件夹
     */
    @Test
    public void renameFile() {
        String newFilePath = "D:\\ss\\qiyi_sysset.ini\\ooo.txt";
        File sourceFile = new File("D:\\ss\\qiyi_sysset.ini\\ttttt.txt");
        File newFile = new File(newFilePath);
        assertEquals(sourceFile.renameTo(newFile), true);
    }

    /**
     * 读取文件或者目录的大小
     */
    @Test
    public void genFileSize() {
        File destFile = new File("D:\\ss\\qiyi_sysset.ini");
        if (destFile.isFile()) {
            System.out.println(destFile.length());
        } else if (destFile.isDirectory()) {
            System.out.println(FileUtils.sizeOfDirectory(destFile));
        }
    }

    /**
     * 判断一个文件是否存在
     * @return
     */
    @Test
    public void isExist() {
        String filePath = "";
        System.out.println(new File(filePath).exists());
    }

    /**
     * 本地某个目录下的文件列表（不递归）
     * @return
     */
    @Test
    public void listFilebySuffix() {
        IOFileFilter fileFilter1 = new SuffixFileFilter("txt");
        IOFileFilter fileFilter2 = new NotFileFilter(DirectoryFileFilter.INSTANCE);
        FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, fileFilter2);
        String[] list = new File("D:\\ss\\qiyi_sysset.ini").list(filenameFilter);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 获取文件扩展名
     */
    @Test
    public void getProperties(){
        String path = "D:\\ss\\qiyi_sysset.ini\\ooo - 副本 (13) - 副本.txt";
        String extension = FilenameUtils.getExtension(path);
        boolean isTxt = FilenameUtils.isExtension(path, "txt");
        System.out.println(isTxt);
        String name = FilenameUtils.getName(path);

        System.out.println(String.format("文件名称：%s", name));
        System.out.println(String.format("文件扩展名为：%s", extension));
    }



    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     * @return
     */
    public void string2File() {
    }
}
