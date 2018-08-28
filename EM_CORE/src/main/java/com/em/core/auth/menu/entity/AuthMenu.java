package com.em.core.auth.menu.entity;

public class AuthMenu {
	private String menuId;

	private String menuName;

	private String menuLabel;

	private String menuCode;

	private String isLeaf;

	private String menuAction;

	private String parameter;

	private String uiEntry;

	private Integer menuLevel;

	private String rootId;

	private String parentId;

	private Integer displayOrder;

	private String imagePath;

	private String expandPath;

	private String menuSeq;

	private String openMode;

	private String appId;

	private String funcId;

	private String menuCss;

	private String iconSkin;

	//特殊字段，用于查询，与数据库表字段无对应
	private String parentMenuName;

	// 特殊字段，用于查询，与数据库表字段无对应
	private String notMenuId;
	// 特殊字段，用于查询，与数据库表字段无对应
	private String funcName;
	

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId == null ? null : menuId.trim();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel == null ? null : menuLabel.trim();
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode == null ? null : menuCode.trim();
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf == null ? null : isLeaf.trim();
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction == null ? null : menuAction.trim();
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter == null ? null : parameter.trim();
	}

	public String getUiEntry() {
		return uiEntry;
	}

	public void setUiEntry(String uiEntry) {
		this.uiEntry = uiEntry == null ? null : uiEntry.trim();
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId == null ? null : rootId.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath == null ? null : imagePath.trim();
	}

	public String getExpandPath() {
		return expandPath;
	}

	public void setExpandPath(String expandPath) {
		this.expandPath = expandPath == null ? null : expandPath.trim();
	}

	public String getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq == null ? null : menuSeq.trim();
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode == null ? null : openMode.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId == null ? null : funcId.trim();
	}

	public String getMenuCss() {
		return menuCss;
	}

	public void setMenuCss(String menuCss) {
		this.menuCss = menuCss == null ? null : menuCss.trim();
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin == null ? null : iconSkin.trim();
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getNotMenuId() {
		return notMenuId;
	}

	public void setNotMenuId(String notMenuId) {
		this.notMenuId = notMenuId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
	

}