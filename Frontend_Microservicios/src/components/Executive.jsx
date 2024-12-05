import React, { useState } from "react";
import executiveService from "../services/executive.service";
import { TextField, Button, Typography, Container } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Executive = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('');


    const handleLogin = (event) => {
        event.preventDefault();

        executiveService
        .loginExecutive({email, password})
        .then((response) => {
            console.log('Inicio de sesión exitoso:', response.data);

            localStorage.setItem('userExecutive', JSON.stringify(response.data));

            navigate('/allsolicitudes'); // Redirigir a la página de solicitudes
        })
        .catch((error) => {
            console.error('Error en inicio de sesión:', error);
            setError('Credenciales incorrectas. Intenta de nuevo.'); // Mostrar error
          });

    }

    const handleNavigate = () => {
      navigate("/");
    }

    return (
        <Container maxWidth="xs">
          <Typography variant="h5" gutterBottom>
            Iniciar Sesión
          </Typography>
          {error && <Typography color="error">{error}</Typography>}
          <form onSubmit={handleLogin}>
            <TextField
              label="Email"
              variant="outlined"
              fullWidth
              margin="normal"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <TextField
              label="Contraseña"
              variant="outlined"
              type="password"
              fullWidth
              margin="normal"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <Button type="submit" variant="contained" color="primary" fullWidth>
              Iniciar Sesión
            </Button>
            <Button onClick={handleNavigate} type="submit" variant="contained" color="secondary" fullWidth>
              Volver
            </Button>
          </form>
        </Container>
      );

}

export default Executive;