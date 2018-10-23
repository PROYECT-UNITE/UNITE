var newEvent = (function () {
	var eventCreator = {
		"name": "",
		"type": "",
		"budget": 0,
		"owner": ""
	};
	var eventDate;
	var eventDescription;

	var getEventName = function () {
		return eventCreator.name;
	};
	var createEvent = function () {
		axios.post("http://localhost:8080/unite/", eventCreator)
		.then(function (response) {})
		.catch(function (error) {
			console.log(error);
		});
	};
	var setEventDescription = function (des) {
		eventDescription = des;
	};
	var getEventDescription = function () {
		return eventDescription;
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

	return {
		createEvent: createEvent,
		getEventName: getEventName,
		setEventName: setEventName,
		getEventDescription: getEventDescription,
		getEventType: getEventType,
		setEventType: setEventType,
		getEventDate: getEventDate,
		setEventDate: setEventDate,
		setEventDescription: setEventDescription,
		getEventBudget: getEventBudget,
		setEventBudget: setEventBudget
	};
})();