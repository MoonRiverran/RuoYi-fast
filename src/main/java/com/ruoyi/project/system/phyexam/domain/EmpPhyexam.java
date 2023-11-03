package com.ruoyi.project.system.phyexam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 员工体检日期对象 emp_phyexam
 * 
 * @author ruoyi
 * @date 2023-10-16
 */
public class EmpPhyexam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 工号 */
    @Excel(name = "工号")
    private String jobNumber;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String userName;

    /**
     * 是否过期（0否 1是）
     */
    @Excel(name = "是否过期", readConverterExp = "0=否,1=是")
    private String status;

    private String statusname;

    private String diffDate;


    /**
     * 体检时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "体检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examTime;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setJobNumber(String jobNumber)
    {
        this.jobNumber = jobNumber;
    }

    public String getJobNumber()
    {
        return jobNumber;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Date getExamTime() {
        return examTime;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getDiffDate() {
        return diffDate;
    }

    public void setDiffDate(String diffDate) {
        this.diffDate = diffDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("jobNumber", getJobNumber())
                .append("userName", getUserName())
                .append("status", getStatus())
                .append("statusName", getStatusname())
                .append("diffDate", getDiffDate())
                .append("examTime", getExamTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
