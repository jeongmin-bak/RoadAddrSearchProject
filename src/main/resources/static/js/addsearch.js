$(document).ready(checkReferrerLocalStorage());

function checkReferrerLocalStorage(){
    const savedSearchHistory = localStorage.getItem("searchAddr");
    if(savedSearchHistory != null){
        console.log("checkReferrerLocalStorage : " + savedSearchHistory);
        $('#address')[0].value = savedSearchHistory;
        localStorage.removeItem("searchAddr");
        searchAddress();
    }
}
function searchAddress(){
    var juso = $('#address').val();
    // 검색 이력에서 넘어오지 않았다면 검색 기록 저장 메소드 실행
    if(!document.referrer.includes('/addr/search/history')){
        saveSearchAddress(juso);
    }
    $.ajax({
        url: '/juso/search/list',
        type: 'POST',
        data: JSON.stringify({
            juso: juso
        }),
        contentType: "application/json",
        datatype: 'json',
        success:
            function (data) {
                $('section div.list-group').empty();
                for (let i = 0; i < data.length; i++) {
                    let tempHtml = addHTML(data[i])
                    $('section div.list-group').append(tempHtml);
                }
            }
        ,
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('에러 발생:', textStatus, errorThrown);
        }
    });
}

function saveSearchAddress(juso){
    console.log("주소 검색 저장 메소드 호출!");
    const authToken = getAuthorizationToken();
    $.ajax({
        url: '/juso/search/save',
        type: 'POST',
        headers: {
            'Authorization': authToken
        },
        data: JSON.stringify({
            searchAddr: juso
        }),
        contentType: "application/json",
        dataType: 'json',
    });
}

function addHTML(juso) {
    return `<a href="#" onclick="showMap(this)" class="list-group-item list-group-item-action">
            <div class="d-flex w-100 justify-content-between"">
                <h5 class="mb-1">${juso.roadAddr}</h5>
                <div class="fs-4 mb-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
                        <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10m0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6"/>
                    </svg>
                </div>
            </div>
            <p class="mb-1">지번주소 : ${juso.jibunAddr}</p>
            <small>우편번호 : ${juso.zipNo}</small>
        </a>
        `
}

function showMap(ele){
    var clickedElement = ele.closest('a.list-group-item')
    var h5Element = clickedElement.querySelector("h5");
    var pElement = clickedElement.querySelector("p");
    var smallElement = clickedElement.querySelector("small");

    $.ajax({
        url: "/show/map",
        type: "GET",
        data: {
            roadAddr: h5Element.textContent
        },
        success: function (response) {
            console.log("Success!");
            window.location.href = "search/map?roadAddr="+h5Element.textContent+"&jibunAddr="+pElement.textContent+"&zipNo="+smallElement.textContent;
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}