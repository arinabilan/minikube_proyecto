import React from 'react';
import { Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container, Box } from '@mui/material';

const MainLayout = () => {
    return (
      <div>
        
  
        {/* Contenido Principal */}
        <Container style={{ marginTop: '20px', textAlign: 'center' }}>
          <Typography variant="h4" gutterBottom>
            Welcome to "PrestaBanco"
          </Typography>
          <Typography variant="body1" gutterBottom>
          Log in to your account or register to start enjoying our services.
          </Typography>
          <Box mt={2}>
            <Link to="/login">
              <Button variant="contained" color="primary" style={{ marginRight: '10px' }}>
                Login
              </Button>
            </Link>
            <Link to="/register">
              <Button variant="outlined" color="secondary">
                Register
              </Button>
            </Link>
            <Link to="/executive">
              <Button variant="outlined" color="primary">
                Entrar como ejecutivo
              </Button>
            </Link>
          </Box>
        </Container>
  
        
      </div>
    );
  };
  
  export default MainLayout;