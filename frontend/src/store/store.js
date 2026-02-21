import {configureStore} from '@reduxjs/toolkit';
import user from '../Reducers/UserReducer';
const store = configureStore({
    reducer:{
        user
    }
})
export default store;