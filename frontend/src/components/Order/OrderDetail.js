import React from 'react';
import { Table } from 'antd';

class OrderDetail extends React.Component {

    render() {

        const {info} = this.props;

        if (info == null) {
            return null;
        }

        for (let i = 0; i < info.length; ++i) {
            info[i].title = info[i].book.name;
            info[i].bookNumber = info[i].bookNum;
            info[i].bookPrice = info[i].book.price/100;
            info[i].sum = (info[i].bookNum * info[i].book.price/100).toFixed(1);
        }

        const columns = [
            {
                title: 'Title',
                dataIndex: 'title',
                key: 'title',
            },
            {
                title: 'Number',
                dataIndex: 'bookNumber',
                key: 'bookNumber',
            },
            {
                title: 'Price/per',
                dataIndex: 'bookPrice',
                key: 'bookPrice',
            },
            {
                title: 'Sum',
                dataIndex: 'sum',
                key: 'sum',
            },
        ];
        return <Table columns={columns} dataSource={info} />;
    }
}
export default OrderDetail;
