package com.ruoyi.project.system.archifile.service;

import java.util.List;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;

/**
 * 文件信息Service接口
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public interface IProjectArchifileService 
{
    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    public ProjectArchifile selectProjectArchifileByFileId(Long fileId);

    /**
     * 查询文件信息列表
     * 
     * @param projectArchifile 文件信息
     * @return 文件信息集合
     */
    public List<ProjectArchifile> selectProjectArchifileList(ProjectArchifile projectArchifile);

    /**
     * 新增文件信息
     * 
     * @param projectArchifile 文件信息
     * @return 结果
     */
    public int insertProjectArchifile(ProjectArchifile projectArchifile);

    /**
     * 修改文件信息
     * 
     * @param projectArchifile 文件信息
     * @return 结果
     */
    public int updateProjectArchifile(ProjectArchifile projectArchifile);

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键集合
     * @return 结果
     */
    public int deleteProjectArchifileByFileIds(String fileIds);

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    public int deleteProjectArchifileByFileId(Long fileId);

    List<ProjectArchifile> selectProjectArchifileListByArid(String archiveId);
}
