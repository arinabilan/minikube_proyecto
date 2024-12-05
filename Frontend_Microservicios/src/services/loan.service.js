import httpClient from "../http-common";

const getLoanRequirements = () => {
    return httpClient.get('/solicitude/find/all/requirement');
};

const getLoanRequirementById = (id) => {
    return httpClient.get(`/solicitude/find/loan/requirement/by/type/loan/id/typeid/${id}`);
};

const getLoanTypes = () => {
    return httpClient.get('/solicitude/gettype')
}

const createSolicitude = (credencials) => {
    return httpClient.post('/solicitude/', credencials);
}

const getAllSolicitudes = () => {
    return httpClient.get('/solicitude/');
}

const getSolicitude = (id) => {
    return httpClient.get(`/solicitude/${id}`);
}

const modificateSolicitude = (id, executive, state) => {
    return httpClient.put(`/solicitude/${id}/${state}/${executive}`);
}

const evaluateSolicitude = (id) => {
    return httpClient.get(`/loanEvaluation/evaluate/solicitude/by/solicitude/id/solicitude/${id}`);
}

const getPercent = (rate) => {
    return httpClient.get(`/solicitude/percent/rate/${rate}/rate`);
}

const getYears = (month) => {
    return httpClient.get(`/solicitude/${month}/years/loan`);
}

const getAllSolicitudesByClientId = (id) => {
    return httpClient.get(`/solicitude/${id}/client`);
}

const getAllSolicitudeByClientIdFromTracking = (clientId) => {
    return httpClient.get(`/tracking/getsolicitudes/${clientId}`);
}

const createLoanBySolicitudeId = (solicitudeId) => {
    return httpClient.get(`/tracking/createLoan/dd/dd/${solicitudeId}`);
}

const getLoanBySolicitudeId = (solicitudeId) => {
    return httpClient.get(`/tracking/${solicitudeId}`);
}

const changeState = (id) => {
    return httpClient.get(`/tracking/change/solicitude/by/solicitudeId/${id}`)
}

export default { getLoanRequirements, getLoanTypes, getLoanRequirementById, createSolicitude,
    getAllSolicitudes, modificateSolicitude, getSolicitude, getPercent, getYears, evaluateSolicitude, getAllSolicitudesByClientId, getAllSolicitudeByClientIdFromTracking, createLoanBySolicitudeId, getLoanBySolicitudeId, changeState };