import React, { Component } from "react";
import { Button, FormGroup, FormControl, FormLabel } from "react-bootstrap";
import  { Redirect, Link } from 'react-router-dom';
import axios from 'axios';
import "./Login.css";
import * as Property from "../properties.js";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = { 
        username: "",
        password: "",
        toDashboardPage: false,
        invalidCredentials: false,
        //host: 'http://localhost:8080/parkson',
      
    };
  }


  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
    //return true;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
    let user = {'username': this.state.username, 'password': this.state.password}

    axios.post(Property.host+'/auth/signin', user).then((response) => {
      if(response.data && response.data.accessToken){
        localStorage.setItem('jwtToken', response.data.accessToken);
        this.setState({toDashboardPage: true});
      }
      
    }).catch((error)=>{
      if(error.response.data.error || error.response.data.violations){
        this.setState({invalidCredentials: true});
      }
    });

  }

  render() {
        if (this.state.toDashboardPage) {
            return <Redirect to='/dashboard' />
        }
    return (
      <div className="Login">
        
        <form onSubmit={this.handleSubmit}>
          {this.state.invalidCredentials && <FormLabel class="alert alert-danger">Invalid Username or Password</FormLabel>}
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
          <Button block bsSize="large" disabled={!this.validateForm()} type="submit">
            Login
          </Button>
          <div class="text-center">Or</div>
          <Link to="/register">
            <Button renderAs="button" block
            bsSize="large">
              <span>Register</span>
            </Button>
          </Link>
        </form>
      </div>
    );
  }
}