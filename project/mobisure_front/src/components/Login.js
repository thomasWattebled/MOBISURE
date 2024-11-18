import React, { useState } from 'react';
import { useAuth } from './AuthContext'; 
import AuthService from '../services/AuthService.tsx';
import { useNavigate } from 'react-router-dom';


export const LoginComponent = () => {
    const Auth = useAuth();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (username, password) => {
        try {
            const response = await AuthService.login({ username, password });
            const userDetails = await response.json()
            console.log(userDetails)
            if (userDetails !== null) {
                
                Auth.userLogin(userDetails)
          
                setUsername('')
                setPassword('')
                navigate('/');
            } else {
                setMessage('Invalid credentials - null details');
            }
        } catch (error) {
            setMessage('Invalid credentials');
        }   
    };
    return (
        <LoginForm handleLogin={handleLogin} message={message}   /> 
    );
}  

const LoginForm = ({handleLogin, message}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        handleLogin( username, password )
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">Login Form</div>
                        <div className="card-body">
                            {message && <div className="alert alert-danger">{message}</div>}
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label>Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginForm;