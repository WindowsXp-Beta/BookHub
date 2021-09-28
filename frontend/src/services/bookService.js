import {deleteRequest, getRequest, postRequest} from "../utils/ajax";

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
    const url = `/admin/book${id}`;
    deleteRequest(url, null, callback);
};

export const addBook = (data, callback) => {
    const url = `/admin/book`;
    postRequest(url, data, callback);
};

export const editBook = (data,callback) =>
{
    const url = `/admin/Book`;
    postRequest(url, data, callback);
};