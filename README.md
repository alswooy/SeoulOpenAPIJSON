# SeoulOpenAPI JSON 파싱
SeoulOpenAPI JSON 파싱
 - ERD와 구현영상 : https://drive.google.com/file/d/11zt1LOx8mVO_FCeLkp0Y4VqeYF8Sx9TI/view?usp=share_link
# 목적  
- 학습한 자바와 웹, 데이터베이스의 개념을 이용한 학습과 사용해보지 않은 SQLite 그리고 API활용 학습
 
# 개발 환경  
- 언어 : Java(jdk11), html, css, js
- 툴 : ecipse, exerd
- DB : SQLite
- 라이브러리 : lombok, sqlite-jdbc, okhttp3, gson
- 서버 : Tomcat v8.5
# 주요 기능 
 - 서울 OpenAPI 파싱 
 - json 파싱한 데이터 DB 저장
 - navigator.geolocation.getCurrentPosition을 활용한 내 위치 정보 
 - 위치 정보 DB에 저장
 - Json파싱한 데이터와 내위치 정보를 계산해서 가까운 순으로 20개 출력
 - 내가 검색한 위치 정보 목록 출력
 - 내가 검색한 위치 정보 목록 삭제(update)
