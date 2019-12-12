import React, { Component } from 'react';
import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import { FormLabel } from "react-bootstrap";
import  { Redirect } from 'react-router-dom';
import MaterialDatatable from "material-datatable";
import {Select,MenuItem} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import FileBase64 from 'react-file-base64';
import "./index.css";
//import { ResponsiveContainer } from 'recharts';
// import Title from './Title';

class Company extends Component {
  state = {
    isAuthenticated: false,
    companies: [],
    apiBasePoint: 'http://localhost:8080/parkson/company/',
    newCompany: {
      code: '',
      name: '',
      active: '',
      registerationNumber: '',
      logo: '',
      activatedOn: '',
      codeHris: '',
      abbreviatedName: '',
      createdOn: '',
      createdBy:''
    },
    newModal: false,
    editCustomerModal: false,
    statusOption: [{name: 'Active', id:true},{name: 'InActive', id:false}],
    validationErrors:''
  }
  componentWillMount() {
    this._refreshCompanies();
    console.log(localStorage.jwtToken)
    if(localStorage.jwtToken){
        this.setState({isAuthenticated: true});
      }
  }
  toggleNewModal() {
    this.setState({
      newModal: ! this.state.newModal
    });
  }
  addCompany() {
    axios.post(this.state.apiBasePoint+'add', this.state.newCompany, { headers: {"Authorization" : `Bearer `+localStorage.getItem('jwtToken')}}).then((response) => {
      let { company } = this.state;

      company.push(response.data);

      this.setState({ company, newCompany: {
      code: '',
      name: '',
      active: '',
      registerationNumber: '',
      logo: '',
      activatedOn: '',
      codeHris: '',
      abbreviatedName: '',
      createdOn: '',
      createdBy:''
      }});
      
    }).catch(error=>{
      if(error.response){
            this.setState({validationErrors : Array.from(error.response.data.violations).map(err => err.message)});
      }
      else{
        this.toggleNewModal();
        this._refreshCompanies();
      }
      
    });//to catch the errors if any;
  }
  deleteCompany(id) {
    axios.delete(this.state.apiBasePoint+'delete/' + id, { headers: {"Authorization" : `Bearer `+localStorage.getItem('jwtToken')}}).then((response) => {
      this._refreshCompanies();
    });
  }
  _refreshCompanies() {
    axios.get(this.state.apiBasePoint+'all', { headers: {"Authorization" : `Bearer `+localStorage.getItem('jwtToken')} }).then((response) => {
      this.setState({
        companies: response.data
      })
    }).catch((error)=>{
      console.log("error "+JSON.stringify(error));      
      this.setState({isAuthenticated: false});
    });
  }
  //const countries: string[] = ["Africa", "America", "Asia", "Europe"];
  handleChange = event => {
    this.state.newCompany.active= event.target.value;
    this.setState({ newCompany: this.state.newCompany })
    //this.setState({ selected: event.target.value, name: event.target.name});
  };
  renderOptions() {
     return this.state.statusOption.map((dt, i) => {
      //console.log(dt);
       return (
           <MenuItem
             label="Select a country"
             value={dt.id}>{dt.name}</MenuItem>
         
       );
     });
   }
  getFileBase64(file){
    this.state.newCompany.logo= file.base64;
    this.setState({ newCompany: this.state.newCompany })
    console.log("log "+JSON.stringify(this.state.newCompany.logo));
  }

  render() {
    if (this.state.isAuthenticated === false) {
        return <Redirect to='/' />
    }
   const useStyles = makeStyles(theme => ({
      container: {
        display: 'flex',
        flexWrap: 'wrap',
        top:100
      },
      textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
      },
    }));
    const columns = [
      { name: "Code", field: "code" },
      { name: "Name", field: "name" },
      { name: "Last Modified By", field: "lastModifiedBy" },
      { name: "Last Modified On", field: "lastModifiedOn" }
    ];
    //const classes = useStyles();
      const options = {
        filterType: "dropdown",
        responsive: "scroll",
        onRowsDelete: (array) => {
          if (window.confirm('Are you sure you wish to delete this item?')){
              console.log(JSON.stringify(array.data));
              array.data.forEach((item)=>{
                  var companyId = this.state.companies[item.rowIndex].code;
                  this.deleteCompany(companyId);   
              });

          }
        }
      };
    return (
    <React.Fragment>
      <div className="App container">

      <h1>Parkson Companies App</h1>

      <Button className="my-3" color="primary" onClick={this.toggleNewModal.bind(this)}>Add New Company</Button>

      <Modal isOpen={this.state.newModal} toggle={this.toggleNewModal.bind(this)}>
        <ModalHeader toggle={this.toggleNewModal.bind(this)}>Add a new company</ModalHeader>
        <form novalidate>
        <ModalBody>
        
          {this.state.validationErrors && <FormLabel class="alert alert-danger">
              {this.state.validationErrors.map(message =>
                <li>{message}</li>)}
          </FormLabel>}
            <FormGroup>
              <Label for="title">Code</Label>
              <Input required id="title" required value={this.state.newCompany.code} onChange={(e) => {
                let { newCompany } = this.state;

                newCompany.code = e.target.value;

                this.setState({ newCompany });
              }} />
            </FormGroup>
            <FormGroup>
              <Label for="name">Name</Label>
              <Input id="name" required value={this.state.newCompany.name} onChange={(e) => {
                let { newCompany } = this.state;

                newCompany.name = e.target.value;

                this.setState({ newCompany });
              }} />
            </FormGroup>
            <FormGroup>
              <Label for="rating">Registeration Number</Label>
              <Input id="rating" value={this.state.newCompany.registerationNumber} onChange={(e) => {
                let { newCompany } = this.state;

                newCompany.registerationNumber = e.target.value;

                this.setState({ newCompany });
              }} />
            </FormGroup>
            <FormGroup>
              <Label for="rating">Logo</Label>
              <FileBase64
                type ={'jpg', 'jpeg'}
                multiple={ false }
                onDone={ this.getFileBase64.bind(this) } />
            </FormGroup>
            <FormGroup>
              <Label for="rating">Code HRIS</Label>
              <Input id="rating" value={this.state.newCompany.codeHris} onChange={(e) => {
                let { newCompany } = this.state;

                newCompany.codeHris = e.target.value;

                this.setState({ newCompany });
              }} />
            </FormGroup>
            <FormGroup>
                <TextField
                  id="datetime-local"
                  label="Activated On"
                  type="datetime-local"
                  defaultValue="2017-05-24T23:30"
                  value={this.state.newCompany.activatedOn} onChange={(e) => {
                    let { newCompany } = this.state;

                    newCompany.activatedOn = e.target.value;

                    this.setState({ newCompany });
                  }}
                  className={useStyles.textField}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
            </FormGroup>
             <FormGroup>
              <Label for="rating">Abbreviated Name</Label>
              <Input id="rating" value={this.state.newCompany.abbreviatedName} onChange={(e) => {
                let { newCompany } = this.state;

                newCompany.abbreviatedName = e.target.value;

                this.setState({ newCompany });
              }} />
            </FormGroup>
             <FormGroup>
              <Label for="status">Status</Label>
              <Select className="width50" value={this.state.selected} onChange={this.handleChange}>
               {this.renderOptions()}
             </Select>


            </FormGroup>
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.addCompany.bind(this)}>Save</Button>{' '}
            <Button color="secondary" onClick={this.toggleNewModal.bind(this)}>Cancel</Button>
          </ModalFooter>
        </form>
      </Modal>

        <MaterialDatatable
          title={"Companies List"}
          data={this.state.companies}
          columns={columns}
          options={options}
        />
      </div>
      </React.Fragment>
    );
  }
}

export default Company;
