# Changsushop
- Changsushop 개발 관련 내용
- 상품 등록 및 주문 할 수 있는 주문관리 웹 서비스
------------
# 이력
- v0.0.1
  - 2022-01
    - changsushop 개발시작
    - DATABASE 기획 및 등록
    - AWS EC2, S3, RDS 등록
  - 2022-02
    - 회원 CRUD 개발
    - 상품 CRUD 개발
    - 주문 CRUD 개발
    - 배달 CRUD 개발
    - Spring Security login,logout,oauth2 개발
  - 2022-03
    - changsushop 깃 등록
    - aws 배포
  - 2022-03-13
    - 라이브러리 정리
    - 개발 구조 정리
    - README 정리
------------
# 구성
- 환경
  - window 10
  - github
  - mysql
  - Spring Boot 2.6.3
  - JQuery
  - java 1.8
  - QueryDSL
  - Spring Data JPA
  - thymeleaf

- 라이브러리
```
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6'
	implementation 'org.mindrot:jbcrypt:0.4'
	implementation "com.querydsl:querydsl-jpa:5.0.0"
	annotationProcessor "com.querydsl:querydsl-apt:5.0.0"
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2:1.4.199'
	runtimeOnly 'mysql:mysql-connector-java'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
```
------------
# 개발
- 구조
```
  src
  ├─main
  │  ├─java
  │  │  └─com
  │  │      └─changsu
  │  │          └─project
  │  │              └─changsushop
  │  │                  ├─auth //권한
  │  │                  │  ├─argumentresolver //login
  │  │                  │  ├─dto //세션DTO
  │  │                  │  ├─interceptor //interceptor 설정
  │  │                  │  └─security //Spring Security 설정
  │  │                  ├─controller //컨트롤러
  │  │                  │  ├─dto //컨트롤러 DTO
  │  │                  │  └─form //컨트롤러 Form
  │  │                  ├─domain //모든 Entity
  │  │                  │  ├─base //등록일, 수정일, 등록자, 수정자 
  │  │                  │  └─item //상품 (앨범, 책 ,영화)
  │  │                  ├─exception 예외처리
  │  │                  ├─exhandler
  │  │                  │  └─advice
  │  │                  ├─jpa //JPA관련 파일
  │  │                  ├─repository //모든 레포지토리
  │  │                  │  ├─category //카테고리 레포지토리
  │  │                  │  ├─delivery //배달 레포지토리
  │  │                  │  ├─item //상품 레포지토리
  │  │                  │  ├─member //회원 레포지토리
  │  │                  │  └─order //주문 레포지토리
  │  │                  └─service //모든 서비스
  │  │                      └─item //상품 서비스(앨범, 책, 영화)
  │  └─resources
  │      ├─static
  │      │  ├─css
  │      │  └─js
  │      └─templates
  │          ├─deliveries //배달 pages
  │          ├─error //오류 pages
  │          ├─fragments //layout pages
  │          ├─items //상품 pages
  │          │  ├─albums //앨범 pages
  │          │  ├─books //책 pages
  │          │  └─movies //영화 pages
  │          ├─members //회원 pages
  │          └─orders //주면 pages
  └─test
```

