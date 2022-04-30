import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './Navbar/Navbar';
import ProductsOffers from './ProductsOffers/ProductsOffers';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navbar />} >
          <Route path='/' element={<ProductsOffers />} />
          {/* <Route path='/market' element={<ProductsOffers />} /> 
          <Route path='/fridge' element={<ProductsOffers />} /> */}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
