package com.ruoyi.project.system.distri.service.impl;

import java.io.File;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.distri.mapper.ProjectDistriMapper;
import com.ruoyi.project.system.distri.domain.ProjectDistri;
import com.ruoyi.project.system.distri.service.IProjectDistriService;
import com.ruoyi.common.utils.text.Convert;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;
import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 项目下发Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectDistriServiceImpl implements IProjectDistriService 
{
    @Autowired
    private ProjectDistriMapper projectDistriMapper;

    @Autowired
    private ProjectArchiveMapper  projectArchiveMapper;

    private String baseDirectory = "D:\\ruoyi\\project";


    /**
     * 查询项目下发
     * 
     * @param projectId 项目下发主键
     * @return 项目下发
     */
    @Override
    public ProjectDistri selectProjectDistriByProjectId(Long projectId)
    {
        return projectDistriMapper.selectProjectDistriByProjectId(projectId);
    }

    /**
     * 查询项目下发列表
     * 
     * @param projectDistri 项目下发
     * @return 项目下发
     */
    @Override
    public List<ProjectDistri> selectProjectDistriList(ProjectDistri projectDistri)
    {
        Long deptid = getSysUser().getDeptId();
        if(deptid != null && deptid != 100){
            projectDistri.setDeptId(deptid);
        }
        return projectDistriMapper.selectProjectDistriList(projectDistri);
    }

    /**
     * 新增项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    @Override
    public int insertProjectDistri(ProjectDistri projectDistri) {
        // 创建主文件夹
        String entityFolderName = projectDistri.getProjectNumber() + "_" + projectDistri.getProjectLeader();
        //根据entityFolderName查询baseDirectory是否有相同名字的文件夹，如果有的话给出校验提示
        boolean folderExists = isFolderExists(entityFolderName);
//        if (folderExists) {
//            return ResponseEntity.badRequest().body("文件夹已存在，请更改文件夹名称。");
//        } else {
//            return ResponseEntity.ok("文件夹名称可用。");
//        }
        String entityFolderPath = baseDirectory + File.separator + entityFolderName;
        File entityFolder = new File(entityFolderPath);
        entityFolder.mkdirs();

        projectDistri.setCreateBy(getLoginName());
        projectDistri.setCreateTime(DateUtils.getNowDate());
        projectDistri.setMemo("图纸未归档");
        int rowsInserted  = projectDistriMapper.insertProjectDistri(projectDistri);
        String projectNumber = projectDistri.getProjectNumber();
        if(rowsInserted>0){
            Long projectid = projectDistri.getProjectId();
            // 创建7个子文件夹
            String[] subfolderNames = {"1-项目启动", "2-图纸下发", "3-厂内调试", "4-随机资料", "5-设计更改", "6-程序归档", "7-程序评审文件"};
            ProjectArchive projectArchive = new ProjectArchive();
            for (String subfolderName : subfolderNames) {
                String subfolderPath = "";
                if(subfolderName.equals("2-图纸下发")){
                    subfolderPath = entityFolderPath + File.separator + subfolderName + File.separator + "第1次下发";
                }else {
                    subfolderPath = entityFolderPath + File.separator + subfolderName;
                }
                    File subfolder = new File(subfolderPath);
                    subfolder.mkdirs();
                    projectArchive.setArchiveName(subfolderName);
                    projectArchive.setProjectId(projectid);
                    projectArchive.setProjectNumber(projectNumber);
                    projectArchive.setFilePath(subfolderPath);
                    projectArchive.setCreateBy(getLoginName());
                    projectArchive.setCreateTime(DateUtils.getNowDate());
                    projectArchiveMapper.insertProjectArchive(projectArchive);
                }
        } else{
            throw new ServiceException("新增项目失败，请检查输入数据。");
        }
        return 1;
    }

    public boolean isFolderExists(String folderName) {
        String folderPath = baseDirectory + File.separator + folderName;
        File folder = new File(folderPath);
        return folder.exists();
    }

    /**
     * 修改项目下发
     * 
     * @param projectDistri 项目下发
     * @return 结果
     */
    @Override
    public int updateProjectDistri(ProjectDistri projectDistri)
    {
        projectDistri.setUpdateBy(getLoginName());
        projectDistri.setUpdateTime(DateUtils.getNowDate());
        return projectDistriMapper.updateProjectDistri(projectDistri);
    }

    /**
     * 批量删除项目下发
     * 
     * @param projectIds 需要删除的项目下发主键
     * @return 结果
     */
    @Override
    public int deleteProjectDistriByProjectIds(String projectIds)
    {
        List<ProjectArchive> projectArchives = projectArchiveMapper.selectProjectArchiveListByPN(projectIds);
        if(projectArchives.isEmpty()){
            return projectDistriMapper.deleteProjectDistriByProjectIds(Convert.toStrArray(projectIds));
        }else{
            throw new ServiceException("新增失败！");
        }
    }

    /**
     * 删除项目下发信息
     * 
     * @param projectId 项目下发主键
     * @return 结果
     */
    @Override
    public int deleteProjectDistriByProjectId(Long projectId)
    {
        return projectDistriMapper.deleteProjectDistriByProjectId(projectId);
    }
}
