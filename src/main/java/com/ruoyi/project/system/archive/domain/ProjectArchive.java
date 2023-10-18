package com.ruoyi.project.system.archive.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 项目归档对象 project_archive
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public class ProjectArchive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 归档文件夹id */
    private Long archiveId;

    /** 归档文件夹名称 */
    @Excel(name = "归档文件夹名称")
    private String archiveName;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** 部门id */
    private Long deptId;

    /** 项目号 */
    @Excel(name = "项目号")
    private String projectNumber;

    /** 文件夹路径 */
    @Excel(name = "文件夹路径")
    private String filePath;

    public void setArchiveId(Long archiveId)
    {
        this.archiveId = archiveId;
    }

    public Long getArchiveId()
    {
        return archiveId;
    }
    public void setArchiveName(String archiveName)
    {
        this.archiveName = archiveName;
    }

    public String getArchiveName()
    {
        return archiveName;
    }
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectNumber(String projectNumber)
    {
        this.projectNumber = projectNumber;
    }
    public String getProjectNumber()
    {
        return projectNumber;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("archiveId", getArchiveId())
            .append("archiveName", getArchiveName())
            .append("projectId", getProjectId())
            .append("projectNumber", getProjectNumber())
            .append("filePath", getFilePath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
