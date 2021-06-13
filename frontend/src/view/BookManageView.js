import React from 'react';
import '../css/Data.css';
import BookTable from "../components/Book/BookTable";

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