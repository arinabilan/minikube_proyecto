import React, { useState, useEffect } from "react";
import loanService from "../services/loan.service";
import { Container, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper, Button, Box, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Observeloan = () => {
    const [loan, setLoan] = useState({});
    const navigate = useNavigate();
    const solicitudeId = JSON.parse(localStorage.getItem('solicitudeIdClient'));

    useEffect(() => {
        loadLoan();
    }, []);


    const loadLoan = async () => {
        const response = await loanService.getLoanBySolicitudeId(solicitudeId);
        setLoan(response.data);
    };

    const handleNavigate = () => {
        navigate("/profile");
    }

    const handleNavigate1 = (id) => {
        loanService.changeState(id);
        navigate("/profile");
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

            <Box sx={{ textAlign: 'center', mt: 4, mb: 2 }}>
                <Typography variant="h4" component="h2" gutterBottom>
                    Detalles de solicitud
                </Typography>
            </Box>

            <TableContainer component={Paper} style={{ marginTop: '40px' }}>
                <Table style={{ minWidth: '1200px' }}>
                    <TableHead>
                        <TableRow>
                            <TableCell>Costo Mensual</TableCell>
                            <TableCell>Seguro desgravamento</TableCell>
                            <TableCell>Seguro incendio</TableCell>
                            <TableCell>Comisiones</TableCell>
                            <TableCell>Costo total</TableCell>
                            <TableCell>Accion</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>{loan.monthlyCost} CLP</TableCell>
                            <TableCell>{loan.insuranceFee} CLP</TableCell>
                            <TableCell>{loan.insuranceFire} CLP</TableCell>
                            <TableCell>{loan.comision} CLP</TableCell>
                            <TableCell>{loan.totalCost} CLP</TableCell>
                            <TableCell>
                                <Button
                                    variant="contained"
                                    color="secondary"
                                    onClick={() => handleNavigate1(solicitudeId)}
                                >
                                    Aprobar
                                </Button>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>

            </TableContainer>

            
        </Container>
    );
};

export default Observeloan;