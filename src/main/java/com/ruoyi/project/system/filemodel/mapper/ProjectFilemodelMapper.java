package com.ruoyi.project.system.filemodel.mapper;

import java.util.List;
import com.ruoyi.project.system.filemodel.domain.ProjectFilemodel;

/**
 * 项目文件模板Mapper接口
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public interface ProjectFilemodelMapper 
{
    /**
     * 查询项目文件模板
     * 
     * @param fileId 项目文件模板主键
     * @return 项目文件模板
     */
    public ProjectFilemodel selectProjectFilemodelByFileId(Long fileId);

    /**
     * 查询项目文件模板列表
     * 
     * @param projectFilemodel 项目文件模板
     * @return 项目文件模板集合
     */
    public List<ProjectFilemodel> selectProjectFilemodelList(ProjectFilemodel projectFilemodel);

    /**
     * 新增项目文件模板
     * 
     * @param projectFilemodel 项目文件模板
     * @return 结果
     */
    public int insertProjectFilemodel(ProjectFilemodel projectFilemodel);

    /**
     * 修改项目文件模板
     * 
     * @param projectFilemodel 项目文件模板
     * @return 结果
     */
    public int updateProjectFilemodel(ProjectFilemodel projectFilemodel);

    /**
     * 删除项目文件模板
     * 
     * @param fileId 项目文件模板主键
     * @return 结果
     */
    public int deleteProjectFilemodelByFileId(Long fileId);

    /**
     * 批量删除项目文件模板
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectFilemodelByFileIds(String[] fileIds);
}
