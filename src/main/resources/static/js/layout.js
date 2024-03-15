$(function(){
    $(".navbar").load("/layout/nav.html");
})

$(function(){
    $("footer").load("/layout/footer.html");
})
function getAuthorizationToken() {
    const cookies = document.cookie.split('; ');

    for (const cookie of cookies) {
        const [name, value] = cookie.split('=');

        // 쿠키 이름 앞뒤의 공백 제거
        const cookieName = name.trim();

        if (cookieName === 'Authorization') {
            // 쿠키 값 앞뒤의 공백 제거 후 반환
            return value.trim();
        }
    }
    // 찾지 못한 경우
    return null;
}

function goToHome() {
    window.location.href = "/";
}

function deleteAuthToken() {
    document.cookie = "Authorization=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    authToken = null;
}

function Logout() {
    deleteAuthToken();
    window.location.href = "";
}

function Login() {
    deleteAuthToken();
    window.location.href = "/users/signin";
}

function Signup() {
    deleteAuthToken();
    window.location.href = "/users/signup";
}