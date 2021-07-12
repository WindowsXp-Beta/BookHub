import React from 'react';
import {List, Input} from 'antd'
import {Book} from './Book'
import {getOnePageBooks} from "../../services/bookService";

const {Search} = Input;

export class BookList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            showbooks: [],
            searchValue: '',
        };
    }

    componentDidMount() {
        const callback =  (data) => {
            this.setState({
                books: data,
                showbooks: data,
            });
        };
        console.log(this.props);
        getOnePageBooks({key: this.props.range}, callback);
    }

    searchChange = ({target: {value}}) => {

        this.setState({searchValue: value})
        let arr = [];
        let list = this.state.books;
        let search = value.toLowerCase();

        for (let i = 0; i < list.length; i++) {
            if (
                list[i].name.toLowerCase().indexOf(search) >= 0
            ) {
                arr.push(list[i]);
            }
        }
        this.setState(
            {showbooks: arr}
        );
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
                grid = {{gutter: 10, column: 4}}
                dataSource = {this.state.showbooks}
                renderItem = {item => (
                    <List.Item style={{border: "none"}}>
                        <Book info={item} />
                    </List.Item>
                )}
            />
            </div>
        );
    }
}