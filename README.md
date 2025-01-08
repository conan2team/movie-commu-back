| 엔드포인트 | 메소드 | 설명 | 요청 파라미터 | 응답 |
|------------|--------|------|----------------|------|
| `/api/posts/write` | POST | 게시글 작성 | Body: `PostDto` | 200 OK |
| `/api/posts/list` | GET | 모든 게시글 조회 | - | 200 OK, `List<PostDto>` |
| `/api/posts/{id}` | GET | 특정 게시글 조회 | Path: `id` | 200 OK, `PostDto`<br>404 Not Found |
| `/api/posts/{id}` | PUT | 게시글 수정 | Path: `id`<br>Body: `Post` | 200 OK, `Post` |
| `/api/posts/{id}` | DELETE | 게시글 삭제 | Path: `id` | 204 No Content |
| `/api/posts/list/page` | GET | 페이징된 게시글 목록 | Query: `page` (기본값: 0)<br>Query: `size` (기본값: 10) | 200 OK, `Page<PostDto>` |
| `/api/posts/search/title` | GET | 제목으로 게시글 검색 | Query: `keyword`<br>Query: `page` (기본값: 0)<br>Query: `size` (기본값: 10) | 200 OK, `Page<PostDto>` |
| `/api/posts/search/content` | GET | 내용으로 게시글 검색 | Query: `keyword`<br>Query: `page` (기본값: 0)<br>Query: `size` (기본값: 10) | 200 OK, `Page<PostDto>` |
| `/api/posts/search` | GET | 제목 또는 내용으로 게시글 검색 | Query: `keyword`<br>Query: `page` (기본값: 0)<br>Query: `size` (기본값: 10) | 200 OK, `Page<PostDto>` |
