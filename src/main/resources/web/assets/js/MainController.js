var controller = (function () {

    var currentChatMessage;
    var currentLink;
    var currentCkeckname;
    var events;
    var newItem;
    var newItemDescription;

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
        axios.get("/unite/events/invited/" + localStorage['UserLoggedIn'])
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

            // subscribe to /topic/theWallAt.{eventId} when connections succeed
            stompClient.subscribe('/topic/theWallAt'+"."+controller.getIdCurrentEvent() , function (eventbody) {
                var text = JSON.parse(eventbody.body);

            },{'Authorization':localStorage['AUTH_TOKEN']});

            stompClient.subscribe(TOPIC_MESSAGES + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                if (body.author !== localStorage['UserLoggedIn']) {
                    showMessage(body.text, true);
                }
                console.log(body)
            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/newlink.{eventId} when connections succeed for the wall messages
            stompClient.subscribe(TOPIC_LINKS + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                if (body.author !== localStorage['UserLoggedIn']) {
                       showNewLink(body.text);
                 }
            }, {'Authorization': localStorage['AUTH_TOKEN']});


            // subscribe to /topic/additem.{eventId} when connections succeed to add items in gather
            stompClient.subscribe(TOPIC_ADD_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                showNewItemOnGather(body);

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/removeitem.{eventId} when connections succeed to remove items in gather
            stompClient.subscribe(TOPIC_REMOVE_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                console.log(eventbody);
                //Todo

            }, {'Authorization': localStorage['AUTH_TOKEN']});

            // subscribe to /topic/takechargeitem.{eventId} when connections succeed to take charge items in gather
            stompClient.subscribe(TOPIC_TAKE_TO_GATHER + '.' + getIdCurrentEvent(), function (eventbody) {
                var body = JSON.parse(eventbody.body)
                changeUserOnChargeGather(body);

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
                var body = JSON.parse(eventbody.body)
                showNewCkeckbox(body);

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
    var sendLink = function () {
            var state = {
                "author": localStorage['UserLoggedIn'],
                "text": currentLink
            }
            stompClient.send("/app/newlink." + getIdCurrentEvent(), {'Authorization': localStorage['AUTH_TOKEN']}, JSON.stringify(state));
            showNewLink(currentLink);

        };

    var takeOnChargeItem=function(){
        var state = {
            "oncharge": localStorage['UserLoggedIn'],
            "name": newItem,
            "description":newItemDescription
        }
        stompClient.send("/app/takechargeitem." + getIdCurrentEvent(), {'Authorization': localStorage['AUTH_TOKEN']}, JSON.stringify(state));
    };
    var addItem=function(){
            var state = {
                "oncharge": localStorage['UserLoggedIn'],
                "name": newItem,
                "description":newItemDescription
            }
            stompClient.send("/app/additem." + getIdCurrentEvent(), {'Authorization': localStorage['AUTH_TOKEN']}, JSON.stringify(state));
        };

    var sendItemCheck = function () {
        var item = {
            "name": currentCkeckname
        };
        stompClient.send("/app/additemchecklist." + getIdCurrentEvent(), {'Authorization': localStorage['AUTH_TOKEN']}, JSON.stringify(item));
        // showNewCkeckbox(item);
    };

    var changecheck = function () {
    }
    var getEvent = function (callback) {
        var event;
        axios.get("/unite/event/" + localStorage.getItem("id"))
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
    var setNewEvent = function (evt) {
            newEvent = evt;
        };
    var setNewEventDescription = function (des) {
            newEventDescription = des;
      };
    var setCurrentLink = function (link) {
            currentLink = link;
        };

    var setCurrentCheckname = function (name) {
        currentCkeckname = name;
    }
    var saveInfoTheWall = function () {
        var wall = document.getElementById("theWallTextArea").value;
        stompClient.send("/app/theWallAt"+"."+controller.getIdCurrentEvent(), {'Authorization':localStorage['AUTH_TOKEN']}, JSON.stringify(wall));
    };


    return {
        getIdCurrentEvent: getIdCurrentEvent,
        setIdCurrentEvent: setIdCurrentEvent,
        getEvents: getEvents,
        sendMessage: sendMessage,
        setCurrentChatMessage: setCurrentChatMessage,
        setCurrentLink: setCurrentLink,
        sendLink: sendLink,
        addItem: addItem,
        setNewEvent: setNewEvent,
        setNewEventDescription: setNewEventDescription,
        loadDashboardContent: loadDashboardContent,
        saveInfoTheWall:saveInfoTheWall,
        sendItemCheck: sendItemCheck,
        setCurrentCheckname:setCurrentCheckname,
        changecheck:changecheck

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
function showNewItemOnGather(item){
    var table = document.getElementById("gather");
            var row = document.createElement("tr");
            table.insertBefore(row);
            var html=
            '<tr>'
            +'<td class="text-truncate">'
            +'beers'
            +'</td>'
            +'<td class="text-truncate">5 beers</td>'
            +'<td class="text-truncate" id="'+item.name+'">';
            if(item.oncharge!=null){
                html=html+' <span class="badge badge-default badge-success">'+item.oncharge+'</span>'
            }else{
                html=html+'<button type="submit" class="btn mr-1 mb-1 btn-success btn-sm" onclick="controller.takeOnChargeItem()">'
            }
            html=html+'<i class="fa fa-thumb-tack"></i> Take it'
            +'</button>'
            +'</td>'
            +'</tr>'
            item.innerHTML=html;
}

function changeUserOnChargeGather(item){
    var table = document.getElementById(item.name).innerHTML=' <span class="badge badge-default badge-success">'+item.oncharge+'</span>';
}
function showEventInformation(event) {
    document.getElementById("eventName").innerHTML = event["name"];
    document.getElementById("eventLocation").innerHTML = event["location"];
    document.getElementById("eventBudget").innerHTML = '<i></i>' + event["budget"] + ' USD';
    document.getElementById("theWallTextArea").value = event["wall"];
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
                html = html + '<span id=' + user + '  class="badge badge-default badge-warning">Pending</span>';
            } else if (event["assistantsState"][user] === "declined") {
                html = html + '<span id=' + user + ' class="badge badge-default badge-danger">Declined</span>';
            } else {
                numberOfAssistants++;
                html = html + '<span id=' + user + ' class="badge badge-default badge-success">Assistant</span>';
            }
            html = html + '</td>'
                + '</tr>';
            tr.innerHTML=html;

        }
    }
    document.getElementById("confirmedAssistants").innerHTML = "<i></i>" + numberOfAssistants;
    showChatHistory(event.chat.record);
    showLinkHistory(event.linkChat.record);
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
function showLinkHistory(history){
    for(var i=0;i<history.length;i++){
        if(history[i]["author"]!==localStorage['UserLoggedIn']){
            showNewLink(history[i]["text"]);
        }else{
            showNewLink(history[i]["text"]);
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
        + '</div>';
}

function showNewLink(link) {
    var list = document.getElementById("informationOfInterest");
    var item = document.createElement("li");
    item.setAttribute("class", "list-group-item");

    list.appendChild(item);
    item.innerHTML = '<a href="#" class="card-link">' + link + '</a>'
}
function showNewCkeckbox(item) {
    var list = document.getElementById("tablechecklist");
    var itemshow = document.createElement("tr");
    list.appendChild(itemshow);
    var html =
        '<td class="text-truncate">\n';
    if(item.onCharge === null || typeof item.onCharge === "undefined") {
        html = html + '<input type="checkbox" class="icheck-activity">\n';
    }else
        html = html + '<input type="checkbox" class="icheck-activity" checked>\n';
    html = html + '</td>\n' +
        '<td class="text-truncate">'+item.name+'</td>\n' +
        '<td class="text-truncate">\n';
    if(item.onCharge === null || typeof item.onCharge === "undefined") {
        html = html + '<span class="badge badge-default badge-danger">Pending</span>\n' +
            '</td>';
    }else
        html = html + '<span class="badge badge-default badge-success">Done</span>\n' + '</td>';
    itemshow.innerHTML = html;
}
