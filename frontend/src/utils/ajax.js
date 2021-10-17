import axios from 'axios';
import {message} from "antd";

axios.defaults.baseURL = 'http://localhost:8080';

function handleError(response) {
    let errorMessage;
    if(typeof response === "undefined") {
        errorMessage = "网络连接出错";
    }
    else {
        console.log(response);
        switch (response.status) {
            case 404:
                errorMessage = `未找到请求内容：${response.data}`;
                break;
            case 500:
                errorMessage = `服务器错误：${response.data}，请通知网站管理员`;
                break;
            case 403:
                errorMessage = `请求被禁止：${response.data}`;
                break;
            default:
                errorMessage = `未知错误`;
                break;
        }
    }
        message.error(errorMessage);
}

export function getRequest(url, queryParams, callback, failureCallback = null) {
    axios.get(
        url, {
            params: queryParams,
            withCredentials: true,
        }).then((response) => {
        callback(response);
    }).catch((error) => {
        if (failureCallback !== null) {
            failureCallback(error.response);
        } else {
            console.log(error);
            handleError(error.response);
        }
    });
}

export function postRequest(url, requestBody, callback, requestParam = null, failureCallback = null) {
    axios.post(
        url, requestBody, {
            withCredentials: true,
        }).then((response) => {
        callback(response);
    }).catch((error) => {
        if(failureCallback !== null) {
            failureCallback(error.response);
        } else {
            handleError(error.response);
        }
    });
}

export function putRequest(url, requestBody, callback, requestParam = null, failureCallback = null) {
    axios.put(
        url, requestBody, {
            withCredentials: true,
        }).then((response) => {
        callback(response);
    }).catch((error) => {
        if(failureCallback !== null) {
            failureCallback(error.response);
        } else {
            handleError(error.response);
        }
    });
}

export function deleteRequest(url, queryParams, callback, failureCallback = null) {
    axios.delete(
        url, {
            params: queryParams,
            withCredentials: true,
        }).then((response) => {
        console.log(response);
        callback(response.data);
    }).catch((error) => {
        if(failureCallback !== null) {
            failureCallback(error.response);
        } else {
            handleError(error.response);
        }
    });
}