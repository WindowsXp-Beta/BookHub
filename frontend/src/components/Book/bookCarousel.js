import React from 'react';
import { Carousel } from 'antd';

export class BookCarousel extends React.Component{

    createContent = (ctx) => {
        const images = ctx.keys().map(ctx);//require.context.keys()匹配成功模块名字组成的数组
        let result = [];
        for (let i = 0; i < ctx.keys().length; i++) {
            let img = images[i];
            result.push(<div><img alt={i} src={img.default}/></div>);
        }
        return result;
    };

    render(){
        const requireContext = require.context("../../assets/carousel", true, /^\.\/.*\.jpg$/);//an api of webpack
        return (
            <Carousel autoplay>
                {this.createContent(requireContext)}
            </Carousel>
        )
    }
}


