<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/component/ntko/OfficeContorlFunctions.js"></script>
<script type="text/javascript">
	$(function(){
		intializePage("http://localhost/testntko/uploadOfficeFile/1.officefile.新建word文档.xls");
		alert(OFFICE_CONTROL_OBJ.activeDocument);
	});
	//关闭时提醒保存需要借鉴ntko示例进行二次开发
</script>
<div class=" tabPage">
  <div class="row">
  	<div clss="col-md-12">
  		<div id="officecontrol">
				<SPAN STYLE="color:red">如果不能装载控件。请确认你可以连接网络或者检查浏览器的选项中安全设置。
					<a href="http://www.ntko.com/control/officecontrol/officecontrol.zip">下载演示产品</a>
				</SPAN>
                <!-- 用来产生编辑状态的ActiveX控件的JS脚本-->
				<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->
				<object id="TANGER_OCX" classid="clsid:A39F1330-3322-4a1d-9BF0-0BA2BB90E970" 
				codebase="http://www.ntko.com/control/officecontrol/OfficeControl.cab#version=5,0,2,7" width="100%" height="100%">
				<param name="IsUseUTF8URL" value="-1">
				<param name="IsUseUTF8Data" value="-1">
				<param name="BorderStyle" value="1">
				<param name="BorderColor" value="14402205">
				<param name="TitlebarColor" value="15658734">
				<param name="TitlebarTextColor" value="0">
				<param name="MenubarColor" value="14402205">
				<param name="MenuButtonColor" VALUE="16180947">
				<param name="MenuBarStyle" value="3">
				<param name="MenuButtonStyle" value="7">
				<param name="WebUserName" value="NTKO">
				<param name="Caption" value="NTKO OFFICE文档控件示例演示 http://www.ntko.com">
				<SPAN STYLE="color:red">不能装载文档控件。请确认你可以连接网络或者检查浏览器的选项中安全设置。<a href="http://www.ntko.com/control/officecontrol/officecontrol.zip">安装演示产品</a></SPAN>
				</object>
                <div id=statusBar style="height:20px;width:100%;background-color:#c0c0c0;font-size:12px;"></div>
								<script language="JScript" for=TANGER_OCX event="OnDocumentClosed()">
									setFileOpenedOrClosed(false);
								</script>
								<script language="JScript" for="TANGER_OCX" event="OnDocumentOpened(TANGER_OCX_str,TANGER_OCX_obj)">
									
									//获取文档控件中打开的文档的文档类型
									switch (OFFICE_CONTROL_OBJ.doctype)
									{
										case 1:
											fileType = "Word.Document";
											fileTypeSimple = "wrod";
											break;
										case 2:
											fileType = "Excel.Sheet";
											fileTypeSimple="excel";
											break;
										case 3:
											fileType = "PowerPoint.Show";
											fileTypeSimple = "ppt";
											break;
										case 4:
											fileType = "Visio.Drawing";
											break;
										case 5:
											fileType = "MSProject.Project";
											break;
										case 6:
											fileType = "WPS Doc";
											fileTypeSimple="wps";
											break;
										case 7:
											fileType = "Kingsoft Sheet";
											fileTypeSimple="et";
											break;
										default :
											fileType = "unkownfiletype";
											fileTypeSimple="unkownfiletype";
									}
									setFileOpenedOrClosed(true);
								</script>
									<script language="JScript" for=TANGER_OCX event="BeforeOriginalMenuCommand(TANGER_OCX_str,TANGER_OCX_obj)">
									alert("BeforeOriginalMenuCommand事件被触发");
								</script>
								<script language="JScript" for=TANGER_OCX event="OnFileCommand(TANGER_OCX_str,TANGER_OCX_obj)">
									if (TANGER_OCX_str == 3) 
									{
										alert("不能保存！");
										CancelLastCommand = true;
									}
								</script>
								<script language="JScript" for=TANGER_OCX event="AfterPublishAsPDFToURL(result,code)">
									result=trim(result);
									alert(result);
									document.all("statusBar").innerHTML="服务器返回信息:"+result;
									if(result=="文档保存成功。")
									{window.close();}
								</script>
								<script language="JScript" for=TANGER_OCX event="OnCustomMenuCmd2(menuPos,submenuPos,subsubmenuPos,menuCaption,menuID)">
								alert("第" + menuPos +","+ submenuPos +","+ subsubmenuPos +"个菜单项,menuID="+menuID+",菜单标题为\""+menuCaption+"\"的命令被执行.");
								</script>
                </div>
  		
  		
  	</div>
  </div>
</div>
