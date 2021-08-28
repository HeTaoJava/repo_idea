package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {
        //封装课时信息
        Course course = new Course();

        //补全课时信息
        //BeanUtils.copyProperties(course,courseVo);
            BeanUtils.copyProperties(courseVo,course);
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程

        courseMapper.saveCourse(course);
        //获取新插入数据的id值
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,teacher       );
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        courseMapper.saveTeacher(teacher);

    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        //封装课时信息
        Course course = new Course();

        //补全课时信息
        BeanUtils.copyProperties(courseVo,course);
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);


        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVo,teacher       );
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(course.getId());
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int courseId, int status) {
        //封装课时信息
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }
}
