const userUrl = 'http://localhost:8080/api/user';


function getUserPage() {
    fetch(userUrl).then(response => response.json())./*then(user => getInfoForNavbar(user)).*/then(user =>
        getInformationAboutUser(user))
}

async function getMyUser() {
    let res = await fetch('/api/user');
    let resUser = await res.json();
    userNavbarDetails(resUser);
}

window.addEventListener('DOMContentLoaded', getMyUser);

function userNavbarDetails(resUser) {
    let userList = document.getElementById('myUserDetails');
    let roles = ''
    for (let role of resUser.roles) {
        roles += role.name.replace("ROLE_", "") + ' ';
    }
    userList.insertAdjacentHTML('beforeend', `
        <b> ${resUser.username} </b> with roles: <a>${roles} </a>`);
}

function getInformationAboutUser(user) {

    let roles = ''
    for (let role of user.roles) {
        roles += role.name.replace("ROLE_", "") + ' ';
    }
    let result = '';
    result =
        `<tr>
        <th>${user.id}</th>
        <th>${user.firstName}</th>
        <th>${user.lastName}</th>
        <th>${user.age}</th>
        <th>${user.email}</th>
        <td><strong>${roles}</strong></td>
        </tr>`
    document.getElementById('userInfo').innerHTML = result;
}


getUserPage();