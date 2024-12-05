import React, { useState, useEffect } from 'react';
import clientService from '../services/client.service';
import loanService from '../services/loan.service';
import { Button, TextField, FormControlLabel, Checkbox, Typography, Container, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const LoanSolicitude3 = () => {
    const navigate = useNavigate();

    const userData = JSON.parse(localStorage.getItem('userData'));

    //SECTOR PARA OBJETO SAVINGCAPACITY (CAPACIDAD DE AHORRO)
    const [balance, setBalance] = useState(0); //saldo enn cuenta ahorro que sera para R71:saldo minimo requerido
    const [withdrawals, setWithdrawals] = useState(0); //suma de retiros en ultimos 12 meses R72:Historial de ahorro consistente
    const [withdrawal, setWithdrawal] = useState(false); //historial de ahorro consistente: ¿Tiene Usted ahorro consistente en ultimos 12 meses? - (True o False)
    const [deposits, setDeposits] = useState(0); //suma de depositos mensuales de ultimos 12 meses R73:Depositos periodicos
    const [yearsSavings, setYearsSavings] = useState(0); //años de antiguidad de la cuenta de ahorro R74:Relacion saldo/Años antiguidad
    const [recentWithdrawals, setRecentWithdrawals] = useState(0); //suma de retiros en ultimos 6 meses R75:Retiros recientes
    

    const [savingCapacity, setSavingCapacity] = useState({}); //aqui se va a guardar JSON capacidad de ahorro del cliente

    useEffect(() => {
        
    }, []);

    const handleProfile = () => {
        navigate("/profile");
    }

    const handleSavingCapacity = (e) => {
        e.preventDefault();

        const savingSaveCapacity = {
            balance,
            withdrawals,
            withdrawal,
            deposits,
            yearsSavings,
            recentWithdrawals,
            client: {"id": userData.id}
        };
        
        clientService.savingCapacity(savingSaveCapacity)
            .then((response) => {
                console.log('Cuenta ahorro registrada:', response.data);
                setSavingCapacity(response.data);
                localStorage.setItem('savingCapacity', JSON.stringify(response.data));
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
                Registro cuenta ahorro 
            </Typography>

                <form onSubmit={handleSavingCapacity}>
                    <TextField
                        label="Saldo en cuenta ahorro"
                        type="number"
                        value={balance}
                        onChange={(e) => setBalance(e.target.value)}
                        fullWidth
                        required
                    />
                    
                    <TextField
                        label="Suma de retiros"
                        type="number"
                        value={withdrawals}
                        onChange={(e) => setWithdrawals(e.target.value)}
                        fullWidth
                        required
                    />
                    

                    <FormControlLabel
                        control={<Checkbox checked={withdrawal} onChange={(e) => setWithdrawal(e.target.checked)} />}
                        label="¿Tiene Usted ahorro consistente?"
                    />
                    <TextField
                        label="Suma depositos mensuales de ultimos 12 meses"
                        type="number"
                        value={deposits}
                        onChange={(e) => setDeposits(e.target.value)}
                        fullWidth
                        required
                    />

                    <TextField
                        label="Años con cuenta de ahorro"
                        type="number"
                        value={yearsSavings}
                        onChange={(e) => setYearsSavings(e.target.value)}
                        fullWidth
                        required
                    />
                    

                    <TextField
                        label="Suma de retiro en ultimos 6 meses"
                        type="number"
                        value={recentWithdrawals}
                        onChange={(e) => setRecentWithdrawals(e.target.value)}
                        fullWidth
                        required
                    />

                    <Button type="submit"  variant="contained" color="primary">
                        Registrar cuenta ahorro
                    </Button>
                    <Button onClick={handleProfile} variant="outlined" color="secondary">
                        Regresar al perfil
                    </Button>
                </form>
        </Container>
    );
};

export default LoanSolicitude3;