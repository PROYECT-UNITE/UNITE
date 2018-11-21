var InvitationsController = (function () {
    var user = "NicGarcia";
    var createdEvts;
    var getEventInvitations = function () {
        axios.get("http://localhost:8080/unite/event/invited/" + user)
            .then(function (response) {
                createdEvts = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(createdEvts);
            });
    };
    var updateEvent = function () {

    };
    var getCreatedEvents = function (callback) {
        axios.get("http://localhost:8080/unite/events/" + user)
            .then(function (response) {
                createdEvts = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(createdEvts);
            });
    };
    var getUser = function () {
        return user;
    };
    var editEventName=function(i,value){
        createdEvts[i]["name"]=value;
    };
    var saveEditedEvent=function(pos){
        axios.put("http://localhost:8080/unite/"+createdEvts[pos].id+"/rename/"+createdEvts[pos].name)
            .then(function (response) {
                location.reload(true);
                alert("Event name changed");
            })
            .catch(function (error) {
            })
            .then(function () {
            });
    }
    return {


    };
})();