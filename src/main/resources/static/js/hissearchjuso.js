const authToken = getAuthorizationToken();

$(document).ready(userAddrSearchHistory());

function userAddrSearchHistory(){
    $.ajax({
        type : "GET",
        url : "/check/user/search/history",
        headers:{
            'Authorization': authToken
        },
        dataType : 'json',
        success:
            function (data) {
                $('tbody tr').empty();
                for (let i = 0; i < data.length; i++) {
                    let tempHtml = addHTML(data[i], i+1)
                    $('tbody').append(tempHtml);
                }
            }
        ,

    })
}

function addHTML(data, index){
    let dateTime = new Date(`${data.searchTime}`);
    let year = dateTime.getFullYear();
    let month = String(dateTime.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 문자열로 변환합니다.
    let date = String(dateTime.getDate()).padStart(2, '0');

    let formattedDateTime = `${year}-${month}-${date}`;

    return `<tr id="user-history-"${index}>
                      <th scope="row">${index}</th>
                      <td><a href="#" onclick="showSearchAddr(this)">${data.searchJuso}</a></td>
                      <td>${formattedDateTime}</td>
                      <td>${data.searchCount}</td>
                      <td><button type="button" class="btn btn-secondary" onclick="deleteUserSearchHistory(this)">기록삭제</button></td>
                 </tr>
                `
}

function showSearchAddr(ele){
    juso = ele.textContent;
    $.ajax({
        url: "/juso/search/list",
        type: "POST",
        dataType : "json",
        data: JSON.stringify({
            juso: juso
        }),
        contentType : "application/json",
        success: function (response) {
            console.log("Success!");
            window.location.href = "/juso/search";
            localStorage.setItem("searchAddr", juso)
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function deleteUserSearchHistory(ele){
    let row = ele.parentNode.parentNode;
    let cells = row.getElementsByTagName("td");
    let searchJuso = cells[0].innerText;

    $.ajax({
        url: "/delete/juso/search/history",
        type: "POST",
        headers:{
            'Authorization': authToken
        },
        data: JSON.stringify({
            searchAddr: searchJuso
        }),
        contentType: 'application/json',
        success: function (response) {
            console.log("delete Success!");
            userAddrSearchHistory();
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}