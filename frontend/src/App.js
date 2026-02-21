import logo from './logo.svg';
import './App.css';
import reg from '../src/Style/Reg.module.css';
import Register from './Page/Register';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainPage from './Page/Main';
import LoginPage from './Page/Login';
import Error404 from './Component/Error/Error404';
import User from './common/User';
import HomePage from './Page/HomePage';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<MainPage/>} />
          <Route path='/signup' element={<Register/>} />
          <Route path='/login' element={<LoginPage/>} />
          <Route element={<User/>}>
            <Route path='/home' element={<HomePage/>} />
          </Route>
          <Route path='*' element={<Error404/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
