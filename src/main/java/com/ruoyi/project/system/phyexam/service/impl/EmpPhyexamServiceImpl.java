package com.ruoyi.project.system.phyexam.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.phyexam.mapper.EmpPhyexamMapper;
import com.ruoyi.project.system.phyexam.domain.EmpPhyexam;
import com.ruoyi.project.system.phyexam.service.IEmpPhyexamService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 员工体检日期Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-16
 */
@Service
public class EmpPhyexamServiceImpl implements IEmpPhyexamService 
{
    @Autowired
    private EmpPhyexamMapper empPhyexamMapper;

    /**
     * 查询员工体检日期
     * 
     * @param userId 员工体检日期主键
     * @return 员工体检日期
     */
    @Override
    public EmpPhyexam selectEmpPhyexamByUserId(Long userId)
    {
        return empPhyexamMapper.selectEmpPhyexamByUserId(userId);
    }

    /**
     * 查询员工体检日期列表
     * 
     * @param empPhyexam 员工体检日期
     * @return 员工体检日期
     */
    @Override
    public List<EmpPhyexam> selectEmpPhyexamList(EmpPhyexam empPhyexam)
    {
        empPhyexamMapper.updateEmpTime();
        return empPhyexamMapper.selectEmpPhyexamList(empPhyexam);
    }

    /**
     * 新增员工体检日期
     * 
     * @param empPhyexam 员工体检日期
     * @return 结果
     */
    @Override
    public int insertEmpPhyexam(EmpPhyexam empPhyexam)
    {
        empPhyexam.setCreateTime(DateUtils.getNowDate());
        return empPhyexamMapper.insertEmpPhyexam(empPhyexam);
    }

    /**
     * 修改员工体检日期
     * 
     * @param empPhyexam 员工体检日期
     * @return 结果
     */
    @Override
    public int updateEmpPhyexam(EmpPhyexam empPhyexam)
    {
        empPhyexam.setUpdateTime(DateUtils.getNowDate());
        return empPhyexamMapper.updateEmpPhyexam(empPhyexam);
    }

    /**
     * 批量删除员工体检日期
     * 
     * @param userIds 需要删除的员工体检日期主键
     * @return 结果
     */
    @Override
    public int deleteEmpPhyexamByUserIds(String userIds)
    {
        return empPhyexamMapper.deleteEmpPhyexamByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除员工体检日期信息
     * 
     * @param userId 员工体检日期主键
     * @return 结果
     */
    @Override
    public int deleteEmpPhyexamByUserId(Long userId)
    {
        return empPhyexamMapper.deleteEmpPhyexamByUserId(userId);
    }
}
