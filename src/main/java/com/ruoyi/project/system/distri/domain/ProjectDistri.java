package com.ruoyi.project.system.distri.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 项目下发对象 project_distri
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
public class ProjectDistri extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long projectId;

    /** 项目号 */
    @Excel(name = "项目号")
    private String projectNumber;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String projectLeader;

    /** 项目负责人等级 */
    @Excel(name = "项目负责人等级")
    private String leaderLv;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customName;

    /** 使用环境描述 */
    @Excel(name = "使用环境描述")
    private String environComment;

    /** 项目下发图纸节点 */
    @Excel(name = "项目下发图纸节点")
    private String columnType;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String projectType;

    /** 项目完成节点 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目完成节点", width = 30, dateFormat = "yyyy-MM-dd")
    private Date compleNode;

    /** 备注 */
    @Excel(name = "备注")
    private String memo;

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
    public void setProjectLeader(String projectLeader)
    {
        this.projectLeader = projectLeader;
    }

    public String getProjectLeader()
    {
        return projectLeader;
    }
    public void setLeaderLv(String leaderLv)
    {
        this.leaderLv = leaderLv;
    }

    public String getLeaderLv()
    {
        return leaderLv;
    }
    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getCustomName()
    {
        return customName;
    }
    public void setEnvironComment(String environComment)
    {
        this.environComment = environComment;
    }

    public String getEnvironComment()
    {
        return environComment;
    }
    public void setColumnType(String columnType)
    {
        this.columnType = columnType;
    }

    public String getColumnType()
    {
        return columnType;
    }
    public void setProjectType(String projectType)
    {
        this.projectType = projectType;
    }

    public String getProjectType()
    {
        return projectType;
    }
    public void setCompleNode(Date compleNode)
    {
        this.compleNode = compleNode;
    }

    public Date getCompleNode()
    {
        return compleNode;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getMemo()
    {
        return memo;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectNumber", getProjectNumber())
            .append("projectLeader", getProjectLeader())
            .append("leaderLv", getLeaderLv())
            .append("customName", getCustomName())
            .append("environComment", getEnvironComment())
            .append("columnType", getColumnType())
            .append("projectType", getProjectType())
            .append("compleNode", getCompleNode())
            .append("memo", getMemo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
