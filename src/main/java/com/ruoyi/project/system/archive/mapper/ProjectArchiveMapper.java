package com.ruoyi.project.system.archive.mapper;

import com.ruoyi.project.system.archive.domain.ProjectArchive;

import java.util.List;

/**
 * 项目归档Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-18
 */
public interface ProjectArchiveMapper {
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
     * 删除项目归档
     * 
     * @param archiveId 项目归档主键
     * @return 结果
     */
    public int deleteProjectArchiveByArchiveId(Long archiveId);

    /**
     * 批量删除项目归档
     * 
     * @param archiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectArchiveByArchiveIds(String[] archiveIds);

    public List<ProjectArchive> selectProjectArchiveListByPN(String projectId);

}
