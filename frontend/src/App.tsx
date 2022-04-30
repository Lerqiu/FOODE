import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './Navbar/Navbar';
import ProductsOffers from './ProductsOffers/ProductsOffers';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navbar />} >
          <Route path='/' element={<ProductsOffers />} />
          {/* <Route path='/Ala' element={<h2>Ala</h2>} /> */}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
