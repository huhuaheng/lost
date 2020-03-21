package com.tomhuwd.lost.util;

import java.io.*;

/**
 * hhh
 */
public class FileUtils {

    // 创建文件夹
    public static boolean createFolder(String path) {
        File file = new File(path);
        //如果文件夹不存在
        if (!file.exists()) {
            //创建文件夹
            file.mkdir();
            return true;
        }
        return false;
    }

    // 创建文件，并输出内容
    public static void createFile(String filePath,String text){
        BufferedWriter bw= null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // File 转byte
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
}
