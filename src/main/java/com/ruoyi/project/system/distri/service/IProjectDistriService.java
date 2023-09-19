package com.ruoyi.project.system.distri.service;

import java.util.List;
import com.ruoyi.project.system.distri.domain.ProjectDistri;

/**
 * 项目下发Service接口
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public interface IProjectDistriService 
{
    /**
     * 查询项目下发
     * 
     * @param projectId 项目下发主键
     * @return 项目下发
     */
    public ProjectDistri selectProjectDistriByProjectId(Long projectId);

    /**
     * 查询项目下发列表
     * 
     * @param projectDistri 项目下发
     * @return 项目下发集合
     */
    public List<ProjectDistri> selectProjectDistriList(ProjectDistri projectDistri);

    /**
     * 新增项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    public int insertProjectDistri(ProjectDistri projectDistri);

    /**
     * 修改项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    public int updateProjectDistri(ProjectDistri projectDistri);

    /**
     * 批量删除项目下发
     * 
     * @param projectIds 需要删除的项目下发主键集合
     * @return 结果
     */
    public int deleteProjectDistriByProjectIds(String projectIds);

    /**
     * 删除项目下发信息
     * 
     * @param projectId 项目下发主键
     * @return 结果
     */
    public int deleteProjectDistriByProjectId(Long projectId);
}
