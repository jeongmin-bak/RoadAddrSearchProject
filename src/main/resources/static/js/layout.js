

$(function(){
    $(".navbar").load("/layout/nav.html");
})

$(function(){
    $("footer").load("/layout/footer.html");
})

function goToHome() {
    window.location.href = "/";
}

function deleteAuthToken() {
    document.cookie = "Authorization=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    //authToken = null;
}

function Logout() {
    deleteAuthToken();
    window.location.href = "";
}

function Login() {
    deleteAuthToken();
    window.location.href = "/users/login";
}

function Signup() {
    deleteAuthToken();
    window.location.href = "/users/signup";
}