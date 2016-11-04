<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
	<head onload="/hello">
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>T3 • TodoNotMVC</title>
      <link href="Style.css" rel="stylesheet" type="text/css" media="all" />
      <link href="Base.css" rel="stylesheet" type="text/css" media="all" />
      <style>
      	.toDoInput{
      		font-size: 24px;
      	}
		.todo-list {
	    	margin: 0;
	    	padding: 0;
	    	list-style: none;
		}
		.todo-list li {
		    position: relative;
		    font-size: 24px;
		    border-bottom: 1px solid #ededed;
		}
		.todo-list li label {
		    white-space: pre-line;
		    word-break: break-all;
		    padding: 10px 10px;
		    margin-left: 45px;
		    display: block;
		    line-height: 1.2;
		    transition: color 0.4s;
		    background-color: #fff;
		    margin-left: 0px;
		}
		.todo-list li .destroy {
		    display: block;
		    position: absolute;
		    top: 0;
		    right: 10px;
		    bottom: 0;
		    width: 40px;
		    height: 40px;
		    margin: auto 0;
		    font-size: 30px;
		    color: #cc9a9a;
		    margin-bottom: 11px;
		    transition: color 0.2s ease-out;
		}
		button {
    margin: 0;
    padding: 0;
    border: 0;
    background: none;
    font-size: 100%;
    vertical-align: baseline;
    font-family: inherit;
    font-weight: inherit;
    color: inherit;
    -webkit-appearance: none;
    appearance: none;
    -webkit-font-smoothing: antialiased;
    -moz-font-smoothing: antialiased;
    font-smoothing: antialiased;
}
.todo-list li .destroy:after {
    content: '×';
}
button, input[type="checkbox"] {
    outline: none;
}
		
	</style>
	</head>
	<body >
	
			<section id="main" data-module="list">
				
				<div>
					<input class="toDoInput" type="textfield" style="padding: 20px; width: 100%"></input>
				</div>
				<ul class="todo-list">
				</ul>
				
			</section>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script>
		$( document ).ready(function() {
			
			$( ".toDoInput" ).keypress(function(event) {
				
			
				var keycode = (event.keyCode ? event.keyCode : event.which);
				if(keycode == "13"){
					
					var val = $(".toDoInput").val();
					$.ajax({
						url:"/addsave",
						data:"data="+val,
						sucess:function(data){
							alert(data);
						}
						
					});
					var myList = '<li id='+val+'><label>'+val+'<label><button class="destroy"></button></li>';
					$('.todo-list').append(myList);
					
				}
			});
			
			$( ".destroy" ).bind( "click", function() {
				  alert( "User clicked on 'foo.'" );
				    $(this).parent().remove();

				});
    		
		});
	
	</script>
	</body>
</html>
