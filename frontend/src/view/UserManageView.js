import React from 'react';
import '../css/Data.css';
import UserTable from "../components/User/UserTable";

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