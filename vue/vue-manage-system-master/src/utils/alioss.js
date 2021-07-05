var OSS = require('ali-oss');
export function client() {
    var client = new OSS({
        region: 'oss-cn-hongkong',
        accessKeyId: 'LTAI4GDRuer7CbcUjncN1xa2',
        accessKeySecret: 'rrHlPjKp7PigsDLqfiXsLCAnH6P9IH',
        bucket: 'minshopimage'
    }) //后端提供数据
    return client
}