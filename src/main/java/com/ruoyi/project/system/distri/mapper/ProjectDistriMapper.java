package com.ruoyi.project.system.distri.mapper;

import com.ruoyi.project.system.distri.domain.ProjectDistri;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 项目下发Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-18
 */
public interface ProjectDistriMapper {
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
    @Options(useGeneratedKeys = true, keyProperty = "projectId")
    public int insertProjectDistri(ProjectDistri projectDistri);

    /**
     * 修改项目下发
     *
     * @param projectDistri 项目下发
     * @return 结果
     */
    public int updateProjectDistri(ProjectDistri projectDistri);

    /**
     * 删除项目下发
     *
     * @param projectId 项目下发主键
     * @return 结果
     */
    public int deleteProjectDistriByProjectId(Long projectId);

    /**
     * 批量删除项目下发
     *
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectDistriByProjectIds(String[] projectIds);

    public int selectProjectDistriByPN(String projectNumber);

    public void updateMemo();
}
