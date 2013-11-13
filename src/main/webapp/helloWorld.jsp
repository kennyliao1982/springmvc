<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript">
	$(function() {
		(function getMessages(){
            $.ajax({
                dataType: "json",
                url : "app/query/" + requestCode,
                //cache: false,
                success: function(res){
                	if (res.result) {
    					$('#result').append(res.code + ':' + res.result + '<br/>');
    					requestCode+=2;
    				}
                }
            }).always(function(){
                getMessages();
            });
        })();
	});
	var requestCode = 2;
	function getResponse() {
		$.ajax({
			dataType : "json",
			async : false,
			type : "GET",
			url : "app/query/" + requestCode,
			success : function(res) {
				if (res.result) {
					$('#result').append(res.code + ':' + res.result + '<br/>');
					requestCode+=2;
				}
			}
		});
	}
	//window.setInterval("getResponse()", 1500);
</script>

</head>
<body>
	<h1>results:</h1>
	<div id="result"></div>
</body>
</html>