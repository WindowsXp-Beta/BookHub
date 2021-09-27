import {getRequest, postRequest, postRequestForm} from "../utils/ajax";

export const getBooks = (data, callback) => {
    const url = `/book`;
    getRequest(url, data, callback);
};


export const getBookDetail = (id, callback) => {
    const data = {id: id};
    const url = `/book/detail`;
    getRequest(url, data, callback);
};

export const deleteBook = (id, callback) => {
    const data = {id: id};
    const url = `http://localhost:8080/deleteBook`;
    postRequestForm(url, data, callback);
};

export const addBook = (data,callback) => {
    const url = `http://localhost:8080/addBook`;
    postRequest(url, data, callback);
};

export const editBook = (data,callback) =>
{
    const url = `http://localhost:8080/editBook`;
    postRequest(url, data, callback);
};