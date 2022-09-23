import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import LoginForm from './components/security/login/LoginForm';
import Modal from './components/general/modal/Modal';
import Sidebar from './components/sidebar/Sidebar';
import RegisterForm from './components/user/register/UserRegisterForm';
import UsersTable from './components/user/usersTable/UsersTable';

function App() {
  return (
    <div className="App">
      <Modal />
      <Sidebar />
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
