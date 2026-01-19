import fetch from 'node-fetch';
import crypto from 'crypto';
import {base_url, deviceNo, userId, key} from './constant.js'

const timestamp = Date.now().toString();
const token = crypto.createHash('md5').update(key + timestamp).digest('hex');

fetch(base_url + '/en/device/' + deviceNo + '/open', {
    method: 'POST',
    headers: {
        'time': timestamp,
        'token': token,
        'userId': userId,
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
