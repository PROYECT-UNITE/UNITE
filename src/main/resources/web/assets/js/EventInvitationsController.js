var InvitationsController = (function () {

    var invitedEvents;

    var stompClient;

    var getEventInvitations = function (callback) {
        connectStomp();
        axios.get("/unite/events/invited/" + localStorage['UserLoggedIn'])
            .then(function (response) {

                invitedEvents = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(invitedEvents);
            });
    };
    var connectStomp = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        stompClient.connect({'Authorization':localStorage['AUTH_TOKEN']}, function (frame) {
            console.log('Connected: ' + frame);
        });

    };
    var answerInvitation = function (eventId,answer) {
        var state = {
            "username": localStorage['UserLoggedIn'],
            "state": answer
        }
        stompClient.send("/app/assistance."+eventId, {'Authorization':localStorage['AUTH_TOKEN']}, JSON.stringify(state));
        document.getElementById(eventId).remove();
    };

    var declineEventInvitation = function (eventId) {
        answerInvitation(eventId,"declined")
    };
    var acceptEventInvitation = function (eventId) {
        answerInvitation(eventId,"assistant")
    };

    return {
        getEventInvitations: getEventInvitations,
        acceptEventInvitation: acceptEventInvitation,
        declineEventInvitation: declineEventInvitation

    };
})();

function showInvitedEvents(events) {
    var body = document.getElementById("eventsInvitations");
    for (var i = 0; i < events.length; i++) {
        if (events[i]["owner"] != localStorage['UserLoggedIn'] && events[i]["assistantsState"][localStorage['UserLoggedIn']] == "pending") {
            var tab = document.createElement("div");
            tab.setAttribute("class", "card");
            tab.setAttribute("id",events[i]["id"] );
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
                + '<button type="button" onclick="InvitationsController.acceptEventInvitation(' + events[i]["id"] + ')" class="btn mr-1 mb-1 btn-success"><i class="fa fa-check-circle"></i> Accept</button>'
                + '<button type="button" onclick="InvitationsController.declineEventInvitation(' + events[i]["id"] + ')" class="btn mr-1 mb-1 btn-danger"><i class="fa fa-archive"></i> Decline</button>'
                + '</div>'
                + '</div>'
        }
    }
}