function validEmail(obj){
    if(validEmailCheck(obj)==false){
        alert('올바른 이메일 주소를 입력해주세요.')
        obj.value='';
        obj.focus();
        return false;
    }
}

function validEmailCheck(obj){
    var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    return (obj.value.match(pattern)!=null)
}

function validatePassword() {
    var passwordInput = document.getElementById("password");
    var passwordError = document.getElementById("passwordError");

    if (!isValidPassword(passwordInput.value)) {
        passwordError.style.display = "inline";
    } else {
        passwordError.style.display = "none";
    }
}

function isValidPassword (str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}