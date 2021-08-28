package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper mapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseID) {
        return mapper.findSectionAndLessonByCourseId(courseID);
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return mapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection section) {
        //1.补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        mapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        courseSection.setUpdateTime(new Date());

        mapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setId(id);
        courseSection.setUpdateTime(new Date());

        //调用mapper
        mapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //1.补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        System.out.println(courseLesson);
        //2.调用mapper
        mapper.saveLesson(courseLesson);
    }
}
