package com.ruoyi.project.system.archifile.service.impl;

import java.io.File;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.archifile.mapper.ProjectArchifileMapper;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.service.IProjectArchifileService;
import com.ruoyi.common.utils.text.Convert;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;

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

    @Autowired
    private ProjectArchiveMapper projectArchiveMapper;


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

    @Override
    public List<ProjectArchifile> selectProjectArchifileListByArid(String archiveId)
    {
        return projectArchifileMapper.selectProjectArchifileListByArid(archiveId);
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
        Long arid = projectArchifile.getArchiveId();
        ProjectArchive projectArchive = projectArchiveMapper.selectProjectArchiveByArchiveId(arid);
        String filepath = projectArchive.getFilePath();

        // 构建上传文件的保存路径
        File uploadDir = new File(filepath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File destFile = new File(uploadDir.getAbsolutePath() + File.separator + projectArchifile.getFileName());

        // 将文件保存到指定路径
        //file.transferTo(destFile);

        projectArchifile.setArchiveId(arid);
        projectArchifile.setCreateBy(getLoginName());
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
        projectArchifile.setUpdateBy(getLoginName());
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
