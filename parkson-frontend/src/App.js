import React, { Component } from 'react';
import { BrowserRouter,Router, Switch, Route } from 'react-router-dom'
import Login from "./containers/Login";
import ImageUpload from './ImageUpload';
import Dashboard from './Dashboard';
import IdleTimer from 'react-idle-timer';
import { createBrowserHistory } from "history";



class App extends Component {
    constructor(props) {
        super(props)
        this.idleTimer = null
        this.onAction = this._onAction.bind(this)
        this.onActive = this._onActive.bind(this)
        this.onIdle = this._onIdle.bind(this)
    }
    render() {
      const history = createBrowserHistory();
      return (

            <Router history={history} basename={'/parkson'}>
              <div className="app">
              <IdleTimer
              ref={ref => { this.idleTimer = ref }}
              element={document}
              onActive={this.onActive}
              onIdle={() => this.onIdle(history)}
              onAction={this.onAction}

              debounce={250}
              timeout={1000 * 60 * 10} />
              <Switch>
                <Route exact path="/" component={Login}/>
                <Route path="/dashboard" component={Dashboard} />
              </Switch>
              </div>
          </Router>
          )
  }

   _onAction(e) {
  }
 
  _onActive(e) {
  }
 
  _onIdle(redirect) {
    if(localStorage.jwtToken){
      localStorage.removeItem('jwtToken');
      redirect.push("");
    }

  }

}

export default App;
