import React from 'react';
import {Button, Checkbox, Form, Input} from 'antd';
import {LockOutlined, UserOutlined} from '@ant-design/icons';
import {Link} from "react-router-dom";
import * as userService from '../../services/userService'


class LoginForm extends React.Component {

    onFinish = values => {
        console.log('Received values of form: ', values);
        userService.login(values);
    };

    render() {
        return (
            <Form className="login-form"
                  initialValues={{remember: true}}
                  onFinish={this.onFinish}>
                <Form.Item
                    name="username"
                    rules={[{required: true, message: 'Please input your username!'}]}
                >
                    <Input
                        prefix={<UserOutlined className="site-form-item-icon" style={{ color: '#ccc' }}/>}
                        placeholder="Username"
                        autoComplete="off"
                    />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[{required: true, message: 'Please input your Password!'}]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" style={{ color: '#ccc' }}/>}
                        type="password"
                        placeholder="Password"
                        autoComplete="off"
                    />
                </Form.Item>
                <Form.Item>
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                        <Checkbox style={{color: '#f90',}}>Remember me</Checkbox>
                    </Form.Item>

                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
                    </Button>
                    <span style={{color: '#f90'}}>Or</span> <Link to={"/Register"}>register now!</Link>
                </Form.Item>
            </Form>
        );
    }
}

export default LoginForm