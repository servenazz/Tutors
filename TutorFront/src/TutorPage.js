import { Component } from "react";
import AboutText from "./AboutText";
import Header from "./Header";
import TutorsView from "./TutorsView";

class TutorPage extends Component {

    constructor(props) {
        super(props)
        console.log('Danil show subjectId is ', this.props.match.params.subjectId)
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
                    <TutorsView subjectId={this.props.match.params.subjectId} history={this.props.history}/>
                </div>
            </div>
        )
    }
}
export default TutorPage;