package com.tomhuwd.lost.util;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ImagesHttpUtils {

    // 链接url下载图片
    public static void downloadPicture(String urlList, String path) {
        if (urlList == null || "".equals(urlList)) return;
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);//返回Base64编码过的字节数组字符串
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
