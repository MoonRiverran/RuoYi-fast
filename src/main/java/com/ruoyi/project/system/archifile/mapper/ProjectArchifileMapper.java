package com.ruoyi.project.system.archifile.mapper;

import com.ruoyi.project.system.archifile.domain.ProjectArchifile;

import java.util.List;

/**
 * 文件信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-09-18
 */
public interface ProjectArchifileMapper {
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
     * 删除文件信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    public int deleteProjectArchifileByFileId(Long fileId);

    /**
     * 批量删除文件信息
     *
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectArchifileByFileIds(String[] fileIds);

    public List<ProjectArchifile> selectProjectArchifileListByArid(String archiveId);

    public List<ProjectArchifile> selectProjectArchifilesByFileIds(List<Long> fileIdList);
}
