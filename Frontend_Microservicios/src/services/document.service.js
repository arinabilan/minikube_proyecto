import httpClient  from "../http-common";

const bankServiceBackendServer = import.meta.env.VITE_BANKSERVICE_BACKEND_SERVER
const bankServiceBackendPort = import.meta.env.VITE_BANKSERVICE_BACKEND_PORT

const uploadDocument = (clientId, documentId, formData) => {
    return httpClient.postForm(`/solicitude/file/${clientId}/${documentId}`, formData);
}

const getAllDocuments = () => {
    return httpClient.get('/solicitude/get/documents');
}

const getDocumentsByClientId = (id) => {
    return httpClient.get(`/solicitude/find/list/document/by/client/id/${id}`);
}

const getDocumentByTitle = (title) => {
    return httpClient.get(`/solicitude/find/document/by/title/${title}`);
}

const putDocument = (document) => {
    return httpClient.put(`/solicitude/updatedocument`, document);
}

const viewDocument = (documentId) => {
    return `http://${bankServiceBackendServer}:${bankServiceBackendPort}/solicitude/file/downloadById/${documentId}`;
}

export default {uploadDocument, getAllDocuments, getDocumentsByClientId, getDocumentByTitle, putDocument, viewDocument};