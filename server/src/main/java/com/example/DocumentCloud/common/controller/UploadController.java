package com.autumn.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * UploadController
 * @author: 秋雨
 * 2021-02-01 16:32
 **/
@Controller
@RequestMapping(value = "/uploadController", method = { RequestMethod.GET,RequestMethod.POST })
public class UploadController {

    /**
     * 上传文件
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST })
    public String Upload(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        req.setCharacterEncoding("utf-8");
        List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
        MultipartFile file = files.get(0);
        String pathfolder = req.getParameter("pathfolder");

        if (file.isEmpty()) {
            String json = "{\"success\":false,\"message\":\"file is empty!\"}";
            resp.getOutputStream().write(json.getBytes("utf-8"));
        } else {
            Date time =new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateNowStr = sdf.format(time);
            //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
            String folderPath = File.separator+"upload"+File.separator+pathfolder+File.separator+dateNowStr;
            String savePath = req.getSession().getServletContext().getRealPath(folderPath);
            File fileFolder = new File(savePath);
            //判断上传文件的保存目录是否存在
            if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                //创建目录
                fileFolder.mkdirs();
            }
            //文件保存目录
            String filesavepath = savePath+File.separator+file.getOriginalFilename();

            //IO流写入
            OutputStream outputStream=new FileOutputStream(filesavepath);
            InputStream inputStream = file.getInputStream();
            //FileUtil.copyStream(inputStream, outputStream);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes))!=-1){    //读取到文件末尾的话可以读到-1
                outputStream.write(bytes,0,len);
            }
            //flush输出流
            outputStream.flush();
            //关闭流文件
            inputStream.close();
            outputStream.close();

            //图片存储路径(web用)
            String webPath = folderPath+File.separator+file.getOriginalFilename();
            //因为URLEncoder.encode会把空格改为+号,要手动把加号改为%20
            webPath = URLEncoder.encode(webPath,"utf-8").replace("+", "%20");
            String json = "{\"success\":true,\"message\":\"文件上传成功！\",\"path\":\""+ webPath +"\"}";

            resp.resetBuffer();
            resp.setContentType("text/html;charset=UTF-8");
            resp.getOutputStream().write(json.getBytes("utf-8"));
            resp.getOutputStream().flush();
        }
        return null;
    }
}