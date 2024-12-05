import React, { useEffect, useState } from "react";
import { TextField, Button, Typography, Container, Box, Paper } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import documentService from "../services/document.service";

const Documents = () => {
    const [documents, setDocuments] = useState([]);
    const [fileSalary, setFileSalary] = useState(null);
    const [fileDicom, setFileDicom] = useState(null);
    const [fileCapacity, setFileCapacity] = useState(null);
    const [fileContract, setFileContract] = useState(null);
    const [docSalary, setDocSalary] = useState(null);
    const [docDicom, setDocDicom] = useState(null);
    const [docCapacity, setDocCapacity] = useState(null);
    const [docContract, setDocContract] = useState(null);

    const navigate = useNavigate();
    const userData = JSON.parse(localStorage.getItem('userData'));

    useEffect(() => {
        loadDocumentTypes();
    }, []);

    const loadDocumentTypes = async () => {
        const response = await documentService.getAllDocuments();
        setDocuments(response.data);

        //Obtener los documentos específicos por su título
        const responseSalary = await documentService.getDocumentByTitle('Comprobante de ingresos de 2 años');
        const responseDicom = await documentService.getDocumentByTitle('Certificado Dicom');
        const responseCapacity = await documentService.getDocumentByTitle('Certificado de Cuenta Ahorro');
        const responseContract = await documentService.getDocumentByTitle('Contrato laboral');

        setDocSalary(responseSalary.data);
        setDocDicom(responseDicom.data);
        setDocCapacity(responseCapacity.data);
        setDocContract(responseContract.data);
    };

    const handleFileChange = (e, setFile) => {
        setFile(e.target.files[0]);
    };

    const handleUpload = (file, docType) => {
        if (!file) {
            alert("Por favor selecciona un archivo.");
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        documentService.uploadDocument(userData.id, docType.id, formData)
            .then(() => {
                alert(`Documento ${docType.title} subido exitosamente`);
            })
            .catch((error) => {
                console.error(`Error al subir el documento ${docType.title}:`, error);
                alert("Posiblemente ya subio ese documento");
            });
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Subir Documentos
            </Typography>

            {/* Comprobante de ingresos de 2 años */}
            <Paper elevation={3} sx={{ padding: 2, marginBottom: 2 }}>
                <Box>
                    <Typography variant="h6">{docSalary?.title || "Cargando..."}</Typography>
                    <input type="file" onChange={(e) => handleFileChange(e, setFileSalary)} />
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={() => handleUpload(fileSalary, docSalary)}
                        sx={{ marginTop: 1 }}
                    >
                        Subir Comprobante de ingresos
                    </Button>
                </Box>
            </Paper>

            {/* Certificado Dicom */}
            <Paper elevation={3} sx={{ padding: 2, marginBottom: 2 }}>
                <Box>
                    <Typography variant="h6">{docDicom?.title || "Cargando..."}</Typography>
                    <input type="file" onChange={(e) => handleFileChange(e, setFileDicom)} />
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={() => handleUpload(fileDicom, docDicom)}
                        sx={{ marginTop: 1 }}
                    >
                        Subir Certificado Dicom
                    </Button>
                </Box>
            </Paper>

            {/* Certificado de Cuenta Ahorro */}
            <Paper elevation={3} sx={{ padding: 2, marginBottom: 2 }}>
                <Box>
                    <Typography variant="h6">{docCapacity?.title || "Cargando..."}</Typography>
                    <input type="file" onChange={(e) => handleFileChange(e, setFileCapacity)} />
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={() => handleUpload(fileCapacity, docCapacity)}
                        sx={{ marginTop: 1 }}
                    >
                        Subir Certificado de Cuenta Ahorro
                    </Button>
                </Box>
            </Paper>
            {/* Contrato laboral */}
            <Paper elevation={3} sx={{ padding: 2, marginBottom: 2 }}>
                <Box>
                    <Typography variant="h6">{docContract?.title || "Cargando..."}</Typography>
                    <input type="file" onChange={(e) => handleFileChange(e, setFileContract)} />
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={() => handleUpload(fileContract, docContract)}
                        sx={{ marginTop: 1 }}
                    >
                        Subir Contrato laboral
                    </Button>
                </Box>
            </Paper>

            <Button 
                onClick={() => navigate("/profile")} 
                variant="outlined" 
                color="secondary" 
                sx={{ marginTop: 2 }}
            >
                Volver al perfil
            </Button>
        </Container>
    );
};

export default Documents;