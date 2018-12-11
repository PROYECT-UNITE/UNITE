var controller = (function () {

    var events;

    var stompClient;

    var TOPIC_ASSISTANCE = "/topic/assistance";

    var getEvents = function (callback) {
        axios.get("http://localhost:8080/unite/events/invited/" + localStorage['UserLoggedIn'])
            .then(function (response) {
                events = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(events);
            });
    };
    var loadDashboardContent = function () {
        connectStomp();
        getEvent(showEventInformation);

    };

    var connectStomp = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        stompClient.connect({'Authorization':localStorage['AUTH_TOKEN']}, function (frame) {
            console.log('Connected: ' + frame);
            // subscribe to /topic/assistance.{eventId} when connections succeed
            stompClient.subscribe(TOPIC_ASSISTANCE + '.' +getIdCurrentEvent() , function (eventbody) { // 12 is an id for test
                var body=JSON.parse(eventbody.body)
                showChangeAssistenceOfEvent(body.username,body.state);
                console.log(body)

            },{'Authorization':localStorage['AUTH_TOKEN']});
            stompClient.subscribe("topic/newmessage." +getIdCurrentEvent() , function (eventbody) { // 12 is an id for test
                var body=JSON.parse(eventbody.body)
                showChangeAssistenceOfEvent(body.username,body.state);
                console.log(body)

            },{'Authorization':localStorage['AUTH_TOKEN']});
        });

    };
    var sendMessage = function (msg) {
        var state = {
            "username": localStorage['UserLoggedIn'],
            "state": msg
        }
        stompClient.send("/app/newmessage."+getIdCurrentEvent(), {'Authorization':localStorage['AUTH_TOKEN']}, JSON.stringify(state));
        document.getElementById(eventId).remove();

    };
    var getEvent = function (callback) {
        var event
        axios.get("http://localhost:8080/unite/event/" + localStorage.getItem("id"))
            .then(function (response) {
                event = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(event);
            });
    };
    var getIdCurrentEvent = function () {
        return localStorage.getItem("id");
    };
    var setIdCurrentEvent = function (ev) {
        localStorage.setItem("id", ev);
    };



    return {
        getIdCurrentEvent: getIdCurrentEvent,
        setIdCurrentEvent: setIdCurrentEvent,
        getEvents: getEvents,
        loadDashboardContent: loadDashboardContent

    };
})();
var numberOfAssistants;
function showEvents(evts) {
    var body = document.getElementById("events");
    for (var i = 0; i < evts.length; i++) {
        if (evts[i]["assistantsState"][localStorage['UserLoggedIn']] == "assistant" || evts[i]["owner"] == localStorage['UserLoggedIn']) {
            var tab = document.createElement("div");
            tab.setAttribute("class", "card");
            body.appendChild(tab);
            tab.innerHTML =

                '<div class="card-content">'
                + '<div class="card-body">'
                + '<h4 class="card-title info">' + evts[i]["name"] + '</h4>'
                + '<p class="card-text">Description: ' + evts[i]["description"] + '</p>'
                + '<p class="card-text">Date: ' + evts[i]["date"] + '</p>'
                + '<a href="main-dashboard.html" onclick="controller.setIdCurrentEvent(' + evts[i]["id"] + ')" class="btn btn-outline-info">Go to event dashboard</a>'
                + '</div>'
                + '</div>';

        }
    }
}

function showEventInformation(event) {
    document.getElementById("eventName").innerHTML = event["name"];
    document.getElementById("eventLocation").innerHTML = event["location"];
    document.getElementById("eventBudget").innerHTML = '<i></i>' + event["budget"] + ' USD';
    var invitedUsersTable = document.getElementById("invitedUsersTable");
     numberOfAssistants=0;
    for (var user in event["assistantsState"]) {
        if (user !== localStorage['UserLoggedIn']) {
            var tr = document.createElement("tr");
            invitedUsersTable.appendChild(tr);
            var html =
                '<tr>'
                + '<td class="text-truncate">' + user + '</td>'
                + '<td class="text-truncate">';
            if (event["assistantsState"][user] === "pending") {
                html = html + '<span id='+user+'  class="badge badge-default badge-warning">Pending</span>';
            } else if (event["assistantsState"][user] === "declined") {
                html = html + '<span id='+user+' class="badge badge-default badge-danger">Declined</span>';
            } else {
                numberOfAssistants++;
                html = html + '<span id='+user+' class="badge badge-default badge-success">Assistant</span>';
            }
            html = html + '</td>'
                + '</tr>';
            tr.innerHTML=html;

        }
    }
    document.getElementById("confirmedAssistants").innerHTML = "<i></i>"+numberOfAssistants;
}
function showChangeAssistenceOfEvent(user,status){
    console.log(user,status);
    var state=document.getElementById(user);
    if (status === "declined") {
        state.outerHTML= '<span id='+user+' class="badge badge-default badge-danger">Declined</span>';
    } else {
        state.outerHTML= '<span id='+user+' class="badge badge-default badge-success">Assistant</span>';
        numberOfAssistants++;
        document.getElementById("confirmedAssistants").innerHTML ="<i></i>"+numberOfAssistants;
    }
}

var theWall = (function () {
    

    var getEvents = function (callback) {
        axios.get("http://localhost:8080/unite/events/invited/" + user)
            .then(function (response) {
                events = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(events);
            });
    };
    var getUser = function () {
        return user;
    };
    var getIdCurrentEvent = function () {
        return localStorage.getItem("id");
    };
    var setIdCurrentEvent = function (ev) {
        localStorage.setItem("id", ev);
    };

    var saveEditedEvent = function (pos) {
        axios.put("http://localhost:8080/unite/" + createdEvts[pos].id + "/rename/" + createdEvts[pos].name)
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
        getIdCurrentEvent: getIdCurrentEvent,
        setIdCurrentEvent: setIdCurrentEvent,
        getEvents: getEvents

    };
})();


