package com.ruoyi.project.system.phyexam.mapper;

import java.util.List;
import com.ruoyi.project.system.phyexam.domain.EmpPhyexam;

/**
 * 员工体检日期Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-16
 */
public interface EmpPhyexamMapper 
{
    /**
     * 查询员工体检日期
     * 
     * @param userId 员工体检日期主键
     * @return 员工体检日期
     */
    public EmpPhyexam selectEmpPhyexamByUserId(Long userId);

    /**
     * 查询员工体检日期列表
     * 
     * @param empPhyexam 员工体检日期
     * @return 员工体检日期集合
     */
    public List<EmpPhyexam> selectEmpPhyexamList(EmpPhyexam empPhyexam);

    /**
     * 新增员工体检日期
     * 
     * @param empPhyexam 员工体检日期
     * @return 结果
     */
    public int insertEmpPhyexam(EmpPhyexam empPhyexam);

    /**
     * 修改员工体检日期
     * 
     * @param empPhyexam 员工体检日期
     * @return 结果
     */
    public int updateEmpPhyexam(EmpPhyexam empPhyexam);

    /**
     * 删除员工体检日期
     * 
     * @param userId 员工体检日期主键
     * @return 结果
     */
    public int deleteEmpPhyexamByUserId(Long userId);

    /**
     * 批量删除员工体检日期
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmpPhyexamByUserIds(String[] userIds);

    public void updateEmpTime();
}
