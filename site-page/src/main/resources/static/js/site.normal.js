/**
 * 搜索结果页的结果展示函数
 * @param div
 * @param data
 * @returns
 */
function appendResultToPage(div, data) {
	div.html("");
    $(data).each(function (index, item) {
    	//for(var i = 0; i< 10; i++){
    			// 网站缩略图
    			var imgContent = "<p class='dev-text' style='height: 200px;text-align:center'><img src='/"+item.imageUrl+"' style='width: 100%;'></p>";
    			
        		// 点击跳转的按钮
            var buttonDiv = $("<div class='layer-btn'><a href=/target?url="+item.url+"><p style='color: #af9595;'>立即体验</p></a></div>");
            // 网站描述的内容
            var conetDiv = $("<div class='layer-text'><p class='dev-text'>"+item.siteDesc+"</p>" + imgContent +"</div>");
            // 网站的标题
            var titleDiv = $("<div class='layer-figure'><img src='/img/web.png'/></div><h2 class='dev-list'>"+item.title+"</h2>");
            // 放具体内容的div
            var subDiv = $("<div class='tile center-layer'></div>");
            // 加上内容
            subDiv.append(titleDiv);
            subDiv.append(conetDiv);
            subDiv.append(buttonDiv);
            // 单个网站信息的div层
            var siteDiv = $("<div class='col-sm-6'></div>");
            siteDiv.append(subDiv);
            
            // 最后添加到页面中
            div.append(siteDiv);
    	//}
    });
}


/**
 * 优化后的UI版本
 * V1版
 * @param div
 * @param data
 * @returns
 */
function appendResultToPageV1(div, data){
	div.html("");
    $(data).each(function (index, item) {
    			// 放具体内容的div
    			var subDiv = $('<div id="singleResult"></div>');
    			
    			// 放置具体内容的页面
    			var contentDiv = $('<div class="result result-default"></div>');
    			// 标题加超链接
    			var ahref = $('<h4 class="result_header"><a href="/target?url='+item.url+'"target="_blank" rel="noopener noreferrer">'+item.title+'<span class="highlight"></span></a></h4>');
    			var detailDiv = $('<p class="result-content">'+item.siteDesc+'<span class="highlight"></span></p>');
    			var targetDiv = $('<div class="external-link"><span class="url">'+item.url+'</span><span class="proxy"> <a href="'+item.url+'"class="text-info" target="_blank" rel="noopener noreferrer">前往访问</a><!--<div class="tips"></div></span></div>-->');
            // 加上内容
    			contentDiv.append(ahref);
    			contentDiv.append(detailDiv);
    			contentDiv.append(targetDiv);
    			subDiv.append(contentDiv);
    			
    			
    			// 网站缩略图
    			var imgContent = '<div class="pull-left"><img src="/'+item.imageUrl+'" style="width: 150px; height: 110px;"></img></div>';
    			//var imgContent = "<p class='dev-text' style='height: 200px;text-align:center'><img src='/"+item.imageUrl+"' style='width: 100%;'></p>";
    			//subDiv.append(imgContent);
    			//console.log(contentDiv.style.height);
    			
            // 最后添加到页面中
            div.append(subDiv);
    });
}


/**
 * 结果页的查询按钮事件
 * @returns
 */
$('#su_search').click(function(){
	//var div = $("#result_show");
	var div = $(".default-container");
	localStorage.searchKey=$("#ipt").val();
	var searchKey = localStorage["searchKey"];
	if(searchKey == ''){
		return;
	}
	$.get("/search?query="+searchKey ,function (data){
		appendResultToPageV1(div, data);
	});
});

/**
 * 首页的查询按钮事件
 * 做页面跳转
 * @returns
 */
$('#index_search').click(function(){
	localStorage.searchKey=$("#ipt").val();
	//alert(localStorage["searchKey"]);
	if(localStorage["searchKey"] == ""){
		return;
	}else{
		window.location.href = 'result.html';
	}
});

// 定义一个词云点击事件
function wordClick(keyword){
	localStorage.searchKey=keyword;
	if(localStorage["searchKey"] == ""){
		return;
	}else{
		window.location.href = '/result.html';
	}
}

// 最近更新的数据
function recentData(){
	$.get("/recent" ,function (data){
		var div = $("#recentDate");
		div.html("");
		var titleDiv = '<div class="panel-heading"><h4 class="panel-title">最近更新</h4></div>';
		div.append(titleDiv);
	    $(data).each(function (index, item) {
	    		// 放具体内容的div
	    		var subDiv = $('<div class="panel-body" style="padding:0px"><p><a href="/target?url='+item.url+'">'+item.title+'</a></p></div>');
	    		// 最后添加到页面中
	        div.append(subDiv);
	    });
	});
}
