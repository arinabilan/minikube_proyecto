import httpClient from "../http-common";

const getAll = () => {
   
    return httpClient.get('/register/');

}

export default {getAll};