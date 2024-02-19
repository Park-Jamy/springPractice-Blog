const deletButton = document.getElementById('delete-btn'); // 버튼에 대한 요소 확인

if(deletButton){
    deletButton.addEventListener('click', event =>{ // 해당 버튼에 대한 클릭이 이루어 졌을 때
        let id = document.getElementById('article-id').value; // 현재 글에 대한 ID 값 추출
        fetch(`/api/articles/${id}`, { // DELETE 형식으로 ID 값을 가지고 URL "/api/articles/{id}"을 호출
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles'); // 해당 화면을 URL "/articles" 호출
        });
    });
}

const modifyButton = document.getElementById('modify-btn');

if(modifyButton) {
    modifyButton.addEventListener('click', event => { // 해당 버튼에 대한 클릭이 이루어 졌을 때
        let params = new URLSearchParams(location.search); // URL에 있는 "?"를 기준으로 되어 있는 값들을 객체 형태로 변환
        let id = params.get('id'); // ID 값에 대한 데이터 확인

        fetch(`/api/articles/${id}`, { // PUT 형식으로 Body에 작성된 데이터 값과 ID 값을 가지고 URL "/api/article/{id}"을 호출
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            }),
        })
        .then(() => {
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);  // 해당 화면을 URL "/articles/{id}" 호출
        });
    })
}

const createButton = document.getElementById("create-btn"); // 버튼에 대한 요소 확인

if(createButton){
    createButton.addEventListener("click", (event) => { // 해당 버튼에 대한 클릭이 이루어 졌을 때
        fetch("/api/articles",{ // POST 형식으로 Body에 작성된 데이터 값을 가지고 URL "/api/articles"을 호출
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        })
            .then(() => {
                alert("등록 완료되었습니다.");
                location.replace("/articles"); // 해당 화면을 URL "/articles" 호출
            });
    });
}
