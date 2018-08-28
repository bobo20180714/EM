/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.dictionary.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.page.PageView;
import com.em.common.retobj.RetObj;
import com.em.common.utils.DictUtils;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.sys.dictionary.entity.SysDictionary;
import com.em.core.sys.dictionary.service.ISysDictionaryService;
import com.em.core.sys.dictionary.service.ISysDictionaryTypeService;
import com.em.core.sys.dictionary.type.entity.SysDictionaryType;

/**
 * @ClassName: SysDictController.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:44:06
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictionaryController extends BaseController {
	// 日志记录器
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ISysDictionaryService dictService;
	@Autowired
	private ISysDictionaryTypeService dictTypeService;

	@RequestMapping("/forTreeMain")
	public String forTreeStructMain(HttpServletRequest request, String dictTypeId, String typeCode) {
		request.setAttribute("dictTypeId", dictTypeId);
		request.setAttribute("typeCode", typeCode);
		return "core/sys/sys_dict/sys_dict_forTreeMain";
	}

	@RequestMapping("/queryTreeNodesByDictType")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodesByDictType(HttpServletRequest request, String dictTypeId, String pId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TYPE_ID", dictTypeId);
		paramMap.put("PID", pId);
		return dictService.queryTreeNodesByDictType(paramMap);
	}

	@RequestMapping("/forTreeNodeUpdate")
	public String forTreeNodeUpdate(HttpServletRequest request, String id, String dictTypeId, String pId) {
		Map record = initDictItemUpdateRecord(id, dictTypeId);
		record.put("pId", pId);
		request.setAttribute("record", record);

		return "core/sys/sys_dict/sys_dict_forTreeNodeUpdate";
	}

	/**
	 * 
	 * @Title: 根据PID递归删除
	 * @author：刘宇祥
	 * @Description: 根据PID递归删除
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/rightRecursiveDelete")
	@ResponseBody
	public RetObj rightRecursiveDelete(HttpServletRequest request, String ids, String typeCode) {
		try {
			dictService.rightRecursiveDelete(ids);
			DictUtils.refreshCachedDicItemsByTypeCode(typeCode);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	// ----------分隔线-↑↑树形操作相关---------------------

	@RequestMapping("/forQueryPage")
	public String forQueryPage() {
		/*
		 * Map<String,Object> record = HttpUtils.getRequestMap(request); //
		 * 对应查询的mybatis的xml中的id必须以queryPage结尾，查询中加入select top 100 //
		 * percent，结束时并按某个字段排序，否则不能进行分页 try { pageView =
		 * dictService.queryPage(pageView, record); } catch (Exception e) {
		 * e.printStackTrace(); } request.setAttribute("pageView", pageView);
		 * request.setAttribute("record", record);
		 */
		return "core/sys/sys_dict/sys_dict_forQueryPage";
	}

	@RequestMapping("/getPageData")
	@ResponseBody
	public PageView<SysDictionaryType> getPageData(HttpServletRequest request) {
		PageView<SysDictionaryType> pageView = new PageView<SysDictionaryType>(request);

		Map<String, Object> record = HttpUtils.getRequestMap(request);
		try {
			pageView = dictTypeService.queryPage(pageView, record);
		} catch (Exception e) {
			//TODO 添加异常写入log日志
			if(logger.isDebugEnabled()){
				logger.debug(e.getMessage());
			}
			
		}

		return pageView;
	}

	//XXX
	@RequestMapping("/rightQueryPage")
	public String rightQueryPage(HttpServletRequest request, String id, String typeCode, PageView pageView) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		record.put("DIC_TYPE_ID", id);
		record.put("TYPE_ID", id);
		record.put("TYPE_CODE", typeCode);
		// request.setAttribute("pageView", pageView);
		request.setAttribute("record", record);
		return "core/sys/sys_dict/sys_dictionary_forRight";
	}
//XXX
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getChildPageData")
	@ResponseBody
	public PageView getChildPageData(String id, HttpServletRequest request) {//DIC_TYPE_ID
		PageView<Map> pageView = new PageView<Map>(request);
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		record.put("DIC_TYPE_ID", id);
		record.put("TYPE_ID", id);
		try {
			pageView = dictService.rightQueryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}
//XXX
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(String id) {
		ModelAndView mv = new ModelAndView();
		SysDictionaryType sysDictionaryType = null;
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			sysDictionaryType = dictTypeService.getSysDictionaryType(id);
		}
		mv.addObject("record", sysDictionaryType);
		mv.setViewName("core/sys/sys_dict/sys_dictionary_type_forUpdate");
		// request.setAttribute("pageNow", pageNow);
		return mv;
	}
	
	/**
	 * XXX
	 * queryByMap:检查记录是否存在
	 * insert 和 update 实体类更新
	 * @Title: update  
	 * @Description:
	 * @return: RetObj 
	 * @author: teng.min
	 * @Date 2018年5月24日
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(HttpServletRequest request,SysDictionaryType dictType) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		List<SysDictionaryType> list;
		try {
			if (StringUtils.isEmpty(dictType.getDicTypeId())) {
				// 新增
				// 此处应有检测重复相关代码
				list = dictTypeService.queryByMap(record);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "字典编码已经存在！");
				}
//				record.put("DIC_TYPE_ID", StringUtils.uniqueKey36());
				dictType.setDicTypeId(StringUtils.uniqueKey36());
				dictTypeService.insertSysDictionaryType(dictType);
			} else {
				// 修改
				//FIXME:对应mapper和 该方法中的record中都可以不用notId 就可以实现该方法！即notId多余
				record.put("notId", record.get("dicTypeId"));
				list = dictTypeService.queryByMap(record);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "字典编码已经存在！");
				}
				dictTypeService.updateSysDictionaryType(dictType);
			}
			return new RetObj(true, "操作成功", dictType.getDicTypeId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping("/rightForUpdate")
	public String rightForUpdate(HttpServletRequest request, String id, String typeId) {
		Map record1 = new HashMap();
		Map record = initDictItemUpdateRecord(id, typeId);	
		//{DIC_ID=null, TYPE_ID=f89cab63-4a35-41dc-9ae7-0b5e637e193e, TYPE_CODE=OPEN_MODE}
		/*//使用迭代器，获取key
		Iterator<String> iter = record.keySet().iterator();
		  while(iter.hasNext()){
		   String key=iter.next();
		   record1.put(key, record.get(key));
		  }*/
		
		request.setAttribute("record", record);
		return "core/sys/sys_dict/sys_dict_right_forUpdate";
	}

	/**
	 * 
	 * @Title: initDictItemUpdateData
	 * @author：刘宇祥
	 * @date：2016年9月14日下午3:12:24
	 * @Description: 初始化 修改/新增 数据
	 * @param request
	 * @param id
	 * @param typeId
	 */
	@SuppressWarnings("rawtypes")
	private Map initDictItemUpdateRecord(String id,String typeId) {
		Map record = new HashMap();
		record.put("DIC_ID", id);
		if(!StringUtils.isEmpty(id)) {
			//修改数据
			record = dictService.rightGetByMap(id);
			//FIXME 大小写转换，待改进
			record.put("dicId", record.get("DIC_ID"));
			record.put("typeId", record.get("TYPE_ID"));
			record.put("text", record.get("TEXT"));
			record.put("typeCode", record.get("TYPE_CODE"));
			record.put("value", record.get("VALUE"));
			record.put("isUse", record.get("IS_USE"));
			record.put("seq", record.get("SEQ"));
			if(record==null) {
				throw new RuntimeException("未找到相应数据");
			}
		}else {
			record.put("TYPE_ID", typeId);
			Map sysDicTypeRecord = dictService.getByMap(typeId);
			record.put("TYPE_CODE", sysDicTypeRecord.get("TYPE_CODE"));
			//变为小写是为了对应jsp页面小写字段
			record.put("dicId", record.get("DIC_ID"));
			record.put("typeId", record.get("TYPE_ID"));
			record.put("typeCode", record.get("TYPE_CODE"));
		}
		return record;
	}
	
	//	private Class<?> initDictItemUpdateRecord(String id,String typeId) {
////		Map record = new HashMap();
////		record.put("DIC_ID", id);
//		Class<?> clazz = null;
//		SysDictionary dict = null;
//		SysDictionaryType dictType = null;
//		if(!StringUtils.isEmpty(id)) {
//			//修改数据
////			record = dictService.rightGet(id);
//			dict = dictService.getSysDictionary(id);
//			if(dict==null) {
//				throw new RuntimeException("未找到相应数据");
//			}
//			clazz = dict.getClass();
//		}else {
//			dictType.setDicTypeId(typeId);
////			record.put("TYPE_ID", typeId);
//			SysDictionaryType dictType1 = dictTypeService.getSysDictionaryType(dictType.getDicTypeId());
////			Map sysDicTypeRecord = dictService.get(typeId);
////			record.put("TYPE_CODE", sysDicTypeRecord.get("TYPE_CODE"));
//			dictType.setTypeCode(dictType1.getTypeCode());
//			clazz = dictType.getClass();
//		}
//		return clazz;
//	}
//XXX： 参数值为前台的小写字段
	@SuppressWarnings("rawtypes")
	@RequestMapping("/rightUpdate")
	@ResponseBody
	public RetObj rightUpdate(HttpServletRequest request,SysDictionary dict) {
		//{timeStamp=1527238862783, isUse=0, typeId=f89cab63-4a35-41dc-9ae7-0b5e637e193e, pId=, dicId=, 
		//text=横说竖说, csrftoken=366cbf2778db4cb3a6880ff494f86b27, value=gfhh, seq=56, typeCode=OPEN_MODE}
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		List<Map> list;
		try {
			if (StringUtils.isEmpty(record.get("dicId"))) {
				// 新增
				// 此处应有检测重复相关代码
				list = dictService.rightQuery(record);//关联查询
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "字典值已经存在！");
				}
				
				dict.setDicId(StringUtils.uniqueKey36());
				dictService.insertSysDictionary(dict);
				
			} else {
				// 修改
				//FIXME:对应mapper和 该方法中的record中都可以不用notId 就可以实现该方法！即notId多余
				record.put("notId", record.get("dicId"));
				list = dictService.rightQuery(record);//关联查询
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "字典值已经存在！");
				}
				dictService.updateSysDictionary(dict);
			}
			//{timeStamp=1527238084566, isUse=1, typeId=f89cab63-4a35-41dc-9ae7-0b5e637e193e, pId=, dicId=, text=测试添加字典项, 
			//csrftoken=366cbf2778db4cb3a6880ff494f86b27, value=dfaf, seq=12, typeCode=OPEN_MODE}
			DictUtils.refreshCachedDicItemsByTypeCodeOfItem(record);
			return new RetObj(true, request, record.get("dicId"));
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	/**
	 * 
	 * @Title: 批量删除
	 * @author：yuqing
	 * @Description: 右侧表格批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/rightBatchDelete")
	@ResponseBody
	public RetObj rightBatchDelete(HttpServletRequest request, String ids, String typeCode) {
		try {
			dictService.rightBatchDelete(StringUtils.getStrJoinQuotes(ids));
			DictUtils.refreshCachedDicItemsByTypeCode(typeCode);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	/**
	 * 
	 * @Title: 批量删除
	 * @author：yuqing
	 * @Description: 字典类型批量删除数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request, String ids) {
		try {
			dictService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

}
