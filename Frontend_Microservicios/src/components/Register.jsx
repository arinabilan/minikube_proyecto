import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Typography, Box } from '@mui/material';

import clientService from '../services/client.service';

const Register = () => {
    const [rut, setRut] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [birthday, setBirthday] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate(); // Declarar navigate para redirección

    const handleRegister = (e) => {
        e.preventDefault();
    
        // Aquí puedes llamar a tu servicio de inicio de sesión
        clientService
          .clientRegister({ rut, name, surname, birthday, email, password })
          .then((response) => {
            // Manejar la respuesta y redirigir al usuario
            console.log('Inicio de sesión exitoso:', response);
            navigate('/'); // Redirigir a la página principal
          })
          .catch((error) => {
            console.error('Error en inicio de sesión:', error);
            setError('Credenciales incorrectas. Intenta de nuevo.'); // Mostrar error
          });
    };

    const handleNavigate = () => {
        navigate("/");
    }

    return (
        <Container maxWidth="sm">
            <Box sx={{ textAlign: 'center', mt: 4, mb: 2 }}>
                <Typography variant="h4" component="h2" gutterBottom>
                    Registro de Cliente
                </Typography>
            </Box>
            <form onSubmit={handleRegister}>
                <TextField
                    label="RUT"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={rut}
                    onChange={(e) => setRut(e.target.value)}
                    required
                />
                <TextField
                    label="Nombre"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />
                <TextField
                    label="Apellido"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}
                    required
                />
                <TextField
                    label="Fecha de Nacimiento"
                    type="date"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    
                    value={birthday}
                    onChange={(e) => setBirthday(e.target.value)}
                    required
                />
                <TextField
                    label="Correo Electrónico"
                    type="email"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <TextField
                    label="Contraseña"
                    type="password"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <Box sx={{ mt: 3, textAlign: 'center' }}>
                    <Button type="submit" variant="contained" color="primary">
                        Registrarse
                    </Button>
                    <Button onClick={handleNavigate} type="submit" variant="contained" color="secondary">
                        Volver
                    </Button>
                </Box>
                
            </form>
        </Container>
    );
};

export default Register;