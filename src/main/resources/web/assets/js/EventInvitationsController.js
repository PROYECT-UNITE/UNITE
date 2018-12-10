var InvitationsController = (function () {
    var invitedEvents;
    var getEventInvitations = function (callback) {
        axios.get("http://localhost:8080/unite/events/invited/" + localStorage['UserLoggedIn'])
            .then(function (response) {

                invitedEvents = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(invitedEvents);
            });
    };
    var declineEventInvitation = function (eventId) {
    };

    return {
        getEventInvitations: getEventInvitations

    };
})();

function showInvitedEvents(events) {
    console.log(events)
    var body = document.getElementById("eventsInvitations");
    for (var i = 0; i < events.length; i++) {
        if (events[i]["owner"] != localStorage['UserLoggedIn'] && events[i]["assistantsState"][localStorage['UserLoggedIn']] =="indeterminate" ) {
            var tab = document.createElement("div");
            tab.setAttribute("class", "card");
            body.appendChild(tab);
            tab.innerHTML =
                '<div class="card-header card-head-inverse bg-primary">'
                + '<h4 class="card-title">' + events[i]["name"] + '</h4>'
                + '<a class="heading-elements-toggle"><i'
                + 'class="fa fa-ellipsis-v font-medium-3"></i></a>'
                + '<div class="heading-elements">'
                + '</div>'
                + '</div>'
                + '<div class="card-header">'
                + '<div class="card-content px-1">'
                + '<div class="card-body">'
                + '<p class="card-text">Event Description: <br/>' + events[i]["description"] + '</p>'
                + '<p class="card-text">Event date: <br/> ' + events[i]["date"] + '</p>'
                + '<p class="card-text">Event Location:<br/> ' + events[i]["location"] + '</p>'
                + '<p class="card-text">Event Creator:<br/> ' + events[i]["owner"] + '</p>'
                + '</div><br/>'
                + '<div class="form-group">'
                + '<button type="button" class="btn mr-1 mb-1 btn-success"><i class="fa fa-check-circle"></i> Accept</button>'
                + '<button type="button" class="btn mr-1 mb-1 btn-danger"><i class="fa fa-archive"></i> Decline</button>'
                + '</div>'
                + '</div>'
        }
    }
}