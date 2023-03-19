import { Component } from "react";
import TutorView from "./TutorView";
import axios from "axios";

class TutorsView extends Component {

    constructor(props) {
        super(props)
        this.state = { services: [] }
    }

    componentDidMount() {
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
            }
        }

        axios.get(`/service/by-subject?subjectId=${this.props.subjectId}`, config)
            .then((response => response.data))
            .then((data) => this.setState({ services: data }))
            .catch(error => alert(error));
    }

    render() {
        return (
            //common
            <div>
                {
                    this.state.services.map(service => {
                        return (
                            <TutorView 
                                fio={service.user.firstName + " " + service.user.lastName}
                                price={service.price}
                                isBusy={service.status !== "FREE"}
                                subject={service.subject.title}
                                expirience={service.experience}
                                rating={service.rating}
                                history={this.props.history}
                             />
                        )
                    })
                }
            </div>
        )
    }
}
export default TutorsView;