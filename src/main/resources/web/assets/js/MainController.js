var controller = (function () {
    var events;
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
        getEvent(showEventInformation);
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
    
    var getFunctionsReady = function (){
        theWall.connectAndSubscribe();
    };


    return {
        getIdCurrentEvent: getIdCurrentEvent,
        setIdCurrentEvent: setIdCurrentEvent,
        getEvents: getEvents,
        loadDashboardContent: loadDashboardContent,
        getFunctionsReady: getFunctionsReady
    };
})();

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
    var numberOfAssistants=0;
    for (var user in event["assistantsState"]) {
        if (user !== localStorage['UserLoggedIn']) {
            var tr = document.createElement("tr");
            invitedUsersTable.appendChild(tr);
            var html =
                '<tr>'
                + '<td class="text-truncate">' + user + '</td>'
                + '<td class="text-truncate">';
            if (event["assistantsState"][user] === "pending") {
                html = html + '<span class="badge badge-default badge-warning">Pending</span>';
            } else if (event["assistantsState"][user] === "declined") {
                html = html + '<span class="badge badge-default badge-danger">Declined</span>';
            } else {
                numberOfAssistants++;
                html = html + '<span class="badge badge-default badge-success">Assistant</span>';
            }
            html = html + '</td>'
                + '</tr>';
            tr.innerHTML=html;

        }
    }
    document.getElementById("confirmedAssistants").innerHTML = "<i></i>"+numberOfAssistants;
}

var theWall = (function () {
    
    var stompClient = null;
    
    var loadInfo = function () {
        
    };
    var saveInfo = function () {
       stompClient.send("/topic/theWallAt"+controller.getIdCurrentEvent(), {}, JSON.stringify(document.getElementById("theWallTextArea").value));
    };
    
    var connectAndSubscribe = function (){
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        
        
        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/theWallAt'+controller.getIdCurrentEvent(), function (eventBody) {
                 var text=JSON.parse(eventBody.body);
                 document.getElementById("theWallTextArea").value = text;               
            });
        });
    };
    
    
    return {
        loadInfo: loadInfo,
        saveInfo: saveInfo,
        connectAndSubscribe: connectAndSubscribe
    };
})();


