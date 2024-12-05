import httpClient from "../http-common";

const loginClient = credentials => {
    
    return httpClient.post('/register/', credentials);
    
}



export default {loginClient};