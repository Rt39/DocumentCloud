<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>参数配置</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" href="./asset/layui2.5.5/css/layui.css" type="text/css">
		
		<script src="./js/jquery-3.4.1.min.js" type="text/javascript"></script>
		<script src="./js/common.js" type="text/javascript"></script>
		<script src="./layui/lay/modules/form.js" charset="utf-8"></script>
		<script src="./asset/layui2.5.5/layui.js" charset="utf-8"></script>
		<style>
			.layui-input-block{
				width:250px;
			}

			.layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
			     top: 50%;
			     transform: translateY(-50%);
			}

			#addDIV,#editDIV{
				padding-top: 50px;
			}
		</style>
	</head>

	<body>
				<div class="layui-tab-content">
					<form class="layui-form" action="">
						<blockquote class="layui-elem-quote">
							<div class="layui-input-inline">
								<label class="layui-form-label">
									键
								</label>
								<div class="layui-input-inline">
									<input autocomplete="off" class="layui-input" id="codeQuery" placeholder="请输入键">
								</div>
							</div>
							<div class="layui-input-inline">
								<label class="layui-form-label">
									值
								</label>
								<div class="layui-input-inline">
									<input autocomplete="off" class="layui-input" id="valueQuery" placeholder="请输入值">
								</div>
							</div>
							<div class="layui-input-inline">
								<label class="layui-form-label">
									类型:
								</label>
								<div class="layui-input-block">
									<input type="text" name="type" id="typeQuery" autocomplete="off" class="layui-input" placeholder="请输入类型,格式:表名_字段名">
								</div>
							</div>
							<div class="layui-btn-group" style="float: right">
								<a href="JavaScript:void(0)" class="layui-btn ty-btn" onclick="queryList()">
									<span style="font-size: 14px">搜索</span>
								</a>
								<a href="JavaScript:void(0)" class="layui-btn ty-btn" onclick="queryItemClear()">
									<span style="font-size: 14px">重置</span>
								</a>
								<a href="JavaScript:void(0)" onclick="openAddDiv()"
									class="layui-btn ty-btn">新增</a>
							</div>
						</blockquote>
						
						<table id="dataGridList" lay-filter="dataGrid-filter"></table>
						
						<!-- 数据表格按钮 -->
						<script type="text/html" id="toolbar">
						  <div class="layui-btn-group">
						</div>
						</script>
						
						<script type="text/html" id="colbar">
  							<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  							<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
						</script>
					</form>
				</div>
		<script>
		
        /*数据表格显示*/
        layui.use('table', function(){
			  table = layui.table;
			  table.render({
			    elem: '#dataGridList'
			    ,height: 510
			    ,url: './setting/getListByPage' //数据接口
			    ,title: ''
			    ,toolbar: '#toolbar'
			    ,defaultToolbar: ['filter', 'exports', 'print']
			    ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
			      layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
			      limit:20,
			      limits:[10,15, 20, 50 ,100,500]
			    }
			    ,cellMinWidth: 20
			    ,cols: [[ //表头
			      {field: 'code', title: '键'}
			      ,{field: 'value', title: '值'}
			      ,{field: 'type', title: '类型'}
			      ,{field: 'remark', title: '备注'}
			      ,{field: 'sys_create', title: '添加时间',sort: true,
				      templet: function(d){
				      	return unixToDateTime(d.sysCreate);
				      }
				  }
			      ,{title:'操作', align:'center', toolbar: '#colbar'}
			    ]]
			  });
				
			  //监听工具条
			  table.on('tool(dataGrid-filter)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'edit'){
			    	edit(data);  //编辑
			    }else if(obj.event === 'del'){
			    	del(data);  //删除
			    }else if(obj.event === 'allotPermission'){
                    openallotPermission(data);  //分配权限
			    }
			  });
				  
   		});
		
	    //查询
		function queryList(){
			var code = $('#codeQuery').val();
			var value = $('#valueQuery').val();
			var type = $('#typeQuery').val();

            table.reload('dataGridList', {
				url: "./setting/getListByPage",
                page:{
                    curr:1
                }
			    ,where: {code:code,value:value,type:type}
			});
		}
		
		/*重置查询表单*/
		function queryItemClear(){
			layui.use('form', function(){
				$('#codeQuery').val('');
				$('#valueQuery').val('');
				$('#typeQuery').val('');
				var form = layui.form;
				form.render('select'); //刷新select选择框渲染
			});
		}

/*********************************删除**************************************/
		/*删除用户*/
		function del(rowdata){
			//确认删除
			layer.confirm('确认删除吗?', {icon: 3, title:'提示'}, function(index){
				var d =rowdata;
				$.ajax({
					url: "./setting/deleteByPrimaryKey"
						, data: {id:d.id}
						, type: "post"
						, dataType: "json"
						, success: function (data) {
							if (data.code == "success") {
								layer.alert(data.msg,{title:'提示',icon: 1},function(index){
									layer.close(index);
									layui.form.render();
									table.reload('dataGridList', {
									  url: "./setting/getListByPage"
									});
								});
							}else {
								layer.alert(data.msg,{title:'提示',icon: 2});
							}
						}
				});
			    layer.close(index);
			});
		}
/*********************************删除end**************************************/

/*********************************添加**************************************/
		/*添加弹框*/
        function openAddDiv(){
            $("#form_add")[0].reset();
            $('#remark').removeAttr("readOnly");
        	addLayer = layer.open({
			  type: 1,
			  title: ['新增配置', 'font-size:18px;'],
			  content: $('#addDIV'),
			  area: ['450px', '420px']
			});
        }
        
        /*添加表单提交*/
        layui.use('form', function(){
		  var form = layui.form;
		  //监听添加提交
		  form.on('submit(formAdd)', function(data){
			$.ajax({
                url: "./setting/insertSelective"
                    , data: data.field
                    , type: "post"
                    , dataType: "json"
                    , success: function (data) {
                        if (data.code == "success") {
                            layer.alert(data.msg,{title:'提示',icon: 1},function(index){
                            	layer.close(addLayer);
                            	layer.close(index);
                        		layui.form.render();
                            	table.reload('dataGridList', {
								  url: "./setting/getListByPage"
								  //,where: {} //设定异步数据接口的额外参数
								});
                            });
                            $("#form_add")[0].reset();
                        }else {
                            layer.alert(data.msg,{title:'提示',icon: 2});
                        }
                    }
            });
            return false;
		  });
		});
        
/*********************************添加 end**************************************/

/*********************************修改**************************************/
        //编辑操作
        function edit(rowdata){
	    	var d =rowdata;
	    	$('#id_up').val(d.id);
	    	$('#code_up').val(d.code);
	    	$('#value_up').val(d.value);
	    	$('#type_up').val(d.type);
	    	$('#remark_up').val(d.remark);
    		$('#remark_up').removeAttr("readOnly");
	    	noticeLayer = layer.open({
			  type: 1,
			  title: ['编辑配置', 'font-size:18px;'],
			  content: $('#editDIV'),
			  area: ['450px', '420px']
			});
        }
		
        /*修改表单提交*/
        layui.use('form', function(){
		  var form = layui.form;
		  //监听修改提交
		  form.on('submit(formEdit)', function(data){
			$.ajax({
                url: "./setting/updateByPrimaryKeySelective"
                    , data: data.field
                    , type: "post"
                    , dataType: "json"
                    , success: function (data) {
                        if (data.code == "success") {
                            layer.alert(data.msg,{title:'提示',icon: 1},function(index){
                            	layer.close(noticeLayer);
                            	layer.close(index);
                        		layui.form.render();
                            	table.reload('dataGridList', {
								  url: "./setting/getListByPage"
								  //,where: {} //设定异步数据接口的额外参数
								});
                            });
                            $("#editForm")[0].reset();
                        }else {
                            layer.alert(data.msg,{title:'提示',icon: 2});
                        }
                    }
            });
            return false;
		  });
		});
/*********************************修改 end**************************************/

/****************** 公共配置第三方组件 *****************/
/*引入样式*/
layui.link('./asset/layui2.5.5/extra/autocomplete.css');

/*配置第三方库*/
layui.config({
	version: false,
	debug: false,
	base: './asset/layui2.5.5/extra/'
});

/*使用autocomplete*/
layui.use(['jquery', 'autocomplete'], function () {
	var $ = layui.jquery,
		autocomplete = layui.autocomplete;
		/*渲染查询框*/
		autocomplete.render({
			elem: $('#typeQuery')[0],
			url: './setting/getSettingType',
			template_val: '{{d.type}}',
			template_txt: '{{d.type}} <span class=\'layui-badge layui-bg-gray\'>{{d.remark}}</span>',
			onselect: function (resp) {
			}
		});
	/*渲染添加框*/
    autocomplete.render({
        elem: $('#type')[0],
        url: './setting/getSettingType',
        template_val: '{{d.type}}',
        template_txt: '{{d.type}} <span class=\'layui-badge layui-bg-gray\'>{{d.remark}}</span>',
        onselect: function (resp) {
            $('#remark').val(resp.remark);
            $('#remark').attr("readOnly","true");
        }
    });
	/*渲染修改框*/
    autocomplete.render({
        elem: $('#type_up')[0],
        url: './setting/getSettingType',
        template_val: '{{d.type}}',
        template_txt: '{{d.type}} <span class=\'layui-badge layui-bg-gray\'>{{d.remark}}</span>',
        onselect: function (resp) {
            $('#remark_up').val(resp.remark);
            $('#remark_up').attr("readOnly","true");
        }
    });
});

        </script>
	</body>

	<!-- 添加窗口 -->
	<div id="addDIV" style="display:none;">
		<form id="form_add" class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">
					键:
				</label>
				<div class="layui-input-block">
					<input type="text" name="code" lay-verify="required|n30length"
						placeholder="请输入键" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">
					值:
				</label>
				<div class="layui-input-block">
					<input type="text" name="value" lay-verify="required|n30length"
						   placeholder="请输入键" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">
					类型:
				</label>
				<div class="layui-input-block">
					<input type="text" name="type" id="type" autocomplete="off" class="layui-input" placeholder="请输入类型,格式:表名_字段名">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">
					备注:
				</label>
				<div class="layui-input-block">
					<input type="text" name="remark" id="remark" lay-verify="required|n30length"
						   placeholder="请输入备注" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formAdd">
						提交
					</button>
					<button type="reset" class="layui-btn layui-btn-primary">
						重置
					</button>
				</div>
			</div>
			
		</form>
	</div>
	
	<!-- 修改窗口 -->
	<div id="editDIV" style="display:none;">
		<form class="layui-form" id="editForm" action="">
			<input id="id_up" name="id" hidden/>
			<div class="layui-form-item">
				<label class="layui-form-label">
					键:
				</label>
				<div class="layui-input-block">
					<input type="text" name="code" id="code_up" lay-verify="required|n30length"
						   placeholder="请输入键" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">
					值:
				</label>
				<div class="layui-input-block">
					<input type="text" name="value" id="value_up" lay-verify="required|n30length"
						   placeholder="请输入值" autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">
					类型:
				</label>
				<div class="layui-input-block">
					<input type="text" name="type" id="type_up" autocomplete="off" class="layui-input" placeholder="请输入类型,格式:表名_字段名">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">
					备注:
				</label>
				<div class="layui-input-block">
					<input type="text" name="remark" id="remark_up" lay-verify="required|n30length"
						   placeholder="请输入备注" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formEdit">
						提交
					</button>
				</div>
			</div>
			
		</form>
	</div>
</html>
