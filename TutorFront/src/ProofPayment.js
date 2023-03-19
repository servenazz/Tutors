import { Component } from "react";
import AboutText from "./AboutText";
import Header from "./Header";


class ProofPayment extends Component {

    constructor(props) {
        super(props)
        this.state = {
            model: {
                code: "",
            }
        }
    }

    orderId = this.props.match.params.orderId;
    price = this.props.match.params.price;

    openSuccessPage() {
        this.props.history.push('/result/success')
    }

    openErrorPage() {
        this.props.history.push('/result/error')
    }

    onClick() {
        if(this.state.model.code && this.state.model.code === '777')
            this.openSuccessPage()
        else
            this.openErrorPage()
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


    render() {
        return (
            <div>
                <Header props={this.props} />
                <div style={{
                    display: 'flex',
                    flexDirection: 'row'
                }}>
                    <div
                        style={{
                            marginLeft: 20,
                            width: '25%',
                            borderRight: '1.5px solid rgba(0, 0, 0, 0.10)',
                            borderColor: "black"
                        }}>
                        <AboutText />
                    </div>
                    <div
                        style={{
                            marginTop: 40,
                            marginLeft: 40
                        }}
                    >
                        <p>Увыжаемый {localStorage.getItem("fullName") ?? "Не определено"}</p>
                        <p>Ваш заказ  {this.orderId} </p>
                        <p> На сумму {this.price} рублей </p>
                        <h3>Готов к оплате</h3>
                        <p>Вы действительно хотите оплатить заказ? </p>

                        <input type={Text}
                            style={{
                                marginRight: 15
                            }}
                            value={this.state.model.code}
                            onChange={(e) => this.handleInputChange(e)}
                            name='code'
                        />
                        <input type={'button'} value='Оплатить' onClick={(e) => this.onClick(e)} />
                    </div>
                </div>
            </div>

        )

    }

}
export default ProofPayment;