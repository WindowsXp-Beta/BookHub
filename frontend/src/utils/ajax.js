import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';

let getRequest = (url, queryParams, callback) =>
    axios.get(
        url, {
            params: queryParams,
            withCredentials: true,
        }).then((response) => {
        console.log(response);
        callback(response);
    }).catch((error) => {
        callback(error);
    });

let postRequest = (url, requestBody, callback) =>
    axios.post(
        url, requestBody, {
            withCredentials: true,
        }).then((response) => {
        callback(response);
    }).catch((error) => {
        callback(error.response);
    });

let deleteRequest = (url, queryParams, callback) =>
    axios.delete(
        url, {
            params: queryParams,
            withCredentials: true,
        }).then((response) => {
            console.log(response);
            callback(response.data);
    }).catch((error) => {
        callback(error.response);
    });

let postRequestForm = (url, data, callback) => {
    let formData = new FormData();

    for (let p in data) {
        if (data.hasOwnProperty(p))
            formData.append(p, data[p]);
    }

    let opts = {
        method: "POST",
        body: formData,
        credentials: "include"
    };

    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

let postRequest_deprecated = (url, json, callback) => {

    let opts = {
        method: "POST",
        body: JSON.stringify(json),
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: "include"
    };

    fetch(url, opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            console.log(data);
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
};

export {getRequest, postRequest, deleteRequest, postRequestForm};