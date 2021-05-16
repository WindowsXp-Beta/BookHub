// import config from 'config';
import {postRequest} from "../utils/ajax";
import {history} from '../utils/history';
import {message} from 'antd';



export const login = (data) => {
    const url = `http://localhost:8080/login`;
    const callback = (data) => {
        if(data.status >= 0) {
            console.log(data.data);
            localStorage.setItem('user', JSON.stringify(data.data));
            history.push("/");
            message.success(data.msg);
        }
        else{
            message.error(data.msg);
        }
    };
    postRequest(url, data, callback);
};

export const logout = () => {
    const url = `http://localhost:8080/logout`;

    const callback = (data) => {
        if(data.status >= 0) {
            localStorage.removeItem("user");
            history.push("/login");
            message.success(data.msg);
        }
        else{
            message.error(data.msg);
        }
    };
    postRequest(url, {}, callback);
};

export const checkSession = (callback) => {
    const url = `http://localhost:8080/checkSession`;
    postRequest(url, {}, callback);
};

export const getCart = (userId, callback) => {
    const data = {userId: userId};
    const url = `http://localhost:8080/getCart`;
    postRequest(url, data, callback);
};

export const delCartItem = (data, callback) => {
    const url = `http://localhost:8080/deleteCart`;
    postRequest(url, data, callback);
};

export const addCart = (data,callback) => {
    const url = `http://localhost:8080/addCart`;
    postRequest(url, data, callback);
};
export const editCartItemNumber = (data,callback) =>
{
    const url = `http://localhost:8080/editCartItemNum`;
    postRequest(url, data, callback);
};