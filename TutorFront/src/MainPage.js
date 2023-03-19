import { Component } from "react";
import AboutText from "./AboutText";
import Header from "./Header";
import SearchSubject from "./SearchSubject";

class MainPage extends Component {


    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div
            style={{
            }}>
                <Header props={this.props} />


                <div
                    style={{
                        margin: '10%'
                    }}
                >
                    <SearchSubject props={this.props} />
                </div>
                <div
                    style={{
                        margin: '10%',
                    }}>
                    <AboutText />

                </div>
            </div>

        )
    }
}
export default MainPage;