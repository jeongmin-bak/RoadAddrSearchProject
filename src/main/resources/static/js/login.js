//navbar ul 숨김처리
window.onload = function (){
    const ulElement = document.querySelector('.navbar-nav');
    if(ulElement){
        console.log("함수실행")
        ulElement.style.display = 'none';
    }
}

$(document).ready(function () {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
});

const host = 'http://' + window.location.host;

function onLogin() {
    let username = $('#userid').val();
    let password = $('#password').val();

    $.ajax({
        type: "POST",
        url: `/api/user/login`,
        contentType: "application/json",
        data: JSON.stringify({userId: username, password: password}),
        dataType: "text"
    })
        .done(function (res, status, xhr) {
            console.log(xhr);
            alert('Login Success!')
            const token = xhr.getResponseHeader('Authorization');
            document.cookie = 'Authorization=' + token + '; path=/';
            $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
                jqXHR.setRequestHeader('Authorization', token);
            });
            window.location.href = host;
        })
        .fail(function (jqXHR, textStatus) {
            const errorMessage = jqXHR.responseText;
            alert(`Login Fail: " ${errorMessage}`);
        });
}