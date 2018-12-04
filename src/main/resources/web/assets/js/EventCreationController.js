var newEvent = (function () {
	var eventCreator = {
	    "owner":"",
		"name": "",
		"type": "",
		"budget": 0,
		"owner": "",
		"location":"",
		"description":""
	};
	var eventDate;
	var eventGuests;

	var getEventName = function () {
		return eventCreator.name;
	};
	var createEvent = function () {
		eventCreator.owner=controller.getUser();
		axios.post("http://localhost:8080/unite/newEvent", eventCreator)
		.then(function (response) {
            location.reload(true);
            alert("Event Created");
            eventCreator = {
                "owner":"",
                "name": "",
                "type": "",
                "budget": 0,
                "owner": "",
                "location":"",
                "description":""
            };
		})
		.catch(function (error) {

		});
	};

	var setEventGuests = function (guests) {
		eventGuests = guests;
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
		eventDate = date;
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