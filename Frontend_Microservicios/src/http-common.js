import axios from "axios";

const bankServiceBackendServer = import.meta.env.VITE_BANKSERVICE_BACKEND_SERVER
const bankServiceBackendPort = import.meta.env.VITE_BANKSERVICE_BACKEND_PORT

export default axios.create(
    {
        baseURL: `http://${bankServiceBackendServer}:${bankServiceBackendPort}`,
        headers: {
            'Content-Type': "application/json"
        }
    }
)