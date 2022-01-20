document.addEventListener('DOMContentLoaded',function(){
	
	getCategoryList();
	
	document.getElementById('btn_search').addEventListener('click',function(){
		getPrice();
	})
	
})


//category select 내부 option에 들어갈 카테고리 가져오기
function getCategoryList(){
	$.ajax({
		method : 'GET',
		dataType : 'json',
		url : `${contextPath}/getCategoryList`,
		success : function(result){
			console.debug(result);
			makeOptions(result, 'category');
		},
		error : function() {
			alert('서버연결실패')
		}
	})
}

function makeOptions(arr, id) {
	document.getElementById(id).innerHTML = `<option value="0">${id} 선택</option>`;
	arr.forEach(item => {
		document.getElementById(id).innerHTML 
		+= `<option value="${item}">${item}</option>`;
	})
}

function getProductList() {
	
	let category = findSelectedValue(document.querySelectorAll('#category > option'));
	if(category == 0) return;
	$.ajax({
		method : 'GET',
		dataType : 'json',
		data : {category : category},
		url : `${contextPath}/getProductList`,
		success : function(result) {
			console.debug(result);
			if(result.productList != null) {
				makeOptions(result.productList, 'product');
			} else if(result.error != null) {
				alert(error);
			}
		}, 
		error : function(){
			alert('서버연결실패');
		}
	})
}



function getPrice() {
	
	//검색 전 validation
	let category = findSelectedValue(document.querySelectorAll('#category > option'))
	let name = findSelectedValue(document.querySelectorAll('#product > option'))
	
	console.debug('category',category,'name',name);
	if(category == 0) {
		alert('카테고리를 선택하세요');
		return;
	} else if(name == 0) {
		alert('검색할 ' + category + '를 입력하세요');
		return;
	}
	
	$.ajax({
		method : 'GET',
		url : `${contextPath}/getPrice`,
		dataType : 'json',
		data : {category : category, name : name},
		success : function(result) {
			console.debug(result);
		},
		error : function() {
			alert('서버연결실패')
		}
	})
	
	
	
}

function findSelectedValue(nodes){
	for(let i = 0; i < nodes.length; i++) {
		if(nodes[i].selected) {
			return nodes[i].value;
		}
	}
}