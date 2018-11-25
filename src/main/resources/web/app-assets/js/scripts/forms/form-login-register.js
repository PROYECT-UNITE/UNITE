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
            var user = {
                "username": document.getElementById("namesOfUser").value,
                "name" : document.getElementById("user-name").value,
                "mail" : document.getElementById("user-email").value,
                "password" : document.getElementById("user-password").value,
            };
            axios.post(url+"/unite/newAccount",user).then(function (response) {
                alert("User Created Succesfully");
                window.location.href = url+"/login.html";
                
            })
        }
    };
 })();