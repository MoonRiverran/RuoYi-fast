package com.ruoyi.project.system.archive.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.service.IProjectArchiveService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 项目归档Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectArchiveServiceImpl implements IProjectArchiveService 
{
    @Autowired
    private ProjectArchiveMapper projectArchiveMapper;

    /**
     * 查询项目归档
     * 
     * @param archiveId 项目归档主键
     * @return 项目归档
     */
    @Override
    public ProjectArchive selectProjectArchiveByArchiveId(Long archiveId)
    {
        return projectArchiveMapper.selectProjectArchiveByArchiveId(archiveId);
    }

    /**
     * 查询项目归档列表
     * 
     * @param projectArchive 项目归档
     * @return 项目归档
     */
    @Override
    public List<ProjectArchive> selectProjectArchiveList(ProjectArchive projectArchive)
    {
        return projectArchiveMapper.selectProjectArchiveList(projectArchive);
    }

    /**
     * 新增项目归档
     * 
     * @param projectArchive 项目归档
     * @return 结果
     */
    @Override
    public int insertProjectArchive(ProjectArchive projectArchive)
    {
        projectArchive.setCreateTime(DateUtils.getNowDate());
        return projectArchiveMapper.insertProjectArchive(projectArchive);
    }

    /**
     * 修改项目归档
     * 
     * @param projectArchive 项目归档
     * @return 结果
     */
    @Override
    public int updateProjectArchive(ProjectArchive projectArchive)
    {
        projectArchive.setUpdateTime(DateUtils.getNowDate());
        return projectArchiveMapper.updateProjectArchive(projectArchive);
    }

    /**
     * 批量删除项目归档
     * 
     * @param archiveIds 需要删除的项目归档主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchiveByArchiveIds(String archiveIds)
    {
        return projectArchiveMapper.deleteProjectArchiveByArchiveIds(Convert.toStrArray(archiveIds));
    }

    /**
     * 删除项目归档信息
     * 
     * @param archiveId 项目归档主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchiveByArchiveId(Long archiveId)
    {
        return projectArchiveMapper.deleteProjectArchiveByArchiveId(archiveId);
    }
}
