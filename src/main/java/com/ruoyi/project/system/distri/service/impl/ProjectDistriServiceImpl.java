package com.ruoyi.project.system.distri.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.distri.mapper.ProjectDistriMapper;
import com.ruoyi.project.system.distri.domain.ProjectDistri;
import com.ruoyi.project.system.distri.service.IProjectDistriService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 项目下发Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectDistriServiceImpl implements IProjectDistriService 
{
    @Autowired
    private ProjectDistriMapper projectDistriMapper;

    /**
     * 查询项目下发
     * 
     * @param projectId 项目下发主键
     * @return 项目下发
     */
    @Override
    public ProjectDistri selectProjectDistriByProjectId(Long projectId)
    {
        return projectDistriMapper.selectProjectDistriByProjectId(projectId);
    }

    /**
     * 查询项目下发列表
     * 
     * @param projectDistri 项目下发
     * @return 项目下发
     */
    @Override
    public List<ProjectDistri> selectProjectDistriList(ProjectDistri projectDistri)
    {
        return projectDistriMapper.selectProjectDistriList(projectDistri);
    }

    /**
     * 新增项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    @Override
    public int insertProjectDistri(ProjectDistri projectDistri)
    {
        projectDistri.setCreateTime(DateUtils.getNowDate());
        return projectDistriMapper.insertProjectDistri(projectDistri);
    }

    /**
     * 修改项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    @Override
    public int updateProjectDistri(ProjectDistri projectDistri)
    {
        projectDistri.setUpdateTime(DateUtils.getNowDate());
        return projectDistriMapper.updateProjectDistri(projectDistri);
    }

    /**
     * 批量删除项目下发
     * 
     * @param projectIds 需要删除的项目下发主键
     * @return 结果
     */
    @Override
    public int deleteProjectDistriByProjectIds(String projectIds)
    {
        return projectDistriMapper.deleteProjectDistriByProjectIds(Convert.toStrArray(projectIds));
    }

    /**
     * 删除项目下发信息
     * 
     * @param projectId 项目下发主键
     * @return 结果
     */
    @Override
    public int deleteProjectDistriByProjectId(Long projectId)
    {
        return projectDistriMapper.deleteProjectDistriByProjectId(projectId);
    }
}
