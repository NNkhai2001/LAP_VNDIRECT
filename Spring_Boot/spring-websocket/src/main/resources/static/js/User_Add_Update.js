var stompClient = null;
//name form listuser
var name = getRequestParam('name');

function connect() {
    var socket = new SockJS('/user');
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
        loadGroups();
        loadInfoUser(name);
    });
}

function showMessage(text) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(text));
    response.appendChild(p);
}

function save() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var email = document.getElementById('email').value;
    var age = document.getElementById('age').value;
    var groupId = document.getElementById('groupId').value;
    console.log("Sending...." + username);
    stompClient.send("/app/user", {}, JSON.stringify({
        'username': username,
        'password': password,
        'email': email,
        'age': age,
        'groupId': groupId,
        'content': getRequestParam('name')
    }));
}

function getRequestParam(name) {
    if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
}

function loadGroups() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            // console.log(xmlhttp.responseText);
            var groups = JSON.parse(xmlhttp.responseText);
            var select = document.getElementById('groupId');
            for (var index in groups) {
                var group = groups[index];
                select.appendChild(createOptionGroup(group));
            }
        }
    };
    xmlhttp.open("GET", "/groups", true);
    xmlhttp.send();
}

function createOptionGroup(group) {
    var option = document.createElement('option');
    option.appendChild(document.createTextNode(group.name));
    var att = document.createAttribute("value");
    att.value = group.id;
    option.setAttributeNode(att);
    return option;
}

function loadInfoUser(name) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            console.log(xmlhttp.responseText); //Return list group

            var users = JSON.parse(xmlhttp.responseText);
            document.getElementById('username').value = users.username;
            document.getElementById('password').value = users.password;
            document.getElementById('email').value = users.email;
            document.getElementById('age').value = users.age;
            document.getElementById('groupId').value = users.groupId;
            document.getElementById('username').readOnly = true;
        }
    };
    xmlhttp.open("GET", "getuser/" + name, true);
    xmlhttp.send();
}

