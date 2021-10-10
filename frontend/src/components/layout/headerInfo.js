import React from 'react';
import {Col, Row} from 'antd';
import '../../css/index.css'
import logo from '../../assets/logo.png';
//import logoFont from '../assets/logo-name.svg';
import {UserAvatar} from "./userAvatar";

export class HeaderInfo extends React.Component {

    render(){

        const user = JSON.parse(localStorage.getItem("user"));

        return(
            <div id="header">
                <div id="header-content">
                    <Row>
                        {/*<Col xs={24} sm={24} md={5} lg={5} xl={5} xxl={4}>*/}
                        <Col flex={20}>
                        <a id="logo" href={"/"}>
                                <img alt="logo"  className="logo" src={logo} style={{ height:49 }}/>
                                {//<img alt="book Store"  className="logo-font" src={logoFont} style={{ height:40 }}/>
                                    }
                            </a>
                        </Col>
                        {/*<Col xs={0} sm={0} md={5} lg={5} xl={5} xxl={5}>*/}
                        <Col flex={5}>
                            <span className={"pageView"}>{`本站已经被访问了${this.props.pageView}次`}</span>
                        </Col>
                        {/*<Col xs={0} sm={0} md={14} lg={14} xl={14} xxl={14}>*/}
                        <Col flex={5}>
                            {user != null ? <UserAvatar user={user}/> : null}
                        </Col>
                    </Row>
                </div>
            </div>
        );
    }
}