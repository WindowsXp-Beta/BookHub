import {getRequest, postRequest} from "../utils/ajax";

export const getAllOrders = (data, callback) => {
    const url = `http://localhost:8080/getAllOrders`;
    postRequest(url, data, callback);
};

export const getBestSales = (data, callback) => {
    const url = `http://localhost:8080/getBestSales`;
    postRequest(url, data, callback);
};

export const getBestCustomers = (data, callback) => {
    const url = `http://localhost:8080/getBestCustomer`;
    postRequest(url, data, callback);
}

export const getOneBestSales = (data, callback) => {
    const url = `http://localhost:8080/getOneBestSales`;
    postRequest(url, data, callback);
}

export const getOrders = (data, callback) => {
    const url = `/order`;
    getRequest(url, data, callback);
}

export const getOrderNumber = (callback) => {
    const url = `/order/number`;
    getRequest(url, null, callback);
}

export const addOrder = (data, callback) => {
    const url = `/order`;
    postRequest(url, data, callback);
}
