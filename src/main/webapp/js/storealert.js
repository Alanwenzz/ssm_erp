$(function(){
	$('#grid').datagrid({
		url:'storedetail_storealertList',
		columns:[[
		    {field:'uuid',title:'商品编号',width:100},
		    {field:'name',title:'商品名称',width:100},
		    {field:'storenum',title:'库存数量',width:100},
		    {field:'outnum',title:'待发货数量',width:100}
		]],
		singleSelect:true,
		rownumbers:true,
		toolbar: [{
			text:'发送预警邮件',
			iconCls: 'icon-add',
			handler: function(){
				//触发发送邮件
				$.ajax({
					url:'storedetail_sendStorealerMail',
					type:'post',
					dataType:'json',
					success:function(rtn){
						if(rtn==0)
						$.messager.alert('提示','发送成功','info');
					}
				});
			}
		}]
	});
});