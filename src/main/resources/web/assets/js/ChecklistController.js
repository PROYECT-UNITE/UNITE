var checklist = (function () {

    var checklist;

    var loadChecklist =  function () {
        checklist(loadData);
    };

    var loadData = function (checklist) {
        var table = document.getElementById("tablechecklist");
    };

    var checklist = function (callback) {
        axios.get("/unite/"+controller.getIdCurrentEvent()+"/checklist")
            .then(function (response) {
                checklist = response.data;
                callback(checklist);
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    return {
        loadChecklist:loadChecklist

    };
})();