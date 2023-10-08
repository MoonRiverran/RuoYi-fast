package com.ruoyi.project.system.filemodel.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.filemodel.mapper.ProjectFilemodelMapper;
import com.ruoyi.project.system.filemodel.domain.ProjectFilemodel;
import com.ruoyi.project.system.filemodel.service.IProjectFilemodelService;
import com.ruoyi.common.utils.text.Convert;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;

/**
 * 项目文件模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectFilemodelServiceImpl implements IProjectFilemodelService 
{
    @Autowired
    private ProjectFilemodelMapper projectFilemodelMapper;

    /**
     * 查询项目文件模板
     * 
     * @param fileId 项目文件模板主键
     * @return 项目文件模板
     */
    @Override
    public ProjectFilemodel selectProjectFilemodelByFileId(Long fileId)
    {
        return projectFilemodelMapper.selectProjectFilemodelByFileId(fileId);
    }

    /**
     * 查询项目文件模板列表
     * 
     * @param projectFilemodel 项目文件模板
     * @return 项目文件模板
     */
    @Override
    public List<ProjectFilemodel> selectProjectFilemodelList(ProjectFilemodel projectFilemodel)
    {
        return projectFilemodelMapper.selectProjectFilemodelList(projectFilemodel);
    }

    /**
     * 新增项目文件模板
     * 
     * @param projectFilemodel 项目文件模板
     * @return 结果
     */
    @Override
    public int insertProjectFilemodel(ProjectFilemodel projectFilemodel)
    {
        projectFilemodel.setCreateBy(getLoginName());
        projectFilemodel.setCreateTime(DateUtils.getNowDate());
        return projectFilemodelMapper.insertProjectFilemodel(projectFilemodel);
    }

    /**
     * 修改项目文件模板
     * 
     * @param projectFilemodel 项目文件模板
     * @return 结果
     */
    @Override
    public int updateProjectFilemodel(ProjectFilemodel projectFilemodel)
    {
        projectFilemodel.setUpdateBy(getLoginName());
        projectFilemodel.setUpdateTime(DateUtils.getNowDate());
        return projectFilemodelMapper.updateProjectFilemodel(projectFilemodel);
    }

    /**
     * 批量删除项目文件模板
     * 
     * @param fileIds 需要删除的项目文件模板主键
     * @return 结果
     */
    @Override
    public int deleteProjectFilemodelByFileIds(String fileIds)
    {
        return projectFilemodelMapper.deleteProjectFilemodelByFileIds(Convert.toStrArray(fileIds));
    }

    /**
     * 删除项目文件模板信息
     * 
     * @param fileId 项目文件模板主键
     * @return 结果
     */
    @Override
    public int deleteProjectFilemodelByFileId(Long fileId)
    {
        return projectFilemodelMapper.deleteProjectFilemodelByFileId(fileId);
    }
}
