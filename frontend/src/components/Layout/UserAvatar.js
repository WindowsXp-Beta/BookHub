import React from 'react';
import { Avatar, Dropdown, Menu} from 'antd';
import { UserOutlined } from '@ant-design/icons';
import '../../css/index.css'
import * as userService from '../../services/userService'
import { Link } from 'react-router-dom';

export class UserAvatar extends React.Component {

    render() {

        const menu = (
            <Menu>
                <Menu.Item>
                    <Link to="/login" onClick={userService.logout}>
                        Log Out
                    </Link>
                </Menu.Item>
            </Menu>
        );

        const {user} = this.props;


        return(
            <div id="avatar">
                <span className="name">Hi, {user.userName}</span>
                <Dropdown overlay={menu} placement="bottomRight">
                    {/*<Avatar src={imgUrl} style={{cursor:"pointer"}}/>*/}
                    <Avatar  icon={<UserOutlined type="user" />} style={{cursor:"pointer"}}/>
                </Dropdown>
            </div>
        );
    }
}