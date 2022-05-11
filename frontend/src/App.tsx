import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import OffersPage from "./page/offer-page/OffersPage";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navbar />} >
          <Route path='/' element={<h1>Strona główna</h1>} />
          <Route path='/market' element={<OffersPage />} />
          <Route path='/fridge' element={<h1>TODO</h1>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
