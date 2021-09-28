import React from 'react';
import {Redirect, Route} from 'react-router-dom'
import * as userService from "./services/userService"
import {message} from "antd";

export default class PrivateRoute extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isAuthed: false,
            hasAuthed: false,
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

        if (!this.state.hasAuthed) {
            return null;
        }

        return <Route path={path} exact={exact} strict={strict} render={props => (
            this.state.isAuthed ? (
                <Component {...props}/>
            ) : (
                <Redirect to={{
                    pathname: '/login',
                    state: {from: props.location}
                }}/>
            )
        )}/>
    }
}

