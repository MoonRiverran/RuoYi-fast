package com.ruoyi.project.system.archifile.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.archifile.mapper.ProjectArchifileMapper;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.service.IProjectArchifileService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectArchifileServiceImpl implements IProjectArchifileService 
{
    @Autowired
    private ProjectArchifileMapper projectArchifileMapper;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    @Override
    public ProjectArchifile selectProjectArchifileByFileId(Long fileId)
    {
        return projectArchifileMapper.selectProjectArchifileByFileId(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param projectArchifile 文件信息
     * @return 文件信息
     */
    @Override
    public List<ProjectArchifile> selectProjectArchifileList(ProjectArchifile projectArchifile)
    {
        return projectArchifileMapper.selectProjectArchifileList(projectArchifile);
    }

    /**
     * 新增文件信息
     * 
     * @param projectArchifile 文件信息
     * @return 结果
     */
    @Override
    public int insertProjectArchifile(ProjectArchifile projectArchifile)
    {
        projectArchifile.setCreateTime(DateUtils.getNowDate());
        return projectArchifileMapper.insertProjectArchifile(projectArchifile);
    }

    /**
     * 修改文件信息
     * 
     * @param projectArchifile 文件信息
     * @return 结果
     */
    @Override
    public int updateProjectArchifile(ProjectArchifile projectArchifile)
    {
        projectArchifile.setUpdateTime(DateUtils.getNowDate());
        return projectArchifileMapper.updateProjectArchifile(projectArchifile);
    }

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchifileByFileIds(String fileIds)
    {
        return projectArchifileMapper.deleteProjectArchifileByFileIds(Convert.toStrArray(fileIds));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchifileByFileId(Long fileId)
    {
        return projectArchifileMapper.deleteProjectArchifileByFileId(fileId);
    }
}
