import hashlib
import time
import requests
from constant import base_url, device_no, user_id, key

timestamp = str(int(time.time() * 1000))
token = hashlib.md5((key + timestamp).encode()).hexdigest()

url = f"{base_url}/en/device/{device_no}/open"
headers = {
    "time": timestamp,
    "token": token,
    "userId": user_id,
    "Content-Type": "application/json"
}

response = requests.post(url, headers=headers)
print(response.json())
