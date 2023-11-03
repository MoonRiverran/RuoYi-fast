package com.ruoyi.project.system.archive.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.mapper.ProjectArchifileMapper;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import com.ruoyi.project.system.archive.service.IProjectArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;
import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 项目归档Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectArchiveServiceImpl implements IProjectArchiveService {
    @Autowired
    private ProjectArchiveMapper projectArchiveMapper;

    @Autowired
    private ProjectArchifileMapper projectArchifileMapper;

    /**
     * 查询项目归档
     *
     * @param archiveId 项目归档主键
     * @return 项目归档
     */
    @Override
    public ProjectArchive selectProjectArchiveByArchiveId(Long archiveId) {
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
        Long deptid = getSysUser().getDeptId();
        if(deptid != null && deptid != 100){
            projectArchive.setDeptId(deptid);
        }
        return projectArchiveMapper.selectProjectArchiveList(projectArchive);
    }

    @Override
    public List<ProjectArchive> selectProjectArchiveListByPN(String projectId)
    {
        return projectArchiveMapper.selectProjectArchiveListByPN(projectId);
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
        File folder = new File(projectArchive.getFilePath());
        if (folder.exists()) {
            throw new ServiceException("文件夹已经存在，请检查路径是否正确。");
        } else {
            folder.mkdirs();
            projectArchive.setCreateBy(getLoginName());
            projectArchive.setCreateTime(DateUtils.getNowDate());
            return projectArchiveMapper.insertProjectArchive(projectArchive);
        }
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
        projectArchive.setUpdateBy(getLoginName());
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
        List<ProjectArchifile> files = projectArchifileMapper.selectProjectArchifileListByArid(archiveIds);
        if (!files.isEmpty()) {
            throw new ServiceException("文件夹内存在项目文件，请手动删除文件再删除子表记录！");
        }
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
