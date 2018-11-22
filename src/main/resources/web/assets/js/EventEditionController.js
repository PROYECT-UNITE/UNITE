var prueba;
var eventEditionController = (function () {

    var createdEvts;

    var getEventName = function () {
        return eventCreator.name;
    };
    var updateEvent = function () {
        axios.post("http://localhost:8080/unite/newEvent", eventCreator)
            .then(function (response) {
                location.reload(true);
                alert("Event Created");
            })
            .catch(function (error) {

            });
    };

    var getCreatedEvents = function (callback) {
        axios.get("http://localhost:8080/unite/events/" + controller.getUser())
            .then(function (response) {
                createdEvts = response.data;
            })
            .catch(function (error) {

            })
            .then(function () {
                callback(createdEvts);
            });
    };

    var editEventName=function(i,value){
        createdEvts[i]["name"]=value;
    };

    var saveEditedEvent=function(pos){
        axios.put("http://localhost:8080/unite/"+createdEvts[pos].id+"/rename/"+createdEvts[pos].name)
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
        getCreatedEvents: getCreatedEvents,
        saveEditedEvent: saveEditedEvent,
        editEventName: editEventName

    };
})();

function showCreatedEvts(events) {
    prueba=events;
    var card = document.getElementById("createdEvents");
    for (var i=0;i<events.length;i++ ) {
        var tab = document.createElement("div");
        card.appendChild(tab);

        tab.innerHTML = '<div id="tab'+events[i].id+'" role="tab" class="card-header border-bottom-blue-grey border-bottom-lighten-2">'
            + '<a data-toggle="collapse" data-parent="#accordionWrap1" href="#tabPanel'+events[i].id+'" aria-expanded="true"'
            + 'aria-controls="tabPanel'+events[i].id+'" class="h6 blue collapsed">'+events[i].name+'</a>'
            + '</div>'
            + '<div id="tabPanel'+events[i].id+'" role="tabpanel" aria-labelledby="tab'+events[i].id+'" class="collapse"'
            + 'aria-expanded="false">'
            + '<div class="card-body">'
            + '<div class="row">'
            + '<div class="col-md-6">'
            + '<div class="form-group">'
            + '<label for="eventName'+events[i].id+'">'
            + 'Event Name :'
            + '</label>'
            + '<input type="text" class="form-control required" id="eventName'+events[i].id+'" name="firstName" value="'+events[i].name+'" oninput="controller.editEventName('+i+',this.value)"></input>'
            + '</div>'
            + '</div>'
            + '<div class="col-md-6">'
            + '<fieldset class="form-group">'
            + '<label for="eventDescriptionTextarea'+events[i].id+'">Description :</label>'
            + '<textarea class="form-control" id="eventDescriptionTextarea'+events[i].id+'"  oninput="eventEditionController.editEvent('+i+', this.value)" rows="3">'+events[i].description+'</textarea>'
            + '</fieldset>'
            + '<div class="row">'
            + '<div class="col-md-5 offset-md-5">'
            + '<button type="button" onclick="eventEditionController.saveEditedEvent('+i+')" class="btn btn-success btn-block">Save</button>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'


    }


}