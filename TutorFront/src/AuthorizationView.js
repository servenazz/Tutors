import axios from "axios";
import { Component } from "react";
import { FormGroup, Input, Label, Form } from "reactstrap";

class AuthorizationView extends Component {


    constructor(props) {
        super(props)
        this.state = {
            model: {
                login: "",
                password: ""
            }
        }
    }

    openRegistration() {
        this.props.history.push('/registration')
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        this.setState({
            model: {
                ...this.state.model,
                [name]: value
            }
        });
    }

    submitAction(event) {
        event.preventDefault();
        console.log("Event is ", event)
        console.log("this.state is ", this.state)
        axios.post("/user/login", this.state.model).then(response => {
            console.log("Response is = ", response);
            const token = response.data.token;
            console.log("token is", token);
            if (token) {
                localStorage.setItem("token", token);
                localStorage.setItem("fullName", response.data.fullName);
                this.props.history.push('/main')
                event.persist();
            }
        }).catch(error => {
            console.log("Error is = ", error);
        })

    }


    render() {
        return (


            <div
                style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                  
                }}>
                <Form
                    style={{
                        border: '1.5px solid rgba(0, 0, 0, 0.10)',
                        borderColor: "black"
                    }}
                    onSubmit={(e) => this.submitAction(e)}
                >
                    <h2>Authorization</h2>
                    <FormGroup>
                        <Label>Логин</Label>
                        <Input
                            required
                            type="text"
                            name="login"
                            pattern="\w{5,18}"
                            placeholder="Введите логин"
                            value={this.state.model.login}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label>Пароль</Label>
                        <Input
                            type="password"
                            name="password"
                            pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\S+$).{8,16}$"
                            placeholder="Введите пароль"
                            value={this.state.model.password}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>

                    <FormGroup style={{
                        marginTop: 10
                    }}>
                        <Input type="submit" value="login" />
                    </FormGroup>
                    <input type={'button'} value='registration' onClick={() => this.openRegistration()}
                        style={{
                            height: 40,
                            width: "100%",
                        }} />
                </Form>
            </div>
        )
    }
}

export default AuthorizationView;
