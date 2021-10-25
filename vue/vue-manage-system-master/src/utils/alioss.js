var OSS = require('ali-oss');
export function client() {
    var client = new OSS({
        region: 'oss-cn-hongkong',
        accessKeyId: '',
        accessKeySecret: '',
        bucket: 'minshopimage'
    }) //后端提供数据
    return client
}
