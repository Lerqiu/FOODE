import React, { useState } from 'react';
import {
  Modal,
} from 'react-bootstrap';
import 'react-datepicker/dist/react-datepicker.css';
import { useQuery, useQueryClient } from 'react-query';
import OfferService from '../../../services/OfferService';
import ICity from '../../../interfaces/city/ICity';
import CityService from '../../../services/CityService';
import AddOfferForm from '../AddOfferForm/AddOfferForm';

function AddOfferModal(props: {show: any, onHide: any}) {
  const { show, onHide } = props;
  const [cities, setCities] = useState<ICity[]>([]);

  const title = 'Dodaj oferte';

  const queryClient = useQueryClient();

  useQuery<ICity[], Error>(
    'city-get',
    async () => CityService.findAll(),
    {
      onSuccess: (res) => {
        setCities(res);
      },
      onError: () => {
        setCities([]);
      },
    },
  );

  const addOffer = (offer: any) => {
    const product = {
      name: offer.productName,
      expirationDate: offer.expirationDate,
    };

    console.log(offer);

    const offerToSend = {
      ...offer,
      date: new Date(),
      user: {
        id: 1,
      },
      product,
      city: JSON.parse(offer.city),
    };
    OfferService.save(offerToSend)
      .then(() => queryClient.invalidateQueries('offers-get'));
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
        <AddOfferForm
          cities={cities}
          submit={addOffer}
        />
      </Modal.Body>
    </Modal>
  // <AddOfferForm cities={cities} submit={(e) => console.log(e)} />
  );
}

export default AddOfferModal;
