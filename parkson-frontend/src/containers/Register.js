import React, { Component } from "react";
import { Button, FormGroup, FormControl, FormLabel } from "react-bootstrap";
import  { Redirect } from 'react-router-dom';
import axios from 'axios';
import "./Login.css";

export default class Register extends Component {
  constructor(props) {
    super(props);

    this.state = { 
        username: "",
        password: "",
        toLoginPage: false,
        invalidCredentials: false,
        host: 'http://localhost:8080/parkson',
      
    };
  }


  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
    // let {user} = this.state;
    // user.username = this.state.username;
    // user.password = this.state.password;
    let user = {'username': this.state.username, 'password': this.state.password}

    axios.post(this.state.host+'/auth/signup', user).then((response) => {
      if(response.data && response.data.success === true){
        localStorage.setItem('jwtToken', response.data.accessToken);
              this.setState({toLoginPage: true});
      }
      
    }).catch((error)=>{
      if(error.response.data.success === false || error.response.data.error || error.response.data.violations){
        this.setState({invalidCredentials: true});
      }
    });

  }

  render() {
        if (this.state.toLoginPage) {
            return <Redirect to='/' />
        }
    return (
      <div className="Login">
        
        <form onSubmit={this.handleSubmit}>
          {this.state.invalidCredentials && <FormLabel class="alert alert-danger">Invalid Username or Password or username is already taken</FormLabel>}
          <FormGroup controlId="username" bsSize="large">
            <FormLabel>Username</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.username}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" bsSize="large">
            <FormLabel>Password</FormLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Register
          </Button>
        </form>
      </div>
    );
  }
}