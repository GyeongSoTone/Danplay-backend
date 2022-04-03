# Danplay-backend

### 명령어 정리

1. **스프링부트 이미지 빌드**

```bash
docker build -t myspring/danplay-test-01 .
```

1. **레디스 컨테이너 실행**

```bash
# redis 
docker run --name redis-01 -d -p 6379:6379 redis:alpine

# redis cli
docker exec -it redis-01 redis-cli
```

1. **스프링부트 컨테이너 실행**

```bash
docker run --name danplay-01 -d -p 2021:2021 myspring/danplay-test-01
```

1. **Docker**

```bash
# 중지된 것도 확인
docker ps -a 

# 컨테이너 중지
docker stop

# 컨테이너 중지된 것도 삭제
docker rm
```