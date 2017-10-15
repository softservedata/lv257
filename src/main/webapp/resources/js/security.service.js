function getPrivileges() {

    var resourcesAPI = projectPathPrefix+ "/api/privilege/";
    var data;
    //var data = {};
    /*data["query"] = $("#query").val();*/
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : resourcesAPI,
        data : JSON.stringify(data),
        dataType : 'json',
        success : function(data) {
            console.log("SUCCESS: ", data);
            //display(data);
            displayTable(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}
/*function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}*/

function displayTable(data) {
    var list1 = [];
    var list2 = [];
    var list3 = [];
    for(i=0; i<data.length; i++){

        var privilegeType = data[i].privilegeType;
        console.log(privilegeType + ' - tut');

        if(privilegeType==="SYSTEM"){
            list1.push(data[i]);
        }
        if(privilegeType==='RESOURCE_TYPE'){
            list2.push(data[i]);
        }
        if(privilegeType==='PROPERTY'){
            list3.push(data[i]);
        }
    }
    //var list1 = data.systemPrivileges;
    $.each(list1, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "</tr>";
        console.log(item);
        $('#sp').append(eachrow);
    });

    //var list2 = data.resourceTypePrivileges;
    $.each(list2, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>TODO</td>"
            + "</tr>";
        console.log(item);
        $('#rtp').append(eachrow);
    });

    //var list3 = data.resourcePropertyPrivileges;
    console.log(list3);
    $.each(list3, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>TODO</td>"
            + "</tr>";
        console.log(item + "rpp");
        $('#rpp').append(eachrow);
    });

}

function getPrivileges2() {

    var resourcesAPI = projectPathPrefix + "/api/privilege/new/?rn=ROLE_ADMIN";
    var data;
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : resourcesAPI,
        data : JSON.stringify(data),
        dataType : 'json',
        success : function(data) {
            console.log("SUCCESS: ", data);
            //display(data);
            displayTable2(data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

function displayTable2(data) {
    var roleName = data.roleName;

    var ResTypePrivileges = new Array();
    var systemPrivilege = new Object();

    var typeRegEx = /^type:/;
    var propertyRegEx = /^property:/;

    console.log("hello before");
    $.each(data.privileges, function (index, item) {

        if (typeRegEx.test(index)){
            var res = index.slice(index.indexOf(':')+1);
            var typeName = res.substring(0,res.indexOf(':'));
            var accessType = res.substring(res.indexOf(':')+1);

            //ResTypePrivileges.push(new TypePrivilege({}));

            var checkIfExist = false;
            for(i=0; i<ResTypePrivileges; i++){
                if(ResTypePrivileges[i].name===typeName){
                    checkIfExist = true;
                    return;
                }
            }

            if(!checkIfExist){
                ResTypePrivileges[i].name=typeName;
            }

            ResTypePrivileges[i].activities[accessType] = item;


            console.log(typeName + " is type ???????? access type is - " + accessType);
            console.log(ResTypePrivileges[i]);
        }

        else if (propertyRegEx.test(index)){
            console.log(index + " is property !!!!!!!!!!!!!!");
        }

        else
            console.log(index + " is system ********");

        /*var eachrow =
        console.log(index + " rpp " + item);*/
    });
    console.log("hello after");

    /*
    var list1 = [];
    var list2 = [];
    var list3 = [];
    for(i=0; i<data.length; i++){

        var privilegeType = data[i].privilegeType;
        console.log(privilegeType + ' - tut');

        if(privilegeType==="SYSTEM"){
            list1.push(data[i]);
        }
        if(privilegeType==='RESOURCE_TYPE'){
            list2.push(data[i]);
        }
        if(privilegeType==='PROPERTY'){
            list3.push(data[i]);
        }
    }
    //var list1 = data.systemPrivileges;
    $.each(list1, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "</tr>";
        console.log(item);
        $('#sp').append(eachrow);
    });

    //var list2 = data.resourceTypePrivileges;
    $.each(list2, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>TODO</td>"
            + "</tr>";
        console.log(item);
        $('#rtp').append(eachrow);
    });

    //var list3 = data.resourcePropertyPrivileges;
    console.log(list3);
    $.each(list3, function (index, item) {
        var eachrow = "<tr>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>" + item.name + "</td>"
            + "<td>TODO</td>"
            + "</tr>";
        console.log(item + "rpp");
        $('#rpp').append(eachrow);
    });*/

}