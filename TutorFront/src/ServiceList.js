import { Component } from "react";
import axios from "axios";


class ServiceList extends Component {

    constructor(props) {
        super(props)
        this.state = { service: [] }
    }

    componentDidMount() {
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token"),
            }
        }

        axios.get('/service/by-subject?subjectId=1', config)
            .then((response => response.data))
            .then((data) => this.setState({ service: data }))
            .catch(error => alert(error));
    }

    render() {
        return (
            <div>
                {
                    this.state.service.map(s => {
                        return (
                            <div>
                                <h1>{s.name}</h1>
                            </div>
                        )
                    })
                }
            </div>
        )
    }
}
export default ServiceList;