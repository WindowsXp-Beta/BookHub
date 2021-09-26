import React from 'react';
import '../css/data.css';
import BookTable from "../components/Book/bookTable";

class BookManageView extends React.Component {
    render() {
        return (
            <div>
                <BookTable/>
            </div>
        );
    }
}

export default BookManageView;