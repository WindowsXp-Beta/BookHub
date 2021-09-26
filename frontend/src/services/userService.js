// import config from 'config';
import {postRequest, postRequestForm, getRequest, deleteRequest} from "../utils/ajax";
import {history} from '../utils/history';
import {message} from 'antd';


export const login = (data) => {
    const url = `/login`;
    const callback = (response) => {
        if(response.status === 200) {
            console.log(response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
            history.push("/");
            message.success("登录成功");
        }
        else{
            message.error(response.data);
        }
    };
    postRequest(url, data, callback);
};

export const logout = () => {
    const url = '/logout';
    const callback = (response) => {
        if(response.status === 204) {
            localStorage.removeItem("user");
            message.success(response.data);
        }
        else{
            message.error(response.data);
        }
    };
    deleteRequest(url, {}, callback);
};

export const checkSession = (callback) => {
    const url = `/session`;
    getRequest(url, {}, callback);
};

export const getUsers = (data, callback) => {
    const url = `http://localhost:8080/getUsers`;
    postRequest(url, data, callback);
};

export const editUser = (data,callback) =>
{
    const url = `http://localhost:8080/editUser`;
    postRequest(url, data, callback);
};

export const deleteUser = (id, callback) => {
    const data = {id: id};
    const url = `http://localhost:8080/deleteUser`;
    postRequestForm(url, data, callback);
};

export const getCart = (data, callback) => {
    const url = `/cart`;
    getRequest(url, data, callback);
};

export const delCartItem = (data, callback) => {
    const url = `http://localhost:8080/deleteCart`;
    postRequest(url, data, callback);
};

export const addCart = (data,callback) => {
    const url = `/cart`;
    postRequest(url, data, callback);
};

export const editCartItemNumber = (data,callback) =>
{
    const url = `http://localhost:8080/editCartItemNum`;
    postRequest(url, data, callback);
};

export const getOrders = (userId, callback) => {
    const data = {userId: userId};
    const url = `http://localhost:8080/getOrders`;
    postRequest(url, data, callback);
};

export const addOrder = (data, callback) => {
    const url = `http://localhost:8080/addOrder`;
    postRequest(url, data, callback);
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