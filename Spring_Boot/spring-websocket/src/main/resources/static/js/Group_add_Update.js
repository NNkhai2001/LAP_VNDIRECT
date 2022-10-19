var stompClient = null;
//name form listuser
var id = getRequestParam('id');

function connect() {
    var socket = new SockJS('/group');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat',
            function (message) {
                var text = showMessage(JSON.parse(message.body).content
                    )
                ;
                return text;
            });
        // loadGroups();
        if (id != null) {
            loadInfoUser(id);
        }
    });
}

function showMessage(text) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(text));
    response.appendChild(p);
}

function save() {
    var groupname = document.getElementById('groupname').value;
    var groupId = document.getElementById('groupId').value;
    console.log("Sending...." + groupname);
    stompClient.send("/app/group", {}, JSON.stringify({
        'name': groupname,
        'id': groupId,
        'content': getRequestParam('id')
    }));
}

function getRequestParam(id) {
    if (id = (new RegExp('[?&]' + encodeURIComponent(id) + '=([^&]*)')).exec(location.search))
        return decodeURIComponent(id[1]);
}

// function loadGroups() {
//     var xmlhttp = new XMLHttpRequest();
//     xmlhttp.onreadystatechange = function () {
//         if (xmlhttp.readyState == XMLHttpRequest.DONE) {
//             // console.log(xmlhttp.responseText);
//             var groups = JSON.parse(xmlhttp.responseText);
//             var select = document.getElementById('groupId');
//             for (var index in groups) {
//                 var group = groups[index];
//                 select.appendChild(createOptionGroup(group));
//             }
//         }
//     };
//     xmlhttp.open("GET", "/groups", true);
//     xmlhttp.send();
// }
//
// function createOptionGroup(group) {
//     var option = document.createElement('option');
//     option.appendChild(document.createTextNode(group.id));
//     var att = document.createAttribute("value");
//     att.value = group.id;
//     option.setAttributeNode(att);
//     return option;
// }

function loadInfoUser(id) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            console.log(xmlhttp.responseText); //Return list group

            var groups = JSON.parse(xmlhttp.responseText);
            document.getElementById('groupname').value = groups.name;
            document.getElementById('groupId').value = groups.id;
        }
    };
    xmlhttp.open("GET", "getgroup/" + id, true);
    xmlhttp.send();
}

