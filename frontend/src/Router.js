import React from 'react';
import { Router, Switch, Redirect, Route} from 'react-router-dom';
import PrivateRoute from './PrivateRoute'
import LoginRoute from  './LoginRoute'
import HomeView from "./view/HomeView";
import LoginView from './view/LoginView'
import BookView from "./view/BookView";
import CartView from "./view/CartView";
import OrderView from "./view/OrderView";
import DataView from "./view/UserManageView";
import BookManageView from "./view/BookManageView";
import RegisterView from "./view/RegisterView";
import OrderManageView from "./view/OrderManageView";
import BestSalesView from "./view/BestSalesView";
import BestCustomerView from "./view/BestCustomerView";

import {history} from "./utils/history";


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
                    <Redirect from="/*" to="/" />
                </Switch>
            </Router>
        )
    }
}

export default BasicRoute;