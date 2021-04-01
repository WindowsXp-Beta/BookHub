import React from 'react';
import { Router, Route, Switch, Redirect} from 'react-router-dom';
import PrivateRoute from './PrivateRoute'
//import LoginRoute from  './LoginRoute'
import HomeView from "./view/HomeView";
//import LoginView from './view/LoginView'
import {history} from "./utils/history";
//import BookView from "./view/BookView";


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
                    <PrivateRoute exact path="/" component={HomeView} />
                    {/*<LoginRoute exact path="/login" component={LoginView} />*/}
                    {/*<PrivateRoute exact path="/bookDetails" component={BookView} />*/}
                    <Redirect from="/*" to="/" />
                </Switch>

            </Router>
        )
    }


}

export default BasicRoute;