import fetch from 'node-fetch';
import crypto from 'crypto';
import {base_url, deviceNo, userId, key} from './constant.js'

const timestamp = Date.now().toString();
const token = crypto.createHash('md5').update(key + timestamp).digest('hex');

fetch(base_url + '/en/device/' + deviceNo + '/info', {
    method: 'GET',
    headers: {
        'time': timestamp,
        'token': token,
        'userId': userId
    }
})
    .then(response => response.json())
    .then(data => console.log(JSON.stringify(data)))
    .catch(error => console.error('Error:', error));
