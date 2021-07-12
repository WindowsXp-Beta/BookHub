import React from 'react';
import LoginForm from '../components/User/LoginForm';
import {withRouter} from "react-router-dom";
import '../css/login.css'

class LoginView extends React.Component{

    render(){
        return (
            <div className="login-page">
                <div className="login-container">
                    <div className="register-info">
                        <div className="BookStoreLogo"></div>
                        <h1>BookStore的内容可比你想的要多得多！</h1>
                    </div>
                    <div className="login-box-shell">
                        <div className="login-box">
                            <h1 className="page-title">Login</h1>
                            <div className="login-content">
                                <LoginForm />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(LoginView);