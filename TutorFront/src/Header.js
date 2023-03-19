import { Component, Image } from "react";
import logo512 from "./assets/logo512.png";


class Header extends Component {

    constructor(props) {
        super(props)
    }

    openAuth() {
        console.log('props is ', this.props.props)
        this.props.props.history.push('/authorization')
    }

    openTutors() {
        this.props.props.history.push('/tutorPage/1')
    }

    render() {

        return (
            <div
                style={{
                   backgroundColor:"#E5f1f2",
                    border: '1.5px solid rgba(0, 0, 0, 0.10)',
                    borderColor: "black"

                }}>


                <div
                    style={{
                        height: 100,
                        width: 110,
                        display: 'flex',
                        flexDirection: 'row',
                        



                    }}>
                    <p style={{
                        marginLeft: 15,
                        marginTop: 20,
                    }}><h1>
                            Tuttor.app
                        </h1>
                    </p>
                    <img
                        style={{
                            marginRight: 20,
                            marginLeft: 30,
                            resizeMode: 'cover',
                            height: 90,
                            width: 90,
                        }}
                        src={logo512} />

                    <input type={'button'} value='Репетиторы' onClick={() => this.openTutors()}
                        style={{
                            marginTop: 20,
                            height: 30,
                            width: 110,
                            marginRight: 20
                        }} />


                    <input type={'button'} value='войти' onClick={() => this.openAuth()}
                        style={{
                            position: "absolute",
                            right: 0,
                            marginRight: 15,
                            marginTop: 15,
                            height: 40,
                            width: 70,
                        }} />



                </div >

            </div>
        )
    }



}
export default Header;