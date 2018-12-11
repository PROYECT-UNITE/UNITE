var controller = (function () {

    var currentChatMessage;
    var events;

    var stompClient;

    var TOPIC_ASSISTANCE = "/topic/assistance";

    var TOPIC_MESSAGES = "/topic/newmessage";

    var TOPIC_LINKS = "/topic/newlink";

    var TOPIC_ADD_TO_GATHER = "/topic/additem";
    var TOPIC_REMOVE_TO_GATHER = "/topic/removeitem";
    var TOPIC_TAKE_TO_GATHER = "/topic/takechargeitem";

    var TOPIC_ADD_TO_POLL = "/topic/addtopic";
    var TOPIC_REMOVE_TO_POLL = "/topic/removetopic";
    var TOPIC_VOTE_TO_POLL = "/topic/votetopic";

    var TOPIC_ADD_TO_CHECKLIST = "/topic/additemchecklist";
    var TOPIC_REMOVE_TO_CHECKLIST = "/topic/removeitemchecklist";
    var TOPIC_TAKE_TO_CHECKLIST = "/topic/takechargeitemchecklist";

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

        stompClient.connect({'Authorization': localStorage['AUTH_TOKEN']}, function (frame) {
            console.log('Connected: ' + frame);
            // subscribe to /topic/assistance.{eventId} when connections succeed
            stompClient.subscribe(TOPIC_ASSISTANCE + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                showChangeAssistenceOfEvent(body.username, body.state);


            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/newmessage.{eventId} when connections succeed for chat messages
            stompClient.subscribe(TOPIC_MESSAGES + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                if(body.author!==localStorage['UserLoggedIn']){
                    showMessage(body.text,true);
                }

                console.log(body)

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/newlink.{eventId} when connections succeed for the wall messages
            stompClient.subscribe(TOPIC_LINKS + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo edit links dynamically

            }, {'Authorization': localStorage['AUTH_TOKEN']});


            // subscribe to /topic/additem.{eventId} when connections succeed to add items in gather
            stompClient.subscribe(TOPIC_ADD_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/removeitem.{eventId} when connections succeed to remove items in gather
            stompClient.subscribe(TOPIC_REMOVE_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/takechargeitem.{eventId} when connections succeed to take charge items in gather
            stompClient.subscribe(TOPIC_TAKE_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});


            // subscribe to /topic/addtopic.{eventId} when connections succeed to add items in poll
            stompClient.subscribe(TOPIC_ADD_TO_POLL + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/removetopic.{eventId} when connections succeed to remove items in poll
            stompClient.subscribe(TOPIC_REMOVE_TO_POLL + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/votetopic.{eventId} when connections succeed to vote items in poll
            stompClient.subscribe(TOPIC_VOTE_TO_POLL + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});


            // subscribe to /topic/additemchecklist.{eventId} when connections succeed to add items in checklist
            stompClient.subscribe(TOPIC_ADD_TO_CHECKLIST + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/removeitemchecklist.{eventId} when connections succeed to remove items in checklist
            stompClient.subscribe(TOPIC_REMOVE_TO_CHECKLIST + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/takechargeitemchecklist.{eventId} when connections to take charge items in checklist
            stompClient.subscribe(TOPIC_TAKE_TO_CHECKLIST + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});
        });

    };
    var sendMessage = function () {
        var state = {
            "author": localStorage['UserLoggedIn'],
            "text": currentChatMessage
        }
        stompClient.send("/app/newmessage." + getIdCurrentEvent(), {'Authorization': localStorage['AUTH_TOKEN']}, JSON.stringify(state));
        showMessage(currentChatMessage,false);

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
                console.log(event);
                callback(event);
            });
    };
    var getIdCurrentEvent = function () {
        return localStorage.getItem("id");
    };
    var setIdCurrentEvent = function (ev) {
        localStorage.setItem("id", ev);
    };
    var setCurrentChatMessage = function (msg) {
        currentChatMessage = msg;
    };


    return {
        getIdCurrentEvent: getIdCurrentEvent,
        setIdCurrentEvent: setIdCurrentEvent,
        getEvents: getEvents,
        sendMessage: sendMessage,
        setCurrentChatMessage: setCurrentChatMessage,
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
    numberOfAssistants = 0;
    for (var user in event["assistantsState"]) {
        if (user !== localStorage['UserLoggedIn']) {
            var tr = document.createElement("tr");
            invitedUsersTable.appendChild(tr);
            var html =
                '<tr>'
                + '<td class="text-truncate">' + user + '</td>'
                + '<td class="text-truncate">';
            if (event["assistantsState"][user] === "pending") {
                html = html + '<span id=' + user + '  class="badge badge-default badge-warning">Pending</span>';
            } else if (event["assistantsState"][user] === "declined") {
                html = html + '<span id=' + user + ' class="badge badge-default badge-danger">Declined</span>';
            } else {
                numberOfAssistants++;
                html = html + '<span id=' + user + ' class="badge badge-default badge-success">Assistant</span>';
            }
            html = html + '</td>'
                + '</tr>';
            tr.innerHTML = html;

        }
    }
    document.getElementById("confirmedAssistants").innerHTML = "<i></i>" + numberOfAssistants;
    showChatHistory(event.chat.record)
}
function showChatHistory(history){
    for(var i=0;i<history.length;i++){
        if(history[i]["author"]!==localStorage['UserLoggedIn']){
            showMessage(history[i]["text"],true);
        }else{
            showMessage(history[i]["text"],false);
        }
    }
}
function showChangeAssistenceOfEvent(user, status) {
    console.log(user, status);
    var state = document.getElementById(user);
    if (status === "declined") {
        state.outerHTML = '<span id=' + user + ' class="badge badge-default badge-danger">Declined</span>';
    } else {
        state.outerHTML = '<span id=' + user + ' class="badge badge-default badge-success">Assistant</span>';
        numberOfAssistants++;
        document.getElementById("confirmedAssistants").innerHTML = "<i></i>" + numberOfAssistants;
    }
}

function showMessage(msg,recived) {
    var chat = document.getElementById("chat");
    var message = document.createElement("div");
    message.setAttribute("class", "chat");
    if(recived){
        message.setAttribute("class", "chat-left");
    }
    chat.appendChild(message);
    message.innerHTML =
        '<div class="chat">'
        + '<div class="chat-avatar">'
        + '<a class="avatar" data-toggle="tooltip" href="#"'
        + 'data-placement="right" title=""'
        + 'data-original-title="">'
        + '<img src="../../../app-assets/images/portrait/small/avatar-s-1.png"'
        + 'alt="avatar" />'
        + '</a>'
        + '</div>'
        + '<div class="chat-body">'
        + '<div class="chat-content">'
        + '<p>'+msg+'</p>'
        + '</div>'
        + '</div>'
        + '</div>'
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


