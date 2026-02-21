import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate, Outlet } from 'react-router-dom';

function User() {
    const auth = useSelector((state) => state.user.auth);
    const logout = useSelector((state) => state.user.isLogout);
    console.log(auth, logout);
    if(auth && !logout){
        return <Outlet/>
    }else{
        console.log("yes");
        return <Navigate to={"/"} />
    }
}

export default User