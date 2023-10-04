package com.ruoyi.project.system.archifile.controller;

import java.util.List;

import com.ruoyi.project.system.archive.domain.ProjectArchive;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.service.IProjectArchifileService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 文件信息Controller
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Controller
@RequestMapping("/system/archifile")
public class ProjectArchifileController extends BaseController
{
    private String prefix = "system/archifile";

    @Autowired
    private IProjectArchifileService projectArchifileService;

    @RequiresPermissions("system:archifile:view")
    @GetMapping()
    public String archifile()
    {
        return prefix + "/archifile";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("system:archifile:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectArchifile projectArchifile)
    {
        startPage();
        List<ProjectArchifile> list = projectArchifileService.selectProjectArchifileList(projectArchifile);
        return getDataTable(list);
    }

    /**
     * 查询文件信息列表
     */
    @PostMapping("/listByArid")
    @ResponseBody
    public TableDataInfo listByPN(@RequestParam("archiveId") String archiveId)
    {
        startPage();
        List<ProjectArchifile> list = projectArchifileService.selectProjectArchifileListByArid(archiveId);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("system:archifile:export")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectArchifile projectArchifile)
    {
        List<ProjectArchifile> list = projectArchifileService.selectProjectArchifileList(projectArchifile);
        ExcelUtil<ProjectArchifile> util = new ExcelUtil<ProjectArchifile>(ProjectArchifile.class);
        return util.exportExcel(list, "文件信息数据");
    }

    /**
     * 新增文件信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息
     */
    @RequiresPermissions("system:archifile:add")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectArchifile projectArchifile)
    {
        return toAjax(projectArchifileService.insertProjectArchifile(projectArchifile));
    }

    /**
     * 修改文件信息
     */
    @RequiresPermissions("system:archifile:edit")
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        ProjectArchifile projectArchifile = projectArchifileService.selectProjectArchifileByFileId(fileId);
        mmap.put("projectArchifile", projectArchifile);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息
     */
    @RequiresPermissions("system:archifile:edit")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectArchifile projectArchifile)
    {
        return toAjax(projectArchifileService.updateProjectArchifile(projectArchifile));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("system:archifile:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectArchifileService.deleteProjectArchifileByFileIds(ids));
    }
}
