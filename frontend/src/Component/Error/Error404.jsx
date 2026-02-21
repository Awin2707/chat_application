import React from 'react'
import err from '../../Style/Err.module.css';

function Error404() {
  return (
    <div className={err.main}>
      <div className={err.popup}>
        <h1 className={err.head}>404</h1>
        <label className={err.subhead}>Page Not Found</label>
        <label className={err.topic}>Oops! It seems you've followed a broken link or entered a URL that doesn't exist. Please check the address or try one of the options below.</label>
        <div className={err.subdiv}>
          <input type='button' value={"Go to Homepage"} className={err.home} />
          <input type='button' value={"Contact Support"} className={err.contact} />
        </div>
      </div>
    </div>
  )
}

export default Error404