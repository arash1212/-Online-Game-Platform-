import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Header from './components/header/header/Header';
// import Footer from './components/footer/Footer'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Header />
    <App />
    {/* <Footer /> */}
  </React.StrictMode>
);

reportWebVitals();
