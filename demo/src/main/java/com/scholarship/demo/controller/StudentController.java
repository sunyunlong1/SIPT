package com.scholarship.demo.controller;

import com.alibaba.fastjson.JSON;
import com.scholarship.demo.api.LoginDto;
import com.scholarship.demo.api.MyProjectDto;
import com.scholarship.demo.api.StudentRequestDto;
import com.scholarship.demo.model.FileInfo;
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
import java.util.Map;

@RequestMapping("/student")
@Controller
public class StudentController {



    //存放文件的路径 ，这里直接放到controller文件夹下
    //private static final String folder = "Users/syl/Public/SIPT/demo/src/main/java/com/scholarship/demo/controller";


    @Autowired
    private StudentService studentService;


    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody StudentRequestDto studentRequestDto) {
        String result = studentService.studentSave(studentRequestDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }


    @RequestMapping("/apply")
    @ResponseBody
    public String apply(@RequestBody StudentRequestDto studentRequestDto) {
        String result = studentService.studentApply(studentRequestDto);
        return JSON.toJSONString(new Result(200,"-",result));
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody LoginDto leaderAccount){
        Map<String, Object> resultMap = studentService.studentEdit(leaderAccount.getAccount());
        return JSON.toJSONString(new Result(200,"-",resultMap));
    }

    @RequestMapping("/myProject")
    @ResponseBody
    public String myProject(@RequestBody LoginDto leaderAccount){
        MyProjectDto myProjectDto = studentService.myProject(leaderAccount.getAccount());
        return JSON.toJSONString(new Result(200,"-",myProjectDto));
    }

    @RequestMapping("/currentProcess")
    @ResponseBody
    public String currentProcess(@RequestBody LoginDto leaderAccount){
        MyProjectDto myProjectDto = studentService.myProject(leaderAccount.getAccount());
        return JSON.toJSONString(new Result(200,"-",myProjectDto.getCurrentProgress()));
    }

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/update")
    public String update(MultipartFile file) throws IOException {
        /**
         * 注意：file名字要和参入的name一致
         */
        File files = new File("StudentController1");
        String folder = files.getCanonicalPath();

        System.out.println("file name=" + file.getName());
        System.out.println("origin file name=" + file.getOriginalFilename());
        System.out.println("file size=" + file.getSize());

        /**
         * 这里是写到本地
         * 还可以用file.getInputStrem()
         * 获取输入流，然后存到阿里oss。。或七牛。。
         */
        File localFile = new File(folder, System.currentTimeMillis() + ".txt");

        //把传入的文件写到本地文件
        file.transferTo(localFile);

        FileInfo fileInfo  = new FileInfo(localFile.getAbsolutePath());

        //getAbsolutePath为绝对路径
        return JSON.toJSONString(new Result(200,"-",fileInfo.getPath()));

    }

    @RequestMapping("/download")
    public String downloadFile(String leaderAccount,HttpServletRequest request, HttpServletResponse response) {
        File files = new File("StudentController1");
        try {
            String folder = files.getCanonicalPath();



        String fileName = studentService.selectById(leaderAccount);//文件名

        //String fileName = "dalaoyang.jpeg";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(folder+fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return JSON.toJSONString(new Result(200,"-","下载成功"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(new Result(404,"-","下载失败"));
    }
}