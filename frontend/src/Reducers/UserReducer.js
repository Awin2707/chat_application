import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    auth: false,
    users: {},
    isLogout: true
}

const userReducers = createSlice({
    name: "user",
    initialState: initialState,
    reducers: {
        addUser: (state, action) => {
            state.users = action.payload;
        },
        Verified: (state, action) => {
            state.auth = true;
            state.isLogout = false;
        },
        LogoutUser: (state, action) => {
            state.isLogout = true;
            state.auth = false;
        }
    }
}) 
export const {addUser, Verified, LogoutUser} = userReducers.actions;
export default userReducers.reducer;