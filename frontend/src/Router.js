import React from 'react';
import {Router, Switch, Redirect, Route} from 'react-router-dom';

import PrivateRoute from './PrivateRoute'
import LoginRoute from  './LoginRoute'

import HomeView from "./view/homeView";
import LoginView from './view/loginView'
import BookView from "./view/bookView";
import CartView from "./view/cartView";
import OrderView from "./view/orderView";
import DataView from "./view/userManageView";
import BookManageView from "./view/bookManageView";
import RegisterView from "./view/registerView";
import OrderManageView from "./view/orderManageView";
import BestSalesView from "./view/bestSalesView";
import BestCustomerView from "./view/bestCustomerView";
import OrderCountView from "./view/orderCountView";

import {history} from "./utils/history";
import ChatRoomView from "./view/chatRoomView";


class BasicRoute extends React.Component{

    constructor(props) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            console.log(location,action);
        });
    }

    render(){
        return(
            <Router history={history}>
                <Switch>
                    <PrivateRoute exact path={"/"} component={HomeView} />
                    <LoginRoute exact path={"/Register"} component={RegisterView}/>
                    <LoginRoute exact path={"/login"} component={LoginView} />
                    <PrivateRoute exact path={"/bookDetails"} component={BookView} />
                    <PrivateRoute exact path={"/CartView"} component={CartView} />
                    <PrivateRoute exact path={"/OrderView"} component={OrderView} />
                    <PrivateRoute exact path={"/bookManage"} component={BookManageView} />
                    <PrivateRoute exact path={"/OrderManage"} component={OrderManageView} />
                    <PrivateRoute exact path={"/UserManage"} component={DataView} />
                    <PrivateRoute exact path={"/BestSales"} component={BestSalesView} />
                    <PrivateRoute exact path={"/BestCustomer"} component={BestCustomerView} />
                    <PrivateRoute exact path={"/OrderCountView"} component={OrderCountView} />
                    <Route exact path={"/ChatRoom"} component={ChatRoomView} />
                    <Redirect from="/*" to="/" />
                </Switch>
            </Router>
        )
    }
}

export default BasicRoute;