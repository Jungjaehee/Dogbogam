import requests

url = 'http://localhost:8888/api/ai-dignosis/obesity'
image_path = './fat.jpg'

# 파일 및 데이터 준비
files = {'image': open(image_path, 'rb')}
data = {'weight': 10.5, 'breed': '비숑 프리제'}

# POST 요청
response = requests.post(url, files=files, data=data)

# 응답 출력
print(response.status_code)
print(response.json())
