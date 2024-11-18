import logo from './logo.svg';
import './App.css';
import {AuthProvider} from './components/AuthContext.js'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import {Login} from './components/LoginComponent.js';
export default function App() {
  return( <AuthProvider>
  
    <BrowserRouter basename={APP_ABS_DOMAIN_NAME}>
    <Routes>
    <Route path="/" element={<Layout/>} >
    <Route path="/login" element={<Login/>} />
    </Route>
    </Routes>
    </BrowserRouter>
    </AuthProvider>);
  //return <FilterableStageTable stage={STAGE} />;
  
}
