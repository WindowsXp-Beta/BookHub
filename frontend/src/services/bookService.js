import {deleteRequest, getRequest, postRequest, putRequest} from "../utils/ajax";

export const getBooks = (data, callback) => {
    const url = `/book`;
    getRequest(url, data, callback);
};

export const searchBooks = (data, callback) => {
    const url = `/search/book`;
    getRequest(url, data, callback);
};

export const getBookDetail = (id, callback) => {
    const url = `/book/${id}`;
    getRequest(url, null, callback);
};

export const deleteBook = (id, callback) => {
    const url = `/admin/book${id}`;
    deleteRequest(url, null, callback);
};

export const addBook = (data, callback) => {
    const url = `/admin/book`;
    postRequest(url, data, callback);
};

export const editBook = (data, callback) =>
{
    const url = `/admin/Book`;
    putRequest(url, data, callback);
};