import React from 'react';
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import MainLayout from './components/MainLoyout';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile';
import Simulate from './components/Simulate';
import LoanSolicitude from './components/LoanSolicitude';
import LoanSolicitude2 from './components/LoanSolicitude2';
import LoanSolicitude3 from './components/LoanSolicitude3';
import Documents from './components/Documents';
import Executive from './components/Executive';
import AllSolicitudes from './components/AllSolicitudes';
import Evaluate from './components/Evaluate';
import Clientsolicitudes from './components/Clientsolicitudes';
import Observeloan from './components/Observeloan';

function App() {
  return (
    <Router>
      <div className = "container">
        
          <Routes>
            <Route path = "/home" element = {<Home/>}>
            </Route>
            <Route path = "/" element = {<MainLayout/>}></Route>
            <Route path='/login' element = {<Login/>}></Route>
            <Route path='/register' element = {<Register/>}></Route>
            <Route path='/profile' element = {<Profile/>}></Route>
            <Route path='/simulateloan' element = {<Simulate/>}></Route>
            <Route path='/solicitude' element = {<LoanSolicitude/>}></Route>
            <Route path='/solicitude2' element = {<LoanSolicitude2/>}></Route>
            <Route path='/solicitude3' element = {<LoanSolicitude3/>}></Route>
            <Route path='/documents' element = {<Documents/>}></Route>
            <Route path='/executive' element = {<Executive/>}></Route>
            <Route path='/allsolicitudes' element = {<AllSolicitudes/>}></Route>
            <Route path='/evaluate' element = {<Evaluate/>}></Route>
            <Route path='/clientsolicitudes' element = {<Clientsolicitudes/>}></Route>
            <Route path='/loanobserve' element = {<Observeloan/>}></Route>
          </Routes>
        
      </div>
    </Router>
  )
}

export default App;

//104.41.24.253 ip de maquina virtual
