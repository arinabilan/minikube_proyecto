import React, { useState, useEffect } from 'react';
import clientService from '../services/client.service';
import loanService from '../services/loan.service';
import { Button, TextField, FormControlLabel, Checkbox, Typography, Container, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const LoanSolicitude2 = () => {
    const navigate = useNavigate();

    const userData = JSON.parse(localStorage.getItem('userData'));

    //SECTOR PARA OBJETO CLIENTDATES (DATOS DE CLIENTE)
    const [monthSalary, setMonthSalary] = useState(0); //sueldo mensual del cliente
    const [timeClient, setTimeClient] = useState(0); //cantidad de tiempo (en meses) que lleva siendo cliente del banco, en objeto es atributo date
    const [initialContract, setInitialContract] = useState(0); //cantidad de meses que lleva con el contrato laboral
    const [dicom, setDicom] = useState(false); //booleano para saber si está en dicom o no
    const [typeWorker, setTypeWorker] = useState(2); //tipo de trabajador(1: independiente, 2: no dependiente), por defecto no dependiente, en objeto es atributo type
    const [mediaSalary, setMediaSalary] = useState(0); //promedio de ingresos en ultimos 2 años
    const [monthlyDebt, setMonthlyDebt] = useState(0); //deuda mensual que está pagando cliente

    const [clientDate, setClientDate] = useState({}); //aqui se va a guardar JSON datos del cliente

    useEffect(() => {
        
    }, []);

    const handleProfile = () => {
        navigate("/profile");
    }

    const handleClientDates = (e) => {
        e.preventDefault();

        const savingClientDates = {
            monthSalary,
            date: timeClient,
            initialContract,
            dicom,
            type: typeWorker,
            mediaSalary,
            monthlyDebt,
            client: {"id": userData.id}
        };

        clientService.createDates(savingClientDates)
            .then((response) => {
                console.log('Datos registrados:', response.data);
                setClientDate(response.data);
                localStorage.setItem('clientData', JSON.stringify(response.data));
                alert("Listo!");
                navigate("/profile");
            })
            .catch((error) => {
                console.error('Error al registrar datos:', error);
            })
    }
   
    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Registro de datos 
            </Typography>

                <form onSubmit={handleClientDates}>
                    <TextField
                        label="Salario mensual"
                        type="number"
                        value={monthSalary}
                        onChange={(e) => setMonthSalary(e.target.value)}
                        fullWidth
                        required
                    />
                    
                    <TextField
                        label="Años con el banco"
                        type="number"
                        value={timeClient}
                        onChange={(e) => setTimeClient(e.target.value)}
                        fullWidth
                        required
                    />
                    

                    <TextField
                        label="Años con contrato"
                        type="number"
                        value={initialContract}
                        onChange={(e) => setInitialContract(e.target.value)}
                        fullWidth
                        required
                    />
                    

                    <FormControlLabel
                        control={<Checkbox checked={dicom} onChange={(e) => setDicom(e.target.checked)} />}
                        label="¿Está en DICOM?"
                    />
                    <TextField
                        label="Tipo de trabajador (1 - Independiente, 2 - No Independiente)"
                        type="number"
                        value={typeWorker}
                        onChange={(e) => setTypeWorker(e.target.value)}
                        fullWidth
                        required
                    />

                    <TextField
                        label="Promedio de ingresos en los últimos 2 años"
                        type="number"
                        value={mediaSalary}
                        onChange={(e) => setMediaSalary(e.target.value)}
                        fullWidth
                        required
                    />
                    

                    <TextField
                        label="Deuda mensual"
                        type="number"
                        value={monthlyDebt}
                        onChange={(e) => setMonthlyDebt(e.target.value)}
                        fullWidth
                        required
                    />

                    <Button type="submit"  variant="contained" color="primary">
                        Registrar datos
                    </Button>
                    <Button onClick={handleProfile} variant="outlined" color="secondary">
                        Regresar al perfil
                    </Button>
                </form>
        </Container>
    );
};

export default LoanSolicitude2;