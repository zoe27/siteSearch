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
	div.append(setData(data.t));
}

function setData(data){
	var div = $('<div></div>');
	$(data).each(function (index, item) {
	// 放具体内容的div
	var ahref = $('<a href="javascript:;" class="aui-flex"></a>');
	var subDiv = $('<div class="aui-flex-box"></div>');
	var secondDiv = $('<div class="aui-monthly"></div>');

	var info = JSON.parse(decrypt(item.coreInfo));
	if (info.baseOfMont == undefined){
		info.baseOfMont = Math.ceil(info.baseComp / 12);
	}
	var title = $('<h2>'+item.title+'</h2>');
	var locDegreeYear = $('<p>'+item.location+ '|工作'+ item.yearOfExp + '年|' +item.degree+'</p>');
	var salary = $('<span><em>'+'级别:'+item.level+'</em>'+
						'<em>年包:'+ info.totalComp + '</em>' +
						'<em>入职:'+ item.yearInCome + '年</em>'+
						'<em>年终:'+ info.bounsComp + '/年</em>'+
						'<em>股票:'+ info.stockComp + '</em>'+
						'</span>');

	var company = $('<p>'+item.company+'</p>');
	var baseMon = $('<h3>'+info.baseOfMont+'</h3><p>  </p>');


	/*	var title = $('<h2>'+item.title+'</h2>');
		var locDegreeYear = $('<p>'+item.location+ '|工作'+ item.yrOfExp + '年|' +item.degree+'</p>');
		var salary = $('<span><em>'+'级别:'+item.level+'</em>'+ '<em>年包:'+ item.totalComp + '</em>' + '<em>入职:'+ item.yrInCom + '年</em>'+ '<em>年终:'+ item.bonusComp + '年</em>'+'</span>');

		var company = $('<p>'+item.company+'</p>');
		var baseMon = $('<h3>'+item.baseMonthComp+'</h3><p>  </p>');*/
		
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

function decryptTest(word) {
	var key = CryptoJS.enc.Utf8.parse("site");
	var decrypt = CryptoJS.AES.decrypt(word, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
	return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}

function levelCompare(company) {
	var div = $("#level");
	$.get("/salary/level?company="+company ,function (data){
		addLevel(div, data);
	});
}

function addLevel(div, data) {

	var divLevel = $('<div style="float:left; width:33%"></div>')

	var divTable = $('<div class="table-div" style="height: '+data.tableHeight+'px; margin-top: 3px;"></div>')
	var table = $('<table class="levelTable" style="height: 100%; width: 100%; border-collapse: collapse;"></table>')

	var tybody = $('<tbody></tbody>')

	$(data.o).each(function (index, item){
		if (item.high == 0 || item.height.startsWith("-")) {return}
		var contentTr = $('<tr class="position-row"' +
			'style="height: '+item.height+'; max-height: '+item.height+'; text-align: center; color: rgb(51, 51, 51); position: relative; background: rgba('+item.color+');">' +
			'<td><span class="span-f" style="font-size:1.1em;display:inline-block;">'+item.level+'</span>' +
			// '<br/>' +
			'<span' +
			'class="span-f" style="font-size:0.7em;display:inline-block;">'+item.median+'</span><br/><a' +
			'style="display:block" href="/company/Microsoft/salaries/Software-Engineer/SDE/"></a></td>' +
			'</tr>')
		tybody.append(contentTr)
	});

	table.append(tybody);
	divTable.append(table);
	divLevel.append(divTable);
	div.append(divLevel);

}





