package com.ruoyi.project.system.distri.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import com.ruoyi.project.system.distri.domain.ProjectDistri;
import com.ruoyi.project.system.distri.mapper.ProjectDistriMapper;
import com.ruoyi.project.system.distri.service.IProjectDistriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        projectDistriMapper.updateMemo();
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
        int count = isPNExists(projectDistri.getProjectNumber());
        if(count > 0){
            throw new ServiceException("已存在相同的项目号，请确认您要输入的项目号。");
        }
        //根据entityFolderName查询baseDirectory是否有相同名字的文件夹，如果有的话给出校验提示
        boolean folderExists = isFolderExists(entityFolderName);
        if (folderExists) {
            throw new ServiceException("文件夹已存在，请更改文件夹名称。");
        }
        String entityFolderPath = baseDirectory + File.separator + entityFolderName;
        File entityFolder = new File(entityFolderPath);
        entityFolder.mkdirs();

        projectDistri.setCreateBy(getLoginName());
        projectDistri.setCreateTime(DateUtils.getNowDate());
        projectDistri.setMemo("图纸未归档完全");
        int rowsInserted  = projectDistriMapper.insertProjectDistri(projectDistri);
        String projectNumber = projectDistri.getProjectNumber();

        //如果成功创建项目则生成对应的默认7个文件夹
        if(rowsInserted>0){
            Long projectid = projectDistri.getProjectId();
            // 创建7个子文件夹
            String[] subfolderNames = {"1-项目启动", "2-图纸下发", "3-厂内调试", "4-随机资料", "5-设计更改", "6-程序归档", "7-程序评审文件"};
            // 创建7个子文件各自应该上传文件数量的Map映射
            Map<String, Integer> folderNameToCount = new HashMap<>();
            folderNameToCount.put("1-项目启动", 6);
            folderNameToCount.put("2-图纸下发", 6);
            folderNameToCount.put("3-厂内调试", 4);
            folderNameToCount.put("4-随机资料", 2);
            folderNameToCount.put("5-设计更改", 2);
            folderNameToCount.put("6-程序归档", 1);
            folderNameToCount.put("7-程序评审文件", 5);

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
                    int fileNum = folderNameToCount.getOrDefault(subfolderName, 0);
                projectArchive.setUpfileNum(fileNum);
                projectArchive.setActualfileNum(0);
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

    private int isPNExists(String projectNumber) {
        int count = projectDistriMapper.selectProjectDistriByPN(projectNumber);
        return count;
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
            throw new ServiceException("删除失败，项目中有对应的归档文件信息未删除！");
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
