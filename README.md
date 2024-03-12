# 1. 프로젝트 정보

    학교 강의 "모바일앱프로그래밍실습"의 자유 주제 기말 개인 프로젝트
   
    앱: Android Studio, JAVA를 사용하여 개발
    서버: AWS, Flask, python을 사용하여 개발

    개발 기간: 2022/05

# 2. 프로젝트 구조도
<img src="https://github.com/slllldka/Tetris_App/assets/121309640/cfcc81da-3951-418f-8326-14cf6449d6dd" width="800" height="200"/>

# 3. 프로젝트 설명
  ### 1. 로그인
  <img src="https://github.com/slllldka/Tetris_App/assets/121309640/73cd645d-c00f-4b11-a95f-b81dfe2b77c0" width="200" height="400"/>
    
    id, pw 입력 후 Register 버튼으로 DB에 등록
    id가 이미 존재할 경우 등록 실패

    id, pw 입력 후 Start 버튼으로 로그인
    id, pw가 일치하지 않을 경우 로그인 실패
  
  ### 2. 로비 화면
  <img src="https://github.com/slllldka/Tetris_App/assets/121309640/983352d7-76cd-4a28-95db-f5278b3b8bab" width="200" height="400"/>
  
    인게임, 기록, 랭킹 화면으로 이동

  ### 3. 인게임 화면
  <img src="https://github.com/slllldka/Tetris_App/assets/121309640/45a52d32-0268-43ff-ad87-e76250f27c55" width="200" height="400"/>

    테트리스 게임 구현
    
    좌우 이동, 블록 회전(시계 방향), Hard drop, Soft drop, Hold
    블록 등장은 7-bag 적용
    
    블록이 화면을 벗어나서 쌓일 경우 게임 종료
    
    종료와 동시에 DB에 점수 및 날짜 기록
    그 점수가 사용자의 최고 점수라면 DB에 랭킹 업데이트

  ### 4. 기록 화면
  <img src="https://github.com/slllldka/Tetris_App/assets/121309640/e69601e3-6bd5-4c81-a5b5-1003203d4f70" width="200" height="400"/>

    DB에 저장된 사용자의 모든 기록(점수 및 날짜)을 보여줌

  ### 5. 랭킹 화면
  <img src="https://github.com/slllldka/Tetris_App/assets/121309640/a1b6c7e7-2b5a-4c2c-bd58-a9390b11cf71" width="200" height="400"/>
  
    DB에 저장된 랭킹을 보여줌
    

# 3. 기타

    현재는 AWS 사용을 중단하여 서버 접속이 불가하므로 소스코드 상의 http 요청 코드는 주석 처리를 해놓은 상태입니다.
  
