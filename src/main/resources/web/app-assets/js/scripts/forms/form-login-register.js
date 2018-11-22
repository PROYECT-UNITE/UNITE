$(document).ready(function(){
	'use strict';
	//Login Register Validation
	if($("form.form-horizontal").attr("novalidate")!=undefined){
		$("input,select,textarea").not("[type=submit]").jqBootstrapValidation();
	}

	// Remember checkbox
	if($('.chk-remember').length){
		$('.chk-remember').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
		});
	}
});

var registrationServices = (function (){
     var url = '';
     return {
        signUp : function (){
            var user  = document.getElementById("namesOfUser").value;
            var names = document.getElementById("user-name").value;
            var email = document.getElementById("user-email").value;
            var pwd = document.getElementById("user-password").value;
            axios.post(url+"/unite/newAccount?username="+user+"&pwd="+pwd+"&mail="+email+"&name="+names).then(function (response) {
                alert("User Created Succesfully");
                window.location.href = url+"/login.html";
                
            })
        }
    };
 })();