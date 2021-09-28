// import config from 'config';
import {deleteRequest, getRequest, postRequest} from "../utils/ajax";
import {history} from '../utils/history';
import {message} from 'antd';


export const login = (data) => {
    const url = `/login`;
    const callback = (response) => {
        console.log(response.data);
        localStorage.setItem('user', JSON.stringify(response.data));
        history.push("/");
        message.success("登录成功");
    };
    postRequest(url, data, callback);
};

export const logout = () => {
    const url = '/logout';
    const callback = (response) => {
        localStorage.removeItem("user");
        message.success(response.data);
    };
    deleteRequest(url, null, callback);
};

export const checkSession = (callback, failureCallback) => {
    const url = `/session`;
    getRequest(url, null, callback, failureCallback);
};

export const getUsers = (data, callback) => {
    const url = `/admin/user`;
    postRequest(url, data, callback);
};

export const editUser = (data, callback) => {
    const url = `/admin/user`;
    postRequest(url, data, callback);
};

export const deleteUser = (id, callback) => {
    const data = {id: id};
    const url = `/admin/user`;
    deleteUser(url, data, callback);
};

export const register = (data) => {
    const url = `/user`;
    const callback = (response) => {
        if (response.status === 201) {
            localStorage.setItem('user', JSON.stringify(response.data));
            history.push("/");
            message.success("注册成功");
        } else {
            message.error(response.data);
        }
    };
    postRequest(url, data, callback);
};

export const checkUsername = (data, callback) => {
    const url = `/user/name`;
    getRequest(url, data, callback);
}