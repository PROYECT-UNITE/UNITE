/*=========================================================================================
		File Name: form-login.js
		
==========================================================================================*/
var loginServices = (function (){ 
     var url = 'http://localhost:8080';    
    return {
       validate: function () { 
            var user = document.getElementById("user-name").value;
            var pwd = document.getElementById("user-password").value;
            axios.post(url+"/unite/access?username="+user+"&pwd="+pwd+"").then(function (response) {
                if(JSON.stringify(response.data) == "true"){
                    window.location.href = url+"/event-creation.html";
                }else{
                    alert("Wrong name or password");
                }
            })
        },
        redirect: function(validation){
            alert(JSON.stringify(validation));
            if(JSON.stringify(validation) == "true"){
                alert("Welcome");
            }else{
                alert("Wrong name or password");
            }
        }
    };
    
})();
