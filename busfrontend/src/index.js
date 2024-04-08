import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { Home, Trips,Reservate, Reserva } from './pages';
import reportWebVitals from './reportWebVitals';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>,
  },
  {
    path: "/trips",
    element:<Trips/>
  },
  {
    path: "/reservate/:id",
    element:<Reservate/>
  },
  {
    path:"/reserva/:id",
    element:<Reserva/>
  }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
