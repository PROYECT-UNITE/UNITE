var newEvent = (function () {
    var eventCreator = {
        "owner": "",
        "name": "",
        "type": "",
        "budget": 0,
        "location": "",
        "description": "",
        "date": ""
    };
    var eventDate;
    var eventGuests;
    var users;

    var getEventName = function () {
        return eventCreator.name;
    };
    var createEvent = function () {
        eventCreator.owner = localStorage['UserLoggedIn'];
        axios.post("http://localhost:8080/unite/newEvent", eventCreator)
            .then(function (response) {
                inviteAllUsers(response.data);
                eventCreator = {
                    "owner": "",
                    "name": "",
                    "type": "",
                    "budget": 0,
                    "location": "",
                    "description": "",
                    "date": ""
                };
            })
            .catch(function (error) {

            });
    };
    var inviteUser = function (eventId, user) {
        axios.post("http://localhost:8080/unite/" + eventId + "/invite/" + user)
            .then(function (response) {

            })
            .catch(function (error) {

            });
    };
    var inviteAllUsers = function (eventId) {
        for (var i = 0; i < users.length; i++) {
            inviteUser(eventId, users[i]);
        }
        location.reload(true);
        alert("Event Created");
    }

    var getAllUsers = function (callback) {
        axios.get("http://localhost:8080/unite/AllUsers")
            .then(function (response) {
                users = response.data;
            })
            .catch(function (error) {
            })
            .then(function () {
                callback(users);
            });
    };

    var setEventGuests = function (guests) {
        eventGuests = $("#guestE-mail").select2('val');
    };
    var getEventGuests = function () {
        return eventGuests;
    };

    var setEventDescription = function (des) {
        eventCreator.description = des;
    };
    var getEventDescription = function () {
        return eventCreator.description;
    };
    var setEventName = function (name) {
        eventCreator.name = name;
    };

    var getEventDate = function () {
        return eventDate;
    };
    var setEventDate = function (date) {
        eventCreator.date = date;
    };
    var getEventType = function () {
        return eventCreator.type;
    };
    var setEventType = function (type) {
        eventCreator.type = type;
    };
    var getEventBudget = function () {
        return eventCreator.budget;
    };
    var setEventBudget = function (budget) {
        eventCreator.budget = budget;
    };
    var getEventLocation = function () {
        return eventCreator.location;
    };
    var setEventLocation = function (location) {
        eventCreator.location = location;
    };
    var getEventOwner = function () {
        return eventCreator.owner;
    };
    var setEventOwner = function (owner) {
        eventCreator.owner = owner;
    };

    return {
        createEvent: createEvent,
        getAllUsers: getAllUsers,
        getEventName: getEventName,
        getEventGuests: getEventGuests,
        setEventGuests: setEventGuests,
        setEventName: setEventName,
        getEventDescription: getEventDescription,
        getEventType: getEventType,
        setEventType: setEventType,
        getEventDate: getEventDate,
        setEventDate: setEventDate,
        setEventDescription: setEventDescription,
        getEventBudget: getEventBudget,
        setEventBudget: setEventBudget,
        setEventOwner: setEventOwner,
        getEventOwner: getEventOwner,
        setEventLocation: setEventLocation,
        getEventLocation: getEventLocation
    };
})();

function showUsers(users) {
    var body = document.getElementById("guestE-mail");
    for (var i = 0; i < users.length; i++) {
        if (users[i] != localStorage['UserLoggedIn']) {
            var opt = document.createElement("option");
            opt.setAttribute("value", users[i]);
            body.appendChild(opt);
            opt.innerHTML = users[i];
        }
    }
}