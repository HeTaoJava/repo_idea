package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseService {
    /**
     * 多条件课程列表查询
     */
    List<Course> findCourseByCondition(CourseVo courseVo);


    /**
     *添加课时及讲师信息
     */
    public void saveCourseOrTeacher(CourseVo courseVo);
    /**
     * 回显课程信息
     */
    public CourseVo findCourseById(Integer id);


    /**
     *修改课时及讲师信息
     */
    public void updateCourseOrTeacher(CourseVo courseVo);

    /**
     * 课程状态管理
     */
    void updateCourseStatus(int courseId, int status);



}
