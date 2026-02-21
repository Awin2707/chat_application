import React, { useRef, useState } from 'react'
import reg from '../Style/Reg.module.css';
import CreateAccount from '../Component/Register/CreateAccount';
import ProfileSetup from '../Component/Register/ProfileSetup';
import AccountVerification from '../Component/Register/AccountVerification';
import LoginComponent from '../Component/Login/LoginComponent';
import Error404 from '../Component/Error/Error404';

function Register() {

  const [show, setShow] = useState(false);
  const [count, setCount] = useState(0);
  const passwordShow = (value) => {
    setShow(!value);
  }

  const setUserCount = () => {
    setCount(prev => prev + 1);
    console.log(count);
  }

  return (
    <div className={reg.main}>
      {
        count == 0 && (<CreateAccount />)
      }      
      {
        count == 1 && (<ProfileSetup />)
      }
      {
        count == 2 && (<AccountVerification />)
      }
      {
        count == 3 && (<LoginComponent />)
      }
    </div>
  )
}

export default Register