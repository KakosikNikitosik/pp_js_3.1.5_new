const URL = 'http://localhost:8080/api/admin';

function getUsers() {
     fetch(URL)
        .then(function (res) {
            return res.json()
        })
        .then(users => {


            let response = '';
            for (const user of users) {
                let roles = ''
                for (let role of user.roles) {
                    roles += role.name.replace("ROLE_", "") + ' ';
                }
                response +=
                    `<tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.username}</td>
                        <td>${roles}</td>
                        <td>
                        <button class="btn btn-info" type="button" data-bs-toggle="modal"
                        data-bs-target="#editModal" onclick="editModal(${user.id})">Edit</button>
                        </td>
                        <td>
                        <button class="btn btn-danger" type="button" data-toggle="modal"
                        data-target="#deleteModal" onclick="deleteModal(${user.id})">Delete</button>
                        </td>
                    </tr>`
            }
            document.getElementById('adminId').innerHTML = response;
        })
}

getUsers();


function addUser() {
    event.preventDefault();
    const selectRoles = document.getElementById('rolesNew');
    let firstName = document.getElementById('firstNameNew').value;
    let lastName = document.getElementById('lastNameNew').value;
    let age = document.getElementById('ageNew').value;
    let username = document.getElementById('emailNew').value;
    let password = document.getElementById('passwordNew').value;
    let roles = [];
    for (let i = 0; i < selectRoles.options.length; i++) {
        if (selectRoles.options[i].selected) {
            roles.push({
                id: selectRoles.options[i].value, name: 'ROLE_' + selectRoles.options[i].innerHTML,
                authority: 'ROLE_' + selectRoles.options[i].innerHTML
            })
        }
    }
    fetch(URL, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'firstName': firstName,
            'lastName': lastName,
            'age': age,
            'username': username,
            'password': password,
            'roles': roles
        })
    }).then((response) => {
        if (response.ok) {
            getUsers();
            window.location.reload();
        }

    })
}
function editModal(id) {
    fetch(URL + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (response) {
        return response.json();
    }).then(user => {
        document.getElementById('idUpdate').value = user.id;
        document.getElementById('firstNameUpdate').value = user.firstName;
        document.getElementById('lastNameUpdate').value = user.lastName;
        document.getElementById('ageUpdate').value = user.age;
        document.getElementById('emailUpdate').value = user.username;
        document.getElementById('passwordUpdate').value = user.password;
        document.getElementById('rolesUpdate').value = user.roles[0].name;

        const select = document.querySelector('#rolesUpdate').getElementsByTagName('option');
        for (let i = 0; i < select.length; i++) {
            if (select[i].value === user.roles[0].name)
                select[i].selected = true;
        }
    })
}

function editUser() {
    event.preventDefault();
    const selectRole = document.querySelector('#rolesUpdate').getElementsByTagName('option')
    let id = document.getElementById('idUpdate').value
    let firstName = document.getElementById('firstNameUpdate').value
    let lastName = document.getElementById('lastNameUpdate').value
    let age = document.getElementById('ageUpdate').value
    let username = document.getElementById('emailUpdate').value
    let password = document.getElementById('passwordUpdate').value
    let roleName = document.getElementById('rolesUpdate').value;
    let role = [];

    for (let i = 0; i < selectRole.length; i++) {
        if (selectRole[i].value === roleName) {
            role.push({id: (roleName === 'ROLE_ADMIN') ? 1 : 2, name: roleName, authority: roleName})
        }
    }
    fetch(URL, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'id': id,
            'firstName': firstName,
            'lastName': lastName,
            'age': age,
            'email': username,
            'password': password,
            'roles': role
        })
    }).then((response) => {
        if(response.ok) {
            $('#editModal').hide();
            getUsers();
            window.location.reload();
        }


    })
}
function deleteModal(id) {
    fetch(URL + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (response) {
        return response.json();
    }).then(user => {
        document.getElementById('diDelete').value = user.id;
        document.getElementById('firstNameDelete').value = user.firstName;
        document.getElementById('lastNameDelete').value = user.lastName;
        document.getElementById('ageDelete').value = user.age;
        document.getElementById('emailDelete').value = user.username;
        document.getElementById('rolesDelete').value = user.roles;
    })
}

function deleteUser() {
    let id = document.getElementById('diDelete').value

    fetch(URL + '/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(() => {
        $('#deleteModal').hide();
        getUsers();

    })
}

