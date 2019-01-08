package cn.demo.app.sys.modules.models;

import cn.demo.app.sys.modules.common.FileType;
import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2019/1/8-21:38
 * @Description : 项目中的文件附件
 **/
@Table("sys_file")
@Comment("文件附件")
public class Sys_file extends BaseModel implements Serializable {

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("$me.uuid()")})
    private String id;

    @Column
    @Comment("文件大小")
    private Integer fileSize;

    @Column
    @Comment("文件名称")
    private Integer fileName;

    @Column
    @Comment("文件路径")
    private String filePath;

    @Column
    @Comment("文件类型")
    private FileType fileType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }


    public Integer getFileName() {
        return fileName;
    }

    public void setFileName(Integer fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys_file sys_file = (Sys_file) o;

        if (id != null ? !id.equals(sys_file.id) : sys_file.id != null) return false;
        if (fileSize != null ? !fileSize.equals(sys_file.fileSize) : sys_file.fileSize != null) return false;
        if (filePath != null ? !filePath.equals(sys_file.filePath) : sys_file.filePath != null) return false;
        return fileType == sys_file.fileType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileSize != null ? fileSize.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sys_file{");
        sb.append("id='").append(id).append('\'');
        sb.append(", fileSize=").append(fileSize);
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", fileType=").append(fileType);
        sb.append('}');
        return sb.toString();
    }
}
