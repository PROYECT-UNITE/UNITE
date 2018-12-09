var InvitationsController = (function () {
    var invitedEvents;
    var getEventInvitations = function () {
        axios.get("http://localhost:8080/unite/event/invited/" + controller.getUser())
            .then(function (response) {
                invitedEvents = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(createdEvts);
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
        getEventInvitations: getEventInvitations

    };
})();