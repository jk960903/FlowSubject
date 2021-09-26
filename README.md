# FlowSubject
플로우 과제 제출 용 Git입니다. 



1.2	실행 방법
기본적으로 JDK 14, Apache-Tomcat이 필요합니다. 
1.	.war 파일로 실행하기 (window 10기준)
1.1	Apache-tomcat-8.5.71 버전 사용 권장
Apache-tomcat-8.5.71 버전을 설치후 conf 폴더의 server.xml 수정
<Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true"> 
아래에 
<Context path="" docBase="/FlowSubject" reloadable="true"/> 추가
(해당 작업을 하지 않을시 localhost:8080/FlowSubject/extension으로 접속해도 로직들이 정상 작동하지 않습니다.)
 
FlowSubject.war 파일을 apache-tomcat 폴더의 webapps폴더로 이동

bin 폴더의 startup.bat 실행

chrome 브라우저로 localhost:8080/extension 페이지 접속
해당 페이지 실행 가능 

2.	인텔리J를 통해 실행하기
https://github.com/jk960903/FlowSubject
  를 통해서 소스코드를 다운받고 

플로우 과제 폴더를 InterliJ Files탭에서 Open 후 Run 버튼 클릭 후 실행하시면 됩니다. 
  
  
chrome 브라우저로 localhost:8080/extension 페이지 접속
해당 페이지 실행 가능 
  
  JAVA(Spring Boot), JAVA 언어를 사용하였으며 Spring Boot 프레임워크를 사용하여 Web Page에서의 HTTP 요청을 담당하기 위한 API형식으로 구현하기 위해 사용하였습니다. 

JPA, Spring Boot 내에서 H2DB와의 연동 Repository , ServiceImpl – Service 로 구성하였습니다. 

H2 DB  : 자바 기반의 오픈소스 관계형 데이터베이스 관리 시스템으로 사용은 Embeded mode로 사용하였습니다. Embedded mode로 사용하게되면 Spring boot 프로젝트를 다시 실행할 경우 데이터는 증발하게 됩니다. 이런 이유로 초기 고정 확장자와 커스텀 확장자를 생성하는 DDL 을 schema.sql 파일을 통해 미리 정의해 두었으며 고정 확장자의 데이터는 data.sql을 통해 미리 insert 해두었습니다. (두 파일 모두 프로젝트 실행시 자동 실행되어 DB로 들어가게됩니다.)
 
JavaScript : html 페이지 내에서의 동적 로직을 표현하고 서버와의 연동을 위해 사용하였습니다. 
JQuery : 서버로부터 데이터를 받아온 것들을 동적으로 생성하기 위해 사용 
고정 확장자의 check후 페이지 새로고침후 체크 상태 유지 및 커스텀 확장자 추가시 동적으로 페이지에 추가 및 새로고침시 추가된 확장자등 불러오기 
AJAX: 웹 페이지와 서버를 연동하기 위해 사용하였습니다. 
Html & css : 웹페이지 간단한 디자인 및 초기 틀을 잡기위해 사용

3.고려한점
 1. 파일의 확장자 명에 대하여 
파일을 직접 서버로 받아와서 저장하진 않지만 먼저 파일확장자에 특수문자가 들어가는 경우를 고려해보았습니다. 특수문자가 들어가는 확장자명은 기본적으로 아주 적은 케이스가 있을것으로 고려하여 특수문자 , 한글 을 제외하고 알파벳과 숫자만으로 구성된 확장자명만이 들어갈수 있게 하였습니다. 

2. 기본적인 CRUD 요청
현재 과제에서 기본적인 요청은 고정확장자에서의 체크시 업데이트와 페이지 새로고침시 체크된 값들이 유지되는 요청입니다. 하지만 전제 조건에서 차단을 자주하는 확장자 명이라 하기에 추후 확장을 위해 Create 와 Delete가 필요할 것으로 보였습니다. 요구하는 Read와 Update 말고 Create 와 Delete는 추가구현 하였으며 이후 더 추가할 수 있는 것으로는 커스텀 확장자의 Table에 UseCount칼럼을 추가하여 실제 파일이 들어왔을 경우 UseCount를 Update를 하고 일정 횟수를 초과하게 된다면 고정 확장자 Table로 옮겨가는 형식으로 확장 할수 있도록 생각해보았습니다. (Custom-extension table에 UseCount칼럼과 초과시 Fixed로 옮기는 것에 대해서는 구현하지 않았습니다. )

커스텀 확장자에 대해서도 Update에 대한 것은 기본적으로 구현되어 있지 않아 추가로 확장 할 수 있도록 ID를 통해 검색하고 확장자명을 수정할 수 있는 API 또한 구현하였습니다. 

3.  알맞은 ErrorCode와 그에맞게 사용자에게 ErrorMessage표시 및 HTTP요청에 대해 알맞은 메서드를 잘 써야한다를 고려하였습니다. 
기본적인 CRUD요청에 대해 Get POST PATCH DELETE 메서드를 알맞게 사용하였습니다. PUT이 아닌 PATCH를 사용한 이유는 PUT method의 경우 update요청시 해당 데이터를 전부 바꿔버리고 PATCH 요청한 데이터만을 변경할수 있어 PATCH로 사용하게 되었습니다. 
에러가 발생하였을 때는 왜 발생하였는지에 대해 사용자가 알기 쉽도록 alert해주었습니다. 

기본적으로 각테이블마다 extension_name 칼럼에 unique 속성을 주어서 각 테이블엔 중복된 확장자명이 들어갈순 없지만 만약 따로 처리를 하지 않는다면 서버에러만을 받게되지만 따로 처리과정을 통해 알맞지않은 요청에 대해서는 그 이유가 무엇인지 보여주었습니다. 

4. 확장자의 중복
각테이블마다 extension_name 칼럼에 unique 속성을 주어서 각 테이블엔 중복된 확장자명이 들어갈 순 없지만 커스텀과 고정 확장자는 다른 테이블로 설계하였지만 고정 확장자에 있는 데이터가  커스텀 확장자에는 들어갈수 있는 오류를 처리하였으며 당연히 이미 들어간 데이터는 새로 다시 추가하거나 변경할 수 없도록 고려하여 개발하였습니다. 
