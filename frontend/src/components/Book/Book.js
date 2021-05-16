import React from 'react';
import { Card } from 'antd';
import "../../css/index.css"
import {Link} from 'react-router-dom'

const { Meta } = Card;

export class Book extends React.Component{
    render() {
        const {info} = this.props;
        return (
            <Link to={{
                pathname:"/bookDetails",
                search: "?id=" + info.bookId,
            }}
            >
                <Card
                    hoverable
                    style={{width: 181}}
                    cover={<img alt="image" src={info.img} className={"bookImg"} style={{height: 192,width: 150}}/>}
                >
                    <Meta title={info.title} description={"¥ "+ info.price}/>
                </Card>
            </Link>
        );
    }
}