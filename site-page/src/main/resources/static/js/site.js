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
 * 结果页的查询按钮事件
 * @returns
 */
$('#su_search').click(function(){
	var div = $("#result_show");
	localStorage.searchKey=$("#ipt").val();
	var searchKey = localStorage["searchKey"];
	if(searchKey == ''){
		return;
	}
	$.get("/search?query="+searchKey ,function (data){
		appendResultToPage(div, data);
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
		window.location.href = '/result.html';
	}
});
