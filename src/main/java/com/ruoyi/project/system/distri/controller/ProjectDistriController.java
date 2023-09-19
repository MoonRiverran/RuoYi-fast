package com.ruoyi.project.system.distri.controller;

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
import com.ruoyi.project.system.distri.domain.ProjectDistri;
import com.ruoyi.project.system.distri.service.IProjectDistriService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目下发Controller
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Controller
@RequestMapping("/system/distri")
public class ProjectDistriController extends BaseController
{
    private String prefix = "system/distri";

    @Autowired
    private IProjectDistriService projectDistriService;

    @RequiresPermissions("system:distri:view")
    @GetMapping()
    public String distri()
    {
        return prefix + "/distri";
    }

    /**
     * 查询项目下发列表
     */
    @RequiresPermissions("system:distri:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectDistri projectDistri)
    {
        startPage();
        List<ProjectDistri> list = projectDistriService.selectProjectDistriList(projectDistri);
        return getDataTable(list);
    }

    /**
     * 导出项目下发列表
     */
    @RequiresPermissions("system:distri:export")
    @Log(title = "项目下发", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectDistri projectDistri)
    {
        List<ProjectDistri> list = projectDistriService.selectProjectDistriList(projectDistri);
        ExcelUtil<ProjectDistri> util = new ExcelUtil<ProjectDistri>(ProjectDistri.class);
        return util.exportExcel(list, "项目下发数据");
    }

    /**
     * 新增项目下发
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目下发
     */
    @RequiresPermissions("system:distri:add")
    @Log(title = "项目下发", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectDistri projectDistri)
    {
        return toAjax(projectDistriService.insertProjectDistri(projectDistri));
    }

    /**
     * 修改项目下发
     */
    @RequiresPermissions("system:distri:edit")
    @GetMapping("/edit/{projectId}")
    public String edit(@PathVariable("projectId") Long projectId, ModelMap mmap)
    {
        ProjectDistri projectDistri = projectDistriService.selectProjectDistriByProjectId(projectId);
        mmap.put("projectDistri", projectDistri);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目下发
     */
    @RequiresPermissions("system:distri:edit")
    @Log(title = "项目下发", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectDistri projectDistri)
    {
        return toAjax(projectDistriService.updateProjectDistri(projectDistri));
    }

    /**
     * 删除项目下发
     */
    @RequiresPermissions("system:distri:remove")
    @Log(title = "项目下发", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectDistriService.deleteProjectDistriByProjectIds(ids));
    }
}
