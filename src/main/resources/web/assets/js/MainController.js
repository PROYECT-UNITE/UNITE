var prueba;
var controller = (function () {
    var user = "SergioRt1";
    var events;


    var getEvents = function (callback) {
        axios.get("http://localhost:8080/events/invited/" + user)
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
        getEvents: getEvents

    };
})();

function showEvents(evts) {
    var body = document.getElementById("events");
    for (var i = 0; i < evts.length; i++) {
        var tab = document.createElement("div");
        tab.setAttribute("class", "col-lg-4 col-md-12");
        body.appendChild(tab);
        tab.innerHTML =
            + '<div class="card">'
            + '<div class="card-content">'
            + '<div class="card-body">'
            + '<h4 class="card-title info">Text Align Left</h4>'
            + '<p class="card-text"></p>'
            + '<p class="card-text"></p>'
            + '<a href="#" class="btn btn-outline-info">Go somewhere</a>'
            + '</div>'
            + '</div>'
            + '</div>';

    }
}