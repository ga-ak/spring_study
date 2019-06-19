package com.myspring.pro28.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
public class FileUploadController {
    private static String CURP_IMAGE_REPO_PATH = "/Users/cheolho/Programing/resources";

    @RequestMapping(value = "/form")
    public String form() {
        return "uploadForm";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(MultipartHttpServletRequest multipartReq, HttpServletResponse response) throws Exception {
        multipartReq.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap();
        Enumeration<String> enu = multipartReq.getParameterNames();

        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String value = multipartReq.getParameter(name);
            System.out.println(name+" "+value);
            map.put(name, value);
        }

        List<String> fileList = fileProcess(multipartReq);
        map.put("fileList", fileList);

        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map);
        mav.setViewName("result");

        return mav;
    }

    private List<String> fileProcess(MultipartHttpServletRequest multipartReq) throws Exception {
        List<String> result = new ArrayList<>();
        Iterator<String> fileNames = multipartReq.getFileNames();

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile mFile = multipartReq.getFile(fileName);
            String originalFileName = mFile.getOriginalFilename();
            result.add(originalFileName);

            File file = new File(CURP_IMAGE_REPO_PATH + "/" + fileName);

            System.out.println("fileName : "+fileName);
            System.out.println("originalFileName : "+originalFileName);

            if (mFile.getSize() != 0) {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                mFile.transferTo(new File(CURP_IMAGE_REPO_PATH + "/" + originalFileName));
            }
        }
        return result;
    }
}
