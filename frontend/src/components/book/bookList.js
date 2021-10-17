import React from 'react';
import {List, Input} from 'antd';
import {Book} from './book';
import {getBooks, searchBooks} from "../../services/bookService";

const {Search} = Input;

export class BookList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            showBooks: [],
            query: '',
            page: 1,
            pageSize: 12,
            totalBookNumber: 0,
        };
    }

    componentDidMount() {
        const callback = (response) => {
            this.setState({
                books: response.data.content,
                showBooks: response.data.content,
                totalBookNumber: response.data.size
            });
            this.props.updatePageView(response.data.pageView)
        };
        getBooks({page: this.state.page - 1, pageSize: this.state.pageSize}, callback);
    }

    fetchBooks = (page) => {
        const callback = (response) => {
            this.setState({
                books: response.data.content,
                showBooks: response.data.content,
            });
            console.log(response.data.pageView);
            this.props.updatePageView(response.data.pageView)
        };
        this.setState({
            page: page
        });
        getBooks({page: page - 1, pageSize: this.state.pageSize}, callback);
    }

    searchChange = ({target: {value}}) => {

        console.log(value);
        this.setState({query: value})
        let data = {
            query: value,
            page: 0,
            pageSize: this.state.pageSize
        }
        const callback = (response) => {
            this.setState({
                books: response.data,
                showBooks: response.data,
            });
        }
        if(value !== '')searchBooks(data, callback);
    }

    render() {
        return (
            <div className="bookListContainer">
                <Search value={this.state.searchValue} placeholder="search for book" onChange={this.searchChange}
                        enterButton
                />
                <br/>
                <br/>
                <br/>
                <List
                    grid={{gutter: 10, column: 4}}
                    dataSource={this.state.showBooks}
                    pagination={{
                        position: "bottom",
                        current: this.state.page,
                        pageSize: this.state.pageSize,
                        total: this.state.totalBookNumber,
                        onChange: this.fetchBooks
                    }}
                    renderItem={item => (
                        <List.Item style={{border: "none"}}>
                            <Book info={item}/>
                        </List.Item>
                    )}
                />
            </div>
        );
    }
}
