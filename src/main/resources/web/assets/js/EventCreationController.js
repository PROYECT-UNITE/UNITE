
var newEvent = (function () {
	var eventName;
	var eventType;
	var eventBudget;

	var getEventName = function () {
		return eventName;
	};
	var setEventName = function (name) {
		eventName = name;
	};
	var getEventType = function () {
		return eventType;
	};
	var setEventType = function (type) {
		eventType = type;
	};
	var getEventBudget = function () {
		return eventBudget;
	};
	var setEventBudget = function (budget) {
		eventBudget = budget;
	};

	return {
		getEventName: getEventName,
		setEventName: setEventName,
		getEventType: getEventType,
		setEventType: setEventType,
		getEventBudget: getEventBudget,
		setEventBudget: setEventBudget
	};
})();
