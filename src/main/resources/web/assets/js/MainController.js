var controller = (function () {
    var user = "NicGarcia";
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

        axios.get("http://localhost:8080/unite/events/" + user)
            .then(function (response) {
                createdEvts=response.data;
            })
            .catch(function (error) {

            })
            .then(function () {
                callback(createdEvts);
            });
    };
    var getUser = function () {
        return user;
    };


    return {
        getUser: getUser,
        getCreatedEvents: getCreatedEvents

    };
})();

function showCreatedEvts(events){
    var card=document.getElementById("createdEvents");
    for(var evt in events){
        var tab=document.createElement("div");
        /*tab.setAttribute("id","tab"+evt.id);
        tab.setAttribute("role","tab");
        tab.setAttribute("class","card-header border-bottom-blue-grey border-bottom-lighten-2");
        document.createElement("a");
        tab.appendChild()*/
        tab.outerHTML='<div id="heading11" role="tab" class="card-header border-bottom-blue-grey border-bottom-lighten-2">'
        +'<a data-toggle="collapse" data-parent="#accordionWrap1" href="#accordion11" aria-expanded="true"'
        +'aria-controls="accordion11" class="h6 blue">Accordion Group Item #1</a>'
        +'</div>'
        +'<div id="accordion11" role="tabpanel" aria-labelledby="heading11" class="collapse show"'
        +'aria-expanded="true">'
        +'<div class="card-body">'
        +'<p class="card-text">Caramels dessert chocolate cake pastry jujubes bonbon.'
        +'Jelly wafer jelly beans. Caramels chocolate cake liquorice'
        +'cake wafer jelly beans croissant apple pie.</p>'
        +'</div>'
        +'</div>'

    }


}