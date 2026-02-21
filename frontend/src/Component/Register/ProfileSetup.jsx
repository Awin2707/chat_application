import React from 'react'
import reg from '../../Style/Reg.module.css';
import img from  '../../assest/camera.png'
function ProfileSetup() {
    return (
        <div className={reg.card}>
            <div className={reg.cir_divs}>
                <div className={reg.notcircle}></div>
                <div className={reg.circle}></div>
                <div className={reg.notcircle}></div>
            </div>
            <h1 className={reg.heading}>Set Up Your Profile</h1>
            <label className={reg.subhead}>Add your name and a profile photo (optional).</label>
            <br/>
            <div className={reg.div_center}>
                <div className={reg.imgdiv}>
                    <img alt='x-ico' src={img} className={reg.imglogo}/>
                </div>
                <input type='file' style={{display: "none"}} id='files'/>
                <label htmlFor='files' className={reg.upload}>Upload Photo</label>
                <div className={reg.inputdiv}>
                <input type='text' placeholder='Full Name' className={reg.input} />
            </div>
            <button className={reg.btn}>Continue</button>
            </div>
        </div>
    )
}

export default ProfileSetup