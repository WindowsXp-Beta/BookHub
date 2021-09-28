import {deleteRequest, getRequest, postRequest} from "../utils/ajax";

export const getCart = (data, callback) => {
    const url = `/cart`;
    getRequest(url, data, callback);
};

export const delCartItem = (id, callback) => {
    const url = `/cart/${id}`;
    deleteRequest(url, null, callback);
};

export const addCart = (data, callback) => {
    const url = `/cart`;
    postRequest(url, data, callback);
};

export const editCartItemNumber = (id, data, callback) => {
    const url = `/cart/${id}`;
    postRequest(url, data, callback);
};

export const getCartNumber = (callback) => {
    const url = `/cart/number`;
    getRequest(url, null, callback);
}