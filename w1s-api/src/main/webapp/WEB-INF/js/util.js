var alreadyLocate = false;
var currentLat ,currentLng;
var driveLine;
var dataCache;
var placeId = 0;

var tmpl = [
	'<li class="list-group-item" style="padding-left: 35px;">'
	,'	<div class="row" style="line-height: 80px;"> '
	,'		<div class="col-sm-2" style="line-height: 200px;">'
	,'			<img src="css/images/park-info.png" style="width: 100%;">'
	,'		</div>'
	,'		<div class="col-sm-8">'
	,'			<div class="row" style="">'
	,'				<div class="parkDetail col-sm-6" style="color: #ea5151;font-weight: 800;cursor: pointer;" id="address">189广场停车场</div>'
	,'				<div class="col-sm-6" style="font-weight: 800;">距离:<span id="distance">280</span>米</div>'
	,'			</div>'
	,'			<div class="row" style="">'
	,'				<div class="col-sm-6">总车位:&nbsp;&nbsp;&nbsp;<span id="totalPos">254</span></div>'
	,'				<div class="col-sm-6">空闲车位:&nbsp;&nbsp;&nbsp;<span id="freePos">36</span></div>'
	,'			</div>'
	,'			<div class="row" style="text-align: left;">'
	,'				<div class="col-sm-6">'
	,'					<span style="border: 1px solid #4a4b4f;border-radius: 19px;padding: 3px 21px;">免费</span>'
	,'				</div>'
	,'				<div class="col-sm-6">'
	,'					<span style="border: 1px solid #4a4b4f;border-radius: 19px;padding: 3px 21px;" ><span id="price">400</span>元/小时</span>'
	,'				</div>'
	,'			</div>'
	,'		</div>'
	,'		<div class="col-sm-2" style="text-align: center;line-height: 200px;">'
	,'			<img src="css/images/position.png" style="width: 100%;" id="locate">'
	,'		</div>'
	,'	</div>'
	,'</li>'
];

var listBtnClick = function(){
	if($("#list").css("display")=="none"){
		$("#list").show();
	}else{
		$("#list").hide();
	}
};

var putNormalDot = function(lat,lng,onclick){
	var marker = L.marker([lat,lng]);
	if(typeof(onclick) !="undefined"){
		marker.on('click',onclick);
	}
	marker.addTo(map);
	return marker;
};

var putDot = function(lat,lng,num){
	var myIcon = L.divIcon({
		// iconUrl: 'images/marker-icon.png',
	    //shadowUrl: 'images/marker-shadow.png',
	    iconSize:[1, 1], // size of the icon
	    html:'<div style="width: 25px;height: 41px;line-height: 30px;text-align: center;vertical-align: middle;background-repeat: no-repeat;background-image: url(css/images/marker-icon.png);color: #000;font-size: 10px;">'+(num==0?'':num)+'</div>'
	    // shadowSize:   [50, 64], // size of the shadow
	    // iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
	    // shadowAnchor: [4, 62],  // the same for the shadow
	    // popupAnchor:  [-3, -76]
	});
	// you can set .my-div-icon styles in CSS
	return L.marker([lat,lng], {icon: myIcon}).addTo(map);
};

var putMultiDot = function(dots,iconUrl){
	var result = [];
	$(dots).each(function(index,item){
		var myIcon = L.divIcon({
			// iconUrl: 'images/marker-icon.png',
		    //shadowUrl: 'images/marker-shadow.png',

		    iconSize:[1, 1], // size of the icon
		    html:'<div style="background-size: 50px 82px;width: 50px;height: 82px;line-height: 52px;text-align: center;vertical-align: middle;background-repeat: no-repeat;background-image: url('+iconUrl+');color: #000;font-size: 20px;">'+(item[2]==0?'':item[2])+'</div>'
		    // shadowSize:   [50, 64], // size of the shadow
		    // iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
		    // shadowAnchor: [4, 62],  // the same for the shadow
		    // popupAnchor:  [-3, -76]
		});
		var marker = L.marker([item[0],item[1]], {icon: myIcon});
		marker.on('click',function(){
			alert("我的是"+item[2]);
		});
		result.push(marker);
	});
	return result;
};

var createLegend = function(text,pos,bgcolor,fontcolor,onclick){
	var legend = L.control({position: pos});
	legend.onAdd = function (map) {
	    var div = L.DomUtil.create('div', 'info legend');
	    $(div).css({
		    'width': '160px',
			'height': '160px',
			'text-align': 'center',
			'line-height': '160px',
			'border-radius': '180px',
			'background-color': bgcolor,
			'color': fontcolor,
			'font-weight': '300',
			'font-size': '33px',
			'cursor': 'pointer',
			'box-shadow': '0px 0px 20px'
	    });
		$(div).click(onclick);

		/*mymap.setView([31.22,122.48], 10);
			putDot(31.22,122.48,99);*/
			/*mymap.fitBounds([
				[31.22,121.48],
				[31.22,121.485],
				[31.22,121.490]
			]);*/
       
		div.innerHTML +='<i style="background:#333;"></i> '+text+'<br>' ;
	    return div;
	};
	return legend;
};

var viewSuit = function(dots){
	map.fitBounds(dots);
};

var driveHelp = function(latlngs){
	if(typeof(driveLine)!="undefined"){
		driveLine.remove();
	}
	var polyline = L.polyline(latlngs, {color: 'green'}).addTo(map);
	driveLine = polyline;
	// zoom the map to the polyline
	map.fitBounds(polyline.getBounds());
}

