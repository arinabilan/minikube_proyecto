import React, { useState, useEffect } from 'react';
import clientService from '../services/client.service';
import documentService from '../services/document.service';
import loanService from '../services/loan.service';
import { Button, TextField, FormControlLabel, Checkbox, Typography, Container, MenuItem, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const LoanSolicitude = () => {
    const navigate = useNavigate();

    const userData = JSON.parse(localStorage.getItem('userData'));

    //SECTOR PARA OBJETO SOLICITUDE
    const [loanTypes, setLoanTypes] = useState([]);
    const [loanRequirement, setLoanRequirement] = useState([]);
    const [deadline, setDeadline] = useState(0); //plazo solicitado
    const [amount, setAmount] = useState(0); //monto solicitado
    const [date, setDate] = useState('2024-10-24'); //data de solicitud
    const [state, setState] = useState(0); //estado de solicitud 1 - pendiente por defecto
    const [newSolicitude, setNewSolicitude] = useState({}); //en esta variable se guardó nueva solicitud que se acaba de crear para seguir con siguiente paso
    const [selectedType, setSelectedType] = useState('');
    const [loanRequirementSelected, setLoanRequirementSelected] = useState(null);

    const [files, setFiles] = useState({}); // Estado para almacenar los archivos seleccionados

    useEffect(() => {
        loadLoanTypes();
        loadLoanRequirements();
        if (selectedType) {
            const req = loadLoanRequirementsById(selectedType);
            setLoanRequirementSelected(req);
        }
    }, [selectedType]);

    const loadLoanTypes = async () => {
        const response = await loanService.getLoanTypes();
        setLoanTypes(response.data);
    };

    const loadLoanRequirements = async () => {
        const response = await loanService.getLoanRequirements();
        setLoanRequirement(response.data);
    };

    const loadLoanRequirementsById = (id) => {
        return loanRequirement.find(req => req.typeLoan.id === id);
    };

    const handleProfile = () => {
        navigate('/profile');
    };


    const handleFileChange = (documentId, file) => {
        setFiles(prevFiles => ({ ...prevFiles, [documentId]: file }));
    };

    const handleDocumentUpload = async (documentId) => {
        const formData = new FormData();
        formData.append('file', files[documentId]);

        try {
            await documentService.uploadDocument(userData.id, documentId, formData);
            alert("Documento subido exitosamente");
        } catch (error) {
            console.error('Error al subir documento:', error);
            alert("Error al subir documento");
        }
    };


    const handleSolicitude = (e) => {
        e.preventDefault();
        // obtener id del tipo de prestamo
        var LoanRequirement = loadLoanRequirementsById(selectedType);
        //debugger
        const savingSolicitude = {
            maxMonths: LoanRequirement.maxMonths,
            interestRate: LoanRequirement.interestRate,
            maxAmount: LoanRequirement.maxAmount,
            deadline,
            amount,
            date,
            state,
            clientId: userData.id,
            loanType: {'id': selectedType}
        };

        loanService.createSolicitude(savingSolicitude)
            .then((response) => {
                console.log('Solicitud registrada:', response.data);
                setNewSolicitude(response.data)
                localStorage.setItem('solicitudeData', JSON.stringify(response.data));
                handleProfile(); // Pasar al siguiente paso
            })
            .catch((error) => {
                console.error('Error al registrar solicitud:', error);
            });
    }


    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Solicitud de Préstamo
            </Typography>
            <form onSubmit={handleSolicitude}>
                <TextField
                    select
                    label="Tipo de Préstamo"
                    value={selectedType}
                    onChange={(e) => setSelectedType(e.target.value)}
                    fullWidth
                    margin="normal"
                >
                    {loanTypes.map((t) => (
                        <MenuItem key={t.id} value={t.id}>
                        {t.type}
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
                    label="Plazo en años"
                    type="number"
                    value={deadline}
                    onChange={(e) => setDeadline(e.target.value)}
                    fullWidth
                    margin="normal"
                />

                <TextField
                    label="Fecha de solicitud"
                    type="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    fullWidth
                    margin="normal"
                    required

                />

                <Button type="submit" variant="contained" color="primary">
                    Guardar solicitud
                </Button>

                <Button variant="contained" color="secondary" onClick={handleProfile}>
                    Volver al perfil
                </Button>

            </form>
            {loanRequirementSelected?.documents?.map(d => (
                <Box key={d.id} display="flex" alignItems="center" mt={2}>
                <Typography>{d.title}</Typography>
                <input
                    type="file"
                    onChange={(e) => handleFileChange(d.id, e.target.files[0])}
                    style={{ marginLeft: '10px' }}
                />
                <Button
                    variant="contained"
                    color="secondary"
                    onClick={() => handleDocumentUpload(d.id)}
                    sx={{ ml: 2 }}
                >
                    Subir {d.title}
                </Button>
                </Box>

            ))}
        </Container>
    );
};

export default LoanSolicitude;