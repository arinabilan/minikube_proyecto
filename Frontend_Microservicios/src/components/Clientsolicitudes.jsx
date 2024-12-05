import React, { useState, useEffect } from "react";
import loanService from "../services/loan.service";
import { Container, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Clientsolicitudes = () => {
    const [allSolicitudes, setAllSolicitudes] = useState([]);
    const [showSolicitudes, setShowSolicitudes] = useState(false);
    const navigate = useNavigate();
    const userClient = JSON.parse(localStorage.getItem('userData'));

    useEffect(() => {
        loadSolicitude();
    }, []);

    const getStateFromSolicitude = (solicitud) => {
        switch (solicitud.state) {
            case 1:
                return 'En Revisión Inicial';
            case 2:
                return 'Pendiente de Documentación';
            case 3:
                return 'En Evaluación';
            case 4:
                return 'Pre-Aprobada';
            case 5:
                return 'En Aprobación Final';
            case 6:
                return 'Aprobada';
            case 7:
                return 'Rechazada';
            case 8:
                return 'Cancelada por el Cliente';
            default:
                return 'Sin revisar';
        }
    }

    const loadSolicitude = async () => {
        const response = await loanService.getAllSolicitudeByClientIdFromTracking(userClient.id);
        setAllSolicitudes(response.data);
    };

    const handleNavigate = () => {
        navigate("/profile");
    }

    const handleNavigate1 = (id) => {
        localStorage.setItem('solicitudeIdClient', id);
        navigate("/loanobserve");
    }

    return (
        <Container maxWidth="xl">
            <Button
                onClick={handleNavigate}
                variant="contained"
                color="secondary"
                style={{ position: 'fixed', top: '53px', right: '50px', left: '50px' }}>
                Volver al perfil
            </Button>

            
            <TableContainer component={Paper} style={{ marginTop: '40px' }}>
                <Table style={{ minWidth: '1200px' }}>
                    <TableHead>
                        <TableRow>
                            <TableCell>N</TableCell>
                            <TableCell>Tipo de Préstamo</TableCell>
                            <TableCell>Monto</TableCell>
                            <TableCell>Tasa de Interés</TableCell>
                            <TableCell>Fecha</TableCell>
                            <TableCell>Estado</TableCell>
                            <TableCell>Accion</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {allSolicitudes.map((solicitud, index) => (
                            <TableRow key={solicitud.id}>
                                <TableCell>{index + 1}</TableCell>
                                <TableCell>{solicitud.loanType.type}</TableCell>
                                <TableCell>{solicitud.amount.toLocaleString()} CLP</TableCell>
                                <TableCell>{(solicitud.interestRate * 100).toFixed(2)}%</TableCell>
                                <TableCell>{new Date(solicitud.date).toLocaleDateString()}</TableCell>
                                <TableCell>{getStateFromSolicitude(solicitud)}</TableCell>
                                <TableCell>
                                    {solicitud.state === 4 && ( // Mostrar botón solo si estado es 4
                                        <Button
                                            variant="contained"
                                            color="secondary"
                                            onClick={() => handleNavigate1(solicitud.id)}
                                        >
                                            Revisar detalles
                                        </Button>
                                    )}
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>

            </TableContainer>

            
        </Container>
    );
};

export default Clientsolicitudes;