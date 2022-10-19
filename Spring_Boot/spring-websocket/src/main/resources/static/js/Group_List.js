var stompClient = null;

//Step 1:
function connect() {
    var socket = new SockJS('/groupList');
    //Step 2
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        //Step 5
        stompClient.subscribe('/topic/chat', function (message) {
            var text = JSON.parse(message.body).content;

            showMessage(text);

            console.log("Message Parse: " + text);
        });

        //Step 7
        loadGroup();
    });

}

//Step 6
function showMessage(text) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(text));
    response.appendChild(p);
}

//Step 7
function loadGroup() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            console.log(xmlhttp.responseText); //Return list group

            //Load Option Group vào Select
            var groups = JSON.parse(xmlhttp.responseText);//Get List Groups after open and send
            var tbody = document.getElementById('tbodyGroup');
            var groupStr = ``;
            for (var index in groups) {
                var group = groups[index];
                // tbody.appendChild(createRowTable(group));
                groupStr += createRowTable(group);
            }
            tbody.innerHTML = groupStr;
        }
    };
    xmlhttp.open("GET", "/groups", true);
    xmlhttp.send();
}

function createRowTable(group) {
    let row = `<tr>
                <td>${group.name}</td>
                <td>${group.id}</td>
                <td><a href="group.html?id=${group.id}">Update</a></td>
                <td><a onclick="deleteGroup('${group.id}')">Delete</a></td>
            </tr>`;
    return row;

}

function deleteGroup(id) {
    console.log("Sending... " + id);
    //Step 4
    stompClient.send("/app/group/delete/" + id, {}, JSON.stringify({}));
    loadGroup();
}


