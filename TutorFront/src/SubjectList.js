import { Component } from "react";

class SubjectList extends Component {

    constructor(props) {
        super(props);
        this.state = { subjects: [] }
    }

    componentDidMount() {
        fetch('/subject/all')
            .then(response => response.json())
            .then(data => this.setState({ subjects: data }))
            .catch(error => {
                console.log("error =", error)
            })
    }

    render() {

        return (
            <div>
                {
                    this.state.subjects.map(s => {
                        return (
                            <div>
                                <h1>{s.title}</h1>
                                <h3>{s.description}</h3>
                            </div>
                        )
                    })
                }
            </div>
        )
    }
}

export default SubjectList;