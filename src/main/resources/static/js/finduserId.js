// navbar ul 숨김처리
window.onload = function (){
    const ulElement = document.querySelector('.navbar-nav');
    if(ulElement){
        ulElement.style.display = 'none';
    }
}
$(document).ready(function () {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
});

const host = 'http://' + window.location.host;

function getVerificationNum() {
    console.log("인증번호 받기 함수 호출!")
    var sendEmail = $('#user-email').val();
    $.ajax({
        url: "/mail",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({email: sendEmail}),
        success: function (data) {
            alert("인증번호 발송");
            $("#sendMail").attr("value", data);

        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function checkVerificationNum(){
    let inputNum = $('#input-verification-number').val();
    let userEmail = $('#user-email').val();
    let userName = $('#name').val();

    $.ajax({
        url: "/mail/check/verification-num",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            inputNum: inputNum,
            userEmail: userEmail
        }),
        success: function(data){
            if(data == true){
                alert("이메일 인증에 성공하였습니다.");
                findUserId(userEmail, userName);
            }
        },
        error: function (request, status, error){
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    })
}

function findUserId(email, name){
    $.ajax({
        url: "/users/find/userid",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            email : email,
            name : name
        }),
        success: function (data){
            $("#find-userid").empty();
            $("#find-userid").append(
                `<h2 id="find-userid-result">아이디 : ${data.userId} 입니다.</h2>
                <button id="login-id-btn" onclick="location.href='/users/login'">로그인페이지로 이동</button>
                `
            )
        },
        error: function (request, status, error){
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    })
}