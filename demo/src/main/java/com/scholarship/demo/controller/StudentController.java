package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.*;
import com.scholarship.demo.response.Result;
import com.scholarship.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/student")
@Controller
public class StudentController {


    @Autowired
    private StudentService studentService;


    @RequestMapping("/currentProcess")
    @ResponseBody
    public String currentPorcess(@RequestBody LoginDto loginDto){
        CurrentProcessRep result = studentService.currentPorcess(loginDto.getAccount());
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody StudentRequestDto studentRequestDto) {
        String result = studentService.studentSave(studentRequestDto);
        if(result.equals("指导教师不存在")){
            return JSON.toJSONString(new Result(405,"-",result));
        }else{
            return JSON.toJSONString(new Result(200,"-",result));
        }
    }


    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody StudentRequestDto studentRequestDto) {
        String result = studentService.studentApply(studentRequestDto);
        if(result.equals("指导教师不存在")){
            return JSON.toJSONString(new Result(405,"-",result));
        }else{
            return JSON.toJSONString(new Result(200,"-",result));
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody Key key){
        Map<String, Object> resultMap = studentService.edit(key);
        return JSON.toJSONString(new Result(200,"-",resultMap));
    }

    @RequestMapping("/myProject")
    @ResponseBody
    public String myProject(@RequestBody LoginDto leaderAccount){
        List<MyProjectDto> result = studentService.myProject(leaderAccount.getAccount());
        return JSON.toJSONString(new Result(200,"-",result));
    }

//    @RequestMapping("/currentProcess")
//    @ResponseBody
//    public String currentProcess(@RequestBody LoginDto leaderAccount){
//        MyProjectDto myProjectDto = studentService.myProject(leaderAccount.getAccount());
//        return JSON.toJSONString(new Result(200,"-",myProjectDto.getCurrentProgress()));
//    }

//    /**
//     * 文件上传
//     * @param file
//     * @return
//     * @throws IOException
//     */
//    @RequestMapping("/update")
//    public String update(MultipartFile file) throws IOException {
//        /**
//         * 注意：file名字要和参入的name一致
//         */
//        File files = new File("StudentController1");
//        String folder = files.getCanonicalPath();
//
//        System.out.println("file name=" + file.getName());
//        System.out.println("origin file name=" + file.getOriginalFilename());
//        System.out.println("file size=" + file.getSize());
//
//        /**
//         * 这里是写到本地
//         * 还可以用file.getInputStrem()
//         * 获取输入流，然后存到阿里oss。。或七牛。。
//         */
//        File localFile = new File(folder, System.currentTimeMillis()+"");
//
//        //把传入的文件写到本地文件
//        file.transferTo(localFile);
//
//        FileInfo fileInfo  = new FileInfo(localFile.getCanonicalPath());
//
//        //getAbsolutePath为绝对路径
//        return JSON.toJSONString(new Result(200,"-",fileInfo.getPath()));
//
//    }

//    @RequestMapping("/download")
//    public String downloadFile(String leaderAccount,HttpServletRequest request, HttpServletResponse response) {
//        File files = new File("StudentController1");
//        try {
//            String folder = files.getCanonicalPath();
//
//        String fileName = studentService.selectById(leaderAccount);//文件名
//
//        //String fileName = "dalaoyang.jpeg";// 文件名
//        if (fileName != null) {
//            //设置文件路径
//            File file = new File(folder+fileName);
//            //File file = new File(realPath , fileName);
//            if (file.exists()) {
//                // 设置强制下载不打开
//                response.setContentType("application/force-download");
//                // 设置文件名
//                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    return JSON.toJSONString(new Result(200,"-","下载成功"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return JSON.toJSONString(new Result(404,"-","下载失败"));
//    }


    @RequestMapping("/update")
    @ResponseBody
    public  String uploadOne(HttpServletRequest request,@RequestParam("file")MultipartFile file)
    {
        Map map=new HashMap();
        try {
            //上传目录地址
//            String uploadDir = request.getSession().getServletContext().getRealPath("upload");
            String uploadDir = request.getSession().getServletContext().getRealPath("/") +"upload/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists())
            {
                dir.mkdir();
            }
            //调用上传方法
            String fileName=executeUpload(uploadDir,file);
            uploadDir=uploadDir.substring(0,uploadDir.length()-1);
            map.put("fileName",fileName);
            map.put("dir",uploadDir);
        }catch (Exception e)
        {
            //打印错误堆栈信息
            e.printStackTrace();
            return JSON.toJSONString(new Result(2,"上传失败",map));
        }

        return JSON.toJSONString(new Result(1,"上传成功",map));
    }


    public String executeUpload(String uploadDir,MultipartFile file) throws Exception
    {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String filename = UUID.randomUUID() + suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);

        return filename;
    }


    //下载
    @RequestMapping(value = "/download")
    @ResponseBody
    public String downloadOne(HttpServletRequest req,HttpServletResponse response) throws IOException{
        String fileName = req.getParameter("fileName");// 设置文件名，根据业务需要替换成要下载的文件名
//        String layerId = req.getParameter("layerId");
        String downloadDir = req.getSession().getServletContext().getRealPath("/") +"upload/";
//        String savePath = req.getSession().getServletContext().getRealPath("/") +"download/";
        downloadDir=downloadDir.substring(0,downloadDir.length()-1);
        downloadDir=downloadDir+"\\";//下载目录
        String realPath=downloadDir+fileName;//
        File file = new File(realPath);//下载目录加文件名拼接成realpath
        if(file.exists()){ //判断文件父目录是否存在
//            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(new Result(2,"fail"+realPath+fileName,""));
    }
}