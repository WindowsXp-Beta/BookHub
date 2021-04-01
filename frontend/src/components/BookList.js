import React from 'react';
import {List} from 'antd'
import {Book} from './Book'
//import {getBooks} from "../services/bookService";

const books =[
    {
        title: "深入理解计算机系统",
        img: "../assets/book_temp/algorithm.jpg",
        prize: "120",
    },
    {
        title: "算法导论",
        img: "../assets/book_temp/algorithm.jpg",
        prize: "120",
    },
    {
        title: "现代操作系统",
        img: "../assets/book_temp/operating.jpg",
        prize: "120",
    },
    {
        title: "计算机网络",
        img: "../assets/book_temp/network.jpg",
        prize: "120",
    },
    {
        title: "编译原理",
        img: "../assets/book_temp/compiler.jpg",
        prize: "120",
    },
    {
        title: "计算机安全",
        img: "../assets/book_temp/is.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
    {
        title: "深入理解机器学习",
        img: "../assets/book_temp/ml.jpg",
        prize: "120",
    },
]
export class BookList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            books: books,
        };
    }

    componentDidMount() {

        const callback =  (data) => {
            this.setState({books:data});
        };

        //getBooks({"search":null}, callback);

    }

    render() {
        return (
            <List
                grid={{gutter: 10, column: 4}}
                dataSource={this.state.books}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
        );
    }

}