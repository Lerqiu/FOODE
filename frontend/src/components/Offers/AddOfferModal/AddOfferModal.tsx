/* eslint-disable */
import React, { useState } from 'react';
import {
  Button,
  Form, FormControl, FormLabel, Modal,
} from 'react-bootstrap';
import 'react-datepicker/dist/react-datepicker.css';
import { default as ReactDatePicker } from 'react-datepicker';
import {useQueryClient} from "react-query";
import OfferService from "../../../services/OfferService";

function AddOfferModal(props: {show: any, onHide: any}) {
  const { show, onHide } = props;
  const defaultCity = {
    id: 1,
    name: 'Wrocław',
  };

  const queryClient = useQueryClient();

  const addOffer = (offer: any) => {
      OfferService.save(offer)
      .then( () => queryClient.invalidateQueries('offers-get'))
      .catch(console.error);
  };

  const title = 'Dodaj oferte';
  const [price, setPrice] = useState('');
  const [city, setCity] = useState(defaultCity);
  const [description, setDescription] = useState('');
  const [availability, setAvailability] = useState('');
  const [productName, setProductName] = useState('');
  const [expirationDate, setExpirationDate] = useState(new Date());

  const clearModal = () => {
    setPrice('');
    setCity(defaultCity);
    setDescription('');
    setAvailability('');
    setProductName('');
    setExpirationDate(new Date());
  };

  const handleAddOffer = () => {
    const offer = {
      price: parseInt(price, 10),
      date: new Date(),
      city,
      description,
      availability,
      user: {
        id: 1,
      },
      product: {
        name: productName,
        expirationDate,
      },
    };
    console.log(offer);
    addOffer(offer);
    clearModal();
    onHide();
  };

  return (
    <Modal
      onHide={onHide}
      show={show}
      size="sm"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title>
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <FormLabel>Nazwa produktu</FormLabel>
          <FormControl
            value={productName}
            onChange={(event) => setProductName(event.target.value)}
            placeholder="nazwa produktu"
          />
          <FormLabel>Cena</FormLabel>
          <FormControl
            value={price}
            onChange={(event) => setPrice(event.target.value)}
            placeholder="Cena"
          />
          <FormLabel>Miasto</FormLabel>
          <Form.Select
            defaultValue="PLACEHOLDER"
            onChange={(event) => setCity(JSON.parse(event.target.value))}
          >
            <option value="PLACEHOLDER" hidden>Wybierz miasto</option>
            <option value={JSON.stringify(defaultCity)}>Wroclaw</option>
          </Form.Select>
          <Form.Group
            className="mb-3"
            controlId="exampleForm.ControlTextarea1"
          >
            <Form.Label>Opis</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              value={description}
              onChange={(event) => setDescription(event.target.value)}
              placeholder="Opis"
            />
          </Form.Group>
          <FormLabel>Dostępność</FormLabel>
          <FormControl
            value={availability}
            onChange={(event) => setAvailability(event.target.value)}
            placeholder="dostępność"
          />
          <FormLabel>Data ważności</FormLabel>
          <ReactDatePicker
            dateFormat="yyyy-MM-dd"
            showPopperArrow={false}
            selected={expirationDate}
            onChange={(date) => setExpirationDate(date!)}
            useWeekdaysShort
            fixedHeight
          />
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button
          variant="secondary"
          onClick={handleAddOffer}
        >
          Close
        </Button>
        <Button
          variant="primary"
          onClick={handleAddOffer}
        >
          Save Changes
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default AddOfferModal;
