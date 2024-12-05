import React, { useState, useEffect } from "react";
import loanService from "../services/loan.service";
import documentService from "../services/document.service";
import clientService from "../services/client.service";
import { Typography, TextField, MenuItem,Container, TableContainer, Table, TableHead, TableRow,
    TableCell, TableBody, Paper, Button, Link, Switch, Tooltip } from '@mui/material';
import Grid from '@mui/material/Grid2';
import { useNavigate } from 'react-router-dom';
import PageviewIcon from '@mui/icons-material/Pageview';

// Agregar este componente justo antes de tu componente Evaluate
const DocumentDisplay = ({ documentTitle, clientDocuments, onSwitchChange }) => {
    const findDocumentbyTitle = (documentTitle) => {
        return clientDocuments.find(document => document.document.title === documentTitle);
    };

    const showOnlyName = (ruta) => {
        return 'Ver ' + ruta.split('_')[1];
    };

    const showState = () => {
        return document.estado ? ' Aprobado' : ' No aprobado';
    };

    const document = findDocumentbyTitle(documentTitle);

    const viewDocument = (fileName) => {
        return documentService.viewDocument(fileName);
    }

    if (!document) {
        return <span style={{color:'red'}}>Sin documento</span>;
    }

    return (
        <Container>
            <Tooltip title={showOnlyName(document.rutaDocumento)} arrow placement="top">
                <Link href={viewDocument(document.id)} target="_blank" rel="noopener noreferrer"><PageviewIcon fontSize="large" style={{verticalAlign:'bottom'}}/></Link>
            </Tooltip>
            <Tooltip title={showState()} arrow placement="top">
            <Switch
                checked={document.estado}
                onClick={(e) => onSwitchChange(e, document.document.id)}
            />
            </Tooltip>
        </Container>
    );
};

const Evaluate = () => {

    const [clientDocuments, setClientDocument] = useState([]);
    //const [clientSavingCapacity, setClientSavingCapacity] = useState({});

    const [clientSavingCapacity, setClientSavingCapacity] = useState({
        balance: 0,
        withdrawals: 0,
        withdrawal: false,
        deposits: 0,
        yearsSavings: 0,
        recentWithdrawals: 0,
    });

    const [clientDates, setClientDates] = useState({
        monthSalary: 0,
        date: 0,
        initialContract: 0,
        dicom: false,
        type: 2,
        mediaSalary: 0,
        monthlyDebt: 0
    });
    const [client, setClient] = useState({});
    const [solicitudCliente, setSolicitudCliente] = useState({
        id: 0,
        amount: 0,
        interestRate: 0,
        deadline: 0,
        date: new Date(),
        state: 0,
        client: {},
        loanType: { id: 0, type: '' }
    });

    let _solicitudCliente = JSON.parse(localStorage.getItem('solicitudCliente'));
    const navigate = useNavigate();

    useEffect(() => {
        loadEverythingIneed();
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
                return 'En Revisión Inicial';
        }
    }

    const loadEverythingIneed = async () => {
        try {
            setSolicitudCliente(_solicitudCliente);

            const response = await clientService.getClientDates(_solicitudCliente.clientId);
            setClientDates(response.data); //obtengo datos del cliente

            const responseClient = await clientService.getClient(_solicitudCliente.clientId);
            setClient(responseClient.data); //obtengo cliente

            const responseCapacity = await clientService.getClientCapacity(_solicitudCliente.clientId);
            setClientSavingCapacity(responseCapacity.data); //obtengo capacidad de ahorro del cliente

            const responseDocuments = await documentService.getDocumentsByClientId(_solicitudCliente.clientId);
            setClientDocument(responseDocuments.data); //obtengo documentos del cliente

            //console.log("Datos del cliente cargados:", response.data); // Verifica que los datos se están cargando correctamente
            //console.log("lalala: ", responseCapacity.data);
        } catch (error) {
            console.error("Error al cargar los datos del cliente:", error);
        }
    };

    const saveDocumentState = (e, documentId) => {
        var cd = clientDocuments.find(d => d.document.id === documentId);
        cd.estado = e.target.checked;
        documentService.putDocument(cd);
        loadEverythingIneed();
    }

    const handleEvaluate = async () => {
        try {
            await clientService.updateDates(clientDates);
            await clientService.updateCapacity(clientSavingCapacity);
            let solicitud = await loanService.evaluateSolicitude(solicitudCliente.id);
            console.log('Evaluación de Solicitud evaluada:', solicitud);
            if (solicitud.data) {
                alert("Solicitud evaluada, queda con estado: " + getStateFromSolicitude(solicitud.data));
                localStorage.setItem('solicitudCliente', JSON.stringify(solicitud.data));
                //solicitudCliente = JSON.parse(localStorage.getItem('solicitudCliente'));
                setSolicitudCliente(JSON.parse(localStorage.getItem('solicitudCliente')));
            }
        }
        catch (error) {
            console.error("Error al evaluar solicitud:", error);
        }
    }

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Evaluación de Solicitud <span style={{fontSize:'20px', fontWeight:'bold'}}>({getStateFromSolicitude(solicitudCliente)})</span>
            </Typography>

            <TableContainer component={Paper} style={{ marginTop: '20px' }}>
                <Table style={{ minWidth: '1000px' }}>
                    <TableHead>
                        <TableRow>
                            <TableCell style={{fontWeight:'bold'}}>Cliente</TableCell>
                            <TableCell style={{fontWeight:'bold'}}>Tipo de Préstamo</TableCell>
                            <TableCell style={{fontWeight:'bold'}}>Monto</TableCell>
                            <TableCell style={{fontWeight:'bold'}}>Tasa de Interés</TableCell>
                            <TableCell style={{fontWeight:'bold'}}>Plazo solicitado</TableCell>
                            <TableCell style={{fontWeight:'bold'}}>Fecha</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow key={solicitudCliente.id}>
                        <TableCell>{client.name} {client.surname}</TableCell>
                        <TableCell>{solicitudCliente.loanType.type}</TableCell>
                            <TableCell>{solicitudCliente.amount.toLocaleString()} CLP</TableCell>
                            <TableCell>{(solicitudCliente.interestRate * 100).toFixed(2)}%</TableCell>
                            <TableCell>{(solicitudCliente.deadline / 12)} años</TableCell>
                            <TableCell>{new Date(solicitudCliente.date).toLocaleDateString()}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
            <br/>

            <Typography variant="h5" gutterBottom>
                Datos del Cliente
            </Typography>

            <Grid container rowSpacing={0} columnSpacing={2}>
                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Ingreso Mensual:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientDates.monthSalary}
                        required
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, monthSalary: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}>
                    <DocumentDisplay
                        documentTitle="Comprobante de ingresos"
                        clientDocuments={clientDocuments}
                        onSwitchChange={saveDocumentState}
                    />
                </Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Años con banco:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientDates.date}
                        required
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, date: e.target.value})}
                    />
                    &nbsp;Años </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Años trabajando:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientDates.initialContract}
                        required
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, initialContract: e.target.value})}
                    />
                    &nbsp;Años </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Esta en Dicom:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    No <Switch checked={clientDates.dicom} onClick={(e) => setClientDates({...clientDates, dicom: e.target.checked})} /> Si
                </Grid>
                <Grid size={4}>
                    <DocumentDisplay
                        documentTitle="Certificado Dicom"
                        clientDocuments={clientDocuments}
                        onSwitchChange={saveDocumentState}
                    />
                </Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Tipo de trabajador:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        select
                        value={clientDates.type}
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, type: e.target.value})}
                    >
                        <MenuItem key={1} value={1}>
                            Independiente
                        </MenuItem>
                        <MenuItem key={2} value={2}>
                            Con contrato
                        </MenuItem>
                    </TextField>
                </Grid>
                <Grid size={4}>
                    <DocumentDisplay
                        documentTitle="Contrato laboral"
                        clientDocuments={clientDocuments}
                        onSwitchChange={saveDocumentState}
                    />
                </Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Promedio de ingresos en ultimos 2 años:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientDates.mediaSalary}
                        required
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, mediaSalary: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}>
                    <DocumentDisplay
                        documentTitle="Comprobante de ingresos de 2 años"
                        clientDocuments={clientDocuments}
                        onSwitchChange={saveDocumentState}
                    />
                </Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Deuda mensual:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                <TextField
                        type="number"
                        value={clientDates.monthlyDebt}
                        required
                        size="small"
                        onChange={(e) => setClientDates({...clientDates, monthlyDebt: e.target.value})}
                    />
                    &nbsp;CLP</Grid>
                <Grid size={4}></Grid>
            </Grid>
            <br/>

            <Typography variant="h5" gutterBottom>
                Capacidad de Ahorro
            </Typography>

            <Grid container rowSpacing={1} columnSpacing={2}>
                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Saldo en cuenta ahorro:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientSavingCapacity.balance}
                        required
                        size="small"
                        onChange={(e) => setClientSavingCapacity({...clientSavingCapacity, balance: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}>
                    <DocumentDisplay
                        documentTitle="Certificado de Cuenta Ahorro"
                        clientDocuments={clientDocuments}
                        onSwitchChange={saveDocumentState}
                    />
                </Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Suma de retiros en ultimos 12 meses:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientSavingCapacity.withdrawals}
                        required
                        size="small"
                        onChange={(e) => setClientSavingCapacity({...clientSavingCapacity, withdrawals: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Historial de ahorro consistente:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    No <Switch checked={clientSavingCapacity.withdrawal} onClick={(e) => setClientSavingCapacity({...clientSavingCapacity, withdrawal: e.target.checked})} /> Si
                </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Suma de depositos mensuales de ultimos 12 meses:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientSavingCapacity.deposits}
                        required
                        size="small"
                        onChange={(e) => setClientSavingCapacity({...clientSavingCapacity, deposits: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Años de antiguidad de la cuenta de ahorro:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientSavingCapacity.yearsSavings}
                        required
                        size="small"
                        onChange={(e) => setClientSavingCapacity({...clientSavingCapacity, yearsSavings: e.target.value})}
                    />
                    &nbsp;Años
                </Grid>
                <Grid size={4}></Grid>

                <Grid size={4} style={{textAlign:'left', fontWeight:'bold'}}>Suma de retiros en ultimos 6 meses:</Grid>
                <Grid size={4} style={{textAlign:'left'}}>
                    <TextField
                        type="number"
                        value={clientSavingCapacity.recentWithdrawals}
                        required
                        size="small"
                        onChange={(e) => setClientSavingCapacity({...clientSavingCapacity, recentWithdrawals: e.target.value})}
                    />
                    &nbsp;CLP
                </Grid>
                <Grid size={4}></Grid>
            </Grid>
            <br/>

            <Button
                variant="contained"
                color="secondary"
                onClick={handleEvaluate}
            >
                Evaluar
            </Button>&nbsp;
            <Button
                variant="contained"
                color="secondary"
                onClick={() => navigate('/allsolicitudes')}
            >
                Volver
            </Button>
        </Container>
    );
}

export default Evaluate;