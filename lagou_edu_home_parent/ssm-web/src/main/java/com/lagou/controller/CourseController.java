package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 多条件课程列表查询
     *
     * @param courseVo
     * @return
     */

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo) {
        List<Course> list = courseService.findCourseByCondition(courseVo);
        System.out.println(list);
        return new ResponseResult(true, 200, "响应成功", list);

    }

    /**
     * 课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpLoad(MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
        //3.获取文件名
        String originalFilename = file.getOriginalFilename();
        //4,生成新的文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        System.out.println(filePath);
        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        file.transferTo(filePath);
        //6.进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        return new ResponseResult(true, 200, "响应成功", map);
    }

    /**
     * 新增课程信息及讲师信息
     */
    @RequestMapping("saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) {
        if (courseVo.getId() == null) {//新增操作
            courseService.saveCourseOrTeacher(courseVo);
            return new ResponseResult(true, 200, "新增成功", null);
        } else {
            courseService.updateCourseOrTeacher(courseVo);
            return new ResponseResult(true, 200, "修改成功", null);
        }


    }

    /**
     * 回显课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        CourseVo courseVo = courseService.findCourseById(id);
        return new ResponseResult(true, 200, "响应成功", courseVo);

    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status) {

        courseService.updateCourseStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true, 200, "响应成功", map);
    }

}
