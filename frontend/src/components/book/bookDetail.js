import React from 'react';
import {Descriptions, Button, message} from 'antd';
import {ShoppingCartOutlined, PayCircleOutlined} from '@ant-design/icons';
import {addCart} from "../../services/cartService";


export class BookDetail extends React.Component {

    addToCart = () => {
        const data = this.props.detail;
        let cartItem = {
            bookId: data.id,
            bookNumber: 1
        };
        console.log(cartItem);
        const callback = (response) => {
            message.success("加入购物车成功，请至购物车中查看");
        }
        addCart(cartItem, callback);
    }

    render() {
        const {detail} = this.props;
        if (detail == null) {
            return null;
        }
        return (
            <div className={"content"}>
                <div className={"book-detail"}>
                    <div className={"book-image"}><img alt="image" src={detail.image}
                                                       style={{width: "250px", height: "350px"}}/></div>
                    <div className={"descriptions"}>
                        <Descriptions>
                            <Descriptions.Item className={"title"} span={3}>{detail.name}</Descriptions.Item>
                            <Descriptions.Item label={"作者"} span={3}>{detail.author}</Descriptions.Item>
                            <Descriptions.Item label={"分类"} span={3}>{detail.type}</Descriptions.Item>
                            <Descriptions.Item label={"定价"} span={3}>{<span
                                className={"price"}>{'¥' + (detail.price) / 100}</span>}</Descriptions.Item>
                            <Descriptions.Item label={"状态 "} span={3}>{detail.inventory !== 0 ?
                                <span>有货 <span className={"inventory"}>库存{detail.inventory}件</span></span> :
                                <span className={"status"}>无货</span>}</Descriptions.Item>
                            <Descriptions.Item label={"作品简介"} span={3}>{detail.description}</Descriptions.Item>
                        </Descriptions>
                    </div>
                </div>
                <div className={"button-groups"}>
                    <Button type="danger" icon={<ShoppingCartOutlined/>} size={"large"} onClick={this.addToCart}>
                        加入购物车
                    </Button>
                    <Button type="danger" icon={<PayCircleOutlined/>} size={"large"} style={{marginLeft: "15%"}} ghost>
                        立即购买
                    </Button>
                </div>
            </div>
        );
    }
}