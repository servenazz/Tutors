import { Component } from "react";

class SearchSubject extends Component {


    constructor(props) {
        super(props)
        this.state = {
            defaultSubjects: [],
            foundSubjects: []
        }
    }

    componentDidMount() {
        fetch('/subject/all').then(result => {
            return result.json();
        }).then(subjects => {
            this.setState({
                defaultSubjects: subjects,
                foundSubjects: []
            });
        }).catch(error => {
            console.log('Error result is ', error)
        })
    }

    updateState(found) {
        console.log('Search subject this is ', this)
        this.setState({
            defaultSubjects: this.state.defaultSubjects,
            foundSubjects: found
        });
    }

    search(e) {
        const text = e.target.value;
        if(text.length === 0) {
            this.updateState([])
            return;
        }
        console.log('Search text is ', text)
        const found = this.state.defaultSubjects.filter(s => s.title.toLowerCase().includes(text.toLowerCase()));
        console.log('Found is ', found);
        this.updateState(found)
    }

    openTutorsPage(id) {
        this.props.props.history.push(`/tutorPage/${id}`)
    }

    render() {
        return (
            <div>
                <div style={{
                    display: 'flex',
                    flexDirection: 'row',
                    borderColor: "red",
                    borderWidth: 5,
                    borderRadius: 10
                }}>
                    <input
                        type={'text'}
                        placeholder='Введите название предмета'
                        style={{
                            width: '80%',
                            height: '5vh'
                        }}
                        onChange={(e) => this.search(e)}
                    />
                    <input
                        type={'button'}
                        value='Поиск'
                        style={{ width: '20%' }}
                    />
                </div>
                <div>
                    {
                        this.state.foundSubjects.map((s) => {
                            return (
                                <h2 onClick={() => this.openTutorsPage(s.id)}>{s.title}</h2>
                            )
                        })
                    }
                </div>
            </div>
        )
    }
}


export default SearchSubject;