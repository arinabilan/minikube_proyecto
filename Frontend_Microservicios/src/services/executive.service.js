import httpClient from "../http-common";

const loginExecutive = credentials => {
    return httpClient.post('/loanEvaluation/login/email', {
        email: credentials.email,
        password: credentials.password
    });
}

export default {loginExecutive};