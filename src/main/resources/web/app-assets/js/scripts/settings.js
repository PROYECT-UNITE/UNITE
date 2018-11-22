/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var settingsServices = (function (){
     var url = 'http://localhost:8080';
     return {
        updatePassword : function (){
            var username = document.getElementById("username").value;
            var newPassword = document.getElementById("newPassword").value;
            
            axios.put(url+"/unite/changePassword/"+username+"?newPassword="+newPassword+"").then(function (response) {
                alert("Password Updated Successfully. Please Log Back in Using Your New Password");
                window.location.href = url+"/login.html";
                
            })
        },
        updatePasswordTest : function (username,newPassword){
            
            axios.put(url+"/unite/changePassword/"+username+"?newPassword="+newPassword+"").then(function (response) {
                alert("Password Updated Successfully. Please Log Back in Using Your New Password");
                window.location.href = url+"/login.html";
                
            })
        }
    };
 })();
