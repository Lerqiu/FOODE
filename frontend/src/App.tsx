import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import OffersPage from './page/offer-page/OffersPage';
import Paths from './conf/Paths';

function App() {
  const root = Paths;
  return (
    <BrowserRouter>
      <Routes>
        <Route path={root.__get} element={<Navbar />}>
          <Route path={root.__get} element={<h1>Strona główna</h1>} />
          <Route path={root.market.__get} element={<OffersPage />} />
          <Route path={root.fridge.__get} element={<h1>TODO</h1>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
