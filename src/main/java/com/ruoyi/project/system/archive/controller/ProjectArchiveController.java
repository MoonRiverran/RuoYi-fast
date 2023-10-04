package com.ruoyi.project.system.archive.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.service.IProjectArchiveService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 项目归档Controller
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Controller
@RequestMapping("/system/archive")
public class ProjectArchiveController extends BaseController
{
    private String prefix = "system/archive";

    @Autowired
    private IProjectArchiveService projectArchiveService;

    @RequiresPermissions("system:archive:view")
    @GetMapping()
    public String archive()
    {
        return prefix + "/archive";
    }

    /**
     * 查询项目归档列表
     */
    @RequiresPermissions("system:archive:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectArchive projectArchive)
    {
        startPage();
        List<ProjectArchive> list = projectArchiveService.selectProjectArchiveList(projectArchive);
        return getDataTable(list);
    }


    /**
     * 查询项目归档列表
     */
    @PostMapping("/listByPN")
    @ResponseBody
    public TableDataInfo listByPN(@RequestParam("projectId") String projectId)
    {
        startPage();
        List<ProjectArchive> list = projectArchiveService.selectProjectArchiveListByPN(projectId);
        return getDataTable(list);
    }

    /**
     * 导出项目归档列表
     */
    @RequiresPermissions("system:archive:export")
    @Log(title = "项目归档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectArchive projectArchive)
    {
        List<ProjectArchive> list = projectArchiveService.selectProjectArchiveList(projectArchive);
        ExcelUtil<ProjectArchive> util = new ExcelUtil<ProjectArchive>(ProjectArchive.class);
        return util.exportExcel(list, "项目归档数据");
    }

    /**
     * 新增项目归档
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目归档
     */
    @RequiresPermissions("system:archive:add")
    @Log(title = "项目归档", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectArchive projectArchive)
    {
        return toAjax(projectArchiveService.insertProjectArchive(projectArchive));
    }

    /**
     * 修改项目归档
     */
    @RequiresPermissions("system:archive:edit")
    @GetMapping("/edit/{archiveId}")
    public String edit(@PathVariable("archiveId") Long archiveId, ModelMap mmap)
    {
        ProjectArchive projectArchive = projectArchiveService.selectProjectArchiveByArchiveId(archiveId);
        mmap.put("projectArchive", projectArchive);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目归档
     */
    @RequiresPermissions("system:archive:edit")
    @Log(title = "项目归档", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectArchive projectArchive)
    {
        return toAjax(projectArchiveService.updateProjectArchive(projectArchive));
    }

    /**
     * 删除项目归档
     */
    @RequiresPermissions("system:archive:remove")
    @Log(title = "项目归档", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectArchiveService.deleteProjectArchiveByArchiveIds(ids));
    }
}
