package com.em.core.sys.dictionary.entity;

public class SysDictionary {
    private String dicId;

    private String text;

    private String value;

    private String typeId;

    private String typeCode;

    private String isUse;

    private String pid;

    private Integer seq;
    
    private String typeName;//sys_dict_type表
    
    private String typeIsUse;
    
    private String notId;//非数据库字段

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId == null ? null : dicId.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typeIsUse
	 */
	public String getTypeIsUse() {
		return typeIsUse;
	}

	/**
	 * @param typeIsUse the typeIsUse to set
	 */
	public void setTypeIsUse(String typeIsUse) {
		this.typeIsUse = typeIsUse;
	}

	/**
	 * @return the notId
	 */
	public String getNotId() {
		return notId;
	}

	/**
	 * @param notId the notId to set
	 */
	public void setNotId(String notId) {
		this.notId = notId;
	}
    
}