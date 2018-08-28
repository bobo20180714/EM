package com.em.core.sys.file.entity;

public class SysFile {
    private String fileId;

    private String mainId;//所属主文件主键（用于文件历史变更）

    private String fileName;

    private String fileRealName;//文件真实名（自动生成）

    private String fileType;
    //文件路径
    //FILE_LOCATION
    private String fileLocation;//文件路径

	private String uploadTime;

    private String uploader;

    private String updateTime;

    private String updater;

    private String invalidTime;//废弃时间

    private String invalider;//废弃用户

    private String status;//状态（拓展字段）0:临时文件;1:正在使用；2：历史文档；3：已经废弃
    
    private String remark;

    private Long size;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName == null ? null : fileRealName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime == null ? null : invalidTime.trim();
    }

    public String getInvalider() {
        return invalider;
    }

    public void setInvalider(String invalider) {
        this.invalider = invalider == null ? null : invalider.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
    
    /**
   	 * @return the fileLocation
   	 */
   	public String getFileLocation() {
   		return fileLocation;
   	}

   	/**
   	 * @param fileLocation the fileLocation to set
   	 */
   	public void setFileLocation(String fileLocation) {
   		this.fileLocation = fileLocation;
   	}

   	/**
   	 * @return the remark
   	 */
   	public String getRemark() {
   		return remark;
   	}

   	public void setRemark(String remark) {
   		this.remark = remark;
   	}
}