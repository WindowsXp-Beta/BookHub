import React from 'react';
import { Card } from 'antd';
import "../css/index.css"
import {Link} from 'react-router-dom'

const { Meta } = Card;

export class Book extends React.Component{


    render() {

        const {info} = this.props;
        console.log(info.img);
        return (
            <Link to={{
                pathname:"/bookDetails",
            }}
            >
                <Card
                    hoverable
                    style={{width: 181}}
                    cover={<img alt="image" src={require("../assets/book_temp/algorithm.jpg").default} className={"bookImg"} style={{height: 200,width: 150}}/>}
                    //onClick={this.showBookDetails.bind(this, info.bookId)}
                >
                    <Meta title={info.title} description={"¥ "+ info.prize}/>
                </Card>
            </Link>
        );
    }

}