package com.ruoyi.project.system.archifile.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.archifile.domain.ProjectArchifile;
import com.ruoyi.project.system.archifile.mapper.ProjectArchifileMapper;
import com.ruoyi.project.system.archifile.service.IProjectArchifileService;
import com.ruoyi.project.system.archive.domain.ProjectArchive;
import com.ruoyi.project.system.archive.mapper.ProjectArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.security.ShiroUtils.getLoginName;
import static com.ruoyi.common.utils.security.ShiroUtils.getSysUser;

/**
 * 文件信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-18
 */
@Service
public class ProjectArchifileServiceImpl implements IProjectArchifileService {
    @Autowired
    private ProjectArchifileMapper projectArchifileMapper;

    @Autowired
    private ProjectArchiveMapper projectArchiveMapper;

    /**
     * 查询文件信息
     *
     * @param fileId 文件信息主键
     * @return 文件信息
     */
    @Override
    public ProjectArchifile selectProjectArchifileByFileId(Long fileId) {
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
        List<MultipartFile> mulfiles = projectArchifile.getFiles();
        ProjectArchive projectArchive = projectArchiveMapper.selectProjectArchiveByArchiveId(arid);
        int actulFileNum = projectArchive.getActualfileNum();
        int filecount = mulfiles.size();
        projectArchive.setActualfileNum(filecount + actulFileNum);
        projectArchiveMapper.updateProjectArchive(projectArchive);
        for (MultipartFile mulfile : mulfiles) {
            String fileName = mulfile.getOriginalFilename();
            String fileExtension = getFileExtension(fileName);
            String projectNumber = extractAlphanumeric(projectArchifile.getProjectNumber());

            String newFileName = setName(parentPathName, fileName.substring(0, 2), projectNumber) + "__" + getSysUser().getUserName() + "_" +
                    DateUtils.getDate1() + fileExtension;
            File destFile = new File(filepath, newFileName);
            try {
                mulfile.transferTo(destFile);
            }catch (Exception e){
                throw new ServiceException("文件保存失败, 请检查文件格式和大小。");
            }
            projectArchifile.setArchiveId(arid);
            projectArchifile.setFileName(newFileName);
            projectArchifile.setCreateBy(getLoginName());
            projectArchifile.setCreateTime(DateUtils.getNowDate());
            projectArchifileMapper.insertProjectArchifile(projectArchifile);
        }
        return 1;
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

    private String extractAlphanumeric(String fileName) {
        // 使用正则表达式匹配字母和数字部分
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            // 返回匹配到的部分
            return matcher.group();
        }

        return "";
    }

    private String setName(String pathName, String fileName, String number) {
        List<String> list = Arrays.asList("1-项目启动", "2-图纸下发", "3-厂内调试", "4-随机资料", "5-设计更改", "6-程序归档", "7-程序评审文件");
        List<String> one = Arrays.asList("01_总体评审会议纪要", "02_功能需求表", "03_液压系统评审会议纪要", "04_液压原理图", "05_流体原理图", "06_电气系统评审会议纪要");
        List<String> two = Arrays.asList("01_图纸资料目录", "02_BOM表", "03_电气原理图", "04_电气原理图", "05_装配图", "06_外协图");
        List<String> three = Arrays.asList("01_电气调试工艺文件", "02_调试大纲", "03_零部件图册", "04_产品使用说明书");
        List<String> four = Arrays.asList("01_电气随机原理图", "02_电气随机原理图");
        List<String> five = Arrays.asList("01_设计更改通知单", "02_整改方案");
        List<String> six = Arrays.asList("01_程序归档");
        List<String> seven = Arrays.asList("01_软件需求规格说明书", "02_软件开发计划", "03_软件设计说明", "04_软件测试说明", "05_软件测试报告");

        for (int i = 0; i < list.size(); i++) {
            if (pathName.equals(list.get(i))) {
                List<String> selectedList = null;
                switch (i) {
                    case 0:
                        selectedList = renameFiles(one,number);
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
                    for (String item : selectedList) {
                        if (item.contains(fileName)) {
                            return item;
                        }
                    }
                }
            }
        }
        return"";
    }

    private List<String> renameFiles(List<String> fileNames, String number) {
        List<String> renamedFiles = new ArrayList<>();

        for (String fileName : fileNames) {
            String baseName = fileName;
            // 使用格式化字符串构建新的文件名
            String newFileName = fileName.replaceFirst("^(\\d+)_", String.format("$1_%s", number));
            renamedFiles.add(newFileName);
        }

        return renamedFiles;
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
    public int deleteProjectArchifileByFileIds(String fileIds) {
        // 使用 Stream 和 Arrays 来处理文件ID字符串的拆分和处理
        List<Long> fileIdList = Arrays.stream(fileIds.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        if (fileIdList.isEmpty()) {
            // 如果文件ID列表为空，无需继续执行，直接返回0
            return 0;
        }

        // 获取第一个文件ID，用于后续的更新操作
        Long firstFileId = fileIdList.get(0);

        // 批量查询文件信息
        List<ProjectArchifile> projectArchifiles = projectArchifileMapper.selectProjectArchifilesByFileIds(fileIdList);
        Long arid = projectArchifiles.get(0).getArchiveId();
        ProjectArchive pa = projectArchiveMapper.selectProjectArchiveByArchiveId(arid);
        // 删除文件和记录
        for (ProjectArchifile projectArchifile : projectArchifiles) {
            deleteFile(projectArchifile.getFilePath(), projectArchifile.getFileName());
        }

        // 计算新的文件数量
        int delFileNum = fileIdList.size();
        int actualNum = pa.getActualfileNum();
        int newFileNum = Math.max(0, actualNum - delFileNum);
        pa.setActualfileNum(newFileNum);
        // 更新实际上传文件数量
        projectArchiveMapper.updateProjectArchive(pa);

        // 批量删除文件记录
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

    private void deleteFile(String filePath, String fileName) {
        // 构建文件的完整路径
        String fullFilePath = filePath + File.separator + fileName;

        // 使用java.io.File类删除文件
        File file = new File(fullFilePath);

        // 检查文件是否存在，然后删除
        if (file.exists()) {
            if (!file.delete()) {
                throw new ServiceException("无法删除文件: " + fullFilePath);
            }
        } else {
            throw new ServiceException("文件不存在: " + fullFilePath);
        }
    }
}
