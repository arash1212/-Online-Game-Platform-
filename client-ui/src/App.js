import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginForm from './components/login/LoginForm';
import RegisterForm from './components/register/UserRegisterForm';
import UsersTable from './components/usersTable/UsersTable';

function App() {
  return (
    <div className="App">
      
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginForm />} />
          <Route path="user-register" element={<RegisterForm />} />
          <Route path="/users-list" element={<UsersTable />} />
        </Routes>
      </BrowserRouter>
    </div >


  );
}

export default App;
