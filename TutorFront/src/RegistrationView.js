import axios from "axios";
import { Component } from "react";
import { FormGroup, Input, Label, Form } from "reactstrap";

class RegistrationView extends Component {

    constructor(props) {
        super(props)
        console.log("This is ", this)
        this.state = {
            model: {
                login: "",
                password: "",
                password2: "",
                email: "",
                firstName: "",
                lastName: "",
                role: "STUDENT"
            }
        }

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

    componentDidMount() {

    }

    componentDidUpdate() {

    }

    submitAction(event) {
        event.preventDefault();
        console.log("Event is ", event)
        console.log("this.state is ", this.state)

        if (this.state.model.password !== this.state.model.password2) {
            alert("Troubles")
            return;
        }

        axios.post("/user/register", this.state.model).then(resposne => {
            console.log("Response is = ", resposne);
        }).catch(error => {
            console.log("Error is = ", error);
        })
    }

    errorStyle() {
        return {
            borderWidth: 4,
            borderColor: 'red'
        }
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
                    onSubmit={(e) => this.submitAction(e)}
                    style={{
                        color: "white",
                        backgroundColor: "black",
                        

                    }}>
                    <h2>Registration</h2>
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
                    <FormGroup>
                        <Label>Повторите пароль </Label>
                        <Input
                            required
                            name="password2"
                            pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\S+$).{8,16}$"
                            type="password"
                            placeholder="Введите пароль"
                            value={this.state.model.password2}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label>Email</Label>
                        <Input
                            type="email"
                            required
                            name="email"
                            pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                            placeholder="Введите email"
                            value={this.state.model.email}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label>Имя</Label>
                        <Input type="text"
                            required
                            name="firstName"
                            pattern="\w{2,18}"
                            placeholder="Введите имя "
                            value={this.state.model.firstName}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label>Фамилия</Label>
                        <Input
                            required
                            pattern="\w{2,18}"
                            name="lastName"
                            type="text"
                            placeholder="Введите фамилия "
                            value={this.state.model.lastName}
                            onChange={(e) => this.handleInputChange(e)}
                        />
                    </FormGroup>

                    <FormGroup style={{
                        marginTop: 20
                    }}>
                        <Label>Укажите вашу роль</Label>
                        <select
                            required
                            name="role"
                            value={this.state.model.role}
                            onChange={(e) => this.handleInputChange(e)}
                        >
                            <option value={"STUDENT"}>Студент</option>
                            <option value={"TUTOR"}>Репетитор</option>
                        </select>
                    </FormGroup>

                    <FormGroup style={{
                        marginTop: 20
                    }}>
                        <Input type="submit" value="register" onClick={() =>  this.props.history.push('/authorization')} />
                    </FormGroup>
                </Form>
            </div>

        )
    }
}


export default RegistrationView;



// const submitChackin = event =>{
//     event.preventDefault();
//     if(!validator.isEmail(register.email)){
//         alert("Вы не ввели электронную почту")
//     } else if(register.password !== register.password2){
//         alert("Пароли не совпадают")
//     } else if(!validator.isStrongPassword(register.password,{minSymbols: 0 })){
//         alert("Пароль должен состоять из одной строчной, прописной буквы и цифры, не менее 8 символов")
//     } else {
//         axios.post(DOMEN_SERVER + "/auth/registration/",{
//             username: register.username,
//             email: register.email,
//             password: register.password,
//         }).then(res => {
//             if (res.data === true){
//                 window.location.href = DOMEN_SITE + "/auth "
//             }else {
//                 alert("Пользователь с таким адресом электронной почты уже есть")
//             }
//         }).catch(() => {
//             alert("Произошла ошибка на сервере")
//         })
//     }
// }