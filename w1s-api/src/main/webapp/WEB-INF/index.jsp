<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>智能停车</title>
	<link rel="stylesheet" href="css/leaflet.css"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
	<script src="js/leaflet.js" ></script>
	<script src="js/jquery-1.9.1.js" ></script>
	<script src="js/bootstrap.js" ></script>
	<script src="js/util.js"></script>
	<style type="text/css">
		/* .nav-pills > li {
			float: right;
		} */
		/* .leaflet-control-layers-overlays label{
			float:left;
		} */
		body{
			color:#;
		}

		.leaflet-touch .leaflet-bar a {
		    width: 100px;
		    height: 100px;
		    line-height: 100px;
		}

		.leaflet-touch .leaflet-control-zoom-in, .leaflet-touch .leaflet-control-zoom-out {
		    font-size: 80px;
		}

		h1{
		    font-size: 45px;
		}

		h6 {
		    font-size: 33px;
		}
		.close{
		    font-size: 38px;
		}

		.glyphicon {
		    line-height: 2;
		}

		.leaflet-bottom {
		    bottom: 100px;
		}

	</style>
   
</head>
<body style="margin: 0px;">
	<ul class="nav nav-pills" style="line-height: 94px;font-size: 60px;padding: 1px 1px;">
		<li style=" margin-left: 30px;">
			<span class="glyphicon glyphicon-user"></span>
		</li> 
		<li style=" margin-left: 32%;line-height:2;">停简单</li> 
	  	<li style=" margin-right: 30px;float: right;">
	  		<span class="list-btn glyphicon glyphicon-th-list"></span>
	  	</li> 
	</ul>
	
	<!-- 车位列表 -->
	<ul id="list" class="list-group" style="position: absolute; top:120px;left:0px;z-index: 10000; display: none;width: 100%;font-size: 38px;">
		<li class="list-group-item" style="text-align: center;;">
			<span class="glyphicon glyphicon-chevron-left list-btn" style="float: left;line-height: 1;"></span>
			<span >推荐停车场</span>
		</li>
		<li class="list-group-item" style="text-align: center;background-color: #4a4b4f;color: #fff;">
			<span style="color:red;font-weight: 800;">您的当前位置:</span>上海市长寿路189号
		</li>
		<li class="list-group-item" style="text-align: center;">
			<div class="row"> 
				<div class="col-sm-4">500m></div>
				<div class="col-sm-4">
					<span style="padding: 6px 26px;border-radius: 32px;">距离优先</span>
				</div>
				<div class="col-sm-4">价格优先</div>
			</div>
		</li>
		<li class="list-group-item" style="padding-left: 35px;">
			<div class="row" style="line-height: 80px;"> 
				<div class="col-sm-2" style="line-height: 200px;" >
					<img src="css/images/park-info.png" style=" width: 100%;">
				</div>
				<div class="col-sm-8">
					<div class="row" style="">
						<div class="parkDetail col-sm-6" style="color: #ea5151;font-weight: 800;cursor: pointer;">189广场停车场</div>
						<div class="col-sm-6" style="font-weight: 800;">距离:280米</div>
					</div>
					<div class="row" style="">
						<div class="col-sm-6">总车位:&nbsp;&nbsp;&nbsp;254</div>
						<div class="col-sm-6">空闲车位:&nbsp;&nbsp;&nbsp;36</div>
					</div>
					<div class="row" style="text-align: left;">
						<div class="col-sm-6">
							<span style="border: 1px solid #4a4b4f;border-radius: 19px;padding: 3px 21px;">免费</span>
						</div>
						<div class="col-sm-6">
							<span style="border: 1px solid #4a4b4f;border-radius: 19px;padding: 3px 21px;">400元/月</span>
						</div>
					</div>
				</div>
				<div class="col-sm-2" style="text-align: center;line-height: 200px;">
					<img src="css/images/position.png" style="width: 100%;">
				</div>
			</div>
		</li>

	</ul>
	<div id="mapid"></div>

	<!-- 车位信息模态框 -->
	<div style="margin-top: 15%;" class="modal fade" id="parkInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width: 33%;">
	        <div class="modal-content" style="border-radius: 20px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding: 3px;background-color: #0c0c0c;color: #fff;opacity: 1;line-height: 12px;font-weight: 100;border-radius: 16px;">&times;</button>
	                <h4 class="modal-title" style="text-align: center;font-weight: 700;">停车场信息</h4>
	                <h6 class="modal-title"  style="text-align: center;">提交的车厂信息，一旦采纳，<span style="color:#ea5151;">获得50-100积分</span></h6>
	            </div>
	            <div class="modal-body">
	            	<div class="row">
	            		<div class="col-sm-12" style="color:#ea5151;font-weight: 800;line-height: 25px;">价格信息</div>
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<textarea style="resize:none;background-color: #ebe7e7;" class="form-control" rows="5" placeholder="10元/小时,500元/月"></textarea>
	            		</div>
	            		<div class="col-sm-12" style="color:#ea5151;font-weight: 800;line-height: 25px;">车位信息</div>
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<textarea style="resize:none;background-color: #ebe7e7;" class="form-control" rows="5" placeholder="300个车位,目前剩余100个"></textarea>
	            		</div>
	            	</div>
					
	            </div>
	            <div class="modal-footer" style="text-align: center;">
	                <button type="button" class="btn btn-primary" style="width: 50%;border-radius: 19px;border: none;background-color: #ea5151;">提交</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>

	<!-- 到达弹框 -->
	<div style="margin-top: 50%;" class="modal fade" id="arriveInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width: 70%;">
	        <div class="modal-content" style="border-radius: 20px;font-size: 35px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding: 14px;background-color: #0c0c0c;color: #fff;opacity: 1;line-height: 21px;font-weight: 100;border-radius: 25px;">&times;</button>
	                <h1 class="modal-title" style="text-align: center;font-weight: 700;line-height: 3.428;">您已到达停车场啦!</h1>
	            </div>
	            <div class="modal-body" style="text-align: center;">
	            	<div class="row" style="line-height: 100px;">
	            		<div class="col-sm-12" style="color:#ea5151;font-weight: 800;">待停车完成后点击确定保存车位信息</div>
	            	</div>
	            	<div class="row" style="line-height: 100px;">
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<button type="button" class="btn btn-primary" style="width: 50%;border-radius: 53px;line-height: 76px;margin: 23px 13px;font-size: 40px;border: none;background-color: #393636;">寻找新停车场</button>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 100px;">
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<button type="button" class="btn btn-primary" style="width: 50%;border-radius: 53px;line-height: 76px;margin: 23px 13px;font-size: 40px;border: none;background-color: #ea5151;">确定</button>
	            		</div>
	            	</div>
					
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>

	<!-- 支付弹框 -->
	<div style="margin-top: 50%;" class="modal fade" id="payInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width: 70%;">
	        <div class="modal-content" style="border-radius: 20px;font-size: 35px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding: 14px;background-color: #0c0c0c;color: #fff;opacity: 1;line-height: 21px;font-weight: 100;border-radius: 25px;">&times;</button>
	                <h1 class="modal-title" style="text-align: center;font-weight: 700;">支付</h1>
	                <h6 class="modal-title" style="text-align: center;">2018.6.30 17:34--2018.6.30 17:34</h6>
	            </div>
	            <div class="modal-body" style="text-align: center;padding: 0% 10%;">
	            	<div class="row" style="line-height: 55px;">
	            		<div class="col-sm-12" style="color:#ea5151;font-weight: 800;line-height: 90px;">
							<span>车牌:沪A231231</span>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 55px;text-align: left;">
	            		<div class="col-sm-12" style="font-weight: 800;line-height: 90px;">
							<span>上海189广场停车场</span>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 90px;">
	            		<div class="col-sm-6" style="text-align: left;">
							<span>停车费</span>
	            		</div>
	            		<div class="col-sm-6" style="">
							<span>30.00</span>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 90px;">
	            		<div class="col-sm-6" style="text-align: left;">
							<span>积分抵扣金额</span>
	            		</div>
	            		<div class="col-sm-6" style="">
							<span>10.00</span>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 90px;">
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;text-align: left;">
							<span>待支付金额</span>
	            		</div>
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;">
							<span>20.00</span>
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 90px;">
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;">
							<img src="css/images/wepay.png" style="width: 100%;">
	            		</div>
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;">
							<img src="css/images/alipay.png" style="width: 100%;">
	            		</div>
	            	</div>
	            	
	            	<div class="row" style="line-height: 55px;">
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<button type="button" class="btn btn-primary" style="width: 50%;border-radius: 53px;line-height: 76px;margin: 23px 13px;font-size: 40px;border: none;background-color: #ea5151;" id="pay-btn">余额支付</button>
	            		</div>
	            	</div>
					
	            </div>
	        </div>
	    </div>
	</div>

	<!-- 支付成功弹框 -->
	<div style="margin-top: 10%;" class="modal fade" id="paySuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width: 35%;">
	        <div class="modal-content" style="border-radius: 20px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding: 3px;background-color: #0c0c0c;color: #fff;opacity: 1;line-height: 12px;font-weight: 100;border-radius: 16px;">&times;</button>
	                <h4 class="modal-title" style="text-align: center;font-weight: 700;">支付结果</h4>
	            </div>
	            <div class="modal-body" style="text-align: center;padding: 0% 10%;">	            	
	            	<div class="row" style="line-height: 55px;">
	            		<div class="col-sm-12" style="color:#ea5151;">
	            			<span style="font-size: 23px;color: green;">支付成功</span>
	            		</div>
	            	</div>
					
	            </div>
	        </div>
	    </div>
	</div>

	<!-- 详情弹框 -->
	<div style="margin-top: 35%;font-size: 27px;"  class="modal fade" id="detailInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style=" width: 85%;">
	        <div class="modal-content" style="border-radius: 20px;">
	            <div class="modal-header" style="padding: 0px;">
	                <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding: 3px;background-color: #0c0c0c;color: #fff;opacity: 1;line-height: 12px;font-weight: 100;border-radius: 16px;">&times;</button> -->
	                <img src="css/images/detail.png" style="width: 100%;border-radius: 17px 17px 0px 0px;" >
	            </div>
	            <div class="modal-body" style="text-align: center;padding: 0% 10%;">
	            	<div class="row" style="line-height: 50px;">
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;text-align: left;">
							<span id="detail-name">上海189广场</span>
	            		</div>
	            		<div class="col-sm-6" style="color:#ea5151;font-weight: 800;text-align: right;">
							距离我的位置:<span id="detail-dis">145</span>m
	            		</div>
	            	</div>
	            	<div class="row" style="line-height: 50px;text-align: left;">
	            		<div class="col-sm-9" style="font-weight: 800;line-height: 25px;">
							<div class="row" style="line-height: 45px;">
								<div class="col-sm-12" id="detail-add" >
									上海长寿路189号
								</div>
							</div>
							<div class="row" style="line-height: 45px;text-align: left;">
								<div class="col-sm-3" >
									<span style="border: 1px solid #333;padding: 2px 16px;border-radius: 19px;">免费</span>
								</div>
								<div class="col-sm-4" >
									<span style="border: 1px solid #333;padding: 2px 16px;border-radius: 19px;">认证数据</span>
								</div>
								<div class="col-sm-4" >
									<span style="border: 1px solid #333;padding: 2px 16px;border-radius: 19px;">夜间优惠</span>
								</div>
							</div>
	            		</div>
	            		<div class="col-sm-3" >
							<img src="css/images/position.png" style="width: 100%;">
	            		</div>
	            	</div>
	            	<div class="row" >
	            		<div class="row" >
	            			<div class="col-sm-6" style="color:#ea5151;font-weight: 800;line-height: 50px;text-align: left;">
	            				价格信息
	            			</div>
	            		</div>
	            		<div class="row" style="line-height: 70px;">
	            			<div class="col-sm-6" style="text-align: left;">
								<span id="detail-price">白天:10元/小时</span>
		            		</div>
		            		<div class="col-sm-6" style="">
								<span>包月:500元/月</span>
		            		</div>
	            		</div>
	            		<div class="row" style="line-height: 70px;">
	            			<div class="col-sm-6" style="text-align: left;">
								<span>夜间:5元/小时</span>
		            		</div>
		            		<div class="col-sm-6" style="">
								<button type="button" class="btn btn-primary" style="border-radius: 19px;border: none;background-color: #ea5151;">分享数据</button>
		            		</div>
	            		</div>
	            		
	            	</div>

	            	<div class="row" style="line-height: 70px;" >
	            		<div class="row" >
	            			<div class="col-sm-6" style="color:#ea5151;font-weight: 800;line-height: 25px;text-align: left;">
	            				车位信息
	            			</div>
	            		</div>
	            		<div class="row" style="">
	            			<div class="col-sm-12" style="text-align: left;">
								<span>300个车位，目前剩余100个</span>
		            		</div>
	            		</div>
	            		<div class="row" style="margin-bottom: 15px;">
	            			<div class="col-sm-6" style="text-align: left;">
								<span>官方实时</span>
		            		</div>
		            		<div class="col-sm-6" style="">
								<button type="button" class="btn btn-primary" style="border-radius: 19px;border: none;background-color: #ea5151;">分享数据</button>
		            		</div>
	            		</div>
	            		
	            	</div>
					
	            </div>
	        </div>
	    </div>
	</div>

	<script type="text/javascript">

		$(function(){
			var clitetId = new Date().getTime();
			$.ajax({
				url:'${pageContext.request.contextPath}/parking/list', 
				data: {
				},
				type:'post',
				dataType: 'json',
				//crossDomain: true,
				success: function(list) {
				  	if(list.ret==0){
				  		var type0 = []; //停车场
				  		var type1 = []; //加油站
				  		var type2 = []; //厕所
				  		var type3 = []; //便利店
				  		var posSet = [];

						$(list.data).map(function(index,val){
							posSet.push([val.latitude,val.longitude]);
							switch(val.type){
								case 0:
									type0.push([val.latitude,val.longitude,val.amount-val.used]);
							 		break;
						 		case 1:
						 			type1.push([val.latitude,val.longitude,val.amount-val.used]);
						 			break;
						 		case 2:
						 			type2.push([val.latitude,val.longitude,val.amount-val.used]);
						 			break;
					 			case 3:
					 				type3.push([val.latitude,val.longitude,val.amount-val.used]);
						 			break;
						 		default:
						 			break;
							}
							var $item = $(tmpl.join(''));
							$item.find("#address").data("lat",val.latitude);
							$item.find("#address").data("lng",val.longitude);

							$item.find("#locate").data("lat",val.latitude);
							$item.find("#locate").data("lng",val.longitude);

							$item.find("#address").data("info",val);
							$item.find("#address").click(function(){
								listBtnClick();
								$("#detail-price").html($(this).data('info').price);
								$("#detail-add").html($(this).data('info').address);
								$("#detail-name").html($(this).data('info').name);
								$("#detail-dis").html($(this).data('info').distance);
								$("#detailInfo").modal('show');
								/*$(this).data('lat');*/
							});

							$item.find("#locate").click(function(){
								listBtnClick();
								map.setView([$(this).data('lat'),$(this).data('lng')],18);
							});
							$item.find("#distance").html(val.distance);
							$item.find("#address").html(val.address);
							$item.find("#totalPos").html(val.amount);
							$item.find("#freePos").html(val.amount-val.used);
							$("#list").append($item);
						});

						var layerGroup0 = L.layerGroup(putMultiDot(type0,'css/images/1.png')).addTo(map);
						var layerGroup1 = L.layerGroup(putMultiDot(type1,'css/images/2.png')).addTo(map);
						var layerGroup2 = L.layerGroup(putMultiDot(type2,'css/images/3.png')).addTo(map);
						var layerGroup3 = L.layerGroup(putMultiDot(type3,'css/images/4.png')).addTo(map);

						var overlays ={
							/*"就近停":marker1,
							"实惠停":marker2,*/
							"<img src=\"css/images/cheap.png\" style='width:60px;' >":layerGroup0,
							"<img src=\"css/images/here.png\" style='width:60px;'>":layerGroup1,
							"<img src=\"css/images/month.png\" style='width:60px;'>":layerGroup2,
							"<img src=\"css/images/power.png\" style='width:60px;'>":layerGroup3,
						};

						var control = L.control;
						var layers = control.layers( {},overlays).addTo(map);
						viewSuit(posSet);

	    			}
				}
			});

			$("#pay-btn").click(function(){
				$.ajax({
					url:'${pageContext.request.contextPath}/request_pay', 
					data: {
						client_id:clitetId,
						place_id:11
					},
					type:'post',
					dataType: 'json',
					//crossDomain: true,
					success: function(result) {
						if(result.ret>0){
							alert("支付失败！");
						}else{
							$("#payInfo").modal('hide');
							$("#paySuccess").modal('show');
							setTimeout(function(){
								$("#paySuccess").modal('hide');
							},1000)
							

						}
					}
				});
			});
/*
			$.post("http://172.20.10.4:8084/parking/list",{},function(list){
    			if(list.ret==0){
					$(list.data).map(function(index,val){
						var $item = $(tmpl);
						$item.find("#distance").val(val.distance);
						$item.find("#address").val(val.address);
						$item.find("#totalPos").val(val.amount);
						$item.find("#freePos").val(val.amount-val.used);
						$("#list").append();
					});
    			}

	  		});*/
		});

		//菜单按钮点击事件
		$(".list-btn").click(function(){
			listBtnClick();
			//$("#parkInfo").modal('show');
			//
			//
			//$("#detailInfo").modal('show');
		});

		$(".parkDetail").click(function(){
			listBtnClick();
			$("#detailInfo").modal('show');
		});

		//自适应地图大小
		document.getElementById("mapid").style.height = document.documentElement.clientHeight-34+"px";
 		window.onresize=function(){
        	document.getElementById("mapid").style.height = document.documentElement.clientHeight-34+"px";
      	}

      	//初始化地图
   		var map = L.map('mapid').setView([31.22,121.48], 10);
		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		    attribution: '',
		    maxZoom: 18,
		    id: 'mapbox.streets',
		    accessToken: 'pk.eyJ1Ijoid2Y5OTg4Nzc2NiIsImEiOiJjaml6cjI0dHowYTBsM2t0NGIxcDdueHNzIn0.muPB50596c5-EcIMVmh2HA'
		}).addTo(map);
		map.on('click', function(e) {
			console.log("You clicked the map at " + e.latlng.toString());
		});


		$(".list-group .flag").click(function(){
			listBtnClick();
			var line = [[$(this).attr("lat"),$(this).attr("lng")],[currentLat,currentLng]];
			driveHelp(line);
			//map.setView([$(this).attr("lat"),$(this).attr("lng")],18);
		});

		/*var data = [
			[31.22,121.48,3],
			[31.216619, 121.48819,2],
			[31.22,121.490,1]
		]

		var layerGroup = L.layerGroup(putMultiDot(data)).addTo(map);

		var overlays ={
			"<img src=\"css/images/cheap.png\" style='width:60px;' >":layerGroup,
			"<img src=\"css/images/here.png\" style='width:60px;'>":layerGroup,
			"<img src=\"css/images/month.png\" style='width:60px;'>":layerGroup,
			"<img src=\"css/images/power.png\" style='width:60px;'>":layerGroup,
			"<img src=\"css/images/freepark.png\" style='width:60px;'>":layerGroup
		};
		var control = L.control;
		var layers = control.layers( {},overlays).addTo(map);*/

		createLegend("就近停",'bottomleft','#3957c6','#fff',function(){
			alert("3333");
		}).addTo(map);

		createLegend("实惠停",'bottomleft','#ea5151','#fff',function(){
			alert("3333");
		}).addTo(map);

		createLegend("我要支付",'bottomleft','#fff','#333',function(){
			$("#payInfo").modal('show');
		}).addTo(map);

		createLegend("我要找车",'bottomright','#393636','#fff',function(){
			$("#arriveInfo").modal('show');
			/*if(!alreadyLocate){
				currentLat = 31.227702;
				currentLng = 121.483383;
				putNormalDot(31.227702, 121.483383,function(){
					if(alreadyLocate){
						this.remove();	
						alreadyLocate = false;				
					}
				});
				alreadyLocate = true;
			}
			map.setView([31.227702, 121.483383],16);*/
			
		}).addTo(map);
	
		/*$.post("test",{param:'ff'},function(result){
	    	
	  	});*/

		
   </script>
</body>
</html>