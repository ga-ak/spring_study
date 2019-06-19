package com.myspring.pro28.ex02;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class FileDownloadController {
    private static String CURP_IMAGE_REPO_PATH = "/Users/cheolho/Programing/resources";

    @RequestMapping("/download")
    protected void download(@RequestParam("imageFileName") String imageFileName, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        OutputStream out = resp.getOutputStream();
        String downFile = CURP_IMAGE_REPO_PATH + "/" + imageFileName;
        File file = new File(downFile);

        int lastIndex = imageFileName.lastIndexOf(".");
        String fileName = imageFileName.substring(0, lastIndex);
        File thumbnail = new File(CURP_IMAGE_REPO_PATH + "/thumbnail/" + fileName + ".png");

        System.out.println("download : "+fileName);

        if (file.exists()) {
            System.out.println("download : file.exists()");
            thumbnail.getParentFile().mkdirs();
            Thumbnails.of(file).size(50,50).outputFormat("png").toFile(thumbnail);
        }

        FileInputStream in = new FileInputStream(thumbnail);
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
