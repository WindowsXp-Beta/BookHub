import React from 'react';
import '../css/data.css';
import UserTable from "../components/user/userTable";

class DataView extends React.Component {
    render() {
        return (
            <div>
                <UserTable/>
            </div>
        );
    }
}

export default DataView;