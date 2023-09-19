package com.ruoyi.project.system.filemodel.controller;

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
import com.ruoyi.project.system.filemodel.domain.ProjectFilemodel;
import com.ruoyi.project.system.filemodel.service.IProjectFilemodelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目文件模板Controller
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Controller
@RequestMapping("/system/filemodel")
public class ProjectFilemodelController extends BaseController
{
    private String prefix = "system/filemodel";

    @Autowired
    private IProjectFilemodelService projectFilemodelService;

    @RequiresPermissions("system:filemodel:view")
    @GetMapping()
    public String filemodel()
    {
        return prefix + "/filemodel";
    }

    /**
     * 查询项目文件模板列表
     */
    @RequiresPermissions("system:filemodel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectFilemodel projectFilemodel)
    {
        startPage();
        List<ProjectFilemodel> list = projectFilemodelService.selectProjectFilemodelList(projectFilemodel);
        return getDataTable(list);
    }

    /**
     * 导出项目文件模板列表
     */
    @RequiresPermissions("system:filemodel:export")
    @Log(title = "项目文件模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectFilemodel projectFilemodel)
    {
        List<ProjectFilemodel> list = projectFilemodelService.selectProjectFilemodelList(projectFilemodel);
        ExcelUtil<ProjectFilemodel> util = new ExcelUtil<ProjectFilemodel>(ProjectFilemodel.class);
        return util.exportExcel(list, "项目文件模板数据");
    }

    /**
     * 新增项目文件模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目文件模板
     */
    @RequiresPermissions("system:filemodel:add")
    @Log(title = "项目文件模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectFilemodel projectFilemodel)
    {
        return toAjax(projectFilemodelService.insertProjectFilemodel(projectFilemodel));
    }

    /**
     * 修改项目文件模板
     */
    @RequiresPermissions("system:filemodel:edit")
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        ProjectFilemodel projectFilemodel = projectFilemodelService.selectProjectFilemodelByFileId(fileId);
        mmap.put("projectFilemodel", projectFilemodel);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目文件模板
     */
    @RequiresPermissions("system:filemodel:edit")
    @Log(title = "项目文件模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectFilemodel projectFilemodel)
    {
        return toAjax(projectFilemodelService.updateProjectFilemodel(projectFilemodel));
    }

    /**
     * 删除项目文件模板
     */
    @RequiresPermissions("system:filemodel:remove")
    @Log(title = "项目文件模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectFilemodelService.deleteProjectFilemodelByFileIds(ids));
    }
}
