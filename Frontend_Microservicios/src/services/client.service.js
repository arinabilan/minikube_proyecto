import httpClient from "../http-common";

const getAll = () => {

    return httpClient.get('/register/');

}

const getClient = (id) => {
    return httpClient.get(`/register/${id}`);
}

const clientRegister = userData => {

    return httpClient.post('/register/', userData);

}

const loginClient = credentials => {

    //return httpClient.post('/api/v1/clients/login/email', credentials);
    return httpClient.post('/register/login/email', {
        email: credentials.email,
        password: credentials.password
    });

}

const savingCapacity = clientData => {
    return httpClient.post('/register/create/saving/capacity', clientData);
}

const updateCapacity = clientData => {
    return httpClient.put('/register/update/saving', clientData);
}

const createDates = clientData => {
    return httpClient.post('/register/createdates', clientData);
}

const updateDates = clientData => {
    return httpClient.put('/register/updatedates', clientData);
}

const getClientDates = (id) => {
    return httpClient.get(`/register/clientdates/${id}`);
}

const getClientCapacity = (id) => {
    return httpClient.get(`/register/get/capacity/${id}`);
}

const simulateAmount = (amount, interesRate, years) => {
    return httpClient.get(`/simulate/${amount}/${interesRate}/${years}`);
}

export default {getAll, clientRegister, loginClient, savingCapacity, createDates,
    getClientDates, getClientCapacity, getClient, simulateAmount, updateCapacity, updateDates};