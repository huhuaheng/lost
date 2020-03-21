package com.tomhuwd.lost.controller;


import com.tomhuwd.lost.common.Base.BaseController;
import com.tomhuwd.lost.common.Config;
import com.tomhuwd.lost.core.CoreCode;
import com.tomhuwd.lost.util.FileUtils;
import com.tomhuwd.lost.util.KeyUtils;
import com.tomhuwd.lost.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import com.tomhuwd.lost.domain.Lost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class CodeController extends BaseController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/code/getAll")
    public void getAll(Lost lost, HttpServletResponse response) throws FileNotFoundException {
        String path = CoreCode.allCode(lost.getUrlArray());
        String fileName = KeyUtils.genUniqueKey() + ".zip";
        String zipPath = Config.zifPath + "\\" + fileName;

        ZipUtils.toZip(path, new FileOutputStream(new File(zipPath)), true);
        try {
            genCode(response, FileUtils.File2byte(new File(zipPath)), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data, String fileName) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }


}
