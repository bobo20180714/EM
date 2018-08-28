<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<head>
<%@ include file="../../common/common_header.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/lang/zh-cn/zh-cn.js"></script>
<title>后台管理界面</title>
</head>
<body>
<script id="testUeditor" type="text/plain" style="width:100%;"></script>
</body>
<script type="text/javascript">
var testUeditor = UE.getEditor('testUeditor');
</script>
</html>
 --%>


<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx }/component/ueditor/lang/zh-cn/zh-cn.js"></script>
<script id="testUeditor" type="text/plain" style="width:100%;"></script>
<script type="text/javascript">
var testUeditor = UE.getEditor('testUeditor',{
    wordCount:false,
    //关闭elementPath
    elementPathEnabled:false,
    //默认的编辑区域高度
    initialFrameHeight:300
});
</script>
