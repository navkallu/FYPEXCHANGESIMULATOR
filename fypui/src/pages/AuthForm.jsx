import React, { useState } from 'react';
import './AuthForm.css';

const AuthForm = () => {
    const [isLogin, setIsLogin] = useState(false);
    const [formData, setFormData] = useState({
        firstName: 'John',
        lastName: 'Smith',
        email: 'johnsmith14@gmail.com',
        password: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission here
        console.log(formData);
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
                            <button type="submit" className="auth-button">
                                {isLogin ? 'Log In' : 'Create account'}
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AuthForm;