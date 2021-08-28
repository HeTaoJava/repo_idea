package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 根据课程id查询关联的章节信息及课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseID);

    /**
     * 回显章节对应课时信息
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 保存章节
     */
    public void saveSection(CourseSection section);

    /**
     * 更新章节
     *
     * @param courseSection
     */
    void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     */
    void updateSectionStatus(CourseSection courseSection);

    /**
     * 新建课时信息
     */
    void saveLesson(CourseLesson courseLesson);
}
