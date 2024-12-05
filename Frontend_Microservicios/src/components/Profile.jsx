import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import documentService from "../services/document.service";
import { Typography, Container, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';

const Profile = () => {
    const navigate = useNavigate();
    // Obtener los datos del usuario desde el almacenamiento local
    const userData = JSON.parse(localStorage.getItem('userData'));
    const [documents, setClientDocuments] = useState([]);

    useEffect(() => {
        loadDocuments();
    }, []);

    const loadDocuments = async () => {
        const response = await documentService.getDocumentsByClientId(userData.id);
        console.log("lalala:", response.data)
        setClientDocuments(response.data);
    }

    const handleSimulateLoan = () => {
        // Redirigir a la página de simulación de crédito
        navigate('/simulateloan');
    };

    const handleSolicitude = () => {
        // Redirigir a la página de solicitud
        navigate('/solicitude');
    };

    const handleClienDates = () => {
        navigate("/solicitude2");
    };

    const handleSavingCapacity = () => {
        navigate("/solicitude3");
    };

    const handleDocuments = () => {
        navigate("/documents");
    };

    const handleNavigate = () => {
        navigate("/");
    }

    const handleSolicitudes = () => {
        navigate("/clientsolicitudes")
    }

    return (
        <Container>
            <Paper 
                style={{ 
                    padding: '10px', 
                    backgroundColor: '#9c27b0', // Color lila
                    marginBottom: '20px',
                    borderRadius: '8px',
                    position: 'fixed', 
                    top: '15px', 
                    right: '50px', 
                    left: '50px',
                    bottom: '610px'
                }}
            >
                <Typography variant="h4" gutterBottom style={{ color: 'white' }}>
                    Perfil de Usuario
                </Typography>
            </Paper>
            {userData ? (
                <>
                    <Paper 
                        style={{ 
                            padding: '10px', 
                            backgroundColor: '#D6C1E5', 
                            marginBottom: '20px',
                            borderRadius: '8px',
                            position: 'fixed', 
                            top: '77px', 
                            right: '50px', 
                            left: '50px',
                            bottom: '10px'
                        }}
                    >
                        

    
                    <Typography>Nombre: {userData.name} {userData.surname}</Typography>
                    <Typography>Correo: {userData.email}</Typography><br/><br/>
                    <Typography variant="h6" gutterBottom>
                            Documentos subidos por Usuario
                        </Typography>
                        {documents.length > 0 ? (
                            documents.map((doc) => (
                                <Typography key={doc.id}>
                                    - {doc.document.title} (Cargado el: {new Date(doc.fechaCarga).toLocaleDateString()})
                                </Typography>
                            ))
                        ) : (
                            <Typography>No hay documentos disponibles.</Typography>
                        )}
               
                    </Paper>

                    {/* Botón para simular el crédito */}
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleSimulateLoan}
                        style={{ marginTop: '20px', position: 'fixed', top: '60px', right: '1270px', left: '50px' }}
                    >
                        Simular el crédito
                    </Button>

                    {/* Botón para solicitud del crédito */}
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleSolicitude}
                        style={{ marginTop: '20px', position: 'fixed', top: '100px', right: '1270px', left: '50px' }}
                    >
                        Solicitud de credito
                    </Button>

                    <br/>
                    
                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleClienDates}
                        style={{ marginTop: '20px', position: 'fixed', top: '140px', right: '1270px', left: '50px' }}
                    >
                        Ingresar datos
                    </Button>

                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleSavingCapacity}
                        style={{ marginTop: '20px', position: 'fixed', top: '180px', right: '1270px', left: '50px' }}
                    >
                        Cuenta ahorro
                    </Button>

                    <br/>

                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleDocuments}
                        style={{ marginTop: '20px', position: 'fixed', top: '220px', right: '1270px', left: '50px' }}
                    >
                        Subir documentos
                    </Button>

                    <Button 
                        variant="contained" 
                        color="primary" 
                        onClick={handleSolicitudes}
                        style={{ marginTop: '20px', position: 'fixed', top: '260px', right: '1270px', left: '50px' }}
                    >
                        Mis solicitudes
                    </Button>

                    <Button 
                        variant="contained" 
                        color="secondary" 
                        onClick={handleNavigate}
                        style={{ marginTop: '20px', position: 'fixed', top: '600px', right: '50px', left: '1270px' }}
                    >
                        Volver
                    </Button>
                    
                </>
            ) : (
                <Typography variant="body1">No hay datos del usuario disponibles.</Typography>
            )}
        </Container>
    );
};

export default Profile;
