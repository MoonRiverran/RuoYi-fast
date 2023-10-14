package com.ruoyi.project.system.archifile.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件信息对象 project_archifile
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public class ProjectArchifile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** project_archive表archive_id的外键 */
    @Excel(name = "project_archive表archive_id的外键")
    private Long archiveId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getFileId()
    {
        return fileId;
    }
    public void setArchiveId(Long archiveId)
    {
        this.archiveId = archiveId;
    }

    public Long getArchiveId()
    {
        return archiveId;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("archiveId", getArchiveId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("file", getFile())
            .toString();
    }
}
