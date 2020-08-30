/**
 * 优化后的UI版本
 * V1版
 * @param div
 * @param data
 * @returns
 */
function appendResultToPageV1(div, data){
	div.html("");

	//补充无结果展示
	if (data.t.length == 0){
		var imgContent = '<div style="text-align: center;"><img src="/img/nores.png" style="width: 60%;"></img></div>';
		div.append(imgContent);
		return;
	}

//	<a href="javascript:;" class="aui-flex">
//    <div class="aui-flex-box">
//        <h2>web前端高级工程师开发</h2>
//        <p>北京 5-10年 本科</p>
//        <span>
//            <em>周末双休</em>
//            <em>五险一金</em>
//            <em>年底双薪</em>
//            <em>节日福利</em>
//        </span>
//        <p>北京AUI素材网网络科技有限公司</p>
//    </div>
//    <div class="aui-monthly">
//        <h3>5万-7万月</h3>
//        <p>10月25</p>
//    </div>
//    </a>
	
	div.append(setData(data.t));
//    $(data.t).each(function (index, item) {
//    			// 放具体内容的div
//			var ahref = $('<a href="javascript:;" class="aui-flex"></a>');
//    			var subDiv = $('<div class="aui-flex-box"></div>');
//    			var secondDiv = $('<div class="aui-monthly"></div>');
//    			
//    			var title = $('<h2>'+item.title+'</h2>');
//    			var locDegreeYear = $('<p>'+item.location+ '|工作'+ item.yrOfExp + '年|' +item.degree+'</p>');
//    			var salary = $('<span><em>'+'级别:'+item.level+'</em>'+ '<em>年包:'+ item.totalComp + '</em>' + '<em>入职:'+ item.yrInCom + '年</em>'+ '<em>年终:'+ item.bonusComp + '年</em>'+'</span>');
//    			
//    			var company = $('<p>'+item.company+'</p>');
//    			var baseMon = $('<h3>'+item.baseMonthComp+'</h3><p>  </p>');
//    			
//            	// 加上内容
//    			subDiv.append(title);
//    			subDiv.append(locDegreeYear);
//    			subDiv.append(salary);
//    			subDiv.append(company);
//    			secondDiv.append(baseMon);
//    			
//            // 最后添加到页面中
//    			ahref.append(subDiv);
//    			ahref.append(secondDiv);
//    			// 最后添加到页面中
//            div.append(ahref);
//    });
}

function setData(data){
	var div = $('<div></div>');
	$(data).each(function (index, item) {
	// 放具体内容的div
	var ahref = $('<a href="javascript:;" class="aui-flex"></a>');
	var subDiv = $('<div class="aui-flex-box"></div>');
	var secondDiv = $('<div class="aui-monthly"></div>');
		
	var title = $('<h2>'+item.title+'</h2>');
	var locDegreeYear = $('<p>'+item.location+ '|工作'+ item.yrOfExp + '年|' +item.degree+'</p>');
	var salary = $('<span><em>'+'级别:'+item.level+'</em>'+ '<em>年包:'+ item.totalComp + '</em>' + '<em>入职:'+ item.yrInCom + '年</em>'+ '<em>年终:'+ item.bonusComp + '年</em>'+'</span>');
		
	var company = $('<p>'+item.company+'</p>');
	var baseMon = $('<h3>'+item.baseMonthComp+'</h3><p>  </p>');
		
    	// 加上内容
	subDiv.append(title);
	subDiv.append(locDegreeYear);
	subDiv.append(salary);
	subDiv.append(company);
	secondDiv.append(baseMon);
		
    // 最后添加到页面中
	ahref.append(subDiv);
	ahref.append(secondDiv);
	// 最后添加到页面中
	div.append(ahref);
	});
	return div[0].innerHTML;
}


/**
 * 结果页的查询按钮事件
 * @returns
 */
function getSalary(){
	//var div = $("#result_show");
	var div = $(".aui-job-list");
	$.get("/getSalary?begin=10&limit=10" ,function (data){
		appendResultToPageV1(div, data);
	});
}




