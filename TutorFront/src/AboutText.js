import { Component } from "react";


class AboutText extends Component {


    constructor(props) {
        super(props)
    }


    render() {

        return (

            <div
                style={{
                    // backgroundColor:"#b7e6ee",
                    // backgroundColor:"#d3eaf2",
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',

                }}>

                <h4>
                    Меня зовут Сара и я являюсь выпускником МГУ в сфере преподавания АНГЛИЙСКОГО ЯЗЫКА.
                    В 16 лет я впервые выиграла грант на проживание в США 🇺🇸 и обучение в американской школе СОВЕРШЕННО БЕСПЛАТНО. У меня получилось, получится и у вас!!! Мой опыт преподавания помог мне составить свою авторскую программу для всех, кто хочет:
                    <br />
                    📍 заговорить с первых занятий;
                    <br />
                    📍продвинуться с порогового уровня Pre-Intermediate;
                    <br />
                    📍изучить все нюансы Academic English;
                    <br />
                    📍успешно сдать международные тесты TOEFL, IELTS, CAE;
                    <br />
                    📍а также сдать специализированные экзамены GRE, SAT, ACT, LSAT, SHSAT, и т.д.;
                    <br />
                    📍успешно пройти СОБЕСЕДОВАНИЯ в ТОПОВЫЕ американские компании 🇺🇸;
                    <br />
                    📍сдать экзамен на гражданство 🇺🇸 
                </h4>

            </div >

        )
    }
}

export default AboutText;