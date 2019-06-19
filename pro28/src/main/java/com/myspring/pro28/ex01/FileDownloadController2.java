package com.myspring.pro28.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class FileDownloadController2 {
    private static String CURP_IMAGE_REPO_PATH = "/Users/cheolho/Programing/resources";

    @RequestMapping("/download2")
    protected void download(@RequestParam("imageFileName") String imageFileName, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        OutputStream out = resp.getOutputStream();
        String downFile = CURP_IMAGE_REPO_PATH + "/" + imageFileName;
        File file = new File(downFile);

        resp.setHeader("Cache-Control", "no-cache");
        resp.addHeader("Content-disposition", "attachment; fileName =" + imageFileName);
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[1024 * 8];

        while (true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }
            out.write(buffer, 0, count);
        }

        in.close();
        out.close();
    }
}
