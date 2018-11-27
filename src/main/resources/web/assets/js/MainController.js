var prueba;
var controller = (function () {
    var user = "NicGarcia";
    var createdEvts;

    var getEventName = function () {
        return eventCreator.name;
    };
    var updateEvent = function () {
        axios.post("http://localhost:8080/unite/newEvent", eventCreator)
            .then(function (response) {
                location.reload(true);
                alert("Event Created");
            })
            .catch(function (error) {

            });
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
        getUser: getUser,
        getCreatedEvents: getCreatedEvents,
        saveEditedEvent: saveEditedEvent,
        editEventName: editEventName

    };
})();