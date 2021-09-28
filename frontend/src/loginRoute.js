import React from 'react';
import {Redirect, Route} from 'react-router-dom'
import * as userService from "./services/userService"
import {message} from "antd";

export class LoginRoute extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isAuthed: false,
            hasAuthed: false,
            userInfo:{}
        };
    }

    checkAuthSucceed = (response) => {
        this.setState({isAuthed: true, hasAuthed: true});
    }

    checkAuthFail = (response) => {
        message.error("登录失效");
        localStorage.removeItem('user');
        this.setState({isAuthed: false, hasAuthed: true});
    }


    componentDidMount() {
        userService.checkSession(this.checkAuthSucceed, this.checkAuthFail);
    }

    render() {
        const {component: Component, path="/",exact=false,strict=false} = this.props;
        console.log(this.state.isAuthed);

        if (!this.state.hasAuthed) {
            return null;
        }

        return <Route path={path} exact={exact} strict={strict} render={props => (
            this.state.isAuthed ? (
                <Redirect to={{
                    pathname: '/',
                    state: {from: props.location, name: this.state.userInfo}
                }}/>
            ) : (
                <Component {...props}/>
            )
        )}/>
    }
}

export default LoginRoute