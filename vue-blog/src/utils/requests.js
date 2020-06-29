import Axios from 'axios'

//第一种方式
export function request(config) {
    //创建一个axios实例
    const instance = Axios.create({
        baseURL: "http://localhost:8090/api",
        timeout: 5000
    });
    //发送真正的网络请求
    return instance(config);
}
//第二种方式
export function request1(config) {
    return new Promise(((resolve, reject) => {
        const instance = Axios.create({
            baseURL: "http://localhost:8080",
            timeout: 500,
        });
        instance(config).then(response => {
            resolve(response);
            console.log(response);
        }).catch(err => {
            reject(err);
            console.log(err);
        })
    }));

}
//第三种方式
export function request2(config, success, err) {
    //创建axios实例
    const instance = Axios.create({
        baseURL: "http://localhost:8080",
        timeout: 5000
    });
    //发送真正的网络请求
    instance(config).then(response => {
        success(response);
    }).catch(err => {
        err(err);
    })
}

//拦截器
// Axios.interceptors.request.use({
//
// });