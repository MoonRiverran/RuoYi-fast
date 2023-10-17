package com.ruoyi.project.system.phyexam.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.phyexam.domain.EmpPhyexam;
import com.ruoyi.project.system.phyexam.service.IEmpPhyexamService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 员工体检日期Controller
 * 
 * @author ruoyi
 * @date 2023-10-16
 */
@Controller
@RequestMapping("/system/phyexam")
public class EmpPhyexamController extends BaseController
{
    private String prefix = "system/phyexam";

    @Autowired
    private IEmpPhyexamService empPhyexamService;

    @RequiresPermissions("system:phyexam:view")
    @GetMapping()
    public String phyexam()
    {
        return prefix + "/phyexam";
    }

    /**
     * 查询员工体检日期列表
     */
    @RequiresPermissions("system:phyexam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EmpPhyexam empPhyexam)
    {
        startPage();
        List<EmpPhyexam> list = empPhyexamService.selectEmpPhyexamList(empPhyexam);
        return getDataTable(list);
    }

    /**
     * 导出员工体检日期列表
     */
    @RequiresPermissions("system:phyexam:export")
    @Log(title = "员工体检日期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EmpPhyexam empPhyexam)
    {
        List<EmpPhyexam> list = empPhyexamService.selectEmpPhyexamList(empPhyexam);
        ExcelUtil<EmpPhyexam> util = new ExcelUtil<EmpPhyexam>(EmpPhyexam.class);
        return util.exportExcel(list, "员工体检日期数据");
    }

    /**
     * 新增员工体检日期
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存员工体检日期
     */
    @RequiresPermissions("system:phyexam:add")
    @Log(title = "员工体检日期", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EmpPhyexam empPhyexam)
    {
        return toAjax(empPhyexamService.insertEmpPhyexam(empPhyexam));
    }

    /**
     * 修改员工体检日期
     */
    @RequiresPermissions("system:phyexam:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        EmpPhyexam empPhyexam = empPhyexamService.selectEmpPhyexamByUserId(userId);
        mmap.put("empPhyexam", empPhyexam);
        return prefix + "/edit";
    }

    /**
     * 修改保存员工体检日期
     */
    @RequiresPermissions("system:phyexam:edit")
    @Log(title = "员工体检日期", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EmpPhyexam empPhyexam)
    {
        return toAjax(empPhyexamService.updateEmpPhyexam(empPhyexam));
    }

    /**
     * 删除员工体检日期
     */
    @RequiresPermissions("system:phyexam:remove")
    @Log(title = "员工体检日期", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(empPhyexamService.deleteEmpPhyexamByUserIds(ids));
    }
}
