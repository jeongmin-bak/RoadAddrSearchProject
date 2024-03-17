$(document).ready(getAuthorizationToken());
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

