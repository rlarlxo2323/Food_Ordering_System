# 2021년도 3학년 1학기 소프트웨어설계공학
<h2 align=left>1분반 3조 음식주문</h2>

## 목차

1. [배경](#1-배경)
2. [개발 환경](#2-개발-환경)
3. [주요 기능](#3-주요-기능)
4. [사용법](#4-사용법)
5. [발표 영상](#5-발표-영상)
6. [데모 영상](#6-데모-영상)

## 1. 배경

&nbsp;4차 산업혁명의 시작으로 지금 전 세계는 로봇에 대한 관심이 커지고 있다. 특히 요즘 가장 뜨거운 관심을 가지고 있는 분야는 바로 자율주행 자동차라 할 수 있다.

&nbsp; 실제 차량을 이용한 자율 주행 테스트는 위험성이 높고 비용적인 부담이 되기 때문에 본 프로젝트는 ROS 프레임워크를 사용하여 터틀봇을 사용한 자율 주행 환경을 제공한다.

## 2. 개발 환경

```
* OS : Ubuntu 18.04 LTS
* Programming Language : Python2.7
* IDE : Pycharm 2.7.17
* Framework : Ros melodic
* Library : OpenCV
* Version control : Docker, Git
```

## 3. 주요 기능

1. 차단바 인식</br>
입력 영상에서 차단바를 인식하면 4초간 정지한다.  <br/><br/>
![차단바 인식](image/차단바인식.png)

2. 정지선 인식 및 정지</br>
Image에 대한 마스킹을 진행하여 정지선을 검출하고, 터틀봇을 3초간 정지시킨다.  <br/>
Canny 알고리즘을 사용하여 기울기가 0 이 될때까지 좌우로 움직여 수평을 맞춘다.<br/>
![정지선 인식](image/정지선검출.PNG)

3. 차선 인식 및 추적</br>
Canny 알고리즘을 사용하여 차선을 인식하고, 인식되는 차선에 따라 주행 방향을 설정한다.  <br/><br/>
![차선 인식](image/차선인식.png)

4. 정지 표지판 인식 및 정지</br>
정지 표지판의 윤곽선을 검출하여 터틀봇을 3초간 정지시킨다.  <br/><br/>
![정지표지판 인식](image/정지표지판인식.png)

5. 장애물 인식 및 정지</br>
LaserScan을 사용하여 전방에 장애물이 있는지 스캔하고, 인식되면 터틀봇을 정지시킨다.  <br/><br/>
![장애물 인식](image/장애물인식.PNG)

## 4. 사용법

터틀봇에 좌, 우 카메라 추가  
[kobuki.urdf.xacro](https://github.com/ads0070/deu_car/blob/master/kobuki.urdf.xacro) 참고
```
$ cd ~/catkin_ws/src/kobuki_description/urdf
$ gedit kobuki.urdf.xacro
$ cd ~/catkin_ws
$ catkin_make
```

launch 파일 및 car_state_machine.py 실행

```
$ roscd deu_car
$ source ./gazebo_env.sh
$ chmod +x ./scripts/blocking_bar_control.sh
$ chmod +x ./scripts/obstacle_spawn.py
$ roslaunch deu_car car_test.launch #1차선
  roslaunch deu_car car_test.launch line:=2 #2차선
$ rosrun deu_car car_state_machine.py
```

## 5. 발표 영상

[![발표 영상](https://img.youtube.com/vi/rXEB4ofZOt8/0.jpg)](https://youtu.be/rXEB4ofZOt8)

## 6. 데모 영상

[![데모 영상](https://img.youtube.com/vi/Jb6omYSgUpM/0.jpg)](https://youtu.be/I3JtWWx0EbM)
