/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var settingsServices = (function () {

    var url = '';

    var updatePassword = function () {
        var username = document.getElementById("username").value;
        var newPassword = document.getElementById("newPassword").value;
        var confirm = document.getElementById("confirmNewPassword").value;
        if(confirm === newPassword){
            newPassword = {"newPassword":newPassword};
            axios.put(url + "/unite/changePassword/" + username, newPassword).then(function (response) {
                alert("Password Updated Successfully. Please Log Back in Using Your New Password");
                window.location.href = url + "/login.html";
            }).catch(function (error) {
                alert(error.response.data);
            });
        }
        else alert("New password and confirmation are not equal");
    };

    var updatePasswordTest = function (username, newPassword) {
        var confirm = document.getElementById("confirmNewPassword").value;

        axios.put(url + "/unite/changePassword/" + username, newPassword).then(function (response) {
            alert("Password Updated Successfully. Please Log Back in Using Your New Password");
            window.location.href = url + "/login.html";
        }).catch(function (error) {
            alert(error.response.data);
        });
    };

    return {
        updatePassword: updatePassword,
        updatePasswordTest: updatePasswordTest
    };
})();
