package com.tomhuwd.lost.core;

import com.tomhuwd.lost.common.Config;
import com.tomhuwd.lost.util.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

@Slf4j
public class CoreCode {

    private static String folderPath;
    private static String jsPath;
    private static String cssPath;
    private static String imagesPath;

    public static void main(String[] args) throws IOException {
        allCode(new String[]{"https://www.jianshu.com/"});
    }

    public static String allCode(String[] allUrl) {
        init();
        for (String url : allUrl) {
            try {
                code(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return folderPath;
    }


    private static void init() {
        // 创建始于这次爬虫的文件地址 初始化
        String folderName = KeyUtils.genUniqueKey();
        folderPath = Config.path + "\\" + folderName;
        jsPath = Config.path + "\\" + folderName + "\\" + "js";
        cssPath = Config.path + "\\" + folderName + "\\" + "css";
        imagesPath = Config.path + "\\" + folderName + "\\" + "images";
        // 创建文件夹 js  css  images
        boolean folder = FileUtils.createFolder(folderPath);
        FileUtils.createFolder(jsPath);
        FileUtils.createFolder(cssPath);
        FileUtils.createFolder(imagesPath);
        // 未能成功创建文件夹情况，递归
        if (!folder) {
            init();
        }
    }


    private static String code(String url) throws IOException {
//       -----------------------文件夹准备好了，可以填充内容了----------------------
//       -----------------------1.获取html  document---------------------------------
        Document document = Jsoup.connect(url).get();
//       -----------------------2.获取其中 js,css 文件,并替换url-----------------------------
        replaceJsAndCSS(document);
//       ----------------------3.对图片 进行统一处理  ---------------------------------
        replaceImages(document);
//        ---------------------4.生成页面--------------------------------------
        FileUtils.createFile(folderPath + "\\" + KeyUtils.genUniqueKey() + ".html", document.toString());
        return folderPath;

    }


    // 替换图片  images
    private static void replaceImages(Document document) {
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            try {
                // 取出所有的 src
                String src = imgElement.attr("src");
                log.info("src:" + src);
                if (src == null || "".equals(src)){
                    src = imgElement.attr("data-original-src");
                    if(src == null || "".equals(src)){
                        continue;
                    }
                }
                // 判断 src 都有效性,并对src进行处理，
                if (!UrlUtils.UrlIsFitness(src)) src = "https:" + src;
                String imageFileName = Md5Utils.hash(src) + ".png";
                String imagesFileUrl = imagesPath + "\\" + imageFileName;
                // 下载图片
                ImagesHttpUtils.downloadPicture(src, imagesFileUrl);
                // 图片替换
                imgElement.attr("src", "images/" + imageFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 替换 js and css
    private static void replaceJsAndCSS(Document document) {
        // 对css js 文件进行替换处理
        generalProcessing("js",jsPath, "script[src]", "src", ".js", document);
        generalProcessing("css",cssPath, "link[rel=stylesheet]", "href", ".css", document);
    }


    /**
     * js and css 替换公用代码
     *
     * @param path              cssPath
     * @param cssQuery          link
     * @param attributeKey      href src
     * @param filenameExtension .css .js
     * @param document          页面元素
     * @return
     */
    private static void generalProcessing(String type,String path, String cssQuery, String attributeKey, String filenameExtension, Document document) {
        Elements select = document.select(cssQuery);
        for (Element element : select) {
            try {
                String url = element.attr(attributeKey);
                // 判断url是否符合规范
                boolean b = UrlUtils.UrlIsFitness(url);
                if (!b) url = "https:" + url;
                // 获取css内容
                String text = HttpUtils.sendGet(url, "");
                // 输出文件 1.确认文件名  2.确认输出地址 3.输出文件并写入内容
                String fileName = Md5Utils.hash(text) + filenameExtension;
                String filePath = path + "\\" + fileName;
                FileUtils.createFile(filePath, text);
                // 对element元素进行替换  相对路径替换
                String tomPath = type + "/" + fileName;
                element.attr(attributeKey, tomPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
