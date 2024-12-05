import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import homeService from "../services/home.service";
import clientService from "../services/client.service";
import Button from '@mui/material/Button';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Table } from "@mui/material";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";



import Paper from "@mui/material/Paper";


const Home = () => {
    const [clients, setClients] = useState([]);

    const navigate = useNavigate;

    const init = () => {
        clientService
            .getAll()
            .then((response) => {
                console.log("Mostrando listado de todos los clientes", response),
                setClients(response.data)
            })
            .catch((error) => {
                console.log("error", error);
            });
    };

    useEffect(() => {
        init();
    }, []);

    return(
        <TableContainer component={Paper}>
      <br />
      
      <Link
        //to="/employee/add"
        style={{ textDecoration: "none", marginBottom: "1rem" }}
      >
        <Button
          variant="contained"
          color="primary"
          
        >
          Añadir
        </Button>
      </Link>
      <br /> <br />
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Rut
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Nombre
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Apellido
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Nacimiento
            </TableCell>
            <TableCell align="right" sx={{ fontWeight: "bold" }}>
              Email
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Operaciones
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {clients.map((client) => (
            <TableRow
              key={client.id}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell align="left">{client.rut}</TableCell>
              <TableCell align="left">{client.name}</TableCell>
              <TableCell align="right">{client.surname}</TableCell>
              <TableCell align="right">{client.birthday}</TableCell>
              <TableCell align="right">{client.email}</TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  color="info"
                  size="small"
                  onClick={() => handleEdit(client.id)}
                  style={{ marginLeft: "0.5rem" }}
                  
                >
                  Editar
                </Button>

                <Button
                  variant="contained"
                  color="error"
                  size="small"
                  onClick={() => handleDelete(client.id)}
                  style={{ marginLeft: "0.5rem" }}
                  
                >
                  Eliminar
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    );
};

export default Home;





























/*
const theme = createTheme({
    palette: {
      primary: {
        main: '#1976d2', // Color principal personalizado
      },
      secondary: {
        main: '#d32f2f', // Color secundario personalizado
      },
    },
  });
  
  function Home() {
    return (
      <ThemeProvider theme={theme}>
        <div>
          <h1>Hola Mundo con Material UI</h1>
          <Button variant="contained" color="primary">
            ¡Click Me!
          </Button>
          <Button variant="outlined" color="secondary">
            Secundario
          </Button>
        </div>
      </ThemeProvider>
    );
}

export default Home;
*/