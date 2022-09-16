import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from './components/header/header/Header'
import LoginForm from './components/login/LoginForm';
import RegisterForm from './components/register/UserRegisterForm';

function App() {
  return (
    <div className="App">
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginForm />} />
          <Route path="user-register" element={<RegisterForm />} />
        </Routes>
      </BrowserRouter>
    </div >
  );
}

export default App;
