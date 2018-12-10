/*=========================================================================================
		File Name: form-login.js
		
==========================================================================================*/
var loginServices = (function (){ 

    return {
       validate: function () { 
            var user = document.getElementById("user-name").value;
            var pwd = document.getElementById("user-password").value;
            axios.post("/unite/access",user,pwd).then(function (response) {
                console.log(response.data)
                if(response.data=="true"){
                    alert("Welcome " + user);
                    window.location.href = url+"/index.html";
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
