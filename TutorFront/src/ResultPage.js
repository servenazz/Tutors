import { Component } from "react";
import AboutText from "./AboutText";
import Header from "./Header";

class ResultPage extends Component {

    constructor(props) {
        super(props)
    }

    result = this.props.match.params.result;

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
                        margin:'15%'
                    }}
                    >
                        <h1>{this.result === 'success' ? "Вы успешно оплатили услугу!" : "Оплата не прошла!"}</h1>
                    </div>
                </div>
            </div>
        )
    }
}

export default ResultPage;