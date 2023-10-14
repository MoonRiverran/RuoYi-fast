package com.ruoyi.project.system.archifile.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.archifile.mapper.ProjectArchifileMapper;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.service.IProjectArchifileService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;

/**
 * 文件信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectArchifileServiceImpl implements IProjectArchifileService 
{
    @Autowired
    private ProjectArchifileMapper projectArchifileMapper;


    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    @Override
    public ProjectArchifile selectProjectArchifileByFileId(Long fileId)
    {
        return projectArchifileMapper.selectProjectArchifileByFileId(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param projectArchifile 文件信息
     * @return 文件信息
     */
    @Override
    public List<ProjectArchifile> selectProjectArchifileList(ProjectArchifile projectArchifile)
    {
        return projectArchifileMapper.selectProjectArchifileList(projectArchifile);
    }

    @Override
    public List<ProjectArchifile> selectProjectArchifileListByArid(String archiveId)
    {
        return projectArchifileMapper.selectProjectArchifileListByArid(archiveId);
    }

    /**
     * 新增文件信息
     *
     * @param projectArchifile 文件信息
     * @return 结果
     */
    @Override
    public int insertProjectArchifile(ProjectArchifile projectArchifile)
    {
        Long arid = projectArchifile.getArchiveId();
        String filepath = projectArchifile.getFilePath();
        String parentPathName = projectArchifile.getFileName();
        MultipartFile file = projectArchifile.getFile();
        String newName =  setName(parentPathName, file.getOriginalFilename());
        File destFile = new File(filepath+ File.separator + newName);
        // 将文件保存到指定路径
        try {
                file.transferTo(destFile);
            }catch (Exception e){
                throw new ServiceException("文件保存失败, 请检查文件格式和大小。");
            }
        projectArchifile.setArchiveId(arid);
        projectArchifile.setFileName(newName);
        projectArchifile.setCreateBy(getLoginName());
        projectArchifile.setCreateTime(DateUtils.getNowDate());
        return projectArchifileMapper.insertProjectArchifile(projectArchifile);
//        Long arid = projectArchifile.get(0).getArchiveId();
//        String filepath = projectArchifile.get(0).getFilePath();
//        String parentPathName = projectArchifile.get(0).getFileName();
//        for (ProjectArchifile projectArchifile : projectArchifile) {
//            MultipartFile file = projectArchifile.getFile();
//            String newName =  setName(parentPathName, file.getOriginalFilename());
//            String destFile = filepath+ File.separator + newName;
//            // 将文件保存到指定路径
//            try {
//                FileUploadUtils.upload(destFile, file);
//            }catch (Exception e){
//                throw new ServiceException("文件保存失败, 请检查文件格式和大小。");
//            }
//            projectArchifile.setArchiveId(arid);
//            projectArchifile.setFileName(newName);
//            projectArchifile.setCreateBy(getLoginName());
//            projectArchifile.setCreateTime(DateUtils.getNowDate());
//            return projectArchifileMapper.insertProjectArchifile(projectArchifile);
//        }
    }

    private String setName(String pathName, String fileName) {
        List<String> list = Arrays.asList("1-项目启动", "2-图纸下发", "3-厂内调试", "4-随机资料", "5-设计更改", "6-程序归档", "7-程序评审文件");
        List<String> one = Arrays.asList("01_a", "02_b", "03_c", "04_d", "05_e", "06_f");
        List<String> two = Arrays.asList("01_a", "02_b", "03_c", "04_d");
        List<String> three = Arrays.asList("01_a", "02_b", "03_c", "04_d");
        List<String> four = Arrays.asList("01_a", "02_b", "03_c", "04_d");
        List<String> five = Arrays.asList("01_a", "02_b", "03_c");
        List<String> six = Arrays.asList("01_a", "02_b", "03_c", "04_d", "05_e", "06_f", "07_g");
        List<String> seven = Arrays.asList("01_a", "02_b");

        for (int i = 0; i < list.size(); i++) {
            if (pathName.equals(list.get(i))) {
                List<String> selectedList = null;
                switch (i) {
                    case 0:
                        selectedList = one;
                        break;
                    case 1:
                        selectedList = two;
                        break;
                    case 2:
                        selectedList = three;
                        break;
                    case 3:
                        selectedList = four;
                        break;
                    case 4:
                        selectedList = five;
                        break;
                    case 5:
                        selectedList = six;
                        break;
                    case 6:
                        selectedList = seven;
                        break;
                }
                if (selectedList != null) {
                    for (String item : selectedList) {//获取前两个字符才行
                        if (item.contains(fileName)) {
                            return item;
                        }
                    }
                }
            }
        }
        return"";
    }


    /**
     * 修改文件信息
     * 
     * @param projectArchifile 文件信息
     * @return 结果
     */
    @Override
    public int updateProjectArchifile(ProjectArchifile projectArchifile)
    {
        projectArchifile.setUpdateBy(getLoginName());
        projectArchifile.setUpdateTime(DateUtils.getNowDate());
        return projectArchifileMapper.updateProjectArchifile(projectArchifile);
    }

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的文件信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchifileByFileIds(String fileIds)
    {
        return projectArchifileMapper.deleteProjectArchifileByFileIds(Convert.toStrArray(fileIds));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectArchifileByFileId(Long fileId)
    {
        return projectArchifileMapper.deleteProjectArchifileByFileId(fileId);
    }
}
