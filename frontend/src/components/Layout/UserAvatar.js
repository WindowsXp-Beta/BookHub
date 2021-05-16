import React from 'react';
import { Avatar, Dropdown, Menu, Icon} from 'antd';
//import { UserOutlined } from '@ant-design/icons';
import '../../css/index.css'
import * as userService from '../../services/userService'
// import config from 'config'

export class UserAvatar extends React.Component {

    render() {

        const menu = (
            <Menu>
                <Menu.Item>
                    <a target="_blank" rel="noopener noreferrer" href="http://www.alipay.com/">
                        Show Profile
                    </a>
                </Menu.Item>
                <Menu.Item>
                    <a href="#" onClick={userService.logout}>
                        Log Out
                    </a>
                </Menu.Item>
            </Menu>
        );

        const {user} = this.props;


        return(
            <div id="avatar">
                <span className="name">Hi, {user.userName}</span>
                <Dropdown overlay={menu} placement="bottomRight">
                    {/*<Avatar src={imgUrl} style={{cursor:"pointer"}}/>*/}
                    <Avatar  icon={<Icon type="user" />} style={{cursor:"pointer"}}/>
                </Dropdown>
            </div>
        );
    }
}