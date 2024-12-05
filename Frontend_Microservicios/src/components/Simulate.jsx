import React, { useEffect, useState } from "react";
import loanService from "../services/loan.service";
import clientService from "../services/client.service";
import { useNavigate } from 'react-router-dom';
import { TextField, Button, Typography, Container, MenuItem, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper } from '@mui/material';

const Simulate = () => {
    const [loanTypes, setLoanTypes] = useState([]);
    const [loanRequirements, setLoanRequirements] = useState([]);
    const [selectedType, setSelectedType] = useState('');
    const [amount, setAmount] = useState(0); //monto solicitado
    const [years, setYears] = useState(0); //años de prestamo
    const [interest, setInterest] = useState(0); //tasa de interes
    const [interestRate, setInterestRate] = useState(0); //tasa de interes de tipo de prestamo
    const [monthlyPayment, setMonthlyPayment] = useState(null);
    const [maxMonths, setMaxMonths] = useState(0);
    const [maxAmount, setMaxAmount] = useState(0);
    const [document, setDocument] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadLoanTypes();
        loadLoanRequirements();
    }, []);

    const loadLoanTypes = async () => {
        const response = await loanService.getLoanTypes();
        setLoanTypes(response.data);
    };

    const loadLoanRequirements = async () => {
        const response = await loanService.getLoanRequirements();
        setLoanRequirements(response.data);
    };

    const handleNavigate = () => {
        navigate("/profile");
    }

    const calculateMonthlyPayment = async () => {
        try{
            const requirement = loanRequirements.find(req => req.typeLoan.id === selectedType);
            if (!requirement) {
                alert("Por favor selecciona un tipo de préstamo válido.");
                return;
            }
        
            //setInterestRate(requirement.interestRate);
            //const response = await clientService.simulateAmount(amount, interest, years);

            const responserate = await loanService.getPercent(requirement.interestRate);
            setInterestRate(responserate.data);
            setMaxMonths(requirement.maxMonths);
            const responsemax = await loanService.getPercent(requirement.maxAmount);
            setMaxAmount(responsemax.data);
            const responseyears = await loanService.getYears(requirement.maxMonths);
            setMaxMonths(responseyears.data);
            setDocument(requirement.documents);
            
            const response = await clientService.simulateAmount(amount, interest, years);
            setMonthlyPayment(response.data);
        } catch (error) {
            console.error("Error al calcular el pago mensual:", error);
            alert("Ocurrió un error al calcular el pago mensual. Por favor, intenta de nuevo.");
        }
    };


    return (
        <Container>
            <Typography variant="h4" gutterBottom>Simulador de Crédito Hipotecario</Typography>
            <TextField
                select
                label="Tipo de Préstamo"
                value={selectedType}
                onChange={(e) => setSelectedType(e.target.value)}
                fullWidth
                margin="normal"
            >
                {loanTypes.map((type) => (
                    <MenuItem key={type.id} value={type.id}>
                        {type.type}
                    </MenuItem>
                ))}
            </TextField>
            <TextField
                label="Monto"
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Años"
                type="number"
                value={years}
                onChange={(e) => setYears(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Tasa de interes"
                type="number"
                value={interest}
                onChange={(e) => setInterest(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Button 
                variant="contained" 
                color="primary" 
                onClick={calculateMonthlyPayment}
                style={{ marginTop: '20px' }}
            >
                Calcular
            </Button>
            <Button onClick={handleNavigate} type="submit" variant="contained" color="secondary">
                        Volver al perfil
            </Button>
            {monthlyPayment && (
                <Typography variant="h6" style={{ marginTop: '20px' }}>
                    Pago Mensual: ${monthlyPayment}
                </Typography>
            )}

            <TableContainer component={Paper} style={{ marginTop: '20px' }}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Detalles</TableCell>
                            <TableCell align="right">Valor</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>Tasa de Interés</TableCell>
                            <TableCell align="right">{interestRate}%</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Cantidad maxima de años</TableCell>
                            <TableCell align="right">{maxMonths}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Monto Máximo</TableCell>
                            <TableCell align="right">{maxAmount}%</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>

            <TableContainer component={Paper} style={{ marginTop: '40px' }}>
                    <Table style={{ minWidth: '1200px' }}>
                        <TableHead>
                            <TableRow>
                                <TableCell>N</TableCell>
                                <TableCell>Doumentos requeridos</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {document.map((doc, index) => (
                                <TableRow key={doc.id}>
                                    <TableCell>{index + 1}</TableCell>
                                    <TableCell>{doc.title}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
        </Container>
    );
};

export default Simulate;
