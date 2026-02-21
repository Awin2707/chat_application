import React, { useState } from 'react'
import log from '../../Style/Reg.module.css';
import { NavLink } from 'react-router-dom';

function LoginComponent() {
    const [show, setShow] = useState(false);
        const passwordShow = (value) => {
            setShow(!value);
        }
    return (
        <div className={log.card}>
            <div className='mt-3'></div>
            <h1 className={log.heading}>Log In to Your Account.</h1>
            <label className={log.subhead}>Welcome back! Log in to continue exploring.</label>
            <form>
                <div className={log.inputdiv}>
                    <input type='email' placeholder='Email Address' className={log.input} />
                    <label className={log.err}>{ }</label>
                </div>
                <div className={log.inputdiv}>
                    <div className={log.inputs}>
                        <input type={show ? "text" : 'password'} placeholder='Password' className={log.passin} />
                        <label className={log.show} onClick={() => { passwordShow(show) }}>{show ? "Hide" : "Show"}</label>
                    </div>
                    <label className={log.err}>{ }</label>
                </div>
                <div className="w-100 d-flex align-item-center justify-content-end mb-2">
                    <label className={log.forget}>Forget password ?</label>
                </div>
                <div className={log.inputdiv1}>
                    <input type='button' className={log.btn} value={"SignUp"} />
                </div>
            </form>
            <div className={log.inputdiv1}>
                <label className={log.txt}>I have a account already ? </label>
                <NavLink to={"/signup"} className={log.subtxt}>Sign Up</NavLink>
            </div>
        </div>
    )
}

export default LoginComponent