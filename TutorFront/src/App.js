import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import SubjectList from './SubjectList';
import ServiceList from './ServiceList';
import RegistrationView from './RegistrationView';
import AuthorizationView from './AuthorizationView';
import Header from './Header';
import AboutText from './AboutText';
import MainPage from './MainPage';
import TutorsView from './TutorsView';
import TutorPage from './TutorPage';
import SearchSubject from './SearchSubject';
import ProofPayment from './ProofPayment';
import ResultPage from './ResultPage';
import OpenErrorPage from './OpenErrorPage';


class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/subject' exact={true} component={SubjectList} />
            <Route path='/service' exact ={true} component={ServiceList}/>
            <Route path='/registration' exact ={true} component={RegistrationView}/>
            <Route path='/authorization' exact ={true} component={AuthorizationView}/>
            <Route path='/about'exact={true} component ={AboutText}/>
            <Route path='/heder'exact={true} component ={Header}/>
            <Route path='/main'exact={true} component ={MainPage}/>
            <Route path='/tutor'exact={true} component ={TutorsView}/>
            <Route path='/tutorPage/:subjectId' exact={true} component ={TutorPage}/>
            <Route path='/searchSubject'exact={true} component ={SearchSubject}/>
            <Route path='/payment/:orderId/:price' exact={true} component ={ProofPayment}/>
            <Route path='/result/:result' exact={true} component ={ResultPage}/>
          </Switch>
        </Router>
    )
  }
}


export default App;