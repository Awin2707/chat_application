import React from 'react'
import reg from '../../Style/Reg.module.css';
import { useState } from 'react'
import { NavLink } from 'react-router-dom';

function CreateAccount() {
    const [show, setShow] = useState(false);
    const passwordShow = (value) => {
        setShow(!value);
    }
    return (
        <div className={reg.card}>
            <div className={reg.cir_divs}>
                <div className={reg.circle}></div>
                <div className={reg.notcircle}></div>
                <div className={reg.notcircle}></div>
            </div>
            <h1 className={reg.heading}>Create Your Account</h1>
            <label className={reg.subhead}>Create your account and start chatting.</label>
            <form>
                <div className={reg.inputdiv}>
                    <input type='email' placeholder='Email Address' className={reg.input} />
                    <label className={reg.err}>{ }</label>
                </div>
                <div className={reg.inputdiv}>
                    <div className={reg.inputs}>
                        <label> +91</label>
                        <input type='phone' placeholder='Phone Number' className={reg.passin} />
                    </div>
                    <label className={reg.err}>{ }</label>
                </div>
                <div className={reg.inputdiv}>
                    <div className={reg.inputs}>
                        <input type={show ? "text" : 'password'} placeholder='Password' className={reg.passin} />
                        <label className={reg.show} onClick={() => { passwordShow(show) }}>{show ? "Hide" : "Show"}</label>
                    </div>
                    <label className={reg.err}>{ }</label>
                </div>
                <div className={reg.inputdiv1}>
                    <input type='checkbox' className={reg.checkbox} />
                    <label>I agree the all terms and conditions</label>
                </div>
                <div className={reg.inputdiv1}>
                    <input type='button' className={reg.btn} value={"SignUp"} />
                </div>
            </form>
            <div className={reg.inputdiv1}>
                <label className={reg.txt}>I have a account already ? </label>
                <NavLink to={"/login"} className={reg.subtxt}>Log In</NavLink>
            </div>
        </div>
    )
}

export default CreateAccount