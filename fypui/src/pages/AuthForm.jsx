import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { signup, login } from '../services/authAPI';
import './AuthForm.css';

const AuthForm = () => {
    const [isLogin, setIsLogin] = useState(false);
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    });
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
        setError('');
    };

    // const handleSubmit = async (e) => {
    //     e.preventDefault();
    //     setIsLoading(true);
    //     setError('');

    //     // Basic client-side validation
    //     if (!formData.email || !formData.password) {
    //         setError('Email and password are required');
    //         setIsLoading(false);
    //         return;
    //     }

    //     if (!isLogin && (!formData.firstName || !formData.lastName)) {
    //         setError('All fields are required');
    //         setIsLoading(false);
    //         return;
    //     }

    //     try {
    //         let response;
    //         if (isLogin) {
    //             response = await login({
    //                 email: formData.email,
    //                 password: formData.password
    //             });
    //         } else {
    //             response = await signup({
    //                 firstName: formData.firstName,
    //                 lastName: formData.lastName,
    //                 email: formData.email,
    //                 password: formData.password
    //             });
    //         }

    //         // Verify the response structure
    //         if (!response || !response.token || !response.user) {
    //             throw new Error('Invalid response from server');
    //         }

    //         // Save token and user data
    //         localStorage.setItem('token', response.token);
    //         localStorage.setItem('user', JSON.stringify(response.user));

    //         // Redirect to dashboard or home page
    //         navigate('/dashboard');
    //     } catch (err) {
    //         setError(err.message || 'An error occurred');
    //     } finally {
    //         setIsLoading(false);
    //     }
    // };
    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setError('');

        // Basic client-side validation
        if (!formData.email || !formData.password) {
            setError('Email and password are required');
            setIsLoading(false);
            return;
        }

        if (!isLogin && (!formData.firstName || !formData.lastName)) {
            setError('All fields are required');
            setIsLoading(false);
            return;
        }

        try {
            let response;
            if (isLogin) {
                response = await login({
                    email: formData.email.trim(),
                    password: formData.password
                });
                console.log('Login response:', response.user, response.token); // Debug
            } else {
                response = await signup({
                    firstName: formData.firstName.trim(),
                    lastName: formData.lastName.trim(),
                    email: formData.email.trim(),
                    password: formData.password
                });
            }

            // Verify the response structure
            if (!response || !response.token || !response.user) {
                throw new Error('Invalid response from server');
            }

            localStorage.setItem('token', response.token);
            localStorage.setItem('user', JSON.stringify(response.user));

            navigate('/dashboard');
        } catch (err) {
            // More specific error handling
            const errorMsg = err.message.includes('Failed to fetch')
                ? 'Network error - could not connect to server'
                : err.message;
            setError(errorMsg);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="auth-container">
            <div className="title">Stock Exchange Simulator</div>
            <div className="auth-card">
                <h2>{isLogin ? 'Log In' : 'Create new account'}</h2>

                {!isLogin && (
                    <div className="switch-text">
                        Already a member? <span onClick={() => setIsLogin(true)}>Log In</span>
                    </div>
                )}

                {isLogin && (
                    <div className="switch-text">
                        Need an account? <span onClick={() => setIsLogin(false)}>Sign Up</span>
                    </div>
                )}

                {error && <div className="error-message">{error}</div>}

                <form onSubmit={handleSubmit} className='form'>
                    {!isLogin && (
                        <div className='fullname'>
                            <div className="form-group">
                                <label>First name</label>
                                <input
                                    type="text"
                                    name="firstName"
                                    value={formData.firstName}
                                    onChange={handleChange}
                                    placeholder="Enter first name"
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Last name</label>
                                <input
                                    type="text"
                                    name="lastName"
                                    value={formData.lastName}
                                    onChange={handleChange}
                                    placeholder="Enter last name"
                                    required
                                />
                            </div>
                        </div>
                    )}

                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="Enter email"
                            required
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Enter password"
                            required
                            minLength="6"
                        />
                    </div>

                    {isLogin && (
                        <div className="forgot-password">
                            <span>Forgot password?</span>
                        </div>
                    )}

                    <div className='submitbuttons'>
                        <div className="change-method">
                            <span>Change method</span>
                        </div>
                        <div className="form-groups">
                            <button
                                type="submit"
                                className="auth-button"
                                disabled={isLoading}
                            >
                                {isLoading ? 'Processing...' : (isLogin ? 'Log In' : 'Create account')}
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AuthForm;