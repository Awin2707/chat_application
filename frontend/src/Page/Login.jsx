import React from 'react'
import LoginComponent from '../Component/Login/LoginComponent';
import log from '../Style/Reg.module.css';

function LoginPage() {
  return (
    <div className={log.main}>
      <LoginComponent />
    </div>
  )
}

export default LoginPage;