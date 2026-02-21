import React, { useEffect, useState } from 'react'
import reg from '../../Style/Reg.module.css';
import lock from '../../assest/lock.png'

function AccountVerification() {

    const [otp, setOtp] = useState(Array(6).fill(""));
    const [timer, setTimer] = useState(60);

    useEffect(() => {

    })



    useEffect(() => {
        if(timer <= 0) return;
        const interval =  setInterval(() => {
            setTimer(prev => prev - 1);
        }, 1000);
        return () => clearInterval(interval);
    }, [timer])

    return (
        <div className={reg.card}>
            <div className={reg.cir_divs}>
                <div className={reg.notcircle}></div>
                <div className={reg.notcircle}></div>
                <div className={reg.circle}></div>
            </div>
            <div className={reg.div_center}>
                <div className={reg.card_circle}>
                    <img alt='x-ico' src={lock} className={reg.lock} />
                </div>
            </div>
            <h1 className={reg.heading}>Verify Your Account</h1>
            <label className={reg.subhead}>We have sent a 6-digit code to your email address</label>
            <br />
            <div className={reg.inputdiv_row}>
                {
                    otp.map((val, ind) => {
                        return (
                            <input type='number' placeholder='0' className={reg.inp} />
                        )
                    })
                }
            </div>
            <div className='d-flex align-item-center justify-content-between'>
                <label className={reg.txt}>I did not receive the OTP code</label>
                {
                    timer > 0 ?
                        <label className={reg.txt}>0 : {timer}</label> :
                        <label className={reg.txt_otp}>Resend Code</label>
                }
            </div>
            <div className='d-flex justify-content-center mb-2'>
                <input type='button' value={"Verify Code"} className={reg.btn} />
            </div>
        </div>
    )
}

export default AccountVerification