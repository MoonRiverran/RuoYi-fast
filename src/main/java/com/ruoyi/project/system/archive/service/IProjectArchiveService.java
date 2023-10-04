package com.ruoyi.project.system.archive.service;

import java.util.List;
import com.ruoyi.project.system.archive.domain.ProjectArchive;

/**
 * 项目归档Service接口
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public interface IProjectArchiveService 
{
    /**
     * 查询项目归档
     * 
     * @param archiveId 项目归档主键
     * @return 项目归档
     */
    public ProjectArchive selectProjectArchiveByArchiveId(Long archiveId);

    /**
     * 查询项目归档列表
     * 
     * @param projectArchive 项目归档
     * @return 项目归档集合
     */
    public List<ProjectArchive> selectProjectArchiveList(ProjectArchive projectArchive);

    /**
     * 通过projectNumber查询项目归档列表
     *
     * @param projectNumber 项目归档
     * @return 项目归档集合
     */
    public List<ProjectArchive> selectProjectArchiveListByPN(String projectId);

    /**
     * 新增项目归档
     * 
     * @param projectArchive 项目归档
     * @return 结果
     */
    public int insertProjectArchive(ProjectArchive projectArchive);

    /**
     * 修改项目归档
     * 
     * @param projectArchive 项目归档
     * @return 结果
     */
    public int updateProjectArchive(ProjectArchive projectArchive);

    /**
     * 批量删除项目归档
     * 
     * @param archiveIds 需要删除的项目归档主键集合
     * @return 结果
     */
    public int deleteProjectArchiveByArchiveIds(String archiveIds);

    /**
     * 删除项目归档信息
     * 
     * @param archiveId 项目归档主键
     * @return 结果
     */
    public int deleteProjectArchiveByArchiveId(Long archiveId);
}
