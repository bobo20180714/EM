/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.em.common.page.PageView;
import com.em.common.service.IBaseService;
import com.em.core.sys.file.entity.SysFile;

/**
 * @ClassName: ISysFileService.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:46:04
 */

public interface ISysFileService{
	
	int batchInsert(List list);

	/**
	 * @Title: queryPage  
	 * @Description:
	 * @return: PageView<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	PageView<SysFile> queryPage(PageView<SysFile> pageView,
			Map<String, Object> paramMap);

	/**
	 * @Title: querySysFile  
	 * @Description:
	 * @return: List<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysFile> querySysFile(SysFile sysFile);

	/**
	 * @Title: querySysFileByMap  
	 * @Description:
	 * @return: List<SysFile> 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	List<SysFile> querySysFileByMap(Map paramMap);

	/**
	 * @Title: getSysFile  
	 * @Description:
	 * @return: SysFile 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	SysFile getSysFile(String sysFileId);

	/**
	 * @Title: addSysFile  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void addSysFile(SysFile sysFile);

	/**
	 * @Title: delSysFile  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void delSysFile(String sysFileId);

	/**
	 * @Title: updateSysFile  
	 * @Description:
	 * @return: void 
	 * @author: teng.min
	 * @Date 2018年5月23日
	 */
	void updateSysFile(SysFile fileInfo);

}
